package com.ssyijiu.alertdialog.ui;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

public class ToastUtil {
	private volatile static Toast mToast;

	private ToastUtil(){
		/* cannot be instantiated */
		throw new UnsupportedOperationException("cannot be instantiated");
	}

	private static Toast getInstance(Context context) {
		if(mToast == null) {
			synchronized (PreferenceUtil.class) {
				if(mToast == null) {
					mToast = Toast.makeText(context, "", Toast.LENGTH_SHORT);
				}
			}
		}
		return mToast;
	}

	public static void show(Context mContext, String msg) {
		mToast = getInstance(mContext);
		mToast.setText(msg);
		mToast.show();
	}
}
