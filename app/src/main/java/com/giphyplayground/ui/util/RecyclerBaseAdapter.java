package com.giphyplayground.ui.util;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.List;

public abstract class RecyclerBaseAdapter<T> extends RecyclerView.Adapter<BaseHolder<T>> {
    private List<T> items;

    public RecyclerBaseAdapter(List<T> items) {
        this.items = items;
    }

    public void replace(List<T> items){
        this.items.clear();
        this.items.addAll(items);
        notifyDataSetChanged();
    }

    public void add(List<T> items){
        this.items.addAll(items);
        int origSize = this.items.size();
        notifyItemRangeInserted(origSize, this.items.size());
    }

    @NonNull
    @Override
    public abstract BaseHolder<T> onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType);

    @Override
    public void onBindViewHolder(@NonNull BaseHolder<T> tBaseHolder, int i) {
        tBaseHolder.bind(items.get(i));
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public int getItemCount() {
        return items != null ? items.size() : 0;
    }

    private OnItemClickListener onClickListener;
    public void setOnClickListener(OnItemClickListener onClickListener){
        this.onClickListener = onClickListener;
    }
    public interface OnItemClickListener<T> {
        /**
         * The item that is clicked
         * @param item
         */
        void onItemClicked(T item);
    }
}
