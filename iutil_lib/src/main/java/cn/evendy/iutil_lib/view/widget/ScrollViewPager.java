package cn.evendy.iutil_lib.view.widget;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * 可控制是否可以滑动的viewpager
 * 
 * @author evendy
 * @date 2015-4-13
 */
public class ScrollViewPager extends ViewPager {

	private boolean mIsEnable = true;

	public ScrollViewPager(Context context) {
		super(context);
	}

	public ScrollViewPager(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		if (mIsEnable) {
			try {
				return super.onInterceptTouchEvent(ev);
			} catch (IllegalArgumentException e) {
				return false;
			}
		}
		return true;
	}

	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		if (mIsEnable) {
			return super.onTouchEvent(ev);
		}
		return false;
	}

	@Override
	public void setAdapter(PagerAdapter arg0) {
		super.setAdapter(arg0);
	}

	public void setAdapter(PagerAdapter arg0, int index) {
		super.setAdapter(arg0);
		setCurrentItem(index, false);
	}

	public void setEnableTouchScroll(boolean isEnable) {
		mIsEnable = isEnable;
	}
}
