package cn.evendy.iutil_lib.view.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by evendy on 2015/5/6.
 */
public abstract class CommonAdapter<T> extends BaseAdapter {

    private Context mContext;
    private List<T> mData;//待填充的数据列表
    private int itemLayoutId;//待填充的每一项的layoutId

    /**
     * @param context
     * @param mData        待填充的数据列表
     * @param itemLayoutId 待填充的每一项的layoutId
     */
    public CommonAdapter(Context context, List<T> mData, int itemLayoutId) {
        this.mContext = context;
        this.mData = mData;
        this.itemLayoutId = itemLayoutId;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public T getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = getViewHolder(position, convertView, parent);
        inflaterView(viewHolder, position, getItem(position));
        return viewHolder.getConvertView();
    }

    public abstract void inflaterView(ViewHolder viewHolder, int position, T item);

    public ViewHolder getViewHolder(int position, View convertView, ViewGroup parent) {
        return ViewHolder.get(mContext, convertView, parent, itemLayoutId, position);
    }

}
