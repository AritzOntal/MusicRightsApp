package com.svalero.musicrightsapp.model;

import android.content.Context;

import com.svalero.musicrightsapp.Db.AppDatabase;
import com.svalero.musicrightsapp.contract.FavoriteConcertsContract;
import com.svalero.musicrightsapp.domain.Concert;

import java.util.List;

public class FavoriteConcertModel implements FavoriteConcertsContract.Model {

    private Context context;


    public FavoriteConcertModel (Context context) {
        this.context = context;
    }


    @Override
    public void loadFavorites(OnLoadFavoritesListener listener) {
        AppDatabase db = AppDatabase.getInstance(context);

        List<Concert> concerts = db.concertDao().getAll();

        if(concerts != null) {
            listener.onLoadSuccess(concerts);
        } else {
            listener.onError("No existe una lista de conciertos guardados");
        }
    }

    @Override
    public void deleteFavorite(long id, OnLoadFavoritesListener listener) {
        AppDatabase db = AppDatabase.getInstance(context);
        db.concertDao().delete(id);
    }

    @Override
    public void addFavoriteConcert(Concert concert, OnLoadFavoritesListener listener) {
        AppDatabase db = AppDatabase.getInstance(context);
        long id = db.concertDao().insert(concert);

        if(id > 0) {
            listener.onSuccess("¡El concierto se ha guardado!");
        } else {
            listener.onError("El concierto no ha sido guardado");
        }
    }
}
