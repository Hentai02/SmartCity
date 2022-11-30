package com.example.smartcity04.ui.stopwhere;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.smartcity04.R;
import com.example.smartcity04.adapter.stopwhere.ProductAdapter;
import com.example.smartcity04.pojo.reqdata.RequestMessage;
import com.example.smartcity04.pojo.stopwhere.Product;
import com.example.smartcity04.util.CommonUtil;
import com.example.smartcity04.viewmodels.StopWhereViewModel;

import java.util.List;

public class StoreActivity extends AppCompatActivity {
    private static final String LOG_TAG = StoreActivity.class.getSimpleName();
    private StopWhereViewModel stopWhereViewModel;

    private RecyclerView mProductRv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store);
        CommonUtil.topBar(this,"积分商店");
        stopWhereViewModel = new ViewModelProvider(this).get(StopWhereViewModel.class);
        mProductRv = findViewById(R.id.rv_product_list);
        mProductRv.setLayoutManager(new LinearLayoutManager(this));

        stopWhereViewModel.getProduct().observe(this,this::initProduct);



    }

    private void initProduct(List<Product> data){
        ProductAdapter adapter = new ProductAdapter(data,this);
        mProductRv.setAdapter(adapter);

        adapter.setBuyConsumeListener(new ProductAdapter.BuyConsumeListener() {
            @Override
            public void onBuyConsumeListener(Integer productId) {
                stopWhereViewModel.consume(CommonUtil.getToken(StoreActivity.this),productId).observe(StoreActivity.this, new Observer<RequestMessage>() {
                    @Override
                    public void onChanged(RequestMessage requestMessage) {
                        Toast.makeText(StoreActivity.this, requestMessage.getMsg(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}