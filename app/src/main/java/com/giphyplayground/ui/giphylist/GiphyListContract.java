package com.giphyplayground.ui.giphylist;

import com.giphyplayground.data.model.GiphyData;

import java.util.List;

public interface GiphyListContract {
    interface View{
        void onTrendingLoaded(List<GiphyData> list);
    }

    interface Presenter{
        void getTrendingGiphy(int offset);
    }
}
