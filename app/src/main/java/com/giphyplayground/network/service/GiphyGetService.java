package com.giphyplayground.network.service;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GiphyGetService {

    @GET("/v1/gifs/trending")
    Call<ResponseBody> getTrending(
            @Query("api_key") String apiKey,
            @Query("limit") int size);
}
