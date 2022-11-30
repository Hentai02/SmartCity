package com.example.smartcity04.pojo.stopwhere;

import android.content.Intent;

public class ScoreRank implements Comparable<ScoreRank>{


    @com.google.gson.annotations.SerializedName("updateBy")
    private Object updateBy;
    @com.google.gson.annotations.SerializedName("userId")
    private int userId;
    @com.google.gson.annotations.SerializedName("deptId")
    private String deptId;
    @com.google.gson.annotations.SerializedName("userName")
    private String userName;
    @com.google.gson.annotations.SerializedName("nickName")
    private String nickName;
    @com.google.gson.annotations.SerializedName("email")
    private String email;
    @com.google.gson.annotations.SerializedName("phonenumber")
    private String phonenumber;
    @com.google.gson.annotations.SerializedName("sex")
    private String sex;
    @com.google.gson.annotations.SerializedName("avatar")
    private String avatar;
    @com.google.gson.annotations.SerializedName("salt")
    private String salt;
    @com.google.gson.annotations.SerializedName("status")
    private String status;
    @com.google.gson.annotations.SerializedName("delFlag")
    private String delFlag;
    @com.google.gson.annotations.SerializedName("loginIp")
    private String loginIp;
    @com.google.gson.annotations.SerializedName("loginDate")
    private String loginDate;
    @com.google.gson.annotations.SerializedName("idCard")
    private String idCard;
    @com.google.gson.annotations.SerializedName("balance")
    private String balance;
    @com.google.gson.annotations.SerializedName("score")
    private int score;
    @com.google.gson.annotations.SerializedName("admin")
    private boolean admin;

    public Object getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(Object updateBy) {
        this.updateBy = updateBy;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public String getLoginIp() {
        return loginIp;
    }

    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }

    public String getLoginDate() {
        return loginDate;
    }

    public void setLoginDate(String loginDate) {
        this.loginDate = loginDate;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    @Override
    public int compareTo(ScoreRank o) {
        Double o1 = Double.valueOf(this.balance);
        Double o2 = Double.valueOf(o.balance);
        return (int) (o2- o1);
    }
}
