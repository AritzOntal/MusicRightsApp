package com.svalero.musicrightsapp.model;

import com.svalero.musicrightsapp.api.ConcertApi;
import com.svalero.musicrightsapp.api.ConcertApiInterface;
import com.svalero.musicrightsapp.contract.ConcertListContract;
import com.svalero.musicrightsapp.domain.Concert;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ConcertListModel implements ConcertListContract.Model {

    @Override
    public void loadConcerts(OnLoadLister listener) { // <--- Recibimos el "busca" (Listener)

        // PREPARAMOS LA LLAMADA
        ConcertApiInterface api = ConcertApi.buildInstance();
        Call<List<Concert>> call = api.getConcerts();

        // EJECUTAMOS
        call.enqueue(new Callback<List<Concert>>() {
            @Override
            public void onResponse(Call<List<Concert>> call, Response<List<Concert>> response) {
                if (response.isSuccessful() && response.body() != null) {

                    // ÉXITO: Avisamos al Listener pasando la lista
                    List<Concert> concerts = response.body();

                    //AVISAMOS AL LISTENER CON EL LISTENER EN EL QUE TENGO YA EL PRESENTER
                    listener.onLoadSucces(concerts);

                } else {
                    //Avisamos al Listener pasando el mensaje
                    listener.onLoadError("No se ha podido conectar con el servidor. Código de error:" + response.code());
                }
            }

            @Override
            public void onFailure(Call<List<Concert>> call, Throwable t) {
                // FALLO RED: Avisamos al Listener
                listener.onLoadError("Error Red: " + t.getMessage());
            }
        });
    }

    @Override
    public void deleteConcerts(long id, OnLoadLister listener) {
        ConcertApiInterface api = ConcertApi.buildInstance();
        Call<Concert> call = api.deleteConcert(id);

        call.enqueue(new Callback<Concert>() {
            @Override
            public void onResponse(Call<Concert> call, Response<Concert> response) {
                if (response.code()== 204) {
                    listener.onDeleteSucces("Concierto borrado con éxito");
                } else {
                    listener.onDeleteError("El concierto no ha sido borrado");
                }

            }

            @Override
            public void onFailure(Call<Concert> call, Throwable t) {
                listener.onDeleteError("No se ha podido conectar:" + t.getMessage());
            }
        });
    }


}
