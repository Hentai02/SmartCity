package com.example.smartcity04.pojo.stopwhere;

import com.google.gson.annotations.SerializedName;

public class Car {


    @SerializedName("id")
    private int id;
    @SerializedName("userId")
    private int userId;
    @SerializedName("plateNo")
    private String plateNo;
    @SerializedName("type")
    private String type;
    @SerializedName("userName")
    private String userName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getPlateNo() {
        return plateNo;
    }

    public void setPlateNo(String plateNo) {
        this.plateNo = plateNo;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
