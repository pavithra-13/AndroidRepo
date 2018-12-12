package com.example.devuser4.examresultlist.remote;

import android.content.Context;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    private static Retrofit retrofit = null;
    private static OkHttpClient okHttpClient;
    private static String TIMEOUT = "120";

    private static void initOkHttp(final Context context){

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpClient = new OkHttpClient().newBuilder();
        httpClient.addInterceptor(interceptor)
                .connectTimeout(Long.parseLong(TIMEOUT),TimeUnit.SECONDS)
                .readTimeout(Long.parseLong(TIMEOUT),TimeUnit.SECONDS)
                .writeTimeout(Long.parseLong(TIMEOUT),TimeUnit.SECONDS);
        okHttpClient = httpClient.build();
    }

    public static Retrofit getClient(Context context){
        if(okHttpClient == null)
            initOkHttp(context);

        if(retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(Const.BASE_URL)
                    .client(okHttpClient)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
