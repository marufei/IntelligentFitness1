package com.health.demo.intelligentfitness.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.health.demo.intelligentfitness.R;
import com.health.demo.intelligentfitness.adapter.LvEvaluateAdapter;
import com.health.demo.intelligentfitness.util.MyUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * A simple {@link Fragment} subclass.
 */
public class EvaluateFragment extends Fragment {
    private List<Map<String, String>> listMap = new ArrayList<>();
    private LvEvaluateAdapter adapter;
    private ListView lv_evaluate_show;

    public EvaluateFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_evaluate, container, false);
        lv_evaluate_show=(ListView)view.findViewById(R.id.lv_evaluate_show);

        Map<String, String> map = new HashMap<>();
        map.put("real", "2000");
        map.put("plan", "5000");
        Map<String, String> map1 = new HashMap<>();
        map1.put("real", "1320");
        map1.put("plan", "3000");
        Map<String, String> map2 = new HashMap<>();
        map2.put("real", "3500");
        map2.put("plan", "7000");
        Map<String, String> map3 = new HashMap<>();
        map3.put("real", "4860");
        map3.put("plan", "3000");
        listMap.add(map);
        listMap.add(map1);
        listMap.add(map2);
        listMap.add(map3);
        for(int i=0;i<listMap.size();i++){
            MyUtils.Loge("bbb","real:"+listMap.get(i).get("real"));
            MyUtils.Loge("bbb","plan:"+listMap.get(i).get("plan"));
        }
        adapter = new LvEvaluateAdapter(getActivity(), listMap);
        lv_evaluate_show.setAdapter(adapter);
        return view;
    }
}
