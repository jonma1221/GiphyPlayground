package com.giphyplayground.ui.giphylist;

import com.giphyplayground.data.model.GiphyData;
import com.giphyplayground.data.source.GiphyListDataSource;

import java.util.List;

public class GiphyListPresenter implements GiphyListContract.Presenter {
    private GiphyListContract.View mGiphyListView;
    private GiphyListDataSource giphyListDataSource;

    public GiphyListPresenter(GiphyListDataSource giphyListDataSource,
                              GiphyListContract.View mGiphyListView) {
        this.giphyListDataSource = giphyListDataSource;
        this.mGiphyListView = mGiphyListView;
    }

    @Override
    public void getTrendingGiphy(int offset){
        giphyListDataSource.getGiphyList(offset, new GiphyListDataSource.GiphyListCallback() {
            @Override
            public void onGiphyLoaded(List<GiphyData> tasks) {
                mGiphyListView.onTrendingLoaded(tasks);
            }

            @Override
            public void onDataNotAvailable() {

            }
        });
    }
}
