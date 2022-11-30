package com.example.smartcity04.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import com.example.smartcity04.R;
import com.example.smartcity04.adapter.CommentAdapter;
import com.example.smartcity04.pojo.Comment;
import com.example.smartcity04.util.CommonUtil;
import com.example.smartcity04.util.SmContext;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import retrofit2.converter.gson.GsonConverterFactory;

public class MyCommentActivity extends AppCompatActivity {

    private static final String LOG_TAG = MyCommentActivity.class.getSimpleName();
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_comment);
        CommonUtil.topBar(this,"我的评论");
        mRecyclerView = findViewById(R.id.rv_my_comment_list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        SharedPreferences sharedPreferences = getSharedPreferences(SmContext.SHARE_FILE_USER_TOKEN,MODE_PRIVATE);
        String username = sharedPreferences.getString("username", "");
        try {
            List<Comment> comments = loadComment(username);
            Collections.sort(comments);
            mRecyclerView.setAdapter(new CommentAdapter(comments));
            for(Comment comment:comments){
                Log.d(LOG_TAG,comment.getContent());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    private List<Comment> loadComment(String userName) throws FileNotFoundException, JSONException {
        List<Comment> result = new ArrayList<>();
        FileInputStream fis = this.openFileInput("comments_news.json");
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
            // Error occurred when opening raw file for reading.
        } finally {
            Gson gson = new Gson();
            String contents = stringBuilder.toString();
            JSONArray jsonArray = new JSONArray(contents);
            for (int i=0;i<jsonArray.length();i++){
                Comment comment = gson.fromJson(jsonArray.getString(i), Comment.class);
                if(comment.getUserName().equals(userName)){
                    result.add(comment);
                }
            }
        }
        return result;
    }
}