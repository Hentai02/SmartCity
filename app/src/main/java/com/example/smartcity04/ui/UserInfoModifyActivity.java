package com.example.smartcity04.ui;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.smartcity04.R;
import com.example.smartcity04.network.BasicApi;
import com.example.smartcity04.pojo.UserInfo;
import com.example.smartcity04.util.CommonUtil;
import com.example.smartcity04.viewmodels.BasicViewModel;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.File;
import java.util.concurrent.atomic.AtomicReference;

public class UserInfoModifyActivity extends AppCompatActivity {

    private static final String LOG_TAG = UserInfoActivity.class.getSimpleName();
    private ImageView mAvatarIv;
    private EditText mModifyNickName;
    private EditText mModifyPhone;
    private RadioGroup mRadioGroup;
    BasicViewModel basicViewModel;

    RadioButton radioButton;
    RadioButton radioButton2;

    private TextView mUid;
    private TextView mId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info_modify);
        CommonUtil.topBar(this,"修改用户信息");

        mAvatarIv = findViewById(R.id.iv_modify_user_avatars);
        mModifyNickName = findViewById(R.id.et_modify_nickname);
        mModifyPhone = findViewById(R.id.et_modify_phone);
        mRadioGroup = findViewById(R.id.rg_isGender);
        mUid = findViewById(R.id.tv_modify_uid);
        mId = findViewById(R.id.tv_modify_id);
        radioButton = mRadioGroup.findViewById(R.id.radioButton);
        radioButton2 = mRadioGroup.findViewById(R.id.radioButton2);
        basicViewModel = new ViewModelProvider(this).get(BasicViewModel.class);

        initUserInfo();

        mAvatarIv.setOnClickListener(v -> {
            selectImage();
        });

    }

    static final int REQUEST_IMAGE_OPEN = 1;
    public void selectImage(){
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent,REQUEST_IMAGE_OPEN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_OPEN && resultCode == RESULT_OK) {
            Uri uri = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};
            Cursor query = getContentResolver().query(uri, filePathColumn, null, null, null);
            query.moveToFirst();
            int columnIndex = query.getColumnIndex(filePathColumn[0]);
            String imgPath = query.getString(columnIndex);

            basicViewModel.upload(CommonUtil.getToken(this),new File(imgPath)).observe(this,jsonObject -> {
                int code = jsonObject.get("code").getAsInt();
                if(code == 200){
                    String url = jsonObject.get("url").getAsString();
                    mAvatarIv.setTag(url);
                    Glide.with(UserInfoModifyActivity.this).load(url).into(mAvatarIv);
                }
                //{"msg":"操作成功","fileName":"/profile/upload/2021/12/06/01d698b5-acb3-45ec-b2a4-91ccf0e78057.jpg","code":200,"url":"http://124.93.196.45/profile/upload/2021/12/06/01d698b5-acb3-45ec-b2a4-91ccf0e78057.jpg"}
                Log.d(LOG_TAG,jsonObject.toString());
            });

        }
    }

    private void initUserInfo(){
        basicViewModel.getUserInfo(CommonUtil.getToken(this)).observe(this, new Observer<UserInfo>() {
            @Override
            public void onChanged(UserInfo info) {
                mModifyNickName.setText(info.getNickName());
                mModifyPhone.setText(info.getPhonenumber());
                mUid.setText(getString(R.string.modify_uid_format,info.getUserId()));
                mId.setText(getString(R.string.modify_id_format,info.getIdCard()));
                Log.d(LOG_TAG,info.getAvatar());
                Glide.with(UserInfoModifyActivity.this)
                        .load("https://i.loli.net/2021/11/09/VfMas3rBoSqThbn.jpg")
                        .apply(CommonUtil.roundImg())
                        .into(mAvatarIv);
                if(info.getSex().equals("男")){
                    radioButton.setChecked(true);
                }else {
                    radioButton2.setChecked(true);
                }

            }
        });

    }

    public void savaUserInfo(View view) {
        CommonUtil.hideKeyboard(this,view);
        String nickName = mModifyNickName.getText().toString();
        String phone = mModifyPhone.getText().toString();
        AtomicReference<String> sex = new AtomicReference<>("");
        mRadioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            if(checkedId == R.id.radioButton){
                sex.set("0");
            }else{
                sex.set("1");
            }
        });
        JsonObject userInfo = new JsonObject();
        userInfo.addProperty("nickName",nickName);
        userInfo.addProperty("sex",sex.get());
        userInfo.addProperty("phonenumber",phone);
        //userInfo.addProperty("avatar",mAvatarIv.getTag().toString());
        //Log.d(LOG_TAG,mAvatarIv.getTag().toString());
        basicViewModel.modifyUserInfo(CommonUtil.getToken(this)
                ,userInfo).observe(this,requestMessage -> {

            AlertDialog.Builder builder = new AlertDialog.Builder(this)
                    .setTitle("修改成功！")
                    .setNegativeButton("确认", (dialog, which) -> {
                        finish();
                    });
            builder.create().show();

        });

    }
}