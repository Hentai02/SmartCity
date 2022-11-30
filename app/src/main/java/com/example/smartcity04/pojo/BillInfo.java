package com.example.smartcity04.pojo;

public class BillInfo {

    @com.google.gson.annotations.SerializedName("id")
    private int id;
    @com.google.gson.annotations.SerializedName("userId")
    private int userId;
    @com.google.gson.annotations.SerializedName("event")
    private String event;
    @com.google.gson.annotations.SerializedName("changeAmount")
    private double changeAmount;
    @com.google.gson.annotations.SerializedName("changeType")
    private String changeType;
    @com.google.gson.annotations.SerializedName("userName")
    private String userName;
    @com.google.gson.annotations.SerializedName("changeTime")
    private String changeTime;

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

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public double getChangeAmount() {
        return changeAmount;
    }

    public void setChangeAmount(double changeAmount) {
        this.changeAmount = changeAmount;
    }

    public String getChangeType() {
        return changeType;
    }

    public void setChangeType(String changeType) {
        this.changeType = changeType;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getChangeTime() {
        return changeTime;
    }

    public void setChangeTime(String changeTime) {
        this.changeTime = changeTime;
    }
}
