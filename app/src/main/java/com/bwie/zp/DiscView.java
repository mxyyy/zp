package com.bwie.zp;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;

/**
 * date:2018/11/2
 * author:mxy(M)
 * function:
 */
public class DiscView extends View implements View.OnClickListener {



    private RotateAnimation rotateAnimation;
    private Paint mPaint;
    private Paint strPaint;
    private int mWidth;
    private int mPadding;
    private boolean isStart = false;
    private RectF rectF;
    private String str="start";
    private String[] contents = new String[]{"美 女","女 神","热 舞","丰 满","性 感","知 性"};



    public DiscView(Context context) {
        this(context,null);
    }

    public DiscView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,-1);
    }

    public DiscView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
        initAnim();
        setOnClickListener(this);
    }

    private void initAnim() {
        //以veiw的中心点为旋转参考点
        rotateAnimation = new RotateAnimation(0f,360f,
                Animation.RELATIVE_TO_SELF,0.5f,
                Animation.RELATIVE_TO_SELF,0.5f);
        rotateAnimation.setRepeatCount(-1);
        rotateAnimation.setFillAfter(true);

    }

    private void initPaint() {
        strPaint = new Paint();
        strPaint.setStyle(Paint.Style.STROKE);
        strPaint.setAntiAlias(true);
        strPaint.setColor(Color.WHITE);
        strPaint.setStrokeWidth(5);

        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.WHITE);
        mPaint.setStrokeWidth(3);
    }
    protected void onMeasure(int widthMeasureSpec, int
            heightMeasureSpec){


        setMeasuredDimension(300,300);
        mWidth = getMeasuredWidth();
        mPadding=5;
        initRect();
    }

    private void initRect() {
        rectF = new RectF(0,0,mWidth,mWidth);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //绘制圆
        mPaint.setStyle(Paint.Style.STROKE);
        canvas.drawCircle(mWidth/2,mWidth/2,
                mWidth/2-mPadding,mPaint);
        //绘制6个椭圆
        mPaint.setStyle(Paint.Style.FILL);

        initArc(canvas);

        //绘制里面的小圆
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(mWidth/2,mWidth/2,50,mPaint);

        mPaint.setColor(Color.WHITE);
        mPaint.setTextSize(24);
        Rect rect = new Rect();
        mPaint.getTextBounds(str,0,str.length(),rect);
        //文本的宽度
        int strWidth = rect.width();
        //文本的高度
        int textHeight = rect.height();
        canvas.drawText(str,mWidth/2-25+25-strWidth/2,
                mWidth/2+textHeight/2,mPaint);
    }

        /*绘制6个弧和对应的文字*/

    private void initArc(Canvas canvas) {
        for (int i=0; i<6;i++){
            mPaint.setColor(colors[i]);
            canvas.drawArc(rectF,(i-1)*60+60,
                    60,true,mPaint);
        }
        for (int i=0; i<6;i++){
            mPaint.setColor(Color.BLACK);
            Path path = new Path();
            path.addArc(rectF,(i-1)*60+60,60);
            canvas.drawTextOnPath(contents[i],path,60,60,mPaint);

        }
    }

    public  int[] colors = new int[]{Color.parseColor("#8EE5EE"),Color.parseColor("#FFD700"),Color.parseColor("#FFD39B"),Color.parseColor("#FF8247"),Color.parseColor("#FF34B3"),Color.parseColor("#F0E68C")};


    @Override
    public void onClick(View view) {
        if(!isStart){
            isStart = true;
            rotateAnimation.setDuration(1000);
            rotateAnimation.setInterpolator(new LinearInterpolator());//不停顿
            startAnimation(rotateAnimation);
        }else{
            isStart = false;
            stopAnim();
        }
    }
    /**
     * 停止动画
     */
    public void stopAnim() {
        clearAnimation();


    }
}
