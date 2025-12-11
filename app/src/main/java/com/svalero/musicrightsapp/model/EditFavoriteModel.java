package com.svalero.musicrightsapp.model;

import android.content.Context;

import com.svalero.musicrightsapp.Db.AppDatabase;
import com.svalero.musicrightsapp.contract.EditFavoriteContract;
import com.svalero.musicrightsapp.domain.Concert;

public class EditFavoriteModel implements EditFavoriteContract.Model {

    private Context context;

    public EditFavoriteModel(Context context) {
        this.context = context;
    }

    @Override
    public void updateConcert(Concert concert, OnUpdateListener listener) {
        AppDatabase db = AppDatabase.getInstance(context);

        try {

            db.concertDao().update(concert);
            listener.onUpdateSuccess("Concierto favorito editado");

        } catch (Exception e){
            listener.onUpdateSuccess("Erro al actualizar: " + e.getMessage());
        }
    }
}
