package com.example.smartcity04.ui.stopwhere;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.smartcity04.R;
import com.example.smartcity04.adapter.stopwhere.RechargeInfoAdapter;
import com.example.smartcity04.dialog.StopWhereRechargeFragment;
import com.example.smartcity04.pojo.reqdata.RequestMessage;
import com.example.smartcity04.pojo.stopwhere.RechargeInfo;
import com.example.smartcity04.util.CommonUtil;
import com.example.smartcity04.viewmodels.StopWhereViewModel;

import java.util.List;

public class StopWhereWalletActivity extends AppCompatActivity {
    private static final String LOG_TAG = StopWhereWalletActivity.class.getSimpleName();
    private RecyclerView mRechargeInfoList;
    private StopWhereViewModel stopWhereViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stop_where_wallet);
        CommonUtil.topBar(this,"钱包");
        mRechargeInfoList = findViewById(R.id.rv_park_recharge_list);
        mRechargeInfoList.setLayoutManager(new LinearLayoutManager(this));
        stopWhereViewModel = new ViewModelProvider(this).get(StopWhereViewModel.class);

        String token = CommonUtil.getToken(this);
        stopWhereViewModel.getRechargeInfo(token).observe(this,this::initRechargeInfoList);

        findViewById(R.id.tv_text).setOnClickListener(this::recharge);

    }

    private void initRechargeInfoList(List<RechargeInfo> data){
        mRechargeInfoList.setAdapter(new RechargeInfoAdapter(data,this));
    }

    public void recharge(View view) {

        StopWhereRechargeFragment fragment = new StopWhereRechargeFragment();
        fragment.show(getSupportFragmentManager(),null);
        fragment.setRefreshListener(this::refresh);

    }

    private void refresh(String payType,Integer money){
        stopWhereViewModel.recharge(CommonUtil.getToken(this), money, payType).observe(this, new Observer<RequestMessage>() {
            @Override
            public void onChanged(RequestMessage requestMessage) {
                if(requestMessage.getCode() == 200){
                    List<RechargeInfo> value = stopWhereViewModel.
                            getRechargeInfo(CommonUtil.getToken(StopWhereWalletActivity.this)).getValue();
                    initRechargeInfoList(value);
                }

                Toast.makeText(StopWhereWalletActivity.this, requestMessage.getMsg(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}