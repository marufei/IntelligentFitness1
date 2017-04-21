package com.health.demo.intelligentfitness;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.health.demo.intelligentfitness.fragment.EvaluateFragment;
import com.health.demo.intelligentfitness.fragment.HealthFragment;
import com.health.demo.intelligentfitness.fragment.PersionFragment;
import com.health.demo.intelligentfitness.fragment.PlanFragment;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    private Toolbar tl_custom;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private FrameLayout fl_drawerlayout_layout;
    private RelativeLayout rl_drawerlayout_health;
    private RelativeLayout rl_drawerlayout_plan;
    private RelativeLayout rl_drawerlayout_evaluate;
    private RelativeLayout rl_evaluate_persional;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        setSupportActionBar(tl_custom);
        getSupportActionBar().setHomeButtonEnabled(true);//设置返回键可用
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //创建返回键，并实现打开关/闭监听
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, tl_custom, R.string.drawer_open, R.string.drawer_close) {

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                //
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                //
            }
        };
        mDrawerToggle.syncState();
        mDrawerLayout.setDrawerListener(mDrawerToggle);
    }

    private void initViews() {
        tl_custom = (Toolbar) findViewById(R.id.tl_custom);
        tl_custom.setTitle("智能健身");
        mDrawerLayout = (DrawerLayout) findViewById(R.id.dl_left);
        fl_drawerlayout_layout = (FrameLayout) findViewById(R.id.fl_drawerlayout_layout);
        rl_drawerlayout_health = (RelativeLayout) findViewById(R.id.rl_drawerlayout_health);
        rl_drawerlayout_plan = (RelativeLayout) findViewById(R.id.rl_drawerlayout_plan);
        rl_drawerlayout_evaluate = (RelativeLayout) findViewById(R.id.rl_drawerlayout_evaluate);
        rl_evaluate_persional = (RelativeLayout) findViewById(R.id.rl_evaluate_persional);
        rl_drawerlayout_health.setOnClickListener(this);
        rl_drawerlayout_plan.setOnClickListener(this);
        rl_drawerlayout_evaluate.setOnClickListener(this);
        rl_evaluate_persional.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_evaluate_persional:
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                PersionFragment persionFragment = new PersionFragment();
                fragmentTransaction.replace(R.id.fl_drawerlayout_layout, persionFragment).commit();
                break;
            case R.id.rl_drawerlayout_evaluate:
                FragmentManager fragmentManager1 = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction1 = fragmentManager1.beginTransaction();
                EvaluateFragment evaluateFragment = new EvaluateFragment();
                fragmentTransaction1.replace(R.id.fl_drawerlayout_layout, evaluateFragment).commit();
                break;
            case R.id.rl_drawerlayout_plan:
                FragmentManager fragmentManager2 = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction2 = fragmentManager2.beginTransaction();
                PlanFragment planFragment = new PlanFragment();
                fragmentTransaction2.replace(R.id.fl_drawerlayout_layout, planFragment).commit();
                break;
            case R.id.rl_drawerlayout_health:
                FragmentManager fragmentManager3 = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction3 = fragmentManager3.beginTransaction();
                HealthFragment healthFragment = new HealthFragment();
                fragmentTransaction3.replace(R.id.fl_drawerlayout_layout, healthFragment).commit();
                break;
        }
    }
}
