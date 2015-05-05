package cn.evendy.iutil_lib.view.dialog;

import android.content.Context;
import android.view.View;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import cn.evendy.iutil_lib.R;
import cn.evendy.iutil_lib.view.adapter.NumericWheelAdapter;
import cn.evendy.iutil_lib.view.listener.OnWheelScrollListener;
import cn.evendy.iutil_lib.view.widget.WheelView;

public class WhellTimePickerDialog extends BaseDialog {

	private WheelView hoursWhell;
	private WheelView minsWhell;

	private View.OnClickListener onLeftBTNClickListener = null;
	private View.OnClickListener onRightBTNClickListener = null;

	private Calendar mCalendar;

	private DateFormat dateFormat = new SimpleDateFormat("HH:mm");

	public WhellTimePickerDialog(Context context) {
		super(context);
		setDialogContentView(R.layout.include_whell_time_picker);

		initTimePickerView();
	}

	public WhellTimePickerDialog(Context context, Calendar calendar) {
		this(context);
		this.mCalendar = calendar;
		setDialogContentView(R.layout.include_whell_time_picker);

		initTimePickerView();
	}

	private void initTimePickerView() {
		hoursWhell = (WheelView) findViewById(R.id.hour);
		hoursWhell.setAdapter(new NumericWheelAdapter(0, 23));
		hoursWhell.setLabel("hours");
		hoursWhell.setCyclic(true);// 可循环滚动

		minsWhell = (WheelView) findViewById(R.id.mins);
		minsWhell.setAdapter(new NumericWheelAdapter(0, 59, "%02d"));
		minsWhell.setLabel("mins");
		minsWhell.setCyclic(true);

		hoursWhell.setVisibleItems(5);
		minsWhell.setVisibleItems(5);

		// set time
		if (mCalendar == null) {
			mCalendar = Calendar.getInstance();
		}

		int curHours = mCalendar.get(Calendar.HOUR_OF_DAY);
		int curMinutes = mCalendar.get(Calendar.MINUTE);

		hoursWhell.setCurrentItem(curHours);
		minsWhell.setCurrentItem(curMinutes);

		updateTime();

		OnWheelScrollListener scrollListener = new OnWheelScrollListener() {
			public void onScrollingStarted(WheelView wheel) {
				updateTime();
			}
			public void onScrollingFinished(WheelView wheel) {
				updateTime();
			}
		};

		hoursWhell.addScrollingListener(scrollListener);
		minsWhell.addScrollingListener(scrollListener);
	}

	private void updateTime() {
		int hour = hoursWhell.getCurrentItem();
		int mins = minsWhell.getCurrentItem();
		mCalendar.set(mCalendar.get(Calendar.YEAR),
				mCalendar.get(Calendar.MONTH),
				mCalendar.get(Calendar.DAY_OF_MONTH), hour, mins);

		String dateText = dateFormat.format(mCalendar.getTime());
		setTitle(dateText);
	}

	public Calendar getCurrentTime() {
		return mCalendar;
	}

	public void setCurrentTime(Calendar mCalendar) {
		this.mCalendar = mCalendar;
	}

	public void setButton(CharSequence text,
						  OnClickListener listener) {
		super.setButton1(text, listener);
	}

	public void setButton(CharSequence text1,
						  OnClickListener listener1, CharSequence text2,
						  OnClickListener listener2) {
		super.setButton1(text1, listener1);
		super.setButton2(text2, listener2);
	}

}
