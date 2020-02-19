package com.xing.progressandroid.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.xing.progressandroid.R;
import com.xing.progressandroid.adapter.MyTreeAdapter;
import com.xing.progressandroid.adapter.TreeAdapter;
import com.xing.progressandroid.bean.tree.Employee;

import java.util.ArrayList;
import java.util.List;

public class TreeRecyclerViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tree_recycler_view);
        RecyclerView recyclerView = findViewById(R.id.tree_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        List<Employee> list = new ArrayList<>();
        list.add(new Employee("1001", null, "董事"));
        list.add(new Employee("2001", "1001", "CEO"));
        list.add(new Employee("2002", "1001", "CTO"));
        list.add(new Employee("2003", "1001", "财务部"));
        list.add(new Employee("2004", "1001", "研发部"));
        list.add(new Employee("3001", "2002", "销售部"));
        list.add(new Employee("3002", "2002", "运营部"));
        list.add(new Employee("4001", "2004", "Android"));
        list.add(new Employee("4002", "2004", "iOS"));
        list.add(new Employee("4003", "2004", "Java 后端"));

        recyclerView.setAdapter(new MyTreeAdapter(R.layout.item_tree, list));
    }
}
