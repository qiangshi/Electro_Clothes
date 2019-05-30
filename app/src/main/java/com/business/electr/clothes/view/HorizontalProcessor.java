package com.business.electr.clothes.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import com.business.electr.clothes.R;
import androidx.annotation.Nullable;

/**
 * ClassName: HorizontalProcessor
 * Description: java类作用描述
 * Author: 曾海强
 * CreateDate: 2019/5/25 21:02
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
        String hint = "", lastHint = "";
        String showNum = "";
        switch (mShowType) {
            case 0:
                hint = "每天睡眠时间";
                lastHint = " 小时";
                total = 8;
                number = 16;
                showNum = String.valueOf((process * total));
                break;
            case 1:
                hint = "每天采集心跳数";
                lastHint = " 跳";
                total = 50000;
                number = 50;
                showNum = String.valueOf((int) (process * total));
                break;
            case 2:
                hint = "每天步数";
                lastHint = " 步";
                total = 20000;
                number = 50;
                showNum = String.valueOf((int) (process * total));
                break;
        }
        canvas.drawText(hint, 0, 50, paint);
        paint.setTextAlign(Paint.Align.RIGHT);
        canvas.drawText(showNum + lastHint, width, 50, paint);
        paint.setColor(getResources().getColor(R.color.color_44979797));
        paint.setStrokeWidth(6);
        canvas.drawLine(0, 150, width, 150, paint);
    }


    private void drawProgress(Canvas canvas) {
        view_show_x = (width - 64) * process;
        if (view_show_x < 0) view_show_x = 0;
        paint.setColor(getResources().getColor(R.color.color_353535));
        canvas.drawLine(0, 150, view_show_x, 150, paint);
        canvas.restore();
        canvas.save();
        canvas.translate(view_show_x, 150);
        Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.drawable.icon_task_progress);
        Rect rect = new Rect(0, bmp.getHeight(), bmp.getWidth(), 0);
        Rect rect1 = new Rect(0, -bmp.getHeight() / 2, bmp.getWidth(), bmp.getHeight() / 2);
        canvas.drawBitmap(bmp, rect, rect1, paint);
        canvas.restore();
        canvas.save();

    }

    /**
     * 改变进度值  根据进度值取离当前进度最近的可显示进度
     */
    private void changeProcess() {
        for (int i = 0; i < number; i++) {
            if (process > i * 1.0f / number && process < (i + 1) * 1.0f / number) {
                if (process - i * 1.0f / number <= 1 * 1.0f / number / 2) {
                    process = i * 1.0f / number;
                } else {
                    process = (i + 1) * 1.0f / number;
                }
                break;
            }
        }
        if (process < 0) process = 0;
        if (process > 1) process = 1;
    }


    private float view_show_x;
    private boolean isStart;


    /**
     * 活动进度条
     * param event
     * return
     */
    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x;
        float y;
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                x = event.getX();
                y = event.getY();
                if (x > view_show_x - 150 && x < view_show_x + 50 && y > 80 && y < 220) {
                    isStart = true;
                }
                break;

            case MotionEvent.ACTION_MOVE:
                if (isStart && event.getY() < height && event.getX() < width) {
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

    public void setCurrent(float current){
        process = current *1.0f / total;
        invalidate();
    }


    public float getSizeNumber() {
        return total * process;
    }
}
