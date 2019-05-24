package com.business.electr.clothes.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;

import com.business.electr.clothes.R;
import com.business.electr.clothes.utils.MLog;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

import androidx.annotation.Nullable;

/**
 * Description: java类作用描述
 * Author: 曾海强
 * CreateDate: 2019/5/24 10:32
 */
public class TodayTaskCoverView extends View {
    private Paint linePaint, squarePaint;
    private Path linePath;
    private int widthNum = 20;
    private int heightNum = 10;
    private float per_width;//每个方格的宽度
    private float per_height;//每个方格的高度
    private int width, height;
    private int lineColor = getResources().getColor(R.color.color_8c919b);//分割线的颜色
    private int squareColor = getResources().getColor(R.color.white);//方格的颜色

    private float randomRatio = 0;//图片显示比例

    public TodayTaskCoverView(Context context) {
        this(context, null);
    }

    public TodayTaskCoverView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TodayTaskCoverView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = w;
        height = h;
        per_width = w * 1.0f / widthNum;
        per_height = h * 1.0f / heightNum;
        MLog.e("====zhq====>width<" + width + "===>height<" + height);
        MLog.e("====zhq====>per_width<" + per_width + "===>per_height<" + per_height);
    }

    private void init() {
        this.setBackgroundColor(getResources().getColor(R.color.translucent));
        linePaint = new Paint();
        linePaint.setColor(lineColor);
        linePaint.setStyle(Paint.Style.STROKE);
        linePaint.setStrokeWidth(1);

        squarePaint = new Paint();
        squarePaint.setColor(squareColor);
        squarePaint.setStyle(Paint.Style.FILL);
        squarePaint.setStrokeWidth(5);

        linePath = new Path();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (false) {
            drawLine(canvas);
        }
        drawSquare(canvas);
    }


    /**
     * 绘制分割线
     *
     * @param canvas 画布
     */
    private void drawLine(Canvas canvas) {
        for (int i = 1; i < widthNum; i++) {
            canvas.drawLine(per_width * i, 0, per_width * i, height, linePaint);
        }

        for (int i = 0; i < heightNum; i++) {
            canvas.drawLine(0, per_height * i, width, per_height * i, linePaint);
        }
    }

    private void drawSquare(Canvas canvas) {
        int randomNum = (int) ((1 - randomRatio) * widthNum * heightNum);//方格的数量
        generaRandom(canvas,randomNum);
    }


    /**
     * 生成随机数
     */
    private void generaRandom(Canvas canvas,int randomNum) {
        Integer[] tempInt = new Integer[widthNum * heightNum];
        for (int i = 0; i < widthNum * heightNum; i++) {//初始化
            tempInt[i] = i;
        }

        for (int i = 0; i < randomNum; i++) {//生产random个不同的随机数
            Random random = new Random();
            int current = random.nextInt(widthNum * heightNum);
            while (tempInt[current] == -1) {
                current = random.nextInt(widthNum * heightNum);
            }
            tempInt[current] = -1;
            float x = (current % widthNum - 1) * per_width;
            float y = current / widthNum * per_height;
            MLog.e("====zhq====>current<"+current);
            if (current % widthNum == 0 && current / widthNum > 0) {
                x = (widthNum - 1) * per_width;
                y = (current / widthNum - 1) * per_height;
            } else if (current % widthNum == 0 && current / widthNum == 0) {
                x = y = 0;
            }
            canvas.drawRect(x + 1, y + 1, x - 1 + per_width, y - 1 + per_height, squarePaint);
            canvas.drawRect(x, y, x + per_width, y + per_height, linePaint);
        }
    }

//    /**
//     * 排序
//     */
//    private Comparator comparatorInteger = new Comparator<Integer>() {
//        public int compare(Integer o, Integer o1) {
//            int flag;
//            if (o > o1) flag = 1;
//            else flag = -1;
//            return flag;
//        }
//    };


    /**
     * 设置图片显示比例
     *
     * @param ratio 比例
     */
    public void setRatio(float ratio) {
        randomRatio = ratio;
        invalidate();
    }
}
