package com.example.smartcity04.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.smartcity04.R;
import com.example.smartcity04.pojo.User;
import com.example.smartcity04.pojo.reqdata.RequestToken;
import com.example.smartcity04.util.CommonUtil;
import com.example.smartcity04.util.SmContext;
import com.example.smartcity04.viewmodels.BasicViewModel;

public class LoginActivity extends AppCompatActivity {
    private static final String LOG_TAG = LoginActivity.class.getSimpleName();

    private TextView mUserNameText;
    private TextView mPasswordText;
    private Button mLoginButton;
    private BasicViewModel basicViewModel;
    private SharedPreferences mPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        CommonUtil.topBar(this,"登录");
        mPreferences = getSharedPreferences(SmContext.SHARE_FILE_USER_TOKEN,MODE_PRIVATE);
        mUserNameText = findViewById(R.id.et_username);
        mPasswordText = findViewById(R.id.et_password);
        mLoginButton = findViewById(R.id.btn_login);
        CheckBox checkBox = findViewById(R.id.cb_protocol);
        basicViewModel = new ViewModelProvider(this).get(BasicViewModel.class);
        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLoginButton.setEnabled(checkBox.isChecked());
            }
        });

    }

    public void login(View view) {
        // 隐藏软键盘，清除edittext焦点
        hideKeyboard(view);
        mUserNameText.clearFocus();
        mPasswordText.clearFocus();

        User user = new User(mUserNameText.getText().toString().trim()
                ,mPasswordText.getText().toString().trim());

        mPreferences.edit().putString("username",user.getUsername()).apply();
        mPreferences.edit().putString("password",user.getPassword()).apply();

        basicViewModel.getLoginRequest(user).observe(this, new Observer<RequestToken<String>>() {
            @Override
            public void onChanged(RequestToken<String> stringRequestToken) {
                if(stringRequestToken.getCode() == 200) {
                    // 将token保存到共享首选项，并返回
                    String token = stringRequestToken.getObject();
                    mPreferences.edit().putString("token",token).apply();
                    finish();
                }
                CommonUtil.displayMessage(LoginActivity.this,"" + stringRequestToken.getMsg());
            }
        });
    }

    /**
     * 隐藏软键盘
     * @param view
     */
    private void hideKeyboard(View view){
        InputMethodManager methodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        if(methodManager != null){
            methodManager.hideSoftInputFromWindow(view.getWindowToken(),InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }
}