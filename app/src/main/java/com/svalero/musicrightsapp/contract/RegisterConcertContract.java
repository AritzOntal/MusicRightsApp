package com.svalero.musicrightsapp.contract;

import com.svalero.musicrightsapp.domain.Concert;
import com.svalero.musicrightsapp.domain.Musician;

import java.time.LocalDate;

public interface RegisterConcertContract    {


    interface View {
        void showSuccessMessage(String message);
        void showError(String message);
    }

    interface Presenter {
        void registerConcert(String showTitle, String city, String province, LocalDate date, String status, Boolean performed, Float ticketPrice, Double latitude, Double longitude, Musician musician);
        void modifyConcert(long id, String showTitle, String city, String province, LocalDate date, String status, Boolean performed, Float ticketPrice, Double latitude, Double longitude);
}


    interface Model {
        interface OnRegisterListener {
            void onRegisterSuccess(String message);
            void onRegisterError(String message);
        }

        void registerConcert(Concert concert, OnRegisterListener listener);
        void modifyConcert(long id, Concert concert, OnRegisterListener listener);
    }


}
