package cn.evendy.iutil.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cn.evendy.iutil.R;
import cn.evendy.iutil_lib.base.BaseFragment;

/**
 * Created by evendy on 2015/5/4.
 */
public class VoiceImageFragment extends BaseFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_voiceimage, container, false);
    }
}
