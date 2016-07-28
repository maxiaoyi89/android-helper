package com.ssyijiu.alertdialog.ui;

import java.lang.reflect.Type;

import android.util.Log;

import com.google.gson.Gson;

/**
 * <pre>
 * Gson解析工具
 * 如果要解析的json数据根元素是一个对象，则使用{@link #json2Bean(String, Class)}
 * 如果要解析的json数据根元素是一个集合，则使用{@link #json2Bean(String, Type)}
 * </pre>
 */
public class GsonUtil {

	private GsonUtil(){
		/* cannot be instantiated */
		throw new UnsupportedOperationException("cannot be instantiated");
	}

	/** 用于解析json的类 */
	private static final Gson GSON = new Gson();

	/**
	 * 把json字符串转换为JavaBean
	 * @param json json字符串
	 * @param beanClass JavaBean的Class
	 * @return 解析成功返回JavaBean类，失败返回null
	 */
	public static <T> T json2Bean(String json, Class<T> beanClass) {
		T bean = null;
		try {
			bean = GSON.fromJson(json, beanClass);
		} catch (Exception e) {
			Log.i("GsonUtil", "解析json数据时出现异常\njson = " + json, e);
		}
		return bean;
	}
	
	/**
	 * 把json字符串转换为JavaBean，如果json的根节点就是一个集合，则使用此方法<p>
	 * type参数的获取方式为：<p> Type type = new TypeToken<集合泛型>(){}.getType();
	 * @param json json字符串
	 * @return 解析成功返回指定的数据类型，失败返回null
	 */
	public static <T> T json2Bean(String json, Type type) {
		T bean = null;
		try {
			bean = GSON.fromJson(json, type);
		} catch (Exception e) {
			Log.i("GsonUtil", "解析json数据时出现异常\njson = " + json, e);
		}
		return bean;
	}
	
}
