package com.example.smartcity04.pojo.stopwhere;

/**
 * 停车场充值记录
 */
public class RechargeInfo {

    @com.google.gson.annotations.SerializedName("id")
    private int id;
    @com.google.gson.annotations.SerializedName("userId")
    private int userId;
    @com.google.gson.annotations.SerializedName("money")
    private int money;
    @com.google.gson.annotations.SerializedName("rechargeDate")
    private String rechargeDate;
    @com.google.gson.annotations.SerializedName("payType")
    private String payType;
    @com.google.gson.annotations.SerializedName("userName")
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

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public String getRechargeDate() {
        return rechargeDate;
    }

    public void setRechargeDate(String rechargeDate) {
        this.rechargeDate = rechargeDate;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
