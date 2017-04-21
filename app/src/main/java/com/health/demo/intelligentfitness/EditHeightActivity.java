package com.health.demo.intelligentfitness;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class EditHeightActivity extends BaseActivity {

    @InjectView(R.id.tl_custom)
    Toolbar tlCustom;
    @InjectView(R.id.et_edit_height_edit)
    EditText etEditHeightEdit;
    @InjectView(R.id.tv_edit_height_sure)
    TextView tvEditHeightSure;
    @InjectView(R.id.activity_edit_height)
    LinearLayout activityEditHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_height);
        ButterKnife.inject(this);
        initToolbar("修改身高");
    }

    @OnClick(R.id.tv_edit_height_sure)
    public void onViewClicked() {
    }
}
