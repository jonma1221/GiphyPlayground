package com.giphyplayground.network.interceptors;

import com.giphyplayground.BuildConfig;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class ApiKeyInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request original = chain.request();

        // instead of adding the api key as a param every time
        // we add it to a interceptor
        HttpUrl newUrl = original.url().newBuilder()
                .addQueryParameter("api_key", BuildConfig.GiphyApiKey).build();
        
        Request.Builder builder = original.newBuilder().url(newUrl);
        Request request = builder.build();
        return chain.proceed(request);
    }
}
