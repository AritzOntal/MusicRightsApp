package com.svalero.musicrightsapp.presenter;

import android.content.Context;
import com.svalero.musicrightsapp.contract.EditFavoriteContract;
import com.svalero.musicrightsapp.domain.Concert;
import com.svalero.musicrightsapp.model.EditFavoriteModel;

public class EditFavoritePresenter implements EditFavoriteContract.Presenter, EditFavoriteContract.Model.OnUpdateListener {

    private EditFavoriteContract.View view;
    private EditFavoriteContract.Model model;

    public EditFavoritePresenter(EditFavoriteContract.View view, Context context) {
        this.view = view;
        this.model = new EditFavoriteModel(context);
    }

    @Override
    public void updateConcert(Concert concert) {
        model.updateConcert(concert, this);
    }

    @Override
    public void onUpdateSuccess(String message) {
        view.showSuccessMessage(message);
    }

    @Override
    public void onUpdateError(String message) {
        view.showError(message);
    }
}