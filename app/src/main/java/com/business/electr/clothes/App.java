package com.business.electr.clothes;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Build;

import com.bumptech.glide.Glide;
import com.business.electr.clothes.ui.activity.BaseActivity;
import com.business.electr.clothes.utils.FontsOverride;
import com.business.electr.clothes.utils.MLog;
import com.langlang.data.LanglangUserInfo;
import com.langlang.operation.LanglangCallback;
import com.langlang.operation.LanglangInitCallback;
import com.langlang.operation.LanglangSDKUtils;
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

import org.json.JSONArray;

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
        initLangLangSDK();
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


    /**
     * 初始化郎朗sdk
     */
    private void initLangLangSDK() {
        LanglangSDKUtils.getInstance().setContext(getApplicationContext(), "jjkiAK5QvwJ", "85eb1a03e1bbad6df2ebc883b751f11c", new LanglangInitCallback() {
            @Override
            public void initResult(boolean result, String msg) {
                MLog.e("====zhq====>1111<" + result + "====>" + msg);
                if (result) {
                    LanglangSDKUtils.getInstance().setMac("00:23:03:55:00:06"); // 传入设备
                    LanglangSDKUtils.getInstance().setUserInfo(new LanglangUserInfo("4646", 0, 55, 170, "1975-06-03", "zhaoxc")); //传入用户
                    LanglangSDKUtils.getInstance().init(new LanglangCallback() {

                        @Override
                        public void getOrigEcgResult(int[] ecg) {
                            System.out.println("received origecg");
                        }

                        @Override
                        public void getVoltage(float vol) {

                            System.out.println("received vol : " + vol);
                        }

                        @Override
                        public void getRssi(int rssi) {
                            System.out.println("received rssi");

                        }

                        @Override
                        public void getBleState(int state) {

                            System.out.println("received blestate");
                        }

                        @Override
                        public void getCalories(int cal) {

                            System.out.println("received calories");
                        }

                        @Override
                        public void getStep(int step) {

                            System.out.println("received step");
                        }

                        @Override
                        public void getAccel(int[] xyz) {

                            System.out.println("received accel");
                        }

                        @Override
                        public void getFilterEcg(int[] ecg) {

                            System.out.println("received filterecg");
                        }

                        @Override
                        public void getUploadPath(String path) {

                            System.out.println("received uploadpath");
                        }

                        @Override
                        public void getPnn50(float pnn50, String stress) {

                            System.out.println("received pnn50");
                        }

                        @Override
                        public void getHr(int hr) {

                            System.out.println("received hr");
                        }

                        @Override
                        public void getPos(String pos) {

                            System.out.println("received pos");
                        }

                        @Override
                        public void getServResult(int heart, int maxHeart, int minHeart, String pnn50, String st, String pr, String qt, String qtc, JSONArray countArray) {

                            System.out.println("received servresult");
                        }
                    });
                } else {
                    //初始化失败 请确认APPID，SECRET
                    System.out.println("received fail");
                }
            }
        });
    }

}
