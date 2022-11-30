package com.example.smartcity04.pojo.reqdata;

import com.example.smartcity04.pojo.Carousel;

import java.util.List;

public class RequestRows<T>{

    @com.google.gson.annotations.SerializedName("total")
    private int total;
    @com.google.gson.annotations.SerializedName("rows")
    private java.util.List<T> rows;
    @com.google.gson.annotations.SerializedName("code")
    private int code;
    @com.google.gson.annotations.SerializedName("msg")
    private String msg;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }


}
