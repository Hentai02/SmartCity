package com.example.smartcity04.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartcity04.R;
import com.example.smartcity04.pojo.Comment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.ViewHolder> {
    private static final String LOG_TAG = CommentAdapter.class.getSimpleName();

    private final List<Comment> data;

    public CommentAdapter(List<Comment> data){
        this.data = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_news_comment,parent,false);
        return new ViewHolder(view);
    }

    public void add(Comment comment){
        data.add(comment);
        notifyDataSetChanged();
    }

    public void addAll(List<Comment> comments){
        data.addAll(comments);
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Comment comment = data.get(position);
        holder.userText.setText(comment.getUserName());
        holder.contentText.setText(comment.getContent());
        holder.commentDateText.setText(comment.getCommentDate());
        holder.likeNumText.setText(String.valueOf(comment.getLikeNum()));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView userText;
        private final TextView contentText;
        public final TextView commentDateText;
        public  final  TextView likeNumText;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            userText = itemView.findViewById(R.id.tv_news_reply_username);
            contentText = itemView.findViewById(R.id.tv_news_reply_content);
            commentDateText = itemView.findViewById(R.id.tv_news_reply_commentDate);
            likeNumText = itemView.findViewById(R.id.tv_news_reply_like_num);
        }
    }
}
