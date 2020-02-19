package com.xing.progressandroid.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseViewHolder;
import com.xing.progressandroid.R;
import com.xing.progressandroid.bean.tree.Employee;
import com.xing.progressandroid.bean.tree.TreeNode;

import java.util.List;

public class MyTreeAdapter extends TreeAdapter<Employee> {

    public MyTreeAdapter(int layoutResId, @Nullable List<Employee> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, TreeNode<Employee> item) {
        super.convert(helper, item);
        helper.setText(R.id.tv_text, item.getData().getName());
    }
}
