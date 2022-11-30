package com.example.smartcity04.adapter;

import static com.example.smartcity04.util.SmContext.*;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.smartcity04.ui.NewsDetailsActivity;
import com.example.smartcity04.R;
import com.example.smartcity04.pojo.NewsInfo;
import com.example.smartcity04.util.SmContext;

import java.util.List;

public class NewsListAdapter extends RecyclerView.Adapter<NewsListAdapter.NewsViewHolder> {
    private static final String LOG_TAG = NewsListAdapter.class.getSimpleName();

    private final List<NewsInfo> data;
    private final Context mContext;
    private final SharedPreferences sharedPreferences;
    private final SharedPreferences sharedPreferences2;


    public NewsListAdapter(List<NewsInfo> dataSet, Context context){
        this.mContext = context;
        this.data = dataSet;
        sharedPreferences = mContext.getSharedPreferences(SHARE_FILE_LIKE_NEWS,Context.MODE_PRIVATE);
        sharedPreferences2 = mContext.getSharedPreferences(SHARE_FILE_USER_TOKEN,Context.MODE_PRIVATE);
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NewsViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_news, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        NewsInfo item = data.get(position);
//        Log.d(LOG_TAG,"新闻标题：" + item.getTitle() + ",新闻ID：" + item.getId());
        String username = sharedPreferences2.getString("username", "null");
        String key = username + ":" + item.getId();

        int likeId = sharedPreferences.getInt(key, -1);
        //Log.d(LOG_TAG,likeId + "点赞Id:");
        if(likeId != -1){
            holder.mLikeNumIv.setImageResource(R.drawable.ic_action_liked);
        }


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, NewsDetailsActivity.class);
                intent.putExtra(NEWS_EXTRA_TITLE,item.getTitle());
                intent.putExtra(NEWS_EXTRA_CONTENT,item.getContent());
                intent.putExtra(NEWS_EXTRA_COVER,item.getCover());
                intent.putExtra(NEWS_EXTRA_TYPE,item.getType());
                intent.putExtra(NEWS_EXTRA_UPDATE_TIME,item.getUpdateTime());
                intent.putExtra(NEWS_EXTRA_READ_NUM,item.getReadNum());
                intent.putExtra(NEWS_EXTRA_ID,item.getId());
                intent.putExtra(NEW_EXTRA_COMMENT_NUM,item.getCommentNum());
                intent.putExtra(NEWS_EXTRA_LIKE_NUM,item.getLikeNum());
                mContext.startActivity(intent);
            }
        });

        holder.mTitleTextView.setText(item.getTitle());
        holder.mContentTextView.setText(Html.fromHtml(item.getContent(),Html.FROM_HTML_MODE_COMPACT));
        holder.mReadNumTextView.setText(String.valueOf(item.getReadNum()));
        holder.mLikeNumTextView.setText(String.valueOf(item.getLikeNum()));
        holder.mCommentNumTextView.setText(String.valueOf(item.getCommentNum()));

        if(item.getCover() != null){
            Glide.with(mContext).load(item.getCover()).into(holder.mCoverImageView);
        }else{
            holder.mCoverImageView.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class NewsViewHolder extends RecyclerView.ViewHolder{

        public final ImageView mCoverImageView;
        public final TextView mTitleTextView;
        public final TextView mContentTextView;
        public final TextView mUpdateTimeView;
        public final TextView mReadNumTextView;
        public final TextView mLikeNumTextView;
        public final TextView mCommentNumTextView;
        private final ImageView mLikeNumIv;

        public NewsViewHolder(@NonNull View itemView) {
            super(itemView);
            mCoverImageView = itemView.findViewById(R.id.iv_news_cover_i);
            mTitleTextView = itemView.findViewById(R.id.tv_news_title);
            mContentTextView = itemView.findViewById(R.id.tv_news_content);
            mUpdateTimeView = itemView.findViewById(R.id.tv_news_updateTime);
            mReadNumTextView = itemView.findViewById(R.id.tv_news_read_num);
            mLikeNumTextView = itemView.findViewById(R.id.tv_news_like_num);
            mCommentNumTextView = itemView.findViewById(R.id.tv_news_comment_num);
            mLikeNumIv = itemView.findViewById(R.id.iv_like_num);
        }
    }
}
