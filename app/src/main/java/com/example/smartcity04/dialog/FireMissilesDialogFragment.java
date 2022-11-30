package com.example.smartcity04.dialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

public class FireMissilesDialogFragment extends DialogFragment implements DialogInterface.OnClickListener {

    private static final String LOG_TAG = FireMissilesDialogFragment.class.getSimpleName();

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("登录注册解锁更多内容")
                .setTitle("登录")
                .setPositiveButton("确认",this)
                .setNegativeButton("取消", this);
        return builder.create();
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        switch (which){
            case Dialog.BUTTON_POSITIVE:
                Log.d(LOG_TAG,"点击了确认按钮");
                break;
            case Dialog.BUTTON_NEGATIVE:
                Log.d(LOG_TAG,"点击了取消按钮");
                break;
        }
    }
}
