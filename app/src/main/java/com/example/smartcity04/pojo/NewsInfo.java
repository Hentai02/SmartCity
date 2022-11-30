package com.example.smartcity04.pojo;

public class NewsInfo {


    @com.google.gson.annotations.SerializedName("createTime")
    private String createTime;
    @com.google.gson.annotations.SerializedName("updateTime")
    private String updateTime;
    @com.google.gson.annotations.SerializedName("id")
    private int id;
    @com.google.gson.annotations.SerializedName("appType")
    private String appType;
    @com.google.gson.annotations.SerializedName("cover")
    private String cover;
    @com.google.gson.annotations.SerializedName("title")
    private String title;
    @com.google.gson.annotations.SerializedName("subTitle")
    private Object subTitle;
    @com.google.gson.annotations.SerializedName("content")
    private String content;
    @com.google.gson.annotations.SerializedName("status")
    private String status;
    @com.google.gson.annotations.SerializedName("publishDate")
    private String publishDate;
    @com.google.gson.annotations.SerializedName("commentNum")
    private int commentNum;
    @com.google.gson.annotations.SerializedName("likeNum")
    private int likeNum;
    @com.google.gson.annotations.SerializedName("readNum")
    private int readNum;
    @com.google.gson.annotations.SerializedName("type")
    private String type;
    @com.google.gson.annotations.SerializedName("top")
    private String top;
    @com.google.gson.annotations.SerializedName("hot")
    private String hot;

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

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

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Object getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(Object subTitle) {
        this.subTitle = subTitle;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    public int getCommentNum() {
        return commentNum;
    }

    public void setCommentNum(int commentNum) {
        this.commentNum = commentNum;
    }

    public int getLikeNum() {
        return likeNum;
    }

    public void setLikeNum(int likeNum) {
        this.likeNum = likeNum;
    }

    public int getReadNum() {
        return readNum;
    }

    public void setReadNum(int readNum) {
        this.readNum = readNum;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTop() {
        return top;
    }

    public void setTop(String top) {
        this.top = top;
    }

    public String getHot() {
        return hot;
    }

    public void setHot(String hot) {
        this.hot = hot;
    }
}
