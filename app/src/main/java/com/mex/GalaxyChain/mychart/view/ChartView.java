package com.mex.GalaxyChain.mychart.view;

import android.content.Context;
import android.graphics.Canvas;
import android.os.Handler;
import android.support.v4.view.NestedScrollingChild;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;


/**
 * Created by LSJ on 2017/5/25.
 */
public abstract class ChartView extends View implements ChartConstant, CrossView.OnMoveListener, NestedScrollingChild {

	/**
	 * 分时线或K线类型，取值见ChartConstant.TYPE_FENSHI
	 */
	protected int lineType = TYPE_FENSHI;
	/**
	 * 指标的类型
	 */
	protected int indexType = INDEX_VOL;
	//分时图,K线高度
	protected float mainH;
	//指标高度
	protected float indexH;
	//指标左上y坐标
	protected float indexStartY;
	//时间左上Y坐标
	protected float timeStartY;
	//总宽
	protected float mWidth;
	//总高
	protected float mHeight;
	//十字线布局
	protected CrossView crossView;
	//十字线点对应的详情展示
	protected TextView msgText;
	protected LinearLayout llFenshi;
	protected TextView time, fenshiPrice, averagePrice, rose;
	//双击监听，全屏
	protected OnDoubleTapListener onDoubleTapListener;
	//十字线最后停下的点，当切换指标的时候，使用这个点来计算指标应该显示的文字
	protected float lastX, lastY;
	protected LinearLayout msg;
	protected TextView kai, gao, fu, chengjiao, shou, di, e, junjia, sma5, sma10, sma20;
	protected LinearLayout llMa;
	//点击手势监听,显示十字线，全屏显示
	protected GestureDetector gestureDetector = new GestureDetector(getContext(), new GestureDetector.SimpleOnGestureListener() {
		@Override
		public boolean onSingleTapUp(final MotionEvent e) {
			//延时300毫秒显示，为双击腾出时间
			new Handler().postDelayed(new Runnable() {
				@Override
				public void run() {
					//单击显示十字线
					if (crossView != null) {
						if (crossView.getVisibility() == View.GONE) {
							onCrossMove(e.getX(), e.getY());
						}
					}
				}
			}, DOUBLE_TAP_DELAY);
			return super.onSingleTapUp(e);
		}

		@Override
		public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
			return onViewScroll(e1, e2, distanceX, distanceY);
		}

		@Override
		public boolean onDoubleTap(MotionEvent e) {
			if (onDoubleTapListener != null)
				onDoubleTapListener.onDoubleTap();
			return super.onDoubleTap(e);
		}
	});

	public ChartView(Context context, AttributeSet attrs) {
		super(context, attrs);
		//关闭硬件加速，不然虚线显示为实线了
		setLayerType(View.LAYER_TYPE_SOFTWARE, null);
	}

	protected abstract boolean onViewScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY);

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		//1,初始化需要的数据
		initWidthAndHeight();
		//2，画网格
		drawGrid(canvas);
		//3，画线（分时线的价格线、均价线或K线的均线）
		drawLines(canvas);
		if (lineType != TYPE_FENSHI) {
			//4，如果是K线另外画烛形图
			drawCandles(canvas);
		}
		//5，写上XY轴的文字（写早了会被覆盖）
		drawText(canvas);
		//6，画需要显示的指标
		switch (indexType) {

			case INDEX_VOL:
				drawVOL(canvas);
				break;

			case INDEX_MACD:
				drawMACD(canvas);
				break;

			case INDEX_KDJ:
				drawKDJ(canvas);
				break;

			case INDEX_BOLL:
				drawBOLL(canvas);
				break;
		}
	}

	private void initWidthAndHeight() {
		mHeight = getMeasuredHeight();
		mWidth = getMeasuredWidth();
		mainH = mHeight * FENSHI_SCALE;
		indexH = mHeight * INDEX_SCALE;
		indexStartY = mHeight - indexH;
		timeStartY = indexStartY - mHeight * TIME_SCALE;
		init();
	}

	/**
	 * 画网格包含主网格和指标网格
	 *
	 * @param canvas
	 */
	protected abstract void drawGrid(Canvas canvas);

	/**
	 * 分时价格线均线、K线的均线
	 *
	 * @param canvas
	 */
	protected void drawLines(Canvas canvas) {
	}

	protected void drawCandles(Canvas canvas) {
	}

	/**
	 * 写X，Y轴文字
	 *
	 * @param canvas
	 */
	protected abstract void drawText(Canvas canvas);

	/**
	 * 画量
	 *
	 * @param canvas
	 */
	protected abstract void drawVOL(Canvas canvas);

	protected void drawMACD(Canvas canvas) {
	}

	protected void drawKDJ(Canvas canvas) {
	}

	protected void drawBOLL(Canvas canvas) {
	}

	/**
	 * 在开始画图前，初始化数据
	 */
	protected abstract void init();

	/**
	 * 话资金
	 *
	 * @param canvas
	 */
	protected abstract void drawZJ(Canvas canvas);

	public void showVOL() {
		indexType = INDEX_VOL;
		postInvalidate();
		resetIndexText();

	}

	/**
	 * 十字线显示的时候，切换指标之后，让十字线显示的指标文字也切换掉
	 */
	protected void resetIndexText() {
		if (crossView != null && crossView.getVisibility() == VISIBLE) {
			onCrossMove(lastX, lastY);
		}
	}

//    public void showZJ() {
//        indexType = INDEX_ZJ;
//        postInvalidate();
//        resetIndexText();
//    }

	@Override
	public void onCrossMove(float x, float y) {
		lastX = x;
		lastY = y;
	}

	public void showMACD() {
		indexType = INDEX_MACD;
		postInvalidate();
		resetIndexText();
	}

	public void showKDJ() {
		indexType = INDEX_KDJ;
		postInvalidate();
		resetIndexText();
	}

	public void showBOLL() {
		indexType = INDEX_BOLL;
		postInvalidate();
		resetIndexText();
	}

	/**
	 * 设置需要使用的View
	 *
	 * @param crossView
	 * @param msgText
	 */
	public void setUsedViews(CrossView crossView, TextView msgText) {
		this.crossView = crossView;
		this.msgText = msgText;
		crossView.setOnMoveListener(this);
	}

	public void setUsedViews(CrossView crossView, LinearLayout llFenshi, TextView time, TextView price, TextView rose, TextView averagePrice) {
		this.crossView = crossView;
		this.llFenshi = llFenshi;
		this.time = time;
		this.fenshiPrice = price;
		this.rose = rose;
		this.averagePrice = averagePrice;
		crossView.setOnMoveListener(this);
	}

	public void setUsedViews(CrossView crossView, LinearLayout msg, TextView kai, TextView gao, TextView fu, TextView chengjiao,
							 TextView shou, TextView di, TextView e, TextView junjia, LinearLayout llMa, TextView sma5, TextView sma10, TextView sma20) {
		this.crossView = crossView;
		this.msg = msg;
		this.kai = kai;
		this.gao = gao;
		this.fu = fu;
		this.chengjiao = chengjiao;
		this.shou = shou;
		this.di = di;
		this.e = e;
		this.junjia = junjia;
		this.llMa = llMa;
		this.sma5 = sma5;
		this.sma10 = sma10;
		this.sma20 = sma20;
		crossView.setOnMoveListener(this);
	}

	public void setOnDoubleTapListener(OnDoubleTapListener onDoubleTapListener) {
		this.onDoubleTapListener = onDoubleTapListener;
	}

	public interface OnDoubleTapListener {
		void onDoubleTap();
	}
}
