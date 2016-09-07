package com.ssyijiu.ahelper;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by ssyijiu on 2016/8/16.
 * Github: ssyijiu
 * E-mail: lxmyijiu@163.com
 */
public class PreferenceUtil {

	/**
	 * ContextImpl中有一个静态的ArrayMap变量sSharedPrefs，无论有多少个ContextImpl对象实例，
	 * 系统都共享这一个sSharedPrefs的Map，应用启动以后首次使用SharePreference时创建，系统结束时才可能会被垃圾回收器回收，
	 * 所以如果我们一个App中频繁的使用不同文件名的SharedPreferences很多时这个Map就会很大，也即会占用移动设备宝贵的内存空间。
	 * 所以我们应用中应该尽可能少的使用不同文件名的SharedPreferences，取而代之的是合并他们，减小内存使用
	 *
	 * SharedPreferences在实例化时首先会从sdcard异步读文件，在xml文件异步加载未完成时调运SharePreference的getXXX及setXXX方法是阻塞等待的,
	 * 然后缓存在内存中,接下来的读操作都是内存缓存操作而不是文件操作。
	 *
	 * 在SharedPreferences的Editor中如果用commit()方法提交数据，其过程是先把数据更新到内存，然后在当前线程中写文件操作，提交完成返回提交状态；
	 * 如果用的是apply()方法提交数据，首先也是写到内存，接着在一个新线程中异步写文件，然后没有返回值。
	 *
	 * commit时有三级锁操作，所以效率很低，所以当我们一次有多个修改写操作时等都批量put完了再一次提交确认，这样可以提高效率。
	 */

	private volatile static SharedPreferences sp;

	private PreferenceUtil(){
		/* cannot be instantiated */
		throw new UnsupportedOperationException("PreferenceUtil cannot be instantiated !");
	}



	private static SharedPreferences getInstance(Context context) {

		if(sp == null) {
			sp = context.getSharedPreferences("config", Context.MODE_PRIVATE);
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
	private static SharedPreferences getInstance(Context context, String preferenceName) {
		if(sp == null) {
			sp = context.getSharedPreferences(preferenceName, Context.MODE_PRIVATE);
		}
		return sp;
	}

	private static final String PRE_TOKEN = "token";
	public static void putToken(Context context, String token) {
		sp = getInstance(context,PRE_TOKEN);
		sp.edit().putString(PRE_TOKEN, token).apply();
	}

	public static String getToken(Context context) {
		sp = getInstance(context,PRE_TOKEN);
		return sp.getString(PRE_TOKEN,"");
	}
}
