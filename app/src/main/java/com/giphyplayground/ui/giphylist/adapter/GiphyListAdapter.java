package com.giphyplayground.ui.giphylist.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.giphyplayground.R;
import com.giphyplayground.ui.giphylist.viewholder.GiphyListViewholder;
import com.giphyplayground.model.GiphyData;

import java.util.List;

public class GiphyListAdapter extends RecyclerView.Adapter<GiphyListViewholder> {
    List<GiphyData> giphyData;

    public GiphyListAdapter() {
    }

    public GiphyListAdapter(List<GiphyData> giphyData) {
        this.giphyData = giphyData;
    }

    @NonNull
    @Override
    public GiphyListViewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        if(viewType == R.layout.giphy_list_item){

        }
        View v = inflater.inflate(R.layout.giphy_list_item, null);
        GiphyListViewholder giphyListViewholder = new GiphyListViewholder(v);
        return giphyListViewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull GiphyListViewholder giphyListViewholder, int i) {
        giphyListViewholder.bind(giphyData.get(i));
    }

    @Override
    public int getItemCount() {
        return giphyData.size();
    }
}
