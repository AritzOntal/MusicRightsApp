package com.svalero.musicrightsapp.contract;

import com.svalero.musicrightsapp.domain.Concert;

public interface EditFavoriteContract {

    interface View {
        void showSuccessMessage(String message);
        void showError(String message);
    }

    interface Presenter {
        void updateConcert(Concert concert);
    }

    interface Model {
        interface OnUpdateListener {
            void onUpdateSuccess(String message);
            void onUpdateError(String message);
        }

        void updateConcert(Concert concert, OnUpdateListener listener);
    }
}