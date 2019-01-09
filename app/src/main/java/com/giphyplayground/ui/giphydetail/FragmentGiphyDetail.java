package com.giphyplayground.ui.giphydetail;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.giphyplayground.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FragmentGiphyDetail extends Fragment {
    @NonNull
    private static final String GIPHY_ID = "com.giphyplayground.ui.giphydetail.GIPHY_ID";

    @BindView(R.id.fragment_giphy_detail)
    ImageView giphyDetail;

    public static FragmentGiphyDetail newInstance(String giphyId){
        FragmentGiphyDetail f = new FragmentGiphyDetail();
        Bundle b = new Bundle();
        b.putString(GIPHY_ID, giphyId);
        f.setArguments(b);
        return f;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_giphy_detail, container, false);
        ButterKnife.bind(this, v);


        return v;
    }
}
