package com.xing.progressandroid.customview;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.ComposeShader;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.RectF;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

import com.xing.progressandroid.R;

/**
 * 组合着色器，它通过 Xfermode 将两个着色器组合起来。
 */
public class ComposeShaderView extends View {

    private static final String TAG = "ComposeShaderView";
    private Paint paint;
    private int mViewWidth;
    private int mViewHeight;
    private ComposeShader composeShader;
    private Bitmap bitmap;
    private float centerX;
    private float centerY;
    private int radius;
    private int imgResId;
    private RectF rectF;
    private float value;
    private BitmapShader bitmapShader;
    private LinearGradient linearGradient;
    private Matrix gradientMatrix;
    private ValueAnimator valueAnimator;

    public ComposeShaderView(Context context) {
        super(context);
        init();
    }

    public ComposeShaderView(Context context, AttributeSet attrs) {
        super(context, attrs);
        readAttrs(context, attrs);
        init();
    }

    private void readAttrs(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ComposeShaderView);
        imgResId = typedArray.getResourceId(R.styleable.ComposeShaderView_imgSrc, 0);
        if (imgResId == 0) {
            throw new IllegalArgumentException("the image resource can't be null");
        }
        typedArray.recycle();
    }

    private void init() {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        bitmap = BitmapFactory.decodeResource(getResources(), imgResId);
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

        centerX = mViewWidth / 2f;
        centerY = mViewHeight / 2f;
        radius = Math.min(mViewWidth, mViewHeight) / 2;
        value = -mViewWidth;

        rectF = new RectF(0, 0, mViewWidth, mViewHeight);

        // 缩放 bitmap 对象，宽高和 控件宽高一致
        Bitmap scaledBitmap = Bitmap.createScaledBitmap(bitmap, mViewWidth, mViewHeight, false);
        // 创建 BitmapShader
        bitmapShader = new BitmapShader(scaledBitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        // 创建 LinearGradient 线性渐变
        linearGradient = new LinearGradient(0, 0, mViewWidth, mViewHeight,
                new int[]{0x22000000, 0xee333333, 0x22000000},
                new float[]{0.3f, 0.5f, 0.7f},
                Shader.TileMode.CLAMP);
        // 混合渲染 将两个效果叠加,使用PorterDuff叠加模式
//        composeShader = new ComposeShader(bitmapShader, linearGradient, PorterDuff.Mode.MULTIPLY);

        gradientMatrix = new Matrix();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int saveCount = canvas.saveLayer(0, 0, mViewWidth, mViewHeight, paint, Canvas.ALL_SAVE_FLAG);
        if (linearGradient != null) {
            gradientMatrix.setTranslate(value, 0);
            // 为着色器设置矩阵
            linearGradient.setLocalMatrix(gradientMatrix);
            // 混合渲染 将两个效果叠加,使用PorterDuff叠加模式
            composeShader = new ComposeShader(bitmapShader, linearGradient, PorterDuff.Mode.MULTIPLY);
            paint.setShader(composeShader);
            canvas.drawRect(rectF, paint);
            paint.setXfermode(null);
        }
        canvas.restoreToCount(saveCount);
    }

    public void startAnimator() {
        valueAnimator = ValueAnimator.ofFloat(-mViewWidth, mViewWidth);
        valueAnimator.setDuration(1300);
        valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                value = (float) animation.getAnimatedValue();
                invalidate();
            }
        });
        valueAnimator.start();
    }


    /**
     * 停止动画
     */
    public void stopAnimator() {
        if (valueAnimator != null) {
            valueAnimator.cancel();
            valueAnimator = null;
        }
    }
}
