package com.giphyplayground.ui.giphylist.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.giphyplayground.R;
import com.giphyplayground.ui.giphylist.OnGiphyClickListener;
import com.giphyplayground.ui.giphylist.viewholder.GiphyListViewholder;
import com.giphyplayground.data.model.GiphyData;
import com.giphyplayground.ui.util.BaseHolder;
import com.giphyplayground.ui.util.RecyclerBaseAdapter;

import java.util.List;

public class GiphyListAdapter extends RecyclerBaseAdapter<GiphyData, OnGiphyClickListener> {

    public GiphyListAdapter(List<GiphyData> giphyData) {
        super(giphyData);
    }

    @NonNull
    @Override
    public BaseHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        if(viewType == R.layout.giphy_list_item){

        }
        View v = inflater.inflate(R.layout.giphy_list_item, null);
        GiphyListViewholder giphyListViewholder = new GiphyListViewholder(v);
        return giphyListViewholder;
    }
}
