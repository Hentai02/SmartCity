package com.example.smartcity04.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartcity04.R;
import com.example.smartcity04.adapter.CommentAdapter;
import com.example.smartcity04.pojo.Comment;
import com.example.smartcity04.ui.NewsDetailsActivity;
import com.example.smartcity04.util.SmContext;
import com.example.smartcity04.viewmodels.BasicViewModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HomeNewsCommentFragment extends Fragment {
    private final String LOG_TAG = HomeNewsCommentFragment.class.getSimpleName();

    private RecyclerView mCommentRecycler;
    private Integer mNewsId;
    BasicViewModel mBasicViewModel;
    CommentAdapter adapter;

    public HomeNewsCommentFragment(){

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBasicViewModel = new ViewModelProvider(this).get(BasicViewModel.class);

        // TODO:点击新闻详情的时候加载评论，400条左右会出现卡顿（绘制），考虑在加载新闻列表时将评论加载传递给当前Fragment
        if(getArguments() != null){
            Bundle arguments = getArguments();
            mNewsId = arguments.getInt(SmContext.NEWS_EXTRA_ID);
            mBasicViewModel.getComment(mNewsId,null,null).observe(requireActivity(),this::initComments);
        }

        loadComment();

        FragmentActivity fragmentActivity = requireActivity();
        if(fragmentActivity instanceof NewsDetailsActivity){
            ((NewsDetailsActivity) fragmentActivity).setReplyNewsCommentListener(new NewsDetailsActivity.replyNewsCommentListener() {
                @Override
                public int OnReplyNewsCommentListener() {
                    List<Comment> value = mBasicViewModel.getComment(mNewsId, null, null).getValue();
                    initComments(value);
                    assert value != null;
                    return value.size();
                }
            });
        }

    }

    private void loadComment(){

        FileInputStream fis = null;
        try {
            fis = requireActivity().openFileInput("comments_news.json");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        if(fis != null){
            InputStreamReader inputStreamReader =
                    new InputStreamReader(fis, StandardCharsets.UTF_8);
            StringBuilder stringBuilder = new StringBuilder();
            try (BufferedReader reader = new BufferedReader(inputStreamReader)) {
                String line = reader.readLine();
                while (line != null) {
                    stringBuilder.append(line).append('\n');
                    line = reader.readLine();
                }
            } catch (IOException e) {

            } finally {
                String contents = stringBuilder.toString();
                try {
                    JSONArray jsonArray = new JSONArray(contents);
                    for (int i=0;i<jsonArray.length();i++){
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        //Log.d(LOG_TAG,jsonObject.toString());

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }else{

        }

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.subfrag_news_comment_list,container,false);
        mCommentRecycler = view.findViewById(R.id.rv_comment_list);
        view.findViewById(R.id.tv_show_more_comment).setOnClickListener(v -> {
            mBasicViewModel.getComment(mNewsId, null, null).observe(requireActivity(), new Observer<List<Comment>>() {
                @Override
                public void onChanged(List<Comment> comments) {
                    if(comments == null) return;
                    int itemCount = adapter.getItemCount();
                    if(itemCount == comments.size())return;

                    int i1 = comments.size() - itemCount;
                    List<Comment> result = new ArrayList<>();
                    if(i1>5){
                        for(int i=itemCount;i<itemCount+5;i++){
                            result.add(comments.get(i));
                        }
                        itemCount = adapter.getItemCount();
                    }else{
                        for(int i=itemCount;i<itemCount+i1;i++){
                            result.add(comments.get(i));
                        }
                    }
                    adapter.addAll(result);

                }
            });
        });
        return view;
    }

//    private void replyNewsComment(String comment){
//        BasicViewModel basicViewModel = new ViewModelProvider(requireActivity()).get(BasicViewModel.class);
//        List<Comment> value = basicViewModel.getComment(mNewsId, null, null).getValue();
//        initComments(value);
//    }

    private void initComments(List<Comment> comments){
        //Collections.sort(comments);
        List<Comment> result = new ArrayList<>();
        if(comments != null){
            if(comments.size() > 20){
                for (int i=0;i<20;i++){
                    result.add(comments.get(i));
                }
                adapter = new CommentAdapter(result);
            }else{
                adapter = new CommentAdapter(comments);
            }
            mCommentRecycler.setAdapter(adapter);
        }


    }
}
