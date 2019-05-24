package com.business.electr.clothes.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.graphics.RectF;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import com.business.electr.clothes.R;
import com.business.electr.clothes.utils.MLog;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

/**
 * @Description: java类作用描述
 * @Author: 曾海强
 * @CreateDate: 2019/4/30 17:01
 */
public class ElectView extends View {


    //    private TextView view;
    private Paint linePaint;//背景线的画笔
    private Paint electPaint;//心电图的画笔
    private Path linePath;
    private Path electPath;//心电图轨迹画笔
    private Paint pointPaint;//指针的画笔
    private int backgroubColor = getResources().getColor(R.color.color_44979797);//背景线的颜色
    private int electColor = getResources().getColor(R.color.color_23c688);//心电图的颜色
    private int horizontalLineNum = 10;//横向的线数量
    private int verticalLineNum;
    private int per_elect_num;//每个格子代表的心电数
    private int per_height;//每个格子的边长
    private int per_height_num;//每个格子的数据量
    private boolean isHavePoint = false;//是否有指示器
    private Map<Float, Float> xyMap;
    private int allTime = 24;//总共多少个小时
    private float multiple = 1;//播放倍数

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

    /**
     * 设置心电图的倍数
     * @param multiple
     */
    public void setMultiple(float multiple) {
        this.multiple = multiple;
        invalidate();
    }

    /**
     * 设置是否有指示器
     * @param havePoint
     */
    public void setHavePoint(boolean havePoint) {
        isHavePoint = havePoint;
    }

    /**
     * 设置是否有背景
     * @param drawGird
     */
    public void setDrawGird(boolean drawGird) {
        isDrawGird = drawGird;
    }

    /**
     * 设置总共展示的时间
     * @param allTime
     */
    public void setAllTime(int allTime) {
        this.allTime = allTime;
        invalidate();
    }

    private void init() {
        linePaint = new Paint();
        linePaint.setAntiAlias(true);
        linePaint.setColor(backgroubColor);
        linePaint.setStyle(Paint.Style.STROKE);
        PathEffect effects = new DashPathEffect(new float[]{6, 6}, 3);
        linePaint.setPathEffect(effects);

        electPaint = new Paint();
        electPaint.setColor(electColor);
        electPaint.setStyle(Paint.Style.STROKE);
        electPaint.setStrokeWidth(5);

        pointPaint = new Paint();
        pointPaint.setStrokeWidth(5);

        electPath = new Path();
        linePath = new Path();
        xyMap = new HashMap<>();
    }

