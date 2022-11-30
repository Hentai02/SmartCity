package com.example.smartcity04.ui.stopwhere;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;

import com.example.smartcity04.R;
import com.example.smartcity04.adapter.stopwhere.ProductAdapter;
import com.example.smartcity04.adapter.stopwhere.ScoreBillAdapter;
import com.example.smartcity04.dialog.ScoreRankDialogFragment;
import com.example.smartcity04.pojo.Comment;
import com.example.smartcity04.pojo.stopwhere.ScoreInfo;
import com.example.smartcity04.util.CommonUtil;
import com.example.smartcity04.viewmodels.StopWhereViewModel;

import java.util.List;

public class ScoreBillActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {
    private static final String LOG_TAG = ScoreBillActivity.class.getSimpleName();
    private RecyclerView mScoreBillListRv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_bill);
        CommonUtil.topBar(this,"积分账单");
        mScoreBillListRv = findViewById(R.id.rv_score_bill_list);
        mScoreBillListRv.setLayoutManager(new LinearLayoutManager(this));

        StopWhereViewModel stopWhereViewModel = new ViewModelProvider(this).get(StopWhereViewModel.class);
        stopWhereViewModel.getScoreList(CommonUtil.getToken(this)).observe(this,this::initScoreBill);

        ImageView view = findViewById(R.id.iv_more);
        view.setOnClickListener(v -> {
            PopupMenu popupMenu = CommonUtil.onPopupMenu(view, ScoreBillActivity.this, R.menu.score_bill_menu);
            popupMenu.setOnMenuItemClickListener(ScoreBillActivity.this);
            popupMenu.show();
        });

    }

    private void initScoreBill(List<ScoreInfo> data){
        mScoreBillListRv.setAdapter(new ScoreBillAdapter(data,this));

    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        ScoreRankDialogFragment fragment = new ScoreRankDialogFragment();
        fragment.show(getSupportFragmentManager(),"");
        return true;
    }
}