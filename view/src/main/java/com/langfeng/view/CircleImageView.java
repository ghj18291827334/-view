package com.langfeng.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Debug;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

public class CircleImageView extends AppCompatImageView {
    private float width;
    private float height;
    private float radius;
    private Paint paint;
    private Matrix matrix;
    private Path path;
    private Camera camera;
    private Context mContext;
    private String shape_type;// 形状的类型


    public CircleImageView(Context context) {
        this(context, null);


    }

    public CircleImageView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CircleImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        paint = new Paint();
        paint.setAntiAlias(true);   //设置抗锯齿
        matrix = new Matrix();      //初始化缩放矩阵
        path = new Path();            //初始化 Path 对象
        camera = new Camera();
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
                R.styleable.shapeimageview);

        // 得到形状的类型
        shape_type = mArray.getString(R.styleable.shapeimageview_shape_type);

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
        Drawable drawable = getDrawable();
        if (drawable == null) {
            super.onDraw(canvas);
            return;
        }
        if (drawable instanceof BitmapDrawable) {
            paint.setShader(initBitmapShader((BitmapDrawable) drawable));//将着色器设置给画笔

            if ("star".equals(shape_type)) {
                /**
                 * 绘制五边形
                 */
                path.moveTo(0, height * 2 / 5);
                path.lineTo(width, height * 2 / 5);
                path.lineTo(width * 1 / 5, height);
                path.lineTo(width / 2, 0);
                path.lineTo(width / 5 * 4, height);
                path.close();
                canvas.drawPath(path, paint);
            } else if ("triangle".equals(shape_type)) {
                /**
                 * 绘制三角形
                 */

                path.moveTo(0, height);
                path.lineTo(width, height);
                path.lineTo(width / 2, 0);
                path.close();
                canvas.drawPath(path, paint);
            } else {
                /**
                 * 使用画笔在画布上画圆
                 */
                canvas.drawCircle(width / 2, height / 2, radius, paint);
            }


            return;
        }
        super.onDraw(canvas);


    }

    /**
     * 获取ImageView中资源图片的Bitmap，利用Bitmap初始化图片着色器,通过缩放矩阵将原资源图片缩放到铺满整个绘制区域，避免边界填充
     */
    private BitmapShader initBitmapShader(BitmapDrawable drawable) {
        Bitmap bitmap = drawable.getBitmap();
        BitmapShader bitmapShader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        float scale = Math.max(width / bitmap.getWidth(), height / bitmap.getHeight());
        matrix.setScale(scale, scale);//将图片宽高等比例缩放，避免拉伸
        bitmapShader.setLocalMatrix(matrix);
        return bitmapShader;
    }

}
