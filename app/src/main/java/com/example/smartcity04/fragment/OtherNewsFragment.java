package com.example.smartcity04.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.smartcity04.R;
import com.example.smartcity04.adapter.NewsListAdapter;
import com.example.smartcity04.pojo.NewsInfo;
import com.example.smartcity04.util.SmContext;
import com.example.smartcity04.viewmodels.BasicViewModel;

import java.util.List;


public class OtherNewsFragment extends Fragment {
    private static final String LOG_TAG = OtherNewsFragment.class.getSimpleName();

    // 新闻列表
    private RecyclerView mNewsRecycler;
    private int position = 0;

    public OtherNewsFragment(int position) {
        // Required empty public constructor
        this.position = position;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences mSharePrf = requireActivity().getSharedPreferences(SmContext.SHARE_FILE_NEWS_CATEGORY, Context.MODE_PRIVATE);
        int key = mSharePrf.getInt(SmContext.PREFIX_CATEGORY_P + position, 9);
        _initFragment(key);
    }


    public void _initFragment(int typeId){
        BasicViewModel model = new ViewModelProvider(this).get(BasicViewModel.class);
        // 加载新闻列表
        model.getNewsInfoList(String.valueOf(typeId)).observe(this,this::onLoadingNewsList);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.subfrag_home_news_others, container, false);
        mNewsRecycler = view.findViewById(R.id.rv_news_list);
        return view;
    }

    /**
     * 加载新闻列表
     *
     * @param
     */
    private void onLoadingNewsList(List<NewsInfo> data) {
        mNewsRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        mNewsRecycler.setAdapter(new NewsListAdapter(data, getContext()));
    }
}