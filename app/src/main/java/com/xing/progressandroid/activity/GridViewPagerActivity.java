package com.xing.progressandroid.activity;

import android.graphics.Color;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.xing.progressandroid.R;
import com.xing.progressandroid.bean.GridCategoryBean;
import com.xing.progressandroid.customview.gridviewpager.GridRecyclerAdapter;
import com.xing.progressandroid.customview.gridviewpager.GridViewPager;
import com.xing.progressandroid.customview.gridviewpager.GridViewPagerAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GridViewPagerActivity extends AppCompatActivity {

    private Random random;
    private int[] colors = {0xffec407a, 0xffab47bc, 0xff29b6f6, 0xff7e57c2
            , 0xffe24073, 0xffee8360, 0xff26a69a, 0xffef5350,
            0xff2baf2b,
            0xffffa726};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_view_pager);
        GridViewPager gridViewPager = findViewById(R.id.gvp_viewpager);
        final List<GridCategoryBean> list = new ArrayList<>();
        for (int i = 0; i < 21; i++) {
            list.add(new GridCategoryBean(i + "", R.drawable.meizi));
        }
        gridViewPager.setOnGridItemClickListener(new GridViewPager.OnGridItemClickListener() {
            @Override
            public void onGridItemClick(int position, View view) {
                Toast.makeText(GridViewPagerActivity.this, list.get(position).toString(), Toast.LENGTH_SHORT).show();
            }
        });
        random = new Random();
        gridViewPager.setAdapter(new GridViewPagerAdapter<GridCategoryBean>(list) {

            @Override
            public void bindData(GridRecyclerAdapter.ViewHolder viewHolder, GridCategoryBean data, int position) {
                int color = Color.argb(255, random.nextInt(256), random.nextInt(256), random.nextInt(256));
                Log.e("debbug", "bindData: position =  " + position + ", color = " + color);
                ShapeDrawable shapeDrawable = new ShapeDrawable();
                shapeDrawable.setShape(new OvalShape());
                shapeDrawable.getPaint().setColor(colors[position % colors.length]);
                viewHolder.setText(R.id.tv_title, data.getTitle())
                        .setText(R.id.tv_icon, data.getTitle())
                        .setBackground(R.id.tv_icon, shapeDrawable);

            }
        });
    }
}
