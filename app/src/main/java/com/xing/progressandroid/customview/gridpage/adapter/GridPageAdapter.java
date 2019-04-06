package com.xing.progressandroid.customview.gridpage.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xing.progressandroid.R;
import com.xing.progressandroid.adapter.GridCategoryAdapter;
import com.xing.progressandroid.bean.GridCategoryBean;

import java.util.ArrayList;
import java.util.List;

public class GridPageAdapter extends RecyclerView.Adapter<GridPageAdapter.ViewHolder> {

    private Context context;
    private List<GridCategoryBean> dataList = new ArrayList<>();
    private int page;

    public GridPageAdapter(Context context) {
        this.context = context;
    }

    public GridPageAdapter(Context context, List<GridCategoryBean> list, int page, GridCategoryAdapter.OnItemClickListener listener) {
        this.context = context;
        this.dataList = list;
        this.page = page;
        this.listener = listener;
    }


    public void setData(List<GridCategoryBean> list, int page, GridCategoryAdapter.OnItemClickListener listener) {
        this.dataList = list;
        this.page = page;
        this.listener = listener;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public GridPageAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_page_category, viewGroup, false);
        return new GridPageAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull GridPageAdapter.ViewHolder viewHolder, final int position) {
        viewHolder.bindData(dataList.get(position));
        // 设置监听
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    int index = page * 10 + position;
                    listener.onItemClick(index, v);
                }
            }
        });
    }

    private GridCategoryAdapter.OnItemClickListener listener;

    @Override
    public int getItemCount() {
        return dataList == null ? 0 : dataList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        TextView icon;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            icon = itemView.findViewById(R.id.tv_icon);
            title = itemView.findViewById(R.id.tv_title);
        }

        public void bindData(GridCategoryBean bean) {
            title.setText(bean.getTitle());
            icon.setText(bean.getTitle());
        }
    }
}
