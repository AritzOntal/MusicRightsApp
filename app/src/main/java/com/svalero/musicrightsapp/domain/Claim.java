package com.svalero.musicrightsapp.domain;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Claim implements Serializable {
    private long id;
    private String reference;
    private String status;
    private String type;
    private String description;
    private boolean pending;
    private Musician musician; // Objeto anidado (Retrofit lo mapea solo)

    // Constructor para enviar datos sin ID
    public Claim(String reference, String status, String type, String description, boolean pending, Musician musician) {
        this.reference = reference;
        this.status = status;
        this.type = type;
        this.description = description;
        this.pending = pending;
        this.musician = musician;
    }
}