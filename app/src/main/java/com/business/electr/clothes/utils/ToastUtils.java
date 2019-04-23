package com.business.electr.clothes.utils;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.NinePatchDrawable;
import android.os.Build;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.business.electr.clothes.R;

import androidx.annotation.CheckResult;
import androidx.annotation.ColorInt;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;

import static androidx.core.view.ViewCompat.setBackground;

public class ToastUtils {

    private static Toast currentToast;
    private static final String TOAST_TYPEFACE = "sans-serif-condensed";
    @ColorInt
    private static final int DEFAULT_TEXT_COLOR = Color.parseColor("#FFFFFF");
    @ColorInt
    private static final int BG_COLOR = Color.parseColor("#E6353535");

    /**
     * 显示居中弹出的toast
     *
     * @param context 上下文
     * @param msg     消息内容
     */
    public static void showToastCenter(Context context, String msg) {
        custom(context, msg, null, DEFAULT_TEXT_COLOR, BG_COLOR, Toast.LENGTH_SHORT, false, true, true).show();
    }

    /**
     * 显示toast
     *
     * @param context 上下文
     * @param msg     消息内容
     */
    public static void showToast(Context context, String msg) {
        custom(context, msg, null, DEFAULT_TEXT_COLOR, BG_COLOR, Toast.LENGTH_SHORT, false, true, false).show();
    }

    /**
     * 显示长时间显示的toast
     *
     * @param context 上下文
     * @param msg     消息内容
     */
    public static void showToastLong(Context context, String msg) {
        custom(context, msg, null, DEFAULT_TEXT_COLOR, BG_COLOR, Toast.LENGTH_LONG, false, true, false).show();
    }

    /**
     * @param context    上下文
     * @param message    消息内容
     * @param icon       图标
     * @param textColor  字体颜色
     * @param tintColor  背景颜色
     * @param duration   显示时长
     * @param withIcon   是否需要图标
     * @param shouldTint 是否需要背景色
     * @param isCenter   是否需要居中
     * @return
     */
    @CheckResult
    public static Toast custom(@NonNull Context context,
                               @NonNull String message,
                               Drawable icon,
                               @ColorInt int textColor,
                               @ColorInt int tintColor,
                               int duration,
                               boolean withIcon,
                               boolean shouldTint,
                               boolean isCenter) {
        if (currentToast == null) {
            currentToast = new Toast(context);
        }
        final View toastLayout = ((LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.toast_layout, null);
        final ImageView toastIcon = toastLayout.findViewById(R.id.toast_icon);
        final TextView toastTextView = toastLayout.findViewById(R.id.toast_text);
        Drawable drawableFrame;

        if (shouldTint) {
            drawableFrame = tint9PatchDrawableFrame(context, tintColor);
        } else {
            drawableFrame = getDrawable(context, R.drawable.toast_frame);
        }
        setBackground(toastLayout, drawableFrame);

        if (withIcon) {
            if (icon == null)
                throw new IllegalArgumentException("Avoid passing 'icon' as null if 'withIcon' is set to true");
            setBackground(toastIcon, icon);
        } else
            toastIcon.setVisibility(View.GONE);

        toastTextView.setTextColor(textColor);
        toastTextView.setText(message);
        toastTextView.setTypeface(Typeface.create(TOAST_TYPEFACE, Typeface.NORMAL));

        currentToast.setView(toastLayout);
        currentToast.setDuration(duration);
        if (isCenter) {
            currentToast.setGravity(Gravity.CENTER, 0, 0);
        }
        return currentToast;
    }

    private static Drawable tint9PatchDrawableFrame(@NonNull Context context, @ColorInt int tintColor) {
        final NinePatchDrawable toastDrawable = (NinePatchDrawable) getDrawable(context, R.drawable.toast_frame);
        toastDrawable.setColorFilter(new PorterDuffColorFilter(tintColor, PorterDuff.Mode.SRC_IN));
        return toastDrawable;
    }

    public static Drawable getDrawable(@NonNull Context context, @DrawableRes int id) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            return context.getDrawable(id);
        else
            return context.getResources().getDrawable(id);
    }

}
