package com.tbh.app.widgets;

import android.content.Context;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tbh.app.R;

/**
 * 导航栏Tab
 * <p>
 * Created by tanbiheng on 2017/9/27.
 */

public class NavigateTab extends LinearLayout {

    private ImageView navTabIv = null;
    private TextView navTabTv = null;

    private int curPosition = 0;

    public NavigateTab(Context context) {
        super(context);
        init(context, null);
    }

    public NavigateTab(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public NavigateTab(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.navigate_tab_layout, this);

        navTabIv = (ImageView) view.findViewById(R.id.navigate_tab_iv);
        navTabTv = (TextView) view.findViewById(R.id.navigate_tab_tv);
    }

    public void setCurPosition(int position){
        curPosition = position;
    }

    public int getCurPosition() {
        return curPosition;
    }

    public void setImageResource(int resId){
        navTabIv.setImageResource(resId);
    }

    public void setText(String text){
        navTabTv.setText(text);
    }

    public void setTextColor(int textColor){
        navTabTv.setTextColor(textColor);
    }

    public void setTextSize(float textSize){
        navTabTv.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
    }
}
