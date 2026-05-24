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

    private MutableLiveData<String> textoDisponible = new MutableLiveData<>();
    public InmuebleViewModel(@NonNull Application application) {
        super(application);

    }

    public LiveData<List<Inmueble>> getListadoInmuebles(){
        return listadoInmuebleM;
    }

    public LiveData<Inmueble> getInmuebleDetalle(){ return inmuebleDetalleM; }

    public LiveData<String> getTextoDisponible(){
        return textoDisponible;
    }

    public void recuperarInmueble(Bundle bundle){
        Inmueble inmueble = (Inmueble)
        bundle.getSerializable("inmueble", Inmueble.class);
        inmuebleDetalleM.setValue(inmueble);
        if(inmueble.isDisponible()){
            textoDisponible.postValue("Disponible");
        } else {
            textoDisponible.postValue("No disponible");
        }

    }

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

    public void cambiarDisponibilidad(boolean disponible){
        Inmueble inmueble = inmuebleDetalleM.getValue();

        inmueble.setDisponible(disponible);
        String token = ApiClientt.obtenerToken(getApplication());
        ApiClientt.ServicioInmobiliaria servicio = ApiClientt.getServicio();

        Call<Inmueble> call = servicio.cambiarDisponibilidad(token, inmueble);

        call.enqueue(new Callback<Inmueble>() {
            @Override
            public void onResponse(Call<Inmueble> call, Response<Inmueble> response) {
                if(response.isSuccessful() && response.body()!=null){
                    Inmueble inmuebleActualizado = response.body();
                    inmuebleDetalleM.postValue(inmuebleActualizado);
                    if(inmueble.isDisponible()){
                        textoDisponible.postValue("Disponible");
                    } else {
                        textoDisponible.postValue("No disponible");
                    }

                }
            }

            @Override
            public void onFailure(Call<Inmueble> call, Throwable t) {

            }
        });

    }
}
