package cn.evendy.iutil_lib.view.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by evendy on 2015/5/5.
 * 流式布局,装载各种子控件,当一行装满后自动换行继续装载子控件
 */
public class FlowLayout extends ViewGroup {
    private int lineWidth;

    public FlowLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(), attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int sizeWidth = MeasureSpec.getSize(widthMeasureSpec);
        int sizeHeight = MeasureSpec.getSize(heightMeasureSpec);

        int width = 0;
        int height = 0;
        int count = getChildCount();

        int lineWidth = 0;
        int lineHeight = 0;

        MarginLayoutParams cParams;
        int childWidth = 0;
        int childHeight = 0;

        for (int i = 0; i < count; i++) {
            View childView = getChildAt(i);
            measureChild(childView, widthMeasureSpec, heightMeasureSpec);
            cParams = (MarginLayoutParams) childView.getLayoutParams();
            childWidth = childView.getMeasuredWidth() + cParams.rightMargin + cParams.leftMargin;
            childHeight = childView.getMeasuredHeight() + cParams.topMargin + cParams.bottomMargin;
            if (childWidth + lineWidth > sizeWidth) {
                width = Math.max(lineWidth, childWidth);
                height += lineHeight;
                //将该子控件放置到新的一行
                lineHeight = childHeight;
                lineWidth = childWidth;
            } else {
                lineWidth += childWidth;
                lineHeight = Math.max(lineHeight, childHeight);
            }
        }
        width = Math.max(lineWidth, width);
        height += lineHeight;

        setMeasuredDimension((widthMode == MeasureSpec.EXACTLY ? sizeWidth : width), (heightMode == MeasureSpec.EXACTLY ? sizeHeight : height));
    }

    /**
     * 存储所有的View，按行记录
     */
    private List<List<View>> mAllViews = new ArrayList<List<View>>();
    /**
     * 记录每一行的最大高度
     */
    private List<Integer> mLineHeight = new ArrayList<Integer>();

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        mAllViews.clear();
        mLineHeight.clear();

        int width = getWidth();
        int count = getChildCount();

        int lineWidth = 0;
        int lineHeight = 0;
        //存储每行所有的view
        List<View> lineViews = new ArrayList<View>();
        for (int i = 0; i < count; i++) {
            View childView = getChildAt(i);
            MarginLayoutParams cParam = (MarginLayoutParams) childView.getLayoutParams();
            int childWidth = childView.getMeasuredWidth() + cParam.leftMargin + cParam.rightMargin;
            int childHeight = childView.getMeasuredHeight() + cParam.topMargin + cParam.bottomMargin;

            if (childWidth + lineWidth > width) {
                mLineHeight.add(lineHeight);
                mAllViews.add(lineViews);
                lineViews = new ArrayList<View>();
                lineWidth = 0;
                lineHeight = 0;
            }
            lineViews.add(childView);
            lineWidth += childWidth;
            lineHeight = Math.max(childHeight, lineHeight);
        }
        mLineHeight.add(lineHeight);
        mAllViews.add(lineViews);

        int left = 0;
        int top = 0;
        int lineNum = mAllViews.size();
        Log.i("evendy", "lineNum=" + lineNum);


        for (int i = 0; i < lineNum; i++) {
            lineViews = mAllViews.get(i);
            lineHeight = mLineHeight.get(i);
            Log.i("evendy", "lineViews=" + lineViews.size());
            for (int j = 0; j < lineViews.size(); j++) {
                View childView = lineViews.get(j);
                if (childView.getVisibility() == View.GONE) {
                    continue;
                }
                MarginLayoutParams cParam = (MarginLayoutParams) childView.getLayoutParams();

                int cl = left + cParam.leftMargin;
                int ct = top + cParam.topMargin;
                int cr = cl + childView.getMeasuredWidth();
                int cb = ct + childView.getMeasuredHeight();

                childView.layout(cl, ct, cr, cb);

                left += childView.getMeasuredWidth() + cParam.leftMargin + cParam.rightMargin;
            }
            left = 0;
            top += lineHeight;
        }

    }
}
