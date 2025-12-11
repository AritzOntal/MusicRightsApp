package com.svalero.musicrightsapp.presenter;

import android.content.Context;

import com.svalero.musicrightsapp.contract.FavoriteConcertsContract;
import com.svalero.musicrightsapp.domain.Concert;
import com.svalero.musicrightsapp.model.FavoriteConcertModel;
import com.svalero.musicrightsapp.view.FavoriteConcertsView;

import java.util.List;

public class FavoriteConcertsPresenter implements FavoriteConcertsContract.Presenter, FavoriteConcertsContract.Model.OnLoadFavoritesListener {

    private FavoriteConcertsContract.Model model;
    private FavoriteConcertsContract.View view;

    public FavoriteConcertsPresenter(FavoriteConcertsContract.View view, Context context){
        this.model = new FavoriteConcertModel(context);
        this.view = view;
    }


    @Override
    public void onLoadSuccess(List<Concert> concerts) {
        view.showFavoriteConcerts(concerts);
    }

    @Override
    public void onError(String message) {
        view.showError(message);
    }

    @Override
    public void onSuccess(String message) {
        view.showMessage(message);
    }

    @Override
    public void loadFavoriteConcerts() {
        model.loadFavorites(this);
    }

    @Override
    public void deleteFavoriteConcert(long id) {
        model.deleteFavorite(id, this);
    }

    @Override
    public void addFavoriteConcert(Concert concert) {
        model.addFavoriteConcert(concert, this);
    }
}
