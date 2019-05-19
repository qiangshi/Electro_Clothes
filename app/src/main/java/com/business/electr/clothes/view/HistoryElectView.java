package com.business.electr.clothes.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.graphics.RectF;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import com.business.electr.clothes.R;
import com.business.electr.clothes.bean.StripData;
import com.business.electr.clothes.utils.DateUtils;
import com.business.electr.clothes.utils.MLog;
import java.util.ArrayList;
import java.util.List;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

/**
 * @ClassName: HistoryElectView
 * @Description: java类作用描述
 * @Author: 曾海强
 * @CreateDate: 2019/5/15 23:19
 */
public class HistoryElectView extends View {
    private Paint dynamicPaint;//动态绘制条形
    private Paint textPaint;//绘制文字
    private Paint pointPaint;//标记条
    private Paint bgPaint;//绘制背景
    private Path linePath;
    private int bgColor;//背景颜色
    private float per_width;//每个的大小
    private int width, height;//整个控件的宽高
    private int stripHeight = 30;//条形图的高
    private int num = 144;//条形图分割的段数
    private float per_height = 103;//每个竖向坐标间隔高度

    private List<StripData> dataList;
    private String textTag = "读书";//标记


    public void setTextTag(String textTag) {
        this.textTag = textTag;
        invalidate();
    }

    public HistoryElectView(Context context) {
        super(context);
        init();
    }

