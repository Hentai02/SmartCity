package com.example.smartcity04.network;

import android.util.Log;

import androidx.annotation.NonNull;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceFactory {

    private static final String LOG_TAG = ServiceFactory.class.getSimpleName();

    private static Retrofit retrofit;
    public static Retrofit getInstance(){
        if(retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(BasicApi.BASE_URI)
                    .client(
                            new OkHttpClient.Builder()
                                    .writeTimeout(10,TimeUnit.SECONDS)
                                    .readTimeout(10,TimeUnit.SECONDS)
                                    .build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            Log.d(LOG_TAG,"new Client");
        }
        return retrofit;
    }

    public static <T> T getService (@NonNull Class<T> clazz){
        return getInstance().create(clazz);
    }

}
