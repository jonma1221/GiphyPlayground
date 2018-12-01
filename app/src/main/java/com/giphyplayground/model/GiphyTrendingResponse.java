package com.giphyplayground.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GiphyTrendingResponse {
    @SerializedName("data")
    List<GiphyData> list;

    public List<GiphyData> getList(){
        return list;
    }
}
