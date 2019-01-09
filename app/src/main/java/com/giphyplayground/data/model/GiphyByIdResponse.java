package com.giphyplayground.data.model;

import com.google.gson.annotations.SerializedName;

public class GiphyByIdResponse {
    @SerializedName("data")
    GiphyData giphyData;

    public GiphyData getGiphyData() {
        return giphyData;
    }
}
