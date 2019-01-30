package com.giphyplayground.ui.giphylist;

import com.giphyplayground.data.model.GiphyData;
import com.giphyplayground.ui.BasePresenter;

import java.util.List;

public interface GiphyListContract {
    interface View{
        void onTrendingLoaded(List<GiphyData> list);
        void onSearchResult(List<GiphyData> searchResult);
        void onError();
    }

    interface Presenter extends BasePresenter {
        void getTrendingGiphyList(int offset);
        void searchGiphy(String query, int offset);
    }
}
