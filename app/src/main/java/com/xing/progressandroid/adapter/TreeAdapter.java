package com.xing.progressandroid.adapter;

import android.support.annotation.Nullable;
import android.util.TypedValue;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xing.progressandroid.bean.tree.INode;
import com.xing.progressandroid.bean.tree.TreeNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class TreeAdapter<T extends INode> extends BaseQuickAdapter<TreeNode<T>, BaseViewHolder> {

    public TreeAdapter(int layoutResId, @Nullable List<T> data) {
        super(layoutResId, convertTreeNode(data));
    }

    private static <T extends INode> List<TreeNode<T>> convertTreeNode(List<T> data) {
        List<TreeNode<T>> list = new ArrayList<>();
        Map<String, TreeNode> map = new HashMap<>();
        for (int i = 0; i < data.size(); i++) {
            INode iNode = data.get(i);
            TreeNode treeNode = new TreeNode(iNode);
            map.put(iNode.getId(), treeNode);
            list.add(treeNode);
        }
        Iterator<TreeNode<T>> iterator = list.iterator();
        while (iterator.hasNext()) {
            TreeNode<T> treeNode = iterator.next();
            String parentId = treeNode.getParentId();
            TreeNode parentNode = map.get(parentId);
            if (parentNode != null) {
                parentNode.getChildren().add(treeNode);
                treeNode.setParent(parentNode);
            }
        }
        return list;
    }

    @Override
    protected void convert(BaseViewHolder helper, TreeNode<T> item) {
        int level = item.getLevel();
        ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) helper.itemView.getLayoutParams();
        layoutParams.leftMargin = getLeftMargin() * level;
    }

    private int getLeftMargin() {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 20, mContext.getResources().getDisplayMetrics());
    }
}
