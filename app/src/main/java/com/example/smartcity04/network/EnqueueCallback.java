package com.example.smartcity04.network;

import android.util.Log;

import androidx.annotation.NonNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public abstract class EnqueueCallback<T> implements Callback<T> {


    @Override
    public abstract void onResponse(@NonNull Call<T> call, @NonNull Response<T> response);

    @Override
    public void onFailure(@NonNull Call<T> call, Throwable t) {
        Log.d("onFailure >>> ",t.getMessage());
        t.printStackTrace();
    }
}
