package com.example.smartcity04.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.smartcity04.pojo.User;
import com.example.smartcity04.pojo.reqdata.RequestToken;
import com.example.smartcity04.ui.LoginActivity;
import com.example.smartcity04.R;
import com.example.smartcity04.ui.UserInfoActivity;
import com.example.smartcity04.ui.WalletBillActivity;
import com.example.smartcity04.adapter.UserServicesItemAdapter;
import com.example.smartcity04.adapter.UserSettingsAdapter;
import com.example.smartcity04.pojo.IconAndTitleItem;
import com.example.smartcity04.pojo.UserInfo;
import com.example.smartcity04.ui.stopwhere.ScoreBillActivity;
import com.example.smartcity04.util.CommonUtil;
import com.example.smartcity04.util.SmContext;
import com.example.smartcity04.viewmodels.BasicViewModel;

import java.util.ArrayList;
import java.util.List;

public class UserHomeFragment extends Fragment {
    private static final String LOG_TAG = UserHomeFragment.class.getSimpleName();
    private SharedPreferences mPreferences;

    private CardView mLoginCard;
    private CardView mUserInfoCard;
    private RecyclerView mUserServices;
    private BasicViewModel basicViewModel;
    private RecyclerView mUserSettings;


    // 用户信息
    private ImageView mUserAvatarImageView;
    private TextView mUserNameTextView;// 用户名
    private TextView mUserIdTextView;//用户id
    private TextView mBalanceTextView;// 余额
    private TextView mScoreTextView;// 积分

    private TextView mLogout;

    public UserHomeFragment(){}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPreferences = requireActivity().getSharedPreferences(SmContext.SHARE_FILE_USER_TOKEN, Context.MODE_PRIVATE);
        basicViewModel = new ViewModelProvider(requireActivity()).get(BasicViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_home, container, false);
        mUserServices = view.findViewById(R.id.rv_user_service_list);
        mLoginCard = view.findViewById(R.id.card_login);
        mUserInfoCard = view.findViewById(R.id.card_user_info);
        view.findViewById(R.id.btn_login).setOnClickListener(this::startLoginActivity);
        mBalanceTextView = view.findViewById(R.id.tv_balance);
        mScoreTextView = view.findViewById(R.id.tv_score);
        mUserNameTextView = view.findViewById(R.id.tv_username);
        mUserIdTextView = view.findViewById(R.id.tv_userid);
        mUserAvatarImageView = view.findViewById(R.id.iv_user_avatar);
        mUserSettings = view.findViewById(R.id.rv_user_settings_list);
        mLogout = view.findViewById(R.id.tv_logout);

        mBalanceTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),WalletBillActivity.class);
                startActivity(intent);
            }
        });

        mLogout.setOnClickListener(this::logout);
        mScoreTextView.setOnClickListener(this::scoreInfo);
        view.findViewById(R.id.layout_user_info).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(requireContext(), UserInfoActivity.class);
                requireActivity().startActivity(intent);
                Log.d(LOG_TAG,"个人详情页面。。。");
            }
        });

        initUserServices();
        checkLogin();
        initUserSettings();
        return view;
    }


    private void scoreInfo(View view){
        Intent intent = new Intent(getContext(), ScoreBillActivity.class);
        startActivity(intent);
    }

    private void initUserServices(){
        List<IconAndTitleItem> serviceItemList = new ArrayList<>();
        serviceItemList.add(new IconAndTitleItem(R.drawable.ic_user_item_focuson,"我的关注"));
        serviceItemList.add(new IconAndTitleItem(R.drawable.ic_user_item_focuson,"我的关注"));
        serviceItemList.add(new IconAndTitleItem(R.drawable.ic_user_item_focuson,"我的关注"));
        serviceItemList.add(new IconAndTitleItem(R.drawable.ic_user_item_focuson,"我的关注"));
        mUserServices.setAdapter(new UserServicesItemAdapter(serviceItemList));
    }

    public void startLoginActivity(View view){
        Intent loginIntent = new Intent(getContext(), LoginActivity.class);
        startActivity(loginIntent);
    }

    /**
     * 取得焦点重新检查token是否存在
     */
    @Override
    public void onResume() {
        super.onResume();
        if(mPreferences != null){
            checkLogin();
        }
    }

    /**
     * 检查登录状态
     */
    private void checkLogin(){
        String token = mPreferences.getString("token","");
        basicViewModel.getUserInfo(token).observe(requireActivity(), new Observer<UserInfo>() {
            @Override
            public void onChanged(UserInfo info) {
                if(info == null){
                    mLoginCard.setVisibility(View.VISIBLE);
                    mUserInfoCard.setVisibility(View.GONE);
                    mLogout.setVisibility(View.GONE);

//                    String username = mPreferences.getString("username","");
//                    String password = mPreferences.getString("password","");
//                    User user = new User(username,password);
//                    if(password.length()>0 && username.length()>0){
//                        Log.d(LOG_TAG,user.toString());
//                        basicViewModel.getLoginRequest(user).observe(requireActivity(),UserHomeFragment.this::getNewToken);
//                    }
                }else{
                    mLoginCard.setVisibility(View.GONE);
                    mUserInfoCard.setVisibility(View.VISIBLE);
                    mLogout.setVisibility(View.VISIBLE);
                    initUserInfo(info);
                }
            }
        });
    }

    private void getNewToken(RequestToken<String> token){
        if(token.getCode() == 200) {
            Log.d(LOG_TAG,"NewToken:" + token.getObject());
            mPreferences.edit().putString("token",token.getObject()).apply();
        }
    }

    private void logout(View view){
        mPreferences.edit().clear().apply();
        String token = mPreferences.getString("token", "");
        String username = mPreferences.getString("username", "");
        String password = mPreferences.getString("password", "");
        Log.d(LOG_TAG,"\ntoken:" + token + "\nusername:" + username + "\npassword:" + password);
        checkLogin();
    }

    private void initUserInfo(UserInfo info){
        mUserIdTextView.setText(getString(R.string.uid_format,info.getUserId()));
        mBalanceTextView.setText(getString(R.string.balance_userinfo_format,info.getBalance()));
        mScoreTextView.setText(getString(R.string.score_userinfo_format,info.getScore()));
        if(info.getNickName() != null){
            mUserNameTextView.setText(info.getNickName());
        }else{
            mUserNameTextView.setText(info.getUserName());
        }
        if(info.getAvatar().length()>0){
            RequestOptions options = new RequestOptions().centerCrop().transform(new RoundedCorners(90));
            Glide.with(requireContext()).load("https://i.loli.net/2021/11/09/VfMas3rBoSqThbn.jpg").apply(options).into(mUserAvatarImageView);
        }
    }

    private void initUserSettings(){
        List<IconAndTitleItem> data = new ArrayList<>();
        data.add(new IconAndTitleItem(R.drawable.ic_user_setting_comment,getString(R.string.setting_my_reply)));
        data.add(new IconAndTitleItem(R.drawable.ic_user_setting_backlist,getString(R.string.setting_blacklist_management)));
        data.add(new IconAndTitleItem(R.drawable.ic_user_setting_feedback,getString(R.string.setting_feedback)));
        data.add(new IconAndTitleItem(R.drawable.ic_user_setting_all,getString(R.string.setting_more)));
        data.add(new IconAndTitleItem(R.drawable.ic_user_setting_set,getString(R.string.setting_set)));
        UserSettingsAdapter adapter = new UserSettingsAdapter(data,getContext());
        mUserSettings.setAdapter(adapter);
    }
}