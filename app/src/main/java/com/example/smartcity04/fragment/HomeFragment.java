package com.example.smartcity04.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;

import com.example.smartcity04.R;
import com.example.smartcity04.adapter.DemoCollectionAdapter;
import com.example.smartcity04.pojo.Comment;
import com.example.smartcity04.pojo.NewsCategory;
import com.example.smartcity04.util.SmContext;
import com.example.smartcity04.viewmodels.BasicViewModel;
import com.google.android.material.tabs.TabLayout;

import java.util.List;


public class HomeFragment extends Fragment {
    private static final String LOG_TAG = HomeFragment.class.getSimpleName();
    // 新闻类别
    private TabLayout mNewsCategoryTab;

    private ViewPager2 mCategoryPage;

    // 共享首选项
    private SharedPreferences mPreferences;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPreferences = requireActivity().getSharedPreferences(SmContext.SHARE_FILE_NEWS_CATEGORY,Context.MODE_PRIVATE);
        BasicViewModel model = new ViewModelProvider(requireActivity()).get(BasicViewModel.class);
        // 加载tab新闻类别
        model.getNewsCategory().observe(this, this::initNewsCategoryTab);

        model.getComment(null,null,null).observe(requireActivity(), new Observer<List<Comment>>() {
            @Override
            public void onChanged(List<Comment> comments) {
                if(onLoadNewsCommentListener != null){
                    onLoadNewsCommentListener.onLoadNewsCommentListener();
                }
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        // 初始化控件
        mNewsCategoryTab = view.findViewById(R.id.tab_news_category);
        mCategoryPage = view.findViewById(R.id.vp2_news_category);
        return view;
    }

    /**
     * 保存新闻分类到共享首选项
     * @param name
     * @param id
     */
    public void saveNewsCategory(String name ,int id){
        int categoryId = mPreferences.getInt(name, -1);
        if(categoryId != -1 && categoryId == id) return;
        SharedPreferences.Editor edit = mPreferences.edit();
        edit.putInt(name,id);
        edit.apply();
    }


    /**
     * 初始化新闻类别
     * @param category
     */
    public void initNewsCategoryTab(List<NewsCategory> category){
        for (int i=0;i<category.size();i++){
            NewsCategory e = category.get(i);
            mNewsCategoryTab.addTab(mNewsCategoryTab.newTab().setText(e.getName()));
            saveNewsCategory(SmContext.PREFIX_CATEGORY_P + i,e.getId());
        }

        mCategoryPage.setAdapter(new DemoCollectionAdapter(requireActivity(),category.size()));

        mNewsCategoryTab.setTabGravity(TabLayout.GRAVITY_FILL);
        mCategoryPage.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                mNewsCategoryTab.setScrollPosition(position,positionOffset,true);
            }


        });
        mNewsCategoryTab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mCategoryPage.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });

    }

    private interface OnLoadNewsCommentListener{
        void onLoadNewsCommentListener();
    }

    private OnLoadNewsCommentListener onLoadNewsCommentListener;

    public void setOnLoadNewsCommentListener(OnLoadNewsCommentListener onLoadNewsCommentListener) {
        this.onLoadNewsCommentListener = onLoadNewsCommentListener;
    }
}

