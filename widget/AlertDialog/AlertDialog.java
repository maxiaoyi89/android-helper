package com.ssyijiu.alertdialog.ui;


import android.app.Dialog;
import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.ssyijiu.alertdialog.R;

/**
 * Created by lixiaoming on 2016/7/28.
 */
public class AlertDialog extends Dialog implements View.OnClickListener {

    private TextView btnConfirm;
    private TextView btnCancel;

    private TextView tvMessage;
    private TextView tvTitle;

    private String title;
    private String message;

    private View lineHorizontal;
    private View lineVertical;

    private View contentView;



    public AlertDialog(Context context, String title, String message) {
        super(context);
        this.title = title;
        this.message = message;
        initView(context);
    }
    public AlertDialog(Context context) {
        super(context);
        initView(context);
    }

    private void initView(Context context) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        contentView = View.inflate(context,R.layout.alert_dialog_layout,null);

        this.setContentView(contentView);

        btnConfirm = (TextView) findViewById(R.id.btn_confirm);
        btnCancel = (TextView) findViewById(R.id.btn_cancel);

        tvTitle = (TextView) findViewById(R.id.tv_title);
        tvMessage = (TextView) findViewById(R.id.tv_message);

        lineVertical = findViewById(R.id.line_vertical);
        lineHorizontal = findViewById(R.id.line_horizontal);

        setTitle(title);
        setMessage(message);

        hasCancelButton(false);

        btnConfirm.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
    }

    /**
     * 设置标题，不设置默认不显示。
     * @param title
     * @return
     */
    public AlertDialog setTitle(String title) {
        this.title = title;
        if(!TextUtils.isEmpty(title)) {
            tvTitle.setText(title);
            tvTitle.setVisibility(View.VISIBLE);
        } else {
            tvTitle.setVisibility(View.GONE);
        }
        return this;
    }

    public AlertDialog setMessage(String message) {
        this.message = message;
        if(!TextUtils.isEmpty(message)) {
            tvMessage.setText(this.message);
        }
        return this;
    }

    /**
     * 设置确认按钮。
     * listener为null,点击事件为dismiss
     * @param confirm_text
     * @param listener
     * @return
     */
    public AlertDialog setPositiveView(String confirm_text, View.OnClickListener listener) {

        if(!TextUtils.isEmpty(confirm_text)) {
            btnConfirm.setText(confirm_text);
        }

        if(listener != null) {
            btnConfirm.setOnClickListener(listener);
        }
        return this;
    }

    /**
     * 设置取消按钮，不设置默认不显示。
     * listener为null,点击事件为dismiss
     * @param cancel_text
     * @param listener
     * @return
     */
    public AlertDialog setNegativeView(String cancel_text, View.OnClickListener listener) {
        hasCancelButton(true);
        if(!TextUtils.isEmpty(cancel_text)) {
            btnCancel.setText(cancel_text);
        }

        if(listener != null) {
            btnCancel.setOnClickListener(listener);
        }
        return this;
    }

    private void hasCancelButton(boolean flag) {
        btnCancel.setVisibility(flag ? View.VISIBLE:View.GONE);
        lineVertical.setVisibility(flag ? View.VISIBLE:View.GONE);

    }

    // 默认点击事件
    @Override
    public void onClick(View v) {
        dismiss();
    }

    /**
     * 设置分割线的颜色
     * @param color
     */
    public AlertDialog setLineColor(int color) {
        lineVertical.setBackgroundColor(color);
        lineHorizontal.setBackgroundColor(color);
        return this;
    }


    /**
     * 设置Dialog的宽度
     * @param width
     * @return
     */
    public AlertDialog setWidth(int width) {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.width = width;
        getWindow().setAttributes(lp);
        return this;
    }

    public TextView getNegativeView() {
        return btnConfirm;
    }

    public TextView getPositiveView() {
        return btnCancel;
    }

    public TextView getTvMessage() {
        return tvMessage;
    }

    public TextView getTvTitle() {
        return tvTitle;
    }

}