package com.giphyplayground.ui.giphydetail;

import com.giphyplayground.data.model.GiphyData;
import com.giphyplayground.data.source.remote.GiphyDataSource;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

public class GiphyDetailPresenterTest {

    GiphyDetailPresenter giphyDetailPresenter;
    @Mock
    GiphyDetailContract.View mView;
    @Mock
    GiphyDataSource dataSource;
    @Captor
    ArgumentCaptor<GiphyDataSource.GetGiphyCallback<GiphyData>> getGiphyCallbackCaptor;


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        giphyDetailPresenter = new GiphyDetailPresenter(dataSource, mView);
    }

    @Test
    public void destroyView() {
        giphyDetailPresenter.destroyView();
    }

    @Test
    public void getGiphyByIdSuccess() {
        giphyDetailPresenter.getGiphyById("");
        verify(dataSource).getGiphy(anyString(), getGiphyCallbackCaptor.capture());
        GiphyData giphyData = Mockito.mock(GiphyData.class);
        getGiphyCallbackCaptor.getValue().onGiphyLoaded(giphyData);
        verify(mView).onGiphyRetrieved(any(GiphyData.class));
    }

    @Test
    public void getGiphyByIdFail() {
        giphyDetailPresenter.getGiphyById("");
        verify(dataSource).getGiphy(anyString(), getGiphyCallbackCaptor.capture());
        getGiphyCallbackCaptor.getValue().onDataNotAvailable();
        verify(mView, never()).onGiphyRetrieved(any(GiphyData.class));
    }
}