package com.giphyplayground;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.giphyplayground.model.GiphyData;
import com.giphyplayground.model.GiphyTrendingResponse;
import com.giphyplayground.network.service.GiphyGetService;
import com.giphyplayground.network.RetrofitClientInstance;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    Call<GiphyTrendingResponse> call;

    @BindView(R.id.gifphy_image)
    ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        GiphyGetService giphyGetService = RetrofitClientInstance.getInstance()
                .create(GiphyGetService.class);
        call = giphyGetService.getTrending(1);
    }

    @Override
    protected void onResume() {
        super.onResume();

        call.clone().enqueue(new Callback<GiphyTrendingResponse>() {
            @Override
            public void onResponse(Call<GiphyTrendingResponse> call, Response<GiphyTrendingResponse> response) {
                List<GiphyData> giphyData = response.body().getList();
                Glide.with(MainActivity.this)
                        .load(giphyData.get(0).getGiphyImages().getDownsized().getUrl())
                        .into(iv);
            }

            @Override
            public void onFailure(Call<GiphyTrendingResponse> call, Throwable t) {
                Log.d("[Response]", "failed");
            }
        });
    }
}
