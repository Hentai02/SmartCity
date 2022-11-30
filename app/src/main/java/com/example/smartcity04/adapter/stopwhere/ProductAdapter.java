package com.example.smartcity04.adapter.stopwhere;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartcity04.R;
import com.example.smartcity04.dialog.ConsumeDialog;
import com.example.smartcity04.pojo.reqdata.RequestMessage;
import com.example.smartcity04.pojo.stopwhere.Product;
import com.example.smartcity04.util.CommonUtil;
import com.example.smartcity04.viewmodels.StopWhereViewModel;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {
    private static final String LOG_TAG = ProductAdapter.class.getSimpleName();
    private final List<Product> data;
    private final Context mContext;

    public ProductAdapter(List<Product> data, Context context){
        this.data = data;
        this.mContext = context;
    }

    @NonNull
    @Override
    public ProductAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_sw_product,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductAdapter.ViewHolder holder, int position) {
        Product e = data.get(position);
        holder.mNameTv.setText(e.getName());
        holder.mTotalTv.setText(mContext.getString(R.string.product_total_format,e.getTotal()));
        holder.mSaleQuantityTv.setText(mContext.getString(R.string.product_saleQuantity_format,e.getSaleQuantity()));
        holder.mPriceTv.setText(mContext.getString(R.string.product_price_format,e.getPrice()));
        holder.mScoreTv.setText(mContext.getString(R.string.product_score_format,e.getScore()));
        Log.d(LOG_TAG,e.getName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                builder.setTitle("购买")
                        .setMessage("是否购买加油卡？")
                        .setPositiveButton("确认", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                if (buyConsumeListener != null){
                                    buyConsumeListener.onBuyConsumeListener(e.getId());
                                }
                            }
                        })
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // User cancelled the dialog
                            }
                        }).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public final TextView mNameTv;
        public final TextView mTotalTv;
        public final TextView mSaleQuantityTv;
        public final TextView mPriceTv;
        public final TextView mScoreTv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            mNameTv = itemView.findViewById(R.id.tv_product_name);
            mTotalTv = itemView.findViewById(R.id.tv_product_total);
            mSaleQuantityTv = itemView.findViewById(R.id.tv_product_saleQuantity);
            mPriceTv = itemView.findViewById(R.id.tv_product_price);
            mScoreTv = itemView.findViewById(R.id.tv_product_score);
        }
    }

    public interface BuyConsumeListener{
        void onBuyConsumeListener(Integer productId);
    }

    private BuyConsumeListener buyConsumeListener;

    public void setBuyConsumeListener(BuyConsumeListener buyConsumeListener) {
        this.buyConsumeListener = buyConsumeListener;
    }
}
