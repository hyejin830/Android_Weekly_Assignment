package com.example.week4_project.assignment02;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface RetrofitExService {

    @Headers("Content-Type: application/json")

    @GET("restful/restaurant/{id}")
    Call<Restaurant> getRestaurantInfo(@Path("id") int id);

    @GET("restful/restaurant")
    Call<List<Restaurant>> getAllRestaurant();

    @POST("restful/restaurant/")
    Call<Restaurant> postRestaurant(@Body Restaurant restaurant);
}
