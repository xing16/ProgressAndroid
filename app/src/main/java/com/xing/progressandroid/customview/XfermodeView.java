package com.xing.progressandroid.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.xing.progressandroid.R;

public class XfermodeView extends View {

    private Paint textPaint;
    private Paint paint;
    private RectF dstRect;
    private RectF srcRect;
    private int mode;


    public XfermodeView(Context context) {
        this(context, null);
    }

    public XfermodeView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public XfermodeView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        readAttrs(context, attrs);
        init();
    }

    private void readAttrs(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.XfermodeView);
        mode = typedArray.getInt(R.styleable.XfermodeView_mode, 0);
        typedArray.recycle();
    }

    private void init() {
        textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        textPaint.setColor(Color.BLACK);
        textPaint.setTextSize(60);

        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStyle(Paint.Style.FILL);

        dstRect = new RectF(20, 20, 150, 150);
        srcRect = new RectF(50, 50, 250, 250);

//        setLayerType(View.LAYER_TYPE_SOFTWARE, null);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.DKGRAY);

        //将绘制操作保存到新的图层，因为图像合成是很昂贵的操作，将用到硬件加速，这里将图像合成的处理放到离屏缓存中进行
        int saveCount = canvas.saveLayer(0, 0, getWidth(), getHeight(), paint, Canvas.ALL_SAVE_FLAG);

        // 绘制 dest
        paint.setColor(Color.RED);
        canvas.drawRect(dstRect, paint);
        // 设置 xfermode
        if (mode != 0) {
            paint.setXfermode(new PorterDuffXfermode(getMode(mode)));
        }
        // 绘制 src
        paint.setColor(Color.BLUE);
        canvas.drawRect(srcRect, paint);
        paint.setXfermode(null);

        canvas.restoreToCount(saveCount);
        canvas.drawText(getMode(mode).toString(), getWidth() - 300, getHeight() / 2f, textPaint);
    }

    private PorterDuff.Mode getMode(int value) {
        PorterDuff.Mode mode = null;
        switch (value) {
            case 1:
                mode = PorterDuff.Mode.CLEAR;
                break;
            case 2:
                mode = PorterDuff.Mode.SRC;
                break;
            case 3:
                mode = PorterDuff.Mode.DST;
                break;
            case 4:
                mode = PorterDuff.Mode.SRC_OVER;
                break;
            case 5:
                mode = PorterDuff.Mode.DST_OVER;
                break;
            case 6:
                mode = PorterDuff.Mode.SRC_IN;
                break;
            case 7:
                mode = PorterDuff.Mode.DST_IN;
                break;
            case 8:
                mode = PorterDuff.Mode.SRC_OUT;
                break;
            case 9:
                mode = PorterDuff.Mode.DST_OUT;
                break;
            case 10:
                mode = PorterDuff.Mode.SRC_ATOP;
                break;
            case 11:
                mode = PorterDuff.Mode.DST_ATOP;
                break;
            case 12:
                mode = PorterDuff.Mode.XOR;
                break;
            case 13:
                mode = PorterDuff.Mode.DARKEN;
                break;
            case 14:
                mode = PorterDuff.Mode.LIGHTEN;
                break;
            case 15:
                mode = PorterDuff.Mode.MULTIPLY;
                break;
            case 16:
                mode = PorterDuff.Mode.SCREEN;
                break;
        }
        return mode;
    }
}
