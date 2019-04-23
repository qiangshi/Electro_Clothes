package com.business.electr.clothes.net.exception;

import org.apache.http.conn.ConnectTimeoutException;
import org.json.JSONException;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.text.ParseException;

import javax.net.ssl.SSLHandshakeException;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;
import retrofit2.HttpException;

public class ExceptionConvert<E> implements Function<Throwable, ObservableSource<E>> {
    @Override
    public ObservableSource<E> apply(Throwable throwable) {
        return Observable.error(handler(throwable));
    }

    private ResponseException handler(Throwable e) {
        ResponseException exception;
        if (e instanceof ApiException) {
            exception = new ResponseException(e, ((ApiException) e).getErrorCode(), ((ApiException) e).getErrorMessage());
        } else if (e instanceof HttpException) {
            return new ResponseException(e, "$HTTP_ERROR:" + ((HttpException) e).code(), "网络连接错误");
        } else if (e instanceof JSONException
                || e instanceof ParseException) {
            return new ResponseException(e, "1001", "解析错误");
        } else if (e instanceof ConnectException) {
            return new ResponseException(e, "1002", "连接失败");
        } else if (e instanceof ConnectTimeoutException
                || e instanceof SocketTimeoutException) {
            return new ResponseException(e, "1003", "网络连接超时");
        } else if (e instanceof SSLHandshakeException) {
            return new ResponseException(e, "1004", "证书验证失败");
        } else {
            e.printStackTrace();
            return new ResponseException(e, "1005", "未知错误");
        }
        return exception;
    }
}
