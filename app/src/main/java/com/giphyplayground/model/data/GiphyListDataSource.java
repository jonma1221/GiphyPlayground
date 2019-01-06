package com.giphyplayground.model.data;

import com.giphyplayground.model.GiphyData;
import java.util.List;

public interface GiphyListDataSource {
    interface GiphyListCallback{
        void onGiphyLoaded(List<GiphyData> tasks);
        void onDataNotAvailable();
    }

    void getGiphyList(int offset, GiphyListCallback callback);
}
