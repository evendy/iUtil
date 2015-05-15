package cn.evendy.iutil.fragment;

import android.animation.Keyframe;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.graphics.Point;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import cn.evendy.iutil.R;
import cn.evendy.iutil_lib.base.BaseFragment;
import cn.evendy.iutil_lib.util.ScreenUtils;

/**
 * Created by evendy on 2015/5/4.
 */
public class PropertyAnimationFragment extends BaseFragment {

    private ObjectAnimator animator;
    private DisplayMetrics metrics;

    private ImageView icon1;
    private ImageView icon2;
    private ImageView icon3;
    private ImageView icon4;
    private ImageView icon5;

    float shakeFactor = 1f;
    float delta = 5;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        metrics = ScreenUtils.getScreenInfo(getContext());
        return inflater.inflate(R.layout.fragment_propertyanimation, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        icon1 = findViewById(R.id.icon1);
        icon1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ObjectAnimator.ofFloat(icon1, "rotation", 0.0f, 360.0f).setDuration(1000).start();//rotation,rotationX,rotationY,旋转
            }
        });

        icon2 = findViewById(R.id.icon2);
        icon2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PropertyValuesHolder pvh = PropertyValuesHolder.ofFloat("alpha", 1.0f, 0f);//͸透明度
                PropertyValuesHolder pvhX = PropertyValuesHolder.ofFloat("scaleX", 1.0f, 0f);//放大缩小
                PropertyValuesHolder pvhY = PropertyValuesHolder.ofFloat("scaleY", 1.0f, 0f);
                if (animator == null) {
                    animator = ObjectAnimator.ofPropertyValuesHolder(icon2, pvh, pvhX, pvhY).setDuration(1000);
                    animator.setRepeatCount(9);
                    animator.setRepeatMode(ValueAnimator.REVERSE);
                    animator.start();
                } else if (animator.isRunning()) {
                    animator.cancel();
                    animator = null;
                }
            }
        });

        icon3 = findViewById(R.id.icon3);
        icon3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ValueAnimator animator = ValueAnimator.ofFloat(0, metrics.heightPixels - icon3.getHeight());
                animator.setTarget(icon3);
                animator.setRepeatMode(ValueAnimator.REVERSE);
                animator.setRepeatCount(1);
                animator.setDuration(2000).start();
                animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        icon3.setTranslationY((Float) animation.getAnimatedValue());
                    }
                });
            }
        });

        icon4 = findViewById(R.id.icon4);
        icon4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PropertyValuesHolder propertyScaleX = PropertyValuesHolder.ofKeyframe(View.SCALE_X,
                        Keyframe.ofFloat(0f, 1f),
                        Keyframe.ofFloat(0.1f, 0.9f),
                        Keyframe.ofFloat(0.2f, 0.9f),
                        Keyframe.ofFloat(0.3f, 0.9f),
                        Keyframe.ofFloat(0.4f, 0.9f),
                        Keyframe.ofFloat(0.5f, 0.9f),
                        Keyframe.ofFloat(0.6f, 1.1f),
                        Keyframe.ofFloat(0.7f, 1.1f),
                        Keyframe.ofFloat(0.8f, 1.1f),
                        Keyframe.ofFloat(0.9f, 1.1f),
                        Keyframe.ofFloat(1f, 1f)
                );

                /**
                 *  Keyframe.ofFloat(key, value)时间与数值的改变对应关系
                 *  key：time值
                 *  value:改变值
                 */

                PropertyValuesHolder propertyScaleY = PropertyValuesHolder.ofKeyframe(View.SCALE_Y,
                        Keyframe.ofFloat(0f, 1f),
                        Keyframe.ofFloat(0.1f, 0.9f),
                        Keyframe.ofFloat(0.2f, 0.9f),
                        Keyframe.ofFloat(0.3f, 0.9f),
                        Keyframe.ofFloat(0.4f, 0.9f),
                        Keyframe.ofFloat(0.5f, 0.9f),
                        Keyframe.ofFloat(0.6f, 1.1f),
                        Keyframe.ofFloat(0.7f, 1.1f),
                        Keyframe.ofFloat(0.8f, 1.1f),
                        Keyframe.ofFloat(0.9f, 1.1f),
                        Keyframe.ofFloat(1f, 1f)
                );

                PropertyValuesHolder propertyRotate = PropertyValuesHolder.ofKeyframe(View.ROTATION,
                        Keyframe.ofFloat(0f, 0f),
                        Keyframe.ofFloat(0.1f, -3f * shakeFactor),
                        Keyframe.ofFloat(0.2f, 3f * shakeFactor),
                        Keyframe.ofFloat(0.3f, -3f * shakeFactor),
                        Keyframe.ofFloat(0.4f, 3f * shakeFactor),
                        Keyframe.ofFloat(0.5f, -3f * shakeFactor),
                        Keyframe.ofFloat(0.6f, 3f * shakeFactor),
                        Keyframe.ofFloat(0.7f, -3f * shakeFactor),
                        Keyframe.ofFloat(0.8f, 3f * shakeFactor),
                        Keyframe.ofFloat(0.9f, -3f * shakeFactor),
                        Keyframe.ofFloat(1f, 0)
                );

                ObjectAnimator.ofPropertyValuesHolder(icon4, propertyScaleX, propertyScaleY, propertyRotate).setDuration(1000).start();
            }
        });

        icon5 = findViewById(R.id.icon5);
        icon5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PropertyValuesHolder propertyX = PropertyValuesHolder.ofKeyframe(View.TRANSLATION_X,
                        Keyframe.ofFloat(0f, 0f),
                        Keyframe.ofFloat(0.1f, -delta),
                        Keyframe.ofFloat(0.2f, delta),
                        Keyframe.ofFloat(0.3f, -delta),
                        Keyframe.ofFloat(0.4f, delta),
                        Keyframe.ofFloat(0.5f, -delta),
                        Keyframe.ofFloat(0.6f, delta),
                        Keyframe.ofFloat(0.7f, -delta),
                        Keyframe.ofFloat(0.8f, delta),
                        Keyframe.ofFloat(0.9f, -delta),
                        Keyframe.ofFloat(1f, 0)
                );
                ObjectAnimator.ofPropertyValuesHolder(icon5, propertyX).setDuration(1000).start();
            }
        });
    }
}
