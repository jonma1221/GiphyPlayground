package com.giphyplayground.network.service;

import com.giphyplayground.model.GiphyTrendingResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GiphyGetService {

    @GET("/v1/gifs/trending")
    Call<GiphyTrendingResponse> getTrending(
            @Query("limit") int size
    );
}
