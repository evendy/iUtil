package cn.evendy.iutil.test;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import cn.evendy.iutil.R;

/**
 * 实现ViewPager页卡
 *
 * @author: evendy
 * @time: 2015/5/19 10:08
 */
public class AdViewPagerAdapter extends PagerAdapter {
    private List<GridView> mLists;
    private List showItems;
    private Context context;
    private ShowItemClickListener listener;

    public AdViewPagerAdapter(Context context, List showItems, int row, int column) {
        this.context = context;
        this.showItems = showItems;
        mLists = getAdvisementGrid(row, column);
    }

    @Override
    public int getCount() {
        return mLists.size();
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        return arg0 == arg1;
    }

    @Override
    public Object instantiateItem(View arg0, int arg1) {
        ((ViewPager) arg0).addView(mLists.get(arg1));
        return mLists.get(arg1);
    }

    @Override
    public void destroyItem(View arg0, int arg1, Object arg2) {
        ((ViewPager) arg0).removeView((View) arg2);
    }

    public Object getShowItem(int position) {
        if (position > showItems.size())
            return null;
        return showItems.get(position);
    }

    public void setShowItemClickListener(ShowItemClickListener listener) {
        this.listener = listener;
    }

    private List<GridView> getAdvisementGrid(int row, int column) {
        List<GridView> pageGrid = new ArrayList<GridView>();
        int pageSum = showItems.size() % (row * column) == 0 ? showItems.size() / (row * column) : showItems.size() / (row * column) + 1;
        for (int page = 0; page < pageSum; page++) {
            GridView gridView = new GridView(context);
            ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
            gridView.setLayoutParams(lp);
            AdGridViewAdapter gridViewAdapter = new AdGridViewAdapter(context, showItems, page, gridView);
            gridViewAdapter.setItemLayoutId(R.layout.ad_item);
            gridViewAdapter.setGridRowAndColumn(row, column);
            gridView.setAdapter(gridViewAdapter);
            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    if (null != listener) {
                        listener.onShowItemClick(parent.getAdapter().getItemId(position));
                    }
                }
            });
            pageGrid.add(gridView);
        }
        return pageGrid;
    }

    public interface ShowItemClickListener {
        void onShowItemClick(long position);
    }
}
