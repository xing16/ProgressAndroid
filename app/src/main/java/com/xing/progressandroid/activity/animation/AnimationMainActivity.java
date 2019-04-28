package com.xing.progressandroid.activity.animation;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.widget.Button;

import com.xing.progressandroid.R;
import com.xing.progressandroid.activity.BaseActivity;
import com.xing.progressandroid.customview.ViewWrapper;
import com.xing.progressandroid.utils.DensityUtil;

public class AnimationMainActivity extends BaseActivity {

    private Button btn;
    private ViewGroup.MarginLayoutParams layoutParams;
    private int screenWidth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation_main);
        btn = findViewById(R.id.btn_test);
        screenWidth = getResources().getDisplayMetrics().widthPixels;
        layoutParams = (ViewGroup.MarginLayoutParams) btn.getLayoutParams();
    }


    @Override
    protected void onRestart() {
        super.onRestart();
//        AnimatorSet animatorSet = new AnimatorSet();
//        animatorSet.setDuration(500);
//        animatorSet.setInterpolator(new LinearInterpolator());
//        animatorSet.playTogether(leftMarginAnimation(), widthAnimation());
//        animatorSet.start();
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    public void click(View view) {
        Intent intent = new Intent(this, AnimationDestActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.anim_no, R.anim.anim_no);
    }

    private ValueAnimator leftMarginAnimation() {
        ValueAnimator valueAnimator = ValueAnimator.ofInt(DensityUtil.dp2px(this, 30), DensityUtil.dp2px(this, 80));
        valueAnimator.setInterpolator(new AccelerateInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int value = (int) animation.getAnimatedValue();
                layoutParams.leftMargin = value;
                btn.setLayoutParams(layoutParams);
            }
        });
        return valueAnimator;
    }


    private ValueAnimator widthAnimation() {
        ViewWrapper viewWrapper = new ViewWrapper(btn);
        ObjectAnimator objectAnimator = ObjectAnimator.ofInt(viewWrapper, "width", screenWidth - DensityUtil.dp2px(this, 50), screenWidth - DensityUtil.dp2px(this, 90));
        objectAnimator.setInterpolator(new AccelerateInterpolator());
        return objectAnimator;
    }

}
