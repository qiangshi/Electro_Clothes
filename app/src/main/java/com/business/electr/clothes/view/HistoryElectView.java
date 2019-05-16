package com.business.electr.clothes.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.util.AttributeSet;
import android.view.View;

import com.business.electr.clothes.R;
import com.business.electr.clothes.bean.StripData;
import com.business.electr.clothes.utils.DateUtils;
import com.business.electr.clothes.utils.MLog;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;

/**
 * @ClassName: HistoryElectView
 * @Description: java类作用描述
 * @Author: 曾海强
 * @CreateDate: 2019/5/15 23:19
 */
public class HistoryElectView extends View {
    private Paint dynamicPaint;//动态绘制条形
    private Paint textPaint;//绘制文字
    private Paint bgPaint;//绘制背景
    private Path linePath;
    private int bgColor;//背景颜色
    private float per_width;//每个的大小
    private int width, height;//整个控件的宽高
    private int stripHeight = 30;//条形图的高
    private int num = 144;//条形图分割的段数
    private float per_height = 103;//每个竖向坐标间隔高度

    private List<StripData> dataList;

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
        per_height = (h-93) *1.0f/7;
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
        textPaint.setColor(getResources().getColor(R.color.color_b6b6b6));
        textPaint.setStyle(Paint.Style.FILL);
        textPaint.setTextSize(33);
        textPaint.setStrokeWidth(stripHeight);

        bgPaint = new Paint();
        bgPaint.setColor(getResources().getColor(R.color.color_44979797));
        bgPaint.setStyle(Paint.Style.STROKE);
        bgPaint.setStrokeWidth(2);
        PathEffect effects = new DashPathEffect(new float[]{6, 6}, 0);
        bgPaint.setPathEffect(effects);

        linePath = new Path();
        dataList = new ArrayList<>();
        generalTestData();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        initBackground(canvas);//背景绘制
        initText(canvas);//字体描绘
        initStrip(canvas);//横坐标描绘
    }


    private void initBackground(Canvas canvas) {
        canvas.drawLine(66, 0, 66, height - 93, bgPaint);
        for (int i = 0; i < 7; i++) {
            float startX = 66;
            if(i == 0 || i == 2 || i==5){
                startX = 0;
            }
            linePath.reset();
            linePath.moveTo(startX, i * per_height);
            linePath.lineTo(width, i * per_height);
            canvas.drawPath(linePath, bgPaint);
        }
        textPaint.setStrokeWidth(1);
        textPaint.setAntiAlias(true);
        textPaint.setTextSize(33);
        textPaint.setColor(getResources().getColor(R.color.color_8c919b));
        canvas.drawText("高",0,136,textPaint);
        canvas.drawText("常规",0,410,textPaint);
        canvas.drawText("低",0,700,textPaint);

//        drawText(canvas, "高", 0, 136, textPaint, -90);
//        canvas.save();
//        drawText(canvas, "常规", 0, 410, textPaint, -90);
//        drawText(canvas, "低", 0, 700, textPaint, -90);
    }


    /**
     * 初始化Text文字
     *
     * @param canvas
     */
    private void initText(Canvas canvas) {
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
            canvas.drawLine(0, 0, per_width + 1, 0, dynamicPaint);
            canvas.translate(per_width, 0);
        }
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
            canvas.rotate(angle,x,y);
        }
        canvas.drawText(text, x, y, paint);
        if (angle != 0) {
            canvas.rotate(-angle,x,y);
        }
    }

    public void setDataList(List<StripData> list) {
        this.dataList = list;
        invalidate();
    }


    private void generalTestData() {
        dataList.add(new StripData(15, 65));
        dataList.add(new StripData(16, 65));
        dataList.add(new StripData(17, 66));
        dataList.add(new StripData(18, 67));
        dataList.add(new StripData(19, 68));
        dataList.add(new StripData(20, 69));
        dataList.add(new StripData(21, 65));
        dataList.add(new StripData(23, 50));
        dataList.add(new StripData(24, 50));
        dataList.add(new StripData(25, 50));
        dataList.add(new StripData(26, 50));
        dataList.add(new StripData(27, 50));
        dataList.add(new StripData(28, 50));

        dataList.add(new StripData(50, 85));
        dataList.add(new StripData(51, 80));
        dataList.add(new StripData(52, 90));
        dataList.add(new StripData(53, 98));
        dataList.add(new StripData(54, 90));
        dataList.add(new StripData(55, 90));
        dataList.add(new StripData(56, 90));


        dataList.add(new StripData(75, 90));

        dataList.add(new StripData(89, 120));
        dataList.add(new StripData(90, 150));
        dataList.add(new StripData(91, 123));
        dataList.add(new StripData(92, 150));
        dataList.add(new StripData(93, 120));
        dataList.add(new StripData(94, 160));
        dataList.add(new StripData(95, 120));

        dataList.add(new StripData(112, 85));
        dataList.add(new StripData(113, 85));
        dataList.add(new StripData(114, 85));
        dataList.add(new StripData(115, 85));
        dataList.add(new StripData(116, 85));
        dataList.add(new StripData(117, 85));
        dataList.add(new StripData(118, 85));
        dataList.add(new StripData(119, 85));
        dataList.add(new StripData(120, 85));
        dataList.add(new StripData(121, 85));
        dataList.add(new StripData(122, 85));
        dataList.add(new StripData(123, 50));
        dataList.add(new StripData(124, 50));
        dataList.add(new StripData(125, 50));
        dataList.add(new StripData(126, 50));
        dataList.add(new StripData(127, 50));
        dataList.add(new StripData(128, 50));
        dataList.add(new StripData(129, 50));

    }

}
