package cn.evendy.iutil_lib.view.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.HorizontalScrollView;

import cn.evendy.iutil_lib.R;

/**
 * @author£ºevendy
 * @time: 2015/5/6 22:58
 * @mail£º244085027@qq.com
 */
public class MySlideMenuLayout extends HorizontalScrollView {
    public MySlideMenuLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MySlideMenuLayout, R.styleable.def_def_theme, 0);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

    }
}
