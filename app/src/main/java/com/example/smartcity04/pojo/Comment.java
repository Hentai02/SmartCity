package com.example.smartcity04.pojo;

import android.annotation.SuppressLint;
import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class Comment implements Comparable<Comment> {


    private static final String LOG_TAG = Comment.class.getSimpleName();
    @com.google.gson.annotations.SerializedName("id")
    private int id;
    @com.google.gson.annotations.SerializedName("appType")
    private String appType;
    @com.google.gson.annotations.SerializedName("newsId")
    private int newsId;
    @com.google.gson.annotations.SerializedName("content")
    private String content;
    @com.google.gson.annotations.SerializedName("commentDate")
    private String commentDate;
    @com.google.gson.annotations.SerializedName("userId")
    private int userId;
    @com.google.gson.annotations.SerializedName("likeNum")
    private int likeNum;
    @com.google.gson.annotations.SerializedName("userName")
    private String userName;
    @com.google.gson.annotations.SerializedName("newsTitle")
    private String newsTitle;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAppType() {
        return appType;
    }

    public void setAppType(String appType) {
        this.appType = appType;
    }

    public int getNewsId() {
        return newsId;
    }

    public void setNewsId(int newsId) {
        this.newsId = newsId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(String commentDate) {
        this.commentDate = commentDate;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getLikeNum() {
        return likeNum;
    }

    public void setLikeNum(int likeNum) {
        this.likeNum = likeNum;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getNewsTitle() {
        return newsTitle;
    }

    public void setNewsTitle(String newsTitle) {
        this.newsTitle = newsTitle;
    }

    @Override
    public int compareTo(Comment o) {
        if (this.commentDate!=null && o.getCommentDate() != null){
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            long o1Time = 0;
            long o2Time = 0;
            try {
                o1Time = sdf.parse(this.getCommentDate()).getTime();
                o2Time = sdf.parse(o.getCommentDate()).getTime();
                if(o1Time > o2Time){
                    return -1;
                }
                if(o1Time < o2Time){
                    return 1;
                }
            } catch (ParseException e) {
                return 0;
            }
        }
        return 0;
    }
}
