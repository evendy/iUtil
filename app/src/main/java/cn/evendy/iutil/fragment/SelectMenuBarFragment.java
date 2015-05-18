package cn.evendy.iutil.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.InjectView;
import cn.evendy.iutil.R;
import cn.evendy.iutil.menu.TabMenuItem;
import cn.evendy.iutil_lib.base.BaseFragment;
import cn.evendy.iutil_lib.listener.MenuSelectItemChangeListener;
import cn.evendy.iutil_lib.view.menubar.SelectMenuBar;

/**
 * @author: evendy
 * @time: 2015/5/18 12:40
 */
public class SelectMenuBarFragment extends BaseFragment implements MenuSelectItemChangeListener {
    @InjectView(R.id.bottom_menubar)
    protected SelectMenuBar bottomMenuBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_selectmenubar, container, false);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initBottomBar();
    }

    private void initBottomBar() {
        bottomMenuBar.addView(new TabMenuItem(getContext(), R.drawable.tab_home, R.string.tab_home).getView());
        bottomMenuBar.addView(new TabMenuItem(getContext(), R.drawable.tab_home, R.string.tab_home).getView());
        bottomMenuBar.addView(new TabMenuItem(getContext(), R.drawable.tab_home, R.string.tab_home).getView());
        bottomMenuBar.addView(new TabMenuItem(getContext(), R.drawable.tab_home, R.string.tab_home).getView());
        bottomMenuBar.setMenuSelectItemChangeListener(this);
    }

    @Override
    public void MenuSelectItemChange(View menuView, int position) {
        Toast.makeText(getContext(), "menu " + position, Toast.LENGTH_SHORT).show();
    }
}
