package com.example.smartcity04.pojo.stopwhere;

public class ScoreInfo {


    @com.google.gson.annotations.SerializedName("id")
    private int id;
    @com.google.gson.annotations.SerializedName("userId")
    private int userId;
    @com.google.gson.annotations.SerializedName("event")
    private String event;
    @com.google.gson.annotations.SerializedName("score")
    private String score;
    @com.google.gson.annotations.SerializedName("changeDate")
    private String changeDate;
    @com.google.gson.annotations.SerializedName("userName")
    private String userName;
    @com.google.gson.annotations.SerializedName("type")
    private String type;


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

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getChangeDate() {
        return changeDate;
    }

    public void setChangeDate(String changeDate) {
        this.changeDate = changeDate;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
