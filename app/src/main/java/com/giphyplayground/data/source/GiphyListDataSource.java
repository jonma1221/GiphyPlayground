package com.giphyplayground.data.source;

import com.giphyplayground.data.model.GiphyData;
import java.util.List;

public interface GiphyListDataSource {
    interface GiphyListCallback{
        void onGiphyLoaded(List<GiphyData> tasks);
        void onDataNotAvailable();
    }

    void getGiphyList(int offset, GiphyListCallback callback);
}
