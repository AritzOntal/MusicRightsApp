package com.svalero.musicrightsapp.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Musician implements Serializable {
    private Long id;
    private String firstName;
    private String lastName;
    private String birthDate;
    private Boolean affiliated;
    private String dni;
    private Float performanceFee;
    private Long affiliatedNumber;
}
