package com.example.smartcity04.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.smartcity04.R;
import com.example.smartcity04.pojo.NewsInfo;
import com.example.smartcity04.viewmodels.BasicViewModel;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.LongFunction;

public class TestFragment3 extends Fragment {

    private static final String LOG_TAG = TestFragment3.class.getSimpleName();
    private BasicViewModel basicViewModel;
    private BarChart barChart;
    private TextView textView;


    public TestFragment3() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        basicViewModel = new ViewModelProvider(requireActivity()).get(BasicViewModel.class);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_test3, container, false);
        barChart = view.findViewById(R.id.bc_test);
        textView = view.findViewById(R.id.textView);
        initBarChat();

        return view;
    }

    /**
     * 初始化柱状图(BarChart)
     */
    private void initBarChat(){
        barChart.getDescription().setEnabled(false);
        barChart.setDrawBarShadow(false);
        barChart.setDrawValueAboveBar(true);
        XAxis xAxis = barChart.getXAxis();// X轴
        YAxis axisLeft = barChart.getAxisLeft();// 左Y轴
        YAxis axisRight = barChart.getAxisRight();// 右Y轴
        axisRight.setEnabled(false);

        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);// 设置 x 标签在底部显示
        xAxis.setAxisLineWidth(1);// 以 dp 为单位设置图表周围边框的宽度
        xAxis.setAxisMinimum(0);// 为X轴设置自定义最小值
        xAxis.setDrawAxisLine(true);// 绘制沿轴的线
        xAxis.setDrawGridLines(false);// 不绘制网格线

        axisLeft.setAxisLineWidth(1);
        axisLeft.setAxisMinimum(0);// 为Y轴设置自定义最小值
        axisLeft.setDrawAxisLine(true);// 绘制沿轴的线
        axisLeft.setDrawGridLines(false);// 不绘制网格线


        setBarData();

    }

    private void setBarData(){
        List<BarEntry> entries = new ArrayList<>();
        basicViewModel.getNewsInfoList("").observe(requireActivity(),newsInfoList -> {

            for (int i=0;i<5;i++){
                if (entries.size()==5)break;
                entries.add(new BarEntry(newsInfoList.get(i).getId(),newsInfoList.get(i).getLikeNum()));
                Log.d(LOG_TAG,"数据：" + i);
                textView.setText(getString(R.string.data_analysis_format
                        ,newsInfoList.get(0).getId(),newsInfoList.get(0).getTitle()
                        ,newsInfoList.get(1).getId(),newsInfoList.get(1).getTitle()
                        ,newsInfoList.get(2).getId(),newsInfoList.get(2).getTitle()
                        ,newsInfoList.get(3).getId(),newsInfoList.get(3).getTitle()
                        ,newsInfoList.get(4).getId(),newsInfoList.get(4).getTitle()));

            }
            BarDataSet barDataSet = new BarDataSet(entries,"新闻ID");
            barChart.getAxisLeft().setAxisMinimum(newsInfoList.get(0).getId());
            barChart.getXAxis().setAxisMinimum(newsInfoList.get(0).getId());
            Log.d(LOG_TAG,"ID:" + newsInfoList.get(0).getId());
            barChart.setData(new BarData(barDataSet));
            barChart.invalidate();
            Log.d(LOG_TAG,"entries:" + entries.size());
        });


        // 使整个视图无效。 如果视图可见， onDraw(Canvas) 将在未来的某个时间点被调用。
        // 这必须从 UI 线程调用。 要从非 UI 线程调用，请调用 postInvalidate()。
        //barChart.invalidate();
    }
}