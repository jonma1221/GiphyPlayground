package com.giphyplayground.ui.util;

import android.content.Context;
import android.net.ConnectivityManager;

public class NetworkUtil {
    public static boolean hasNetwork(Context context){
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        boolean isNetworkAvailable = cm.getActiveNetworkInfo() != null;
        return isNetworkAvailable && cm.getActiveNetworkInfo().isConnected();
    }
}
