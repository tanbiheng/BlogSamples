package com.tbh.app.widgets;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tbh.app.R;
import com.tbh.app.impl.OnLimitClickHelper;
import com.tbh.app.impl.OnLimitClickListener;
import com.tbh.app.utils.Utils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by tanbiheng on 2017/10/13.
 */

public class SearchTitleBar extends LinearLayout implements OnLimitClickListener {
    private final int ANIM_TYPE_START = 1;
    private final int ANIM_TYPE_RESET = 2;

    @BindView(R.id.holder_view)
    View holderView;

    @BindView(R.id.search_layout)
    LinearLayout searchLayout;

    @BindView(R.id.search_tv)
    TextView searchTv;

    @BindView(R.id.search_iv)
    ImageView searchIv;

    @BindView(R.id.search_edit)
    TextView searchEt;

    @BindView(R.id.divide_view)
    View divideView;

    private OnSearchTitleBarListener onSearchTitleBarListener = null;

    private ViewWrapper wrapper = null;
    private ObjectAnimator animator = null;
    private int curAnimType = 0;

    private int holderViewWidth = 0;
    private int searchLayoutInitWidth = 0;

    public void setOnSearchTitleBarListener(OnSearchTitleBarListener onSearchTitleBarListener) {
        this.onSearchTitleBarListener = onSearchTitleBarListener;
    }

    public SearchTitleBar(Context context) {
        super(context);
        builder(context);
    }

    public SearchTitleBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        builder(context);
    }

    public SearchTitleBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        builder(context);
    }

    private void builder(Context context) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.search_title_bar_layout, null);
        view.setLayoutParams(new LayoutParams(
                LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 1.0f));

        searchLayoutInitWidth = context.getResources().getDimensionPixelSize(R.dimen.search_init_width);

        ButterKnife.bind(this, view);

        divideView.setVisibility(View.GONE);

        wrapper = new ViewWrapper(searchLayout);

        this.addView(view);

        searchLayout.setOnClickListener(new OnLimitClickHelper(this));
    }

    public View getSearchLayout() {
        return searchLayout;
    }

    public View getSearchTv() {
        return searchTv;
    }

    public View getSearchEt() {
        return searchEt;
    }

    public View getSearchIv() {
        return searchIv;
    }

    public void setHint(String hint) {
        if (TextUtils.isEmpty(hint)) {
            searchEt.setPadding(0, 0, 0, 0);
        } else {
            searchEt.setPadding(Utils.getResourcesDimension(R.dimen.dimen_size_5), 0, 0, 0);
        }
        searchEt.setText(hint);
    }

    public void showDivide() {
        divideView.setVisibility(View.VISIBLE);
    }

    public void hideDivide() {
        divideView.setVisibility(View.GONE);
    }

    public void performAnimate(final boolean startOrReset) {
        if (holderViewWidth == 0) {
            holderViewWidth = holderView.getWidth();
        }

        int animType = 0;
        int endWidth = 0;

        if (startOrReset) {
            animType = ANIM_TYPE_START;
            endWidth = holderViewWidth;
            searchLayout.setBackgroundResource(R.drawable.shape_search_gray);
        } else {
            animType = ANIM_TYPE_RESET;
            endWidth = searchLayoutInitWidth;
            searchLayout.setBackgroundResource(R.drawable.shape_search_white);
        }

        if (curAnimType != animType) {
            if (animator != null) {
                animator.cancel();
                curAnimType = animType;
            }
        } else {
            return;
        }


        animator = ObjectAnimator.ofInt(wrapper, "width", endWidth);
        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                hideDivide();

                if (startOrReset) {
                    setHint("输入搜索关键字");
                } else {
                    setHint("搜索");
                }
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                if (curAnimType == ANIM_TYPE_START) {
                    showDivide();
                }
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });

        animator.setDuration(300);
        animator.start();
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.search_layout) {
            if (onSearchTitleBarListener != null) {
                onSearchTitleBarListener.onClickSearch();
            }
        }
    }

    public interface OnSearchTitleBarListener {

        void onClickSearch();
    }
}
