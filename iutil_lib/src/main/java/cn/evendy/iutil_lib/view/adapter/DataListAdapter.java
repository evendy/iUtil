package cn.evendy.iutil_lib.view.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cn.evendy.iutil_lib.R;

public class DataListAdapter extends BaseAdapter {

	private LayoutInflater inflater;
	private int selection;
	private ListView listView;
	private List<String> data;
	private Context context;

	private List<View> contentViews;

	public DataListAdapter(Context context, ListView listView, int selection,
			List<String> data) {
		this.listView = listView;
		this.selection = selection;
		this.data = data;
		inflater = LayoutInflater.from(context);
		this.context = context;
		contentViews = new ArrayList<View>();
	}

	@Override
	public int getCount() {
		return data.size();
	}

	@Override
	public Object getItem(int index) {
		return data.get(index);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	private class ViewHolder {
		public TextView title;
		public CheckBox checkbox;
	}

	@Override
	public View getView(int position, View contentView, ViewGroup parent) {
		ViewHolder holder = null;
		if (contentView == null) {
			contentView = inflater.inflate(R.layout.listpicker_item, null);
			holder = new ViewHolder();
			holder.title = (TextView) contentView.findViewById(R.id.item_tile);
			holder.checkbox = (CheckBox) contentView
					.findViewById(R.id.checkbox);

			contentViews.add(contentView);
			contentView.setTag(holder);
		} else {
			holder = (ViewHolder) contentView.getTag();
		}
		contentView.setBackground(context.getResources().getDrawable(
				R.drawable.list_item_selector));
		holder.title.setText(data.get(position));
		holder.checkbox.setId(position);
		addOnClickListener(position, holder.checkbox, contentView);
		if (position == selection) {
			holder.checkbox.setChecked(true);
			Log.i("evendy", position + ":" + holder.checkbox.isChecked());
		} else {
			holder.checkbox.setChecked(false);
			Log.i("evendy", position + ":" + holder.checkbox.isChecked());
		}
		return contentView;
	}

	public void addOnClickListener(final int position, final CheckBox checkBox,
			View contentView) {
		contentView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				if (!checkBox.isChecked()) {
					checkBox.setChecked(true);
					Log.i("evendy", "true setChecked=" + position);
				}
				notifyDataSetChanged();
			}
		});

		checkBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				Log.i("evendy", "position=" + position);
				Log.i("evendy", "selection=" + selection);
				if (checkBox.isChecked() && selection != position) {
					if (selection != -1) {
						// 找到上次点击的checkbox,并把它设置为false,对重新选择时可以将以前的关掉
						CheckBox tempCheckBox = (CheckBox) listView
								.findViewById(selection);
						if (tempCheckBox != null) {
							tempCheckBox.setChecked(!tempCheckBox.isChecked());
							Log.i("evendy", "false setChecked=" + selection);
						}
					}
					selection = buttonView.getId();// 保存当前选中的checkbox的id值
				}

			}
		});

	}

	public int getSelectedIndex() {
		return selection;
	}

}
