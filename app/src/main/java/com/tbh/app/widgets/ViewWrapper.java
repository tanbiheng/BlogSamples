package com.tbh.app.widgets;

import android.view.View;


/**
 * ObjectAnimator 执行动画时需要对应属性有get set方法
 * 因此通过 ViewWrapper 提供get set 方法封装view
 *
 * Created by tanbiheng on 2017/10/10.
 */
public class ViewWrapper {
    private View mTarget;

    public ViewWrapper(View target) {
        mTarget = target;
    }

    public int getWidth() {
        return mTarget.getLayoutParams().width;
    }

    public void setWidth(int width) {
        mTarget.getLayoutParams().width = width;
        mTarget.requestLayout();
    }

    public int getHeight() {
        return mTarget.getLayoutParams().height;
    }

    public void setHeight(int height) {
        mTarget.getLayoutParams().height = height;
        mTarget.requestLayout();
    }
}
