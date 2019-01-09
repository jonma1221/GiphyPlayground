package com.giphyplayground.ui.util;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.List;

/**
 * Base RecyclerView adapter that handles all the typical boilerplate operations.
 *
 * @param <T>  object type of the data set
 * @param <L>  click listener
 */
public abstract class RecyclerBaseAdapter<T, L extends RecyclerBaseAdapter.BaseRecyclerListener>
        extends RecyclerView.Adapter<BaseHolder<T, L>> {
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
    public abstract BaseHolder<T, L> onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType);

    @Override
    public void onBindViewHolder(@NonNull BaseHolder<T, L> tBaseHolder, int i) {
        tBaseHolder.bind(items.get(i));
        tBaseHolder.setOnClickListener(onClickListener);
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public int getItemCount() {
        return items != null ? items.size() : 0;
    }

    private L onClickListener;
    public void setOnClickListener(L onClickListener){
        this.onClickListener = onClickListener;
    }

    public interface OnItemClickListener<T> extends BaseRecyclerListener{
        /**
         * The item that is clicked
         * @param item
         */
        void onItemClicked(T item);
    }

    /**
     * Base listener
     */
    public interface BaseRecyclerListener {
    }
}
