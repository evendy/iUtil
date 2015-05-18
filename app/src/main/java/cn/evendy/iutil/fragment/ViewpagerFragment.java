package cn.evendy.iutil.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import cn.evendy.iutil.R;
import cn.evendy.iutil.test.MyAdGridViewAdapter;
import cn.evendy.iutil_lib.base.BaseFragment;

/**
 * @author: evendy
 * @time: 2015/5/18 13:33
 */
public class ViewpagerFragment extends BaseFragment {
    @InjectView(R.id.adview)
    protected ViewPager adview;

    private List<Integer> bitMaps;
    private static final int count = 20;
    private int bitMapRes = R.mipmap.tuanlist_category_icon_dianying_high;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_viewpager, container, false);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initAdvertisement();
    }

    private int onePageShowItem = 8;

    private void initAdvertisement() {
        bitMaps = new ArrayList<Integer>();
        for (int i = 0; i < count; i++) {
            bitMaps.add(bitMapRes);
        }

        List<GridView> pageGrid = new ArrayList<GridView>();
        int pageSum = bitMaps.size() % onePageShowItem == 0 ? bitMaps.size() / onePageShowItem : bitMaps.size() / onePageShowItem + 1;

        for (int page = 0; page < pageSum; page++) {
            GridView gridView = new GridView(getContext());
            MyGridViewAdapter gridViewAdapter = new MyGridViewAdapter(getContext(), bitMaps, page);
            gridViewAdapter.setMaxItem(onePageShowItem);
            gridView.setAdapter(gridViewAdapter);
            gridView.setGravity(Gravity.CENTER);
            gridView.setClickable(true);
            gridView.setFocusable(true);
            gridView.setNumColumns(8);
            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Toast.makeText(getContext(), "menu " + position, Toast.LENGTH_SHORT).show();
                }
            });
            pageGrid.add(gridView);
        }

        adapter = new MyViewPagerAdapter(getContext(), pageGrid);
        adview.setAdapter(adapter);
    }

    private MyViewPagerAdapter adapter;

    /**
     * 实现ViewPager页卡
     *
     * @author jiangqq
     */
    public class MyViewPagerAdapter extends PagerAdapter {
        private List<GridView> mLists;

        public MyViewPagerAdapter(Context context, List<GridView> array) {
            this.mLists = array;
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
    }

    class MyGridViewAdapter extends BaseAdapter {
        private Context context;
        private List mDatas;
        private int pageIndex;
        private int maxItem = 8;

        public MyGridViewAdapter(Context context, List mDatas, int pageIndex) {
            this.context = context;
            this.mDatas = mDatas;
            this.pageIndex = pageIndex;
        }

        @Override
        public int getCount() {
            return mDatas.size() % maxItem;
        }

        @Override
        public Object getItem(int position) {
            int index = pageIndex * maxItem + position;
            if (index > mDatas.size()) {
                return null;
            } else {
                return mDatas.get(index);
            }
        }

        @Override
        public long getItemId(int position) {
            return pageIndex * maxItem + position;
        }

        public int getMaxItem() {
            return maxItem;
        }

        public void setMaxItem(int maxItem) {
            this.maxItem = maxItem;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                holder = new ViewHolder();
                convertView = LayoutInflater.from(context).inflate(R.layout.view_tabmenu_item, null);
                holder.textView = findViewById(convertView, R.id.tab_menu_name);
                holder.imageView = findViewById(convertView, R.id.tab_menu_icon);
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
}
