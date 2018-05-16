package com.mex.GalaxyChain.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * name：
 * describe:自定义wheelView
 * author: LSJ
 * time 26/4/18 上午10:11
 */
public class CustomWheelView extends ScrollView {
	public static final int OFF_SET_DEFAULT = 1;
	private static final int SCROLL_DIRECTION_UP = 0;
	private static final int SCROLL_DIRECTION_DOWN = 1;
	private int initialY;
	private Runnable scrollerTask;
	private int newCheck = 50;
	private int offset = OFF_SET_DEFAULT; // 偏移量（需要在最前面和最后面补全）
	private int displayItemCount; // 每页显示的数量
	private int itemHeight = 0;
	private int selectedIndex = 1;
	private List<String> items;
	private String COLOR_BORDER = "#ececec";
	/**
	 * 选中区域的边界
	 */
	private int[] selectedAreaBorder;
	private int scrollDirection = -1;
	private Paint paint;
	private int viewWidth;
	private Context context;
	private LinearLayout views;
	private OnWheelViewListener onWheelViewListener;

	public CustomWheelView(Context context) {
		super(context);
		init(context);
	}

	private void init(Context context) {
		this.context = context;
		this.setVerticalScrollBarEnabled(false);
		views = new LinearLayout(context);
		views.setOrientation(LinearLayout.VERTICAL);
		this.addView(views);

		scrollerTask = new Runnable() {

			public void run() {

				int newY = getScrollY();
				if (initialY - newY == 0 && itemHeight > 0) { // stopped
					final int remainder = initialY % itemHeight;
					final int divided = initialY / itemHeight;
					if (remainder == 0) {
						selectedIndex = divided + offset;

						onSeletedCallBack();
					} else {
						if (remainder > itemHeight / 2) {
							CustomWheelView.this.post(new Runnable() {
								@Override
								public void run() {
									CustomWheelView.this.smoothScrollTo(0, initialY - remainder
											+ itemHeight);
									selectedIndex = divided + offset + 1;
									onSeletedCallBack();
								}
							});
						} else {
							CustomWheelView.this.post(new Runnable() {
								@Override
								public void run() {
									CustomWheelView.this
											.smoothScrollTo(0, initialY - remainder);
									selectedIndex = divided + offset;
									onSeletedCallBack();
								}
							});
						}

					}

				} else {
					initialY = getScrollY();
					CustomWheelView.this.postDelayed(scrollerTask, newCheck);
				}
			}
		};

	}

	/**
	 * 选中回调
	 */
	private void onSeletedCallBack() {
		if (null != onWheelViewListener) {
			onWheelViewListener.onSelected(selectedIndex, items.get(selectedIndex));
		}

	}

