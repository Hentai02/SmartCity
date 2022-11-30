package com.example.smartcity04.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.smartcity04.R;
import com.example.smartcity04.adapter.GlideImageLoader;
import com.example.smartcity04.pojo.IconAndTitleItem;
import com.example.smartcity04.adapter.NewsListAdapter;
import com.example.smartcity04.adapter.ServiceItemAdapter;
import com.example.smartcity04.pojo.Carousel;
import com.example.smartcity04.pojo.NewsInfo;
import com.example.smartcity04.network.BasicApi;
import com.example.smartcity04.util.SmContext;
import com.example.smartcity04.viewmodels.BasicViewModel;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

public class HomeTodayNewsFragment extends Fragment {
    private static final String LOG_TAG = HomeTodayNewsFragment.class.getSimpleName();

    private BasicViewModel mBasicViewModel;

    // 轮播图
    private Banner mBanner;
    // 服务列表
    private RecyclerView mServiceItemRecycler;
    // 新闻列表
    private RecyclerView mNewsRecycler;

    private int mPosition;

    public HomeTodayNewsFragment(){}

    public HomeTodayNewsFragment(int position) {
        // Required empty public constructor
        this.mPosition = position;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences mSharePrf = requireActivity().getSharedPreferences(SmContext.SHARE_FILE_NEWS_CATEGORY, Context.MODE_PRIVATE);
        int key = mSharePrf.getInt(SmContext.PREFIX_CATEGORY_P + mPosition,9);
        _initFragment(key);
    }


    public void _initFragment(int typeId){
        BasicViewModel model = new ViewModelProvider(requireActivity()).get(BasicViewModel.class);
        // 加载新闻列表
        model.getNewsInfoList(String.valueOf(typeId)).observe(this,this::onLoadingNewsList);
        // 加载轮播图
        model.getCarousel().observe(this,this::initCarousel);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.subfrag_home_today_news, container, false);
        mBanner = (Banner) view.findViewById(R.id.banner);
        mServiceItemRecycler = view.findViewById(R.id.rv_service_list);
        mNewsRecycler = view.findViewById(R.id.rv_news_list);
        initServiceItems();// 初始化服务项
        return view;
    }

    /**
     * 加载新闻列表
     *
     * @param
     */
    private void onLoadingNewsList(List<NewsInfo> data) {
        mNewsRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        mNewsRecycler.setAdapter(new NewsListAdapter(data,getContext()));
    }

    /**
     * 初始化服务项
     */
    private void initServiceItems() {
        List<IconAndTitleItem> items = new ArrayList<>();
        mServiceItemRecycler.setLayoutManager(new GridLayoutManager(getContext(), 5));
        mServiceItemRecycler.setAdapter(new ServiceItemAdapter(items, getContext()));
        items.add(new IconAndTitleItem(R.drawable.ic_home_stop_where, getString(R.string.service_stop_where)));
        items.add(new IconAndTitleItem(R.drawable.ic_home_city_subway, getString(R.string.service_city_subway)));
        items.add(new IconAndTitleItem(R.drawable.ic_home_takeaway_order, getString(R.string.service_takeaway_order)));
        items.add(new IconAndTitleItem(R.drawable.ic_home_wisdom_traffic, getString(R.string.service_wisdom_traffic)));
        items.add(new IconAndTitleItem(R.drawable.ic_home_find_job, getString(R.string.service_find_job)));
        items.add(new IconAndTitleItem(R.drawable.ic_home_find_house, getString(R.string.service_find_house)));
        items.add(new IconAndTitleItem(R.drawable.ic_home_event_manage, getString(R.string.service_event_manage)));
        items.add(new IconAndTitleItem(R.drawable.ic_home_living_expenses, getString(R.string.service_living_expenses)));
        items.add(new IconAndTitleItem(R.drawable.ic_home_look_movie, getString(R.string.service_look_movie)));
        items.add(new IconAndTitleItem(R.drawable.ic_user_setting_all, getString(R.string.service_more)));
    }

    /**
     * 初始化轮播图
     * @param data
     */
    private void initCarousel(List<Carousel> data) {
        List<String> advImages = new ArrayList<>();
        for (Carousel e:data){
            advImages.add(BasicApi.BASE_URI + e.getAdvImg());
        }
        //设置图片加载器
        mBanner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        mBanner.setImages(data);
        //banner设置方法全部调用完毕时最后调用
        mBanner.start();
    }
}