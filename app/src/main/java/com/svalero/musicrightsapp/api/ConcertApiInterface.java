package com.svalero.musicrightsapp.api;

import com.svalero.musicrightsapp.domain.Concert;
import com.svalero.musicrightsapp.domain.Musician;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ConcertApiInterface {

    @GET("concerts")
    Call<List<Concert>> getConcerts();

    @POST("concerts")
    Call<Concert> addConcert(@Body Concert concert);

    @PUT("concerts/{id}")
    Call<Concert> modifyConcert(@Path("id") long id, @Body Concert concert);

    @DELETE("concerts/{id}")
    Call<Concert> deleteConcert(@Path("id")long id);

    @GET("musicians")
    Call <List<Musician>> getMusicians();

    @POST("musicians")
    Call<Musician> addMusician(@Body Musician musician);

    @PUT("musicians/{id}")
    Call<Musician> modifyMusician(@Path("id") long id, @Body Musician musician);

    @DELETE("musicians/{id}")
    Call<Musician> deleteMusicians(@Path("id")long id);
}