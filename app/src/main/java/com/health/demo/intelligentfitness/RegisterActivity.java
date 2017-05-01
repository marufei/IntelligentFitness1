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

public class RegisterActivity extends BaseActivity {

    @InjectView(R.id.tl_custom)
    Toolbar tlCustom;
    @InjectView(R.id.et_register_phone)
    EditText etRegisterPhone;
    @InjectView(R.id.iv_register_regcode)
    ImageView ivRegisterRegcode;
    @InjectView(R.id.et_register_regcode)
    EditText etRegisterRegcode;
    @InjectView(R.id.et_register_pwd)
    EditText etRegisterPwd;
    @InjectView(R.id.tv_register_reg)
    TextView tvRegisterReg;
    @InjectView(R.id.tv_register_protocol)
    TextView tvRegisterProtocol;
    @InjectView(R.id.activity_register)
    LinearLayout activityRegister;
    //产生的验证码
    private String realCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.inject(this);
        initToolbar("注册");
        ivRegisterRegcode.setImageBitmap(Code.getInstance().createBitmap());
        realCode=Code.getInstance().getCode().toLowerCase();
    }

    @OnClick({R.id.iv_register_regcode, R.id.tv_register_reg})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_register_regcode:
                ivRegisterRegcode.setImageBitmap(Code.getInstance().createBitmap());
                realCode=Code.getInstance().getCode().toLowerCase();
                break;
            case R.id.tv_register_reg:
                if (TextUtils.isEmpty(etRegisterPhone.getText().toString().trim()) || TextUtils.isEmpty(etRegisterPwd.getText().toString().trim()) || TextUtils.isEmpty(etRegisterRegcode.getText().toString().trim())) {
                    MyUtils.showToast(RegisterActivity.this,"请完善资料再进行下一步");
                    return;
                }
                if(!MyUtils.isPhoneNumber(etRegisterPhone.getText().toString().trim())){
                    MyUtils.showToast(RegisterActivity.this,"请输入正确的手机号");
                    return;
                }
                if(!realCode.equals(etRegisterRegcode.getText().toString().trim())){
                    MyUtils.showToast(RegisterActivity.this,"验证码不正确");
                    return;
                }
                if(!MyUtils.isPassword(etRegisterPwd.getText().toString().trim())){
                    MyUtils.showToast(RegisterActivity.this,"密码格式不正确");
                    return;
                }
                Intent intent =new Intent(RegisterActivity.this,Register2Activity.class);
                intent.putExtra("phone",etRegisterPhone.getText().toString().trim());
                intent.putExtra("pwd",etRegisterPwd.getText().toString().trim());
                startActivity(intent);
                finish();
                break;
        }
    }
}
