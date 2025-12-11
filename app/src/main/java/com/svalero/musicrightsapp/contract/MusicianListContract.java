package com.svalero.musicrightsapp.contract;

import com.svalero.musicrightsapp.domain.Musician;

import java.util.List;

public interface MusicianListContract {

    interface Model {
        interface OnLoadListener {
            void onLoadSuccess(List<Musician> musicians);
            void onLoadError(String message);
            void onDeleteSuccess(String message);
            void onDeleteError(String message);
        }

        void loadMusicians(OnLoadListener listener);
        void deleteMusician(long id, OnLoadListener listener);
    }

    interface View {
        void showMusicians(List<Musician> musicians);
        void showMessage(String message);
        void showError(String message);
        void resetList();
    }

    interface Presenter {
        void loadMusicians();
        void deleteMusician(long id);
    }
}