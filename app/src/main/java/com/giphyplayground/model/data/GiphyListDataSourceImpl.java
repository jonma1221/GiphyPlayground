package com.giphyplayground.model.data;

import android.util.Log;

import com.giphyplayground.model.GiphyData;
import com.giphyplayground.model.GiphyTrendingResponse;
import com.giphyplayground.network.RetrofitClientInstance;
import com.giphyplayground.network.service.GiphyGetService;
import com.giphyplayground.ui.giphylist.adapter.GiphyListAdapter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GiphyListDataSourceImpl implements GiphyListDataSource{

    Call<GiphyTrendingResponse> call;
    GiphyGetService giphyGetService;
    @Override
    public void getGiphyList(int offset, final GiphyListCallback callback) {
        giphyGetService = RetrofitClientInstance.getInstance()
                .create(GiphyGetService.class);
        call = giphyGetService.getTrending(15, offset);
        call.clone().enqueue(new Callback<GiphyTrendingResponse>() {
            @Override
            public void onResponse(Call<GiphyTrendingResponse> call, Response<GiphyTrendingResponse> response) {
                List<GiphyData> giphyData = response.body().getList();
//                if(offset == 0){
//                    Log.d("pagination", "" + response.body().getPaginationObject().getOffset());
//                    giphyListAdapter = new GiphyListAdapter(giphyData);
//                    giphyListRecyclerView.setAdapter(giphyListAdapter);
//                } else {
//                    giphyListAdapter.add(giphyData);
//                }
                callback.onGiphyLoaded(giphyData);
            }

            @Override
            public void onFailure(Call<GiphyTrendingResponse> call, Throwable t) {
                Log.d("[Response]", "failed");
            }
        });
    }
}
