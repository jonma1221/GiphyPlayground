package com.giphyplayground.data.util;

import android.content.Context;
import android.preference.PreferenceManager;

public class StorePreferences {
    public static class KEY{
        public static final String QUERY_KEY = "com.giphyplayground.data.util.QUERY_KEY";
    }

    public static String getData(Context context, String key){
        return PreferenceManager.getDefaultSharedPreferences(context)
                .getString(key, null);
    }

    public static void putData(Context context, String key, String value) {
        PreferenceManager.getDefaultSharedPreferences(context)
                .edit()
                .putString(key, value)
                .apply();
    }
}
