package cn.evendy.iutil_lib.view.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import cn.evendy.iutil_lib.R;


/**
 * Created by android on 2015/4/28.
 */
public class VoiceImage extends View {
    private int circleColor;
    private int progressColor;
    private int progress;
    private Bitmap icon;
    private float ringWidth;
    private int count;
    private float splitWidth;

    private Paint paint;

    public VoiceImage(Context context, AttributeSet attrs) {
        this(context, attrs, R.styleable.def_def_attr);
    }

    public VoiceImage(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.VoiceImage, defStyleAttr, 0);
        circleColor = typedArray.getColor(R.styleable.VoiceImage_circleColor, android.R.color.black);
        progressColor = typedArray.getColor(R.styleable.VoiceImage_progressColor, android.R.color.darker_gray);
        icon = BitmapFactory.decodeResource(getResources(), typedArray.getResourceId(R.styleable.VoiceImage_vIcon, R.drawable.icon));
        ringWidth = typedArray.getDimension(R.styleable.VoiceImage_ringWidth, 20f);
        splitWidth = typedArray.getDimension(R.styleable.VoiceImage_split, 5f);

        count = typedArray.getInteger(R.styleable.VoiceImage_count, 20);
        progress = typedArray.getInteger(R.styleable.VoiceImage_progress, 3);

        typedArray.recycle();

        paint = new Paint();
        paint.setAntiAlias(true); // 消除锯齿
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int centre = getWidth() / 2;
        float radius = centre - ringWidth / 2;
        drawOval(canvas, centre, radius);

        drawBitmap(canvas, centre, radius);
    }

    private void drawBitmap(Canvas canvas, int centre, float radius) {
        float realR = centre - ringWidth;

        RectF recF = new RectF();
        recF.left = (float) (centre - realR * Math.sqrt(2) / 2);
        recF.top = (float) (centre - realR * Math.sqrt(2) / 2);
        recF.right = (float) (centre + realR * Math.sqrt(2) / 2);
        recF.bottom = (float) (centre + realR * Math.sqrt(2) / 2);

        if (icon.getWidth() < realR * Math.sqrt(2)) {
            int r = icon.getWidth() / 2;
            recF.left = centre - r;
            recF.top = centre - r;
            recF.right = centre + r;
            recF.bottom = centre + r;
        }

        canvas.drawBitmap(icon, null, recF, paint);
    }

    private void drawOval(Canvas canvas, int centre, float radius) {
        paint.setStrokeWidth(ringWidth);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStyle(Paint.Style.STROKE); // 设置空心

        float itemSize = (360 * 1.0f - count * splitWidth) / count;//每个progress块的宽度

        RectF oval = new RectF(centre - radius, centre - radius, centre + radius, centre + radius);

        paint.setColor(circleColor);
        for (int i = 0; i < count; i++) {
            canvas.drawArc(oval, i * (itemSize + splitWidth), itemSize, false, paint);
        }

        paint.setColor(progressColor);
        for (int i = 0; i < progress; i++) {
            canvas.drawArc(oval, i * (itemSize + splitWidth), itemSize, false, paint);
        }


    }
}
