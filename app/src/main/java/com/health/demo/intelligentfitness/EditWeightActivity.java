package com.health.demo.intelligentfitness;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

public class EditWeightActivity extends BaseActivity {

    @InjectView(R.id.tl_custom)
    Toolbar tlCustom;
    @InjectView(R.id.et_edit_weight_edit)
    EditText etEditWeightEdit;
    @InjectView(R.id.tv_edit_weight_sure)
    TextView tvEditWeightSure;
    @InjectView(R.id.activity_edit_weight)
    LinearLayout activityEditWeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_weight);
        ButterKnife.inject(this);
        initToolbar("修改体重");
    }

    @OnClick(R.id.tv_edit_weight_sure)
    public void onViewClicked() {
        if (TextUtils.isEmpty(etEditWeightEdit.getText().toString().trim())) {
            MyUtils.showToast(EditWeightActivity.this, "请完善体重信息");
            return;
        }
        if (Double.valueOf(etEditWeightEdit.getText().toString().trim()) > 250.0) {
            MyUtils.showToast(EditWeightActivity.this, "体重范围在0~250.0kg");
            return;
        }
        MyUtils.showDialog(EditWeightActivity.this, "修改中...");
        String url = ApiAddress.getURL(ApiAddress.EDIT_INFO);
        StringRequest stringRequest = new StringRequest(StringRequest.Method.PUT, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                MyUtils.dismssDialog();
                MyUtils.showToast(EditWeightActivity.this, "response：" + response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String code = jsonObject.getString("code");
                    switch (code) {
                        case "1000":
                            MyUtils.showToast(EditWeightActivity.this, "修改成功");
                            finish();
                            break;
                        case "6001":
                        case "6002":
                            startActivity(new Intent(EditWeightActivity.this, LoginActivity.class));
                            finish();
                            break;
                        default:
                            String msg = jsonObject.getString("msg");
                            MyUtils.showToast(EditWeightActivity.this, msg);
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
                MyUtils.showToast(EditWeightActivity.this, "网络有问题");
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                MyUtils.Loge("AAA", (String) MySharedPrefrencesUtil.getParam(EditWeightActivity.this, "token", ""));
                if (MySharedPrefrencesUtil.getParam(EditWeightActivity.this, "token", "") != null) {
                    map.put("Authorization", (String) MySharedPrefrencesUtil.getParam(EditWeightActivity.this, "token", ""));
                }
                return map;
            }

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("weight", etEditWeightEdit.getText().toString().trim());
                return map;
            }
        };
        MyApplication.getRequestQueue().add(stringRequest);
    }
}
