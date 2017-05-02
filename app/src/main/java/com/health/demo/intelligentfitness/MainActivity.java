package com.health.demo.intelligentfitness;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.health.demo.intelligentfitness.fragment.EvaluateFragment;
import com.health.demo.intelligentfitness.fragment.HealthFragment;
import com.health.demo.intelligentfitness.fragment.HomeFragment;
import com.health.demo.intelligentfitness.fragment.PersionFragment;
import com.health.demo.intelligentfitness.fragment.PlanFragment;
import com.health.demo.intelligentfitness.util.MyUtils;
import com.health.demo.intelligentfitness.util.photo.PictureMenu;
import com.squareup.picasso.Picasso;

import java.io.File;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    private Toolbar tl_custom;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private FrameLayout fl_drawerlayout_layout;
    private RelativeLayout rl_drawerlayout_health;
    private RelativeLayout rl_drawerlayout_plan;
    private RelativeLayout rl_drawerlayout_evaluate;
    private RelativeLayout rl_evaluate_persional;
    private TextView tv_first;
    private ImageView iv_drawerlayout_header;
    private PictureMenu mPhotoMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mPhotoMenu = new PictureMenu();
        initViews();
        setSupportActionBar(tl_custom);
        getSupportActionBar().setHomeButtonEnabled(true);//设置返回键可用
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //创建返回键，并实现打开/关闭监听
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
        //默认第一次进入首页
        FragmentManager fragmentManager4=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction4=fragmentManager4.beginTransaction();
        HomeFragment homeFragment=new HomeFragment();
        fragmentTransaction4.replace(R.id.fl_drawerlayout_layout,homeFragment).commit();
    }

    private void initViews() {
        tl_custom = (Toolbar) findViewById(R.id.tl_custom);
        tl_custom.setTitle("智能健身");
        tv_first=(TextView)findViewById(R.id.tv_first);
        tv_first.setOnClickListener(this);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.dl_left);
        fl_drawerlayout_layout = (FrameLayout) findViewById(R.id.fl_drawerlayout_layout);
        rl_drawerlayout_health = (RelativeLayout) findViewById(R.id.rl_drawerlayout_health);
        rl_drawerlayout_plan = (RelativeLayout) findViewById(R.id.rl_drawerlayout_plan);
        rl_drawerlayout_evaluate = (RelativeLayout) findViewById(R.id.rl_drawerlayout_evaluate);
        rl_evaluate_persional = (RelativeLayout) findViewById(R.id.rl_evaluate_persional);
        iv_drawerlayout_header=(ImageView)findViewById(R.id.iv_drawerlayout_header);
        iv_drawerlayout_header.setOnClickListener(this);
        rl_drawerlayout_health.setOnClickListener(this);
        rl_drawerlayout_plan.setOnClickListener(this);
        rl_drawerlayout_evaluate.setOnClickListener(this);
        rl_evaluate_persional.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_evaluate_persional:
                mDrawerLayout.closeDrawers();
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                PersionFragment persionFragment = new PersionFragment();
                fragmentTransaction.replace(R.id.fl_drawerlayout_layout, persionFragment).commit();
                break;
            case R.id.rl_drawerlayout_evaluate:
                mDrawerLayout.closeDrawers();
                FragmentManager fragmentManager1 = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction1 = fragmentManager1.beginTransaction();
                EvaluateFragment evaluateFragment = new EvaluateFragment();
                fragmentTransaction1.replace(R.id.fl_drawerlayout_layout, evaluateFragment).commit();
                break;
            case R.id.rl_drawerlayout_plan:
                mDrawerLayout.closeDrawers();
                FragmentManager fragmentManager2 = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction2 = fragmentManager2.beginTransaction();
                PlanFragment planFragment = new PlanFragment();
                fragmentTransaction2.replace(R.id.fl_drawerlayout_layout, planFragment).commit();
                break;
            case R.id.rl_drawerlayout_health:
                mDrawerLayout.closeDrawers();
                FragmentManager fragmentManager3 = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction3 = fragmentManager3.beginTransaction();
                HealthFragment healthFragment = new HealthFragment();
                fragmentTransaction3.replace(R.id.fl_drawerlayout_layout, healthFragment).commit();
                break;
            case R.id.tv_first:
                mDrawerLayout.closeDrawers();
                FragmentManager fragmentManager4=getSupportFragmentManager();
                FragmentTransaction fragmentTransaction4=fragmentManager4.beginTransaction();
                HomeFragment homeFragment=new HomeFragment();
                fragmentTransaction4.replace(R.id.fl_drawerlayout_layout,homeFragment).commit();
                break;
            case R.id.iv_drawerlayout_header:
//                mPhotoMenu.show(getSupportFragmentManager(), "PersonActivity");
                MediaUtil.doPickPhotoAction(MainActivity.this);
                break;
        }

    }
    // 用来计算返回键的点击间隔时间
    private long exitTime = 0;

    /**
     * 按两次退出应用
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK
                && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                //弹出提示，可以有多种方式
                MyUtils.showToast(getApplicationContext(), "再按一次退出程序");
                exitTime = System.currentTimeMillis();
            } else {
                finish();
            }
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data!=null){
            switch (requestCode){
                case MediaUtil.SELECT_PHOTO_CODE:// 相册
                case MediaUtil.CAMERA_REQUEST_CODE:// 相机
                    MyUtils.Loge("aaa","data111="+data.toString());
                    MediaUtil.doCropPhoto(MainActivity.this,data);
                    break;
                case MediaUtil.PHOTO_CROP:// 剪裁
//                    iv_head.setImageURI(data.getData());
//                    SettingActivity.setHeadImg(data);
//                    MyUtils.Loge(TAG,"data2="+data.toString());
                    try {
                        MyUtils.Loge("aaa","FilePath="+MediaUtil.getRealFilePath(
                                MainActivity.this, data.getData()));
                        File iconFile = new File(MediaUtil.getRealFilePath(
                                MainActivity.this, data.getData()));
//                        postIcon(iconFile,data);
                        Picasso.with(MainActivity.this).load(iconFile).error(R.drawable.vector_drawable_header).into(iv_drawerlayout_header);
                    } catch (Exception e) {
                        MyUtils.Loge("aaa", "上传头像异常：" + e);
                    }
                    break;
            }
        }
    }
}
