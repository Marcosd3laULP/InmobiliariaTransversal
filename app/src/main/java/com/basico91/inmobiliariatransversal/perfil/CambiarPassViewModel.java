package com.basico91.inmobiliariatransversal.perfil;

import android.app.Application;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.basico91.inmobiliariatransversal.request.ApiClientt;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CambiarPassViewModel extends AndroidViewModel {


    public CambiarPassViewModel(@NonNull Application application) {
        super(application);
    }

    public void cambiarPasswords(String actual, String nueva){
    String token = ApiClientt.obtenerToken(getApplication());
    ApiClientt.ServicioInmobiliaria servicio = ApiClientt.getServicio();
    Call<Void> call = servicio.cambiarContrasenia(token, actual, nueva);
    call.enqueue(new Callback<Void>() {
        @Override
        public void onResponse(Call<Void> call, Response<Void> response) {
            if(response.isSuccessful()){
                Toast.makeText(getApplication(), "Se cambió con exito la contraseña", Toast.LENGTH_SHORT).show();
            } else{
                Toast.makeText(getApplication(), "Error al cambiar la contraseña, verifique la actual", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onFailure(Call<Void> call, Throwable t) {
            Toast.makeText(getApplication(), t.getMessage(), Toast.LENGTH_SHORT).show();
        }
    });
    }
}
