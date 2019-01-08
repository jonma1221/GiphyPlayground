package com.giphyplayground.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GiphyTrendingResponse {
    @SerializedName("data")
    List<GiphyData> list;

    @SerializedName("pagination")
    GiphyPaginationObject paginationObject;

    public List<GiphyData> getList(){
        return list;
    }

    public GiphyPaginationObject getPaginationObject() {
        return paginationObject;
    }
}
