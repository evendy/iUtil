package cn.evendy.iutil_lib.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import cn.evendy.iutil_lib.R;

/**
 * Created by evendy on 2015/5/4.
 */
public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.ViewHolder> {
    private LayoutInflater mInflater;
    private List<Integer> mDatas;

    private GalleryItemClickListener listener;

    public GalleryAdapter(Context context, List<Integer> datats) {
        mInflater = LayoutInflater.from(context);
        mDatas = datats;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = mInflater.inflate(R.layout.gallery_item, null);
        ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.mImg = (ImageView) view.findViewById(R.id.gallery_img);
        viewHolder.mTxt = (TextView) view.findViewById(R.id.gallery_text);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int position) {
        viewHolder.mImg.setImageResource(mDatas.get(position));
        if (listener != null) {
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.OnGalleryItemClick(viewHolder.itemView, position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View itemView) {
            super(itemView);
        }

        ImageView mImg;
        TextView mTxt;
    }

    public void setOnGalleryItemClickListener(GalleryItemClickListener listener) {
        this.listener = listener;
    }

    public interface GalleryItemClickListener {
        void OnGalleryItemClick(View parentView, int position);
    }

}
