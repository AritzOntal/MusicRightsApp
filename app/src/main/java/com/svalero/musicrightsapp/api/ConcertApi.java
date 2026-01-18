package com.svalero.musicrightsapp.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ConcertApi {

    // Construye la conexión
    public static ConcertApiInterface buildInstance() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:8080/") //IP PARA RED EMULADOR
                //.baseUrl("http://192.168.1.129:8080/")// IP DE MI WIFI
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(ConcertApiInterface.class);
    }
}
