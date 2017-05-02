package com.health.demo.intelligentfitness;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
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

public class EditHeightActivity extends BaseActivity {

    @InjectView(R.id.tl_custom)
    Toolbar tlCustom;
    @InjectView(R.id.et_edit_height_edit)
    EditText etEditHeightEdit;
    @InjectView(R.id.tv_edit_height_sure)
    TextView tvEditHeightSure;
    @InjectView(R.id.activity_edit_height)
    LinearLayout activityEditHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_height);
        ButterKnife.inject(this);
        initToolbar("修改身高");
    }

    @OnClick(R.id.tv_edit_height_sure)
    public void onViewClicked() {
        if (TextUtils.isEmpty(etEditHeightEdit.getText().toString().trim())) {
            MyUtils.showToast(EditHeightActivity.this, "请完善身高信息");
            return;
        }
        if (Double.valueOf(etEditHeightEdit.getText().toString().trim()) > 250.0) {
            MyUtils.showToast(EditHeightActivity.this, "体重范围在0~250.0cm");
            return;
        }
        MyUtils.showDialog(EditHeightActivity.this, "修改中...");
        String url = ApiAddress.getURL(ApiAddress.EDIT_INFO);
        StringRequest stringRequest = new StringRequest(StringRequest.Method.PUT, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                MyUtils.dismssDialog();
                MyUtils.showToast(EditHeightActivity.this, "response：" + response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String code = jsonObject.getString("code");
                    switch (code) {
                        case "1000":
                            MyUtils.showToast(EditHeightActivity.this, "修改成功");
                            finish();
                            break;
                        case "6001":
                        case "6002":
                            startActivity(new Intent(EditHeightActivity.this, LoginActivity.class));
                            finish();
                            break;
                        default:
                            String msg = jsonObject.getString("msg");
                            MyUtils.showToast(EditHeightActivity.this, msg);
                            break;

                    }
                } catch (JSONException e) {
                    MyUtils.dismssDialog();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                MyUtils.dismssDialog();
                MyUtils.showToast(EditHeightActivity.this, "网络有问题");
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                MyUtils.Loge("AAA", (String) MySharedPrefrencesUtil.getParam(EditHeightActivity.this, "token", ""));
                if (MySharedPrefrencesUtil.getParam(EditHeightActivity.this, "token", "") != null) {
                    map.put("Authorization", (String) MySharedPrefrencesUtil.getParam(EditHeightActivity.this, "token", ""));
                }
                return map;
            }

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("height", etEditHeightEdit.getText().toString().trim());
                return map;
            }
        };
        MyApplication.getRequestQueue().add(stringRequest);
    }
}
