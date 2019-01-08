package com.giphyplayground.ui.giphylist.viewholder;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.giphyplayground.R;
import com.giphyplayground.data.model.GiphyData;
import com.giphyplayground.ui.util.BaseHolder;

import butterknife.BindView;

public class GiphyListViewholder extends BaseHolder<GiphyData> {
    @BindView(R.id.giphy_list_item_preview)
    ImageView giphyPreview;

    public GiphyListViewholder(@NonNull View itemView) {
        super(itemView);
    }

    @Override
    public void bind(GiphyData giphyData){
        Glide.with(mContext)
                .load(giphyData.getGiphyImages().getDownsized().getUrl())
                .apply(new RequestOptions().centerCrop().transform(new RoundedCorners(16))
                        .override(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
                        .placeholder(R.color.colorPrimary))
                .into(giphyPreview);
    }
}
