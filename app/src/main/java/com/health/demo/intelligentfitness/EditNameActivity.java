package com.health.demo.intelligentfitness;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class EditNameActivity extends BaseActivity {

    @InjectView(R.id.tl_custom)
    Toolbar tlCustom;
    @InjectView(R.id.et_edit_name_edit)
    EditText etEditNameEdit;
    @InjectView(R.id.tv_edit_name_sure)
    TextView tvEditNameSure;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_name);
        ButterKnife.inject(this);
        initToolbar("修改名称");
    }

    @OnClick(R.id.tv_edit_name_sure)
    public void onViewClicked() {
        
    }
}
