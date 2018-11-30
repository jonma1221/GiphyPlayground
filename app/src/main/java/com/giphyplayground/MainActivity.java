package com.giphyplayground;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.giphyplayground.network.service.GiphyGetService;
import com.giphyplayground.network.service.RetrofitClientInstance;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    Call<ResponseBody> call;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GiphyGetService giphyGetService = RetrofitClientInstance.getInstance()
                .create(GiphyGetService.class);
        call = giphyGetService.getTrending(BuildConfig.GiphyApiKey, 1);
    }

    @Override
    protected void onResume() {
        super.onResume();

        call.clone().enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d("[Response]", "failed");
            }
        });
    }
}
