package com.xing.progressandroid.activity;

import android.os.Bundle;
import android.view.View;

import com.xing.progressandroid.R;
import com.xing.progressandroid.activity.animation.AnimationMainActivity;
import com.xing.progressandroid.activity.animation.ScrollListViewActivity;
import com.xing.progressandroid.livedata.LiveDataActivity;
import com.xing.progressandroid.rxjava.RxjavaActivity;
import com.xing.progressandroid.utils.IntentUtil;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        NoNetworkTip noNetworkTip = new NoNetworkTip(this);
//        noNetworkTip.show();
    }

    public void click(View view) {
        switch (view.getId()) {
            case R.id.btn_bezier:
                IntentUtil.startActivity(this, BezierActivity.class);
                break;
            case R.id.btn_xfermode:
                IntentUtil.startActivity(this, XfermodeActivity.class);
                break;
            case R.id.btn_shader:
                IntentUtil.startActivity(this, ShaderActivity.class);
                break;
            case R.id.btn_app_upgrade:
                IntentUtil.startActivity(this, AppUpgradeActivity.class);
                break;
            case R.id.btn_flow:
                IntentUtil.startActivity(this, FlowLayoutActivity.class);
                break;
            case R.id.btn_recycler:
                IntentUtil.startActivity(this, RecyclerViewActivity.class);
                break;
            case R.id.btn_grid_recycler:
                IntentUtil.startActivity(this, GridCategoryActivity.class);
                break;
            case R.id.btn_grid_viewpager:
                IntentUtil.startActivity(this, GridViewPagerActivity.class);
                break;
            case R.id.btn_animation:
                IntentUtil.startActivity(this, AnimationMainActivity.class);
                break;
            case R.id.btn_live_data:
                IntentUtil.startActivity(this, LiveDataActivity.class);
                break;
            case R.id.btn_rxjava:
                IntentUtil.startActivity(this, RxjavaActivity.class);
                break;
            case R.id.btn_gradient_status_bar:
                IntentUtil.startActivity(this, GradientStatusBarActivity.class);
                break;
            case R.id.btn_touch_test:
                IntentUtil.startActivity(this, EventTestActivity.class);
                break;
            case R.id.btn_pwd_keyboard:
                IntentUtil.startActivity(this, PwdKeyboardActivity.class);
                break;
            case R.id.btn_viewpager:
                IntentUtil.startActivity(this, ViewPagerActivity.class);
                break;
            case R.id.btn_scroll_list:
                IntentUtil.startActivity(this, ScrollListViewActivity.class);
                break;
            case R.id.btn_layout_manager:
                IntentUtil.startActivity(this, LayoutManagerActivity.class);
                break;
            case R.id.btn_snap_helper:
                IntentUtil.startActivity(this, SnapHelperActivity.class);
                break;
            case R.id.btn_nested_scroll:
                IntentUtil.startActivity(this, NestedScrollActivity.class);
                break;
            case R.id.btn_tree:
                IntentUtil.startActivity(this, TreeRecyclerViewActivity.class);
                break;
            case R.id.btn_ripple_animation:
                IntentUtil.startActivity(this, RippleAnimationActivity.class);
                break;
            case R.id.btn_db:
                IntentUtil.startActivity(this, DbActivity.class);
                break;
            case R.id.btn_calendar:
                IntentUtil.startActivity(this, CalendarActivity.class);
                break;
        }

    }
}
