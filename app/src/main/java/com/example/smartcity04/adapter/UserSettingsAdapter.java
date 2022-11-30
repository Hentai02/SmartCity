package com.example.smartcity04.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartcity04.R;
import com.example.smartcity04.ui.FeedBackActivity;
import com.example.smartcity04.ui.MyCommentActivity;
import com.example.smartcity04.ui.setting.SettingsActivity;
import com.example.smartcity04.pojo.IconAndTitleItem;

import java.util.List;

public class UserSettingsAdapter extends RecyclerView.Adapter<IconAndTitleViewHolder> {
    private static final String LOG_TAG = UserSettingsAdapter.class.getSimpleName();

    private List<IconAndTitleItem> data;
    private Context mContext;

    public UserSettingsAdapter(List<IconAndTitleItem> dataSet,Context context){
        this.data = dataSet;
        this.mContext = context;
    }


    @NonNull
    @Override
    public IconAndTitleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_user_setting,parent,false);

        return new IconAndTitleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull IconAndTitleViewHolder holder, int position) {
        IconAndTitleItem e = data.get(position);
        holder.mTitle.setText(e.title);
        holder.mIcon.setImageResource(e.id);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(getString(R.string.setting_feedback).equals(e.title)){
                    Intent intent = new Intent(mContext, FeedBackActivity.class);
                    mContext.startActivity(intent);
                }else if(getString(R.string.setting_my_reply).equals(e.title)){
                    Intent intent = new Intent(mContext, MyCommentActivity.class);
                    mContext.startActivity(intent);
                }else if(getString(R.string.setting_blacklist_management).equals(e.title)){
                    Log.d(LOG_TAG,"黑名单管理。。。");
                }else if(getString(R.string.setting_more).equals(e.title)){
                    Log.d(LOG_TAG,"更多。。。");
                }else{
                    Intent intent = new Intent(mContext, SettingsActivity.class);
                    mContext.startActivity(intent);
                    Log.d(LOG_TAG,"设置。。。");
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    private String getString(int resId){
        String string = mContext.getString(resId);
        return string;
    }

}
