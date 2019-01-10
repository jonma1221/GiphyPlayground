package com.giphyplayground.ui.giphylist;

import com.giphyplayground.data.model.GiphyData;
import com.giphyplayground.data.source.GiphyDataSource;

import java.util.List;

public class GiphyListPresenter implements GiphyListContract.Presenter {
    private GiphyListContract.View mGiphyListView;
    private GiphyDataSource giphyDataSource;

    public GiphyListPresenter(GiphyDataSource giphyDataSource,
                              GiphyListContract.View mGiphyListView) {
        this.giphyDataSource = giphyDataSource;
        this.mGiphyListView = mGiphyListView;
    }

    @Override
    public void destroyView() {
        mGiphyListView = null;
    }

    @Override
    public void getTrendingGiphyList(int offset){
        giphyDataSource.getGiphyList(offset, new GiphyDataSource.GetGiphyListCallback() {
            @Override
            public void onGiphyLoaded(List<GiphyData> giphyDataList) {
                mGiphyListView.onTrendingLoaded(giphyDataList);
            }

            @Override
            public void onDataNotAvailable() {

            }
        });
    }

    @Override
    public void searchGiphy(String query, int offset) {
        giphyDataSource.searchGiphy(query, offset, new GiphyDataSource.GetGiphyListCallback() {
            @Override
            public void onGiphyLoaded(List<GiphyData> giphyDataList) {
                mGiphyListView.onSearchResult(giphyDataList);
            }

            @Override
            public void onDataNotAvailable() {

            }
        });
    }
}
