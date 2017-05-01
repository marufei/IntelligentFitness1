package com.health.demo.intelligentfitness;

import android.content.Intent;
import android.os.Bundle;
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

public class LoginActivity extends BaseActivity{
    @InjectView(R.id.et_login_user)
    EditText etLoginUser;
    @InjectView(R.id.et_login_pwd)
    EditText etLoginPwd;
    @InjectView(R.id.tv_login_login)
    TextView tvLoginLogin;
    @InjectView(R.id.tv_login_forget)
    TextView tvLoginForget;
    @InjectView(R.id.tv_login_register)
    TextView tvLoginRegister;
    @InjectView(R.id.activity_login)
    LinearLayout activityLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.inject(this);

    }

    @OnClick(R.id.tv_login_login)
    public void onTvLoginLoginClicked() {
        if(TextUtils.isEmpty(etLoginUser.getText().toString().trim())||TextUtils.isEmpty(etLoginPwd.getText().toString().trim())){
            MyUtils.showToast(LoginActivity.this,"手机号或密码不能为空");
            return;
        }
        if(!MyUtils.isPhoneNumber(etLoginUser.getText().toString().trim())){
            MyUtils.showToast(LoginActivity.this,"请输入正确的手机号");
            return;
        }
        if(!MyUtils.isPassword(etLoginPwd.getText().toString().trim())){
            MyUtils.showToast(LoginActivity.this,"请输入正确的密码格式");
            return;
        }
        login();
    }

    /**
     * 登录
     */
    private void login() {
        MyUtils.showDialog(this,"登录中...");
        String url= ApiAddress.getURL(ApiAddress.LOGIN);
//        VolleyUtils.RequestPost(LoginActivity.this,url,"login",map,volleyListenerInterface);
        StringRequest stringRequest=new StringRequest(StringRequest.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                MyUtils.dismssDialog();
                try {
                    JSONObject jsonObject=new JSONObject(response);
                    String code=jsonObject.getString("code");
                    switch (code){
                        case "1000":
                            JSONObject jsonObject1=jsonObject.getJSONObject("data");
                            String access_token=jsonObject1.getString("access_token");
                            MySharedPrefrencesUtil.setParam(LoginActivity.this,"token",access_token);
                            startActivity(new Intent(LoginActivity.this,MainActivity.class));
                            finish();
                            break;
                        default:
                            String msg=jsonObject.getString("msg");
                            MyUtils.showToast(LoginActivity.this,msg);
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
                MyUtils.showToast(LoginActivity.this,"网络有问题");
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> map=new HashMap<>();
                map.put("tel",etLoginUser.getText().toString().trim());
                map.put("password",etLoginPwd.getText().toString().trim());
                return map;
            }
        };
        MyApplication.getRequestQueue().add(stringRequest);
    }

    @OnClick(R.id.tv_login_forget)
    public void onTvLoginForgetClicked() {
        startActivity(new Intent(this,ForgetPwdActivity.class));
    }

    @OnClick(R.id.tv_login_register)
    public void onTvLoginRegisterClicked() {
        startActivity(new Intent(this,RegisterActivity.class));
    }
}
