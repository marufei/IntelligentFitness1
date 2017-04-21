package com.health.demo.intelligentfitness;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;

public class SplashActivity extends AppCompatActivity {


    private ImageView ivSplashBg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ivSplashBg=(ImageView)findViewById(R.id.iv_splash_bg);
        Glide.with(this).load("http://s1.dwstatic.com/group1/M00/E5/B1/4c0df482fef1bccac8f242d37445c8f4.gif").into(ivSplashBg);
    }
}
