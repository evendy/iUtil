package cn.evendy.iutil;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cn.evendy.iutil_lib.view.activity.BaseFragment;

/**
 * @author£ºevendy
 * @time: 2015/5/6 22:46
 * @mail£º244085027@qq.com
 */
public class MainFragment extends BaseFragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
}
