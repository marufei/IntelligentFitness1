package com.health.demo.intelligentfitness;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class EditPwdActivity extends BaseActivity {

    @InjectView(R.id.tl_custom)
    Toolbar tlCustom;
    @InjectView(R.id.et_edit_pwd_edit)
    EditText etEditPwdEdit;
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
    }
}
