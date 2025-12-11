package com.svalero.musicrightsapp.model;

import com.svalero.musicrightsapp.api.ConcertApi;
import com.svalero.musicrightsapp.api.ConcertApiInterface;
import com.svalero.musicrightsapp.contract.RegisterConcertContract;

import com.svalero.musicrightsapp.domain.Concert;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterConcertModel implements RegisterConcertContract.Model {
    
    @Override
    public void registerConcert(Concert concert, OnRegisterListener listener) {

        ConcertApiInterface api = ConcertApi.buildInstance();
        Call<Concert> call = api.addConcert(concert);
        call.enqueue(new Callback<Concert>() {
            @Override
            public void onResponse(Call<Concert> call, Response<Concert> response) {
                if (response.code()== 201) {
                    listener.onRegisterSuccess(response.body());

                } else if (response.code() == 400) {
                    listener.onRegisterError("Los datos de registro no son correctos" + response.code());

                } else if (response.code() == 500) {
                    listener.onRegisterError("Problema con el servidor" + response.code());
                }
            }

            @Override
            public void onFailure(Call<Concert> call, Throwable t) {
                listener.onRegisterError("No se ha podido conectar:" + t.getMessage());
            }
        });
    }

    @Override
    public void modifyConcert(long id, Concert concert, OnRegisterListener listener) {
        ConcertApiInterface api = ConcertApi.buildInstance();
        Call<Concert> call = api.modifyConcert(id, concert);

        call.enqueue(new Callback<Concert>() {
            @Override
            public void onResponse(Call<Concert> call, Response<Concert> response) {
                if (response.isSuccessful()) {
                    listener.onRegisterSuccess(response.body());
                } else {
                    listener.onRegisterError("No se pudo modificar. Código: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<Concert> call, Throwable t) {
                listener.onRegisterError("Error de conexión: " + t.getMessage());
            }
        });
    }
}
