package com.example.smartcity04.dialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
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
import com.example.smartcity04.viewmodels.StopWhereViewModel;
import com.google.gson.JsonObject;

public class MyCarAddDialogFragment extends DialogFragment
        implements DialogInterface.OnClickListener{
    private static final String LOG_TAG = MyCarAddDialogFragment.class.getSimpleName();

    private StopWhereViewModel stopWhereViewModel;
    private View view;
    private EditText mCarPlateNoTv;
    private EditText mCarTYpe;
    public MyCarAddDialogFragment() {}


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        stopWhereViewModel = new ViewModelProvider(requireActivity()).get(StopWhereViewModel.class);
        view = LayoutInflater.from(getContext()).inflate(R.layout.dialog_my_car_add,null);

        mCarPlateNoTv = view.findViewById(R.id.et_car_plateNo);
        mCarTYpe = view.findViewById(R.id.et_car_type);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("车辆入库")
                .setView(view)
                .setPositiveButton("确认", this)
                .setNegativeButton("取消",this);
        return builder.create();
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        switch (which){
            case Dialog.BUTTON_POSITIVE:
                addCar();
                break;
            case Dialog.BUTTON_NEGATIVE:
                Log.d(LOG_TAG,"点击了取消按钮");
                break;
        }
    }

    private void addCar(){
        String carPlateNo = mCarPlateNoTv.getText().toString();
        String carType = mCarTYpe.getText().toString();
        if(carPlateNo != null && carType != null){
            JsonObject car = new JsonObject();
            car.addProperty("plateNo",carPlateNo);
            car.addProperty("type",carType);
            stopWhereViewModel.addCar(CommonUtil.getToken(requireActivity()),car)
                    .observe(requireActivity(), requestMessage -> {
                        if(refreshListener != null){
                            refreshListener.onRefresh();
                        }
                        //Toast.makeText(requireContext(), requestMessage.getMsg(), Toast.LENGTH_SHORT).show();
                    });
        }else{
            Toast.makeText(requireActivity(), "故意找茬是吧。。。", Toast.LENGTH_SHORT).show();
        }
    }



    public interface RefreshListener{
        void onRefresh();
    }

    private RefreshListener refreshListener;

    public void setRefreshListener(RefreshListener refreshListener) {
        this.refreshListener = refreshListener;
    }


}
