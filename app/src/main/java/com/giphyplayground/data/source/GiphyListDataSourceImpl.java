package com.giphyplayground.data.source;

import android.util.Log;

import com.giphyplayground.data.model.GiphyData;
import com.giphyplayground.data.model.GiphyTrendingResponse;
import com.giphyplayground.network.RetrofitClientInstance;
import com.giphyplayground.network.service.GiphyGetService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GiphyListDataSourceImpl implements GiphyListDataSource{
    private static GiphyListDataSourceImpl instance;

    private Call<GiphyTrendingResponse> call;
    private GiphyGetService giphyGetService;

    public static GiphyListDataSourceImpl getInstance() {
        if(instance == null){
            instance = new GiphyListDataSourceImpl();
        }
        return instance;
    }

    @Override
    public void getGiphyList(int offset, final GiphyListCallback callback) {
        giphyGetService = RetrofitClientInstance.getInstance()
                .create(GiphyGetService.class);
        call = giphyGetService.getTrending(15, offset);
        call.clone().enqueue(new Callback<GiphyTrendingResponse>() {
            @Override
            public void onResponse(Call<GiphyTrendingResponse> call, Response<GiphyTrendingResponse> response) {
                List<GiphyData> giphyData = response.body().getList();
                callback.onGiphyLoaded(giphyData);
            }

            @Override
            public void onFailure(Call<GiphyTrendingResponse> call, Throwable t) {
                Log.d("[Response]", "failed");
            }
        });
    }
}
