package com.svalero.musicrightsapp.domain;

import androidx.room.Database;

import java.io.Serializable;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Concert implements Serializable {
    private Long id;
    private String showTitle;
    private String city;
    private String province;
    private String date;
    private String status;
    private Boolean performed;
    private Float ticketPrice;
    private Float longitude;
    private Float latitude;

    private Musician musician;
}
