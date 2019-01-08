package com.giphyplayground.ui.giphylist;

import com.giphyplayground.data.model.GiphyData;
import com.giphyplayground.data.model.GiphyTrendingResponse;
import com.giphyplayground.data.source.GiphyListDataSource;
import com.giphyplayground.data.source.GiphyListDataSourceImpl;
import com.giphyplayground.network.service.GiphyGetService;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class GiphyListPresenterTest {
    List<GiphyData> giphyDataList = new ArrayList<>();

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

    /**
     * Verify that a list of giphy is fetched and loaded into a view
     */
    @Test
    public void fetchTrendingAndLoadIntoView() {
        giphyListPresenter.getTrendingGiphy(0);
        // Callback is captured and invoked with stubbed tasks
        verify(giphyListDataSource).getGiphyList(Mockito.anyInt(), mTrendingCallbackCaptor.capture());
        // trigger the reply on callbackCaptor.getValue().
        mTrendingCallbackCaptor.getValue().onGiphyLoaded(giphyDataList);

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

        verify(mGiphyListView).onTrendingLoaded(giphyDataList);
    }
}