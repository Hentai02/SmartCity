package com.example.smartcity04.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartcity04.R;
import com.example.smartcity04.adapter.stopwhere.ScoreRankAdapter;
import com.example.smartcity04.pojo.stopwhere.ScoreRank;
import com.example.smartcity04.util.CommonUtil;
import com.example.smartcity04.viewmodels.StopWhereViewModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class WalletRankDialogFragment extends DialogFragment {

    private static final String LOG_TAG = WalletRankDialogFragment.class.getSimpleName();
    private View view;
    private int userTotal = 0;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        view = LayoutInflater.from(getContext()).inflate(R.layout.dialog_score_rank,null);
        RecyclerView scoreRankList = view.findViewById(R.id.rv_score_rank_list);
        scoreRankList.setLayoutManager(new LinearLayoutManager(getContext()));
        TextView tipsTv = view.findViewById(R.id.tv_tips3);
        StopWhereViewModel stopWhereViewModel = new ViewModelProvider(requireActivity()).get(StopWhereViewModel.class);
        stopWhereViewModel.getScoreRank(CommonUtil.getToken(getContext())).observe(requireActivity(), new Observer<List<ScoreRank>>() {
            @Override
            public void onChanged(List<ScoreRank> scoreRanks) {
                Collections.sort(scoreRanks);
                List<ScoreRank> result = new ArrayList<>();
                for (int i=0;i<50;i++){
                    result.add(scoreRanks.get(i));
                    //Log.d(LOG_TAG,"用户名：" + scoreRanks.get(i).getUserName());
                }
                scoreRankList.setAdapter(new ScoreRankAdapter(result,getContext()));
                tipsTv.setText(requireActivity().getString(R.string.rank_tips_format,scoreRanks.size()));
            }
        });

    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("排行榜")
                .setView(view)
                .setNegativeButton("返回", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        return builder.create();
    }
}
