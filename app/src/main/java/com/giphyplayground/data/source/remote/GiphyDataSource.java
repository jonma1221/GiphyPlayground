package com.giphyplayground.data.source.remote;

import com.giphyplayground.data.model.GiphyData;
import java.util.List;

public interface GiphyDataSource {
    interface GetGiphyCallback<T>{
        void onGiphyLoaded(T giphyData);
        void onDataNotAvailable();
    }

    interface GetGiphyListCallback<T> {
        void onGiphyLoaded(List<T> giphyDataList);
        void onDataNotAvailable();
    }

    void getGiphy(String id, GetGiphyCallback<GiphyData> callback);
    void getGiphyList(int offset, GetGiphyListCallback<GiphyData> callback);
    void searchGiphy(String query, int offset, GetGiphyListCallback<GiphyData> callback);
}
