package com.example.smartcity04.util;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.smartcity04.R;
import com.example.smartcity04.dialog.FireMissilesDialogFragment;

import java.util.List;

public class CommonUtil {

    public static void displayMessage(Context context,String msg){
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    public static String getToken(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences(SmContext.SHARE_FILE_USER_TOKEN,Context.MODE_PRIVATE);
        String token = sharedPreferences.getString("token", "");
        if(token.length()>0){
            return token;
        }else{
//            FireMissilesDialogFragment dialogFragment = new FireMissilesDialogFragment();
//            dialogFragment.show(null, "");
            displayMessage(context,"登录过期！请重新登录。");
            return null;
        }

    }

    public static RequestOptions roundImg(){
        RequestOptions options = new RequestOptions().centerCrop().transform(new RoundedCorners(90)); options = new RequestOptions().centerCrop().transform(new RoundedCorners(90));
        return options;
    }

    /**
     * 顶部工具栏
     * @param activity
     * @param title
     */
    public static void topBar(Activity activity, String title){
        activity.findViewById(R.id.iv_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.finish();
            }
        });

        TextView titleText = activity.findViewById(R.id.tv_title);
        if(title.length()>0){
            titleText.setText(title);
        }else{
            titleText.setText("");
        }

    }


    public static void topBarRecharge(Activity activity, String title, DialogFragment dialog){
        activity.findViewById(R.id.iv_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.finish();
            }
        });

        TextView titleText = activity.findViewById(R.id.tv_title);
        if(title.length()>0){
            titleText.setText(title);
        }else{
            titleText.setText("");
        }

        dialog.show(dialog.getChildFragmentManager(), "");


    }

    public static String getResString(Context context,int id){
        return context.getString(id);
    }

    public static void hideKeyboard(Context context,View view){
        InputMethodManager methodManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        if(methodManager != null){
            methodManager.hideSoftInputFromWindow(view.getWindowToken(),InputMethodManager.HIDE_NOT_ALWAYS);
            view.clearFocus();
        }
    }


    public static PopupMenu onPopupMenu(View view,Context context,int resId){
        PopupMenu popupMenu = new PopupMenu(context,view);
        MenuInflater menuInflater = new MenuInflater(context);
        menuInflater.inflate(resId,popupMenu.getMenu());
        return popupMenu;
    }

}
