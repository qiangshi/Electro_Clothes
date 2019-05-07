package com.business.electr.clothes.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
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
 * @Description: 条形时间轴View
 * @Author: 曾海强
 * @CreateDate: 2019/5/7 14:57
 */
public class StripShapeView extends View {
    private Paint dynamicPaint;//动态绘制条形
    private Paint textPaint;//绘制文字
    private int bgColor;//背景颜色
    private float per_width;//每个的大小
    private int width, height;//整个控件的宽高
    private int stripHeight = 18;//条形图的高
    private int num = 144;//条形图分割的段数

    private Context context;
    private List<StripData> dataList;

    public StripShapeView(Context context) {
        super(context);
        this.context = context;
        init();
    }

    public StripShapeView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init();
    }

    public StripShapeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        init();
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = w;
        height = h;
        per_width = (w - stripHeight) * 1.0f / 144;
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
        textPaint.setStyle(Paint.Style.STROKE);
        textPaint.setStrokeWidth(stripHeight);

        dataList = new ArrayList<>();
        generalTestData();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        initText(canvas);
        initStrip(canvas);
    }

    /**
     * 初始化条
     *
     * @param canvas
     */
    private void initStrip(Canvas canvas) {
        canvas.restore();
        canvas.save();
        canvas.translate(stripHeight,stripHeight/2);
        int second = DateUtils.getSecondTime()/10;
        MLog.e("====zhq====>second<"+second );
        dynamicPaint.setStrokeWidth(stripHeight);
        for (int i = 0; i < num; i++) {
            boolean isHas = false;
            if(i == 0){
                canvas.translate(per_width,0);
                dynamicPaint.setColor(getResources().getColor(R.color.color_eeeeee));
                dynamicPaint.setStyle(Paint.Style.FILL);
                RectF rectF = new RectF(-stripHeight,-stripHeight/2,stripHeight,stripHeight/2);
                canvas.drawArc(rectF,90, 180, false,  dynamicPaint);
            }
            dynamicPaint.setStyle(Paint.Style.STROKE);
            if(i == second){
                String currTime = DateUtils.getCurrentTimeHouse();
                textPaint.setTextAlign(Paint.Align.CENTER);
                canvas.drawText(currTime, 0, 50, textPaint);
            }
//            if(i == num){
//                textPaint.setTextAlign(Paint.Align.LEFT);
//                canvas.drawText("23:59", 0, 50, textPaint);
//            }
            if(i > second){
                dynamicPaint.setColor(getResources().getColor(R.color.white));
                canvas.drawLine(0, 0, per_width+1, 0, dynamicPaint);
                canvas.translate(per_width,0);
                continue;
            }
            for (int j = 0; j< dataList.size(); j++){
                StripData bean = dataList.get(j);
                if(i == bean.getTime()){
                    isHas = true;
                    drawPerStrip(canvas,bean);
                }
            }
            if(!isHas) {
                dynamicPaint.setColor(getResources().getColor(R.color.color_eeeeee));
                canvas.drawLine(0, 0, per_width+1, 0, dynamicPaint);
                canvas.translate(per_width,0);
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
            canvas.drawLine(0, 0, per_width+1, 0, dynamicPaint);
            canvas.translate(per_width,0);
        }
    }

    /**
     * 初始化Text文字
     *
     * @param canvas
     */
    private void initText(Canvas canvas) {
        canvas.save();
        canvas.translate(0, 50);
        textPaint.setStrokeWidth(1);
        textPaint.setAntiAlias(true);
        textPaint.setTextSize(30);
        textPaint.setTextAlign(Paint.Align.LEFT);
        canvas.drawText("0:00", 0, 0, textPaint);
    }


    public void setDataList(List<StripData> list){
        this.dataList = list;
        invalidate();
    }


    private void generalTestData(){
        dataList.add(new StripData(15,65));
        dataList.add(new StripData(16,65));
        dataList.add(new StripData(17,66));
        dataList.add(new StripData(18,67));
        dataList.add(new StripData(19,68));
        dataList.add(new StripData(20,69));
        dataList.add(new StripData(21,65));
        dataList.add(new StripData(23,50));
        dataList.add(new StripData(24,50));
        dataList.add(new StripData(25,50));
        dataList.add(new StripData(26,50));
        dataList.add(new StripData(27,50));
        dataList.add(new StripData(28,50));

        dataList.add(new StripData(50,85));
        dataList.add(new StripData(51,80));
        dataList.add(new StripData(52,90));
        dataList.add(new StripData(53,98));
        dataList.add(new StripData(54,90));
        dataList.add(new StripData(55,90));
        dataList.add(new StripData(56,90));


        dataList.add(new StripData(75,90));

        dataList.add(new StripData(89,120));
        dataList.add(new StripData(90,150));
        dataList.add(new StripData(91,123));
        dataList.add(new StripData(92,150));
        dataList.add(new StripData(93,120));
        dataList.add(new StripData(94,160));
        dataList.add(new StripData(95,120));

        dataList.add(new StripData(112,85));
        dataList.add(new StripData(113,85));
        dataList.add(new StripData(114,85));
        dataList.add(new StripData(115,85));
        dataList.add(new StripData(116,85));
        dataList.add(new StripData(117,85));
        dataList.add(new StripData(118,85));
        dataList.add(new StripData(119,85));
        dataList.add(new StripData(120,85));
        dataList.add(new StripData(121,85));
        dataList.add(new StripData(122,85));
        dataList.add(new StripData(123,50));
        dataList.add(new StripData(124,50));
        dataList.add(new StripData(125,50));
        dataList.add(new StripData(126,50));
        dataList.add(new StripData(127,50));
        dataList.add(new StripData(128,50));
        dataList.add(new StripData(129,50));

    }
}
