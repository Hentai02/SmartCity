package com.example.smartcity04.pojo.stopwhere;

import com.google.gson.annotations.SerializedName;

public class ParkInfo {


    @SerializedName("id")
    private int id;
    @SerializedName("parkName")
    private String parkName;
    @SerializedName("vacancy")
    private String vacancy;
    @SerializedName("priceCaps")
    private String priceCaps;
    @SerializedName("imgUrl")
    private String imgUrl;
    @SerializedName("rates")
    private String rates;
    @SerializedName("address")
    private String address;
    @SerializedName("distance")
    private String distance;
    @SerializedName("allPark")
    private String allPark;
    @SerializedName("open")
    private String open;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getParkName() {
        return parkName;
    }

    public void setParkName(String parkName) {
        this.parkName = parkName;
    }

    public String getVacancy() {
        return vacancy;
    }

    public void setVacancy(String vacancy) {
        this.vacancy = vacancy;
    }

    public String getPriceCaps() {
        return priceCaps;
    }

    public void setPriceCaps(String priceCaps) {
        this.priceCaps = priceCaps;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getRates() {
        return rates;
    }

    public void setRates(String rates) {
        this.rates = rates;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getAllPark() {
        return allPark;
    }

    public void setAllPark(String allPark) {
        this.allPark = allPark;
    }

    public String getOpen() {
        return open;
    }

    public void setOpen(String open) {
        this.open = open;
    }
}
