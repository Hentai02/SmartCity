package com.example.smartcity04.ui;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.smartcity04.R;
import com.example.smartcity04.adapter.CommentFragmentStateAdapter;
import com.example.smartcity04.pojo.Comment;
import com.example.smartcity04.pojo.reqdata.RequestMessage;
import com.example.smartcity04.util.CommonUtil;
import com.example.smartcity04.util.SmContext;
import com.example.smartcity04.viewmodels.BasicViewModel;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.JsonObject;

import java.util.List;

public class NewsDetailsActivity extends AppCompatActivity {
    private static final String LOG_TAG = NewsDetailsActivity.class.getSimpleName();
    private TextView mTitleTextView;// 标题
    private TextView mContentTextView;// 内容
    private ImageView mCoverImageView;// 封面
    private TextView mNewsTypeTextView;// 类型
    private TextView mNewsReadNumTextView;// 浏览量
    private TextView mNewsUpdateTimeTextView;// 更新时间
    private TextView mNewsIdTextView;// id
    private EditText mContent;

    private TextView mNewsCommentNumTextview;
    private TextView mNewsLikeNumTextView;

    private TabLayout mCommentInfoTab;
    private ViewPager2 mNewsCommentPage;

    private int newsID;
    private int commentNum;

    private BasicViewModel mBasicViewModel;
    private ImageView mLikeIv;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_details);
        CommonUtil.topBar(this,"新闻详情");
        mTitleTextView = findViewById(R.id.tv_news_details_title);
        mContentTextView = findViewById(R.id.tv_news_details_content);
        mCoverImageView = findViewById(R.id.iv_news_cover);
        mNewsTypeTextView = findViewById(R.id.tv_news_type);
        mNewsUpdateTimeTextView = findViewById(R.id.tv_news_update_time);
        mNewsReadNumTextView = findViewById(R.id.tv_news_read_num);
        mNewsIdTextView = findViewById(R.id.tv_news_id);
        mCommentInfoTab = findViewById(R.id.tab_news_comment);
        mNewsCommentPage = findViewById(R.id.vp2_news_comment);
        mNewsCommentNumTextview = findViewById(R.id.tv_news_comment_num);
        mNewsLikeNumTextView = findViewById(R.id.tv_news_like_num);
        mContent = findViewById(R.id.et_news_reply);
        mLikeIv = findViewById(R.id.iv_like_num);
        sharedPreferences = getSharedPreferences(SmContext.SHARE_FILE_LIKE_NEWS,MODE_PRIVATE);
        mBasicViewModel = new ViewModelProvider(this).get(BasicViewModel.class);
        _initNewsDetails();

        Bundle bundle = new Bundle();
        bundle.putInt(SmContext.NEWS_EXTRA_ID,newsID);
        bundle.putInt(SmContext.NEW_EXTRA_COMMENT_NUM,commentNum);

        mNewsCommentPage.setAdapter(new CommentFragmentStateAdapter(this,bundle));
        mNewsCommentPage.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                mCommentInfoTab.setScrollPosition(position, positionOffset, true);
            }
        });
        mNewsCommentPage.setCurrentItem(1);
        mCommentInfoTab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mNewsCommentPage.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        mContent.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handler = false;
                if(actionId == EditorInfo.IME_ACTION_DONE){
                    reply();
                    handler = true;
                }
                return handler;
            }
        });

        SharedPreferences userSharedPreferences = getSharedPreferences(SmContext.SHARE_FILE_USER_TOKEN,MODE_PRIVATE);
        String username = userSharedPreferences.getString("username", "null");
        String key = username + ":" + newsID;
        int newsId = sharedPreferences.getInt(key, -1);
        if(newsId != -1){
            ((ImageView)findViewById(R.id.iv_like_num)).setImageResource(R.drawable.ic_action_liked);
            return;
        }


        mLikeIv.setOnClickListener(v -> {
            mBasicViewModel.likeNews(CommonUtil.getToken(this),newsID).observe(this, new Observer<RequestMessage>() {
                @Override
                public void onChanged(RequestMessage requestMessage) {
                    String msg = requestMessage.getMsg();
                    ((ImageView)findViewById(R.id.iv_like_num)).setImageResource(R.drawable.ic_action_liked);
                    Toast.makeText(NewsDetailsActivity.this,msg, Toast.LENGTH_SHORT).show();
                    sharedPreferences.edit().putInt(key,newsID).apply();

                }
            });
        });

    }

    private void reply(){
        String content = mContent.getText().toString();
        if(content.length()>0){
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("newsId",newsID);
            jsonObject.addProperty("content",content);
            mBasicViewModel.pressComment(CommonUtil.getToken(this),jsonObject).
                    observe(this, new Observer<RequestMessage>() {
                        @Override
                        public void onChanged(RequestMessage requestMessage) {
                            mContent.setText("");
                            CommonUtil.hideKeyboard(NewsDetailsActivity.this,mContent);
                            if(requestMessage.getCode() == 200){
                                if(replyNewsCommentListener != null){
                                    int size = replyNewsCommentListener.OnReplyNewsCommentListener();
                                    mCommentInfoTab.getTabAt(1).setText("回复" + size);
                                    mNewsCommentNumTextview.setText(String.valueOf(size));
                                }
                                Toast.makeText(NewsDetailsActivity.this, "发表成功！", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }else{
//            new AlertDialog.Builder(this)
//                    .setTitle("hhhh")
//                    .setMessage("wejb ewjlfweplgew").show();
            Toast.makeText(this, "评论内容不能为空！", Toast.LENGTH_SHORT).show();
        }


    }

    /**
     * 加载新闻详情
     */
    private void _initNewsDetails(){
        Intent intent = getIntent();
        if(intent != null){
            String cover = intent.getStringExtra(SmContext.NEWS_EXTRA_COVER);
            String title = intent.getStringExtra(SmContext.NEWS_EXTRA_TITLE);
            String content = intent.getStringExtra(SmContext.NEWS_EXTRA_CONTENT);
            String type = intent.getStringExtra(SmContext.NEWS_EXTRA_TYPE);
            int readNum = intent.getIntExtra(SmContext.NEWS_EXTRA_READ_NUM,-1);
            int commentNum = intent.getIntExtra(SmContext.NEW_EXTRA_COMMENT_NUM,-1);
            int id = intent.getIntExtra(SmContext.NEWS_EXTRA_ID, -1);
            int likeNum = intent.getIntExtra(SmContext.NEWS_EXTRA_LIKE_NUM,-1);

            mNewsCommentNumTextview.setText(String.valueOf(commentNum));
            mNewsLikeNumTextView.setText(String.valueOf(likeNum));

            this.newsID = id;
            this.commentNum = commentNum;

            mCommentInfoTab.addTab(mCommentInfoTab.newTab().setText("点赞" + likeNum));
            mCommentInfoTab.addTab(mCommentInfoTab.newTab().setText( "回复" + commentNum));

            String updateTime = intent.getStringExtra(SmContext.NEWS_EXTRA_UPDATE_TIME);
            Glide.with(this).load(cover).into(mCoverImageView);
            mContentTextView.setText(Html.fromHtml(content,Html.FROM_HTML_SEPARATOR_LINE_BREAK_DIV));
            //mNewsTypeTextView.setText("新闻类型：" + type);
            mNewsReadNumTextView.setText(getString(R.string.news_read_num_format,readNum));
            mNewsUpdateTimeTextView.setText(getString(R.string.news_update_format,updateTime));
            //mNewsIdTextView.setText("ID:" + id);
            mTitleTextView.setText(title);
        }
    }

    public interface replyNewsCommentListener{
        int OnReplyNewsCommentListener();
    }

    private replyNewsCommentListener replyNewsCommentListener;

    public void setReplyNewsCommentListener(NewsDetailsActivity.replyNewsCommentListener replyNewsCommentListener) {
        this.replyNewsCommentListener = replyNewsCommentListener;
    }
}