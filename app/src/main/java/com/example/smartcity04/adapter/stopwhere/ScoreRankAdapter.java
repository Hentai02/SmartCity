package com.example.smartcity04.adapter.stopwhere;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.smartcity04.R;
import com.example.smartcity04.network.BasicApi;
import com.example.smartcity04.pojo.stopwhere.ScoreRank;
import com.example.smartcity04.util.SmContext;

import java.util.List;

public class ScoreRankAdapter extends RecyclerView.Adapter<ScoreRankAdapter.ViewHolder> {
    private static final String LOG_TAG = ScoreRankAdapter.class.getSimpleName();
    private final List<ScoreRank> data;
    private final Context mContext;
    private RequestOptions options;

    public ScoreRankAdapter(List<ScoreRank> data,Context context){
        this.data = data;
        this.mContext = context;
        options = new RequestOptions().centerCrop().transform(new RoundedCorners(90));
    }


    @NonNull
    @Override
    public ScoreRankAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_score_rank,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ScoreRankAdapter.ViewHolder holder, int position) {
        ScoreRank e = data.get(position);
        if(e.getAvatar() != null){
            if(e.getAvatar().contains(BasicApi.BASE_URI) || e.getAvatar().contains("http://124.93.196.45")){
                Glide.with(mContext).load(R.drawable.ic_user_avatars).apply(options).into(holder.mAvatarIv);
            }else if (e.getAvatar().contains("http://") || e.getAvatar().contains("https://")){
                Glide.with(mContext).load(e.getAvatar()).apply(options).into(holder.mAvatarIv);
            }else{
                Glide.with(mContext).load(R.drawable.ic_user_avatars).apply(options).into(holder.mAvatarIv);
            }
        }

        if(e.getNickName() != null){
            holder.mUsernameTv.setText(e.getNickName());
        }else{
            holder.mUsernameTv.setText(e.getUserName());
        }
        String sex = e.getSex().equals("1") ? "女":"男";
        holder.mSexTv.setText(mContext.getString(R.string.rank_sex_format,sex));
        holder.mBalanceTv.setText(mContext.getString(R.string.rank_balance_format,e.getBalance()));
        holder.mScoreTv.setText(mContext.getString(R.string.rank_score_format,e.getScore()));
        holder.mRankTv.setText(String.valueOf(position + 1));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private final ImageView mAvatarIv;
        private final TextView mUsernameTv;
        private final TextView mSexTv;
        private final TextView mBalanceTv;
        private final TextView mScoreTv;

        private final TextView mRankTv;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mAvatarIv = itemView.findViewById(R.id.iv_avatar);
            mUsernameTv = itemView.findViewById(R.id.tv_username);
            mSexTv = itemView.findViewById(R.id.tv_sex);
            mBalanceTv = itemView.findViewById(R.id.tv_balance);
            mScoreTv = itemView.findViewById(R.id.tv_score);
            mRankTv = itemView.findViewById(R.id.tv_rank);
        }
    }
}
