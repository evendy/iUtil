package cn.evendy.iutil.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;

import cn.evendy.iutil.R;
import cn.evendy.iutil_lib.base.BaseFragment;
import cn.evendy.iutil_lib.view.adapter.GalleryAdapter;
import cn.evendy.iutil_lib.view.widget.GalleryView;


/**
 * Created by evendy on 2015/5/5.
 */
public class GalleryFragment extends BaseFragment implements GalleryAdapter.GalleryItemClickListener {
    private GalleryView galleryView;
    private GalleryAdapter adapter;
    private List<Integer> mDatas;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_gallery, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initDatas();
        galleryView = findViewById(R.id.gallery);
        //设置布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        galleryView.setLayoutManager(linearLayoutManager);
        adapter = new GalleryAdapter(getContext(), mDatas);
        adapter.setOnGalleryItemClickListener(this);
        galleryView.setAdapter(adapter);
    }

    private void initDatas() {
        mDatas = Arrays.asList(R.drawable.icon, R.drawable.icon, R.drawable.icon, R.drawable.icon, R.drawable.icon, R.drawable.icon, R.drawable.icon);

    }

    @Override
    public void OnGalleryItemClick(View parentView, int position) {
        Toast.makeText(getContext(), "position=" + position, Toast.LENGTH_SHORT).show();
    }
}
