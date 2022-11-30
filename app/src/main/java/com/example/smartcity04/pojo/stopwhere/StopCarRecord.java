package com.example.smartcity04.pojo.stopwhere;

public class StopCarRecord {


    @com.google.gson.annotations.SerializedName("id")
    private int id;
    @com.google.gson.annotations.SerializedName("lotId")
    private int lotId;
    @com.google.gson.annotations.SerializedName("entryTime")
    private String entryTime;
    @com.google.gson.annotations.SerializedName("outTime")
    private String outTime;
    @com.google.gson.annotations.SerializedName("plateNumber")
    private String plateNumber;
    @com.google.gson.annotations.SerializedName("monetary")
    private String monetary;
    @com.google.gson.annotations.SerializedName("parkName")
    private String parkName;
    @com.google.gson.annotations.SerializedName("parkNo")
    private String parkNo;
    @com.google.gson.annotations.SerializedName("address")
    private String address;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLotId() {
        return lotId;
    }

    public void setLotId(int lotId) {
        this.lotId = lotId;
    }

    public String getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(String entryTime) {
        this.entryTime = entryTime;
    }

    public String getOutTime() {
        return outTime;
    }

    public void setOutTime(String outTime) {
        this.outTime = outTime;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public String getMonetary() {
        return monetary;
    }

    public void setMonetary(String monetary) {
        this.monetary = monetary;
    }

    public String getParkName() {
        return parkName;
    }

    public void setParkName(String parkName) {
        this.parkName = parkName;
    }

    public String getParkNo() {
        return parkNo;
    }

    public void setParkNo(String parkNo) {
        this.parkNo = parkNo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
