package com.example.smartcity04.ui.stopwhere;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.smartcity04.R;
import com.example.smartcity04.adapter.GlideImageLoader;
import com.example.smartcity04.adapter.stopwhere.ParkAdapter;
import com.example.smartcity04.adapter.stopwhere.StopWhereServiceAdapter;
import com.example.smartcity04.network.BasicApi;
import com.example.smartcity04.pojo.Carousel;
import com.example.smartcity04.pojo.IconAndTitleItem;
import com.example.smartcity04.pojo.stopwhere.ParkInfo;
import com.example.smartcity04.util.CommonUtil;
import com.example.smartcity04.viewmodels.StopWhereViewModel;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

public class StopWhereActivity extends AppCompatActivity {

    private StopWhereViewModel model;

    private Banner mBanner;
    private RecyclerView mParkListRv;
    private ImageView mHistoryIv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_where_to_stop);
        CommonUtil.topBar(this,"停哪儿");
        mBanner = findViewById(R.id.banner);
        mParkListRv = findViewById(R.id.rv_park_list);
        model = new ViewModelProvider(this).get(StopWhereViewModel.class);
        mHistoryIv = findViewById(R.id.iv_more);
        model.getCarousel().observe(this,this::initCarousel);
        model.getPark().observe(this,this::initParkList);

        initParkService();
        mHistoryIv.setOnClickListener(v -> stopCarHistory());
    }

    private void stopCarHistory(){
        Intent intent = new Intent(this, StopCarRecordActivity.class);
        startActivity(intent);
    }

    /**
     * 初始化轮播图
     * @param data
     */
    private void initCarousel(List<Carousel> data) {
        List<String> advImages = new ArrayList<>();
        for (Carousel e:data){
            advImages.add(BasicApi.BASE_URI + e.getAdvImg());
        }
        //设置图片加载器
        mBanner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        mBanner.setImages(data);
        //banner设置方法全部调用完毕时最后调用
        mBanner.start();
    }

    private void initParkList(List<ParkInfo> data){
        mParkListRv.setLayoutManager(new LinearLayoutManager(this));
        mParkListRv.setAdapter(new ParkAdapter(data,this));
    }

    private void initParkService(){
        RecyclerView parkService = findViewById(R.id.rv_park_service_list);
        parkService.setLayoutManager(new GridLayoutManager(this,5));
        List<IconAndTitleItem> data = new ArrayList<>();
        data.add(new IconAndTitleItem(R.drawable.ic_stop_where_my_car,getString(R.string.stop_where_my_car)));
        data.add(new IconAndTitleItem(R.drawable.ic_stop_where_news,"资讯"));
        data.add(new IconAndTitleItem(R.drawable.ic_stop_where_recharge,getString(R.string.stop_where_recharge)));
        data.add(new IconAndTitleItem(R.drawable.ic_stop_where_store,getString(R.string.stop_where_store)));
        data.add(new IconAndTitleItem(R.drawable.ic_stop_where_point,getString(R.string.stop_where_point)));
        parkService.setAdapter(new StopWhereServiceAdapter(data,this));
    }
}