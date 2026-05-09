package com.basico91.inmobiliariatransversal;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class RetrofitService {
    private static final String BASE_URL = "https://capacitacion.alwaysdata.net/";
    private static ApiClient apiInterface;

    public static ApiClient getApiInterface() {
        if(apiInterface == null){
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            apiInterface = retrofit.create(ApiClient.class);
        }
    return apiInterface;
    }

}
