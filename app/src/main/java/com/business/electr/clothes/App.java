package com.business.electr.clothes;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Build;

import com.bumptech.glide.Glide;
import com.business.electr.clothes.ui.activity.BaseActivity;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


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
        initTypeface();
        activities = new ArrayList<>();
    }

    /**
     * 初始化默认字体
     */
    private void initTypeface() {
//        replaceFont(this, "SERIF", "fonts/dinpro_medium.ttf"); // OK,需要与<item name="android:typeface">serif</item> 的值对应
//        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
//                                    .setDefaultFontPath("fonts/dinpro_medium.ttf")
//                                    .setFontAttrId(R.attr.fontPath)
//                                    .build());
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


    /**
     * 替换字体，其本质是将系统底层的字体变量进行替换自己的字体引用 ,
     * 只会替换控件中没有自定义字体的控件，已自定义的就是使用的是自定义的字体
     *
     * @param context
     * @param oldFontName           　支持的名称有 MONOSPACE、SERIF，NORMAL（程序无法运行）、SANS与DEFAULT和DEFAULT_BOLD与SANS_SERIF（可以运行但是显示字体没有修改成功）
     *                              而且需要与 需要与AndroidManifest文件application节点的android:theme引用的styles文件中
     *                              <item name="android:typeface">monospace</item> 的值对应
     * @param newFontNameFromAssets 新的字体路径，必须要放在assets文件夹下，如：fonts/Nsimsun.ttf
     */
    public static void replaceFont(Context context, String oldFontName, String newFontNameFromAssets) {
        Typeface newTypeface = Typeface.createFromAsset(context.getAssets(), newFontNameFromAssets);
        try {
            //android 5.0及以上我们反射修改Typeface.sSystemFontMap变量
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                Map<String, Typeface> newMap = new HashMap<>();
                newMap.put(oldFontName, newTypeface);
                final Field staticField = Typeface.class.getDeclaredField("sSystemFontMap");
                staticField.setAccessible(true);
                staticField.set(null, newMap);
            } else {
                final Field staticField = Typeface.class.getDeclaredField(oldFontName);
                staticField.setAccessible(true);
                staticField.set(null, newTypeface);
            }
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
