package com.business.electr.clothes.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.SweepGradient;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.OvershootInterpolator;

import androidx.annotation.Nullable;


/**
 * Created by zenghaiqiang on 2019/4/29.
 * 自定义仪表盘
 */
public class DashBoard extends View {


    private Paint paint , tmpPaint , textPaint ,  strokePain;
    private RectF rect;
    private int backGroundColor;    //背景色
    private float pointLength;      //指针长度
    private float per ;             //指数百分比
    private float perPoint ;        //缓存(变化中)指针百分比
    private float perOld ;          //变化前指针百分比
    private float length ;          //仪表盘半径
    private float r ;

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
        int heitht = width / 2 / 4 * 5;
        initIndex(width / 2);
        //优化组件高度
        setMeasuredDimension(width, heitht);
    }



    private void initIndex(int specSize) {
        backGroundColor = Color.WHITE;
        r = specSize;
        length = r  / 4 * 3;
        pointLength =  - (float) (r *  0.6);
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
        this.length = r  / 4 * 3;
    }

    @Override
    protected void onDraw(Canvas canvas) {

        setLayerType(LAYER_TYPE_SOFTWARE, null);
        //颜色指示的环
        initRing(canvas);
        //刻度文字
        initScale(canvas,60);
        //指针
//        initPointer(canvas);
        //提示内容
        initText(canvas);
    }

    private void initText(Canvas canvas) {
        //抗锯齿
        canvas.setDrawFilter(new PaintFlagsDrawFilter(0, Paint.ANTI_ALIAS_FLAG|Paint.FILTER_BITMAP_FLAG));
        canvas.restore();
        canvas.save();
        canvas.translate(canvas.getWidth()/2, r);
        textPaint.setStrokeWidth(1);
        textPaint.setAntiAlias(true);

        textPaint.setTextSize(150);
        textPaint.setColor(Color.parseColor("#353535"));
        textPaint.setTextAlign(Paint.Align.RIGHT);

        int _per = (int) (per * 80);
        float swidth = textPaint.measureText(String.valueOf(_per));
        //计算偏移量 是的数字和百分号整体居中显示
        swidth =   (swidth - (swidth + 22) / 2);


        canvas.translate( swidth , 0);
        canvas.drawText("" + _per, 0, 0, textPaint);

        textPaint.setTextSize(60);
        textPaint.setTextAlign(Paint.Align.LEFT);

        canvas.drawText("/m" , 0, 0, textPaint);
        textPaint.setTextSize(42);
        textPaint.setTextAlign(Paint.Align.CENTER);
        textPaint.setColor(Color.parseColor("#b6b6b6"));

        canvas.restore();
        canvas.save();
        canvas.translate(canvas.getWidth()/2  , r + length / 3 /2 );

        canvas.drawText("0", -length+30, 0+15f, textPaint);
        canvas.drawText("160", length-30, 0+15f, textPaint);
        textPaint.setTextSize(33);
        canvas.drawText("常规静息心率55-100", 0, 0-10f, textPaint);

    }


    public void setBackGroundColor(int color){
        this.backGroundColor = color;
    }

    public void setPointLength1(float pointLength1){
        this.pointLength = -length * pointLength1 ;
    }

    private void initScale(Canvas canvas,int curElect) {//绘制刻度
        canvas.restore();
        canvas.save();
        canvas.translate(canvas.getWidth()/2, r);
        paint.setColor(Color.parseColor("#c1c3c9"));
        tmpPaint = new Paint(paint); //小刻度画笔对象
        tmpPaint.setTextSize(35);
        tmpPaint.setTextAlign(Paint.Align.CENTER);

        canvas.rotate(-90,0f,0f);

        float  y = length;
        y = - y;
        int count = 80; //总刻度数
        paint.setColor(backGroundColor);

        float tempRou = 180 / 80f;//等分成80份

        paint.setColor(Color.parseColor("#23c688"));
        paint.setStrokeWidth(5);

        //绘制刻度和百分比
        for (int i = 0 ; i <= curElect ; i++){
            if(i == 27){
                canvas.drawLine(0f, y + length / 6 + 10, 0, y + length / 6 + 30, tmpPaint);
                canvas.drawText("55", 0, y + length / 6 + 70, tmpPaint);
            }
            if(i == 50){
                canvas.drawLine(0f, y + length / 6 + 10, 0, y + length / 6 + 30, tmpPaint);
                canvas.drawText("100", 0, y + length / 6 + 70, tmpPaint);
            }
            if(i == curElect){
                paint.setStrokeWidth(8);
                canvas.drawLine(0f, y-length/30 , 0, y + length / 5, paint);
            }else {
                canvas.drawLine(0f, y , 0, y + length / 6, paint);
            }
            canvas.rotate(tempRou,0f,0f);
        }

        paint.setColor(Color.parseColor("#eeeeee"));
        paint.setStrokeWidth(5);
        for (int i = curElect +1; i <= count ; i++){
            if(i == 27){
                canvas.drawLine(0f, y + length / 6 + 10, 0, y + length / 6 + 30, tmpPaint);
                canvas.drawText("55", 0, y + length / 6 + 70, tmpPaint);
            }
            if(i == 50){
                canvas.drawLine(0f, y + length / 6 + 10, 0, y + length / 6 + 30, tmpPaint);
                canvas.drawText("100", 0, y + length / 6 + 70, tmpPaint);
            }
            canvas.drawLine(0f, y , 0, y + length / 6, paint);
            canvas.rotate(tempRou,0f,0f);
        }
    }


    private void initPointer(Canvas canvas) {
        paint.setColor(Color.BLACK);
        canvas.restore();
        canvas.save();
        canvas.translate(canvas.getWidth()/2, r);
        float change;

        if (perPoint < 1 ){
            change = perPoint * 180;
        }else {
            change = 180;
        }

        //根据参数得到旋转角度
        canvas.rotate(-90 + change,0f,0f);

        //绘制三角形形成指针
        Path path = new Path();
        path.moveTo(0 , pointLength);
        path.lineTo(-10 , 0);
        path.lineTo(10,0);
        path.lineTo(0 , pointLength);
        path.close();

        canvas.drawPath(path, paint);

    }

    private void initRing(Canvas canvas) {
        paint.setAntiAlias(true);
        canvas.save();
        canvas.translate(canvas.getWidth()/2, r);

        rect = new RectF( - (length - length / 6f  - 20), -(length / 6f * 5f - 20), length - length / 6f -20 , length / 6f * 5f - 20);
        strokePain = new Paint(paint);

        strokePain.setColor(0xffeeeeee);
        strokePain.setStrokeWidth(5);
        strokePain.setShader(null);
        strokePain.setStyle(Paint.Style.STROKE);
        canvas.drawArc(rect, 241, 52, false, strokePain);
        canvas.restore();
        canvas.save();
        canvas.translate(canvas.getWidth()/2, r);
    }



    public void cgangePer(float per ){
        this.perOld = this.per;
        this.per = per;
        ValueAnimator va =  ValueAnimator.ofFloat(perOld,per);
        va.setDuration(1000);
        va.setInterpolator(new OvershootInterpolator());
        va.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                perPoint = (float) animation.getAnimatedValue();
                invalidate();
            }
        });
        va.start();

    }
}