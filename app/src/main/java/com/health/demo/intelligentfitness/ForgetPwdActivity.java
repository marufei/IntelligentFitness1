package com.health.demo.intelligentfitness;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class ForgetPwdActivity extends BaseActivity {

    @InjectView(R.id.et_forgetpwd_phone)
    EditText etForgetpwdPhone;
    @InjectView(R.id.tv_forgetpwd_regcode)
    TextView tvForgetpwdRegcode;
    @InjectView(R.id.et_forgetpwd_regcode)
    EditText etForgetpwdRegcode;
    @InjectView(R.id.tv_forgetpwd_next)
    TextView tvForgetpwdNext;
    @InjectView(R.id.activity_forget_pwd)
    LinearLayout activityForgetPwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_pwd);
        ButterKnife.inject(this);
        initToolbar("忘记密码");
    }

    @OnClick({R.id.tv_forgetpwd_regcode, R.id.tv_forgetpwd_next})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_forgetpwd_regcode:
                break;
            case R.id.tv_forgetpwd_next:
                startActivity(new Intent(this,ForgetPwd2Activity.class));
                break;
        }
    }
}
