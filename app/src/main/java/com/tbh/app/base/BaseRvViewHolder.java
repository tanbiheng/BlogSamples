package com.tbh.app.base;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import butterknife.ButterKnife;

/**
 * RecylerView.ViewHolder基类
 *
 * Created by tanbiheng on 2017/10/10.
 */

public class BaseRvViewHolder extends RecyclerView.ViewHolder {
    public BaseRvViewHolder(View itemView) {
        super(itemView);

        ButterKnife.bind(this, itemView);
    }
}
