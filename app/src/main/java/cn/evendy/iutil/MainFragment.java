package cn.evendy.iutil;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.Arrays;
import java.util.List;

import cn.evendy.iutil.fragment.DialogFragment;
import cn.evendy.iutil.fragment.FlowLayoutFragment;
import cn.evendy.iutil.fragment.GalleryFragment;
import cn.evendy.iutil.fragment.MySlideMenuLayoutFragment;
import cn.evendy.iutil.fragment.MyViewgroupFragment;
import cn.evendy.iutil.fragment.ORMLiteFragment;
import cn.evendy.iutil.fragment.PropertyAnimationFragment;
import cn.evendy.iutil.fragment.PwdLockFragment;
import cn.evendy.iutil.fragment.SelectMenuBarFragment;
import cn.evendy.iutil.fragment.VoiceImageFragment;
import cn.evendy.iutil.fragment.VolleyFragment;
import cn.evendy.iutil.fragment.XMLParseFragment;
import cn.evendy.iutil_lib.base.BaseFragment;

/**
 * @author: evendy
 * @time: 2015/5/6 22:46
 * @mail: 244085027@qq.com
 */
public class MainFragment extends BaseFragment implements AdapterView.OnItemClickListener {
    private ListView listView;
    private ArrayAdapter arrayAdapter;
    private List<String> items;
    private String[] names = new String[]{"VoiceImageView",
            "PwdLock",
            "DialogFragment",
            "PropertyAnimation",
            "XMLParseFragment",
            "GalleryFragment",
            "MyViewGroupFragment",
            "FlowLayoutFragment",
            "ORMLiteFragment",
            "MySlideMenuLayoutFragment",
            "VolleyFragment",
            "SelectMenuBarFragment"
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        listView = findViewById(R.id.listview);

        initListViewData();

        arrayAdapter = new ArrayAdapter(getContext(), android.R.layout.simple_list_item_1, items);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(this);
    }

    private void initListViewData() {
        items = Arrays.asList(names);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        MainActivity activity = (MainActivity) getActivity();
        BaseFragment baseFragment = null;
        switch (position) {
            case 0: {
                baseFragment = new VoiceImageFragment();
                break;
            }
            case 1: {
                baseFragment = new PwdLockFragment();
                break;
            }
            case 2: {
                baseFragment = new DialogFragment();
                break;
            }
            case 3: {
                baseFragment = new PropertyAnimationFragment();
                break;
            }
            case 4: {
                baseFragment = new XMLParseFragment();
                break;
            }
            case 5: {
                baseFragment = new GalleryFragment();
                break;
            }
            case 6: {
                baseFragment = new MyViewgroupFragment();
                break;
            }
            case 7: {
                baseFragment = new FlowLayoutFragment();
                break;
            }
            case 8: {
                baseFragment = new ORMLiteFragment();
                break;
            }
            case 9: {
                baseFragment = new MySlideMenuLayoutFragment();
                break;
            }
            case 10: {
                baseFragment = new VolleyFragment();
                break;
            }
            case 11: {
                baseFragment = new SelectMenuBarFragment();
                break;
            }

        }
        activity.loadFragment(baseFragment);
    }
}
