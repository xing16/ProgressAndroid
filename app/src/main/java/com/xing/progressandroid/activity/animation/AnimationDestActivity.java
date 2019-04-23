package com.xing.progressandroid.activity.animation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.FloatEvaluator;
import android.animation.ObjectAnimator;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.renderscript.Sampler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xing.progressandroid.R;
import com.xing.progressandroid.customview.ViewWrapper;
import com.xing.progressandroid.utils.DensityUtil;

public class AnimationDestActivity extends AppCompatActivity {

    private ViewGroup.MarginLayoutParams lp;
    private ViewGroup.MarginLayoutParams searchLayoutParams;
    private ImageView backImgView;
    private TextView searchTxtView;
    private EditText inputEditText;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation_dest);
        backImgView = findViewById(R.id.iv_back);
        searchTxtView = findViewById(R.id.tv_search);
        inputEditText = findViewById(R.id.et_input);
        button = findViewById(R.id.btn);
        backImgView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        final int screenWidth = getResources().getDisplayMetrics().widthPixels;
        lp = (ViewGroup.MarginLayoutParams) inputEditText.getLayoutParams();
        searchLayoutParams = (ViewGroup.MarginLayoutParams) searchTxtView.getLayoutParams();
        final int leftStart = getIntent().getIntExtra("left", 0);
//        lp.leftMargin = leftStart;
        inputEditText.setLayoutParams(lp);

        inputEditText.post(new Runnable() {
            @Override
            public void run() {
//                widthAnimation(screenWidth - left - right, screenWidth);
                int leftEnd = backImgView.getWidth();
                int rightMargin = searchTxtView.getWidth();
                int right = inputEditText.getRight();
                Log.e("debug", "run: " + right);
                play(dp2px(10), leftEnd, dp2px(10), dp2px(80));
            }
        });
    }


    private ObjectAnimator widthAnimation(int start, int end) {
        ViewWrapper viewWrapper = new ViewWrapper(inputEditText);
        ObjectAnimator objectAnimator = ObjectAnimator.ofInt(viewWrapper, "width", start, end);
        objectAnimator.setInterpolator(new LinearInterpolator());
        objectAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int animatedValue = (int) animation.getAnimatedValue();
                Log.e("debug", "onAnimationUpdate: " + animatedValue);
            }
        });
        objectAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                int right = inputEditText.getRight();
                Log.e("debug", "run: " + right);
            }
        });
        return objectAnimator;
    }


    private ValueAnimator rightAnimation(int rightStart, int rightEnd) {
        ValueAnimator valueAnimator = ValueAnimator.ofInt(rightStart, rightEnd);
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int value = (int) animation.getAnimatedValue();
                lp.rightMargin = value;
                inputEditText.setLayoutParams(lp);
            }
        });
        return valueAnimator;
    }


    private void play(int leftStart, int leftEnd, int rightStart, int rightEnd) {
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setDuration(300);
        animatorSet.setInterpolator(new AccelerateInterpolator());
        animatorSet.playTogether(leftMarginAnimation(leftStart, leftEnd), rightAnimation(rightStart, rightEnd), leftSearchAnimation());
        animatorSet.start();

    }

    private ObjectAnimator translateAnimation() {
        ObjectAnimator objectAnimator = ObjectAnimator.ofObject(button, "translationX", new FloatEvaluator(), 0, -dp2px(80));
        objectAnimator.setInterpolator(new LinearInterpolator());
        return objectAnimator;
    }


    private ValueAnimator leftMarginAnimation(int leftStart, int leftEnd) {
        ValueAnimator valueAnimator = ValueAnimator.ofInt(leftStart, leftEnd);
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int value = (int) animation.getAnimatedValue();
                lp.leftMargin = value;
                inputEditText.setLayoutParams(lp);
            }
        });
        return valueAnimator;
    }

    private ValueAnimator leftSearchAnimation() {
        ValueAnimator valueAnimator = ValueAnimator.ofInt(0, -dp2px(70));
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int value = (int) animation.getAnimatedValue();
                searchLayoutParams.leftMargin = value;
                searchTxtView.setLayoutParams(searchLayoutParams);
            }
        });
        return valueAnimator;
    }


    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.anim_no, R.anim.anim_no);
    }

    private int dp2px(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, getResources().getDisplayMetrics());
    }
}
