package com.health.demo.intelligentfitness.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

import com.health.demo.intelligentfitness.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * A simple {@link Fragment} subclass.
 */
public class HealthFragment extends Fragment {


    @InjectView(R.id.iv_health_header)
    ImageView ivHealthHeader;
    @InjectView(R.id.lv_health_show)
    ListView lvHealthShow;

    public HealthFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_health, container, false);
        ButterKnife.inject(this, view);
        initData();
        return view;
    }

    private void initData() {
        List<String> listTitle=new ArrayList<>();
        listTitle.add("11111111111");
        listTitle.add("22222222222");
        listTitle.add("33333333333");
        listTitle.add("44444444444");

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
