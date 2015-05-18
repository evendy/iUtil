package cn.evendy.iutil.test;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import cn.evendy.iutil_lib.util.ScreenUtils;

/**
 * @author: evendy
 * @time: 2015/5/18 15:05
 */
public class AdGridView extends ViewGroup {
    private GridItemClickListener gridItemClickListener;
    private int row = 2;
    private int column = 4;

    public AdGridView(Context context, int row, int column) {
        super(context, null);
        this.row = row;
        this.column = column;
    }

    public AdGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void addView(View child) {
        super.addView(child);
        setOnChildClickListener(child);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        // 计算出所有的childView的宽和高
        measureChildren(widthMeasureSpec, heightMeasureSpec);

        int width = ScreenUtils.getScreenWidth(getContext());//占满全屏
        int height = 0;//根据子view计算的高度
        View childView = getChildAt(0);
        if (null != childView) {
            height = row * childView.getMeasuredHeight();
        }

        setMeasuredDimension((widthMode == MeasureSpec.EXACTLY ? widthSize : width), (heightMode == MeasureSpec.EXACTLY ? heightSize : height));
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int count = getChildCount();
        int cWidth = ScreenUtils.getScreenWidth(getContext()) / column;
        int cHeight;
        View childView;
        int cRow;
        int cColumn;
        int cl, ct, cr, cb;
        for (int i = 0; i < count; i++) {
            childView = getChildAt(i);
            cHeight = childView.getMeasuredHeight();
            cRow = i / column;
            cColumn = i % column;
            cl = cColumn * cWidth;
            cr = cl + cWidth;
            ct = cRow * cHeight;
            cb = ct + cHeight;
            childView.layout(cl, ct, cr, cb);
        }
    }

    private void setOnChildClickListener(View child) {
        child.setOnClickListener(new OnChildClickListener(getChildCount() - 1));
    }

    public void setAdapter(AdGridViewAdapter adGridViewAdapter) {
        for (int i = 0; i < adGridViewAdapter.getCount(); i++) {
            addView(adGridViewAdapter.getView(i, null, this));
        }
    }

    private class OnChildClickListener implements OnClickListener {
        private int index;

        public OnChildClickListener(int index) {
            this.index = index;
        }

        @Override
        public void onClick(View v) {
            gridItemClick(v, index);
        }
    }

    private void gridItemClick(View view, int index) {
        if (null != gridItemClickListener) {
            gridItemClickListener.gridItemClick(view, index);
        }
    }

    public void setGridItemClickListener(GridItemClickListener gridItemClickListener) {
        this.gridItemClickListener = gridItemClickListener;
    }

    public interface GridItemClickListener {
        void gridItemClick(View view, int index);
    }
}
