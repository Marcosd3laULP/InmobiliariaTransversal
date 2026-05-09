package com.basico91.inmobiliariatransversal;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivityViewModel extends AndroidViewModel {

    private MutableLiveData usuario = new MutableLiveData();
    private MutableLiveData<String> token;
    private MutableLiveData<String> errorM;
    public LoginActivityViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<String> getToken() {
        if(token == null) {
            token = new MutableLiveData<>();
        }
        return token;
    }

    public LiveData<String> getErrorM() {
        if (errorM == null) {
            errorM = new MutableLiveData<>();
        }
        return errorM;
    }
    public void login(String usuario, String clave) {

        if(usuario == null || usuario.isEmpty() || clave == null || clave.isEmpty()){
            errorM.postValue("Complete todos los campos para poder ingresar");
            return;
        }

        ApiClient api = RetrofitService.getApiInterface();
        Call<String> call = api.login(usuario, clave);

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if(response.isSuccessful()){
                    token.postValue(response.body());
                    ApiRetrofit.SharedPref.guardarToken(getApplication(), "Bearer " + token);
                } else{
                    errorM.postValue("Usuario o clave incorrectos");
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
        errorM.postValue("Error de conexion: " + t.getMessage());
            }
        });
    }
}
