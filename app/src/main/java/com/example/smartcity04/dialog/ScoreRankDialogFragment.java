package com.example.smartcity04.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
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
import com.example.smartcity04.util.SmContext;
import com.example.smartcity04.viewmodels.StopWhereViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * 积分排行榜
 */
public class ScoreRankDialogFragment extends DialogFragment
        implements DialogInterface.OnClickListener{

    private static final String LOG_TAG = ScoreRankDialogFragment.class.getSimpleName();
    private View view;
    private TextView tipsTv;
    private TextView myRankTv;
    private RecyclerView scoreRankList;
    private SharedPreferences sharedPreferences;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferences = requireActivity().getSharedPreferences(SmContext.SHARE_FILE_USER_TOKEN, Context.MODE_PRIVATE);
        view = LayoutInflater.from(getContext()).inflate(R.layout.dialog_score_rank,null);

        scoreRankList = view.findViewById(R.id.rv_score_rank_list);
        scoreRankList.setLayoutManager(new LinearLayoutManager(getContext()));
        tipsTv = view.findViewById(R.id.tv_tips3);
        myRankTv = view.findViewById(R.id.tv_my_rank);
        StopWhereViewModel stopWhereViewModel = new ViewModelProvider(requireActivity()).get(StopWhereViewModel.class);
        stopWhereViewModel.getScoreRank(CommonUtil.getToken(requireActivity())).observe(requireActivity(),this::loadScoreRank);

    }


    public void loadScoreRank(List<ScoreRank> scoreRanks){
        String username = sharedPreferences.getString("username","");
        for (int i=0;i<scoreRanks.size();i++){
            if(scoreRanks.get(i).getUserName().equals(username)){
                myRankTv.setText(requireContext().getString(R.string.rank_my_rank,(i + 1)));
                break;
            }
        }
        List<ScoreRank> result = new ArrayList<>();
        for (int i=0;i<50;i++){
            result.add(scoreRanks.get(i));
            //Log.d(LOG_TAG,"用户名：" + scoreRanks.get(i).getUserName());
        }
        scoreRankList.setAdapter(new ScoreRankAdapter(result,getContext()));
        tipsTv.setText(requireActivity().getString(R.string.rank_tips_format, scoreRanks.size()));
    }


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("排行榜")
                .setView(view)
                .setNegativeButton("返回",this);
        return builder.create();
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        dismiss();
    }
}
