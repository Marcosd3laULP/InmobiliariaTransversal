package com.basico91.inmobiliariatransversal.inmueble;

import android.app.Application;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.basico91.inmobiliariatransversal.modelos.Inmueble;
import com.basico91.inmobiliariatransversal.request.ApiClientt;
import com.google.gson.Gson;

import java.io.File;
import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.MediaType;

public class CargarInmuebleViewModel extends AndroidViewModel {

    private MutableLiveData<Boolean> altaDeInmueble = new MutableLiveData<>();
    private MutableLiveData<String> errorMensaje = new MutableLiveData<>();
    public CargarInmuebleViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<Boolean> getAltaDeInmueble() {return altaDeInmueble; }
    public LiveData<String> getError() {return errorMensaje; }

    public void cargarInmueble(Uri uriImagen, String direccion, String valor, String uso, String tipo, String ambientes, String superficie, String latitud, String longitud){

       if(direccion.isEmpty() || valor.isEmpty() || uso.isEmpty() || tipo.isEmpty() || ambientes.isEmpty() || superficie.isEmpty() || latitud.isEmpty() || longitud.isEmpty()){
           errorMensaje.setValue("Debe completar todos los campos");
           return;
       }

        if(uriImagen == null){
            errorMensaje.setValue("Debe seleccionar una imagen para el inmueble");
            return;
        }

        try {
            //Par = parseado
            int ambientesPar = Integer.parseInt(ambientes);
            double superficiePar = Double.parseDouble(superficie);
            double valorPar = Double.parseDouble(valor);
            double latitudPar = Double.parseDouble(latitud);
            double longitudPar = Double.parseDouble(longitud);

            Inmueble nuevoInmueble = new Inmueble();
            nuevoInmueble.setDireccion(direccion);
            nuevoInmueble.setUso(uso);
            nuevoInmueble.setTipo(tipo);
            nuevoInmueble.setAmbientes(ambientesPar);
            nuevoInmueble.setSuperficie(superficiePar);
            nuevoInmueble.setValor(valorPar);
            nuevoInmueble.setLatitud(latitudPar);
            nuevoInmueble.setLongitud(longitudPar);
            nuevoInmueble.setDisponible(false);
            nuevoInmueble.setIdPropietario(3);
            nuevoInmueble.setTieneContratoVigente(false);


            String rutaReal = rutaDeLaImagen(uriImagen, getApplication());
            if (rutaReal == null) {
                errorMensaje.setValue("No se pudo procesar la imagen");
                return;
            }
            File file = new File(rutaReal);

            RequestBody requestFile = RequestBody.create(MediaType.parse("image/*"), file);
            MultipartBody.Part bodyImagen = MultipartBody.Part.createFormData("imagen", file.getName(), requestFile);

            Gson gson = new Gson();
            String jsonInmueble = gson.toJson(nuevoInmueble);

            RequestBody bodyInmuebleJson = RequestBody.create(MediaType.parse("application/json"), jsonInmueble);

            String token = ApiClientt.obtenerToken(getApplication());

            ApiClientt.ServicioInmobiliaria servicio = ApiClientt.getServicio();
            Call<Inmueble> call = servicio.cargarInmueble(token, bodyImagen, bodyInmuebleJson);
            call.enqueue(new Callback<Inmueble>() {
                @Override
                public void onResponse(Call<Inmueble> call, Response<Inmueble> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        altaDeInmueble.setValue(true);
                    } else {
                        errorMensaje.setValue("Error al cargar: " + response.code());
                        try {
                            String detalleError = response.errorBody().string();
                            Log.e("API_ERROR_400", "Detalle del servidor: " + detalleError);
                            errorMensaje.setValue("Error al cargar: " + detalleError);
                        } catch (Exception e) {
                            errorMensaje.setValue("Error al cargar: " + response.code());
                        }
                    }
                }

                @Override
                public void onFailure(Call<Inmueble> call, Throwable t) {
                    errorMensaje.setValue("Fallo de conexión: " + t.getMessage());
                }
            });

        }catch (NumberFormatException e){
            errorMensaje.setValue("los campos ambientes, superficie y valor solo aceptan valores numericos");
        }
    }

    private String rutaDeLaImagen(Uri contentUri, Context context) {
        String[] projection = { MediaStore.Images.Media.DATA };
        Cursor cursor = context.getContentResolver().query(contentUri, projection, null, null, null);
        if (cursor == null) return null;
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String path = cursor.getString(column_index);
        cursor.close();
        return path;
    }
}

