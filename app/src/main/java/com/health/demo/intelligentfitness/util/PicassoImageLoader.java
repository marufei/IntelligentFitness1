package com.health.demo.intelligentfitness.util;

import android.app.Activity;
import android.net.Uri;
import android.widget.ImageView;

import com.health.demo.intelligentfitness.R;
import com.lzy.imagepicker.loader.ImageLoader;
import com.squareup.picasso.Picasso;

import java.io.File;

/**
 * Created by marufei
 * time on 2017/5/3
 */

public class PicassoImageLoader implements ImageLoader {

    @Override
    public void displayImage(Activity activity, String path, ImageView imageView, int width, int height) {
        Picasso.with(activity)//
                .load(Uri.fromFile(new File(path)))//
                .placeholder(R.mipmap.default_image)//
                .error(R.mipmap.default_image)//
                .resize(width, height)//
                .centerInside()//
//                .memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE)//
                .into(imageView);
    }

    @Override
    public void clearMemoryCache() {
        //这里是清除缓存的方法,根据需要自己实现
    }
}