    private int width, height;
    private float baseLine;//基准线
    private int maxLevel;//最大高度
    private boolean isDrawGird = true;


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = w;
        height = h;
        per_height = (height - 3) / horizontalLineNum;//减去11根线的宽度
        verticalLineNum = width / per_height;
        MLog.e(verticalLineNum + "====zhq====>width<" + width + "===>" + height + "===>" + per_height);
        baseLine = per_height * 5f;
        maxLevel = height / 3;
        per_elect_num = 30;
        per_height_num = 5;
        setData();
    }

    private void setData() {
        generateElectrocar();
    }

    private Canvas canvas;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.canvas = canvas;
        if (isDrawGird) {
            drawGrid(canvas);
        }
        drawElectro(canvas);
        if (isHavePoint) {
            startX = per_height * 2.0f;
            drawPointer(canvas, startX);
        }
    }

    private void drawElectro(Canvas canvas) {
        electPath.moveTo(0, baseLine - datas.get(0));
        for (int i = 0; i < electDatas.size(); i++) {
            float y = baseLine - electDatas.get(i);
            if(i == noElect){
                electPath.moveTo(i * per_height * 1.0f / per_height_num,y);
                continue;
            }
            xyMap.put(i * per_height * 1.0f / per_height_num, y);
            electPath.lineTo(i * per_height * 1.0f / per_height_num, y);
        }
        canvas.drawPath(electPath, electPaint);
    }

    private void drawGrid(Canvas canvas) {
        for (int i = 0; i < verticalLineNum + 1; i++) {
            linePath.reset();
            linePath.moveTo(i * per_height, 0);
            linePath.lineTo(i * per_height, height);
            canvas.drawPath(linePath, linePaint);
        }

        for (int i = 0; i < horizontalLineNum + 1; i++) {
            linePath.reset();
            linePath.moveTo(0, i * per_height);
            linePath.lineTo(width, i * per_height);
            canvas.drawPath(linePath, linePaint);
        }
    }


    private float view_show_x;//pointer显示的x坐标

    /**
     * 绘制指示器
     *
     * @param canvas
     */
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void drawPointer(Canvas canvas, Float x) {
        canvas.save();
        x += x_change;
        view_show_x = x;
        if(view_show_x < 0) {
            view_show_x =0;
            x = 0f;
        }
        if(view_show_x > width) {
            view_show_x = width;
            x = (float)width;
        }
        pointPaint.setColor(getResources().getColor(R.color.color_44979797));
        pointPaint.setStyle(Paint.Style.STROKE);
        canvas.drawLine(x, height, x, getValueByX_Time(x), pointPaint);
        canvas.restore();
        canvas.save();
        canvas.translate(x, height / 2);
        pointPaint.setColor(getResources().getColor(R.color.color_353535));
        pointPaint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(0, 0, 10, pointPaint);
        RectF f = new RectF(-65f, -70f, 65f, -20f);
        canvas.drawRoundRect(f, 4f, 4f, pointPaint);
        //绘制三角形形成指针
        Path path = new Path();
        path.moveTo(0, -15);
        path.lineTo(10, -20);
        path.lineTo(-10, -20);
        path.lineTo(0, -15);
        path.close();
        canvas.drawPath(path, pointPaint);
        pointPaint.setColor(getResources().getColor(R.color.white));
        pointPaint.setTextSize(33);
        pointPaint.setTextAlign(Paint.Align.CENTER);
        canvas.drawText(getTimeByX(x), 0, -35, pointPaint);
    }


    float startX;
    float x_change;
    private boolean isStart;

    /**
     * 滑动心电图展示时间
     *
     * @param event
     * @return
     */
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if (isHavePoint) {
            float x = 0;
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    x = event.getX();
                    if (x > view_show_x - 100 && x < view_show_x + 100 && event.getY() > 200) {
                        isStart = true;
                        startX = x;
                    }
                    break;

                case MotionEvent.ACTION_MOVE:
                    if (isStart && event.getY() < height && event.getX() < width) {
                        x_change = event.getX() - startX;
                        invalidate();
                    }
                    break;
                case MotionEvent.ACTION_UP:
                    isStart = false;
                    break;
            }
            return true;
        }
        return super.onTouchEvent(event);
    }

    /**
     * 根据x轴坐标获取y轴坐标
     *
     * @param x
     * @return
     */
    private float getValueByX_Time(float x) {
        float y = 0.0f;
//        y = xyMap.get(time);
        return height / 2;
    }


    /**
     * 根据x轴坐标获取时间
     *
     * @param x
     * @return
     */
    private String getTimeByX(float x) {
        if(x < 0) x = 0;
        float second = allTime * 60 * 60 * x / width;
        int house = (int) (second / 60 / 60);
        int se = (int) (second / 60 % 60);
        int s = (int) (second % 60);
        StringBuilder stringBuilder = new StringBuilder();
        if(house < 10){
            stringBuilder.append("0"+house);
        }else {
            stringBuilder.append(""+house);
        }
        if(se < 10){
            stringBuilder.append(":0"+se);
        }else {
            stringBuilder.append(":"+se);
        }
        if(s < 10){
            stringBuilder.append(":0"+s);
        }else {
            stringBuilder.append(":"+s);
        }
        return stringBuilder.toString();
    }

    private List<Float> datas = new ArrayList<>();
    private List<Float> electDatas = new ArrayList<>();
    private int noElect = -1;//不绘制的位置

    public void generateElectrocar() {
        for (int i = 0; i < 24; i++) {
            datas.add(0f);
            datas.add(0f);
            datas.add(0.5f*maxLevel);
            datas.add(0.6f*maxLevel);
            datas.add(1f*maxLevel);
            datas.add(0.8f*maxLevel);
            datas.add(0.5f*maxLevel);
            datas.add(-0.1f*maxLevel);
            datas.add(-0.3f*maxLevel);
            datas.add(-0.2f*maxLevel);
            datas.add(0f);
            datas.add(0f);
            datas.add(0.3f*maxLevel);
            datas.add(0.5f*maxLevel);
            datas.add(0.7f*maxLevel);
            datas.add(1f*maxLevel);
            datas.add(0.7f*maxLevel);
            datas.add(0.5f*maxLevel);
            datas.add(-0.1f*maxLevel);
            datas.add(-0.3f*maxLevel);
            datas.add(-0.2f*maxLevel);
            datas.add(0f);
            datas.add(0f);
            datas.add(0.3f*maxLevel);
            datas.add(0.5f*maxLevel);
            datas.add(0.7f*maxLevel);
            datas.add(1f*maxLevel);
            datas.add(0.7f*maxLevel);
            datas.add(0.5f*maxLevel);
            datas.add(-0.1f*maxLevel);
            datas.add(-0.3f*maxLevel);
            datas.add(-0.2f*maxLevel);
        }
//        for (int i = 0; i < 2 * per_height_num; i++) {
//            datas.add(0f);
//        }
//        for (int i = 0; i < 20 * per_height_num; i++) {
//            double random;
//            if (i % 2 == 0) {
//                random = Math.random();
//            } else {
//                random = -Math.random();
//            }
//            float value = (float) (maxLevel * random);
//            datas.add(value);
//        }
//        for (int i = 0; i < 2 * per_height_num; i++) {
//            datas.add(0f);
//        }
    }



    private int index;

    public void addOneData(Float nowElect){
        datas.add(nowElect);
        addData();
    }

    public void addData() {
        if (datas.size() > 0) {
            if(index <= (verticalLineNum+1)*per_height_num){
                electDatas.add(datas.get(index));
            }
            if (index > (verticalLineNum+1)*per_height_num+1 && index <= datas.size() -1) {
                int i = index % ((verticalLineNum+1)*per_height_num);
                electPath.reset();
                electDatas.set(i,datas.get(index));
                noElect = i+1;

            }else if(index > datas.size() -1){
                index =0;
                noElect = -1;
                electDatas.clear();
                electPath.reset();
                datas.clear();
                generateElectrocar();
            }
            index++;
            invalidate();
        }
    }


    Runnable runnable;

    public void startDarw() {
        runnable = new Runnable() {
            @Override
            public void run() {
                addData();
                handler.postDelayed(runnable, (long) (40 / multiple));
            }
        };
        handler.post(runnable);
    }
}
