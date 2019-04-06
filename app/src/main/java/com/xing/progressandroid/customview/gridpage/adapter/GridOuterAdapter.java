package com.xing.progressandroid.customview.gridpage.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xing.progressandroid.R;
import com.xing.progressandroid.adapter.GridCategoryAdapter;
import com.xing.progressandroid.bean.GridCategoryBean;

import java.util.ArrayList;
import java.util.List;

public class GridOuterAdapter<T> extends RecyclerView.Adapter<GridOuterAdapter.ViewHolder> {

    private static final String TAG = "GridOuterAdapter";
    private Context context;
    private List<T> dataList;
    private final GridPageAdapter adapter;

    public GridOuterAdapter(Context context, List<T> list) {
        this.context = context;
        this.dataList = list;
        adapter = new GridPageAdapter(context);
    }

    @NonNull
    @Override
    public GridOuterAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_grid_category, viewGroup, false);
        return new GridOuterAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull GridOuterAdapter.ViewHolder viewHolder, int page) {
        Log.e(TAG, "onBindViewHolder: ");
        int itemCount = getItemCount();
        int end;
        if (page == itemCount - 1) {
            end = page * 10 + dataList.size() % 10;
        } else {
            end = page * 10 + 10;
        }
        final List<T> list = new ArrayList<>();
        for (int i = page * 10; i < end; i++) {
            list.add(dataList.get(i));
        }
        viewHolder.bindData(list, page);

    }

    private GridCategoryAdapter.OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(GridCategoryAdapter.OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    @Override
    public int getItemCount() {
        return dataList == null ? 0 : dataList.size() % 10 == 0 ? dataList.size() / 10 : dataList.size() / 10 + 1;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        RecyclerView recyclerView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            recyclerView = itemView.findViewById(R.id.rv_page);
            recyclerView.setLayoutManager(new GridLayoutManager(context, 5));
        }

        public void bindData(List<GridCategoryBean> list, int page) {
            GridPageAdapter adapter = new GridPageAdapter(context);
            adapter.setData(list, page, onItemClickListener);
            recyclerView.setAdapter(adapter);
        }
    }
}
