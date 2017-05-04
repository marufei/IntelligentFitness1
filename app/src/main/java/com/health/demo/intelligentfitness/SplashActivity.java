package com.health.demo.intelligentfitness;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.health.demo.intelligentfitness.util.MySharedPrefrencesUtil;

public class SplashActivity extends AppCompatActivity {


    private ImageView ivSplashBg;
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    if(TextUtils.isEmpty((String) MySharedPrefrencesUtil.getParam(SplashActivity.this,"token",""))){
                        startActivity(new Intent(SplashActivity.this,LoginActivity.class));
                        finish();
                    }else {
                        startActivity(new Intent(SplashActivity.this,MainActivity.class));
                        finish();
                    }
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ivSplashBg=(ImageView)findViewById(R.id.iv_splash_bg);
        Glide.with(this).load(R.mipmap.back).into(ivSplashBg);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2500);
                    handler.sendEmptyMessage(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }
}
