package com.giphyplayground;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.giphyplayground.model.GiphyTrendingResponse;
import com.giphyplayground.ui.giphylist.fragment.FragmentGiphyList;

import butterknife.ButterKnife;
import retrofit2.Call;

public class MainActivity extends AppCompatActivity {
    Call<GiphyTrendingResponse> call;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
//        GiphyGetService giphyGetService = RetrofitClientInstance.getInstance()
//                .create(GiphyGetService.class);
//        call = giphyGetService.getTrending(1);

        FragmentGiphyList fragmentGiphyList = FragmentGiphyList.newInstance(null, null);
        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction()
                .add(R.id.fragment_container, fragmentGiphyList)
                .addToBackStack(null)
                .commit();
    }

    @Override
    protected void onResume() {
        super.onResume();

//        call.clone().enqueue(new Callback<GiphyTrendingResponse>() {
//            @Override
//            public void onResponse(Call<GiphyTrendingResponse> call, Response<GiphyTrendingResponse> response) {
//                List<GiphyData> giphyData = response.body().getList();
//                Glide.with(MainActivity.this)
//                        .load(giphyData.get(0).getGiphyImages().getDownsized().getUrl())
//                        .into(iv);
//            }
//
//            @Override
//            public void onFailure(Call<GiphyTrendingResponse> call, Throwable t) {
//                Log.d("[Response]", "failed");
//            }
//        });
    }
}
