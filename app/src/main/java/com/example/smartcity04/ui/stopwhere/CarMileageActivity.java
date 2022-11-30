package com.example.smartcity04.ui.stopwhere;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.example.smartcity04.R;
import com.example.smartcity04.adapter.stopwhere.CarMileageAdapter;
import com.example.smartcity04.dialog.AddCarMileageDialog;
import com.example.smartcity04.pojo.reqdata.RequestMessage;
import com.example.smartcity04.pojo.stopwhere.CarMileage;
import com.example.smartcity04.util.CommonUtil;
import com.example.smartcity04.util.RefreshDataListener;
import com.example.smartcity04.viewmodels.StopWhereViewModel;

import java.util.List;

public class CarMileageActivity extends AppCompatActivity
        implements PopupMenu.OnMenuItemClickListener, CarMileageAdapter.OnItemSelectedListener {

    private static final String LOG_TAG = CarMileageActivity.class.getSimpleName();
    private RecyclerView mCarMileage;
    private CarMileageAdapter adapter;
    private String mPlateNo;
    private StopWhereViewModel stopWhereViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_mileage);
        CommonUtil.topBar(this,"里程碑");
        stopWhereViewModel = new ViewModelProvider(this).get(StopWhereViewModel.class);
        mCarMileage = findViewById(R.id.rv_car_mileage_list);
        mCarMileage.setLayoutManager(new LinearLayoutManager(this));

        ImageView view = findViewById(R.id.iv_more);
        view.setOnClickListener(v -> {
            PopupMenu popupMenu = CommonUtil.onPopupMenu(v, this, R.menu.car_mileage_moe);
            popupMenu.show();
            popupMenu.setOnMenuItemClickListener(CarMileageActivity.this);
        });
        Intent intent = getIntent();
        if(intent != null){
            mPlateNo = intent.getStringExtra("plateNo");
            Log.d(LOG_TAG,"车牌号：" + mPlateNo);
        }
        stopWhereViewModel.getCarMileage(CommonUtil.getToken(this),mPlateNo).observe(this, new Observer<List<CarMileage>>() {
            @Override
            public void onChanged(List<CarMileage> carMileages) {
                adapter = new CarMileageAdapter(CarMileageActivity.this,carMileages);
                mCarMileage.setAdapter(adapter);
                adapter.setOnItemSelectedListener(CarMileageActivity.this);
                //Log.d(LOG_TAG,carMileages.get(0).getPlateNo());
            }
        });



    }


    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()){
            case R.id.add_car_mileage_menu:
                AddCarMileageDialog dialog = new AddCarMileageDialog();
                dialog.show(getSupportFragmentManager(),"");
                dialog.setRefreshDataListener(() ->{
                    List<CarMileage> value = stopWhereViewModel.getCarMileage(CommonUtil.getToken(this), mPlateNo).getValue();
                });
                return true;
        }
        return false;
    }

    @Override
    public void onItemSelectedListener(MenuItem item, CarMileage carMileage) {
        switch (item.getItemId()){
            case R.id.modify_car_mileage_menu:
                int id = carMileage.getId();
                AddCarMileageDialog dialog = new AddCarMileageDialog();
                dialog.setCarMileage(carMileage);
                dialog.show(getSupportFragmentManager(),"");

                dialog.setRefreshDataListener(new RefreshDataListener() {
                    @Override
                    public void refresh() {
                        List<CarMileage> value = stopWhereViewModel.
                                getCarMileage(CommonUtil.getToken(CarMileageActivity.this), mPlateNo).getValue();
                    }
                });
                break;
            case R.id.del_car_mileage_menu:
                stopWhereViewModel.delCarMileage(CommonUtil.getToken(this),carMileage.getId()).observe(this, new Observer<RequestMessage>() {
                    @Override
                    public void onChanged(RequestMessage requestMessage) {
                        if(requestMessage.getCode() == 200){
                            stopWhereViewModel.getCarMileage(CommonUtil.getToken(CarMileageActivity.this),mPlateNo).getValue();
                        }
                        Toast.makeText(CarMileageActivity.this, requestMessage.getMsg(), Toast.LENGTH_SHORT).show();
                    }
                });
                break;
        }
    }
}