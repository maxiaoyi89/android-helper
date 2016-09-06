package com.ssyijiu.ahelper;

import android.content.Context;
import android.widget.Toast;
import android.text.TextUtils;
import android.view.Gravity;

public class ToastUtil {
	private static Toast mToast;

	private ToastUtil(){
		/* cannot be instantiated */
		throw new UnsupportedOperationException("ToastUtil cannot be instantiated !");
	}

	private static Toast getInstance(Context context) {
		if(mToast == null) {
			mToast = Toast.makeText(context, "", Toast.LENGTH_SHORT);
		}
		return mToast;
	}

	public static void show(Context mContext, String msg) {
		if(TextUtils.isEmpty(msg)) {
			return ;
		}
		mToast = getInstance(mContext);
		mToast.setText(msg);
		mToast.show();
	}

	public static void showCenter(Context mContext, String msg) {
		if(TextUtils.isEmpty(msg)) {
			return ;
		}
		mToast = getInstance(mContext);
		mToast.setGravity(Gravity.CENTER, 0, 10);
		mToast.setText(msg);
		mToast.show();
	}
}
