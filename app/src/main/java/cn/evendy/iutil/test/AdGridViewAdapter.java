package cn.evendy.iutil.test;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import cn.evendy.iutil.R;

/**
 * @author: evendy
 * @time: 2015/5/19 10:10
 */
public class AdGridViewAdapter extends BaseAdapter {
    private Context context;
    /**
     * 全部展示项目的集合
     */
    private List mDatas;
    /**
     * 第几页
     */
    private int pageIndex;
    /**
     * grid行数
     */
    private int gridRow = 2;
    /**
     * grid列数
     */
    private int gridColumn = 4;

    /**
     *
     */
    private GridView gridView;

    public AdGridViewAdapter(Context context, List mDatas, int pageIndex, GridView gridView, int gridRow, int gridColumn) {
        this(context, mDatas, pageIndex, gridView);
        setGridRowAndColumn(gridRow, gridColumn);
    }

    public AdGridViewAdapter(Context context, List mDatas, int pageIndex, GridView gridView) {
        this.context = context;
        this.mDatas = mDatas;
        this.pageIndex = pageIndex;
        this.gridView = gridView;
        gridView.setGravity(Gravity.CENTER);
        gridView.setClickable(true);
        gridView.setFocusable(true);
        gridView.setNumColumns(gridColumn);
    }

    public void setGridRowAndColumn(int gridRow, int gridColumn) {
        this.gridRow = gridRow;
        this.gridColumn = gridColumn;
        gridView.setNumColumns(gridColumn);
    }

    public int getGirdShowMaxItem() {
        return gridRow * gridColumn;
    }


    @Override
    public int getCount() {
        return mDatas.size() / getGirdShowMaxItem() >= pageIndex + 1 ? getGirdShowMaxItem() : mDatas.size() % getGirdShowMaxItem();
    }

    @Override
    public Object getItem(int position) {
        int index = pageIndex * getGirdShowMaxItem() + position;
        if (index > mDatas.size()) {
            return null;
        } else {
            return mDatas.get(index);
        }
    }

    private int layoutId = R.layout.view_tabmenu_item;

    public void setItemLayoutId(int layoutId) {
        this.layoutId = layoutId;
    }

    @Override
    public long getItemId(int position) {
        return pageIndex * getGirdShowMaxItem() + position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(layoutId, null);
            holder.textView = findViewById(convertView, R.id.item_name);
            holder.imageView = findViewById(convertView, R.id.item_icon);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        Integer ids = (Integer) getItem(position);
        holder.textView.setText("haha");
        holder.imageView.setImageResource(ids);
        return convertView;
    }

    private <V extends View> V findViewById(View container, int id) {
        return (V) container.findViewById(id);
    }

    class ViewHolder {
        private TextView textView;
        private ImageView imageView;
    }
}
