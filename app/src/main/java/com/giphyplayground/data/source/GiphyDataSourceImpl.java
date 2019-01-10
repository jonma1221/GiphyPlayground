package com.giphyplayground.data.source;

import android.util.Log;

import com.giphyplayground.data.model.GiphyByIdResponse;
import com.giphyplayground.data.model.GiphyData;
import com.giphyplayground.data.model.GiphyTrendingResponse;
import com.giphyplayground.network.RetrofitClientInstance;
import com.giphyplayground.network.service.GiphyGetService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GiphyDataSourceImpl implements GiphyDataSource {
    private static GiphyDataSourceImpl instance;

    private Call<GiphyTrendingResponse> call;
    private GiphyGetService giphyGetService;

    public static GiphyDataSourceImpl getInstance() {
        if(instance == null){
            instance = new GiphyDataSourceImpl();
        }
        return instance;
    }

    @Override
    public void getGiphy(String id, final GetGiphyCallback callback) {
        giphyGetService = RetrofitClientInstance.getInstance()
                .create(GiphyGetService.class);
        Call<GiphyByIdResponse> call = giphyGetService.getGiphyById(id);
        call.clone().enqueue(new Callback<GiphyByIdResponse>() {
            @Override
            public void onResponse(Call<GiphyByIdResponse> call, Response<GiphyByIdResponse> response) {
                GiphyData giphyData = response.body().getGiphyData();
                callback.onGiphyLoaded(giphyData);
            }

            @Override
            public void onFailure(Call<GiphyByIdResponse> call, Throwable t) {

            }
        });
    }

    @Override
    public void getGiphyList(int offset, final GetGiphyListCallback callback) {
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

    @Override
    public void searchGiphy(String query, int offset, final GetGiphyListCallback callback) {
        giphyGetService = RetrofitClientInstance.getInstance()
                .create(GiphyGetService.class);
        call = giphyGetService.searchGiphy(query, offset);
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
