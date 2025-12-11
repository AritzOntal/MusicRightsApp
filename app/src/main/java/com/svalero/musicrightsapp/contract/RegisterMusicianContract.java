package com.svalero.musicrightsapp.contract;

import com.svalero.musicrightsapp.domain.Musician;

import java.time.LocalDate;

public interface RegisterMusicianContract {

    interface View {
        void showSuccessMessage(String message);
        void showError(String message);
    }

    interface Presenter {
        void registerMusician(Musician musician);
        void modifyMusician(long id, String firstName, String lastName, LocalDate birthDate, boolean affiliated, String dni, float performanceFee, long affiliatedNumber);
    }

    interface Model {
        interface OnRegisterListener {
            void onRegisterSuccess(Musician createdMusician);
            void onRegisterError(String message);
        }

        void registerMusician(Musician musician, OnRegisterListener listener);
        void modifyMusician(long id, Musician musician, OnRegisterListener listener);
    }
}