package com.giphyplayground.network.service;

import com.giphyplayground.data.model.GiphyByIdResponse;
import com.giphyplayground.data.model.GiphyData;
import com.giphyplayground.data.model.GiphyTrendingResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface GiphyGetService {

    @GET("/v1/gifs/trending")
    Call<GiphyTrendingResponse> getTrending(
            @Query("limit") Integer size,
            @Query("offset") Integer offset
    );

    @GET("/v1/gifs/{gif_id}")
    Call<GiphyByIdResponse> getGiphyById(@Path("gif_id") String gifId);
}
