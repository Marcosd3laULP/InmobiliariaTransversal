package com.basico91.inmobiliariatransversal.perfil;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.basico91.inmobiliariatransversal.modelos.Propietario;
import com.basico91.inmobiliariatransversal.request.ApiClientt;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PerfilViewModel extends AndroidViewModel {
    private MutableLiveData<Propietario> propietario = new MutableLiveData<>();
    private  MutableLiveData<String> Mensaje = new MutableLiveData<>();
    private Context context;
    public PerfilViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<Propietario> getPropiertario(){
        return propietario;
    }

    public void cargarDatos(){
        String token = ApiClientt.obtenerToken(getApplication());
        if(token == null || token.isEmpty()){
            Mensaje.postValue("token no encontrado");
            return;
        }
        String tokenFormateado = "Bearer" + token;

        ApiClientt.ServicioInmobiliaria servicio = ApiClientt.getServicio();
        Call<Propietario> call = servicio.obtenerPerfil(tokenFormateado);

        call.enqueue(new Callback<Propietario>() {
            @Override
            public void onResponse(Call<Propietario> call, Response<Propietario> response) {
                if(response.isSuccessful() && response.body() != null){
                    propietario.postValue(response.body());
                } else{
                    Log.d("Error", response.message());
                    Log.d("Error", response.code() + "");
                    Log.d("Error", response.errorBody().toString() + " ");
                }
            }

            @Override
            public void onFailure(Call<Propietario> call, Throwable t) {
                Log.d("MensajeError", t.getMessage());
            }
        });

    }
}
