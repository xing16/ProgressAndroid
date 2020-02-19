package com.xing.progressandroid.adapter;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xing.progressandroid.R;
import com.xing.progressandroid.bean.MultiItem;

import java.util.List;

/**
 * 多类型布局
 */
public class MultiTypeAdapter extends BaseMultiItemQuickAdapter<MultiItem, BaseViewHolder> {


    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public MultiTypeAdapter(List<MultiItem> data) {
        super(data);
        addItemType(MultiItem.TEXT, R.layout.item_text);
        addItemType(MultiItem.IMG_TEXT, R.layout.item_img_text);
        addItemType(MultiItem.TEXT_IMG, R.layout.item_text_img);
    }

    @Override
    protected void convert(BaseViewHolder helper, MultiItem item) {
        switch (helper.getItemViewType()) {
            case MultiItem.TEXT:
                helper.setText(R.id.tv_title, item.getText());
                break;
            case MultiItem.IMG_TEXT:
                helper.setText(R.id.tv_title, item.getText()).setImageResource(R.id.iv_img_left, item.getResId());
                break;
            case MultiItem.TEXT_IMG:
                helper.setText(R.id.tv_title, item.getText()).setImageResource(R.id.iv_img_right, item.getResId());
                break;
        }
    }
}
