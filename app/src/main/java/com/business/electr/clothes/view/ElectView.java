package com.business.electr.clothes.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import androidx.annotation.Nullable;

/**
 * @Description: java类作用描述
 * @Author: 曾海强
 * @CreateDate: 2019/4/30 17:01
 */
public class ElectView extends View {

    private Paint linePaint;//背景线的画笔
    private Paint electPaint;//心电图的画笔
    private Path electPath;//心电图轨迹画笔
    private int backgroubColor = Color.parseColor("#20979797");//背景线的颜色
    private int electColor = Color.parseColor("#23c688");//心电图的颜色
    private int verticalLineNum = 31;  //纵向的线数量
    private int horizontalLineNum = 7;//横向的线数量

    public ElectView(Context context) {
        super(context);
        init();
    }

    public ElectView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ElectView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @SuppressLint("HandlerLeak")
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

        }
    };


    private void init() {
        linePaint = new Paint();
        linePaint.setColor(backgroubColor);
        linePaint.setStyle(Paint.Style.STROKE);

        electPaint = new Paint();
        electPaint.setColor(electColor);
        electPaint.setStyle(Paint.Style.STROKE);
        electPaint.setStrokeWidth(5);

        electPath = new Path();
    }

    private int width, height;
    private int smallWidth, smallHeight;
    private int baseLine;//基准线
    private int maxLevel;//最大高度
    private boolean isDrawGird = true;

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = w;
        height = h;
        smallWidth = width / verticalLineNum;
        smallHeight = height / horizontalLineNum;
        baseLine = height / 2;
        maxLevel = height / 3;
        setData();
    }

    private void setData() {
        generateElectrocar();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (isDrawGird) {
            drawGrid(canvas);
        }
        drawElectro(canvas);

    }

    private void drawElectro(Canvas canvas) {
        canvas.drawPath(electPath,electPaint);
        electPath.moveTo(0,baseLine - datas.get(0));
        for (int i = 0; i < electDatas.size(); i++) {
            float y = baseLine - electDatas.get(i);
            electPath.lineTo(i*smallWidth,y);
        }
        canvas.drawPath(electPath,electPaint);
    }

    private void drawGrid(Canvas canvas) {
        for (int i = 0; i < verticalLineNum+1; i++) {
            canvas.drawLine(i * smallWidth, 0, i * smallWidth, height, linePaint);
        }
        for (int i = 0; i < horizontalLineNum+1; i++) {
            canvas.drawLine(0, i * smallHeight, width, i * smallHeight, linePaint);
        }
    }


    private List<Float> datas = new ArrayList<>();
    private List<Float> electDatas = new ArrayList<>();
    public void generateElectrocar(){
        for (int i = 0; i < 6; i++) {
            datas.add(0f);
        }
        for (int i = 0; i < 20; i++) {
            double random;
            if(i % 2 ==0){
                random = Math.random();
            }else {
                random = -Math.random();
            }
            float value = (float) (maxLevel * random);
            datas.add(value);
        }
        for (int i = 0; i < 9; i++) {
            datas.add(0f);
        }
    }

    private int index;
    public void addData(){
        if(datas.size() > 0){
            electDatas.add(datas.get(index));
            index++;
            if(index > datas.size() -1){
                index =0;
                electDatas.clear();
                electPath.reset();
                datas.clear();
                generateElectrocar();
            }
            invalidate();
        }
    }


    Runnable runnable;
    public void startDarw(){
        runnable = new Runnable() {
            @Override
            public void run() {
                addData();
                handler.postDelayed(runnable,100);
            }
        };
        handler.post(runnable);
    }
}
