package com.example.smartcity04.adapter;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.smartcity04.fragment.HomeNewsCommentFragment;


public class CommentFragmentStateAdapter extends FragmentStateAdapter {
    private static final String LOG_TAG = CommentFragmentStateAdapter.class.getSimpleName();
    private Bundle bundle;


    public CommentFragmentStateAdapter(@NonNull FragmentActivity fragmentActivity,Bundle bundle) {
        super(fragmentActivity);
        this.bundle = bundle;
    }

    @NonNull
    @Override
    public Fragment createFragment(int i) {
        if (i == 1){
            HomeNewsCommentFragment commentFragment = new HomeNewsCommentFragment();
            commentFragment.setArguments(bundle);
            return commentFragment;
        }else{
            return new HomeNewsCommentFragment();
        }

    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
