package com.example.smartcity04.adapter.stopwhere;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartcity04.R;
import com.example.smartcity04.pojo.stopwhere.Car;
import com.example.smartcity04.ui.stopwhere.CarMileageActivity;
import com.example.smartcity04.util.CommonUtil;

import java.util.List;

public class MyCarAdapter extends RecyclerView.Adapter<MyCarAdapter.ViewHolder> {
    private static final String LOG_TAG = MyCarAdapter.class.getSimpleName();
    private List<Car> data;
    private final Context mContext;

    public MyCarAdapter(List<Car> data,Context context){
        this.data = data;
        this.mContext = context;
    }

    @NonNull
    @Override
    public MyCarAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_my_car,parent,false);
        return new ViewHolder(view);
    }

    public void setData(List<Car> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull MyCarAdapter.ViewHolder holder, int position) {
        Car e = data.get(data.size() - position - 1);
        holder.mPlateNoTv.setText(mContext.getString(R.string.car_platNo_format,e.getPlateNo()));
        holder.mCarTypeTv.setText(mContext.getString(R.string.car_type_format,e.getType()));

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                onPopupMenu(v,e);
                return true;
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, CarMileageActivity.class);
                intent.putExtra("plateNo",e.getPlateNo());
                mContext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        if(data != null)
            return data.size();
        return 0;
    }

    public void onPopupMenu(View view,Car car){
        PopupMenu popupMenu =CommonUtil.onPopupMenu(view,mContext,R.menu.mycar_menu_more);
        popupMenu.setOnMenuItemClickListener(item -> {
            if(onItemSelectedListener != null){
                onItemSelectedListener.onItemSelectedListener(item,car);
                return true;
            }
            return false;
        });
        popupMenu.show();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView mPlateNoTv;
        public TextView mCarTypeTv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mPlateNoTv = itemView.findViewById(R.id.tv_car_plateNo);
            mCarTypeTv = itemView.findViewById(R.id.tv_car_type);
        }
    }

    public interface OnItemSelectedListener{
        void onItemSelectedListener(MenuItem item, Car car);
    }

    private OnItemSelectedListener onItemSelectedListener;

    public void setOnItemSelectedListener(OnItemSelectedListener onItemSelectedListener) {
        this.onItemSelectedListener = onItemSelectedListener;
    }
}
