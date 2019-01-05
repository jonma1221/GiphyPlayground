package com.giphyplayground.ui.giphylist.viewholder;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.giphyplayground.R;
import com.giphyplayground.model.GiphyData;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GiphyListViewholder extends RecyclerView.ViewHolder {
    @BindView(R.id.giphy_list_item_preview)
    ImageView giphyPreview;
    Context context;

    public GiphyListViewholder(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        context = itemView.getContext();
    }

    public void bind(GiphyData giphyData){
        Glide.with(context)
                .load(giphyData.getGiphyImages().getDownsized().getUrl())
                .apply(new RequestOptions().centerCrop()
                        .override(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
                .placeholder(R.color.colorPrimary))
                .into(giphyPreview);
    }
}
