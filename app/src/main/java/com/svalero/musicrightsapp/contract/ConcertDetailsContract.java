package com.svalero.musicrightsapp.contract;

import com.svalero.musicrightsapp.domain.Concert;

import java.util.List;

public interface ConcertDetailsContract {
    interface Model  {
        interface OnLoadLister {
            void onLoadSucces(Concert concert);
            void onLoadError(String message);
        }
        void loadConcert(long id, ConcertListContract.Model.OnLoadLister listener);
    }

    interface View {
        void showMessage(String message);
        void showError(String message);
        void showDetailsConcerts(Concert concert);
    }

    interface Presenter {
        void detailsConcert(long id);
    }
}
