package com.xing.progressandroid.activity;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.xing.progressandroid.R;
import com.xing.progressandroid.adapter.ViewPagerAdapter;
import com.xing.progressandroid.customview.OutterViewPager;
import com.xing.progressandroid.fragment.HomeFragment;
import com.xing.progressandroid.fragment.ViewPagerFragment;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpager);
        OutterViewPager viewPager = findViewById(R.id.ovp_pager);
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(HomeFragment.newInstance("Android", 0xffff3456));
        fragments.add(HomeFragment.newInstance("iOS", 0xffacff12));
        fragments.add(HomeFragment.newInstance("Apple", 0xff2234ff));
        fragments.add(ViewPagerFragment.newInstance());
        fragments.add(HomeFragment.newInstance("RedHat", 0xffff0056));
        viewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager(), fragments));
    }
}
