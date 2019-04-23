package com.business.electr.clothes.manager;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.business.electr.clothes.App;
import com.business.electr.clothes.bean.LoginBean;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Created by pantianxiong on 2018/4/27.
 * 描述：android SharedPreferences缓存管理
 */
@SuppressWarnings("unchecked")
public class DataCacheManager {
    /**
     * 用户信息缓存
     */
    private static final String USER_INFO = "user";
    private static final String TOKEN = "token";

    private static SharedPreferences getSharedPreferences(String spName) {
        return App.getApp().getSharedPreferences(spName, Context.MODE_PRIVATE);
    }

    public static void saveUserInfo(LoginBean.UserBean userInfo) {
        String str = null;
        try {
            str = serializeObject(userInfo);
        } catch (IOException e) {
            e.printStackTrace();
        }
        SharedPreferences.Editor editor = getSharedPreferences(USER_INFO).edit();
        editor.putString(USER_INFO, str);
        editor.apply();
    }

    public static LoginBean.UserBean getUserInfo() {
        SharedPreferences sp = getSharedPreferences(USER_INFO);
        String str = sp.getString(USER_INFO, "");
        if (TextUtils.isEmpty(str)) {
            return new LoginBean.UserBean();
        }
        return deserialize(str, LoginBean.UserBean.class);
    }



    public static void saveToken(String token) {
        SharedPreferences.Editor editor = getSharedPreferences(TOKEN).edit();
        editor.putString(TOKEN, token);
        editor.apply();
    }

    public static void removeToken() {
        SharedPreferences.Editor editor = getSharedPreferences(TOKEN).edit();
        editor.putString(TOKEN, "");
        editor.apply();
    }

    public static String getToken() {
        SharedPreferences sp = getSharedPreferences(TOKEN);
        return sp.getString(TOKEN, "");
    }


    /**
     * 序列化对象
     */
    private static String serializeObject(Object object) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(
                byteArrayOutputStream);
        objectOutputStream.writeObject(object);
        String serStr = byteArrayOutputStream.toString("ISO-8859-1");
        serStr = java.net.URLEncoder.encode(serStr, "UTF-8");
        objectOutputStream.close();
        byteArrayOutputStream.close();
        return serStr;
    }

    /**
     * 反序列化对象
     */
    private static <T> T deserialize(String str, Class<T> clazz) {
        try {
            String redStr = java.net.URLDecoder.decode(str, "UTF-8");
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(
                    redStr.getBytes("ISO-8859-1"));
            ObjectInputStream objectInputStream = new ObjectInputStream(
                    byteArrayInputStream);
            Object object = objectInputStream.readObject();
            objectInputStream.close();
            byteArrayInputStream.close();
            return (T) object;
        } catch (IOException | ClassNotFoundException e) {
            return null;
        }
    }
}
