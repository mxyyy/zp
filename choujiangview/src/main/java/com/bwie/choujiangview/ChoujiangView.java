package com.bwie.choujiangview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;

/**
 * date:2018/11/2
 * author:mxy(M)
 * function:
 */
public class ChoujiangView extends View implements View.OnClickListener {

    //屏幕宽度
    private int screenWith;
    //屏幕高度
    private int screenHeigh;
    //画笔工具
    private Paint mPaint;

    private int centerX;
    private int centerY;

    private int[] colors;

    private String[] desc = new String[]{"性感", "丰满", "知性", "聪明", "贤惠", "优秀"};

    //是否在旋转状态
    private boolean isRote;
    public ChoujiangView(Context context) {
        this(context,null);
    }

    public ChoujiangView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,-1);
    }

    public ChoujiangView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //获取屏幕宽高信息
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        screenWith = displayMetrics.widthPixels;
        screenHeigh = displayMetrics.heightPixels;
        //获取屏幕中心坐标
        centerX = screenWith / 2;
        centerY = screenHeigh / 2;
        //初始化画笔
        initPaint();
        colors = new int[]{Color.RED, Color.GRAY, Color.YELLOW, Color.BLUE, Color.GREEN, Color.DKGRAY, Color.WHITE};
        //初始化旋转动画
        initAnimation();

        //给自己添加点击事件
        this.setOnClickListener(this);
    }

    //测量View大小
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(100,100);
    }

    //绘图
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //移动画布的坐标原点
        canvas.translate(centerX, centerY);

        //绘制6个圆弧
        RectF rect = new RectF(-300, -300, 300, 300);
        float start = 60;
        for (int i = 0; i < 6; i++) {

            mPaint.setColor(colors[i]);
            canvas.drawArc(rect, start * i, 60, true, mPaint);

        }


        //绘制中心的圆
        mPaint.setColor(Color.RED);
        canvas.drawCircle(0, 0, 100, mPaint);

        mPaint.setColor(Color.WHITE);
        mPaint.setTextSize(40);


        //获取文字宽度和高度
        Rect rectText = new Rect();
        mPaint.getTextBounds("start", 0, 5, rectText);
        int width = rectText.width();
        int height = rectText.height();
        canvas.drawText("start", -width / 2, height / 2, mPaint);



        //绘制描述信息
        RectF rectF = new RectF(-200, -200, 200, 200);
        for (int i = 0; i < 6; i++) {
            mPaint.setColor(Color.WHITE);
            Path path = new Path();
            path.addArc(rectF, start * i + 15, 60);
            canvas.drawTextOnPath(desc[i], path, 0, 0, mPaint);
        }


    }

    private void initAnimation() {
    }

    private void initPaint() {
        mPaint = new Paint();
        mPaint.setColor(Color.RED);
        mPaint.setStrokeWidth(20);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setAntiAlias(true);

    }

    private void startAnima(){
        isRote=true;
        RotateAnimation rotateAnimation;
        double random = Math.random();
        rotateAnimation = new RotateAnimation(0, (float) (720*random),
                centerX, centerY);
        rotateAnimation.setDuration(800);
        rotateAnimation.setFillAfter(true);
        //设置重复次数
//        rotateAnimation.setRepeatCount(-1);
        rotateAnimation.setInterpolator(new LinearInterpolator());
        //设置重复模式
        rotateAnimation.setRepeatMode(Animation.RESTART);

        //给动画添加监听
        rotateAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                isRote=false;
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        startAnimation(rotateAnimation);
    }




    @Override
    public void onClick(View v) {
        if (!isRote){
            startAnima();
        }
    }
    //给一个随机的抽奖结果
    private void setRoundDom(){
        double random = Math.random();
        RotateAnimation  rotateAnimation2 = new RotateAnimation(0, (float) (360*random),
                centerX, centerY);
        rotateAnimation2.setDuration(100);
        rotateAnimation2.setFillAfter(true);
        startAnimation(rotateAnimation2);
    }
}
