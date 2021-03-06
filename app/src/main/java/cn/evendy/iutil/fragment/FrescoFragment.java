package cn.evendy.iutil.fragment;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.backends.pipeline.PipelineDraweeController;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;

import java.util.Arrays;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import cn.evendy.iutil.R;
import cn.evendy.iutil_lib.base.BaseFragment;
import cn.evendy.iutil_lib.view.adapter.CommonAdapter;
import cn.evendy.iutil_lib.view.viewholder.ViewHolder;

/**
 * @author: evendy
 * @time: 2015/5/25 16:17
 */
public class FrescoFragment extends BaseFragment {
    String[] imgPaths = new String[]{
            "http://b.zol-img.com.cn/sjbizhi/images/8/640x1136/1432174264455.jpg",
            "http://b.zol-img.com.cn/sjbizhi/images/7/720x1280/1401267196604.jpg",
            "http://b.zol-img.com.cn/sjbizhi/images/8/640x1136/1431681494192.jpg",
            "http://b.zol-img.com.cn/sjbizhi/images/3/320x480/136099432868.jpg",
            "http://b.zol-img.com.cn/sjbizhi/images/3/320x480/1360994345266.jpg",
            "http://b.zol-img.com.cn/sjbizhi/images/8/640x960/1431069076745.jpg",
            "http://b.zol-img.com.cn/sjbizhi/images/8/800x1280/1431069082719.jpg"
    };

    @InjectView(R.id.listview)
    protected ListView listView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_imageloader, container, false);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        List<String> data = Arrays.asList(imgPaths);

        CommonAdapter<String> adapter = new CommonAdapter<String>(getContext(), data, R.layout.view_fresco_item) {
            @Override
            public void inflaterView(ViewHolder viewHolder, int position, String item) {
                viewHolder.setText(R.id.item_name, "picture");
                SimpleDraweeView imageView = viewHolder.getView(R.id.item_icon);
                ImageRequest imageRequest =
                        ImageRequestBuilder.newBuilderWithSource(Uri.parse(item))
                                .setResizeOptions(
                                        new ResizeOptions(imageView.getLayoutParams().width, imageView.getLayoutParams().height))
                                .build();
                DraweeController draweeController = Fresco.newDraweeControllerBuilder()
                        .setImageRequest(imageRequest)
                        .setOldController(imageView.getController())
                        .setAutoPlayAnimations(true)
                        .build();
                imageView.setController(draweeController);
            }
        };
        listView.setAdapter(adapter);
    }
}
