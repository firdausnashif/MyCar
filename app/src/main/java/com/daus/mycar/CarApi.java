package com.daus.mycar;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CarApi {

    @GET("dausgetapi.json")
    Call<CarResponse<CarItem>> getCar();

}
