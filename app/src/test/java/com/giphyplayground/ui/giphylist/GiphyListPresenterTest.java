package com.giphyplayground.ui.giphylist;

import com.giphyplayground.data.model.GiphyData;
import com.giphyplayground.data.source.remote.GiphyDataSource;
import com.giphyplayground.data.source.remote.GiphyDataSourceImpl;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

public class GiphyListPresenterTest {

    private GiphyListPresenter giphyListPresenter;
    @Mock
    private GiphyDataSourceImpl giphyListDataSource;
    @Mock
    private GiphyListContract.View mGiphyListView;
    @Captor
    private ArgumentCaptor<GiphyDataSource.GetGiphyListCallback<GiphyData>> mTrendingCallbackCaptor;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        giphyListPresenter = new GiphyListPresenter(giphyListDataSource, mGiphyListView);
    }

    @Test
    public void destroyViewTest() {
        giphyListPresenter.destroyView();
    }

    /**
     * Verify that a list of giphy is fetched and loaded into a viewt
     */
    @Test
    public void fetchTrendingAndLoadIntoViewSuccess() {
        giphyListPresenter.getTrendingGiphyList(0);
        // Callback is captured and invoked with stubbed tasks
        verify(giphyListDataSource).getGiphyList(Mockito.anyInt(), mTrendingCallbackCaptor.capture());
        // trigger the reply on callbackCaptor.getValue().
        mTrendingCallbackCaptor.getValue().onGiphyLoaded(Mockito.<GiphyData>anyList());
        verify(mGiphyListView).onTrendingLoaded(Mockito.<GiphyData>anyList());
    }

    @Test
    public void fetchTrendingAndLoadIntoViewFail() {
        giphyListPresenter.getTrendingGiphyList(0);
        // Callback is captured and invoked with stubbed tasks
        verify(giphyListDataSource).getGiphyList(Mockito.anyInt(), mTrendingCallbackCaptor.capture());
        // trigger the reply on callbackCaptor.getValue().
        mTrendingCallbackCaptor.getValue().onDataNotAvailable();
        verify(mGiphyListView, never()).onTrendingLoaded(Mockito.<GiphyData>anyList());
    }
}