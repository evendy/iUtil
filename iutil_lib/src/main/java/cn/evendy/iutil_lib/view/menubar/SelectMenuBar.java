package cn.evendy.iutil_lib.view.menubar;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import cn.evendy.iutil_lib.listener.MenuItemSelectListener;
import cn.evendy.iutil_lib.listener.MenuSelectItemChangeListener;

/**
 * @author: evendy
 * @time: 2015/5/18 9:41
 */
public class SelectMenuBar extends MenuBar {
    private int selectItemIndex = 0;
    private MenuItemSelectListener menuItemSelectListener;
    private MenuSelectItemChangeListener menuSelectItemChangeListener;

    public SelectMenuBar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onChildViewClick(View view, int menuIndex) {
        super.onChildViewClick(view, menuIndex);
        onMenuItemSelect(view, menuIndex);
    }

    protected void onMenuItemSelect(View view, int menuIndex) {
        if (null != menuItemSelectListener) {
            menuItemSelectListener.menuItemSelect(view, menuIndex);
        }
        if (null != menuSelectItemChangeListener && menuIndex != selectItemIndex) {
            menuSelectItemChangeListener.MenuSelectItemChange(view, menuIndex);
        }
        setCurrentItemSelect(menuIndex);
    }

    private void setCurrentItemSelect(int menuIndex) {
        if (menuIndex > getChildCount())
            throw new RuntimeException("current Select Item index more than ChildCount");
        View childView;
        for (int i = 0; i < getChildCount(); i++) {
            childView = getChildAt(i);
            childView.setSelected(false);
        }
        getChildAt(menuIndex).setSelected(true);
        selectItemIndex = menuIndex;
    }

    public void setMenuItemSelectListener(MenuItemSelectListener menuItemSelectListener) {
        this.menuItemSelectListener = menuItemSelectListener;
    }

    public void setMenuSelectItemChangeListener(MenuSelectItemChangeListener menuSelectItemChangeListener) {
        this.menuSelectItemChangeListener = menuSelectItemChangeListener;
    }

    public void setCurSelectItem(int index) {
        if (index < 0 || index > getChildCount() - 1) {
            throw new RuntimeException("Select Item index out of ChildCount");
        }
        onMenuItemSelect(getChildAt(index), index);
    }

    public int getCurSelectItemIndex() {
        return selectItemIndex;
    }

}
