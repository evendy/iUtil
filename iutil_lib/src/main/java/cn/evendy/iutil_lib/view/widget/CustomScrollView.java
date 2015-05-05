package cn.evendy.iutil_lib.view.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

/**  
 * 可以监听自身滑动的ScrollView，主要用于做美食团购中的那种效果
 * @author 游  山药蛋
 *
 */
public class CustomScrollView extends ScrollView {
	private OnScrollChangedListener listener;
	public CustomScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

	public CustomScrollView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public CustomScrollView(Context context) {
		super(context);
	}
	
	@Override
	protected void onScrollChanged(int l, int t, int oldl, int oldt) {
		super.onScrollChanged(l, t, oldl, oldt);
		if(listener != null)
			listener.scrollChanged(l, t, oldl, oldt);
	}
	
	/** 设置ScrollView的滑动监听  */
	public void setOnScrollChangedListener(OnScrollChangedListener listener) {
		this.listener = listener;
	}
	
	/** ScrollView滑动监听  */
	public interface OnScrollChangedListener {
		void scrollChanged(int x, int y, int oldX, int oldY);
	}	
	
}
