package com.basico91.inmobiliariatransversal.perfil;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.basico91.inmobiliariatransversal.modelos.Propietario;
import com.basico91.inmobiliariatransversal.request.ApiClientt;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditarPerfilViewModel extends AndroidViewModel {

    private MutableLiveData<Propietario> propietarioEdit = new MutableLiveData<>();
    private MutableLiveData<String> mensaje = new MutableLiveData<>();
    private MutableLiveData<Boolean> exito = new MutableLiveData<>();
    //private Context context;
    public EditarPerfilViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<Propietario> getPropietarioEdit(){
        return propietarioEdit;
    }
    public LiveData<String> getMensaje(){
        return mensaje;
    }
    public LiveData<Boolean> getExito(){
        return exito;
    }

    public void RecuperarPropietarioBundle(Bundle bundle){
        Propietario propietario = (Propietario)
                bundle.getSerializable("propietario");
        propietarioEdit.setValue(propietario);
    }
    public void setPropietarioActual(Propietario propietario){
        if (propietario != null){
            propietarioEdit.setValue(propietario);
        }
    }


    public void guardarCambios(String nombre, String apellido, String dni, String telefono, String email){
        if(nombre.trim().isEmpty() || apellido.trim().isEmpty() || dni.trim().isEmpty() ||  telefono.trim().isEmpty() || email.trim().isEmpty()){
            mensaje.postValue("No puede dejar campos vacios");
            return;
        }
        Propietario propietarioActual = propietarioEdit.getValue();
        if(propietarioActual == null){
            mensaje.postValue("Error interno: no se encontraon los datos del propietario");
            return;
        }

        propietarioActual.setNombre(nombre);
        propietarioActual.setApellido(apellido);
        propietarioActual.setDni(dni);
        propietarioActual.setEmail(email);
        propietarioActual.setTelefono(telefono);


        String token = ApiClientt.obtenerToken(getApplication());
        if(token == null || token.isEmpty()){
            mensaje.postValue("Sesion expirada inicie sesion nuevamente");
            return;
        }


        ApiClientt.ServicioInmobiliaria servicio = ApiClientt.getServicio();
        Call<Propietario> call = servicio.actualizarPerfil(token, propietarioActual);
        call.enqueue(new Callback<Propietario>() {
            @Override
            public void onResponse(Call<Propietario> call, Response<Propietario> response) {
                if(response.isSuccessful() && response.body() != null){
                    propietarioEdit.postValue(response.body());
                    mensaje.postValue("Perfil actualizado con exito");
                    exito.postValue(true);
                } else {
                    mensaje.postValue("Error al actualizar el perfil");
                }
            }

            @Override
            public void onFailure(Call<Propietario> call, Throwable t) {
                Log.d("onFailure", t.getMessage());
            }
        });
    }
}
