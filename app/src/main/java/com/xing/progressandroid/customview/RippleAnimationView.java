package com.xing.progressandroid.customview;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;

public class RippleAnimationView extends View {

    private int startX;
    private int startY;
    private Paint paint;
    private ViewGroup rootView;
    private DisplayMetrics displayMetrics;
    private double rippleMaxRadius;
    private Bitmap bitmap;
    private Bitmap backgroundBitmap;


//    public RippleAnimationView(Context context) {
//        super(context);
//    }

    public RippleAnimationView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    private RippleAnimationView(Context context, int startX, int startY) {
        super(context);
        this.startX = startX;
        this.startY = startY;
        displayMetrics = context.getResources().getDisplayMetrics();
        rootView = (ViewGroup) ((Activity) context).getWindow().getDecorView();


        rippleMaxRadius = getRippleMaxRadius(startX, startY);
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));


    }

    private double getRippleMaxRadius(int startX, int startY) {
        int halfScreenWidth = displayMetrics.widthPixels / 2;
        int halfScreenHeight = displayMetrics.heightPixels / 2;
        int endX = startX > halfScreenWidth ? 0 : displayMetrics.widthPixels;
        int endY = startY > halfScreenHeight ? 0 : displayMetrics.heightPixels;
        return Math.sqrt((endX - startX) * (endX - startX) + (endY - startY) * (endY - startY));

    }

    public static RippleAnimationView create(View clickView) {
        int[] location = new int[2];
        clickView.getLocationOnScreen(location);
        int startX = location[0] + clickView.getMeasuredWidth() / 2;
        int startY = location[1] + clickView.getMeasuredHeight() / 2;
        RippleAnimationView rippleAnimationView = new RippleAnimationView(clickView.getContext(), startX, startY);
        return rippleAnimationView;
    }

    public void start() {
        if (backgroundBitmap != null && !backgroundBitmap.isRecycled()) {
            backgroundBitmap.recycle();
        }
        rootView.setDrawingCacheEnabled(true);
        Bitmap background = rootView.getDrawingCache();
        backgroundBitmap = Bitmap.createBitmap(background);
        rootView.setDrawingCacheEnabled(false);
        attachToRootView();

    }

    private void attachToRootView() {
        rootView.addView(this);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(backgroundBitmap, 0, 0, paint);
        canvas.drawCircle(0, 0, 600, paint);
    }
}
