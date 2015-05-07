package cn.evendy.iutil;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import cn.evendy.iutil_lib.view.activity.BaseFragment;
import cn.evendy.iutil_lib.view.widget.MySlideMenuLayout;

/**
 * @author: evendy
 * @time: 2015/5/6 22:46
 * @mail: 244085027@qq.com
 */
public class MainFragment extends BaseFragment implements View.OnClickListener {
    private Button menuToggle;
    private MySlideMenuLayout mySlideMenuLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        menuToggle = findViewById(R.id.menu_toggle);
        mySlideMenuLayout = findViewById(R.id.slideMenu);
        menuToggle.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        mySlideMenuLayout.toggle();
    }
}
