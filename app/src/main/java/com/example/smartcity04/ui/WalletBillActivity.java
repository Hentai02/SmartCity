package com.example.smartcity04.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.PopupMenu;

import com.example.smartcity04.R;
import com.example.smartcity04.adapter.BillInfoAdapter;
import com.example.smartcity04.dialog.RechargeFragment;
import com.example.smartcity04.dialog.WalletRankDialogFragment;
import com.example.smartcity04.pojo.BillInfo;
import com.example.smartcity04.util.CommonUtil;
import com.example.smartcity04.viewmodels.BasicViewModel;

import java.util.List;
import java.util.Objects;

public class WalletBillActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {
    private static final String LOG_TAG = WalletBillActivity.class.getSimpleName();

    private BasicViewModel basicViewModel;
    private RecyclerView mBillRecyclerView;
    private BillInfoAdapter mBillInfoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallet_bill);
        CommonUtil.topBar(this,"交易记录");
        mBillRecyclerView = findViewById(R.id.rv_bill_list);
        basicViewModel = new ViewModelProvider(this).get(BasicViewModel.class);

        basicViewModel.getBill(Objects.requireNonNull(CommonUtil.getToken(this))).observe(this, bills -> {
            mBillInfoAdapter = new BillInfoAdapter(bills,WalletBillActivity.this);
            mBillRecyclerView.setAdapter(mBillInfoAdapter);
        });

        ImageView view = findViewById(R.id.iv_more);
        view.setOnClickListener(v -> {
            PopupMenu popupMenu = CommonUtil.onPopupMenu(v, this, R.menu.wallet_bill_menu);
            popupMenu.show();
            popupMenu.setOnMenuItemClickListener(WalletBillActivity.this);
        });
    }

    private void recharge() {
        RechargeFragment fragment = new RechargeFragment();
        fragment.show(getSupportFragmentManager(),"充值");
        fragment.setRefreshListener(()->{
            List<BillInfo> value = basicViewModel.getBill(Objects.requireNonNull(CommonUtil.getToken(this))).getValue();
            mBillInfoAdapter.setData(value);
        });
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        int itemId = item.getItemId();
        switch (itemId){
            case R.id.menu_recharge:
                recharge();
                return true;
            case R.id.menu_balance_rank:
                WalletRankDialogFragment fragment = new WalletRankDialogFragment();
                fragment.show(getSupportFragmentManager(),"");
                return true;
        }
        return false;
    }
}