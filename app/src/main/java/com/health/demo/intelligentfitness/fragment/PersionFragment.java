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

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.health.demo.intelligentfitness.EditHeightActivity;
import com.health.demo.intelligentfitness.EditNameActivity;
import com.health.demo.intelligentfitness.EditPwdActivity;
import com.health.demo.intelligentfitness.EditWeightActivity;
import com.health.demo.intelligentfitness.LoginActivity;
import com.health.demo.intelligentfitness.MyApplication;
import com.health.demo.intelligentfitness.R;
import com.health.demo.intelligentfitness.api.ApiAddress;
import com.health.demo.intelligentfitness.util.MySharedPrefrencesUtil;
import com.health.demo.intelligentfitness.util.MyUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

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
    @InjectView(R.id.tv_info_phone)
    TextView tvInfoPhone;
    @InjectView(R.id.tv_info_name)
    TextView tvInfoName;
    @InjectView(R.id.tv_info_height)
    TextView tvInfoHeight;
    @InjectView(R.id.tv_info_weight)
    TextView tvInfoWeight;

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

    @Override
    public void onResume() {
        super.onResume();
        getInfo();
    }

    /**
     * 获取个人信息
     */
    private void getInfo() {
        String url = ApiAddress.getURL(ApiAddress.EDIT_INFO);
        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String code = jsonObject.getString("code");
                    switch (code) {
                        case "1000":
                            JSONObject jsonObject1=jsonObject.getJSONObject("data");
                            String name=jsonObject1.getString("nickname");
                            String height=jsonObject1.getString("height");
                            String tel=jsonObject1.getString("tel");
                            String weight=jsonObject1.getString("weight");
                            tvInfoHeight.setText(height+"公分");
                            tvInfoName.setText(name);
                            tvInfoPhone.setText(tel);
                            tvInfoWeight.setText(weight+"kg");
                            MySharedPrefrencesUtil.setParam(getActivity(),"name",name);
                            break;
                        case "6001":
                        case "6002":
//                            System.exit(0);
                            startActivity(new Intent(getActivity(), LoginActivity.class));
                            getActivity().finish();
                            break;
                        default:
                            String msg = jsonObject.getString("msg");
                            MyUtils.showToast(getActivity(), msg);
                            break;
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                MyUtils.showToast(getActivity(), "网络有问题");
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                MyUtils.Loge("AAA", (String) MySharedPrefrencesUtil.getParam(getActivity(), "token", ""));
                if (MySharedPrefrencesUtil.getParam(getActivity(), "token", "") != null) {
                    map.put("Authorization", (String) MySharedPrefrencesUtil.getParam(getActivity(), "token", ""));
                }
                return map;
            }
        };
        MyApplication.getRequestQueue().add(stringRequest);
    }
}
