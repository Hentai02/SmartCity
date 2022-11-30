package com.example.smartcity04.dialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.smartcity04.R;
import com.example.smartcity04.pojo.reqdata.RequestMessage;
import com.example.smartcity04.util.CommonUtil;
import com.example.smartcity04.viewmodels.BasicViewModel;

import java.util.Objects;

public class RechargeFragment extends DialogFragment implements DialogInterface.OnClickListener{

    private View view;
    private BasicViewModel basicViewModel;
    private EditText mMoneyEditText;

    public RechargeFragment() {}


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        view = LayoutInflater.from(getContext()).inflate(R.layout.dialog_recharge,null);
        mMoneyEditText = view.findViewById(R.id.et_money);
        basicViewModel = new ViewModelProvider(requireActivity()).get(BasicViewModel.class);

    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("充值")
                .setView(view)
                .setPositiveButton("支付", this)
                .setNegativeButton("取消",this);
        return builder.create();
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        switch (which){
            case Dialog.BUTTON_POSITIVE:
                pay();
                break;
            case Dialog.BUTTON_NEGATIVE:
                CommonUtil.displayMessage(getContext(),"取消支付！");
                break;
        }
    }

    private void pay(){
        String money = mMoneyEditText.getText().toString();
        basicViewModel.recharge(Objects.requireNonNull(CommonUtil.getToken(getContext()))
                ,Integer.valueOf(money)).observe(requireActivity(), requestMessage -> {
            if (requestMessage.getCode() == 200){
                if(refreshListener != null){
                    refreshListener.onRefresh();
                }
            }else{
                Toast.makeText(view.getContext()
                        , requestMessage.getMsg() + "错误码：" + requestMessage.getCode()
                        , Toast.LENGTH_LONG).show();
            }
        });
    }

    public interface RefreshListener{
        void onRefresh();
    }

    private RefreshListener refreshListener;

    public void setRefreshListener(RefreshListener refreshListener) {
        this.refreshListener = refreshListener;
    }
}
