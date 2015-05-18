package cn.evendy.iutil_lib.view.menubar;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import cn.evendy.iutil_lib.listener.MenuItemClickListener;

/**
 * @author: evendy
 * @time: 2015/5/18 9:42
 */
public class MenuBar extends LinearLayout {
    protected MenuItemClickListener menuItemClickListener;

    public MenuBar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void addView(View child) {
        if (child.getLayoutParams() == null) {
            LayoutParams lp;
            if (getOrientation() == LinearLayout.HORIZONTAL) {
                lp = new LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 1.0f);
            } else {
                lp = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            }
            lp.setMargins(0, 0, 0, 0);
            child.setLayoutParams(lp);
        }
        child.setFocusable(true);
        child.setClickable(true);
        super.addView(child);

        setChildViewClickListener(child);
    }

    private void setChildViewClickListener(View child) {
        child.setOnClickListener(new OnChildViewClickListener(getChildCount() - 1));
    }

    private class OnChildViewClickListener implements OnClickListener {
        private int menuIndex;

        public OnChildViewClickListener(int menuIndex) {
            this.menuIndex = menuIndex;
        }

        @Override
        public void onClick(View v) {
            onChildViewClick(v, menuIndex);
        }
    }

    protected void onChildViewClick(View view, int menuIndex) {
        if (null != menuItemClickListener) {
            menuItemClickListener.menuItemClick(view, menuIndex);
        }
    }

    public void setMenuItemClickListener(MenuItemClickListener menuItemClickListener) {
        this.menuItemClickListener = menuItemClickListener;
    }
}
