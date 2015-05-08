package cn.evendy.iutil.fragment;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.graphics.Point;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import cn.evendy.iutil.R;
import cn.evendy.iutil_lib.base.BaseFragment;

/**
 * Created by evendy on 2015/5/4.
 */
public class PropertyAnimationFragment extends BaseFragment {
    private ImageView icon;
    private ObjectAnimator animator;
    private ImageView icon2;
    private ImageView icon3;
    private Point point;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        point = new Point();
        getActivity().getWindowManager().getDefaultDisplay().getSize(point);
        return inflater.inflate(R.layout.fragment_propertyanimation, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        icon = findViewById(R.id.icon);

        icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ObjectAnimator.ofFloat(icon, "rotation", 0.0f, 360.0f).setDuration(1000).start();//rotation,rotationX,rotationY,旋转
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
                ValueAnimator animator = ValueAnimator.ofFloat(0, point.y - icon3.getHeight());
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
    }
}
