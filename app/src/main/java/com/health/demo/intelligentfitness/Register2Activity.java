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
import com.health.demo.intelligentfitness.util.MyUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class Register2Activity extends BaseActivity {

    @InjectView(R.id.tl_custom)
    Toolbar tlCustom;
    @InjectView(R.id.et_reg_height)
    EditText etRegHeight;
    @InjectView(R.id.et_reg_weight)
    EditText etRegWeight;
    @InjectView(R.id.tv_edit_sure)
    TextView tvEditSure;
    @InjectView(R.id.activity_register2)
    LinearLayout activityRegister2;
    private String phone;
    private String pwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register2);
        ButterKnife.inject(this);
        initToolbar("注册");
        initData();
    }

    /**
     * 接收数据
     */
    private void initData() {
        Intent intent=getIntent();
        phone=intent.getStringExtra("phone");
        pwd=intent.getStringExtra("pwd");
    }


    @OnClick(R.id.tv_edit_sure)
    public void onViewClicked() {
        if(TextUtils.isEmpty(etRegHeight.getText().toString().trim())){
            MyUtils.showToast(Register2Activity.this,"请完善身高信息");
            return;
        }
        if(TextUtils.isEmpty(etRegWeight.getText().toString().trim())){
            MyUtils.showToast(Register2Activity.this,"请完善体重信息");
            return;
        }
        if(Double.valueOf(etRegHeight.getText().toString().trim())>250.0){
            MyUtils.showToast(Register2Activity.this,"身高最大不能超过250.0");
            return;
        }
        if(Double.valueOf(etRegWeight.getText().toString().trim())>250.0){
            MyUtils.showToast(Register2Activity.this,"体重最大不能超过250.0");
            return;
        }
        register();
    }

    /**
     * 注册
     */
    private void register() {
        MyUtils.showDialog(Register2Activity.this,"注册中...");
        String url= ApiAddress.getURL(ApiAddress.REGISTER);
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
                            MyUtils.showToast(Register2Activity.this,"注册成功");
                            finish();
                            break;
                        default:
                            MyUtils.showToast(Register2Activity.this,msg);
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
                MyUtils.showToast(Register2Activity.this,"网络有问题");
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> map=new HashMap<>();
                map.put("tel",phone);
                map.put("password",pwd);
                map.put("weight",etRegWeight.getText().toString().trim());
                map.put("height",etRegHeight.getText().toString().trim());
                return map;
            }
        };
        MyApplication.getRequestQueue().add(stringRequest);
    }
}
