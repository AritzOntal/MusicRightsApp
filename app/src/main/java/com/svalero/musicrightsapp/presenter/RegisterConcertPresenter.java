package com.svalero.musicrightsapp.presenter;

import com.svalero.musicrightsapp.contract.RegisterConcertContract;
import com.svalero.musicrightsapp.domain.Concert;
import com.svalero.musicrightsapp.domain.Musician;
import com.svalero.musicrightsapp.model.RegisterConcertModel;


import java.time.LocalDate;

public class RegisterConcertPresenter implements RegisterConcertContract.Presenter, RegisterConcertContract.Model.OnRegisterListener {

    private RegisterConcertContract.Model model; //DECLARADO COMO INTERFACE PERO LO LLENO COMO UN OBJETO JAVA
    private RegisterConcertContract.View view;

    public RegisterConcertPresenter(RegisterConcertContract.View view) {
        model = new RegisterConcertModel();
        this.view = view;
    }

    @Override
    public void registerConcert(String showTitle, String city, String province, LocalDate date, String status, Boolean performed, Float ticketPrice, Double latitude, Double longitude, Musician musician) {

        if (date.isAfter(LocalDate.now())) {
            view.showError("La fecha del concierto deber ser antes que hoy");
            return;
        }

        Concert newConcert = Concert.builder()
                .showTitle(showTitle)
                .city(city)
                .province(province)
                .date(date.toString())
                .status(status)
                .performed(performed)
                .ticketPrice(ticketPrice)
                .latitude(latitude)
                .longitude(longitude)
                .musician(musician)
                .build();


        model.registerConcert(newConcert, this);
    }

    @Override
    public void onRegisterSuccess(Concert createdConcert) {
        view.showSuccessMessage("El concierto se ha registrado con éxito");
    }

    @Override
    public void onRegisterError(String message) {
        view.showError("El concierto no se ha podido registrar");
    }

    @Override
    public void modifyConcert(long concertId, String showTitle, String city, String province, LocalDate date, String status, Boolean performed, Float ticketPrice) {

        if (date.isAfter(LocalDate.now())) {
            view.showError("La fecha del concierto deber ser antes que hoy");
            return;
        }

        Concert concertToModify = Concert.builder()
                .showTitle(showTitle)
                .city(city)
                .province(province)
                .date(date.toString())
                .status(status)
                .performed(performed)
                .ticketPrice(ticketPrice)
                .build();

        // TODO RECOGER EL ID DEL MUSICO REAL
        model.modifyConcert(concertId, concertToModify, this);
    }

}