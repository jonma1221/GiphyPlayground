package com.giphyplayground.data.source;

import com.giphyplayground.data.model.GiphyData;
import java.util.List;

public interface GiphyDataSource {
    interface GetGiphyCallback{
        void onGiphyLoaded(GiphyData giphyData);
        void onDataNotAvailable();
    }

    interface GetGiphyListCallback {
        void onGiphyLoaded(List<GiphyData> giphyDataList);
        void onDataNotAvailable();
    }

    void getGiphy(String id, GetGiphyCallback callback);
    void getGiphyList(int offset, GetGiphyListCallback callback);
    void searchGiphy(String query, int offset, GetGiphyListCallback callback);
}
