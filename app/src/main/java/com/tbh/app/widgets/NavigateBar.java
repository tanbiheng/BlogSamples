package com.tbh.app.widgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import com.tbh.app.R;
import com.tbh.app.impl.OnLimitClickHelper;
import com.tbh.app.impl.OnLimitClickListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * 导航栏
 * <p>
 * Created by tanbiheng on 2017/9/27.
 */

public class NavigateBar extends LinearLayout implements OnLimitClickListener {
    // 导航栏tab个数
    private int tabCount = 0;
    // 默认状态下tab图片集合
    private List<Integer> defaultImageList = null;
    // 选中状态下tab图片集合
    private List<Integer> focusImageList = null;
    // tab文字集合
    private List<String> tabTextList = null;
    // 导航栏tab默认字体颜色
    private int defaultTextColor = 0xFF555555;
    // 导航栏tab选中字体颜色
    private int focusTextColor = 0xFF000000;
    // 字体大小
    private float textSize = 14F;

    private Context context = null;

    private ArrayList<NavigateTab> tabList = null;

    private OnNavigateListener listener = null;

    public NavigateBar(Context context) {
        super(context);
        this.context = context;
        init(context, null);
    }

    public NavigateBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init(context, attrs);
    }

    public NavigateBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        defaultImageList = new ArrayList<>();
        focusImageList = new ArrayList<>();

        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.NavigateBar);

        tabCount = array.getInt(R.styleable.NavigateBar_tabCount, 0);

        int defaultImageArrResId = array.getResourceId(R.styleable.NavigateBar_defaultImageArray, 0);
        int focusImageArrResId = array.getResourceId(R.styleable.NavigateBar_focusImageArray, 0);
        int textArrResId = array.getResourceId(R.styleable.NavigateBar_textArray, 0);

        TypedArray defaultImageTypeArray = context.getResources().obtainTypedArray(defaultImageArrResId);
        if (defaultImageTypeArray != null && defaultImageTypeArray.length() > 0) {
            for (int i = 0; i < defaultImageTypeArray.length(); i++) {
                defaultImageList.add(defaultImageTypeArray.getResourceId(i, 0));
            }
        }

        TypedArray focusImageTypeArray = context.getResources().obtainTypedArray(focusImageArrResId);
        if (focusImageTypeArray != null && focusImageTypeArray.length() > 0) {
            for (int i = 0; i < focusImageTypeArray.length(); i++) {
                focusImageList.add(focusImageTypeArray.getResourceId(i, 0));
            }
        }

        String[] tabTextArray = context.getResources().getStringArray(textArrResId);
        if (tabTextArray != null && tabTextArray.length > 0) {
            tabTextList = Arrays.asList(tabTextArray);
        }

        defaultTextColor = array.getColor(R.styleable.NavigateBar_defaultTextColor, 0xFF555555);
        focusTextColor = array.getColor(R.styleable.NavigateBar_focusTextColor, 0xFF000000);
        textSize = array.getDimension(R.styleable.NavigateBar_textSize, 14F);
        // 回收
        array.recycle();

        setOrientation(HORIZONTAL);

        initTabs();
    }

    public void setOnNavigateListener(OnNavigateListener listener) {
        this.listener = listener;

        registerListener();
    }

    private void registerListener() {
        if (tabList != null && tabList.size() > 0) {
            for (NavigateTab tab : tabList) {
                tab.setOnClickListener(new OnLimitClickHelper(this));
            }
        }
    }

    public void setFocusPosition(int position) {
        for (int i = 0; i < tabList.size(); i++) {
            if (i == position) {
                tabList.get(i).setImageResource(focusImageList.get(i));
                tabList.get(i).setTextColor(focusTextColor);
            } else {
                tabList.get(i).setImageResource(defaultImageList.get(i));
                tabList.get(i).setTextColor(defaultTextColor);
            }
        }
    }

    private void initTabs() {
        this.removeAllViews();
        tabList = new ArrayList<>();

        if (tabCount > 0) {
            for (int i = 0; i < tabCount; i++) {
                NavigateTab navigateTab = new NavigateTab(context);
                navigateTab.setCurPosition(i);
                navigateTab.setImageResource(defaultImageList.get(i));
                navigateTab.setText(tabTextList.get(i));
                navigateTab.setTextSize(textSize);
                navigateTab.setTextColor(defaultTextColor);

                LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT,
                        LayoutParams.WRAP_CONTENT, 1.0F);
                navigateTab.setLayoutParams(params);

                tabList.add(navigateTab);
                this.addView(navigateTab);
            }
        }
    }

    @Override
    public void onClick(View view) {
        if (listener != null) {
            if (view instanceof NavigateTab) {
                int position = ((NavigateTab) view).getCurPosition();
                setFocusPosition(position);
                listener.onClickNavigate(position);
            }
        }
    }

    public interface OnNavigateListener {
        void onClickNavigate(int position);
    }
}
