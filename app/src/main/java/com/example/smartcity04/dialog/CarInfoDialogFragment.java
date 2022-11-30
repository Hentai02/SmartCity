package com.example.smartcity04.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.smartcity04.R;
import com.example.smartcity04.adapter.stopwhere.MyCarAdapter;
import com.example.smartcity04.pojo.stopwhere.Car;
import com.example.smartcity04.util.CommonUtil;
import com.example.smartcity04.util.RefreshDataListener;
import com.example.smartcity04.viewmodels.StopWhereViewModel;

public class CarInfoDialogFragment extends DialogFragment implements DialogInterface.OnClickListener{

    private static final String LOG_TAG = CarInfoDialogFragment.class.getSimpleName();
    private View view;

    private final Car car;
    private TextView mPlateNo;
    private TextView mCarType;

    public CarInfoDialogFragment(Car car){
        this.car = car;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        view = LayoutInflater.from(requireContext()).inflate(R.layout.dialog_sw_carinfo,null);
        mPlateNo = view.findViewById(R.id.tv_car_plateNo);
        mCarType = view.findViewById(R.id.tv_car_type);
        mPlateNo.setText(car.getPlateNo());
        mCarType.setText(car.getType());
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setView(view)
                .setTitle("编辑车辆信息")
                .setPositiveButton("确认",this)
                .setNegativeButton("取消",this);
        return builder.create();

    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        switch (which){
            case Dialog.BUTTON_POSITIVE:
                String plateNo = mPlateNo.getText().toString();
                String carType = mCarType.getText().toString();
                car.setPlateNo(plateNo);
                car.setType(carType);
                StopWhereViewModel stopWhereViewModel = new ViewModelProvider(requireActivity()).get(StopWhereViewModel.class);
                stopWhereViewModel.modifyCar(CommonUtil.getToken(requireContext()),car).observe(requireActivity(),requestMessage -> {
                    if(refreshDataListener != null){
                        refreshDataListener.refresh();
                    }
                });
                break;
            case Dialog.BUTTON_NEGATIVE:
                Log.d(LOG_TAG,"点击了取消按钮");
                break;
        }
    }

    private RefreshDataListener refreshDataListener;

    public void setRefreshDataListener(RefreshDataListener refreshDataListener) {
        this.refreshDataListener = refreshDataListener;
    }
}
