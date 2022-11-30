package com.example.smartcity04.repository;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.smartcity04.network.EnqueueCallback;
import com.example.smartcity04.network.ServiceFactory;
import com.example.smartcity04.network.StopWhereApi;
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
import com.example.smartcity04.pojo.reqdata.RequestRows;
import com.google.gson.JsonObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class StopWhereRepo {
    private static final String LOG_TAG = StopWhereRepo.class.getSimpleName();

    private final StopWhereApi service;

    public StopWhereRepo(){
        service = ServiceFactory.getService(StopWhereApi.class);
    }


    // 轮播图
    private final MutableLiveData<List<Carousel>> mCarousel = new MutableLiveData<>();
    public LiveData<List<Carousel>> getCarousel(){
        service.getCarousel().enqueue(new EnqueueCallback<RequestRows<Carousel>>() {
            @Override
            public void onResponse(@NonNull Call<RequestRows<Carousel>> call, @NonNull Response<RequestRows<Carousel>> response) {
                if(response.isSuccessful()){
                    RequestRows<Carousel> body = response.body();
                    assert body != null;
                    mCarousel.setValue(body.getRows());
                }
            }
        });

        return mCarousel;
    }

    // 停车位
    private MutableLiveData<List<ParkInfo>> mPark = new MutableLiveData<>();
    public LiveData<List<ParkInfo>> getPark(){
        service.getPark().enqueue(new EnqueueCallback<RequestRows<ParkInfo>>() {
            @Override
            public void onResponse(@NonNull Call<RequestRows<ParkInfo>> call, @NonNull Response<RequestRows<ParkInfo>> response) {
                if(response.isSuccessful()){
                    RequestRows<ParkInfo> body = response.body();
                    assert body != null;
                    mPark.setValue(body.getRows());
                }
            }
        });
        return mPark;
    }

    // 停车记录
    private final MutableLiveData<List<StopCarRecord>> mStopCarRecord = new MutableLiveData<>();
    public LiveData<List<StopCarRecord>> getStopCarRecord(String entryTime,
                                                          String outTime,
                                                          String parkName,
                                                          String plateNumber){
        service.getStopCarRecord(entryTime, outTime, parkName, plateNumber).enqueue(new EnqueueCallback<RequestRows<StopCarRecord>>() {
            @Override
            public void onResponse(@NonNull Call<RequestRows<StopCarRecord>> call, @NonNull Response<RequestRows<StopCarRecord>> response) {
                if(response.isSuccessful()){
                    RequestRows<StopCarRecord> body = response.body();
                    assert body != null;
                    mStopCarRecord.setValue(body.getRows());
                }
            }
        });

        return mStopCarRecord;

    }


    // 充值记录
    private final MutableLiveData<List<RechargeInfo>> mRechargeInfo = new MutableLiveData<>();
    public LiveData<List<RechargeInfo>> getRechargeInfo(String token){
        service.getRechargeInfo(token).enqueue(new EnqueueCallback<RequestRows<RechargeInfo>>() {
            @Override
            public void onResponse(@NonNull Call<RequestRows<RechargeInfo>> call, @NonNull Response<RequestRows<RechargeInfo>> response) {
                if(response.isSuccessful()){
                    RequestRows<RechargeInfo> body = response.body();
//                    for (RechargeInfo e: body.getRows()){
//                        Log.d(LOG_TAG,"充值金额：" + e.getMoney());
//                    }
//                    assert body != null;
                    mRechargeInfo.setValue(body.getRows());
                }
                //Log.d(LOG_TAG,response.body().getRows().size() + "/////////");
            }
        });
        return mRechargeInfo;
    }

    // 充值
    private final MutableLiveData<RequestMessage> mRecharge = new MutableLiveData<>();
    public LiveData<RequestMessage> getRecharge(String token,Integer money,String payType){
        service.recharge(token,money,payType).enqueue(new EnqueueCallback<RequestMessage>() {
            @Override
            public void onResponse(@NonNull Call<RequestMessage> call, @NonNull Response<RequestMessage> response) {
                if (response.isSuccessful()){
                    RequestMessage body = response.body();
                    mRecharge.setValue(body);
                }
            }
        });
        return mRecharge;
    }

    // 积分换加油卡
    private final MutableLiveData<List<Product>> mProduct = new MutableLiveData<>();
    public LiveData<List<Product>> getProduct(){
        service.getProduct().enqueue(new EnqueueCallback<RequestRows<Product>>() {
            @Override
            public void onResponse(@NonNull Call<RequestRows<Product>> call, @NonNull Response<RequestRows<Product>> response) {
                if(response.isSuccessful()){
                    RequestRows<Product> body = response.body();
                    assert body != null;
                    mProduct.setValue(body.getRows());
                }
            }
        });
        return mProduct;
    }

    private final MutableLiveData<RequestMessage> mConsume = new MutableLiveData<>();
    public LiveData<RequestMessage> consume(String token,Integer productId){
        service.consume(token, productId).enqueue(new EnqueueCallback<RequestMessage>() {
            @Override
            public void onResponse(@NonNull Call<RequestMessage> call, @NonNull Response<RequestMessage> response) {
                if(response.isSuccessful()){
                    RequestMessage body = response.body();
                    mConsume.setValue(body);
                }
            }
        });
        return mConsume;
    }

    private final MutableLiveData<List<ScoreInfo>> mScoreList = new MutableLiveData<>();
    public LiveData<List<ScoreInfo>> getScoreList(String token){
        service.getScoreList(token).enqueue(new EnqueueCallback<RequestRows<ScoreInfo>>() {
            @Override
            public void onResponse(@NonNull Call<RequestRows<ScoreInfo>> call, @NonNull Response<RequestRows<ScoreInfo>> response) {
                if(response.isSuccessful()){
                    assert response.body() != null;
                    List<ScoreInfo> rows = response.body().getRows();
                    mScoreList.setValue(rows);

                }
            }
        });
        return mScoreList;
    }

    // 积分排行
    private final MutableLiveData<List<ScoreRank>> mScoreRank = new MutableLiveData<>();
    public LiveData<List<ScoreRank>> getScoreRank(String token){
        service.getScoreRank(token).enqueue(new EnqueueCallback<RequestRows<ScoreRank>>() {
            @Override
            public void onResponse(@NonNull Call<RequestRows<ScoreRank>> call, @NonNull Response<RequestRows<ScoreRank>> response) {
                if(response.isSuccessful()){
                    if (response.body() == null) throw new AssertionError();
                    List<ScoreRank> rows = response.body().getRows();
                    mScoreRank.setValue(rows);
                }
            }
        });
        return mScoreRank;
    }

    // 获取所有车辆
    private final MutableLiveData<List<Car>> mMyCar = new MutableLiveData<>();
    public LiveData<List<Car>> getMyCarList(String token){
        service.getMyCar(token).enqueue(new EnqueueCallback<RequestRows<Car>>() {
            @Override
            public void onResponse(@NonNull Call<RequestRows<Car>> call, @NonNull Response<RequestRows<Car>> response) {
                if(response.isSuccessful()){
                    RequestRows<Car> body = response.body();
                    assert body != null;
                    List<Car> rows = body.getRows();
                    mMyCar.setValue(rows);
                }
            }
        });
        return mMyCar;
    }

    // 添加车辆
    private final MutableLiveData<RequestMessage> mCar = new MutableLiveData<>();
    public LiveData<RequestMessage> addCar(String token, JsonObject car){
        service.addCar(token, car).enqueue(new EnqueueCallback<RequestMessage>() {
            @Override
            public void onResponse(@NonNull Call<RequestMessage> call, @NonNull Response<RequestMessage> response) {
                if(response.isSuccessful()){
                    RequestMessage body = response.body();
                    mCar.setValue(body);
                }
            }
        });
        return mCar;
    }


    // 修改车辆信息
    private final MutableLiveData<RequestMessage> msgModifyCar = new MutableLiveData<>();
    public LiveData<RequestMessage> modifyCar(String token,Car car){
        service.modifyCar(token, car).enqueue(new EnqueueCallback<RequestMessage>() {
            @Override
            public void onResponse(@NonNull Call<RequestMessage> call, @NonNull Response<RequestMessage> response) {
                if(response.isSuccessful()){
                    RequestMessage body = response.body();
                    msgModifyCar.setValue(body);
                }
            }
        });
        return msgModifyCar;
    }

    // 获取车辆里程信息
    private final MutableLiveData<List<CarMileage>> mCarMileage = new MutableLiveData<>();
    public LiveData<List<CarMileage>> getCarMileage(String token,String plateNo){
        service.getCarMileage(token, plateNo).enqueue(new EnqueueCallback<RequestRows<CarMileage>>() {
            @Override
            public void onResponse(@NonNull Call<RequestRows<CarMileage>> call, @NonNull Response<RequestRows<CarMileage>> response) {
                if(response.isSuccessful()){
                    RequestRows<CarMileage> body = response.body();
                    assert body != null;
                    List<CarMileage> rows = body.getRows();
                    mCarMileage.setValue(rows);
                }
            }
        });
        return mCarMileage;
    }


    // 添加车辆里程信息
    private final MutableLiveData<RequestMessage> msgSetCarMileage = new MutableLiveData<>();
    public LiveData<RequestMessage> setCarMileage(String token,CarMileage carMileage){
        service.setCarMileage(token, carMileage).enqueue(new EnqueueCallback<RequestMessage>() {
            @Override
            public void onResponse(@NonNull Call<RequestMessage> call, @NonNull Response<RequestMessage> response) {
                if(response.isSuccessful()){
                    RequestMessage body = response.body();
                    msgSetCarMileage.setValue(body);
                }
            }
        });
        return msgSetCarMileage;
    }

    // 修改车辆里程信息
    private final MutableLiveData<RequestMessage> msgModifyCarMileage = new MutableLiveData<>();
    public LiveData<RequestMessage> modifyCarMileage(String token,CarMileage carMileage){
        service.editCarMileage(token, carMileage).enqueue(new EnqueueCallback<RequestMessage>() {
            @Override
            public void onResponse(@NonNull Call<RequestMessage> call, @NonNull Response<RequestMessage> response) {
                if(response.isSuccessful()){
                    RequestMessage body = response.body();
                    msgModifyCarMileage.setValue(body);
                }
            }
        });
        return msgModifyCarMileage;
    }

    // 删除车辆里程信息
    private final MutableLiveData<RequestMessage> msgDelCarMileage = new MutableLiveData<>();
    public LiveData<RequestMessage> delCarMileage(String token,Integer id){
        service.delCarMileage(token,id).enqueue(new EnqueueCallback<RequestMessage>() {
            @Override
            public void onResponse(@NonNull Call<RequestMessage> call, @NonNull Response<RequestMessage> response) {
                if(response.isSuccessful()){
                    RequestMessage body = response.body();
                    msgDelCarMileage.setValue(body);
                }
            }
        });
        return msgDelCarMileage;
    }


    // 搜索车辆
    private final MutableLiveData<RequestMessage> msgSearchCar = new MutableLiveData<>();
    public LiveData<RequestMessage> searchCar(String token){
        service.searchCar(token).enqueue(new EnqueueCallback<RequestMessage>() {
            @Override
            public void onResponse(@NonNull Call<RequestMessage> call, @NonNull Response<RequestMessage> response) {
                if(response.isSuccessful()){
                    RequestMessage body = response.body();
                    msgSearchCar.setValue(body);
                }
            }
        });
        return msgSearchCar;
    }

    // 删除车辆
    private final MutableLiveData<RequestMessage> msgDelCar = new MutableLiveData<>();
    public LiveData<RequestMessage> delCar(String token,Integer id){
        service.delCar(token,id).enqueue(new EnqueueCallback<RequestMessage>() {
            @Override
            public void onResponse(@NonNull Call<RequestMessage> call, @NonNull Response<RequestMessage> response) {
                if(response.isSuccessful()){
                    RequestMessage body = response.body();
                    msgDelCar.setValue(body);
                }
            }
        });
        return msgDelCar;
    }

    // 挪车
    private final MutableLiveData<JsonObject> msgMoveCar = new MutableLiveData<>();
    public LiveData<JsonObject> moveCar(String token,JsonObject jsonObject){
        service.moveCar(token, jsonObject).enqueue(new EnqueueCallback<JsonObject>() {
            @Override
            public void onResponse(@NonNull Call<JsonObject> call, @NonNull Response<JsonObject> response) {
                if(response.isSuccessful()){
                    JsonObject body = response.body();
                    msgMoveCar.setValue(body);
                }
            }
        });
        return msgMoveCar;
    }


}
