package com.health.demo.intelligentfitness;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class RegisterActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initToolbar("注册");
    }
}
