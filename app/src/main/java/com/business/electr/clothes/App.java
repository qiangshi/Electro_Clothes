package com.business.electr.clothes;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Build;

import com.bumptech.glide.Glide;
import com.business.electr.clothes.ui.activity.BaseActivity;
import com.business.electr.clothes.utils.FontsOverride;
import com.sankuai.waimai.router.Router;
import com.sankuai.waimai.router.common.DefaultRootUriHandler;
import com.sankuai.waimai.router.core.RootUriHandler;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.DefaultRefreshFooterCreator;
import com.scwang.smartrefresh.layout.api.DefaultRefreshHeaderCreator;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.simple.spiderman.SpiderMan;
import com.yuyh.library.imgsel.ISNav;
import com.yuyh.library.imgsel.common.ImageLoader;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.annotation.NonNull;


/**
 * Created by zenghaiqiang on 2019/1/11.
 */
public class App extends Application {

    private List<BaseActivity> activities;
    private static App app;
    private float latitude;
    private float longitude;
    private String curPosition;

    public static App getApp() {
        return app;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        init();
        initWMRouter();
        initISNav();
        activities = new ArrayList<>();
    }


    public List<BaseActivity> getActivities() {
        return activities;
    }

    private void init() {
        SpiderMan.init(this);
        app = this;
    }

    private void initWMRouter() {
        RootUriHandler rootHandler = new DefaultRootUriHandler(this);
        Router.init(rootHandler);
    }

    static {
        //设置全局的Header构建器
        SmartRefreshLayout.setDefaultRefreshHeaderCreator(new DefaultRefreshHeaderCreator() {
            @Override
            public RefreshHeader createRefreshHeader(Context context, RefreshLayout layout) {
                layout.setPrimaryColorsId(R.color.white, R.color.color_252631);//全局设置主题颜色
                return new ClassicsHeader(context);//.setTimeFormat(new DynamicTimeFormat("更新于 %s"));//指定为经典Header，默认是 贝塞尔雷达Header
            }
        });
        //设置全局的Footer构建器
        SmartRefreshLayout.setDefaultRefreshFooterCreator(new DefaultRefreshFooterCreator() {
            @Override
            public RefreshFooter createRefreshFooter(Context context, RefreshLayout layout) {
                //指定为经典Footer，默认是 BallPulseFooter
                return new ClassicsFooter(context).setDrawableSize(20);
            }
        });
    }

    // 退出应用
    public void quitApp() {
        for (Activity activity : activities) {
            activity.finish();
        }
    }

    /**
     * 初始化图片选择器
     */
    private void initISNav() {
        ISNav.getInstance().init((ImageLoader) (context, path, imageView) -> Glide.with(context).load(path).into(imageView));
    }


}
