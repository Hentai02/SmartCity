package com.example.smartcity04.network;

import androidx.lifecycle.Observer;

import com.example.smartcity04.pojo.BillInfo;
import com.example.smartcity04.pojo.Carousel;
import com.example.smartcity04.pojo.Comment;
import com.example.smartcity04.pojo.NewsCategory;
import com.example.smartcity04.pojo.NewsInfo;
import com.example.smartcity04.pojo.User;
import com.example.smartcity04.pojo.UserInfo;
import com.example.smartcity04.pojo.reqdata.RequestData;
import com.example.smartcity04.pojo.reqdata.RequestToken;
import com.example.smartcity04.pojo.reqdata.RequestRows;
import com.example.smartcity04.pojo.reqdata.RequestMessage;
import com.example.smartcity04.pojo.reqdata.RequestUser;
import com.google.gson.JsonObject;

import java.io.File;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface BasicApi {

    String BASE_URI = "http://124.93.196.45:10001/";

    /**
     * 新闻分类
     * @return
     */
    @GET("/prod-api/press/category/list")
    Call<RequestData<NewsCategory>> getCategory();

    /**
     * 查询引导及主页轮播
     * @return
     */
    @GET("/prod-api/api/rotation/list")
    Call<RequestRows<Carousel>> getCarousel();


    /**
     * 获取新闻列表
     * @return
     */
    @GET("/prod-api/press/press/list")
    Call<RequestRows<NewsInfo>> getNewsInfo(@Query("type") String type);

    /**
     * 获取新闻评论
     * @param newsId 根据新闻id过滤 不能为空
     * @param commentDate 根据时间过滤
     * @param userId 根据id过滤
     * @return
     */
    @GET("/prod-api/press/comments/list")
    Call<RequestRows<Comment>> getComment(@Query("newsId") Integer newsId,
                                          @Query("commentDate") String commentDate,
                                          @Query("userId") Integer userId);


    /**
     * 发表评论
     * @param token
     * @param pressComment
     * @return
     */
    @POST("/prod-api/press/pressComment")
    Call<RequestMessage> pressComment(@Header("Authorization") String token,
                                      @Body JsonObject pressComment);

    /**
     * 新闻点赞
     * @param token
     * @param id
     * @return
     */
    @PUT("/prod-api/press/press/like/{id}")
    Call<RequestMessage> likeNews(@Header("Authorization") String token,@Path("id") Integer id);

    /**
     * 用户登录
     * @return
     */
    @POST("/prod-api/api/login")
    Call<RequestToken<String>> login(@Body User user);

    @POST("/logout")
    Observer<RequestBody> logout();

    /**
     * 查询个人基本信息
     * @param token
     * @return
     */
    @GET("/prod-api/api/common/user/getInfo")
    Call<RequestUser<UserInfo>> getUserInfo(@Header("Authorization") String token);

    /**
     * 修改用户信息
     * @param token
     * @param userInfo
     * @return
     */
    @PUT("/prod-api/api/common/user")
    Call<RequestMessage> modifyUserInfo(@Header("Authorization") String token,@Body JsonObject userInfo);

    /**
     * 查询交易记录
     * @param token
     * @return
     */
    @GET("/prod-api/api/common/balance/list")
    Call<RequestRows<BillInfo>> getBill(@Header("Authorization") String token);

    /**
     * 充值
     * @param token
     * @param money
     * @return
     */
    @POST("/prod-api/api/common/balance/recharge")
    Call<RequestMessage> recharge(@Header("Authorization") String token , @Query("money") Integer money);


    /**
     * 反馈
     * @param token
     * @param feedback
     * @return
     */
    @POST("/prod-api/api/common/feedback")
    Call<RequestMessage> feedback(@Header("Authorization") String token,@Body JsonObject feedback);

    /**
     * 文件上传
     * @param token
     * @param file
     * @return
     */
    @Multipart
    @POST("/prod-api/common/upload")
    Call<JsonObject> upload(@Header("Authorization") String token, @Part MultipartBody.Part file);


}
