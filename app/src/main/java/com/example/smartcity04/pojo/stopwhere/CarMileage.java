package com.example.smartcity04.pojo.stopwhere;

public class CarMileage {


    @com.google.gson.annotations.SerializedName("id")
    private int id;
    @com.google.gson.annotations.SerializedName("plateNo")
    private String plateNo;
    @com.google.gson.annotations.SerializedName("travelDate")
    private String travelDate;
    @com.google.gson.annotations.SerializedName("travelDistance")
    private int travelDistance;
    @com.google.gson.annotations.SerializedName("gasFilling")
    private int gasFilling;
    @com.google.gson.annotations.SerializedName("amount")
    private int amount;
    @com.google.gson.annotations.SerializedName("userId")
    private int userId;
    @com.google.gson.annotations.SerializedName("userName")
    private String userName;

    public CarMileage() {
    }

    public CarMileage(String plateNo, String travelDate, int travelDistance, int gasFilling, int amount) {
        this.plateNo = plateNo;
        this.travelDate = travelDate;
        this.travelDistance = travelDistance;
        this.gasFilling = gasFilling;
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPlateNo() {
        return plateNo;
    }

    public void setPlateNo(String plateNo) {
        this.plateNo = plateNo;
    }

    public String getTravelDate() {
        return travelDate;
    }

    public void setTravelDate(String travelDate) {
        this.travelDate = travelDate;
    }

    public int getTravelDistance() {
        return travelDistance;
    }

    public void setTravelDistance(int travelDistance) {
        this.travelDistance = travelDistance;
    }

    public int getGasFilling() {
        return gasFilling;
    }

    public void setGasFilling(int gasFilling) {
        this.gasFilling = gasFilling;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
