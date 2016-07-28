package com.ssyijiu.netutil;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;

/**
 * Created by lixiaoming on 2016/7/21.
 */
public class NetUtil {

    private NetUtil() {
        /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    public static final int NET_WIFI = 1;
    public static final int NET_4G = 4;
    public static final int NET_3G = 3;
    public static final int NET_2G = 2;
    public static final int NET_UNKNOWN = 5;
    public static final int NET_NO = -1;


    /**
     * TelephonyManager.NETWORK_TYPE_GSM = 16
     */
    private static final int NETWORK_TYPE_GSM = 16;
    /**
     * TelephonyManager.NETWORK_TYPE_TD_SCDMA = 17
     */
    private static final int NETWORK_TYPE_TD_SCDMA = 17;
    /**
     * TelephonyManager.NETWORK_TYPE_IWLAN = 18
     */
    private static final int NETWORK_TYPE_IWLAN = 18;

    /**
     * Returns details about the currently active default data network.
     * @return a {@link NetworkInfo} object for the current default network
     *         or {@code null} if no default network is currently active.
     */
    private static NetworkInfo getActiveNetworkInfo(Context context) {
        ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();

        return mNetworkInfo;
    }

    /**
     * 判断网络是否可用
     *
     * @param context
     * @return
     */
    public static boolean isAvailable(Context context) {

        NetworkInfo mNetworkInfo = getActiveNetworkInfo(context);
        if (mNetworkInfo != null) {
            return mNetworkInfo.isAvailable();
        }
        return false;
    }

    /**
     * 获取当前的网络类型名称
     * @param context
     * @return NET_WIFI, NET_4G, NET_3G, NET_2G, NET_UNKNOWN, NET_NO
     */
    public static String getConnectedTypeName(Context context) {
        switch (getConnectedType(context)) {
            case NET_WIFI:
                return "NET_WIFI";
            case NET_4G:
                return "NET_4G";
            case NET_3G:
                return "NET_3G";
            case NET_2G:
                return "NET_2G";
            case NET_NO:
                return "NET_NO";
            default:
                return "NET_UNKNOWN";
        }
    }

    /**
     * 判断是不是WIFI网络
     *
     * @param context
     * @return
     */
    public static boolean isWIFI(Context context) {
        NetworkInfo mNetworkInfo = getActiveNetworkInfo(context);
        if (mNetworkInfo != null && mNetworkInfo.isAvailable()) {
            return mNetworkInfo.getType() == ConnectivityManager.TYPE_WIFI;
        }
        return false;
    }

    /**
     * 判断是不是4G网络
     *
     * @param context
     * @return
     */
    public static boolean is4G(Context context) {
        NetworkInfo mNetworkInfo = getActiveNetworkInfo(context);
        if (mNetworkInfo != null && mNetworkInfo.isAvailable()) {
            return mNetworkInfo.getSubtype() == TelephonyManager.NETWORK_TYPE_LTE;
        }
        return false;
    }



    /**
     * 获取当前否网络连接类型
     * @param context 上下文
     * @return NET_WIFI, NET_4G, NET_3G, NET_2G, NET_UNKNOWN, NET_NO
     */
    public static int getConnectedType(Context context) {
        int netType = NET_NO;
        NetworkInfo networkInfo = getActiveNetworkInfo(context);
        if (networkInfo != null && networkInfo.isAvailable()) {

            if(networkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
                netType = NET_WIFI;
            } else if(networkInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
                switch (networkInfo.getSubtype()) {

                    case NETWORK_TYPE_GSM:
                    case TelephonyManager.NETWORK_TYPE_GPRS:
                    case TelephonyManager.NETWORK_TYPE_CDMA:
                    case TelephonyManager.NETWORK_TYPE_EDGE:
                    case TelephonyManager.NETWORK_TYPE_1xRTT:
                    case TelephonyManager.NETWORK_TYPE_IDEN:
                        netType = NET_2G;
                        break;

                    case NETWORK_TYPE_TD_SCDMA:
                    case TelephonyManager.NETWORK_TYPE_EVDO_A:
                    case TelephonyManager.NETWORK_TYPE_UMTS:
                    case TelephonyManager.NETWORK_TYPE_EVDO_0:
                    case TelephonyManager.NETWORK_TYPE_HSDPA:
                    case TelephonyManager.NETWORK_TYPE_HSUPA:
                    case TelephonyManager.NETWORK_TYPE_HSPA:
                    case TelephonyManager.NETWORK_TYPE_EVDO_B:
                    case TelephonyManager.NETWORK_TYPE_EHRPD:
                    case TelephonyManager.NETWORK_TYPE_HSPAP:
                        netType = NET_3G;
                        break;

                    case NETWORK_TYPE_IWLAN:
                    case TelephonyManager.NETWORK_TYPE_LTE:
                        netType = NET_4G;
                        break;
                    default:

                        String subtypeName = networkInfo.getSubtypeName();
                        if (subtypeName.equalsIgnoreCase("TD-SCDMA")
                                || subtypeName.equalsIgnoreCase("WCDMA")
                                || subtypeName.equalsIgnoreCase("CDMA2000")) {
                            netType = NET_3G;
                        } else {
                            netType = NET_UNKNOWN;
                        }
                        break;
                }
            } else {
                netType = NET_UNKNOWN;
            }
        }
        return netType;
    }
    /**
     * 打开设置界面
     */
    public static void openSetting(Context context) {
        Intent intent = new Intent();
        intent.setAction(android.provider.Settings.ACTION_SETTINGS );
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        context.startActivity(intent);
    }
}
