package com.example.smartcity04.adapter.stopwhere;

import android.content.Context;
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
import com.example.smartcity04.pojo.stopwhere.CarMileage;
import com.example.smartcity04.util.CommonUtil;

import java.util.List;

public class CarMileageAdapter extends RecyclerView.Adapter<CarMileageAdapter.ViewHolder> {

    private Context mContext;
    private List<CarMileage> data;

    public CarMileageAdapter(Context context,List<CarMileage> data){
        this.mContext = context;
        this.data = data;
    }

    public void setData(List<CarMileage> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_car_mileage,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CarMileage e = data.get(position);
        holder.plateNoTv.setText(e.getPlateNo());
        holder.travelDateTv.setText(mContext.getString(R.string.car_mileage_travelDate_format,e.getTravelDate()));
        holder.travelDistanceTv.setText(mContext.getString(R.string.car_mileage_travelDistance_format,e.getTravelDistance()));
        holder.gasFilling.setText(mContext.getString(R.string.car_mileage_gasFilling,e.getGasFilling()));
        holder.amountTv.setText(mContext.getString(R.string.car_mileage_amount_format,e.getAmount()));

        holder.itemView.setOnLongClickListener(v -> {
            onPopupMenu(v,e);
            return true;
        });

    }

    @Override
    public int getItemCount() {
        if (data!=null){
            return data.size();
        }
        return 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private final TextView plateNoTv;
        private final TextView travelDateTv;
        private final TextView travelDistanceTv;
        private final TextView gasFilling;
        private final TextView amountTv;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            plateNoTv = itemView.findViewById(R.id.tv_carMileage_plateNo);
            travelDateTv = itemView.findViewById(R.id.tv_carMileage_travelDate);
            travelDistanceTv = itemView.findViewById(R.id.tv_carMileage_travelDistance);
            amountTv = itemView.findViewById(R.id.tv_carMileage_amount);
            gasFilling = itemView.findViewById(R.id.tv_carMileage_gasFilling);
        }
    }

    private void onPopupMenu(View view,CarMileage carMileage){
        PopupMenu popupMenu = CommonUtil.onPopupMenu(view, mContext, R.menu.car_mileage_moe2);
        popupMenu.show();
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (onItemSelectedListener != null){
                    onItemSelectedListener.onItemSelectedListener(item,carMileage);
                    return true;
                }
                return false;
            }
        });
    }

    public interface OnItemSelectedListener{
        void onItemSelectedListener(MenuItem item, CarMileage carMileage);
    }

    private OnItemSelectedListener onItemSelectedListener;

    public void setOnItemSelectedListener(OnItemSelectedListener onItemSelectedListener) {
        this.onItemSelectedListener = onItemSelectedListener;
    }
}
