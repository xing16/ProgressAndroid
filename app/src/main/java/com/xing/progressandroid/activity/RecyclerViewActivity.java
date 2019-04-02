package com.xing.progressandroid.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.xing.progressandroid.R;
import com.xing.progressandroid.adapter.RecyclerAdapter;
import com.xing.progressandroid.customview.LinearItemDecoration;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);

        RecyclerView recyclerView = findViewById(R.id.rv_recycler);
        List<String> list = new ArrayList<>();
        list.add("Android");
        list.add("Vue.js");
        list.add("iOS");
        list.add("Flutter");
        list.add("linux");
        list.add("MacBookPro");
        list.add("iPhoneX");
        list.add("pixel");
        list.add("windows");
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        LinearItemDecoration decoration = new LinearItemDecoration(this)
                .height(5)
                .color(Color.parseColor("#0000ff"))
                .margin(20, 20)
                .jumpPositions(new int[]{-1, 1, 3, 20});
        recyclerView.addItemDecoration(decoration);
        RecyclerAdapter adapter = new RecyclerAdapter(R.layout.item_recycler, list);
        recyclerView.setAdapter(adapter);

    }
}
