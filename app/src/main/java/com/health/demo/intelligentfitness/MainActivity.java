package com.health.demo.intelligentfitness;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.health.demo.intelligentfitness.fragment.EvaluateFragment;
import com.health.demo.intelligentfitness.fragment.HealthFragment;
import com.health.demo.intelligentfitness.fragment.HomeFragment;
import com.health.demo.intelligentfitness.fragment.PersionFragment;
import com.health.demo.intelligentfitness.fragment.PlanFragment;
import com.health.demo.intelligentfitness.util.MySharedPrefrencesUtil;
import com.health.demo.intelligentfitness.util.MyUtils;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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
    private TextView tv_drawerlayout_name;
    private int IMAGE_PICKER = 0x11;
    private int REQUEST_CODE_SELECT = 0x12;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String currentDate = (new SimpleDateFormat("yyyy-MM-dd")).format(new Date());//获取当前时间
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
        FragmentManager fragmentManager4 = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction4 = fragmentManager4.beginTransaction();
        HomeFragment homeFragment = new HomeFragment();
        fragmentTransaction4.replace(R.id.fl_drawerlayout_layout, homeFragment).commit();
    }

    private void initViews() {
        tl_custom = (Toolbar) findViewById(R.id.tl_custom);
        tl_custom.setTitle("智能健身");
        tv_first = (TextView) findViewById(R.id.tv_first);
        tv_first.setOnClickListener(this);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.dl_left);
        fl_drawerlayout_layout = (FrameLayout) findViewById(R.id.fl_drawerlayout_layout);
        rl_drawerlayout_health = (RelativeLayout) findViewById(R.id.rl_drawerlayout_health);
        rl_drawerlayout_plan = (RelativeLayout) findViewById(R.id.rl_drawerlayout_plan);
        rl_drawerlayout_evaluate = (RelativeLayout) findViewById(R.id.rl_drawerlayout_evaluate);
        rl_evaluate_persional = (RelativeLayout) findViewById(R.id.rl_evaluate_persional);
        iv_drawerlayout_header = (ImageView) findViewById(R.id.iv_drawerlayout_header);
        tv_drawerlayout_name = (TextView) findViewById(R.id.tv_drawerlayout_name);
        iv_drawerlayout_header.setOnClickListener(this);
        rl_drawerlayout_health.setOnClickListener(this);
        rl_drawerlayout_plan.setOnClickListener(this);
        rl_drawerlayout_evaluate.setOnClickListener(this);
        rl_evaluate_persional.setOnClickListener(this);
        if (!TextUtils.isEmpty((String) MySharedPrefrencesUtil.getParam(MainActivity.this, "path", ""))) {
            Picasso.with(MainActivity.this).load(new File((String) MySharedPrefrencesUtil.getParam(MainActivity.this, "path", ""))).error(R.mipmap.default_image).into(iv_drawerlayout_header);
        }
        tv_drawerlayout_name.setText((String) MySharedPrefrencesUtil.getParam(MainActivity.this, "name", ""));
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
                FragmentManager fragmentManager4 = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction4 = fragmentManager4.beginTransaction();
                HomeFragment homeFragment = new HomeFragment();
                fragmentTransaction4.replace(R.id.fl_drawerlayout_layout, homeFragment).commit();
                break;
            case R.id.iv_drawerlayout_header:
                showDialog();
                break;
        }

    }

    /**
     * 拍照，相册dialog
     */
    public void showDialog() {
        final Dialog dialog = new Dialog(this, R.style.common_dialog);

        View view = LayoutInflater.from(MainActivity.this).inflate(R.layout.picture_menu, null);
        Button btn_camera = (Button) view.findViewById(R.id.btn_camera);
        Button btn_gallery = (Button) view.findViewById(R.id.btn_gallery);
        Button btn_cancel = (Button) view.findViewById(R.id.btn_cancel);
        btn_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ImageGridActivity.class);
                intent.putExtra(ImageGridActivity.EXTRAS_TAKE_PICKERS, true); // 是否是直接打开相机
                startActivityForResult(intent, IMAGE_PICKER);
                dialog.dismiss();
            }
        });
        btn_gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ImageGridActivity.class);
                startActivityForResult(intent, IMAGE_PICKER);
                dialog.dismiss();
            }
        });
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.setContentView(view);
        //设置dialog的显示位置
        Window window = dialog.getWindow();
        window.getDecorView().setPadding(0, 0, 0, 0);
        WindowManager.LayoutParams params = window.getAttributes();
        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        params.gravity = Gravity.BOTTOM;
        window.setAttributes(params);
        dialog.show();
    }

    // 用来计算返回键的点击间隔时间
    private long exitTime = 0;

    /**
     * 按两次退出应用
     *
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
        if (resultCode == ImagePicker.RESULT_CODE_ITEMS) {
            if (data != null && requestCode == IMAGE_PICKER) {//REQUEST_CODE_SELECT
                ArrayList<ImageItem> images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
//                Toast.makeText(this, "images.path:" + images.get(0).path, Toast.LENGTH_SHORT).show();
                Picasso.with(MainActivity.this).load(new File(images.get(0).path)).error(R.mipmap.default_image).into(iv_drawerlayout_header);
                MySharedPrefrencesUtil.setParam(MainActivity.this, "path", images.get(0).path);
            } else {
                Toast.makeText(this, "没有数据", Toast.LENGTH_SHORT).show();
            }

        }
    }
}
