package com.example.smartcity04.dialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.smartcity04.R;
import com.example.smartcity04.network.StopWhereApi;
import com.example.smartcity04.pojo.reqdata.RequestData;
import com.example.smartcity04.util.CommonUtil;
import com.example.smartcity04.viewmodels.StopWhereViewModel;
import com.google.gson.JsonObject;

public class MoveCarDialog extends DialogFragment
        implements DialogInterface.OnClickListener{

    private static final String LOG_TAG = MoveCarDialog.class.getSimpleName();
    private View view;

    private ImageView mPhoto;
    private EditText mProvince;
    private EditText mCity;
    private EditText mArea;
    private EditText mAddress;
    private EditText mPlateNo;
    private EditText mIdCard;
    private EditText mTel;

    private StopWhereViewModel stopWhereViewModel;
    private String token;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        token = CommonUtil.getToken(requireContext());
        view = LayoutInflater.from(requireContext()).inflate(R.layout.dialog_move_car,null);
        mPhoto = view.findViewById(R.id.iv_move_car_photo);
        mProvince = view.findViewById(R.id.et_move_car_province);
        mCity = view.findViewById(R.id.et_move_car_city);
        mArea = view.findViewById(R.id.et_move_car_area);
        mAddress = view.findViewById(R.id.et_move_car_address);
        mPlateNo = view.findViewById(R.id.et_move_car_plateNo);
        mIdCard = view.findViewById(R.id.et_move_car_idCard);
        mTel = view.findViewById(R.id.et_move_car_tel);

        stopWhereViewModel = new ViewModelProvider(requireActivity()).get(StopWhereViewModel.class);


    }


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setView(view)
                .setTitle("挪车")
                .setNegativeButton("确认",this)
                .setPositiveButton("取消",this);
        return builder.create();
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        switch (which){
            case Dialog.BUTTON_NEGATIVE:
                Log.d(LOG_TAG,"BUTTON_NEGATIVE");
                moveCar();
                break;
            case Dialog.BUTTON_POSITIVE:
                Log.d(LOG_TAG,"BUTTON_POSITIVE");
                dismiss();
                break;
        }
    }

    private void moveCar(){
        JsonObject jsonObject = new JsonObject();
        //String photo = mPhoto.getTag().toString();
        String province = mProvince.getText().toString();
        String city = mCity.getText().toString();
        String area = mArea.getText().toString();
        String address = mAddress.getText().toString();
        String plateNo = mPlateNo.getText().toString();
        String idCard = mIdCard.getText().toString();
        String tel = mTel.getText().toString();

        jsonObject.addProperty("photo","");
        jsonObject.addProperty("province",province);
        jsonObject.addProperty("city",city);
        jsonObject.addProperty("area",area);
        jsonObject.addProperty("address",address);
        jsonObject.addProperty("plateNo",plateNo);
        jsonObject.addProperty("idCard",idCard);
        jsonObject.addProperty("tel",tel);

        stopWhereViewModel.moveCar(token,jsonObject)
                .observe(requireActivity(), jsonObjectRequestData -> {
                    int code = jsonObjectRequestData.get("code").getAsInt();
                    if(code == 200){
                        Toast.makeText(view.getContext(), jsonObjectRequestData.get("msg").getAsString(), Toast.LENGTH_SHORT).show();
                    }
        });


    }
}
