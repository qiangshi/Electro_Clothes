package com.business.electr.clothes.ui.activity;


import android.content.Intent;
import android.net.http.SslError;
import android.os.Build;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.business.electr.clothes.R;
import com.business.electr.clothes.constants.Constant;
import com.business.electr.clothes.mvp.presenter.mine.WebViewPresenter;
import com.business.electr.clothes.mvp.view.mine.IWebView;
import com.business.electr.clothes.router.RouterCons;
import com.sankuai.waimai.router.annotation.RouterUri;

import butterknife.BindView;

/**
 * Created by pantianxiong on 2018/5/9.
 * 描述：
 */
@RouterUri(path = {RouterCons.WEB_VIEW_INFO})
public class WebViewContainerActivity extends BaseActivity<WebViewPresenter> implements IWebView {

    @BindView(R.id.web_view)
    WebView mWebView;
    @BindView(R.id.tv_right_btn)
    TextView tvRightBtn;
    private String mUrl;
    @BindView(R.id.root_container)
    RelativeLayout pdfRoot;

    public static final String TITLE = "title";


    @Override
    protected int getLayoutId() {
        return R.layout.activity_webview_container;
    }

    @Override
    protected WebViewPresenter getPresenter() {
        return new WebViewPresenter(this);
    }

    @Override
    protected void initDataAndEvent() {
        String title = getIntent().getStringExtra(TITLE);
        if (!TextUtils.isEmpty(title)) {
            initTitle(title);
        }
        Intent intent = getIntent();
        mUrl = intent.getStringExtra(Constant.URL);

        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setSupportZoom(true);
        webSettings.setAllowFileAccess(true);
        webSettings.setTextZoom(100);
        webSettings.setAllowUniversalAccessFromFileURLs(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setBlockNetworkImage(false);//解决图片不显示
        webSettings.setBuiltInZoomControls(true);//support zoom
        webSettings.setDefaultTextEncodingName("UTF-8");

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            webSettings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }

        if (!TextUtils.isEmpty(mUrl)) {
            mWebView.loadUrl(mUrl.trim());
            // 如果是本地的web页面，则先隐藏，加载完毕再显示
            mWebView.setVisibility(View.INVISIBLE);
            showLoading();
        }
        //添加客户端支持
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return super.shouldOverrideUrlLoading(view, url);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                cancelLoadingFragment();
                if (url.equals(mUrl)) {
                    mWebView.setVisibility(View.VISIBLE);
                }
                super.onPageFinished(view, url);
            }

            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                handler.proceed();
            }
        });
        mWebView.setWebChromeClient(mWebChromeClient);
        mWebView.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    if (keyCode == KeyEvent.KEYCODE_BACK && mWebView.canGoBack()) { // 表示按返回键
                        if (mWebView.canGoBack()) {
                            mWebView.goBack();
                        } else {
                            finish();
                        }
                        return true;
                    }
                }
                return false;
            }
        });
    }


    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        /*
         * try catch 来捕获 viewpager 多点触控导致的 exception
         */
        try {
            return super.onTouchEvent(ev);
        } catch (IllegalArgumentException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        /*
         * try catch 来捕获 viewpager 多点触控导致的 exception
         */
        try {
            return super.dispatchTouchEvent(ev);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // 发送通用字段
    private WebChromeClient mWebChromeClient = new WebChromeClient() {
        // 处理javascript中的alert
        public boolean onJsAlert(WebView view, String url, String message, final JsResult result) {
            return super.onJsAlert(view, url, message, result);
        }

        // 处理javascript中的confirm
        public boolean onJsConfirm(WebView view, String url, String message, final JsResult result) {
            return super.onJsConfirm(view, url, message, result);
        }

        // 处理javascript中的prompt
        public boolean onJsPrompt(WebView view, String url, String message, String defaultValue, final JsPromptResult result) {
            return super.onJsPrompt(view, url, message, defaultValue, result);
        }

        // 设置网页加载的进度条
        public void onProgressChanged(WebView view, int newProgress) {
            if (newProgress > 90) cancelLoadingFragment();
            super.onProgressChanged(view, newProgress);
        }

        // 设置程序的Title
        public void onReceivedTitle(WebView view, String title) {
            super.onReceivedTitle(view, title);
        }
    };

    @Override
    protected void onDestroy() {
        releaseWebViews();
        super.onDestroy();
    }

    /**
     * 释放 webview 资源
     */
    public synchronized void releaseWebViews() {
        if (mWebView != null) {
            try {
                if (mWebView.getParent() != null) {
                    ((ViewGroup) mWebView.getParent()).removeView(mWebView);
                }
                mWebView.destroy();
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
            mWebView = null;
        }
    }

//    private JSONObject getBaseJSONObject() {
//        JSONObject json = new JSONObject();
//        json.put("token", DataCacheManager.getToken());
//        json.put("deviceId", AnyinfoApplicationLike.self().getDeviceId());
//        json.put("language", AppStateUtils.getCurrentLanguage());
//        json.put("version", Util.getVersionName());
//        json.put("osType", "1");
//        json.put("host", BuildConfig.HOST + "/app/");
//        return json;
//    }
}
