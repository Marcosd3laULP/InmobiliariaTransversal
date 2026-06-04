package com.basico91.inmobiliariatransversal.perfil;

import android.app.Application;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.basico91.inmobiliariatransversal.request.ApiClientt;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CambiarPassViewModel extends AndroidViewModel {

    private MutableLiveData<String> errorMensaje = new MutableLiveData<>();
    private MutableLiveData<Boolean> exito = new MutableLiveData<>();
    public CambiarPassViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<String> getError() {return errorMensaje; }
    public LiveData<Boolean> getExito() {return exito; }
    public void cambiarPasswords(String actual, String nueva){
       if(actual.isEmpty() || nueva.isEmpty()){
           errorMensaje.setValue("Para cambiar la contraseña, debe rellenar ambos campos");
           return;

       }

        if(actual == nueva){
            errorMensaje.setValue("La nueva contraseña, no puede ser igual a la actual");
            return;
        }

    String token = ApiClientt.obtenerToken(getApplication());
    ApiClientt.ServicioInmobiliaria servicio = ApiClientt.getServicio();
    Call<Void> call = servicio.cambiarContrasenia(token, actual, nueva);
    call.enqueue(new Callback<Void>() {
        @Override
        public void onResponse(Call<Void> call, Response<Void> response) {
            if(response.isSuccessful()){
                Toast.makeText(getApplication(), "Se cambió con exito la contraseña", Toast.LENGTH_SHORT).show();
                exito.postValue(true);
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
