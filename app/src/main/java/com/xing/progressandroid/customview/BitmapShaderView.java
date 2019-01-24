package com.xing.progressandroid.customview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

import com.xing.progressandroid.R;

/**
 * 图片着色
 */
public class BitmapShaderView extends View {

    private Paint paint;
    private BitmapShader bitmapShader;
    private int mViewWidth;
    private int mViewHeight;
    private Bitmap bitmap;


    public BitmapShaderView(Context context) {
        super(context);
        init();
    }

    public BitmapShaderView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.key);
        int bitmapWidth = bitmap.getWidth();
        int bitmapHeight = bitmap.getHeight();
        bitmapShader = new BitmapShader(bitmap, Shader.TileMode.REPEAT, Shader.TileMode.CLAMP);
        paint.setShader(bitmapShader);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mViewWidth = w;
        mViewHeight = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        canvas.drawRect(0, 0, mViewWidth, mViewHeight, paint);
//        canvas.drawCircle(mViewWidth / 2f, mViewHeight / 2f, 150, paint);
        paint.setTextSize(160f);
        canvas.drawText("甭管什么东西", 0, mViewHeight / 2f, paint);
    }
}
