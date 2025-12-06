package com.svalero.musicrightsapp.contract;

import com.svalero.musicrightsapp.domain.Concert;
import com.svalero.musicrightsapp.domain.Musician;

import java.time.LocalDate;

public interface RegisterConcertContract    {


    interface View {
        void showSuccessMessage(String message);
        void showError(String message);
        void resetForm();
    }

    interface Presenter {
        void registerConcert(String showTitle, String city, String province, LocalDate date, String status, Boolean performed, Float ticketPrice, float latitude, float longitude, Musician musician);
}


    interface Model {
        interface OnRegisterListener {
            void onRegisterSuccess(Concert createdConcert);
            void onRegisterError(String message);
        }

        // Método para lanzar el POST
        void registerConcert(Concert concert, OnRegisterListener listener);
    }


}
