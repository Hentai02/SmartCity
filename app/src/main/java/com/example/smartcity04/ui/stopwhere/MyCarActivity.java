package com.example.smartcity04.ui.stopwhere;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.PopupMenu;

import com.example.smartcity04.R;
import com.example.smartcity04.adapter.stopwhere.MyCarAdapter;
import com.example.smartcity04.dialog.CarInfoDialogFragment;
import com.example.smartcity04.dialog.MoveCarDialog;
import com.example.smartcity04.dialog.MyCarAddDialogFragment;
import com.example.smartcity04.pojo.reqdata.RequestMessage;
import com.example.smartcity04.pojo.stopwhere.Car;
import com.example.smartcity04.util.CommonUtil;
import com.example.smartcity04.viewmodels.StopWhereViewModel;

import java.util.List;

public class MyCarActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener{

    private static final String LOG_TAG = MyCarActivity.class.getSimpleName();
    private RecyclerView mMyCarList;
    private StopWhereViewModel stopWhereViewModel;
    private MyCarAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_car);
        CommonUtil.topBar(this,"爷的车库");
        stopWhereViewModel = new ViewModelProvider(this).get(StopWhereViewModel.class);

        ImageView view = findViewById(R.id.iv_more);
        view.setOnClickListener(v -> {
            PopupMenu popupMenu = CommonUtil.onPopupMenu(v, MyCarActivity.this, R.menu.mycar_menu);
            popupMenu.setOnMenuItemClickListener(MyCarActivity.this);
            popupMenu.show();
        });

        mMyCarList = findViewById(R.id.rv_my_car_list);
        mMyCarList.setLayoutManager(new LinearLayoutManager(this));
        stopWhereViewModel.getMyCarList(CommonUtil.getToken(this)).observe(this,this::initMyCarList);

    }


    @Override
    public boolean onMenuItemClick(MenuItem item) {
        int itemId = item.getItemId();
        switch (itemId){
            case R.id.menu_add_car:
                addCar();
                return true;
            default:
                return false;
        }
    }

    private void initMyCarList(List<Car> data){
        adapter = new MyCarAdapter(data,MyCarActivity.this);
        mMyCarList.setAdapter(adapter);
        adapter.setOnItemSelectedListener(new MyCarAdapter.OnItemSelectedListener() {
            @Override
            public void onItemSelectedListener(MenuItem item, Car car) {
                switch (item.getItemId()){
                    case R.id.modify_car_info_menu:
                        modifyCarInfo(car);
                        break;
                    case R.id.move_car_menu:
                        MoveCarDialog dialog = new MoveCarDialog();
                        dialog.show(getSupportFragmentManager(),"");
                        break;
                    case R.id.del_car_menu:
                        stopWhereViewModel.delCar(CommonUtil.getToken(MyCarActivity.this), car.getId()).observe(MyCarActivity.this, new Observer<RequestMessage>() {
                            @Override
                            public void onChanged(RequestMessage requestMessage) {
                                if(requestMessage.getCode() == 200){
                                    stopWhereViewModel.getMyCarList(CommonUtil.getToken(MyCarActivity.this)).getValue();
                                }
                            }
                        });
                        break;
                }
            }
        });
    }

    // TODO:其实可以先把数据修改部分全部放到数据库完成，
    //  在写一些后台任务提交网络请求，这样感觉应该更合理，
    //  也可以更方便的实现本地化，性能也应该会有所提升

    private void modifyCarInfo(Car car){
        CarInfoDialogFragment dialogFragment = new CarInfoDialogFragment(car);
        dialogFragment.show(getSupportFragmentManager(),"");
        dialogFragment.setRefreshDataListener(()->{
            List<Car> value = stopWhereViewModel.getMyCarList(CommonUtil.getToken(MyCarActivity.this)).getValue();
            adapter.setData(value);
        });
    }

    private void addCar(){
        MyCarAddDialogFragment fragment = new MyCarAddDialogFragment();
        fragment.show(getSupportFragmentManager(),"");
        fragment.setRefreshListener(new MyCarAddDialogFragment.RefreshListener() {
            @Override
            public void onRefresh() {
                List<Car> value = stopWhereViewModel.
                        getMyCarList(CommonUtil.getToken(MyCarActivity.this)).getValue();
                adapter.setData(value);
            }
        });
    }
}