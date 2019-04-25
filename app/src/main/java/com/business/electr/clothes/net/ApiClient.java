package com.business.electr.clothes.net;

import com.business.electr.clothes.net.paramjson.ParamsBuilder;
import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

public class ApiClient {

    private final static String REQUEST_TOKEN = "token";


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
            .baseUrl("http://47.102.145.7:8003/business/")
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
//                .addInterceptor(
//                    chain -> {
//                        Request srcRequest = chain.request();
//                        if (srcRequest.method().equals("POST")) {//post请求
//                            if (srcRequest.body() instanceof FormBody) {
//                                FormBody.Builder newBodyBuilder = new FormBody.Builder();
//                                FormBody formBody = (FormBody) srcRequest.body();
//                                for (int i = 0; i < formBody.size(); i++) {
//                                    newBodyBuilder.addEncoded(formBody.encodedName(i),
//                                        formBody.encodedValue(i));
//                                }
//                                if (SharePreferenceUtil.getBoolean(Constant.IS_LOGIN, false)) {
//                                    formBody = newBodyBuilder
//                                        .addEncoded(REQUEST_TOKEN, DataCacheManager.getToken())
////                                        .addEncoded(REQUEST_USER_ID,
////                                            DataCacheManager.getUserInfo().getId() + "")
//                                        .build();
//                                } else {
//                                    formBody = newBodyBuilder.build();
//                                }
//                                srcRequest = srcRequest.newBuilder().post(formBody).build();
//                            }
//                        }
//                        return chain.proceed(srcRequest);
//                    }
//                )
                .addInterceptor(httpLoggingInterceptor)
                .build();
        } catch (Exception e) {
            return new OkHttpClient.Builder().build();
        }
    }
}
