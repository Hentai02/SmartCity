package com.example.smartcity04.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.smartcity04.R;
import com.example.smartcity04.pojo.UserInfo;
import com.example.smartcity04.util.CommonUtil;
import com.example.smartcity04.viewmodels.BasicViewModel;

// TODO:用户详情
public class UserInfoActivity extends AppCompatActivity {

    private static final String LOG_TAG = UserInfoActivity.class.getSimpleName();

    private TextView mWallet;
    private ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userinfo_details);
        CommonUtil.topBar(this,"用户详情");
        mWallet = findViewById(R.id.tv_details_wallet);
        TextView username = findViewById(R.id.tv_details_username);
        mImageView = findViewById(R.id.iv_details_avatar);

        BasicViewModel basicViewModel = new ViewModelProvider(this).get(BasicViewModel.class);
        basicViewModel.getUserInfo(CommonUtil.getToken(this)).observe(this, new Observer<UserInfo>() {
            @Override
            public void onChanged(UserInfo info) {
                mWallet.setText(getString(R.string.user_info_details_wallet_format,info.getBalance(),info.getScore()));
                username.setText(info.getNickName());
                Glide.with(UserInfoActivity.this).load(info.getAvatar()).into(mImageView);

            }
        });

        findViewById(R.id.tv_details_modify_userinfo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserInfoActivity.this,UserInfoModifyActivity.class);
                startActivity(intent);
            }
        });
    }
}