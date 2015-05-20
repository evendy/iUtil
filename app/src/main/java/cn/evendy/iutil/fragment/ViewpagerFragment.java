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
import cn.evendy.iutil.test.AdGridViewAdapter;
import cn.evendy.iutil.test.AdViewPagerAdapter;
import cn.evendy.iutil_lib.base.BaseFragment;

/**
 * @author: evendy
 * @time: 2015/5/18 13:33
 */
public class ViewpagerFragment extends BaseFragment implements AdViewPagerAdapter.ShowItemClickListener {
    @InjectView(R.id.adview)
    protected ViewPager adview;

    private List<Integer> bitMaps;
    private static final int count = 20;
    private int bitMapRes = R.mipmap.tuanlist_category_icon_dianying_high;

    private AdViewPagerAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_viewpager, container, false);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initAdvertisement(2, 4);
    }

    private void initAdvertisement(int row, int column) {
        bitMaps = new ArrayList<Integer>();
        for (int i = 0; i < count; i++) {
            bitMaps.add(bitMapRes);
        }

        adapter = new AdViewPagerAdapter(getContext(), bitMaps, row, column);
        adview.setAdapter(adapter);
        adapter.setShowItemClickListener(this);
    }

    @Override
    public void onShowItemClick(long position) {
        Toast.makeText(getContext(), "click " + position, Toast.LENGTH_SHORT).show();
    }
}
