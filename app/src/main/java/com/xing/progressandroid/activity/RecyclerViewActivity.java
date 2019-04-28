package com.xing.progressandroid.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.xing.progressandroid.R;
import com.xing.progressandroid.adapter.MultiTypeAdapter;
import com.xing.progressandroid.bean.MultiItem;
import com.xing.progressandroid.customview.LinearItemDecoration;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);

        RecyclerView recyclerView = findViewById(R.id.rv_recycler);
        List<MultiItem> list = new ArrayList<>();
        list.add(new MultiItem("Android", MultiItem.TEXT));
        list.add(new MultiItem("Vue.js", R.drawable.meizi, MultiItem.TEXT_IMG));
        list.add(new MultiItem("iOS", R.drawable.logo, MultiItem.IMG_TEXT));
        list.add(new MultiItem("Flutter", R.drawable.meizi, MultiItem.IMG_TEXT));
        list.add(new MultiItem("linux", MultiItem.TEXT));
        list.add(new MultiItem("MacBookPro", MultiItem.TEXT));
        list.add(new MultiItem("iPhoneX", R.drawable.meizi, MultiItem.TEXT_IMG));
        list.add(new MultiItem("pixel", MultiItem.TEXT));
        list.add(new MultiItem("windows", MultiItem.TEXT));
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        LinearItemDecoration decoration = new LinearItemDecoration(this)
                .height(5)
                .color(Color.parseColor("#0000ff"))
                .margin(20, 20)
                .jumpPositions(new int[]{-1, 1, 3, 20});
        recyclerView.addItemDecoration(decoration);
        MultiTypeAdapter adapter = new MultiTypeAdapter(list);
        recyclerView.setAdapter(adapter);

    }
}
