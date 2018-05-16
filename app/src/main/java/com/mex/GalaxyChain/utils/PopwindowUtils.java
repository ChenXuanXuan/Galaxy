package com.mex.GalaxyChain.utils;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

/**
 * Created by LSJ on 18/3/30.
 */

public class PopwindowUtils {
	/**
	 * 计算出来的位置，y方向就在anchorView的上面和下面对齐显示，x方向就是与屏幕右边对齐显示
	 * 如果anchorView的位置有变化，就可以适当自己额外加入偏移来修正
	 *
	 * @param anchorView  呼出window的view
	 * @param contentView window的内容布局
	 * @return window显示的左上角的xOff, yOff坐标
	 */
	public static int[] calculatePopWindowPos(final View anchorView, final View contentView) {
		final int windowPos[] = new int[2];
		final int anchorLoc[] = new int[2];
		// 获取锚点View在屏幕上的左上角坐标位置
		anchorView.getLocationOnScreen(anchorLoc);
		final int anchorHeight = anchorView.getHeight();
		// 获取屏幕的高宽
		final int screenHeight = getScreenHeight(anchorView.getContext());
		final int screenWidth = getScreenWidth(anchorView.getContext());
		// 测量contentView
		contentView.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
		// 计算contentView的高宽
		final int windowHeight = contentView.getMeasuredHeight();
		final int windowWidth = contentView.getMeasuredWidth();
		// 判断需要向上弹出还是向下弹出显示
		final boolean isNeedShowUp = (screenHeight - anchorLoc[1] - anchorHeight < windowHeight);
		if (isNeedShowUp) {
			windowPos[0] = screenWidth - windowWidth;
			windowPos[1] = anchorLoc[1] - windowHeight;
		} else {
			windowPos[0] = screenWidth - windowWidth;
			windowPos[1] = anchorLoc[1] + anchorHeight;
		}
		return windowPos;
	}

	public static int[] calculatePopWindowPosAtAnchor(final View anchorView, final View contentView) {
		final int windowPos[] = new int[3];
		final int anchorLoc[] = new int[2];
		// 获取锚点View在屏幕上的左上角坐标位置
		anchorView.getLocationOnScreen(anchorLoc);
		final int anchorHeight = anchorView.getHeight();
		// 获取屏幕的高宽
		final int screenHeight = getScreenHeight(anchorView.getContext());
		final int screenWidth = getScreenWidth(anchorView.getContext());
		// 测量contentView
		contentView.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
		// 计算contentView的高宽
		final int windowHeight = contentView.getMeasuredHeight();
		final int windowWidth = contentView.getMeasuredWidth();
		// 判断需要向上弹出还是向下弹出显示
		final boolean isNeedShowUp = (screenHeight - anchorLoc[1] - anchorHeight < windowHeight);
		if (isNeedShowUp) {
			windowPos[0] = anchorLoc[0];
			windowPos[1] = anchorLoc[1] - windowHeight;
		} else {
			windowPos[0] = anchorLoc[0];
			windowPos[1] = anchorLoc[1] + anchorHeight;
		}
		windowPos[2] = isNeedShowUp ? 1 : 0;
		return windowPos;
	}

	public static int[] calculatePopWindowPosAtAnchorTop(final View anchorView, final View parentView, final View contentView) {
		final int windowPos[] = new int[2];
		final int anchorLoc[] = new int[2];
		final int parentLoc[] = new int[2];
		// 获取锚点View在屏幕上的左上角坐标位置
		anchorView.getLocationOnScreen(anchorLoc);
		parentView.getLocationOnScreen(parentLoc);
		final int anchorHeight = anchorView.getHeight();
		// 测量contentView
		contentView.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
		// 计算contentView的高宽
		final int windowHeight = contentView.getMeasuredHeight();
		// 判断需要向上弹出还是向下弹出显示
		windowPos[0] = anchorLoc[0];
		windowPos[1] = anchorLoc[1] - windowHeight;
		if (windowPos[1] < parentLoc[1]) {
			windowPos[1] = parentLoc[1] - windowHeight;
		}
		if (windowPos[1] < 0) {
			windowPos[1] = 0;
		}
		return windowPos;
	}


	public static int getScreenHeight(Context context) {
		return context.getResources().getDisplayMetrics().heightPixels;
	}

	/**
	 * 获取屏幕宽度(px)
	 */
	public static int getScreenWidth(Context context) {
		return context.getResources().getDisplayMetrics().widthPixels;
	}

	/**
	 * 创建一个popwindow 点击外部区域 delPopView 关闭
	 *
	 * @param contentView
	 * @return
	 */
	public static PopupWindow createPopView(View contentView) {
		final PopupWindow delPopView = new PopupWindow(contentView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
		delPopView.setOutsideTouchable(true);
		delPopView.setBackgroundDrawable(new BitmapDrawable());
		delPopView.setTouchInterceptor(new View.OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				if (event.getAction() == MotionEvent.ACTION_OUTSIDE && !delPopView.isFocusable()) {
					//不做任何响应,不 dismiss popupWindow
					delPopView.dismiss();
					return true;
				}
				//否则default，往下dispatch事件:关掉popupWindow，
				return false;
			}
		});
		return delPopView;
	}
}
