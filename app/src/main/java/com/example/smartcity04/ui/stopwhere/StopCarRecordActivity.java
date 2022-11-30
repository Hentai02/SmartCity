package com.example.smartcity04.ui.stopwhere;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.smartcity04.R;
import com.example.smartcity04.adapter.stopwhere.StopCarRecordAdapter;
import com.example.smartcity04.dialog.StopCarHistoryFilterFragment;
import com.example.smartcity04.pojo.stopwhere.StopCarRecord;
import com.example.smartcity04.util.CommonUtil;
import com.example.smartcity04.viewmodels.StopWhereViewModel;

import java.util.List;

public class StopCarRecordActivity extends AppCompatActivity {
    private static final String LOG_TAG = StopCarRecordActivity.class.getSimpleName();
    private StopWhereViewModel model;
    private RecyclerView mRecordRv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stop_car_record);
        CommonUtil.topBar(this,"停车记录");
        mRecordRv = findViewById(R.id.rv_stopCarRecord_list);
        mRecordRv.setLayoutManager(new LinearLayoutManager(this));
        model = new ViewModelProvider(this).get(StopWhereViewModel.class);
        model.getStopCarRecord(null,null,null,null).observe(this,this::initRecord);
        TextView textView = findViewById(R.id.tv_text);
        textView.setText(R.string.text_search);
        textView.setOnClickListener(v -> openFilterDialog());
        findViewById(R.id.fab_record_refresh).setOnClickListener(v -> refresh());
    }

    private void refresh() {
        List<StopCarRecord> value = model.getStopCarRecord(null, null, null, null).getValue();
        initRecord(value);
    }

    public void openFilterDialog(){
        StopCarHistoryFilterFragment fragment = new StopCarHistoryFilterFragment();
        fragment.show(getSupportFragmentManager(),null);

        fragment.setRefreshListener((filterCategory, filter) -> {
            List<StopCarRecord> value;
            Log.d(LOG_TAG,filterCategory);
            if (filterCategory.equals(getString(R.string.entryTime))){
                value = model.getStopCarRecord(filter,null,null,null).getValue();
            }else if(filterCategory.equals(getString(R.string.outTime))){
                value = model.getStopCarRecord(null,filter,null,null).getValue();
            }else if(filterCategory.equals(getString(R.string.parkName))){
                value = model.getStopCarRecord(null,null,filter,null).getValue();
            }else{
                value = model.getStopCarRecord(null,null,null,filter).getValue();
            }
            initRecord(value);

        });
    }

    public void initRecord(List<StopCarRecord> data){
        mRecordRv.setAdapter(new StopCarRecordAdapter(data,this));
    }
}