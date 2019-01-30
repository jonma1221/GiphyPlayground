package com.giphyplayground.ui.giphylist;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.giphyplayground.R;
import com.giphyplayground.data.source.remote.GiphyDataSourceImpl;
import com.giphyplayground.ui.giphydetail.FragmentGiphyDetail;
import com.giphyplayground.ui.giphylist.adapter.GiphyListAdapter;
import com.giphyplayground.data.model.GiphyData;
import com.giphyplayground.ui.util.EndlessScrollListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FragmentGiphyList extends Fragment implements GiphyListContract.View{
    @BindView(R.id.fragment_giphy_list_searchView)
    SearchView searchView;
    @BindView(R.id.fragment_giphy_list_rv)
    RecyclerView giphyListRecyclerView;

    GiphyListAdapter giphyListAdapter;

    GiphyListContract.Presenter mPresenter;

    String currentSearchQuery = "";
    boolean searchTrending = true, shouldReplace = true;

    public static FragmentGiphyList newInstance() {
        FragmentGiphyList fragment = new FragmentGiphyList();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_giphy_list, container, false);
        ButterKnife.bind(this, v);

        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(
                2, StaggeredGridLayoutManager.VERTICAL);
        giphyListRecyclerView.setLayoutManager(staggeredGridLayoutManager);
        final EndlessScrollListener endlessScrollListener = new EndlessScrollListener() {
            @Override
            public boolean onLoadMore(int offset) {
                Log.d("offset", "" + offset);
                // todo - this needs to work with search query
//                if (searchTrending)
//                    mPresenter.getTrendingGiphyList(offset);
//                else
//                    mPresenter.searchGiphy(currentSearchQuery, offset);
                return true;
            }
        };
        giphyListRecyclerView.addOnScrollListener(endlessScrollListener);
        giphyListAdapter = new GiphyListAdapter(new ArrayList<GiphyData>(0));
        giphyListAdapter.setOnClickListener(new OnGiphyClickListener() {
            @Override
            public void onGiphyClicked(GiphyData giphyData) {
                FragmentManager fm = getFragmentManager();
                fm.beginTransaction().add(R.id.fragment_container,
                        FragmentGiphyDetail.newInstance(giphyData.getId()))
                        .addToBackStack(null)
                .commit();
            }
        });
        giphyListRecyclerView.setAdapter(giphyListAdapter);

        mPresenter = new GiphyListPresenter(GiphyDataSourceImpl.getInstance(), this);
        mPresenter.getTrendingGiphyList(0);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                currentSearchQuery = s;
                endlessScrollListener.reset();
                if(s.isEmpty()) {
                    shouldReplace = true;
                    searchTrending = true;
                    mPresenter.getTrendingGiphyList(0);
                }
                else {
                    shouldReplace = true;
                    searchTrending = false;
                    mPresenter.searchGiphy(s, 0);
                }
                return false;
            }
        });
        return v;
    }

    @Override
    public void onTrendingLoaded(List<GiphyData> list) {
        if (shouldReplace) {
            giphyListAdapter.replace(list);
            shouldReplace = false;
        } else {
            giphyListAdapter.add(list);
        }
    }

    @Override
    public void onSearchResult(List<GiphyData> searchResult) {
        if(shouldReplace){
            giphyListAdapter.replace(searchResult);
            shouldReplace = false;
        }
        else giphyListAdapter.add(searchResult);
    }

    @Override
    public void onError() {

    }
}
