package com.giphyplayground.network;

import android.app.Application;
import android.content.Context;

import com.giphyplayground.network.interceptors.ApiKeyInterceptor;
import com.giphyplayground.network.interceptors.AuthenticationInterceptor;

import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.giphyplayground.network.Constants.BASE_URL;

public class RetrofitClientInstance {
    private static int cacheSize = 10 * 1024 * 1024; // 10 MB

    private static Retrofit retrofit;

    public static Retrofit getInstance(){
        if(retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(createClient())
                    .build();
        }
        return retrofit;
    }

    //todo add cache
    private static OkHttpClient createClient(){
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(10, TimeUnit.SECONDS);
        builder.writeTimeout(10, TimeUnit.SECONDS);
        builder.readTimeout(10, TimeUnit.SECONDS);
//        builder.cache(new Cache(context.getCacheDir(), cacheSize));
        builder.addInterceptor(createHttpLoggingInterceptor());
        builder.addInterceptor(new ApiKeyInterceptor());
        return builder.build();
    }

    private static Interceptor createHttpLoggingInterceptor(){
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return loggingInterceptor;
    }
}
