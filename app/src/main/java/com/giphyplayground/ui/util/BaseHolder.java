package com.giphyplayground.ui.util;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import butterknife.ButterKnife;

public abstract class BaseHolder<T, L extends RecyclerBaseAdapter.BaseRecyclerListener> extends RecyclerView.ViewHolder {
    protected Context mContext;
    protected L clickListener;

    public BaseHolder(@NonNull View itemView) {
        super(itemView);
        mContext = itemView.getContext();
        ButterKnife.bind(this, itemView);
    }

    public abstract void bind(T item);

    public void setOnClickListener(L clickListener){
        this.clickListener = clickListener;
    }
}
