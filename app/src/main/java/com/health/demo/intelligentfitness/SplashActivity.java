package com.health.demo.intelligentfitness;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.health.demo.intelligentfitness.util.MySharedPrefrencesUtil;

public class SplashActivity extends AppCompatActivity {


    private ImageView ivSplashBg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ivSplashBg=(ImageView)findViewById(R.id.iv_splash_bg);
        Glide.with(this).load(R.mipmap.back).into(ivSplashBg);
        if(TextUtils.isEmpty((String) MySharedPrefrencesUtil.getParam(this,"token",""))){
            startActivity(new Intent(this,LoginActivity.class));
            finish();
        }else {
            startActivity(new Intent(this,MainActivity.class));
            finish();
        }
    }
}
