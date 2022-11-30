package com.example.smartcity04.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.smartcity04.R;
import com.example.smartcity04.util.CommonUtil;

public class FeedBackActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_back);
        CommonUtil.topBar(this,"反馈");
    }
}