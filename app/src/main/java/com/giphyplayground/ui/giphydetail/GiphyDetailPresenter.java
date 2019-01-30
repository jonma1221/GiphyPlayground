package com.giphyplayground.ui.giphydetail;

import com.giphyplayground.data.model.GiphyData;
import com.giphyplayground.data.source.remote.GiphyDataSource;

public class GiphyDetailPresenter implements GiphyDetailContract.Presenter, GiphyDataSource.GetGiphyCallback<GiphyData> {

    private GiphyDetailContract.View mGiphyDetailView;
    private GiphyDataSource giphyDataSource;

    public GiphyDetailPresenter(GiphyDataSource giphyDataSource, GiphyDetailContract.View mView) {
        this.mGiphyDetailView = mView;
        this.giphyDataSource = giphyDataSource;
    }

    @Override
    public void destroyView() {
        mGiphyDetailView = null;
    }

    @Override
    public void getGiphyById(String id) {
        giphyDataSource.getGiphy(id,this);
    }

    @Override
    public void onGiphyLoaded(GiphyData giphyData) {
        if(mGiphyDetailView != null){
            mGiphyDetailView.onGiphyRetrieved(giphyData);
        }
    }

    @Override
    public void onDataNotAvailable() {

    }
}
