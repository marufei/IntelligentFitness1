package com.health.demo.intelligentfitness;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.health.demo.intelligentfitness.util.MyUtils;
import com.health.demo.intelligentfitness.view.Code;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class ForgetPwdActivity extends BaseActivity {

    @InjectView(R.id.et_forgetpwd_phone)
    EditText etForgetpwdPhone;
    @InjectView(R.id.et_forgetpwd_regcode)
    EditText etForgetpwdRegcode;
    @InjectView(R.id.tv_forgetpwd_next)
    TextView tvForgetpwdNext;
    @InjectView(R.id.activity_forget_pwd)
    LinearLayout activityForgetPwd;
    @InjectView(R.id.tl_custom)
    Toolbar tlCustom;
    @InjectView(R.id.iv_forgetpwd_regcode)
    ImageView ivForgetpwdRegcode;
    private String realCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_pwd);
        ButterKnife.inject(this);
        initToolbar("忘记密码");
        ivForgetpwdRegcode.setImageBitmap(Code.getInstance().createBitmap());
        realCode = Code.getInstance().getCode().toLowerCase();
    }

    @OnClick({R.id.tv_forgetpwd_next})
    public void onViewClicked(View view) {
        if(TextUtils.isEmpty(etForgetpwdPhone.getText().toString().trim())||TextUtils.isEmpty(etForgetpwdRegcode.getText().toString().trim())){
            MyUtils.showToast(ForgetPwdActivity.this,"手机号或者验证码不能为空");
            return;
        }
        if(!MyUtils.isPhoneNumber(etForgetpwdPhone.getText().toString().trim())){
            MyUtils.showToast(ForgetPwdActivity.this,"请输入正确的手机号");
            return;
        }
        if(!realCode.equals(etForgetpwdRegcode.getText().toString().trim())){
            MyUtils.showToast(ForgetPwdActivity.this,"请输入正确的验证码");
            return;
        }
        Intent intent=new Intent(this, ForgetPwd2Activity.class);
        intent.putExtra("phone",etForgetpwdPhone.getText().toString().trim());
        startActivity(intent);
        finish();
    }

    @OnClick(R.id.iv_forgetpwd_regcode)
    public void onViewClicked() {
        ivForgetpwdRegcode.setImageBitmap(Code.getInstance().createBitmap());
        realCode = Code.getInstance().getCode().toLowerCase();
    }
}
