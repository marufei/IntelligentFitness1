package com.health.demo.intelligentfitness;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.LinearLayout;

import com.health.demo.intelligentfitness.util.MyUtils;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class WebActivity extends BaseActivity {

    @InjectView(R.id.tl_custom)
    Toolbar tlCustom;
    @InjectView(R.id.wv_show)
    WebView wvShow;
    @InjectView(R.id.activity_web)
    LinearLayout activityWeb;
    private String urlShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        ButterKnife.inject(this);
        initToolbar("资讯详情");
        Intent intent = getIntent();
        urlShow = intent.getStringExtra("url");
        if (!TextUtils.isEmpty(urlShow)) {
            initData();
        }
    }

    private void initData() {

        MyUtils.Loge("aaa", "改后----urlShow::" + urlShow);
        WebSettings webSettings = wvShow.getSettings();
        //设置WebView属性，能够执行Javascript脚本
        webSettings.setJavaScriptEnabled(true);

        //设置可以访问文件
        webSettings.setAllowFileAccess(true);
        //设置支持缩放
        webSettings.setBuiltInZoomControls(true);//WebView中包含一个ZoomButtonsController，当使用web.getSettings().setBuiltInZoomControls(true);启用后，用户一旦触摸屏幕，就会出现缩放控制图标。
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);//设置加载进来的页面自适应手机屏幕
        webSettings.setAllowFileAccess(true);
        webSettings.setDefaultTextEncodingName("UTF-8");
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setUseWideViewPort(true);
        webSettings.setDomStorageEnabled(true);//启用Dom内存（不加就显示不出来）
        wvShow.setWebChromeClient(new WebChromeClient());
//        加载需要显示的网页
        wvShow.loadUrl(urlShow);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        wvShow.setVisibility(View.GONE);//ZoomButtonsController有一个register和unregister的过程
    }
}
