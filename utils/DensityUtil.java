package com.ssyijiu.ahelper;


import android.content.Context;
import android.content.res.Resources;

/**
 * Created by ssyijiu on 2016/8/16.
 * Github: ssyijiu
 * E-mail: lxmyijiu@163.com
 */
public class DensityUtil {

    private DensityUtil(){
		/* cannot be instantiated */
        throw new UnsupportedOperationException("DensityUtil cannot be instantiated !");
    }

    private static float density = -1F;
    private static float scaledDensity = -1F;
    private static int widthPixels = -1;
    private static int heightPixels = -1;


    public static int dp2px(Context context,float dpValue) {
        return (int) (dpValue * getDensity(context) + 0.5F);
    }

    public static int px2dp(Context context,float pxValue) {
        return (int) (pxValue / getDensity(context) + 0.5F);
    }


    public static int sp2px(Context context, float spValue){
        return (int) (spValue * getScaledDensity(context) + 0.5F);
    }

    public static int getScreenWidth(Context context) {
        if (widthPixels <= 0) {
            widthPixels = context.getResources().getDisplayMetrics().widthPixels;
        }
        return widthPixels;
    }


    public static int getScreenHeight(Context context) {
        if (heightPixels <= 0) {
            heightPixels = context.getResources().getDisplayMetrics().heightPixels;
        }
        return heightPixels;
    }

    private static float getDensity(Context context) {
        if (density <= 0F) {
            density = context.getResources().getDisplayMetrics().density;
        }
        return density;
    }

    private static float getScaledDensity(Context context) {
        if (scaledDensity <= 0F) {
            scaledDensity = context.getResources().getDisplayMetrics().scaledDensity;
        }
        return scaledDensity;
    }
}
