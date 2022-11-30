package com.example.smartcity04;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.example.smartcity04.fragment.HomeFragment;
import com.example.smartcity04.fragment.TestFragment1;
import com.example.smartcity04.fragment.TestFragment2;
import com.example.smartcity04.fragment.TestFragment3;
import com.example.smartcity04.fragment.TestFragment4;
import com.example.smartcity04.fragment.UserHomeFragment;

import com.example.smartcity04.pojo.Comment;
import com.example.smartcity04.ui.UserInfoActivity;
import com.example.smartcity04.util.CommonUtil;
import com.example.smartcity04.viewmodels.BasicViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.gson.Gson;


import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity
        implements NavigationBarView.OnItemSelectedListener{
    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    // 首页fragment切换
    private FragmentManager mFragManager;
    private final List<Fragment> fragments = new ArrayList<>();
    EditText searchView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView mNav = findViewById(R.id.bottom_nav);
        mNav.setOnItemSelectedListener(this);
        mFragManager = getSupportFragmentManager();

        initMainFragment();

        findViewById(R.id.iv_user_avatars).setOnClickListener(v -> {
            Intent intent = new Intent(this, UserInfoActivity.class);
            startActivity(intent);
        });

        searchView = findViewById(R.id.searchView);
        searchView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(actionId == EditorInfo.IME_ACTION_SEND){
                    CommonUtil.hideKeyboard(MainActivity.this,v);
                    displayFragment(TestFragment4.class);
                    Log.d(LOG_TAG,"EditText Clicked");
                    searchView.clearFocus();
                    return true;
                }
                return false;
            }
        });


        BasicViewModel basicViewModel = new ViewModelProvider(this).get(BasicViewModel.class);
        basicViewModel.getComment(null,null,null).observe(this, new Observer<List<Comment>>() {
            @Override
            public void onChanged(List<Comment> comments) {
                Gson gson = new Gson();
                String s = gson.toJson(comments);
                try (FileOutputStream fos = MainActivity.this.openFileOutput("comments_news.json", Context.MODE_PRIVATE)) {
                    fos.write(s.getBytes(StandardCharsets.UTF_8));
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });

//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("")
//                .client(new OkHttpClient.Builder()
//                        .writeTimeout(90,TimeUnit.SECONDS)
//                        .readTimeout(90,TimeUnit.SECONDS)
//                        .build()
//                )
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();


    }


    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        switch (itemId){
            case R.id.home_page_nav:
                displayFragment(HomeFragment.class);
                searchView.setText("");
                return true;
            case R.id.all_service_nav:
                displayFragment(TestFragment1.class);
                return true;
            case R.id.wisdom_part_nav:
                displayFragment(TestFragment2.class);
                return true;
            case R.id.news_nav:
                displayFragment(TestFragment3.class);
                return true;
            case R.id.user_nav:
                displayFragment(UserHomeFragment.class);
                return true;
            default:
                return false;
        }
    }

    /**
     * 初始化主页
     * 将所有fragment添加到容器中，getFragments包含子fragment故重新创建一个容器
     * 隐藏其他fragment，显示homefragment
     *
     */
    private void initMainFragment(){
        HomeFragment homeFragment = new HomeFragment();
        TestFragment1 t1 = new TestFragment1();
        TestFragment2 t2 = new TestFragment2();
        TestFragment3 t3 = new TestFragment3();
        UserHomeFragment userHomeFragment = new UserHomeFragment();
        TestFragment4 t4 = new TestFragment4();

        fragments.add(homeFragment);
        fragments.add(t1);
        fragments.add(t2);
        fragments.add(t3);
        fragments.add(userHomeFragment);
        fragments.add(t4);

        for (Fragment e:fragments){
            mFragManager.beginTransaction().add(R.id.fragment_container,e).commit();
            mFragManager.beginTransaction().hide(e).commit();
            Log.d(LOG_TAG,"init_隐藏fragment:" + e.getClass().getSimpleName());
        }
        mFragManager.beginTransaction().show(homeFragment).commit();
        Log.d(LOG_TAG,"init_显示fragment:" + homeFragment.getClass().getSimpleName());
    }

    /**
     * 隐藏其他只显示选中的nav对应的fragment
     * @param clazz
     */
    private void displayFragment(Class<?> clazz){

        for (Fragment fragment:fragments){
            if(fragment.getClass() == clazz){
                mFragManager.beginTransaction().show(fragment).commit();
                Log.d(LOG_TAG,"显示fragment:" + fragment.getClass().getSimpleName());
            }else{
                mFragManager.beginTransaction().hide(fragment).commit();
                Log.d(LOG_TAG,"隐藏fragment:" + fragment.getClass().getSimpleName());
            }
        }
    }
}