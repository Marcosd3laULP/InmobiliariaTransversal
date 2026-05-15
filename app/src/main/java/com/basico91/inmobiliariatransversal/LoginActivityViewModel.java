package com.basico91.inmobiliariatransversal;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.basico91.inmobiliariatransversal.menu.MainActivity;
import com.basico91.inmobiliariatransversal.request.ApiClientt;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivityViewModel extends AndroidViewModel {

    private  MutableLiveData<String> Mensaje = new MutableLiveData<>();
    private Context context;
    public LoginActivityViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
    }


    public void recuperarDatos(String email, String clave){
        if(email == null || email.isEmpty() || clave == null || clave.isEmpty()){
            Mensaje.postValue("Complete todos los campos para poder ingresar");
        }
        ApiClientt.ServicioInmobiliaria servicio = ApiClientt.getServicio();
        Call<String> call = servicio.login(email, clave);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
            if(response.isSuccessful()){
                String token = response.body();
                Log.d("Token", token);
                Intent intent = new Intent(context, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            } else{
                Toast.makeText(context, "Correo o clave incorrectos", Toast.LENGTH_SHORT).show();
                Log.d("Error", response.message());
                Log.d("Error", response.code() + "");
                Log.d("Error", response.errorBody().toString() + " ");
            }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.d("MensajeError", t.getMessage());
            }
        });
    }

}
