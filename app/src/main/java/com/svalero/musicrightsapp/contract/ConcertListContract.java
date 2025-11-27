package com.svalero.musicrightsapp.contract;

import com.svalero.musicrightsapp.domain.Concert;

import java.util.List;

public interface ConcertListContract {

    //TODO LO QUE HAYA AQUI SE PUEDE UTILIZAR EN CUALQUIER CLASE QUE IMPLEMENTE ESTE CONTRATO

    interface Model  {
        interface OnLoadLister {
            void onLoadSucces(List<Concert> concert);
            void onLoadError(String message);
        }
        void loadConcerts(OnLoadLister listener);  //AQUI PIDE A ALGUIEN "QUE SEPA ESCUCHAR" (COMO EL PRESENTER)
    }

    interface View {
        void showConcerts(List<Concert> concerts);
        void showMessage(String message);
        void showError(String message);
    }

    interface Presenter {
        void loadConcerts();
    }
}
