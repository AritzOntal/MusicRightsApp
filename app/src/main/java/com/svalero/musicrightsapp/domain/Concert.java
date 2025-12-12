package com.svalero.musicrightsapp.domain;

import androidx.room.ColumnInfo;
import androidx.room.Database;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

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
@Entity(tableName = "concert")
public class Concert implements Serializable {
    @PrimaryKey
    private Long id;
    @ColumnInfo
    private String showTitle;
    @ColumnInfo
    private String city;
    @ColumnInfo
    private String province;
    @ColumnInfo
    private String date;
    @ColumnInfo
    private String status;
    @ColumnInfo
    private Boolean performed;
    @ColumnInfo
    private Float ticketPrice;
    @ColumnInfo
    private Double longitude;
    @ColumnInfo
    private Double latitude;

    @Ignore
    private Musician musician;
}
