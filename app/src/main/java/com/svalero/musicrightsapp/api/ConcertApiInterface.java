package com.svalero.musicrightsapp.api;

import com.svalero.musicrightsapp.domain.Concert;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ConcertApiInterface {

    @GET("concerts")
    Call<List<Concert>> getConcerts();

    @POST("concerts")
    Call<Concert> addConcert(@Body Concert concert);

    @GET("concerts/{id}")
    Call<Concert> getConcert(@Path("id") Long id);
}