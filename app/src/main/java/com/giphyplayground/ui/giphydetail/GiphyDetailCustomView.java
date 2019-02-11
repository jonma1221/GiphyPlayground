package com.giphyplayground.ui.giphydetail;

import android.content.Context;
import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.giphyplayground.R;
import com.giphyplayground.data.model.GiphyData;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GiphyDetailCustomView extends ConstraintLayout {
    @BindView(R.id.fragment_giphy_detail)
    ImageView giphyDetail;
    @BindView(R.id.fragment_giphy_detail_share)
    ImageView sharedBtn;

    GiphyData giphyData;
    public GiphyDetailCustomView(Context context) {
        super(context);
        init();
    }

    public GiphyDetailCustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public GiphyDetailCustomView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void init(){
        inflate(getContext(), R.layout.giphy_item_detail, this);
        ButterKnife.bind(this);
        setClickable(true);
        setShareBtn();
    }

    // implicit intent to send text
    private void setShareBtn() {
        sharedBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_SEND); // intent action
                i.setType("text/plain"); // data type of the intent
                i.putExtra(Intent.EXTRA_TEXT, giphyData.getGiphyImages().getDownsized().getUrl());
                getContext().startActivity(Intent.createChooser(i, "send to"));

            }
        });
    }

    public void setData(GiphyData giphyData){
        this.giphyData = giphyData;
    }

    public void setImage(String url){
        Glide.with(getContext())
                .load(url)
                .apply(new RequestOptions().placeholder(R.color.grey_200))
                .into(giphyDetail);
    }
}
