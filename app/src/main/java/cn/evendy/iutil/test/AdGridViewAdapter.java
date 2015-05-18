package cn.evendy.iutil.test;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * @author: evendy
 * @time: 2015/5/18 15:59
 */
public abstract class AdGridViewAdapter {
    private Context mContext;
    private LayoutInflater mInflater;
    private List mDatas;

    public AdGridViewAdapter(Context context, List mDatas) {
        this.mContext = context;
        mInflater = LayoutInflater.from(context);
        this.mDatas = mDatas;
    }

    public int getCount() {
        return mDatas.size();
    }

    public Object getItem(int position) {
        return mDatas.get(position);
    }

    public long getItemId(int position) {
        return position;
    }
    public abstract View getView(int position, View convertView, ViewGroup parent);
}
