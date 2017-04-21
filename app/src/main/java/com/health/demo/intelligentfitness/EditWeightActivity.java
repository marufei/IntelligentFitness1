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

public class EditWeightActivity extends BaseActivity {

    @InjectView(R.id.tl_custom)
    Toolbar tlCustom;
    @InjectView(R.id.et_edit_weight_edit)
    EditText etEditWeightEdit;
    @InjectView(R.id.tv_edit_weight_sure)
    TextView tvEditWeightSure;
    @InjectView(R.id.activity_edit_weight)
    LinearLayout activityEditWeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_weight);
        ButterKnife.inject(this);
        initToolbar("修改体重");
    }

    @OnClick(R.id.tv_edit_weight_sure)
    public void onViewClicked() {
    }
}
