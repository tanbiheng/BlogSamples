package com.tbh.app.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tbh.app.R;
import com.tbh.app.base.BaseRvAdapter;
import com.tbh.app.ui.viewholder.SearchListViewHolder;
import com.tbh.app.ui.viewholder.SearchBannerViewHolder;

import java.util.ArrayList;

/**
 * Created by tanbiheng on 2018/1/24.
 */

public class SearchTitleBarAdapter extends BaseRvAdapter {
    private final int TYPE_BANNER = 1;
    private final int TYPE_NORMAL = 2;

    private Context context = null;
    private ArrayList<Object> dataList = null;

    public SearchTitleBarAdapter(Context context, ArrayList<Object> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        RecyclerView.ViewHolder holder = null;
        if(viewType == TYPE_BANNER){
            View view = LayoutInflater.from(context).inflate(R.layout.item_search_banner,
                    parent, false);
            holder = new SearchBannerViewHolder(view);
        } else {
            View view = LayoutInflater.from(context).inflate(R.layout.item_search_list,
                    parent, false);
            holder = new SearchListViewHolder(view);
        }

        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
    }

    @Override
    public int getItemCount() {
        return dataList == null ? 0 : dataList.size();
    }

    @Override
    public int getItemViewType(int position) {
        if(position == 0){
            return TYPE_BANNER;
        } else {
            return TYPE_NORMAL;
        }
    }
}
