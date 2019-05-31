package com.business.electr.clothes.net;

import com.business.electr.clothes.constants.Constant;
import com.business.electr.clothes.manager.DataCacheManager;
import com.business.electr.clothes.net.paramjson.ParamsBuilder;
import com.business.electr.clothes.utils.SharePreferenceUtil;

import java.util.concurrent.TimeUnit;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

public class ApiClient {

    private final static String REQUEST_TOKEN = "token";
    public static final String BASE_URL = "http://47.102.145.7:8003/";


    public static ApiClient getInstance() {
        return Holder.Instance;
    }

    private static class Holder {

        private static final ApiClient Instance = new ApiClient();
    }


    /**
     * 链式添加参数
     */
    public ParamsBuilder getBuilder(){
        return new ParamsBuilder();
    }

    public static Retrofit retrofit() {
        return new Retrofit.Builder()
            .baseUrl(BASE_URL+"business/")
            .addConverterFactory(FastJsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(getClient())
            .build();
    }

    public static OkHttpClient getClient() {
        //声明日志类
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        //设定日志级别
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        try {
            return new OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)

                .addInterceptor(
                    chain -> {
                        Request original = chain.request();
                        Request.Builder requestBuilder = original.newBuilder()
                                .header(REQUEST_TOKEN, DataCacheManager.getToken());
                        Request request = requestBuilder.build();
                        return chain.proceed(request);
                    }
                )
                .addInterceptor(httpLoggingInterceptor)
                .build();
        } catch (Exception e) {
            return new OkHttpClient.Builder().build();
        }
    }
}
