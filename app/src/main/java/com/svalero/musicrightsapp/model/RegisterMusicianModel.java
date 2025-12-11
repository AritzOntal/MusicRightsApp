package com.svalero.musicrightsapp.model;

import com.svalero.musicrightsapp.api.ConcertApi;
import com.svalero.musicrightsapp.api.ConcertApiInterface;
import com.svalero.musicrightsapp.contract.RegisterMusicianContract;
import com.svalero.musicrightsapp.domain.Musician;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterMusicianModel implements RegisterMusicianContract.Model {

    @Override
    public void registerMusician(Musician musician, OnRegisterListener listener) {

        ConcertApiInterface api = ConcertApi.buildInstance();
        Call<Musician> call = api.addMusician(musician);

        call.enqueue(new Callback<Musician>() {
            @Override
            public void onResponse(Call<Musician> call, Response<Musician> response) {
                if (response.code() == 201) {
                    listener.onRegisterSuccess(response.body());

                } else if (response.code() == 400) {
                    listener.onRegisterError("Los datos del músico no son correctos (400)");

                } else if (response.code() == 500) {
                    listener.onRegisterError("Problema con el servidor (500)");
                } else {
                    listener.onRegisterError("Error desconocido: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<Musician> call, Throwable t) {
                listener.onRegisterError("No se ha podido conectar: " + t.getMessage());
            }
        });
    }

    @Override
    public void modifyMusician(long id, Musician musician, OnRegisterListener listener) {
        ConcertApiInterface api = ConcertApi.buildInstance();

        Call<Musician> call = api.modifyMusician(id, musician);

        call.enqueue(new Callback<Musician>() {
            @Override
            public void onResponse(Call<Musician> call, Response<Musician> response) {
                if (response.isSuccessful()) {
                    listener.onRegisterSuccess(response.body());
                } else {
                    listener.onRegisterError("No se pudo modificar el músico. Código: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<Musician> call, Throwable t) {
                listener.onRegisterError("Error de conexión al modificar: " + t.getMessage());
            }
        });
    }
}