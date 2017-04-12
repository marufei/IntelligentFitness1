package com.health.demo.intelligentfitness;

import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;

public class MainActivity extends AppCompatActivity {

    private Toolbar tl_custom;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        initViews();
        setSupportActionBar(tl_custom);
        getSupportActionBar().setHomeButtonEnabled(true);//设置返回键可用
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //创建返回键，并实现打开关/闭监听
        mDrawerToggle = new ActionBarDrawerToggle(this,mDrawerLayout,tl_custom,R.string.drawer_open,R.string.drawer_close){

            @Override
            public void onDrawerOpened(View drawerView){
                super.onDrawerOpened(drawerView);
                //
            }
            @Override
            public void onDrawerClosed(View drawerView){
                super.onDrawerClosed(drawerView);
                //
            }
        } ;
        mDrawerToggle.syncState();
        mDrawerLayout.setDrawerListener(mDrawerToggle);
    }

    private void initViews() {
        tl_custom = (Toolbar) findViewById(R.id.tl_custom);
        tl_custom.setTitle("智能健身");
        mDrawerLayout=(DrawerLayout)findViewById(R.id.dl_left);
//        tl_custom.set?
    }
}
