package cn.evendy.iutil_lib.view.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;

import cn.evendy.iutil_lib.util.PhotoUtils;

/**
 * 圆角ImageView,自动切成圆角形状
 *
 * @author evendy
 * @date 2015-4-12
 */
public class RoundImageView extends ImageView {

    private int corner = 3;//弧度

    public RoundImageView(Context context) {
        super(context);
    }

    public RoundImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public RoundImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void setImageBitmap(Bitmap bm) {
        this.setImageBitmap(bm, corner);
    }

    public void setImageBitmap(Bitmap bm, int corner) {
        bm = PhotoUtils.toRoundCorner(bm, corner);
        super.setImageBitmap(bm);
    }
}
