package com.langfeng.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Build;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class drawArcView extends View {
    private int width;
    private int height;
    private Paint paint;
    private float radius;
    private float sweepAngle;
    private String text;
    RectF rectF;

    public float getSweepAngle() {
        return sweepAngle;
    }

    public void setSweepAngle(float sweepAngle) {
        this.sweepAngle = sweepAngle;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    private Context mContext;

    public drawArcView(Context context) {
        this(context, null);
    }

    public drawArcView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public drawArcView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        paint = new Paint();
        paint.setAntiAlias(true);   //设置抗锯齿
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(1); // 线条宽度为 20 像素
        setAttributes(attrs);


    }

    /**
     * 获得自定义属性
     *
     * @param attrs
     */
    private void setAttributes(AttributeSet attrs) {
        // TODO Auto-generated method stub
        TypedArray mArray = mContext.obtainStyledAttributes(attrs,
                R.styleable.TextViewPlus);

        // 得到形状的类型
        sweepAngle = mArray.getFloat(R.styleable.TextViewPlus_sweepAngle, -1);

        mArray.recycle();// 回收mArray
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width = getMeasuredWidth();
        height = getMeasuredHeight();
        radius = Math.min(width, height) / 2;
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        /**
         * 因为drawArc(left,top,right,bottom,startAngle,sweepAngle,userCenter,paint)这个方法是level 21 才加入的，所以加入了版本判断
         */
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            canvas.drawArc(0, 0, width, height, 0, 270, false, paint); // 绘制弧形
        }else{
            /**
             * 另外需要注意的是，对数据的处理不能放在构造方法里，因为还没有开始测量数据
             */
            rectF=new RectF(0, 0, width, height);
            canvas.drawArc(rectF,0, sweepAngle, false, paint);
        }
        paint.setColor(Color.RED);
        canvas.drawCircle(width/2,height/2,radius-10,paint);
        canvas.drawText(text+"%", width/2, height/2, paint);

    }



}
