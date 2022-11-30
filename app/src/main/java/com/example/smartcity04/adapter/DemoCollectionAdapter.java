package com.example.smartcity04.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.smartcity04.fragment.HomeTodayNewsFragment;
import com.example.smartcity04.fragment.OtherNewsFragment;

public class DemoCollectionAdapter extends FragmentStateAdapter {
    private static final String LOG_TAG = DemoCollectionAdapter.class.getSimpleName();
    private int mNumOfTabs;

    public DemoCollectionAdapter(@NonNull FragmentActivity activity, int mNumOfTabs) {
        super(activity);
        this.mNumOfTabs = mNumOfTabs;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new HomeTodayNewsFragment(position);
            default:
               return new OtherNewsFragment(position);
        }
    }

    @Override
    public int getItemCount() {
        return mNumOfTabs;
    }
}
