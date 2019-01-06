package com.giphyplayground.ui.util;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;

public abstract class EndlessScrollListener extends RecyclerView.OnScrollListener {
    private boolean loading = true;
    private int pastVisibleItems, visibleItemCount, totalItemCount;
    private int previousTotalItemCount = 0;

    @Override
    public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        StaggeredGridLayoutManager mLayoutManager = (StaggeredGridLayoutManager)
                recyclerView.getLayoutManager();
        int visibleItemCount = mLayoutManager.getChildCount();
        int totalItemCount = mLayoutManager.getItemCount();
        int[] firstVisibleItems = null;
        firstVisibleItems = mLayoutManager.findFirstVisibleItemPositions(firstVisibleItems);
        if(firstVisibleItems != null && firstVisibleItems.length > 0) {
            pastVisibleItems = firstVisibleItems[0];
        }

        // If it's still loading, we check to see if the dataset count has
        // changed, if so we conclude it has finished loading and update the current page
        // number and total item count.
        if (loading && (totalItemCount > previousTotalItemCount)) {
            loading = false;
            previousTotalItemCount = totalItemCount;
        }

        if (!loading) {
            if ((visibleItemCount + pastVisibleItems) >= totalItemCount) {
                Log.d("endlessScroll", "prevItemCount " + previousTotalItemCount);
                Log.d("endlessScroll", "totalItemCount " + totalItemCount);
                loading = onLoadMore(totalItemCount);
            }
        }
    }

    public abstract boolean onLoadMore(int offset);

    @Override
    public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);
    }
}
