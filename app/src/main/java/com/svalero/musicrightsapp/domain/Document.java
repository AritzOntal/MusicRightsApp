package com.svalero.musicrightsapp.domain;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Document implements Serializable {
    private long id;
    private String type;
    private String filename;
    private long size;
    private String createAt;
    private boolean complete;
    private float completionPercentage;
    private Claim claim; // Objeto anidado

    // Constructor para enviar datos sin ID
    public Document(String type, String filename, long size, String createAt, boolean complete, float completionPercentage, Claim claim) {
        this.type = type;
        this.filename = filename;
        this.size = size;
        this.createAt = createAt;
        this.complete = complete;
        this.completionPercentage = completionPercentage;
        this.claim = claim;
    }
}