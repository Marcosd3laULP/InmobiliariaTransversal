package com.basico91.inmobiliariatransversal.inquilino;

import android.app.Application;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.basico91.inmobiliariatransversal.modelos.Contrato;
import com.basico91.inmobiliariatransversal.modelos.Inmueble;
import com.basico91.inmobiliariatransversal.request.ApiClientt;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InquilinoViewModel extends AndroidViewModel {
    private MutableLiveData<List<Inmueble>> listaDeInmueblesConInquilinos = new MutableLiveData<>();
    private MutableLiveData<Contrato> inquilinoEnContrato = new MutableLiveData<>();
    public InquilinoViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<List<Inmueble>> getListaInmuebleConInquilino() { return listaDeInmueblesConInquilinos; }
    public LiveData<Contrato> getInquilino() { return inquilinoEnContrato; }

    public void cargarInmueblesConInquilinos(){
        String token = ApiClientt.obtenerToken(getApplication());

        ApiClientt.ServicioInmobiliaria servicio = ApiClientt.getServicio();
        Call<List<Inmueble>> call = servicio.listarInmueblesAlquilados(token);
        call.enqueue(new Callback<List<Inmueble>>() {
            @Override
            public void onResponse(Call<List<Inmueble>> call, Response<List<Inmueble>> response) {
                if (response.isSuccessful()) {
                    listaDeInmueblesConInquilinos.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Inmueble>> call, Throwable t) {
                Log.d("API_Failure", "Falla de red o conversión: " + t.getMessage());
            }
        });
    }
    public void recuperarInquilino(Bundle bundle){
        int idInmueble = bundle.getInt("idInmueble", -1);
    if(idInmueble != -1){
        String token = ApiClientt.obtenerToken(getApplication());
        ApiClientt.ServicioInmobiliaria servicio = ApiClientt.getServicio();
        Log.d("API_INQUILINO", "Se va a pedir el inquilino para el inmueble ID: " + idInmueble);
        Call<Contrato> call = servicio.traerContrato(token, idInmueble);
        call.enqueue(new Callback<Contrato>() {
            @Override
            public void onResponse(Call<Contrato> call, Response<Contrato> response) {
                Log.d("API_INQUILINO", "Código de respuesta: " + response.code());
                if (response.isSuccessful() && response.body() != null) {
                    Log.d("API_INQUILINO", "¡Inquilino recibido con éxito!: " + response.body().toString());
                    inquilinoEnContrato.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<Contrato> call, Throwable t) {
                Log.e("API_FAILURE", "Fallo total en la comunicación: " + t.getMessage());
            }
        });
    }
    }

}
