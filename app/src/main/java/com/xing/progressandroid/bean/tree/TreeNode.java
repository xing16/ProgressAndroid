package com.xing.progressandroid.bean.tree;

import java.util.ArrayList;
import java.util.List;

public class TreeNode<T extends INode> {

    private TreeNode parent;
    private List<TreeNode> children = new ArrayList<>();
    private int level;
    private T data;
    private boolean isExpand;

    public TreeNode(T data) {
        this.data = data;
    }

    public String getId() {
        if (data == null) {
            return "";
        }
        return data.getId();
    }

    public String getParentId() {
        if (data == null) {
            return "";
        }
        return data.getParentId();
    }

    public boolean isRoot() {
        return parent == null;
    }

    public int getLevel() {
        return parent == null ? 0 : parent.getLevel() + 1;
    }

    public T getData() {
        return data;
    }

    public TreeNode getParent() {
        return parent;
    }

    public void setParent(TreeNode parent) {
        this.parent = parent;
    }

    public List<TreeNode> getChildren() {
        return children;
    }

    public void setChildren(List<TreeNode> children) {
        this.children = children;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setData(T data) {
        this.data = data;
    }

    public boolean isExpand() {
        return isExpand;
    }

    public void setExpand(boolean expand) {
        isExpand = expand;
    }
}
