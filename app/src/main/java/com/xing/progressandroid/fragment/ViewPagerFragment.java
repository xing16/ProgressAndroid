package com.xing.progressandroid.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xing.progressandroid.R;
import com.xing.progressandroid.adapter.ViewPagerAdapter;
import com.xing.progressandroid.customview.InnerViewPager;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ViewPagerFragment extends Fragment {


    public ViewPagerFragment() {
    }


    public static ViewPagerFragment newInstance() {
        return new ViewPagerFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_view_pager, container, false);
        InnerViewPager viewPager = view.findViewById(R.id.ivp_pager);
        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(HomeFragment.newInstance("Inner 01", 0xff00ff23));
        fragmentList.add(HomeFragment.newInstance("Inner 02", 0xffaa8922));
        fragmentList.add(HomeFragment.newInstance("Inner 03", 0xff3300ff));
        viewPager.setAdapter(new ViewPagerAdapter(getChildFragmentManager(), fragmentList));
        return view;
    }

}
