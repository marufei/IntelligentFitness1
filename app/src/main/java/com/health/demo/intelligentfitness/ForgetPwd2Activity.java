package com.health.demo.intelligentfitness;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.health.demo.intelligentfitness.api.ApiAddress;
import com.health.demo.intelligentfitness.util.MyUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class ForgetPwd2Activity extends BaseActivity {

    @InjectView(R.id.tl_custom)
    Toolbar tlCustom;
    @InjectView(R.id.et_forgetpwd2_pwd1)
    EditText etForgetpwd2Pwd1;
    @InjectView(R.id.et_forgetpwd2_pwd2)
    EditText etForgetpwd2Pwd2;
    @InjectView(R.id.tv_forgetpwd2_login)
    TextView tvForgetpwd2Login;
    @InjectView(R.id.activity_forget_pwd2)
    LinearLayout activityForgetPwd2;
    private String phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_pwd2);
        ButterKnife.inject(this);
        initToolbar("设置密码");
        initData();
    }

    private void initData() {
        Intent intent=getIntent();
        phone=intent.getStringExtra("phone");
    }

    @OnClick(R.id.tv_forgetpwd2_login)
    public void onViewClicked() {
        if(TextUtils.isEmpty(etForgetpwd2Pwd1.getText().toString().trim())||TextUtils.isEmpty(etForgetpwd2Pwd2.getText().toString().trim())){
            MyUtils.showToast(ForgetPwd2Activity.this,"两次密码不能为空");
            return;
        }
        if(!MyUtils.isPassword(etForgetpwd2Pwd1.getText().toString().trim())||!MyUtils.isPassword(etForgetpwd2Pwd2.getText().toString().trim())){
            MyUtils.showToast(ForgetPwd2Activity.this,"密码格式不正确");
            return;
        }
        if(!etForgetpwd2Pwd1.getText().toString().trim().equals(etForgetpwd2Pwd2.getText().toString().trim())){
            MyUtils.showToast(ForgetPwd2Activity.this,"两次密码不一致");
            return;
        }
        resetPWD();
    }

    /**
     * 重置密码
     */
    private void resetPWD() {
        MyUtils.showDialog(ForgetPwd2Activity.this,"重置密码中...");
        String url= ApiAddress.getURL(ApiAddress.RET_PWD);
        StringRequest stringRequest=new StringRequest(StringRequest.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                MyUtils.dismssDialog();
                try {
                    JSONObject jsonObject=new JSONObject(response);
                    String code=jsonObject.getString("code");
                    String msg=jsonObject.getString("msg");
                    switch (code){
                        case "1000":
                            MyUtils.showToast(ForgetPwd2Activity.this,"重置密码成功");
                            finish();
                            break;
                        default:
                            MyUtils.showToast(ForgetPwd2Activity.this,msg);
                            break;
                    }
                } catch (JSONException e) {
                    MyUtils.dismssDialog();
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                MyUtils.dismssDialog();
                MyUtils.showToast(ForgetPwd2Activity.this,"网络有问题");
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> map=new HashMap<>();
                map.put("tel",phone);
                map.put("password",etForgetpwd2Pwd1.getText().toString().trim());
                return map;
            }
        };
        MyApplication.getRequestQueue().add(stringRequest);
    }
}
