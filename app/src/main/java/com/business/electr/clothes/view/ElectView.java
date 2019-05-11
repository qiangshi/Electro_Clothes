package com.business.electr.clothes.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.graphics.RectF;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import com.business.electr.clothes.R;
import com.business.electr.clothes.utils.DateFormatUtil;
import com.business.electr.clothes.utils.MLog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

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
    private Map<Float,Float> xyMap;

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
    private int baseLine;//基准线
    private int maxLevel;//最大高度
    private boolean isDrawGird = true;

    public void setHavePoint(boolean havePoint) {
        isHavePoint = havePoint;
    }

    public void setDrawGird(boolean drawGird) {
        isDrawGird = drawGird;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = w;
        height = h;
        per_height = (height - 3) / horizontalLineNum;//减去11根线的宽度
        verticalLineNum = width / per_height;
        MLog.e(verticalLineNum + "====zhq====>width<" + width + "===>" + height + "===>" + per_height);
        baseLine = height / 2;
        maxLevel = height / 3;
        per_elect_num = 30;
        per_height_num = 1;
        setData();
    }

    private void setData() {
        generateElectrocar();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (isDrawGird) {
            drawGrid(canvas);
        }
        drawElectro(canvas);
        if (isHavePoint){
            drawPointer(canvas,per_height*2.0f);
        }
    }

    private void drawElectro(Canvas canvas) {
        electPath.moveTo(0, baseLine - datas.get(0));
        for (int i = 0; i < electDatas.size(); i++) {
            float y = baseLine - electDatas.get(i);
            xyMap.put(i * per_height * 1.0f / per_height_num,y);
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


    /**
     * 绘制指示器
     *
     * @param canvas
     */
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void drawPointer(Canvas canvas, Float x) {
        canvas.save();
        pointPaint.setColor(getResources().getColor(R.color.color_44979797));
        pointPaint.setStyle(Paint.Style.STROKE);
        canvas.drawLine(x,height,x,getValueByX_Time(x),pointPaint);
        canvas.restore();
        canvas.save();
        canvas.translate(x,height/2);
        pointPaint.setColor(getResources().getColor(R.color.color_353535));
        pointPaint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(0,0,10,pointPaint);
        RectF f = new RectF(-65f,-70f,65f,-20f);
        canvas.drawRoundRect(f,4f,4f,pointPaint);
        //绘制三角形形成指针
        Path path = new Path();
        path.moveTo(0 , -15);
        path.lineTo(10 , -20);
        path.lineTo(-10 , -20);
        path.lineTo(0 , -15);
        path.close();
        canvas.drawPath(path,pointPaint);
        pointPaint.setColor(getResources().getColor(R.color.white));
        pointPaint.setTextSize(33);
        pointPaint.setTextAlign(Paint.Align.CENTER);
        canvas.drawText("0:25:12",0,-35,pointPaint);
    }


    private List<Float> datas = new ArrayList<>();
    private List<Float> electDatas = new ArrayList<>();

    public void generateElectrocar() {
        for (int i = 0; i < 2 * per_height_num; i++) {
            datas.add(0f);
        }
        for (int i = 0; i < 20 * per_height_num; i++) {
            double random;
            if (i % 2 == 0) {
                random = Math.random();
            } else {
                random = -Math.random();
            }
            float value = (float) (maxLevel * random);
            datas.add(value);
        }
        for (int i = 0; i < 2 * per_height_num; i++) {
            datas.add(0f);
        }
    }

    /**
     * 根据x轴坐标获取y轴坐标
     * @param time
     * @return
     */
    private float getValueByX_Time(float time) {
        float y = 0.0f;
//        y = xyMap.get(time);
        return height/2;
    }

    private int index;

    public void addData() {
        if (datas.size() > 0) {
            electDatas.add(datas.get(index));
            index++;
            if (index > datas.size() - 1) {
                index = 0;
                electDatas.clear();
                electPath.reset();
                datas.clear();
                generateElectrocar();
            }
            invalidate();
        }
    }


    Runnable runnable;

    public void startDarw() {
        runnable = new Runnable() {
            @Override
            public void run() {
                addData();
                handler.postDelayed(runnable, 100);
            }
        };
        handler.post(runnable);
    }
}
