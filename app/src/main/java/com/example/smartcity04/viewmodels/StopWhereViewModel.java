package com.example.smartcity04.viewmodels;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.smartcity04.pojo.Carousel;
import com.example.smartcity04.pojo.reqdata.RequestData;
import com.example.smartcity04.pojo.reqdata.RequestMessage;
import com.example.smartcity04.pojo.stopwhere.Car;
import com.example.smartcity04.pojo.stopwhere.CarMileage;
import com.example.smartcity04.pojo.stopwhere.ParkInfo;
import com.example.smartcity04.pojo.stopwhere.Product;
import com.example.smartcity04.pojo.stopwhere.RechargeInfo;
import com.example.smartcity04.pojo.stopwhere.ScoreInfo;
import com.example.smartcity04.pojo.stopwhere.ScoreRank;
import com.example.smartcity04.pojo.stopwhere.StopCarRecord;
import com.example.smartcity04.repository.StopWhereRepo;
import com.google.gson.JsonObject;

import java.util.List;

public class StopWhereViewModel extends ViewModel {

    private static StopWhereRepo repo;

    public StopWhereViewModel(){
        repo = new StopWhereRepo();
    }

    public LiveData<List<Carousel>> getCarousel(){
        return repo.getCarousel();
    }

    public LiveData<List<ParkInfo>> getPark(){
        return repo.getPark();
    }

    public LiveData<List<StopCarRecord>> getStopCarRecord(String entryTime,
                                                          String outTime,
                                                          String parkName,
                                                          String plateNumber){
        return repo.getStopCarRecord(entryTime, outTime, parkName, plateNumber);
    }

    public LiveData<List<RechargeInfo>> getRechargeInfo(String token){
        return repo.getRechargeInfo(token);
    }

    public LiveData<RequestMessage> recharge(String token, @NonNull Integer money, String payType){
        return repo.getRecharge(token, money, payType);
    }

    public LiveData<List<Product>> getProduct(){
        return repo.getProduct();
    }

    public LiveData<RequestMessage> consume(String token,Integer productId){
        return repo.consume(token, productId);
    }

    public LiveData<List<ScoreInfo>> getScoreList(String token){
        return repo.getScoreList(token);
    }

    public LiveData<List<ScoreRank>> getScoreRank(String token){
        return repo.getScoreRank(token);
    }

    public LiveData<List<Car>> getMyCarList(String token){
        return repo.getMyCarList(token);
    }

    public LiveData<RequestMessage> addCar(String token, JsonObject car){
        return repo.addCar(token, car);
    }

    public LiveData<RequestMessage> modifyCar(String token,Car car){
        return repo.modifyCar(token,car);
    }

    public LiveData<List<CarMileage>> getCarMileage(String token,String plateNo){
        return repo.getCarMileage(token, plateNo);
    }

    public LiveData<RequestMessage> addCarMileage(String token,CarMileage carMileage){
        return repo.setCarMileage(token, carMileage);
    }

    public LiveData<RequestMessage> modifyCarMileage(String token,CarMileage carMileage){
        return repo.modifyCarMileage(token, carMileage);
    }

    public LiveData<RequestMessage> delCarMileage(String token,Integer id){
        return repo.delCarMileage(token,id);
    }

    public LiveData<RequestMessage> delCar(String token,Integer id){
        return repo.delCar(token, id);
    }

    public LiveData<JsonObject> moveCar(String token,JsonObject jsonObject){
        return repo.moveCar(token, jsonObject);
    }


}
