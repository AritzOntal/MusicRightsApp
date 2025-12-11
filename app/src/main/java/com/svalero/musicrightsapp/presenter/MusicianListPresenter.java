package com.svalero.musicrightsapp.presenter;

import com.svalero.musicrightsapp.contract.MusicianListContract;
import com.svalero.musicrightsapp.domain.Musician;
import com.svalero.musicrightsapp.model.MusicianListModel;

import java.util.List;

public class MusicianListPresenter implements MusicianListContract.Presenter, MusicianListContract.Model.OnLoadListener {

    private MusicianListContract.Model model;
    private MusicianListContract.View view;

    public MusicianListPresenter(MusicianListContract.View view) {
        this.view = view;
        this.model = new MusicianListModel();
    }

    @Override
    public void loadMusicians() {
        // Le pasamos 'this' porque este presenter implementa el Listener
        model.loadMusicians(this);
    }

    @Override
    public void deleteMusician(long id) {
        model.deleteMusician(id, this);
    }

    @Override
    public void onLoadSuccess(List<Musician> musicians) {
        // Pasamos la lista a la vista para que la pinte
        view.showMusicians(musicians);
    }

    @Override
    public void onLoadError(String message) {
        view.showError(message);
    }

    @Override
    public void onDeleteSuccess(String message) {
        view.showMessage(message);
        view.resetList();
    }

    @Override
    public void onDeleteError(String message) {
        view.showError(message);
    }
}