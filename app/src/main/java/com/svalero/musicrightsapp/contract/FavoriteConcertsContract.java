package com.svalero.musicrightsapp.contract;

import com.svalero.musicrightsapp.domain.Concert;

import java.util.List;

    public interface FavoriteConcertsContract {

        interface View {
            void showFavoriteConcerts(List<Concert> concerts);
            void showMessage(String message);
            void showError(String message);
        }

        interface Presenter {
            void loadFavoriteConcerts();
            void deleteFavoriteConcert(long id);
            void addFavoriteConcert(Concert concert);
        }

        interface Model {
            interface OnLoadFavoritesListener {
                void onLoadSuccess(List<Concert> concerts);
                void onError(String message);
                void onSuccess(String message);
            }

            void loadFavorites(OnLoadFavoritesListener listener);
            void deleteFavorite(long id, OnLoadFavoritesListener listener);
            void addFavoriteConcert(Concert concert, OnLoadFavoritesListener listener);
        }
}
