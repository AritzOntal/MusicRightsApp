package com.svalero.musicrightsapp.model;

import com.svalero.musicrightsapp.api.ConcertApi;
import com.svalero.musicrightsapp.api.ConcertApiInterface;
import com.svalero.musicrightsapp.contract.MusicianListContract;
import com.svalero.musicrightsapp.domain.Musician;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MusicianListModel implements MusicianListContract.Model {

    @Override
    public void loadMusicians(OnLoadListener listener) {

        ConcertApiInterface api = ConcertApi.buildInstance();

        Call<List<Musician>> call = api.getMusicians();

        call.enqueue(new Callback<List<Musician>>() {
            @Override
            public void onResponse(Call<List<Musician>> call, Response<List<Musician>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Musician> musicians = response.body();
                    listener.onLoadSuccess(musicians);
                } else {
                    listener.onLoadError("Error al cargar músicos. Código: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<List<Musician>> call, Throwable t) {
                listener.onLoadError("Error de red: " + t.getMessage());
            }
        });
    }

    @Override
    public void deleteMusician(long id, OnLoadListener listener) {
        ConcertApiInterface api = ConcertApi.buildInstance();

        Call<Musician> call = api.deleteMusicians(id);

        call.enqueue(new Callback<Musician>() {
            @Override
            public void onResponse(Call<Musician> call, Response<Musician> response) {
                if (response.code() == 204) {
                    listener.onDeleteSuccess("Músico borrado con éxito");
                } else {
                    listener.onDeleteError("EL músico no se pudo borrar por datos asociados");
                }
            }

            @Override
            public void onFailure(Call<Musician> call, Throwable t) {
                listener.onDeleteError("Error de conexión: " + t.getMessage());
            }
        });
    }
}