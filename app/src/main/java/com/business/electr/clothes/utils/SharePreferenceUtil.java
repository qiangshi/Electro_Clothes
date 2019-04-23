package com.business.electr.clothes.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import com.business.electr.clothes.App;

/**
 * Created by zenghaiqiang on 2018/5/28.
 * 描述：SharePreference 工具类的封装
 */
public class SharePreferenceUtil {

    public static final String FILE_NAME = "share_data";

    public static void putString(String key, String value) {
        Editor editor = getEditor();
        editor.putString(key, value);
        editor.commit();
    }

    public static String getString(String key, String defaultValue) {
        SharedPreferences sharedPreferences = getSharedPreference();
        return sharedPreferences.getString(key, defaultValue);
    }

    public static void putInt(String key, int value) {
        Editor editor = getEditor();
        editor.putInt(key, value);
        editor.commit();
    }

    public static int getInt(String key, int defaultInt) {
        return getSharedPreference().getInt(key, defaultInt);
    }

    public static void putLong(String key, long value) {
        Editor editor = getEditor();
        editor.putLong(key, value);
        editor.commit();
    }

    public static long getLong(String key, long defaultLong) {
        return getSharedPreference().getLong(key, defaultLong);
    }

    public static void putBoolean(String key, boolean value) {
        Editor editor = getEditor();
        editor.putBoolean(key, value);
        editor.commit();
    }

    public static boolean getBoolean(String key, boolean defaultBoolean) {
        return getSharedPreference().getBoolean(key, defaultBoolean);
    }

    public static boolean contains(Context context, String key) {
        SharedPreferences sp = context.getSharedPreferences(FILE_NAME,
                Context.MODE_PRIVATE);
        return sp.contains(key);
    }

    private static SharedPreferences getSharedPreference() {
        return App.getApp().getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
    }

    private static Editor getEditor() {
        return getSharedPreference().edit();
    }
}
