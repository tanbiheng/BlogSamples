package com.tbh.app.impl;

import android.view.View;

import java.util.Calendar;

/**
 * 限制快速点击的ClickListenerHelper
 *
 * Created by tanbiheng on 2017/9/27.
 */

public class OnLimitClickHelper implements View.OnClickListener {
    public static final int LIMIT_TIME = 300;
    private long lastClickTime = 0;
    private OnLimitClickListener onLimitClickListener = null;

    public OnLimitClickHelper(OnLimitClickListener onLimitClickListener){
        this.onLimitClickListener = onLimitClickListener;
    }

    @Override
    public void onClick(View view) {
        long curTime = Calendar.getInstance().getTimeInMillis();
        if (curTime - lastClickTime > LIMIT_TIME) {
            lastClickTime = curTime;
            if(onLimitClickListener != null){
                onLimitClickListener.onClick(view);
            }
        }
    }
}
