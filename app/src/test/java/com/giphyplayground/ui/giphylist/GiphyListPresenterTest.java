package com.giphyplayground.ui.giphylist;

import com.giphyplayground.data.model.GiphyData;
import com.giphyplayground.data.source.GiphyListDataSource;
import com.giphyplayground.data.source.GiphyListDataSourceImpl;

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
import static org.mockito.Mockito.when;

public class GiphyListPresenterTest {

    @Mock
    GiphyListDataSourceImpl giphyListDataSource;
    @Mock
    GiphyListContract.View mGiphyListView;

    @Captor
    private ArgumentCaptor<GiphyListDataSource.GiphyListCallback> mTrendingCallbackCaptor;

    GiphyListPresenter giphyListPresenter;
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
        giphyListPresenter.getTrendingGiphy(0);
        // Callback is captured and invoked with stubbed tasks
        verify(giphyListDataSource).getGiphyList(Mockito.anyInt(), mTrendingCallbackCaptor.capture());
        // trigger the reply on callbackCaptor.getValue().
        mTrendingCallbackCaptor.getValue().onGiphyLoaded(Mockito.<GiphyData>anyList());

//        GiphyGetService giphyGetService = mock(GiphyGetService.class);
//        final Call<GiphyTrendingResponse> onResponseCall = mock(Call.class);
//        when(giphyGetService.getTrending(Mockito.anyInt(), Mockito.anyInt())).thenReturn(onResponseCall);
//        doAnswer(new Answer() {
//            @Override
//            public Object answer(InvocationOnMock invocation) throws Throwable {
//                Callback<GiphyTrendingResponse> callback = invocation.getArgument(0);
//                callback.onResponse(onResponseCall, Response.success(new GiphyTrendingResponse()));
//                return null;
//            }
//        }).when(onResponseCall).enqueue(any(Callback.class));

        verify(mGiphyListView).onTrendingLoaded(Mockito.<GiphyData>anyList());
    }

    @Test
    public void fetchTrendingAndLoadIntoViewFail() {
        giphyListPresenter.getTrendingGiphy(0);
        // Callback is captured and invoked with stubbed tasks
        verify(giphyListDataSource).getGiphyList(Mockito.anyInt(), mTrendingCallbackCaptor.capture());
        // trigger the reply on callbackCaptor.getValue().
        mTrendingCallbackCaptor.getValue().onDataNotAvailable();
        verify(mGiphyListView, never()).onTrendingLoaded(Mockito.<GiphyData>anyList());
    }
}