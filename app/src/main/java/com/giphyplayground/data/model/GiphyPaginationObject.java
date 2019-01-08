package com.giphyplayground.data.model;

import com.google.gson.annotations.SerializedName;

public class GiphyPaginationObject {
    @SerializedName("offset")
    int offset;

    @SerializedName("total_count")
    int totalCount;

    @SerializedName("count")
    int count;

    public int getOffset() {
        return offset;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public int getCount() {
        return count;
    }
}
