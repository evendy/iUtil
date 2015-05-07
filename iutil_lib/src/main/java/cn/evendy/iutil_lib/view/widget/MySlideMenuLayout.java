package cn.evendy.iutil_lib.view.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

import com.nineoldandroids.view.ViewHelper;

import cn.evendy.iutil_lib.R;
import cn.evendy.iutil_lib.util.MyLog;
import cn.evendy.iutil_lib.util.PixelUtils;
import cn.evendy.iutil_lib.util.ScreenUtils;

/**
 * @author: evendy
 * @time: 2015/5/6 22:58
 * @mail: 244085027@qq.com
 */
public class MySlideMenuLayout extends HorizontalScrollView {
    private int defMenuWidth = 100;

    private int mScreenWidth;
    private boolean once;

    private int mMenuWidth;
    private int mHalfMenuWidth;

    private boolean isOpen;

    private ViewGroup menu;
    private ViewGroup main;
    private float mainAlpha;

    public MySlideMenuLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        mScreenWidth = ScreenUtils.getScreenWidth(context);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MySlideMenuLayout, R.styleable.def_def_theme, 0);
        mMenuWidth = (int) typedArray.getDimension(R.styleable.MySlideMenuLayout_menuWidth, PixelUtils.px2dp(context, defMenuWidth));

        typedArray.recycle();
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (!once) {
            LinearLayout parent = (LinearLayout) getChildAt(0);
            menu = (ViewGroup) parent.getChildAt(0);
            main = (ViewGroup) parent.getChildAt(1);
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
            //默认将屏幕向右移动mMenuWidth距离以隐藏slideMenu
            closeSlideMenu();
            once = true;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        switch (action) {
            // Up时，进行判断，如果显示区域大于菜单宽度一半则完全显示，否则隐藏
            case MotionEvent.ACTION_UP: {
                int scrollX = getScrollX();//手机屏幕相对于本布局左边缘的偏移量
                MyLog.i("scrollX==" + scrollX);
                if (scrollX > mHalfMenuWidth) {
                    closeSlideMenu();
                } else {
                    openSlideMenu();
                }
                return true;
            }
        }
        return super.onTouchEvent(ev);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        float scale = l * 1.0f / mMenuWidth;

        float menuAlpha = 1.0f - 0.4f * scale;//0.6-1.0
        float mainAlpha = 0.8f + 0.2f * scale;//1.0-0.8
        ViewHelper.setAlpha(menu, menuAlpha);
        ViewHelper.setAlpha(main, mainAlpha);

        float mainScale = 0.9f + 0.1f * scale;
        float menuScale = 1.0f - 0.2f * scale;
        ViewHelper.setScaleX(main, mainScale);
        ViewHelper.setScaleY(main, mainScale);
        ViewHelper.setScaleX(menu, menuScale);
        ViewHelper.setScaleY(menu, menuScale);

        ViewHelper.setTranslationX(menu, mMenuWidth * scale * 0.6f);

        ViewHelper.setPivotX(main, 0);
        ViewHelper.setPivotY(main, main.getHeight() / 2);


    }

    private void closeSlideMenu() {
        this.scrollTo(mMenuWidth, 0);//在当前视图内容偏移至(x , y)坐标处，即显示(可视)区域位于(x , y)坐标处。
        isOpen = false;
    }

    private void openSlideMenu() {
        this.smoothScrollTo(0, 0);
        isOpen = true;
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
