package com.example.smartcity04.dialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.smartcity04.R;
import com.example.smartcity04.viewmodels.StopWhereViewModel;

public class StopWhereRechargeFragment extends DialogFragment {

    private static final String LOG_TAG = StopWhereRechargeFragment.class.getSimpleName();

    private StopWhereViewModel stopWhereViewModel;
    private EditText mFilter;
    private Spinner spinner;
    private String filterCategory;

    private View view;


    public StopWhereRechargeFragment() {}


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        stopWhereViewModel = new ViewModelProvider(requireActivity()).get(StopWhereViewModel.class);
        view = LayoutInflater.from(getContext()).inflate(R.layout.dialog_stop_car_record_filter,null);
        ((TextView)view.findViewById(R.id.textView_placeholder5)).setText("支付方式");

        mFilter = view.findViewById(R.id.et_stop_car_filter_text);
        spinner = view.findViewById(R.id.spinner_stop_car_filter);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.spinner_payType, R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        final String[] filterArray = new String[1];
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
           @Override
           public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
               filterArray[0] = parent.getItemAtPosition(position).toString();
               filterCategory = filterArray[0];
           }

           @Override
           public void onNothingSelected(AdapterView<?> parent) {

           }
       });
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {




        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("充值")
                .setView(view)
                .setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        String money = mFilter.getText().toString();
                        if(refreshListener != null){
                            refreshListener.onRefresh(filterCategory,Integer.valueOf(money));
                        }
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                    }
                });
        return builder.create();
    }

    public interface RefreshListener{
        void onRefresh(String payType,Integer money);
    }

    private RefreshListener refreshListener;

    public void setRefreshListener(RefreshListener refreshListener) {
        this.refreshListener = refreshListener;
    }


}
