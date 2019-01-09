package com.giphyplayground.ui.giphylist;

import com.giphyplayground.data.model.GiphyData;
import com.giphyplayground.ui.BasePresenter;

import java.util.List;

public interface GiphyListContract {
    interface View{
        void onTrendingLoaded(List<GiphyData> list);
    }

    interface Presenter extends BasePresenter {
        void getTrendingGiphy(int offset);
    }
}
