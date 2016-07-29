package com.ssyijiu.alertdialog.ui;

import android.content.Context;
import android.widget.Toast;

public class Foo {

    private volatile static Toast mToast;

    private Foo() {
    }


    private static class LazyHolder {

        private static Context context;
        private static final Toast INSTANCE = Toast.makeText(context,"",Toast.LENGTH_SHORT);

    }

    public static Toast getInstance(Context context) {
        LazyHolder.context = context;
        return LazyHolder.INSTANCE;

    }

    public static void show(Context mContext, String msg) {
        mToast = getInstance(mContext);
        mToast.setText(msg);
        mToast.show();
    }
}
