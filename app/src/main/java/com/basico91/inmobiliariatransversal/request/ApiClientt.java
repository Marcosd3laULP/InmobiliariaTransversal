package com.basico91.inmobiliariatransversal.request;



import android.content.Context;
import android.content.SharedPreferences;

import com.basico91.inmobiliariatransversal.modelos.Contrato;
import com.basico91.inmobiliariatransversal.modelos.Inmueble;
import com.basico91.inmobiliariatransversal.modelos.Pago;
import com.basico91.inmobiliariatransversal.modelos.Propietario;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;

public class ApiClientt {
    public final static String BASE_URL ="https://capacitacion.alwaysdata.net/";
    public static ServicioInmobiliaria getServicio(){
        Gson gson = new GsonBuilder().setLenient().create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        return retrofit.create(ServicioInmobiliaria.class);
    }

    public interface ServicioInmobiliaria{
        @FormUrlEncoded
        @POST("api/Propietarios/login")
        Call<String> login(@Field("Usuario") String usuario, @Field("Clave") String clave);

        @GET("api/Propietarios")
        Call<Propietario> obtenerPerfil(@Header("Authorization") String token);

        @PUT("api/Propietarios/actualizar") //SALE EN GRIS COMO SI NUNCA LO HUBIERAN USADO
        Call<Propietario> actualizarPerfil(@Header("Authorization") String token, @Body Propietario propietario);

        @FormUrlEncoded
        @PUT("api/Propietarios/changePassword")
        Call<Void> cambiarContrasenia(@Header("Authorization") String token, @Field("currentPassword") String actual, @Field("newPassword") String nueva);

        @GET("api/Inmuebles")
        Call<List<Inmueble>> listarTodosInmuebles(@Header("Authorization") String token);

        @Multipart
        @POST("api/Inmuebles/cargar")
        Call<Inmueble> cargarInmueble(@Header("Authorization") String token, @Part MultipartBody.Part imagen, @Part("inmueble") RequestBody inmuebleJson);

        @PUT("api/Inmuebles/actualizar")
        Call<Inmueble> cambiarDisponibilidad(@Header("Authorization") String token, @Body Inmueble inmueble);

        @GET("api/Inmuebles/GetContratoVigente")
        Call<List<Inmueble>> listarInmueblesAlquilados(@Header("Authorization") String token);

        @GET("api/contratos/inmueble/{id}")
        Call<Contrato> traerContrato(@Header("Authorization") String token, @Path("id") int idInmueble);

        @GET("api/pagos/contrato/{id}")
        Call<List<Pago>> traerPagos(@Header("Authorization") String token, @Path("id") int idContrato);
    }

    public  static  void recuperarToken(Context context, String token) {
        SharedPreferences sp  = context.getSharedPreferences("token.xml", context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("token", "Bearer " +token);
        editor.apply();
    }
    public static String obtenerToken(Context context) {
        SharedPreferences sp  = context.getSharedPreferences("token.xml", context.MODE_PRIVATE);
        return sp.getString("token", null);
    }
}
