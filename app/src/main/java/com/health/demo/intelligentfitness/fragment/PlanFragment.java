package com.health.demo.intelligentfitness.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.health.demo.intelligentfitness.LoginActivity;
import com.health.demo.intelligentfitness.R;
import com.health.demo.intelligentfitness.util.MySharedPrefrencesUtil;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class PlanFragment extends Fragment {
    @InjectView(R.id.tv_exit)
    TextView tvExit;

    public PlanFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_plan, container, false);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @OnClick(R.id.tv_exit)
    public void onViewClicked() {
        MySharedPrefrencesUtil.setParam(getActivity(),"token","");
        startActivity(new Intent(getActivity(), LoginActivity.class));
        getActivity().finish();
    }
}
