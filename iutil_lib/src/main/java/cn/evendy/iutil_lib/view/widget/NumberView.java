package cn.evendy.iutil_lib.view.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import cn.evendy.iutil_lib.R;

/**
 * Created by evendy on 2015/4/29.
 */
public class NumberView extends View {
    private String text;
    private float textSize;
    private Paint paint;

    private Rect mTextBound;

    public NumberView(Context context, AttributeSet attrs) {
        this(context, attrs, R.styleable.def_def_attr);
    }

    public NumberView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.NumberView, defStyleAttr, 0);
        text = typedArray.getString(R.styleable.NumberView_text);
        textSize = typedArray.getDimension(R.styleable.NumberView_textSize, TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
                16, getResources().getDisplayMetrics()));
        typedArray.recycle();

        paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(2);
        paint.setAntiAlias(true);
        paint.setColor(Color.BLUE);

        mTextBound = new Rect();
        paint.setTextSize(textSize);
        // 计算了描绘字体需要的范围
        paint.getTextBounds(text, 0, text.length(), mTextBound);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int radius = getWidth() > getHeight() ? getHeight() / 2 : getWidth() / 2;
        int centre = radius;
        canvas.drawCircle(centre, centre, radius - 10, paint);

        if (mTextBound.width() > getWidth()) {
            TextPaint mPaint = new TextPaint(paint);
            String msg = TextUtils.ellipsize(text, mPaint, getWidth() - getPaddingLeft() - getPaddingRight(),
                    TextUtils.TruncateAt.END).toString();
            canvas.drawText(msg, getPaddingLeft(), getHeight() - getPaddingBottom(), mPaint);
        } else {
            canvas.drawText(text, centre - mTextBound.width() / 2, centre + mTextBound.height() / 2, paint);
        }
    }
}
