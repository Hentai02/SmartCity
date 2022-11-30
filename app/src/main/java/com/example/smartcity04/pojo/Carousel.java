package com.example.smartcity04.pojo;

import java.util.List;

public class Carousel {

    @com.google.gson.annotations.SerializedName("createTime")
    private String createTime;
    @com.google.gson.annotations.SerializedName("updateTime")
    private String updateTime;
    @com.google.gson.annotations.SerializedName("id")
    private int id;
    @com.google.gson.annotations.SerializedName("appType")
    private String appType;
    @com.google.gson.annotations.SerializedName("status")
    private String status;
    @com.google.gson.annotations.SerializedName("sort")
    private int sort;
    @com.google.gson.annotations.SerializedName("advTitle")
    private String advTitle;
    @com.google.gson.annotations.SerializedName("advImg")
    private String advImg;
    @com.google.gson.annotations.SerializedName("servModule")
    private String servModule;
    @com.google.gson.annotations.SerializedName("targetId")
    private int targetId;
    @com.google.gson.annotations.SerializedName("type")
    private String type;

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public String getAdvTitle() {
        return advTitle;
    }

    public void setAdvTitle(String advTitle) {
        this.advTitle = advTitle;
    }

    public String getAdvImg() {
        return advImg;
    }

    public void setAdvImg(String advImg) {
        this.advImg = advImg;
    }

    public String getServModule() {
        return servModule;
    }

    public void setServModule(String servModule) {
        this.servModule = servModule;
    }

    public int getTargetId() {
        return targetId;
    }

    public void setTargetId(int targetId) {
        this.targetId = targetId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
