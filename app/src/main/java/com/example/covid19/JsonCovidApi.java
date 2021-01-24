package com.example.covid19;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JsonCovidApi {
    @GET("sudan")
    Call<List<Sudan>> getSudanData();


}
