package com.xing.progressandroid.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xing.progressandroid.R;
import com.xing.progressandroid.bean.GridCategoryBean;
import com.xing.progressandroid.customview.gridpage.adapter.GridPageAdapter;

import java.util.ArrayList;
import java.util.List;

public class GridCategoryAdapter extends RecyclerView.Adapter<GridCategoryAdapter.ViewHolder> {

    private static final String TAG = "GridCategoryAdapter";
    private Context context;
    private List<GridCategoryBean> dataList;
    private final GridPageAdapter adapter;

    public GridCategoryAdapter(Context context, List<GridCategoryBean> list) {
        this.context = context;
        this.dataList = list;
        adapter = new GridPageAdapter(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_grid_category, viewGroup, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int page) {
        Log.e(TAG, "onBindViewHolder: ");
        int itemCount = getItemCount();
        int end;
        if (page == itemCount - 1) {
            end = page * 10 + dataList.size() % 10;
        } else {
            end = page * 10 + 10;
        }
        final List<GridCategoryBean> list = new ArrayList<>();
        for (int i = page * 10; i < end; i++) {
            list.add(dataList.get(i));
        }
        viewHolder.bindData(list, page);

    }

    public interface OnItemClickListener {
        void onItemClick(int position, View view);
    }

    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener listener) {
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
            adapter.setData(list, page, onItemClickListener);
            recyclerView.setAdapter(adapter);
        }
    }
}
