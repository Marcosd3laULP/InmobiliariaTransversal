package com.basico91.inmobiliariatransversal.inmueble;

import android.app.Application;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.SavedStateHandle;

import com.basico91.inmobiliariatransversal.modelos.Inmueble;
import com.basico91.inmobiliariatransversal.request.ApiClientt;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InmuebleViewModel extends AndroidViewModel {

    private MutableLiveData<List<Inmueble>> listadoInmuebleM = new MutableLiveData<>();
    private MutableLiveData<Inmueble> inmuebleDetalleM = new MutableLiveData<>();
    public InmuebleViewModel(@NonNull Application application, @NonNull SavedStateHandle savedStateHandle) {
        super(application);
        Inmueble inmueble = savedStateHandle.get("Inmueble");

        if(inmueble !=null){
            inmuebleDetalleM.setValue(inmueble);
        }
    }

    public LiveData<List<Inmueble>> getListadoInmuebles(){
        return listadoInmuebleM;
    }

    public LiveData<Inmueble> getInmuebleDetalle(){ return inmuebleDetalleM; }


    public void cargarInmuebles(){
        String token = ApiClientt.obtenerToken(getApplication());

        ApiClientt.ServicioInmobiliaria servicio = ApiClientt.getServicio();
        Call<List<Inmueble>> call = servicio.listarTodosInmuebles(token);
        call.enqueue(new Callback<List<Inmueble>>() {
            @Override
            public void onResponse(Call<List<Inmueble>> call, Response<List<Inmueble>> response) {
                if(response.isSuccessful()){
                    listadoInmuebleM.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Inmueble>> call, Throwable t) {
                Log.d("API_Failure", "Falla de red o conversión: " + t.getMessage());
            }
        });
    }
}
