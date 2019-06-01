package com.xing.progressandroid.activity.animation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.xing.progressandroid.R;
import com.xing.progressandroid.customview.MyScrollView;

import java.util.ArrayList;
import java.util.List;

public class ScrollListViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll_list_view);
        ListView listView = findViewById(R.id.list_view);
        MyScrollView scrollView = findViewById(R.id.scroll_view);
        TextView textView = findViewById(R.id.tv_header);
        scrollView.setListView(listView, textView);
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            list.add("test data  = " + i);
        }
        listView.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list.toArray(new String[list.size()])));
    }
}
