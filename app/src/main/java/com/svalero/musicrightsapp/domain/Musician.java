package com.svalero.musicrightsapp.domain;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Musician implements Serializable {
    private long id;
    private String firstName;
    private String lastName;
    private String birthDate;
    private boolean affiliated;
    private String dni;
    private float performanceFee;
    private long affiliatedNumber;
    // TODO: LISTA DE OBRAS (WORKS)

    // Constructor para enviar datos sin ID
    public Musician(String firstName, String lastName, String birthDate, boolean affiliated, String dni, float performanceFee, long affiliatedNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.affiliated = affiliated;
        this.dni = dni;
        this.performanceFee = performanceFee;
        this.affiliatedNumber = affiliatedNumber;
    }
}
