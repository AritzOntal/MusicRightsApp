package com.svalero.musicrightsapp.presenter;

import com.svalero.musicrightsapp.contract.ConcertListContract;
import com.svalero.musicrightsapp.domain.Concert;
import com.svalero.musicrightsapp.model.ConcertListModel;

import java.util.List;

public class ConcertListPresenter implements ConcertListContract.Presenter, ConcertListContract.Model.OnLoadLister {

    private ConcertListContract.Model model;
    private ConcertListContract.View view;

    public ConcertListPresenter(ConcertListContract.View view) {
        this.view = view;
        this.model = new ConcertListModel();
    }

    //AQUIÍ SOY CAPAZ DE RECICIBR RESPUESTAS DEL MODELO GRACIAS A IMPLEMENTACION DEL INTERFACE (FIRMA DEL CONTRATO)
    @Override
    public void loadConcerts() {
        //LE PASARÉ AL MODEL "YO MISMO"
        model.loadConcerts(this);

    }

    @Override
    public void onLoadSucces(List<Concert> concerts) {
        view.showConcerts(concerts);
        view.showMessage("Los conciertos se han cargado con éxito");
    }

    @Override
    public void onLoadError(String message) {
        view.showError(message);
    }
}
