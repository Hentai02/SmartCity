package com.example.smartcity04.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.smartcity04.R;
import com.example.smartcity04.pojo.reqdata.RequestMessage;
import com.example.smartcity04.pojo.stopwhere.CarMileage;
import com.example.smartcity04.util.CommonUtil;
import com.example.smartcity04.util.RefreshDataListener;
import com.example.smartcity04.viewmodels.StopWhereViewModel;

public class AddCarMileageDialog extends DialogFragment implements DialogInterface.OnClickListener{
    private static final String LOG_TAG = AddCarMileageDialog.class.getSimpleName();
    private View view;

    private EditText mAmount;
    private EditText mGasFilling;
    private EditText mPlateNo;
    private EditText mTravelDate;
    private EditText mTravelDistance;
    private StopWhereViewModel stopWhereViewModel;
    private CarMileage carMileage;

    public void setCarMileage(CarMileage carMileage) {
        this.carMileage = carMileage;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        view = LayoutInflater.from(getContext()).inflate(R.layout.dialog_add_car_mileage, null);
        mAmount = view.findViewById(R.id.et_carMileage_amount);
        mGasFilling = view.findViewById(R.id.et_carMileage_gasFilling);
        mPlateNo = view.findViewById(R.id.et_carMileage_plateNo);
        mTravelDate = view.findViewById(R.id.et_carMileage_travelDate);
        mTravelDistance = view.findViewById(R.id.et_carMileage_travelDistance);
        stopWhereViewModel = new ViewModelProvider(requireActivity()).get(StopWhereViewModel.class);

        if(carMileage != null){
            mAmount.setText(String.valueOf(carMileage.getAmount()));
            mGasFilling.setText(String.valueOf(carMileage.getGasFilling()));
            mPlateNo.setText(carMileage.getPlateNo());
            mTravelDate.setText(carMileage.getTravelDate());
            mTravelDistance.setText(String.valueOf(carMileage.getTravelDistance()));
        }

    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setView(view);
        builder.setTitle("添加车辆里程信息");
        builder.setNegativeButton("确认",this);
        builder.setPositiveButton("取消",this);
        return builder.create();
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        switch (which){
            case Dialog.BUTTON_NEGATIVE:
                int amount = Integer.parseInt(mAmount.getText().toString());
                int gasFilling = Integer.parseInt(mGasFilling.getText().toString());
                String plateNo = mPlateNo.getText().toString();
                String travelDate = mTravelDate.getText().toString();
                int travelDistance = Integer.parseInt(mTravelDistance.getText().toString());
                CarMileage carMileage = new CarMileage(plateNo,travelDate,travelDistance,gasFilling,amount);
                if (carMileage != null){
                    modifyMileage();
                }else{
                    addCarMileage(carMileage);
                }

                break;
            case Dialog.BUTTON_POSITIVE:
                Log.d(LOG_TAG,"test2");
                break;
        }

    }

    private void addCarMileage(CarMileage carMileage){
        stopWhereViewModel.addCarMileage(CommonUtil.getToken(requireContext()),carMileage).observe(requireActivity(), new Observer<RequestMessage>() {
            @Override
            public void onChanged(RequestMessage requestMessage) {
                if(requestMessage.getCode() == 200){
                    if (refreshDataListener != null){
                        refreshDataListener.refresh();
                    }
                }
            }
        });
    }

    private void modifyMileage(){
        int amount = Integer.parseInt(mAmount.getText().toString());
        int gasFilling = Integer.parseInt(mGasFilling.getText().toString());
        String plateNo = mPlateNo.getText().toString();
        String travelDate = mTravelDate.getText().toString();
        int travelDistance = Integer.parseInt(mTravelDistance.getText().toString());
        CarMileage carMileage = new CarMileage(plateNo,travelDate,travelDistance,gasFilling,amount);
        carMileage.setId(this.carMileage.getId());
        stopWhereViewModel.modifyCarMileage(CommonUtil.getToken(requireContext()),carMileage).observe(requireActivity(), new Observer<RequestMessage>() {
            @Override
            public void onChanged(RequestMessage requestMessage) {
                if(requestMessage.getCode() == 200){
                    if (refreshDataListener != null){
                        refreshDataListener.refresh();
                    }
                }
            }
        });
    }

    private RefreshDataListener refreshDataListener;

    public void setRefreshDataListener(RefreshDataListener refreshDataListener) {
        this.refreshDataListener = refreshDataListener;
    }
}
