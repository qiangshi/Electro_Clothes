package com.business.electr.clothes.utils;


import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.request.RequestOptions;
import com.business.electr.clothes.R;
import com.business.electr.clothes.view.GlideRoundTransform;

/**
 * Created by zenghaiqiang on 2019/1/21.
 * 图片加载工具类
 */
public class GlidUtils {


    //普通的图片
    public static void setGrid(Context context, String url, ImageView view) {
        GlideApp
                .with(context)
                .load(url)
                .placeholder(R.drawable.ic_test_avatar)
                .error(R.drawable.ic_test_avatar)
                .into(view);
    }

    //普通的图片
    public static void setGrid(Context context, int url, ImageView view) {
        GlideApp
                .with(context)
                .load(url)
                .placeholder(R.drawable.ic_test_avatar)
                .error(R.drawable.ic_test_avatar)
                .into(view);
    }

    /**
     * 设置圆角图片
     * @param context
     * @param url
     * @param view
     * @param round dp
     */
    public static void setRoundGrid(Context context, String url, ImageView view,int round) {
        GlideApp
                .with(context)
                .load(url)
                .error(R.drawable.ic_test_avatar)
                .apply(new RequestOptions().transform(new GlideRoundTransform(round)))
                .into(view);
    }

    //圆型图片
    public static void setCircleGrid(Context context, String url, ImageView view) {
        GlideApp
                .with(context)
                .load(url)
                .error(R.drawable.icon_photo)
                .apply(new RequestOptions().transform(new GlideRoundTransform(view.getWidth()/2)))
                .into(view);
    }


}


