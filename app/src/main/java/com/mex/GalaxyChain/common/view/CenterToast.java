package com.mex.GalaxyChain.common.view;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.mex.GalaxyChain.R;

/**
 * name：
 * describe:
 * author: LSJ
 * time 19/3/18 上午11:59
 */
public class CenterToast extends Toast{
    /**
     * 不显示图标
     */
    public static final int TYPE_HIDE = -1;
    /**
     * 显示√
     */
    public static final int TYPE_CORRECT = 0;
    /**
     * 显示!
     */
    public static final int TYPE_ERROR = 1;

    private static CenterToast toast;
    public static ImageView toast_image;
    private static LayoutInflater inflater;
    private static TextView toast_text;

    private CenterToast(Context context) {
        super(context);
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public static void cancelToast() {
        if (toast != null) {
            toast.cancel();
            toast = null;
            toast_image = null;
            toast_text = null;
        }
    }

    @Override
    public void cancel() {
        try {
            super.cancel();
        } catch (Exception e) {

        }
    }

    @Override
    public void show() {
        try {
            super.show();
        } catch (Exception e) {

        }
    }

    public static void showToast(Context context, CharSequence text, int time, int imageType) {
        cancelToast();
        if (toast == null) {
            toast = new CenterToast(context);
            toast.setGravity(Gravity.CENTER, 0, 0);
            // 设置显示时长
            if (time == Toast.LENGTH_LONG) {
                toast.setDuration(Toast.LENGTH_LONG);
            } else {
                toast.setDuration(Toast.LENGTH_SHORT);
            }

            View layout = inflater.inflate(R.layout.layout_toast_center, null);

            toast_image = (ImageView) layout.findViewById(R.id.toast_iamge);
            toast_text = (TextView) layout.findViewById(R.id.toast_text);
            toast.setView(layout);
        }

        toast_text.setText(text);

        // 判断图标类型
        if (imageType == TYPE_HIDE) {
            toast_image.setVisibility(View.GONE);
        } else if (imageType == TYPE_CORRECT) {
            toast_image.setImageResource(R.drawable.toast_correct);
            toast_image.setVisibility(View.VISIBLE);
        } else if (imageType == TYPE_ERROR) {
            toast_image.setImageResource(R.drawable.toast_error);
            toast_image.setVisibility(View.VISIBLE);
        }
        // 显示Toast
        toast.show();
    }

    public static void showToast(Context context, int resId, int time, int imageType) {
        showToast(context, context.getResources().getText(resId).toString(), time, imageType);
    }
}
