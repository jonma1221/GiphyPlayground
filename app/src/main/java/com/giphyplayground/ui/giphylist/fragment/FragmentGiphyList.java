package com.giphyplayground.ui.giphylist.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.giphyplayground.R;
import com.giphyplayground.model.data.GiphyListDataSourceImpl;
import com.giphyplayground.ui.giphylist.GiphyListContract;
import com.giphyplayground.ui.giphylist.GiphyListPresenter;
import com.giphyplayground.ui.giphylist.adapter.GiphyListAdapter;
import com.giphyplayground.model.GiphyData;
import com.giphyplayground.model.GiphyTrendingResponse;
import com.giphyplayground.network.RetrofitClientInstance;
import com.giphyplayground.network.service.GiphyGetService;
import com.giphyplayground.ui.util.EndlessScrollListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentGiphyList extends Fragment implements GiphyListContract.View{
    @BindView(R.id.fragment_giphy_list_rv)
    RecyclerView giphyListRecyclerView;

    GiphyListAdapter giphyListAdapter;
    GiphyGetService giphyGetService;
    Call<GiphyTrendingResponse> call;

    GiphyListContract.Presenter mPresenter;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    // TODO: Rename and change types and number of parameters
    public static FragmentGiphyList newInstance(String param1, String param2) {
        FragmentGiphyList fragment = new FragmentGiphyList();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_giphy_list, container, false);
        ButterKnife.bind(this, v);
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(
                2,
                StaggeredGridLayoutManager.VERTICAL);
        giphyListRecyclerView.setLayoutManager(staggeredGridLayoutManager);
        giphyListRecyclerView.addOnScrollListener(new EndlessScrollListener() {
            @Override
            public boolean onLoadMore(int offset) {
                Log.d("offset", "" + offset);
                mPresenter.getTrendingGiphy(offset);
                return true;
            }
        });
        mPresenter = new GiphyListPresenter(new GiphyListDataSourceImpl(), this);
        giphyListAdapter = new GiphyListAdapter(new ArrayList<GiphyData>(0));
        giphyListRecyclerView.setAdapter(giphyListAdapter);
        giphyGetService = RetrofitClientInstance.getInstance()
                .create(GiphyGetService.class);
        mPresenter.getTrendingGiphy(0);
        return v;
    }

    @Override
    public void onTrendingLoaded(List<GiphyData> list) {
        giphyListAdapter.add(list);
    }
}
