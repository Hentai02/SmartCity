package com.example.smartcity04.dialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.smartcity04.pojo.reqdata.RequestMessage;
import com.example.smartcity04.viewmodels.StopWhereViewModel;

public class ConsumeDialog extends DialogFragment implements DialogInterface.OnClickListener {

    private static final String LOG_TAG = ConsumeDialog.class.getSimpleName();
    private final String token;
    private final Integer productId;
    private StopWhereViewModel stopWhereViewModel;

    public ConsumeDialog(String token, Integer productId) {
        this.token = token;
        this.productId = productId;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        stopWhereViewModel = new ViewModelProvider(requireActivity()).get(StopWhereViewModel.class);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity());
        builder.setTitle("购买")
                .setMessage("是否购买加油卡？")
                .setPositiveButton("确认", this)
                .setNegativeButton("取消", this);
        return builder.create();
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        switch (which) {
            case Dialog.BUTTON_POSITIVE:
                stopWhereViewModel.consume(token, productId).observe(requireActivity(), requestMessage -> {
                    Toast.makeText(ConsumeDialog.this.getContext(), requestMessage.getMsg(), Toast.LENGTH_SHORT).show();
                });
                break;
            case DialogInterface.BUTTON_NEGATIVE:
                break;

        }
    }
}
