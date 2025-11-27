package com.svalero.musicrightsapp.domain;

import androidx.room.Database;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Concert implements Serializable {
    private long id;
    private String showTitle;
    private String city;
    private String province;
    private String date;
    private String status;
    private boolean performed;
    private float ticketPrice;
    //TODO IMPLEMENTAR LATITUD, LONGITUD Y LISTA DE MUSICOS

    // Constructor para enviar datos sin ID (lo obtenermos de servidor)
    public Concert(String showTitle, String city, String province, String date, String status, boolean performed, float ticketPrice) {
        this.showTitle = showTitle;
        this.city = city;
        this.province = province;
        this.date = date;
        this.status = status;
        this.performed = performed;
        this.ticketPrice = ticketPrice;
    }
}
