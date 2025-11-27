package com.svalero.musicrightsapp.domain;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Work implements Serializable {
    private long id;
    private String title;
    private String isrc;
    private String genre;
    private float duration;
    private String composedAt;
    private boolean registred;

    // Constructor para enviar datos sin ID
    public Work(String title, String isrc, String genre, float duration, String composedAt, boolean registred) {
        this.title = title;
        this.isrc = isrc;
        this.genre = genre;
        this.duration = duration;
        this.composedAt = composedAt;
        this.registred = registred;
    }
}
