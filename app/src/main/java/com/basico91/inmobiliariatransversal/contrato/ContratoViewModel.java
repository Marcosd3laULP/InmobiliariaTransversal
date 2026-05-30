package com.basico91.inmobiliariatransversal.contrato;

import android.app.Application;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.basico91.inmobiliariatransversal.modelos.Contrato;
import com.basico91.inmobiliariatransversal.modelos.Inmueble;
import com.basico91.inmobiliariatransversal.modelos.Pago;
import com.basico91.inmobiliariatransversal.request.ApiClientt;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ContratoViewModel extends AndroidViewModel {

    private MutableLiveData<List<Inmueble>> listaDeInmuebleAlquiler = new MutableLiveData<>();

    private MutableLiveData<Inmueble> inmuebleMutable = new MutableLiveData<>();
    private MutableLiveData<Contrato> contratoMutable = new MutableLiveData<>();

    private MutableLiveData<Pago> pagoMutable = new MutableLiveData<>();
    private MutableLiveData<List<Pago>> listaPagos = new MutableLiveData<>();
    public ContratoViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<List<Inmueble>> getListaInmuebleAlquier() {
        return listaDeInmuebleAlquiler;
    }

    public LiveData<Contrato> getContratoMutable() {
        return contratoMutable;
    }

    public LiveData<Pago> getPagoMutable() {
        return pagoMutable;
    }

    public LiveData<List<Pago>> getListaPagos(){return listaPagos; }

    public LiveData<Inmueble> getInmuebleMutable() {
        return inmuebleMutable;
    }

    public void CargarInmueblesAlquilados() {
        String token = ApiClientt.obtenerToken(getApplication());

        ApiClientt.ServicioInmobiliaria servicio = ApiClientt.getServicio();
        Call<List<Inmueble>> call = servicio.listarInmueblesAlquilados(token);
        call.enqueue(new Callback<List<Inmueble>>() {
            @Override
            public void onResponse(Call<List<Inmueble>> call, Response<List<Inmueble>> response) {
                if (response.isSuccessful()) {
                    listaDeInmuebleAlquiler.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Inmueble>> call, Throwable t) {
                Log.d("API_Failure", "Falla de red o conversión: " + t.getMessage());
            }
        });
    }

    public void RecuperarContrato(Bundle bundle) {
       int idInmueble = bundle.getInt("idInmueble", -1);

        if (idInmueble != -1) {

            String token = ApiClientt.obtenerToken(getApplication());
            ApiClientt.ServicioInmobiliaria servicio = ApiClientt.getServicio();
            Log.d("API_CONTRATO", "Se va a pedir el contrato para el inmueble ID: " + idInmueble);
            Call<Contrato> call = servicio.traerContrato(token, idInmueble);
            call.enqueue(new Callback<Contrato>() {
                @Override
                public void onResponse(Call<Contrato> call, Response<Contrato> response) {
                    Log.d("API_CONTRATO", "Código de respuesta: " + response.code());
                    if (response.isSuccessful() && response.body() != null) {
                        Log.d("API_CONTRATO", "¡Contrato recibido con éxito!: " + response.body().toString());
                        contratoMutable.postValue(response.body());
                    }
                }

                @Override
                public void onFailure(Call<Contrato> call, Throwable t) {
                    Log.e("API_FAILURE", "Fallo total en la comunicación: " + t.getMessage());
                }
            });
        }
    }

    public void recuperarPagosDeUnContrato(Bundle bundle) {
        Contrato contrato = (Contrato)
                bundle.getSerializable("idContrato", Contrato.class);
        if (contrato != null) {
            contratoMutable.setValue(contrato);
            int idContrato = contrato.getIdContrato();
            String token = ApiClientt.obtenerToken(getApplication());

            ApiClientt.ServicioInmobiliaria servicio = ApiClientt.getServicio();
            Call<List<Pago>> call = servicio.traerPagos(token, idContrato);
            call.enqueue(new Callback<List<Pago>>() {
                @Override
                public void onResponse(Call<List<Pago>> call, Response<List<Pago>> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        pagoMutable.postValue((Pago) response.body());
                    } else {
                        Log.d("API_PAGOS", "error codigo: " + response.code());
                    }
                }

                @Override
                public void onFailure(Call<List<Pago>> call, Throwable t) {
                    Log.d("API_PAGOS", "Error de conexion: " + t.getMessage());
                }
            });
        }
    }
}