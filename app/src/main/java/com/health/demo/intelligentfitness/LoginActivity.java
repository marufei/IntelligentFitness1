package com.health.demo.intelligentfitness;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity {


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
        Toast.makeText(this, "asd;fjk", Toast.LENGTH_LONG).show();
        startActivity(new Intent(this, MainActivity.class));
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
