package com.xing.progressandroid.customview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.xing.progressandroid.R;

import java.util.Random;

/**
 * BitmapShader 实现各形状的图片，继承自 ImageView
 */
public class BitmapShaderShapeImageView extends android.support.v7.widget.AppCompatImageView {

    private Paint paint;
    private BitmapShader bitmapShader;
    private int mViewWidth;
    private int mViewHeight;
    private Bitmap bitmap;
    private Random random;
    private String[] shapes = {"RoundRectangle", "Circle", "Triangle"};
    private int index;
    private RectF rectF;


    public BitmapShaderShapeImageView(Context context) {
        super(context);
        init();
    }

    public BitmapShaderShapeImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.key);
        bitmapShader = new BitmapShader(bitmap, Shader.TileMode.REPEAT, Shader.TileMode.CLAMP);
        paint.setShader(bitmapShader);

        random = new Random();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = getMeasuredSize(widthMeasureSpec, bitmap.getWidth());
        int height = getMeasuredSize(heightMeasureSpec, bitmap.getHeight());
        setMeasuredDimension(width, height);
    }

    private int getMeasuredSize(int measureSpec, int defSize) {
        int mode = MeasureSpec.getMode(measureSpec);
        int size = MeasureSpec.getSize(measureSpec);
        int value;
        if (mode == MeasureSpec.EXACTLY) {
            value = size;
        } else {
            value = defSize;
        }
        return value;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mViewWidth = w;
        mViewHeight = h;
        rectF = new RectF(mViewWidth / 5f, mViewHeight / 4f, mViewWidth * 4 / 5f, mViewHeight * 3 / 4f);
    }

    @Override
    protected void onDraw(Canvas canvas) {
//        super.onDraw(canvas);
        switch (index % shapes.length) {
            case 0:
                canvas.drawRoundRect(rectF, 30, 30, paint);
                break;
            case 1:
                canvas.drawCircle(mViewWidth / 2f, mViewHeight / 2f, 100, paint);
                break;
            case 2:
                canvas.drawOval(rectF, paint);
                break;
        }
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            index++;
            invalidate();
        }
        return true;
    }
}
