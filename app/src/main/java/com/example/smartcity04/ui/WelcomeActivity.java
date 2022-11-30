package com.example.smartcity04.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.View;

import com.example.smartcity04.R;
import com.example.smartcity04.adapter.DemoCollectionAdapter;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        ViewPager2 vp2 = findViewById(R.id.vp_welcome);
        vp2.setAdapter(new DemoCollectionAdapter(this,5));
    }
}