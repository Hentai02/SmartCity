package com.example.smartcity04.repository;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.smartcity04.network.EnqueueCallback;
import com.example.smartcity04.network.ServiceFactory;
import com.example.smartcity04.network.BasicApi;
import com.example.smartcity04.pojo.BillInfo;
import com.example.smartcity04.pojo.Carousel;
import com.example.smartcity04.pojo.Comment;
import com.example.smartcity04.pojo.NewsCategory;
import com.example.smartcity04.pojo.NewsInfo;
import com.example.smartcity04.pojo.User;
import com.example.smartcity04.pojo.UserInfo;
import com.example.smartcity04.pojo.reqdata.RequestData;
import com.example.smartcity04.pojo.reqdata.RequestMessage;
import com.example.smartcity04.pojo.reqdata.RequestToken;
import com.example.smartcity04.pojo.reqdata.RequestRows;
import com.example.smartcity04.pojo.reqdata.RequestUser;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.File;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BasicRepository {
    private static final String LOG_TAG = BasicRepository.class.getSimpleName();
    private final BasicApi service;

    public BasicRepository(){
        service = ServiceFactory.getService(BasicApi.class);
    }

    // 新闻分类
    private final MutableLiveData<List<NewsCategory>> mNewsCategory = new MutableLiveData<>();
    public LiveData<List<NewsCategory>> getNewsCategory(){
        service.getCategory().enqueue(new EnqueueCallback<RequestData<NewsCategory>>() {
            @Override
            public void onResponse(@NonNull Call<RequestData<NewsCategory>> call, @NonNull Response<RequestData<NewsCategory>> response) {
                if(response.isSuccessful()){
                    RequestData<NewsCategory> body = response.body();
                    assert body != null;
                    List<NewsCategory> data = body.getData();
                    mNewsCategory.postValue(data);
                }
            }
        });
        return mNewsCategory;
    }

    // 新闻列表
    private final MutableLiveData<List<NewsInfo>> mNewsInfo = new MutableLiveData<>();
    public LiveData<List<NewsInfo>> getNewsInfoList(String type) {
        service.getNewsInfo(type).enqueue(new EnqueueCallback<RequestRows<NewsInfo>>() {
            @Override
            public void onResponse(@NonNull Call<RequestRows<NewsInfo>> call, @NonNull Response<RequestRows<NewsInfo>> response) {
                if (response.isSuccessful()) {
                    RequestRows<NewsInfo> body = response.body();
                    assert body != null;
                    List<NewsInfo> rows = body.getRows();
                    for (NewsInfo e : rows) {
                        if (e.getCover() != null) {
                            e.setCover(BasicApi.BASE_URI + e.getCover());
                        }
                    }
                    mNewsInfo.setValue(rows);
                }
            }
        });
        return mNewsInfo;
    }

    // 点赞
    private final MutableLiveData<RequestMessage> mLikeNews = new MutableLiveData<>();
    public LiveData<RequestMessage> likeNews(String token,Integer id){
        service.likeNews(token, id).enqueue(new EnqueueCallback<RequestMessage>() {
            @Override
            public void onResponse(@NonNull Call<RequestMessage> call, @NonNull Response<RequestMessage> response) {
                if(response.isSuccessful()){
                    RequestMessage body = response.body();
                    mLikeNews.setValue(body);
                }
            }
        });
        return mLikeNews;
    }

    // 轮播图
    private final MutableLiveData<List<Carousel>> mCarousel = new MutableLiveData<>();
    public LiveData<List<Carousel>> getCarousel(){
        service.getCarousel().enqueue(new EnqueueCallback<RequestRows<Carousel>>() {
            @Override
            public void onResponse(@NonNull Call<RequestRows<Carousel>> call, @NonNull Response<RequestRows<Carousel>> response) {
                if(response.isSuccessful()){
                    RequestRows<Carousel> body = response.body();
                    assert body != null;
                    List<Carousel> rows = body.getRows();
                    mCarousel.setValue(rows);
                }
            }
        });

        return mCarousel;
    }

    // 新闻评论
    private final MutableLiveData<List<Comment>> mComment = new MutableLiveData<>();
    public LiveData<List<Comment>> getComment(  Integer newsId, String commentDate, Integer userId){
        service.getComment(newsId, commentDate, userId).enqueue(new EnqueueCallback<RequestRows<Comment>>() {
            @Override
            public void onResponse(@NonNull Call<RequestRows<Comment>> call, @NonNull Response<RequestRows<Comment>> response) {
                if(response.isSuccessful()){
                    RequestRows<Comment> body = response.body();
                    assert body != null;
                    List<Comment> rows = body.getRows();
                    //Collections.sort(rows);
                    mComment.setValue(rows);
                }
            }
        });
        return mComment;
    }


    // 发表评论
    private final MutableLiveData<RequestMessage> mPressComment = new MutableLiveData<>();
    public LiveData<RequestMessage> pressComment(String token , JsonObject pressComment){
        service.pressComment(token, pressComment).enqueue(new EnqueueCallback<RequestMessage>() {
            @Override
            public void onResponse(@NonNull Call<RequestMessage> call, @NonNull Response<RequestMessage> response) {
                if(response.isSuccessful()){
                    RequestMessage body = response.body();
                    Log.d(LOG_TAG,body.getMsg());
                    mPressComment.setValue(body);
                }
            }
        });
        return mPressComment;
    }

    // 登录
    private final MutableLiveData<RequestToken<String>> mLoginRequest = new MutableLiveData<>();
    public LiveData<RequestToken<String>> getLoginRequest(User user){
        service.login(user).enqueue(new EnqueueCallback<RequestToken<String>>() {
            @Override
            public void onResponse(@NonNull Call<RequestToken<String>> call, @NonNull Response<RequestToken<String>> response) {
                if(response.isSuccessful()){
                    RequestToken<String> body = response.body();
                    mLoginRequest.setValue(body);
                }
            }
        });
        return mLoginRequest;
    }

    public void logout(){
        //service.logout().onChanged();
    }

    // 获取用户信息
    private MutableLiveData<UserInfo> mUserinfo = new MutableLiveData<>();
    public LiveData<UserInfo> getUserInfo(String token){
        service.getUserInfo(token).enqueue(new EnqueueCallback<RequestUser<UserInfo>>() {
            @Override
            public void onResponse(@NonNull Call<RequestUser<UserInfo>> call, @NonNull Response<RequestUser<UserInfo>> response) {
                if(response.isSuccessful()){
                    RequestUser<UserInfo> body = response.body();
                    assert body != null;
                    UserInfo user = body.getObject();
                    mUserinfo.setValue(user);
                }
            }
        });
        return mUserinfo;
    }

    private final MutableLiveData<RequestMessage> msgModify = new MutableLiveData<>();
    public LiveData<RequestMessage> getMsgModify(String token,JsonObject userInfo){
        service.modifyUserInfo(token, userInfo).enqueue(new EnqueueCallback<RequestMessage>() {
            @Override
            public void onResponse(@NonNull Call<RequestMessage> call, @NonNull Response<RequestMessage> response) {
                if(response.isSuccessful()){
                    RequestMessage body = response.body();
                    msgModify.setValue(body);
                }
            }
        });
        return msgModify;
    }

    // 交易记录
    private final MutableLiveData<List<BillInfo>> mBill = new MutableLiveData<>();
    public LiveData<List<BillInfo>> getBill(@NonNull String token){
        service.getBill(token).enqueue(new EnqueueCallback<RequestRows<BillInfo>>() {
            @Override
            public void onResponse(@NonNull Call<RequestRows<BillInfo>> call, @NonNull Response<RequestRows<BillInfo>> response) {
                if(response.isSuccessful()){
                    RequestRows<BillInfo> body = response.body();
                    assert body != null;
                    List<BillInfo> rows = body.getRows();
                    mBill.setValue(rows);
                }
            }
        });
        return mBill;
    }

    // 充值
    private final MutableLiveData<RequestMessage> mRequestMsg = new MutableLiveData<>();
    public LiveData<RequestMessage> recharge(@NonNull String token, Integer money){
        service.recharge(token, money).enqueue(new EnqueueCallback<RequestMessage>() {
            @Override
            public void onResponse(@NonNull Call<RequestMessage> call, @NonNull Response<RequestMessage> response) {
                if(response.isSuccessful()){
                    RequestMessage body = response.body();
                    mRequestMsg.setValue(body);
                }
            }
        });
        return mRequestMsg;
    }

    private final MutableLiveData<JsonObject> msgUpload = new MutableLiveData<>();
    public  LiveData<JsonObject> upload (String token, File file) {
        RequestBody fileReqBody = RequestBody.create(MediaType.parse("image/*"), file);
        MultipartBody.Part fff = MultipartBody.Part.createFormData("file", file.getName(),fileReqBody);
        service.upload(token, fff).enqueue(new EnqueueCallback<JsonObject>() {
            @Override
            public void onResponse(@NonNull Call<JsonObject> call, @NonNull Response<JsonObject> response) {
                if(response.isSuccessful()){
                    JsonObject body = response.body();
                    msgUpload.setValue(body);
                }
            }
        });
        return msgUpload;
    }



}
