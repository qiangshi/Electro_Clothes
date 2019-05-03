package com.business.electr.clothes.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * @ClassName: QuantityView
 * @Description: 自定义电量显示
 * @Author: 曾海强
 * @CreateDate: 2019/4/30 21:33
 */
public class QuantityView extends View {
    private Paint circlePaint;
    private Paint textPaint;
    private int circleColor = Color.parseColor("#4ABC99");
    private int textColor = Color.parseColor("#353535");
    private int process;
    private boolean isDown;//是否是倒计时

    public QuantityView(Context context) {
        super(context);
        init();
    }

    public QuantityView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public QuantityView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    private void init(){
        circlePaint = new Paint();
        circlePaint.setColor(circleColor);
        circlePaint.setShader(null);
        circlePaint.setStrokeWidth(5);
        circlePaint.setStyle(Paint.Style.STROKE);
        textPaint = new Paint();
        textPaint.setColor(textColor);
        textPaint.setStyle(Paint.Style.STROKE);
        textPaint.setTextSize(27);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(isDown){
            circlePaint.setColor(Color.parseColor("#80ffffff"));
            textPaint.setColor(Color.parseColor("#80ffffff"));
            textPaint.setTextSize(48);
        }
        canvas.translate(canvas.getWidth()/2, canvas.getHeight()/2);
        canvas.setDrawFilter(new PaintFlagsDrawFilter(0, Paint.ANTI_ALIAS_FLAG|Paint.FILTER_BITMAP_FLAG));
        RectF rect = new RectF( - (canvas.getWidth()/2-10), - (canvas.getHeight()/2-10), (canvas.getWidth()/2-10), (canvas.getHeight()/2-10));
        int pro = process * 360/ 100 ;
        if(isDown) pro = process * 360 / 60;
        canvas.drawArc(rect, -90, pro, false, circlePaint);
        if(!isDown) textPaint.setTextAlign(Paint.Align.RIGHT);
        else textPaint.setTextAlign(Paint.Align.CENTER);
        textPaint.setTextSize(27);
        float swidth = textPaint.measureText(String.valueOf(process));
        //计算偏移量 是的数字和单位整体居中显示
        swidth =   (swidth - (swidth + 18) / 2);
        canvas.translate( swidth , 8);
        canvas.drawText(""+process,0,0,textPaint);
        if(!isDown){
            textPaint.setTextAlign(Paint.Align.LEFT);
            textPaint.setTextSize(18);
            canvas.drawText("%",0,0,textPaint);
        }

    }

    public void setIsDown(boolean isDown){
        this.isDown = isDown;
    }

    public void setProcess(int process){
        this.process = process;
        invalidate();
    }
}
