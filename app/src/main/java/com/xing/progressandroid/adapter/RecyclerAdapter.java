package com.xing.progressandroid.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.xing.progressandroid.R;
import com.xing.progressandroid.bean.ItemBean;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.RecyclerViewHolder> {


    private Context mContext;
    private List<ItemBean> list;
    private LayoutInflater inflater;

    public RecyclerAdapter(Context context, List<ItemBean> list) {
        this.mContext = context;
        this.list = list;
        inflater = LayoutInflater.from(context);
    }


    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
//        View itemView = inflater.inflate(R.layout.item_card_layout_manager, viewGroup, false);
        View itemView = inflater.inflate(R.layout.item_text, viewGroup, false);
        return new RecyclerViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder recyclerViewHolder, int i) {
        ItemBean itemBean = list.get(i);
        recyclerViewHolder.bindData(itemBean);
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }


    class RecyclerViewHolder extends RecyclerView.ViewHolder {

        private final TextView textView;
        private final ImageView imageView;

        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.tv_title);
            imageView = itemView.findViewById(R.id.iv_image);
        }

        public void bindData(ItemBean itemBean) {
            textView.setText(itemBean.getTitle());
            imageView.setImageDrawable(mContext.getResources().getDrawable(itemBean.getResId()));
        }
    }
}