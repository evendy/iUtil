package cn.evendy.iutil_lib.view.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by evendy on 2015/5/5.
 */
public class MyViewgroup extends ViewGroup {

    public MyViewgroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(), attrs);
    }

    /**
     * 计算所有ChildView的宽度和高度 然后根据ChildView的计算结果，设置自己的宽和高
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int sizeWidth = MeasureSpec.getSize(widthMeasureSpec);
        int sizeHeight = MeasureSpec.getSize(heightMeasureSpec);

        // 计算出所有的childView的宽和高
        measureChildren(widthMeasureSpec, heightMeasureSpec);

        int width = 0;//根据子view计算的宽度
        int height = 0;//根据子view计算的高度
        int cCount = getChildCount();//子view的个数
        int cWidth = 0;//child的宽度
        int cHeight = 0;//child的高度
        MarginLayoutParams cParams;
        int tlHeight = 0, tlWidth = 0;
        int trHeight = 0, trWidth = 0;
        int blHeight = 0, blWidth = 0;
        int brHeight = 0, brWidth = 0;


        for (int i = 0; i < cCount; i++) {
            View child = getChildAt(i);
            cWidth = child.getMeasuredWidth();
            cHeight = child.getMeasuredHeight();
            cParams = (MarginLayoutParams) child.getLayoutParams();
            switch (i) {
                case 0: {
                    tlHeight = cHeight + cParams.topMargin + cParams.bottomMargin;
                    tlWidth = cWidth + cParams.leftMargin + cParams.rightMargin;
                    break;
                }
                case 1: {
                    trHeight = cHeight + cParams.topMargin + cParams.bottomMargin;
                    trWidth = cWidth + cParams.leftMargin + cParams.rightMargin;
                    break;
                }
                case 2: {
                    blHeight = cHeight + cParams.topMargin + cParams.bottomMargin;
                    blWidth = cWidth + cParams.leftMargin + cParams.rightMargin;
                    break;
                }
                case 3: {
                    brHeight = cHeight + cParams.topMargin + cParams.bottomMargin;
                    brWidth = cWidth + cParams.leftMargin + cParams.rightMargin;
                    break;
                }
            }
        }
        width = Math.max(tlWidth, blWidth) + Math.max(trWidth, brWidth);
        height = Math.max(tlHeight, trHeight) + Math.max(blHeight, brHeight);

        setMeasuredDimension((widthMode == MeasureSpec.EXACTLY ? sizeWidth : width), (heightMode == MeasureSpec.EXACTLY ? sizeHeight : height));
    }

    /**
     * onLayout对其所有childView进行定位（设置childView的绘制区域）
     */
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int count = getChildCount();
        int cWidth = 0;
        int cHeight = 0;
        MarginLayoutParams cParams;

        for (int i = 0; i < count; i++) {
            View child = getChildAt(i);
            cWidth = child.getMeasuredWidth();
            cHeight = child.getMeasuredHeight();
            cParams = (MarginLayoutParams) child.getLayoutParams();
            int cl = 0, ct = 0, cr = 0, cb = 0;

            switch (i) {
                case 0: {
                    cl = cParams.leftMargin;
                    ct = cParams.topMargin;
                    break;
                }
                case 1: {
                    cl = getWidth() - cParams.rightMargin - cWidth;
                    ct = cParams.topMargin;
                    break;
                }
                case 2: {
                    cl = cParams.leftMargin;
                    ct = getHeight() - cParams.bottomMargin - cHeight;
                    break;
                }
                case 3: {
                    cl = getWidth() - cParams.rightMargin - cWidth;
                    ct = getHeight() - cParams.bottomMargin - cHeight;
                    break;
                }
            }
            cr = cl + cWidth;
            cb = ct + cHeight;
            child.layout(cl, ct, cr, cb);
        }
    }
}
