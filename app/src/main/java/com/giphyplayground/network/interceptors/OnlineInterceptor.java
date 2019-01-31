package com.giphyplayground.network.interceptors;

import android.util.Log;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;

public class OnlineInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Response response = chain.proceed(chain.request());
        if (response.networkResponse() != null) {
            Log.v("[OnlineInterceptor]", "Response from networkResponse(): " + response.networkResponse());
        } else if (response.cacheResponse() != null) {
            Log.v("[OnlineInterceptor]", "Response from cacheControl(): " + response.cacheResponse());
        }
        int maxAge = 60; // read from cache for 60 seconds even if there is internet connection
        return response.newBuilder()
                .header("Cache-Control", "public, max-age=" + maxAge)
                .removeHeader("Pragma")
                .build();
    }
}
