package com.giphyplayground.ui.giphylist;

import com.giphyplayground.data.model.GiphyData;
import com.giphyplayground.ui.util.RecyclerBaseAdapter;

public interface OnGiphyClickListener extends RecyclerBaseAdapter.BaseRecyclerListener {
    void onGiphyClicked(GiphyData giphyData);
}
