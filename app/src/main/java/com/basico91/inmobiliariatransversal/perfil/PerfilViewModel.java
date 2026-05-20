package com.basico91.inmobiliariatransversal.perfil;

import android.app.Application;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.basico91.inmobiliariatransversal.modelos.Propietario;
import com.basico91.inmobiliariatransversal.request.ApiClientt;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PerfilViewModel extends AndroidViewModel {
    private final MutableLiveData<Propietario> propietario = new MutableLiveData<>();
    private final MutableLiveData<String> mensaje = new MutableLiveData<>();

    public PerfilViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<Propietario> getPropiertario(){
        return propietario;
    }

    public LiveData<String> getMensaje(){
        return mensaje;
    }

    public void cargarDatos(){
        String token = ApiClientt.obtenerToken(getApplication());
        if(token == null || token.isEmpty()){
            mensaje.postValue("token no encontrado");
            return;
        }



        ApiClientt.ServicioInmobiliaria servicio = ApiClientt.getServicio();
        Call<Propietario> call = servicio.obtenerPerfil(token);

        call.enqueue(new Callback<Propietario>() {
            @Override
            public void onResponse(Call<Propietario> call, Response<Propietario> response) {
                if(response.isSuccessful() && response.body() != null){
                    propietario.postValue(response.body());
                } else {
                    Log.d("API_Error", "Mensaje: " + response.message());
                    Log.d("API_Error", "Código Status: " + response.code());

                    // CORREGIDO: Uso de .string() con try-catch para ver el verdadero error del servidor
                    if (response.errorBody() != null) {
                        try {
                            Log.d("API_Error", "Cuerpo del error: " + response.errorBody().string());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<Propietario> call, Throwable t) {
                Log.d("API_Failure", "Falla de red o conversión: " + t.getMessage());
            }
        });
    }
}