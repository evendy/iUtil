package cn.evendy.iutil.menu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import cn.evendy.iutil.R;

/**
 * @author: evendy
 * @time: 2015/5/18 9:57
 */
public class TabMenuItem {
    private Context context;
    private int menuIconRes;
    private int menuNameRes;

    public TabMenuItem(Context context, int menuIconRes, int menuNameRes) {
        this.context = context;
        this.menuIconRes = menuIconRes;
        this.menuNameRes = menuNameRes;
    }

    public View getView() {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View menuItem = layoutInflater.inflate(R.layout.view_tabmenu_item, null);
        ImageView menuIcon = findViewById(menuItem, R.id.item_icon);
        TextView menuName = findViewById(menuItem, R.id.item_name);
        menuIcon.setImageResource(menuIconRes);
        menuName.setText(menuNameRes);
        return menuItem;
    }

    private <V extends View> V findViewById(View view, int viewId) {
        return (V) view.findViewById(viewId);
    }
}
