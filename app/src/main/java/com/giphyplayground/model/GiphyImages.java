package com.giphyplayground.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GiphyImages {

//    @SerializedName("fixed_height_still")
//    @Expose
//    private FixedHeightStill fixedHeightStill;
//    @SerializedName("original_still")
//    @Expose
//    private OriginalStill originalStill;
//    @SerializedName("fixed_width")
//    @Expose
//    private FixedWidth fixedWidth;
//    @SerializedName("fixed_height_small_still")
//    @Expose
//    private FixedHeightSmallStill fixedHeightSmallStill;
//    @SerializedName("fixed_height_downsampled")
//    @Expose
//    private FixedHeightDownsampled fixedHeightDownsampled;
//    @SerializedName("preview")
//    @Expose
//    private Preview preview;
//    @SerializedName("fixed_height_small")
//    @Expose
//    private FixedHeightSmall fixedHeightSmall;
    @SerializedName("downsized_still")
    @Expose
    private ImageBase downsizedStill;
    @SerializedName("downsized")
    @Expose
    private ImageBase downsized;
    @SerializedName("downsized_large")
    @Expose
    private ImageBase downsizedLarge;
    @SerializedName("fixed_width_small_still")
//    @Expose
//    private FixedWidthSmallStill fixedWidthSmallStill;
//    @SerializedName("preview_webp")
//    @Expose
//    private PreviewWebp previewWebp;
//    @SerializedName("fixed_width_still")
//    @Expose
//    private FixedWidthStill fixedWidthStill;
//    @SerializedName("fixed_width_small")
//    @Expose
//    private FixedWidthSmall fixedWidthSmall;
//    @SerializedName("downsized_small")
//    @Expose
//    private DownsizedSmall downsizedSmall;
//    @SerializedName("fixed_width_downsampled")
//    @Expose
//    private FixedWidthDownsampled fixedWidthDownsampled;
//    @SerializedName("downsized_medium")
//    @Expose
//    private DownsizedMedium downsizedMedium;
//    @SerializedName("original")
//    @Expose
//    private Original original;
//    @SerializedName("fixed_height")
//    @Expose
//    private FixedHeight fixedHeight;
//    @SerializedName("looping")
//    @Expose
//    private Looping looping;
//    @SerializedName("original_mp4")
//    @Expose
//    private OriginalMp4 originalMp4;
//    @SerializedName("preview_gif")
//    @Expose
//    private PreviewGif previewGif;
//    @SerializedName("480w_still")
//    @Expose
//    private w480Still;


    public ImageBase getDownsizedStill() {
        return downsizedStill;
    }

    public ImageBase getDownsized() {
        return downsized;
    }

    public ImageBase getDownsizedLarge() {
        return downsizedLarge;
    }
}
