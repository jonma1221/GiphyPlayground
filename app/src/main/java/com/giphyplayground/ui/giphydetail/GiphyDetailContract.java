package com.giphyplayground.ui.giphydetail;

import com.giphyplayground.data.model.GiphyData;
import com.giphyplayground.ui.BasePresenter;

public interface GiphyDetailContract {
    interface View{
        void onGiphyRetrieved(GiphyData giphyData);
    }

    interface Presenter extends BasePresenter{
        void getGiphyById(String id);
    }
}
