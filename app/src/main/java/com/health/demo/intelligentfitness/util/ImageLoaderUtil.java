package com.health.demo.intelligentfitness.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;


import com.health.demo.intelligentfitness.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.nostra13.universalimageloader.core.display.SimpleBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

/**
 * Created by play on 2016/1/6.
 */
public class ImageLoaderUtil {
    private static final String TAG = "ImageLoaderUtil";

    /**
     * 默认配置
     *
     * @param defaultId
     * @return
     */
    public static DisplayImageOptions getImageOptionDefault(int defaultId) {
        DisplayImageOptions options =
                new DisplayImageOptions.Builder().showImageOnLoading(defaultId)
                        .showImageForEmptyUri(defaultId)
                        .showImageOnFail(defaultId)
                        .cacheInMemory(true)
                        .cacheOnDisk(true)
                        .considerExifParams(true)
                        .displayer(new SimpleBitmapDisplayer())
                        .resetViewBeforeLoading(true)
                        .bitmapConfig(Bitmap.Config.RGB_565)
                        .imageScaleType(ImageScaleType.EXACTLY)
                        .build();
        return options;
    }

    /**
     * 默认配置
     *
     * @return
     */
    public static DisplayImageOptions getImageOptionDefault() {
        DisplayImageOptions options =
                new DisplayImageOptions.Builder()
                        .cacheInMemory(true)
                        .cacheOnDisk(true)
                        .considerExifParams(true)
                        .displayer(new SimpleBitmapDisplayer())
                        .resetViewBeforeLoading(true)
                        .bitmapConfig(Bitmap.Config.RGB_565)
                        .imageScaleType(ImageScaleType.EXACTLY)
                        .build();
        return options;
    }

    /**
     * 使用自定义圆形图像配置
     *
     * @return
     */
    public static DisplayImageOptions getCircleOptions() {

        DisplayImageOptions circleOptions = new DisplayImageOptions.Builder()
                /*.showImageForEmptyUri(R.drawable.ic_empty)
                .showImageOnFail(R.drawable.ic_error)*/
                .resetViewBeforeLoading(true)
                .cacheOnDisk(true)
                .imageScaleType(ImageScaleType.EXACTLY)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .displayer(new CircleDisplayer())

                .build();

        return circleOptions;
    }


    /**
     * 设置图片的圆角
     *
     * @param defaultId
     * @param cacheOnDisk
     * @param cacheInMemory
     * @param radius
     * @return
     */
    public static DisplayImageOptions getImageOptionRounded(int defaultId, boolean cacheOnDisk, boolean cacheInMemory, int radius) {
        DisplayImageOptions options = new DisplayImageOptions.Builder().showImageOnLoading(defaultId)
                .showImageForEmptyUri(defaultId) // 设置图片Uri为空或是错误的时候显示的图片
                .showImageOnFail(defaultId)     // 设置图片加载或解码过程中发生错误显示的图片
                .cacheInMemory(cacheInMemory)  // 设置下载的图片是否缓存在内存中
                .cacheOnDisk(cacheOnDisk)     // 设置下载的图片是否缓存在SD卡中
                .displayer(new RoundedBitmapDisplayer(radius)) // 设置图片的圆角
                .bitmapConfig(Bitmap.Config.RGB_565)
                .build();

        return options;
    }


    public static void displayImage(String url, ImageView img) {
//        DisplayImageOptions imgOption;
//        imgOption = ImageLoaderUtil.getImageOptionDefault();
//        ImageLoader.getInstance().displayImage(url, img, imgOption);
        displayImage(url, img, null);

    }

    public static void displayImage(String url, ImageView img, ImageLoadingListener listener) {
        DisplayImageOptions imgOption;
        imgOption = ImageLoaderUtil.getImageOptionDefault();
        if (imgOption != null) {
            ImageLoader.getInstance().displayImage(url, img, imgOption, listener);
        }

    }

    public static void displayImage(String url, ImageView img, int def) {
        DisplayImageOptions imgOption;

        imgOption = ImageLoaderUtil.getImageOptionDefault(def);
        if (imgOption != null) {
            ImageLoader.getInstance().displayImage(url, img, imgOption);
        }

    }

    public static void displayImage(String url, ImageView img, int def, int radius) {
        DisplayImageOptions imgOption;
        imgOption = ImageLoaderUtil.getImageOptionRounded(def, true, true, radius);
        if (imgOption != null) {
            ImageLoader.getInstance().displayImage(url, img, imgOption);
        }
    }

    public static void displayImage(String url, ImageView img, int def, ImageLoadingListener listener) {
        DisplayImageOptions imgOption;
        imgOption = ImageLoaderUtil.getImageOptionDefault(def);
        if (imgOption != null) {
            ImageLoader.getInstance().displayImage(url, img, imgOption, listener);
        }

    }

    /**
     * 缓存的圆形图片
     *
     * @param url
     * @param imgHead
     * @param def
     */
    public static void displayImageCircleCache(String url, ImageView imgHead, int def) {
        DisplayImageOptions imgOption = ImageLoaderUtil.getImageOptionRounded(def, true, true, 200);
        if (imgOption != null) {
            ImageLoader.getInstance().displayImage(url, imgHead, imgOption);

        }

    }


    /**
     * 自定义没有缓存的圆形图片
     *
     * @param url
     * @param imageView
     */
    public static void displayImageCircle(String url, ImageView imageView) {
        DisplayImageOptions imgOption = ImageLoaderUtil.getCircleOptions();
        ImageLoader.getInstance().displayImage(url, imageView, imgOption);
    }

    /**
     * 有缓存的圆角图形
     *
     * @param url
     * @param imageView
     */
    public static void displayImageCornerRound(String url, ImageView imageView, int radius) {
        DisplayImageOptions imgOption =
                ImageLoaderUtil.getImageOptionRounded(R.mipmap.game_fail, true, true, radius);
        ImageLoader.getInstance().displayImage(url, imageView, imgOption);
    }

    /**
     * 设置特定宽和高的图片（通过Uri）
     *
     * @param url
     * @param imageView
     */
    public static void displayImageWidthHeight(String url, ImageView imageView, int width, int height) {
        if (null != url) {
            try {
                // 主动回收内存
                // System.gc();
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inJustDecodeBounds = true;

                BitmapFactory.decodeFile(url, options);
                int scalWidth = options.outWidth / width;
                int scalHeight = options.outHeight / height;
                if (scalWidth <= 0) {
                    scalWidth = 1;
                }
                if (scalHeight <= 0) {
                    scalHeight = 1;
                }
                options.inSampleSize = scalWidth;
                options.inJustDecodeBounds = false;

//                return BitmapFactory.decodeFile(url, options);
            } catch (Exception e) {

                e.printStackTrace();
            }
        } else {
        }
    }


}
