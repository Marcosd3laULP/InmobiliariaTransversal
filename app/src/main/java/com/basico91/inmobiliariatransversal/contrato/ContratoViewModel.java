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
import com.basico91.inmobiliariatransversal.request.ApiClientt;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ContratoViewModel extends AndroidViewModel {

    private MutableLiveData<List<Inmueble>> listaDeInmuebleAlquiler = new MutableLiveData<>();

    private MutableLiveData<Inmueble> inmuebleMutable = new MutableLiveData<>();
    private MutableLiveData<Contrato> contratoMutable = new MutableLiveData<>();
    public ContratoViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<List<Inmueble>> getListaInmuebleAlquier(){return listaDeInmuebleAlquiler;}

    public LiveData<Contrato> getContratoMutable(){return contratoMutable; }

    public LiveData<Inmueble> getInmuebleMutable(){return inmuebleMutable; }

    public void CargarInmueblesAlquilados(){
        String token = ApiClientt.obtenerToken(getApplication());

        ApiClientt.ServicioInmobiliaria servicio = ApiClientt.getServicio();
        Call<List<Inmueble>> call = servicio.listarInmueblesAlquilados(token);
        call.enqueue(new Callback<List<Inmueble>>() {
            @Override
            public void onResponse(Call<List<Inmueble>> call, Response<List<Inmueble>> response) {
                if (response.isSuccessful()){
                    listaDeInmuebleAlquiler.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Inmueble>> call, Throwable t) {
                Log.d("API_Failure", "Falla de red o conversión: " + t.getMessage());
            }
        });
    }

    public void RecuperarContrato(Bundle bundle){
        Inmueble inmueble = (Inmueble)
        bundle.getSerializable("idInmueble", Inmueble.class);
        if(inmueble != null){
            inmuebleMutable.setValue(inmueble);
            int idInmueble = inmueble.getIdInmueble();

            String token = ApiClientt.obtenerToken(getApplication());
            ApiClientt.ServicioInmobiliaria servicio = ApiClientt.getServicio();
            Call<Contrato> call = servicio.traerContrato(token, idInmueble);
            call.enqueue(new Callback<Contrato>() {
                @Override
                public void onResponse(Call<Contrato> call, Response<Contrato> response) {
                    if(response.isSuccessful() && response.body() != null){
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
}
