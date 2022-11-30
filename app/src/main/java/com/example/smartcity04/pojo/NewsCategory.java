package com.example.smartcity04.pojo;

import com.google.gson.annotations.SerializedName;

public class NewsCategory {

    @SerializedName("id")
    private int id;
    @SerializedName("appType")
    private String appType;
    @SerializedName("name")
    private String name;
    @SerializedName("sort")
    private int sort;
    @SerializedName("status")
    private String status;


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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
