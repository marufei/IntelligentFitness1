package com.health.demo.intelligentfitness.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.health.demo.intelligentfitness.EditHeightActivity;
import com.health.demo.intelligentfitness.EditNameActivity;
import com.health.demo.intelligentfitness.EditPwdActivity;
import com.health.demo.intelligentfitness.EditWeightActivity;
import com.health.demo.intelligentfitness.R;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class PersionFragment extends Fragment {


    @InjectView(R.id.textView2)
    TextView textView2;
    @InjectView(R.id.tv_persion_name)
    TextView tvPersionName;
    @InjectView(R.id.iv_persion_arrow_name)
    ImageView ivPersionArrowName;
    @InjectView(R.id.rl_persion_name)
    RelativeLayout rlPersionName;
    @InjectView(R.id.tv_persion_pwd)
    TextView tvPersionPwd;
    @InjectView(R.id.iv_persion_arrow_pwd)
    ImageView ivPersionArrowPwd;
    @InjectView(R.id.rl_persion_pwd)
    RelativeLayout rlPersionPwd;
    @InjectView(R.id.tv_persion_hight)
    TextView tvPersionHight;
    @InjectView(R.id.iv_persion_arrow_hight)
    ImageView ivPersionArrowHight;
    @InjectView(R.id.rl_persion_height)
    RelativeLayout rlPersionHeight;
    @InjectView(R.id.tv_persion_weight)
    TextView tvPersionWeight;
    @InjectView(R.id.iv_persion_arrow_weight)
    ImageView ivPersionArrowWeight;
    @InjectView(R.id.rl_persion_weight)
    RelativeLayout rlPersionWeight;

    public PersionFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_persion, container, false);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @OnClick({R.id.rl_persion_name, R.id.rl_persion_pwd, R.id.rl_persion_height, R.id.rl_persion_weight})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_persion_name:
                startActivity(new Intent(getActivity(), EditNameActivity.class));
                break;
            case R.id.rl_persion_pwd:
                startActivity(new Intent(getActivity(), EditPwdActivity.class));
                break;
            case R.id.rl_persion_height:
                startActivity(new Intent(getActivity(), EditHeightActivity.class));
                break;
            case R.id.rl_persion_weight:
                startActivity(new Intent(getActivity(), EditWeightActivity.class));
                break;
        }
    }
}
