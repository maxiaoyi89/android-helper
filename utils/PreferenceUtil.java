package com.ssyijiu.alertdialog.ui;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferenceUtil {
	
	private volatile static SharedPreferences sp;

	private PreferenceUtil(){
		/* cannot be instantiated */
		throw new UnsupportedOperationException("cannot be instantiated");
	}



	private static SharedPreferences getInstance(Context context) {
		if(sp == null) {
			synchronized (PreferenceUtil.class) {
				if(sp == null) {
					sp = context.getSharedPreferences("config", Context.MODE_PRIVATE);
				}
			}
		}
		return sp;
	}


	public static void putBoolean(Context context,String key, boolean value) {
		sp = getInstance(context);
		sp.edit().putBoolean(key, value).apply();
	}
	
	public static void putString(Context context,String key, String value) {
		sp = getInstance(context);
		sp.edit().putString(key, value).apply();
	}
	
	public static void putInt(Context context,String key, int value) {
		sp = getInstance(context);
		sp.edit().putInt(key, value).apply();
	}
	
	

	public static boolean getBoolean(Context context,String key, boolean defValue) {
		sp = getInstance(context);
		return sp.getBoolean(key, defValue);
	}
	
	public static String getString(Context context,String key, String defValue) {
		sp = getInstance(context);
		return sp.getString(key, defValue);
	}
	
	public static int getInt(Context context,String key, int defValue) {
		sp = getInstance(context);
		return sp.getInt(key, defValue);
	}
	
	public static void remove(Context context, String key) {
		sp = getInstance(context);
		sp.edit().remove(key).apply();
	}


	// 如果需要一个单独的SharedPreferences来保存某些数据，例如：token，可以这样：
	private static final String PRE_TOKEN = "token";
	private static SharedPreferences getInstance(Context context, String preferenceName) {
		if(sp == null) {
			synchronized (PreferenceUtil.class) {
				if(sp == null) {
					sp = context.getSharedPreferences(preferenceName, Context.MODE_PRIVATE);
				}
			}
		}
		return sp;
	}

	public static void putToken(Context context, String token) {
		sp = getInstance(context,PRE_TOKEN);
		sp.edit().putString(PRE_TOKEN, token).apply();
	}

	public static String getToken(Context context) {
		sp = getInstance(context,PRE_TOKEN);
		return sp.getString(PRE_TOKEN,"");
	}
}
