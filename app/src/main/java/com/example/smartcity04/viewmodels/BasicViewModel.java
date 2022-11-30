package com.example.smartcity04.viewmodels;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.smartcity04.pojo.BillInfo;
import com.example.smartcity04.pojo.Carousel;
import com.example.smartcity04.pojo.Comment;
import com.example.smartcity04.pojo.NewsCategory;
import com.example.smartcity04.pojo.NewsInfo;
import com.example.smartcity04.pojo.User;
import com.example.smartcity04.pojo.UserInfo;
import com.example.smartcity04.pojo.reqdata.RequestToken;
import com.example.smartcity04.pojo.reqdata.RequestMessage;
import com.example.smartcity04.repository.BasicRepository;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.File;
import java.util.List;

public class BasicViewModel extends ViewModel {
    private static final String LOG_TAG = BasicViewModel.class.getSimpleName();
    private final BasicRepository repo;

    public BasicViewModel(){
        repo = new BasicRepository();
    }

    /**
     * 获取新闻类别
     * @return
     */
    public LiveData<List<NewsCategory>> getNewsCategory(){
        return repo.getNewsCategory();
    }

    /**
     * 获取新闻首页轮播图
     * @return
     */
    public LiveData<List<Carousel>> getCarousel(){
        return repo.getCarousel();
    }

    /**
     * 获取新闻
     * @param type
     * @return
     */
    public LiveData<List<NewsInfo>> getNewsInfoList(String type) {
        return repo.getNewsInfoList(type);
    }

    public LiveData<RequestMessage> likeNews(String token,Integer id){
        return repo.likeNews(token,id);
    }

    /**
     * 获取新闻评论
     * @param newsId 新闻id
     * @param commentDate 创建时间
     * @param userId 用户id
     * @return
     */
    public LiveData<List<Comment>> getComment(  Integer newsId, String commentDate, Integer userId){
        return repo.getComment(newsId, commentDate, userId);
    }

    public LiveData<RequestMessage> pressComment(String token, JsonObject pressComment){
        return repo.pressComment(token, pressComment);
    }


    /**
     * 登录
     * @param user 用户名和密码
     * @return token
     */
    public LiveData<RequestToken<String>> getLoginRequest(User user){
       return repo.getLoginRequest(user);
    }

    /**
     * 获取用户信息
     * @param token
     * @return 用户名，id,头像等基础信息
     */
    public LiveData<UserInfo> getUserInfo(String token){
       return repo.getUserInfo(token);
    }

    /**
     * 修改用户信息
     * @param token
     * @param info
     * @return
     */
    public LiveData<RequestMessage> modifyUserInfo(String token,JsonObject info){
        return repo.getMsgModify(token, info);
    }

    /**
     *
     * @param token
     * @return 获取账单
     */
    public LiveData<List<BillInfo>> getBill(@NonNull String token){
        return repo.getBill(token);
    }

    /**
     * 充值
     * @param token
     * @param money 金额
     * @return
     */
    public LiveData<RequestMessage> recharge(@NonNull String token, Integer money){
        return repo.recharge(token,money);
    }



    public LiveData<JsonObject> upload(String token, File file){
        return repo.upload(token,file);
    }






}
