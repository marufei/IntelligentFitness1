package com.health.demo.intelligentfitness.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.health.demo.intelligentfitness.MyApplication;
import com.health.demo.intelligentfitness.R;
import com.health.demo.intelligentfitness.WebActivity;
import com.health.demo.intelligentfitness.adapter.LvHealthAdapter;
import com.health.demo.intelligentfitness.api.ApiAddress;
import com.health.demo.intelligentfitness.bean.HomeFragmentListBean;
import com.health.demo.intelligentfitness.util.MyUtils;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {


    @InjectView(R.id.iv_health_header)
    ImageView ivHealthHeader;
    @InjectView(R.id.lv_health_show)
    ListView lvHealthShow;
    private HomeFragmentListBean homeFragmentListBean;
    private LvHealthAdapter adapter;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        initData();
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.inject(this, view);
        setListener();
        return view;
    }

    /**
     * 点击事件
     */
    private void setListener() {
        lvHealthShow.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent=new Intent(getActivity(), WebActivity.class);
                intent.putExtra("url",homeFragmentListBean.getData().get(i).getContent());
                startActivity(intent);
            }
        });
    }

    /**
     * 获取首页列表信息
     */
    private void initData() {
        String url = ApiAddress.getURL(ApiAddress.NEWS_LIST);
        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    Gson gson = new Gson();
                    homeFragmentListBean = gson.fromJson(response, HomeFragmentListBean.class);
                    List<HomeFragmentListBean.DataBean> list = homeFragmentListBean.getData();
                    adapter = new LvHealthAdapter(getActivity(), list);
                    lvHealthShow.setAdapter(adapter);
                } catch (Exception e) {

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                MyUtils.showToast(getActivity(), "网络有问题");
            }
        });
        MyApplication.getRequestQueue().add(stringRequest);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
