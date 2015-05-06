package cn.evendy.iutil_lib.view.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

import cn.evendy.iutil_lib.R;
import cn.evendy.iutil_lib.util.ScreenUtils;

/**
 * @author: evendy
 * @time: 2015/5/6 22:58
 * @mail: 244085027@qq.com
 */
public class MySlideMenuLayout extends HorizontalScrollView {
    private int defLeftPadding = 100;
    private int leftPadding;

    private int mScreenWidth;
    private boolean once;

    private int mMenuWidth;
    private int mHalfMenuWidth;

    private boolean isOpen;

    public MySlideMenuLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        mScreenWidth = ScreenUtils.getScreenWidth(context);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MySlideMenuLayout, R.styleable.def_def_theme, 0);
        leftPadding = (int) typedArray.getDimension(R.styleable.MySlideMenuLayout_leftPadding, defLeftPadding);

        typedArray.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (!once) {
            LinearLayout parent = (LinearLayout) getChildAt(0);
            ViewGroup menu = (ViewGroup) parent.getChildAt(0);
            ViewGroup main = (ViewGroup) parent.getChildAt(1);
            mMenuWidth = mScreenWidth - leftPadding;
            mHalfMenuWidth = mMenuWidth / 2;
            menu.getLayoutParams().width = mMenuWidth;
            main.getLayoutParams().width = mScreenWidth;
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        if (changed) {
            this.scrollTo(mMenuWidth, 0);
            once = true;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        switch (action) {
            // Up时，进行判断，如果显示区域大于菜单宽度一半则完全显示，否则隐藏
            case MotionEvent.ACTION_UP:
                int scrollX = getScrollX();
                if (scrollX > mHalfMenuWidth) {
                    this.smoothScrollTo(mMenuWidth, 0);
                    isOpen = false;
                } else {
                    this.smoothScrollTo(0, 0);
                    isOpen = true;
                }
                return true;
        }
        return super.onTouchEvent(ev);
    }

    /**
     * 打开菜单
     */
    public void openMenu() {
        if (isOpen)
            return;
        this.smoothScrollTo(0, 0);
        isOpen = true;
    }

    /**
     * 关闭菜单
     */
    public void closeMenu() {
        if (isOpen) {
            this.smoothScrollTo(mMenuWidth, 0);
            isOpen = false;
        }
    }

    /**
     * 切换菜单状态
     */
    public void toggle() {
        if (isOpen) {
            closeMenu();
        } else {
            openMenu();
        }
    }
}
