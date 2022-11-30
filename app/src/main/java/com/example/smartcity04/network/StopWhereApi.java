package com.example.smartcity04.network;

import com.example.smartcity04.pojo.BillInfo;
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

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface StopWhereApi {

    // 轮播图
    @GET("/prod-api/api/park/rotation/list")
    Call<RequestRows<Carousel>> getCarousel();

    // 停车位
    @GET("/prod-api/api/park/lot/list")
    Call<RequestRows<ParkInfo>> getPark();

    /**
     *
     * @param entryTime 入场时间 (date-time)
     * @param outTime 出场时间 (date-time)
     * @param parkName 停车场名称
     * @param plateNumber 车牌号
     * @return
     */
    @GET("/prod-api/api/park/lot/record/list")
    Call<RequestRows<StopCarRecord>> getStopCarRecord(@Query("entryTime") String entryTime,
                                                      @Query("outTime") String outTime,
                                                      @Query("parkName") String parkName,
                                                      @Query("plateNumber") String plateNumber);

    /**
     *
     * @return 充值记录
     */
    @GET("/prod-api/api/park/recharge/list")
    Call<RequestRows<RechargeInfo>> getRechargeInfo(@Header("Authorization") String token);

    /**
     * 充值
     * @param token
     * @param money 金额
     * @param payType 支付方式
     * @return
     */
    @POST("/prod-api/api/park/recharge/pay")
    Call<RequestMessage> recharge(@Header("Authorization") String token,
                                  @Query("money") Integer money,
                                  @Query("payType") String payType);

    /**
     * 加油卡列表
     * @return
     */
    @GET("/prod-api/api/park/product/list")
    Call<RequestRows<Product>> getProduct();

    /**
     * 购买加油卡
     * @param token
     * @param productId
     * @return
     */
    @POST("/prod-api/api/park/score/consume/{productId}")
    Call<RequestMessage> consume(@Header("Authorization") String token, @Path("productId") Integer productId);

    /**
     * 积分变更记录
     * @param token
     * @return
     */
    @GET("/prod-api/api/park/score/list")
    Call<RequestRows<ScoreInfo>> getScoreList(@Header("Authorization") String token);

    /**
     * 积分排行
     * @param token
     * @return
     */
    @GET("/prod-api/api/park/score/top/list")
    Call<RequestRows<ScoreRank>> getScoreRank(@Header("Authorization") String token);

    /**
     * 获取我的车库
     * @param token
     * @return
     */
    @GET("/prod-api/api/park/car/my")
    Call<RequestRows<Car>> getMyCar(@Header("Authorization") String token);

    /**
     * 添加车辆
     * @param token
     * @param car
     * @return
     */
    @POST("/prod-api/api/park/car")
    Call<RequestMessage> addCar(@Header("Authorization") String token, @Body JsonObject car);

    /**
     * 修改车辆信息
     * @param token
     * @param car
     * @return
     */
    @PUT("/prod-api/api/park/car")
    Call<RequestMessage> modifyCar(@Header("Authorization") String token,@Body Car car);

    /**
     * 查询车辆里程信息
     * @param token
     * @param plateNo
     * @return
     */
    @GET("/prod-api/api/park/car/consumption")
    Call<RequestRows<CarMileage>> getCarMileage(@Header("Authorization") String token,@Query("plateNo") String plateNo);

    /**
     * 添加车辆里程信息
     * @param token
     * @param carMileage
     * @return
     */
    @POST("/prod-api/api/park/car/consumption")
    Call<RequestMessage> setCarMileage(@Header("Authorization") String token,@Body CarMileage carMileage);

    /**
     * 修改车辆里程信息
     * @param token
     * @param carMileage
     * @return
     */
    @PUT("/prod-api/api/park/car/consumption")
    Call<RequestMessage> editCarMileage(@Header("Authorization") String token,@Body CarMileage carMileage);

    /**
     * 删除车辆里程信息
     * @param token
     * @param id
     * @return
     */
    @DELETE("/prod-api/api/park/car/consumption/{id}")
    Call<RequestMessage> delCarMileage(@Header("Authorization") String token,@Path("id") int id);

    /**
     * 挪车
     * @param token
     * @param jsonObject
     * @return
     */
    @POST("/prod-api/api/park/car/move")
    Call<JsonObject> moveCar(@Header("Authorization") String token
            ,@Body JsonObject jsonObject);

    /**
     * 查询车辆信息
     * @param token
     * @return
     */
    @GET("/prod-api/api/park/car/my")
    Call<RequestMessage> searchCar(@Header("Authorization") String token);

    /**
     * 删除车辆
     * @param token
     * @param id
     * @return
     */
    @DELETE("/prod-api/api/park/car/{id}")
    Call<RequestMessage> delCar(@Header("Authorization") String token,@Path("id") int id);


}