	public CustomWheelView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}


	public CustomWheelView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(context);
	}

	private List<String> getItems() {
		return items;
	}

	public void setItems(List<String> list) {
		if (null == items) {
			items = new ArrayList<String>();
		}
		items.clear();
		items.addAll(list);
		// 前面和后面补全
		for (int i = 0; i < offset; i++) {
			items.add(0, "");
			items.add("");
		}
		initData();

	}

	private void initData() {
		displayItemCount = offset * 2 + 1;

		for (String item : items) {
			views.addView(createView(item));
		}

		refreshItemView(0);
	}

	private TextView createView(String item) {
		TextView tv = new TextView(context);
		tv.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
				ViewGroup.LayoutParams.WRAP_CONTENT));
		tv.setSingleLine(true);
		tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
		tv.setText(item);
		tv.setGravity(Gravity.CENTER);
		int padding = dip2px(10);
		tv.setPadding(padding, padding, padding, padding);
		if (0 == itemHeight) {
			itemHeight = getViewMeasuredHeight(tv);
			if (itemHeight == 0) itemHeight = 1;
			views.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
					itemHeight * displayItemCount));
			LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) this
					.getLayoutParams();
			this.setLayoutParams(new LinearLayout.LayoutParams(lp.width, itemHeight
					* displayItemCount));
		}
		return tv;
	}

	private void refreshItemView(int y) {
		int position = y / itemHeight + offset;
		int remainder = y % itemHeight;
		int divided = y / itemHeight;

		if (remainder == 0) {
			position = divided + offset;
		} else {
			if (remainder > itemHeight / 2) {
				position = divided + offset + 1;
			}
		}

		int childSize = views.getChildCount();
		for (int i = 0; i < childSize; i++) {
			TextView itemView = (TextView) views.getChildAt(i);
			if (null == itemView) {
				return;
			}
			if (position == i) {
				itemView.setTextColor(Color.parseColor("#0288ce"));
			} else {
				itemView.setTextColor(Color.parseColor("#bbbbbb"));
			}
		}
	}

	private int dip2px(float dpValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dpValue * scale + 0.5f);
	}

	private int getViewMeasuredHeight(View view) {
		int width = MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED);
		int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
				MeasureSpec.AT_MOST);
		view.measure(width, expandSpec);
		return view.getMeasuredHeight();
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	@Override
	protected void onScrollChanged(int l, int t, int oldl, int oldt) {
		super.onScrollChanged(l, t, oldl, oldt);
		refreshItemView(t);
		if (t > oldt) {
			scrollDirection = SCROLL_DIRECTION_DOWN;
		} else {
			scrollDirection = SCROLL_DIRECTION_UP;

		}

	}

	@Override
	public void setBackgroundDrawable(Drawable background) {

		if (viewWidth == 0) {
			viewWidth = ((Activity) context).getWindowManager().getDefaultDisplay()
					.getWidth();
		}

		if (null == paint) {
			paint = new Paint();
			paint.setColor(Color.parseColor(COLOR_BORDER));
			paint.setStrokeWidth(dip2px(1f));
		}

		background = new Drawable() {
			@Override
			public void draw(Canvas canvas) {
				canvas.drawLine(0, obtainSelectedAreaBorder()[0],
						viewWidth, obtainSelectedAreaBorder()[0], paint);
				canvas.drawLine(0, obtainSelectedAreaBorder()[1],
						viewWidth, obtainSelectedAreaBorder()[1], paint);
			}

			@Override
			public void setAlpha(int alpha) {

			}

			@Override
			public void setColorFilter(ColorFilter cf) {

			}

			@Override
			public int getOpacity() {
				return 0;
			}
		};

		super.setBackgroundDrawable(background);

	}

	public void setSeletion(int position) {
		final int p = position;
		selectedIndex = p + offset;
		this.post(new Runnable() {
			@Override
			public void run() {
				CustomWheelView.this.smoothScrollTo(0, p * itemHeight);
			}
		});

	}

	public String getSeletedItem() {
		return items.get(selectedIndex);
	}

	public int getSeletedIndex() {
		return selectedIndex - offset;
	}

	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		if (ev.getAction() == MotionEvent.ACTION_UP) {

			startScrollerTask();
		}
		return super.onTouchEvent(ev);
	}

	public void startScrollerTask() {

		initialY = getScrollY();
		this.postDelayed(scrollerTask, newCheck);
	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);
		viewWidth = w;
		setBackgroundDrawable(null);
	}

	private int[] obtainSelectedAreaBorder() {
		if (null == selectedAreaBorder) {
			selectedAreaBorder = new int[2];
			selectedAreaBorder[0] = itemHeight * offset;
			selectedAreaBorder[1] = itemHeight * (offset + 1);
		}
		return selectedAreaBorder;
	}

	@Override
	public void fling(int velocityY) {
		super.fling(velocityY / 3);
	}

	public OnWheelViewListener getOnWheelViewListener() {
		return onWheelViewListener;
	}

	public void setOnWheelViewListener(OnWheelViewListener onWheelViewListener) {
		this.onWheelViewListener = onWheelViewListener;
	}

	public static class OnWheelViewListener {
		public void onSelected(int selectedIndex, String item) {
		}
	}

}