    public HistoryElectView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public HistoryElectView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = w;
        height = h;
        per_width = (w - 66) * 1.0f / 144;//减去离左边文字的距离
        per_height = (h - 93) * 1.0f / 7;
        startX = per_width * 48;
        MLog.e("====zhq====>width<" + width + "===>height<" + height + "===>perWidth<" + per_width);
    }

    /**
     * 初始化
     */
    private void init() {
        bgColor = getResources().getColor(R.color.white);

        this.setBackgroundColor(bgColor);
        dynamicPaint = new Paint();
        dynamicPaint.setAntiAlias(true);
        dynamicPaint.setStyle(Paint.Style.STROKE);
        dynamicPaint.setStrokeWidth(stripHeight);

        textPaint = new Paint();
        textPaint.setColor(getResources().getColor(R.color.color_44979797));
        textPaint.setStyle(Paint.Style.FILL);
        textPaint.setAntiAlias(true);
        textPaint.setTextSize(33);

        bgPaint = new Paint();
        bgPaint.setColor(getResources().getColor(R.color.color_44979797));
        bgPaint.setStyle(Paint.Style.STROKE);

        bgPaint.setStrokeWidth(2);
        PathEffect effects = new DashPathEffect(new float[]{6, 6}, 0);
        bgPaint.setPathEffect(effects);

        pointPaint = new Paint();
        pointPaint.setStyle(Paint.Style.STROKE);
        pointPaint.setStrokeWidth(2);
        pointPaint.setColor(getResources().getColor(R.color.color_353535));

        linePath = new Path();
        dataList = new ArrayList<>();
        generalTestData();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        initBackground(canvas);//背景绘制
        initText(canvas);//字体描绘
        initPointer(canvas, startX);//绘制标记线
        initStrip(canvas);//横坐标描绘
    }


    private void initBackground(Canvas canvas) {
        canvas.save();
        textPaint.setColor(getResources().getColor(R.color.color_44979797));
        textPaint.setTextAlign(Paint.Align.LEFT);
        canvas.drawLine(66, 0, 66, height - 93, bgPaint);
        for (int i = 0; i < 7; i++) {
            float startX = 66;
            if (i == 0 || i == 2 || i == 5) {
                startX = 0;
            }
            canvas.drawText(160 - i * 20 + "", 66 + 10, 40 + i * per_height, textPaint);
            linePath.reset();
            linePath.moveTo(startX, i * per_height);
            linePath.lineTo(width, i * per_height);
            canvas.drawPath(linePath, bgPaint);
        }
        textPaint.setTextSize(33);
        textPaint.setTextAlign(Paint.Align.RIGHT);
        textPaint.setColor(getResources().getColor(R.color.color_8c919b));
        drawText(canvas,"高",40,per_height - 15,textPaint,-90);
        drawText(canvas,"常规", 40, 3.5f * per_height - 15,textPaint,-90);
        drawText(canvas,"低", 40, 6 * per_height - 15,textPaint,-90);
    }


    /**
     * 初始化Text文字
     *
     * @param canvas
     */
    private void initText(Canvas canvas) {
        canvas.restore();
        canvas.save();
        canvas.translate(66, height - 36);
        textPaint.setStrokeWidth(1);
        textPaint.setAntiAlias(true);
        textPaint.setTextSize(33);
        textPaint.setColor(getResources().getColor(R.color.color_8c919b));
        for (int i = 0; i < 5; i++) {
            textPaint.setTextAlign(Paint.Align.CENTER);
            canvas.drawText("0:00", 0, 0, textPaint);
            canvas.drawText("06:00", per_width * 36, 0, textPaint);
            canvas.drawText("12:00", per_width * 72, 0, textPaint);
            canvas.drawText("18:00", per_width * 108, 0, textPaint);
            textPaint.setTextAlign(Paint.Align.RIGHT);
            canvas.drawText("24:00", per_width * 144, 0, textPaint);
        }
        textPaint.setColor(getResources().getColor(R.color.color_979797));

    }

    /**
     * 初始化条
     *
     * @param canvas
     */
    private void initStrip(Canvas canvas) {
        canvas.restore();
        canvas.save();
        canvas.translate(66, height - 93);
        int second = DateUtils.getSecondTime() / 10;
        MLog.e("====zhq====>second<" + second);
        dynamicPaint.setStrokeWidth(stripHeight);
        for (int i = 0; i < num; i++) {
            boolean isHas = false;//当前时间是否有数据
            dynamicPaint.setStyle(Paint.Style.STROKE);
            for (int j = 0; j < dataList.size(); j++) {//从数据中找出当前时间的数据并绘画
                StripData bean = dataList.get(j);
                if (i == bean.getTime()) {
                    isHas = true;
                    drawPerStrip(canvas, bean);
                }
            }
            if (!isHas) {//当前时间没有数据
                dynamicPaint.setColor(getResources().getColor(R.color.color_eeeeee));
                canvas.drawLine(0, 0, per_width + 1, 0, dynamicPaint);
                canvas.translate(per_width, 0);
            }
        }

    }

    /**
     * 绘制单个条
     *
     * @param canvas
     * @param data
     */
    private void drawPerStrip(Canvas canvas, StripData data) {
        canvas.save();
        dynamicPaint.setStrokeWidth(stripHeight);
        if (data != null) {
            if (data.getElect() < 60) {
                dynamicPaint.setColor(getResources().getColor(R.color.start_color));
            } else if (data.getElect() < 100) {
                dynamicPaint.setColor(getResources().getColor(R.color.middle_color));
            } else {
                dynamicPaint.setColor(getResources().getColor(R.color.end_color));
            }
            dynamicPaint.setStrokeWidth(6);
            canvas.drawLine(0, 160 - (data.getElect() - 20) * per_height * 7 / 140, per_width + 1, 160 - (data.getElect() - 20) * per_height * 7 / 140, dynamicPaint);
            dynamicPaint.setStrokeWidth(stripHeight);
            canvas.drawLine(0, 0, per_width + 1, 0, dynamicPaint);
            canvas.translate(per_width, 0);
        }
    }


    /**
     * 绘制标记线
     *
     * @param canvas
     */
    private void initPointer(Canvas canvas, Float x) {
        canvas.restore();
        canvas.save();
        canvas.translate(66, 0);
        pointPaint.setStyle(Paint.Style.STROKE);
        pointPaint.setStrokeWidth(2);
        pointPaint.setColor(getResources().getColor(R.color.color_353535));
        x += x_change;
        view_show_x = x;
        if (view_show_x < 0) {
            view_show_x = 0;
            x = 0f;
        }
        if (view_show_x > width - 66) {
            view_show_x = width - 66;
            x = (float) width - 66;
        }
        canvas.drawLine(x, 0, x, height - 93, pointPaint);
        canvas.restore();
        canvas.save();
        canvas.translate(66 + x, height / 2);
        pointPaint.setStyle(Paint.Style.FILL);
        RectF f = new RectF(-50f, -70f, 50f, -20f);
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
        canvas.drawText(textTag, 0, -35, pointPaint);
    }


    private boolean isStart;
    private float startX, view_show_x, x_change;

    /**
     * 滑动心电图展示时间
     *
     * @param event
     * @return
     */
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = 0;
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                x = event.getX();
                if (x > view_show_x - 100 && x < view_show_x + 100) {
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

    /**
     * 绘制字体旋转
     *
     * @param canvas
     * @param text
     * @param x
     * @param y
     * @param paint
     * @param angle
     */
    private void drawText(Canvas canvas, String text, float x, float y, Paint paint, float angle) {
        if (angle != 0) {
            canvas.rotate(angle, x, y);
        }
        canvas.drawText(text, x, y, paint);
        if (angle != 0) {
            canvas.rotate(-angle, x, y);
        }
    }

    public void setDataList(List<StripData> list) {
        this.dataList = list;
        invalidate();
    }


    private void generalTestData() {
        dataList.add(new StripData(15, 65));
        dataList.add(new StripData(16, 68));
        dataList.add(new StripData(17, 70));
        dataList.add(new StripData(18, 67));
        dataList.add(new StripData(19, 68));
        dataList.add(new StripData(20, 69));
        dataList.add(new StripData(21, 60));
        dataList.add(new StripData(23, 55));
        dataList.add(new StripData(24, 59));
        dataList.add(new StripData(25, 56));
        dataList.add(new StripData(26, 70));
        dataList.add(new StripData(27, 72));
        dataList.add(new StripData(28, 74));

        dataList.add(new StripData(50, 85));
        dataList.add(new StripData(51, 84));
        dataList.add(new StripData(52, 83));
        dataList.add(new StripData(53, 82));
        dataList.add(new StripData(54, 81));
        dataList.add(new StripData(55, 80));
        dataList.add(new StripData(56, 79));


        dataList.add(new StripData(75, 90));

        dataList.add(new StripData(89, 120));
        dataList.add(new StripData(90, 121));
        dataList.add(new StripData(91, 123));
        dataList.add(new StripData(92, 124));
        dataList.add(new StripData(93, 125));
        dataList.add(new StripData(94, 126));
        dataList.add(new StripData(95, 127));

        dataList.add(new StripData(112, 85));
        dataList.add(new StripData(113, 86));
        dataList.add(new StripData(114, 87));
        dataList.add(new StripData(115, 88));
        dataList.add(new StripData(116, 89));
        dataList.add(new StripData(117, 90));
        dataList.add(new StripData(118, 91));
        dataList.add(new StripData(119, 92));
        dataList.add(new StripData(120, 93));
        dataList.add(new StripData(121, 94));
        dataList.add(new StripData(122, 95));
        dataList.add(new StripData(123, 96));
        dataList.add(new StripData(124, 97));
        dataList.add(new StripData(125, 98));
        dataList.add(new StripData(126, 99));
        dataList.add(new StripData(127, 100));
        dataList.add(new StripData(128, 101));
        dataList.add(new StripData(129, 102));

    }

}
