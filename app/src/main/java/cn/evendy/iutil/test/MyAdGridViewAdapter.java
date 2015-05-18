package cn.evendy.iutil.test;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import cn.evendy.iutil.R;

/**
 * @author: evendy
 * @time: 2015/5/18 16:13
 */
public class MyAdGridViewAdapter extends AdGridViewAdapter {
    private Context mContext;

    public MyAdGridViewAdapter(Context context, List mDatas) {
        super(context, mDatas);
        this.mContext = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = new LinearLayout(mContext);
            convertView.setLayoutParams(new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 1.0f));
            View container = LayoutInflater.from(mContext).inflate(R.layout.view_tabmenu_item, null);
            ((LinearLayout) convertView).addView(container);
            holder.imageView = findViewById(container, R.id.tab_menu_icon);
            holder.textView = findViewById(container, R.id.tab_menu_name);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        Integer bitmapId =(Integer) getItem(position);
        holder.textView.setText("haha");
        holder.imageView.setImageResource(bitmapId);
        return convertView;
    }

    class ViewHolder {
        private TextView textView;
        private ImageView imageView;
    }

    private <V extends View> V findViewById(View container, int id) {
        return (V) container.findViewById(id);
    }
}
