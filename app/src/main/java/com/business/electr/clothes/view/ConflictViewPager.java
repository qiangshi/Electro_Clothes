package com.business.electr.clothes.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

/**
 * Description: java类作用描述
 * Author: 曾海强
 * CreateDate: 2019/5/24 17:17
 */
public class ConflictViewPager extends ViewPager {
    public ConflictViewPager(@NonNull Context context) {
        super(context);
    }

    public ConflictViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public boolean onTouchEvent(MotionEvent arg0) {
        if(arg0.getY() > 900){
            return super.onTouchEvent(arg0);
        }else {
            return false;
        }
    }

    public boolean onInterceptTouchEvent(MotionEvent arg0) {
        if(arg0.getY() > 900){
            return super.onTouchEvent(arg0);
        }else {
            return false;
        }
    }
}
