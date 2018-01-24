package com.tbh.app.utils;

import com.tbh.app.base.MyApplication;

/**
 * Created by tanbiheng on 2018/1/24.
 */

public class Utils {

    /**
     * 获取资源文件里的dimension
     *
     * @param resId
     * @return
     */
    public static int getResourcesDimension(int resId) {
        return MyApplication.getInstance().getResources()
                .getDimensionPixelSize(resId);
    }

    /**
     * 获取资源文件里的color
     *
     * @param resId
     * @return
     */
    public static int getResourcesColor(int resId) {
        return MyApplication.getInstance().getResources().getColor(resId);
    }

    /**
     * 获取资源文件里的字符串
     *
     * @param resId
     * @return
     */
    public static String getResourcesString(int resId) {
        return MyApplication.getInstance().getResources().getString(resId);
    }

    /**
     * 获取资源文件里的字符串数组
     *
     * @param resId
     * @return
     */
    public static String[] getResourcesArrString(int resId) {
        return MyApplication.getInstance().getResources()
                .getStringArray(resId);
    }
}
