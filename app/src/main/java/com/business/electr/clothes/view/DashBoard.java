package com.business.electr.clothes.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import com.business.electr.clothes.R;
import com.business.electr.clothes.utils.LinearGradientUtil;

import androidx.annotation.Nullable;


/**
 * Created by zenghaiqiang on 2019/4/29.
 * 自定义仪表盘
 */
public class DashBoard extends View {
    //划刻度线   划文字
    private Paint paint, tmpPaint, textPaint, strokePain;
    private RectF rect;
    private int backGroundColor;    //背景色
    private int per;             //指数百分比
    private float perOld;          //变化前指针百分比
    private float length;          //仪表盘半径
    private float r;

    public DashBoard(Context context) {
        super(context);
        init();
    }

    public DashBoard(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public DashBoard(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        initIndex(width / 2);
        //优化组件高度
        setMeasuredDimension(width, height);
    }

    private void initIndex(int specSize) {
        backGroundColor = Color.WHITE;
        r = specSize;
        length = r / 4 * 3;
        per = 0;
        perOld = 0;
    }


    private void init() {
        paint = new Paint();
        rect = new RectF();
        textPaint = new Paint();
        tmpPaint = new Paint();
        strokePain = new Paint();
    }

    public void setR(float r) {
        this.r = r;
        this.length = r / 4 * 3;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        setLayerType(LAYER_TYPE_SOFTWARE, null);
        initRing(canvas);   //划内弧
        initScale(canvas, per / 2);  //刻度文字
        initText(canvas);//提示内容
    }

    /**
     * 花60-100的圆弧
     *
     * @param canvas
     */
    private void initRing(Canvas canvas) {
        paint.setAntiAlias(true);
        canvas.save();
        canvas.translate(canvas.getWidth() / 2, r / 6 * 5);
        rect = new RectF(-(length - length / 6f - 20), -(length / 6f * 5f - 20), length - length / 6f - 20, length / 6f * 5f - 20);
        strokePain = new Paint(paint);
        strokePain.setColor(0xffeeeeee);
        strokePain.setStrokeWidth(5);
        strokePain.setShader(null);
        strokePain.setStyle(Paint.Style.STROKE);
        canvas.drawArc(rect, 247, 45, false, strokePain);
    }


    private void initScale(Canvas canvas, int curElect) {//绘制刻度
        canvas.restore();
        canvas.save();
        canvas.translate(canvas.getWidth() / 2, r / 6 * 5);
        tmpPaint = new Paint(paint); //小刻度画笔对象
        tmpPaint.setTextSize(35);
        tmpPaint.setColor(getResources().getColor(R.color.color_eeeeee));
        tmpPaint.setTextAlign(Paint.Align.CENTER);
        canvas.rotate(-90, 0f, 0f);

        float y = length;
        y = -y;
        int count = 80; //总刻度数
        float tempRou = 180 / 80f;//等分成80份

        LinearGradientUtil linearGradientUtil = new LinearGradientUtil(getResources().getColor(R.color.start_color), getResources().getColor(R.color.middle_color), getResources().getColor(R.color.end_color));
        paint.setStrokeWidth(5);
        int size = Math.min(29, curElect);

        //绘制刻度和百分比
        for (int i = 0; i <= size; i++) {//划绿色的刻度线
            paint.setColor(linearGradientUtil.getThreeColor(i * 1.0f / 40));
            if (i == curElect) {
                paint.setStrokeWidth(8);
                canvas.drawLine(0f, y - length / 30, 0, y + length / 5, paint);
                canvas.rotate(tempRou, 0f, 0f);
                break;
            } else {
                canvas.drawLine(0f, y, 0, y + length / 6, paint);
                canvas.rotate(tempRou, 0f, 0f);
            }
        }

        paint.setStrokeWidth(5);
        //绘制刻度和百分比
        for (int i = 30; i <= Math.min(curElect, 50); i++) {//划化正常的刻度线
            if (i == 30) {
                canvas.drawLine(0f, y + length / 6 + 10, 0, y + length / 6 + 30, tmpPaint);
                tmpPaint.setColor(getResources().getColor(R.color.color_c1c3c9));
                canvas.drawText("60", 0, y + length / 6 + 70, tmpPaint);
                tmpPaint.setColor(getResources().getColor(R.color.color_eeeeee));
            }
            if (i == 50) {
                canvas.drawLine(0f, y + length / 6 + 10, 0, y + length / 6 + 30, tmpPaint);
                tmpPaint.setColor(getResources().getColor(R.color.color_c1c3c9));
                canvas.drawText("100", 0, y + length / 6 + 70, tmpPaint);
                tmpPaint.setColor(getResources().getColor(R.color.color_eeeeee));
            }
            paint.setColor(linearGradientUtil.getThreeColor(i * 1.0f / 40));
            if (i == curElect) {
                paint.setStrokeWidth(8);
                canvas.drawLine(0f, y - length / 30, 0, y + length / 5, paint);
            } else {
                canvas.drawLine(0f, y, 0, y + length / 6, paint);
            }
            canvas.rotate(tempRou, 0f, 0f);
        }

        paint.setStrokeWidth(5);
        //绘制刻度和百分比
        for (int i = 51; i <= curElect; i++) {//划黄色的刻度线
            paint.setColor(linearGradientUtil.getThreeColor(i * 1.0f / 40));
            if (i == curElect) {
                paint.setStrokeWidth(8);
                canvas.drawLine(0f, y - length / 30, 0, y + length / 5, paint);
                canvas.rotate(tempRou, 0f, 0f);
                break;
            } else {
                canvas.drawLine(0f, y, 0, y + length / 6, paint);
                canvas.rotate(tempRou, 0f, 0f);
            }
        }

        paint.setColor(getResources().getColor(R.color.color_eeeeee));
        paint.setStrokeWidth(5);
        for (int i = curElect + 1; i <= count; i++) {//划灰色的刻度线
            if (i == 30) {
                canvas.drawLine(0f, y + length / 6 + 10, 0, y + length / 6 + 30, tmpPaint);
                tmpPaint.setColor(getResources().getColor(R.color.color_c1c3c9));
                canvas.drawText("60", 0, y + length / 6 + 70, tmpPaint);
                tmpPaint.setColor(getResources().getColor(R.color.color_eeeeee));
            }
            if (i == 50) {
                canvas.drawLine(0f, y + length / 6 + 10, 0, y + length / 6 + 30, tmpPaint);
                tmpPaint.setColor(getResources().getColor(R.color.color_c1c3c9));
                canvas.drawText("100", 0, y + length / 6 + 70, tmpPaint);
                tmpPaint.setColor(getResources().getColor(R.color.color_eeeeee));
            }
            canvas.drawLine(0f, y, 0, y + length / 6, paint);
            canvas.rotate(tempRou, 0f, 0f);
        }
    }

    /**
     * 初始化显示的文字
     *
     * @param canvas
     */
    private void initText(Canvas canvas) {
        //抗锯齿
        canvas.setDrawFilter(new PaintFlagsDrawFilter(0, Paint.ANTI_ALIAS_FLAG | Paint.FILTER_BITMAP_FLAG));
        canvas.restore();
        canvas.save();
        canvas.translate(canvas.getWidth() / 2, r / 6 * 5);
        textPaint.setStrokeWidth(1);
        textPaint.setAntiAlias(true);
        textPaint.setTextSize(150);
        textPaint.setColor(getResources().getColor(R.color.color_353535));
        textPaint.setTextAlign(Paint.Align.RIGHT);

        float swidth = textPaint.measureText(String.valueOf(per));
        //计算偏移量 是的数字和单位整体居中显示
        swidth = (swidth - (swidth + 60) / 2);

        canvas.translate(swidth, 0);
        canvas.drawText("" + per, 0, 0, textPaint);
        textPaint.setTextSize(60);
        textPaint.setTextAlign(Paint.Align.LEFT);
        canvas.drawText("/m", 0, 0, textPaint);
        textPaint.setTextSize(42);
        textPaint.setTextAlign(Paint.Align.CENTER);
        textPaint.setColor(getResources().getColor(R.color.color_b6b6b6));
        canvas.restore();
        canvas.save();
        canvas.translate(canvas.getWidth() / 2, r / 6 * 5 + length / 3 / 2);
        canvas.drawText("0", -length + 30, 0 + 15f, textPaint);
        canvas.drawText("160", length - 30, 0 + 15f, textPaint);
        textPaint.setTextSize(33);
        canvas.drawText("常规静息心率60-100", 0, 0 - 10f, textPaint);

    }


    /**
     * 设置当前的心率
     *
     * @param curElect
     */
    public void setCurElect(int curElect) {
        this.perOld = this.per;
        this.per = curElect;
        invalidate();
    }

}