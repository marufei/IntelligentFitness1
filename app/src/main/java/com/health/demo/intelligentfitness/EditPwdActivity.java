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
import com.health.demo.intelligentfitness.util.MySharedPrefrencesUtil;
import com.health.demo.intelligentfitness.util.MyUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class EditPwdActivity extends BaseActivity {


    @InjectView(R.id.tl_custom)
    Toolbar tlCustom;
    @InjectView(R.id.et_edit_pwd_old)
    EditText etEditPwdOld;
    @InjectView(R.id.et_edit_pwd_new)
    EditText etEditPwdNew;
    @InjectView(R.id.tv_edit_pwd_sure)
    TextView tvEditPwdSure;
    @InjectView(R.id.activity_edit_pwd)
    LinearLayout activityEditPwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_pwd);
        ButterKnife.inject(this);
        initToolbar("修改密码");
    }


    @OnClick(R.id.tv_edit_pwd_sure)
    public void onViewClicked() {
        if(TextUtils.isEmpty(etEditPwdNew.getText().toString().trim())||TextUtils.isEmpty(etEditPwdOld.getText().toString().trim())){
            MyUtils.showToast(EditPwdActivity.this,"请完善新旧密码");
            return;
        }
        if(!MyUtils.isPassword(etEditPwdOld.getText().toString().trim())||!MyUtils.isPassword(etEditPwdNew.getText().toString().trim())){
            MyUtils.showToast(EditPwdActivity.this,"密码格式不正确");
            return;
        }
        MyUtils.showDialog(EditPwdActivity.this,"修改中...");
        String url= ApiAddress.getURL(ApiAddress.EDIT_PWD);
        StringRequest stringRequest=new StringRequest(StringRequest.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                MyUtils.dismssDialog();
                try {
                    JSONObject jsonObject=new JSONObject(response);
                    String code=jsonObject.getString("code");
                    switch (code){
                        case "1000":
                            MyUtils.showToast(EditPwdActivity.this,"修改成功");
                            MySharedPrefrencesUtil.setParam(EditPwdActivity.this,"token","");
                            finish();
                            break;
                        case "6001":
                        case "6002":
                            startActivity(new Intent(EditPwdActivity.this,LoginActivity.class));
                            finish();
                            break;
                        default:
                            String msg=jsonObject.getString("msg");
                            MyUtils.showToast(EditPwdActivity.this,msg);
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
                MyUtils.showToast(EditPwdActivity.this,"网络有问题");
            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String,String> map=new HashMap<>();
                MyUtils.Loge("AAA",(String) MySharedPrefrencesUtil.getParam(EditPwdActivity.this, "token", ""));
                if (MySharedPrefrencesUtil.getParam(EditPwdActivity.this,"token","")!=null) {
                    map.put("Authorization", (String) MySharedPrefrencesUtil.getParam(EditPwdActivity.this, "token", ""));
                }
                return map;
            }

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String>map=new HashMap<>();
                map.put("old_password",etEditPwdOld.getText().toString().trim());
                map.put("new_password",etEditPwdNew.getText().toString().trim());
                return map;
            }
        };
        MyApplication.getRequestQueue().add(stringRequest);
    }
}
