package com.business.electr.clothes.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.business.electr.clothes.R;
import com.business.electr.clothes.utils.MLog;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

/**
 * @ClassName: HorizontalProcessor
 * @Description: java类作用描述
 * @Author: 曾海强
 * @CreateDate: 2019/5/25 21:02
 */
public class HorizontalProcessor extends View {

    private int mShowType;
    private Paint paint;
    private int width, height;
    private int total = 8;//设置的总数
    private float process = 0.5f;
    private int number = 16;//每一个梯度的大小  0.5小时

    public HorizontalProcessor(Context context) {
        this(context, null);
    }

    public HorizontalProcessor(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public HorizontalProcessor(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //获取自定义属性的值
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.CustomDayTaskView, defStyleAttr, 0);
        mShowType = a.getInteger(R.styleable.CustomDayTaskView_mShowType, 2);
        a.recycle();
        init();
    }


    private void init() {
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(getResources().getColor(R.color.color_353535));
        paint.setStrokeWidth(2);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = w;
        height = h;
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawText(canvas);
        drawProgress(canvas);
    }


    private void drawText(Canvas canvas) {
        canvas.save();
        changeProcess();
        paint.setTextSize(42);
        paint.setTextAlign(Paint.Align.LEFT);
        String hint = "",lastHint="";
        String showNum="";
        switch (mShowType){
            case 0:
                hint = "每天睡眠时间";
                lastHint = " 小时";
                total = 8;
                number = 50;
                showNum = String.valueOf((process * total));
                break;
            case 1:
                hint =  "每天采集心跳数";
                lastHint = " 跳";
                total = 50000;
                number = 50;
                showNum = String.valueOf((int)(process * total));
                break;
            case 2:
                hint = "每天步数";
                lastHint = " 步";
                total = 20000;
                number = 50;
                showNum = String.valueOf((int)(process * total));
                break;
        }
        canvas.drawText(hint, 0, 50, paint);
        paint.setTextAlign(Paint.Align.RIGHT);
        canvas.drawText(showNum + lastHint, getMeasuredWidth(), 50, paint);
        paint.setColor(getResources().getColor(R.color.color_44979797));
        paint.setStrokeWidth(6);
        canvas.drawLine(0, 150, getMeasuredWidth(), 150, paint);
    }


    private void drawProgress(Canvas canvas) {
        view_show_x = width * process;
        if(view_show_x < 0) view_show_x = 0;
        MLog.e("====zhq====>view_show_x<" + view_show_x);
        paint.setColor(getResources().getColor(R.color.color_353535));
        canvas.drawLine(0, 150, view_show_x, 150, paint);
        canvas.restore();
        canvas.save();
        canvas.translate(view_show_x, 150);
        Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.drawable.icon_task_progress);
        Rect rect = new Rect(0, bmp.getHeight(), bmp.getWidth(), 0);
        Rect rect1 = new Rect(-bmp.getWidth(), -bmp.getHeight() / 2, 0, bmp.getHeight() / 2);
        canvas.drawBitmap(bmp, rect, rect1, paint);
        canvas.restore();
        canvas.save();

    }

    /**
     * 改变进度值  根据进度值取离当前进度最近的可显示进度
     */
    private void changeProcess() {
        MLog.e("====zhq====>000000000000000<" + process);
        for (int i = 0; i < number; i++) {
            if (process > i * 1.0f / number && process < (i + 1) * 1.0f / number) {
                if (process - i * 1.0f / number <= 1 * 1.0f / number / 2) {
                    process = i * 1.0f / number;
                } else {
                    process = (i + 1) * 1.0f / number;
                }
                MLog.e("====zhq====>000000000000000<" + process);
                break;
            }
        }
        if(process < 0) process = 0;
        if(process > 1) process = 1;
        MLog.e("====zhq====>00000000111111111111<" + process);
    }


    private float view_show_x, x_change, startX;
    private boolean isStart;


    /**
     * 活动进度条
     *
     * @param event
     * @return
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = 0;
        float y = 0;
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                x = event.getX();
                y = event.getY();
                MLog.e("====zhq====>xxxxxxxxxx<" + x);
                MLog.e("====zhq====>yyyyyyyyyy<" + y);
                if (x > view_show_x - 150 && x < view_show_x + 50 && y > 80 && y < 220) {
                    isStart = true;
                    startX = x;
                }
                break;

            case MotionEvent.ACTION_MOVE:
                MLog.e("====zhq====>ACTION_MOVE<" + isStart);
                if (isStart && event.getY() < height && event.getX() < width && event.getX() > 64) {
                    x_change = event.getX() - startX;
                    MLog.e("====zhq====>ACTION_MOVE<" + x_change);
                    process = event.getX() / width;
                    invalidate();
                }
                break;
            case MotionEvent.ACTION_UP:
                isStart = false;
                break;
        }
        return true;

    }


    public float getSizeNumber(){
        return total*process;
    }
}
