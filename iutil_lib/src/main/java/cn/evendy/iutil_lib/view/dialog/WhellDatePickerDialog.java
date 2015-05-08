package cn.evendy.iutil_lib.view.dialog;

import android.content.Context;
import android.view.View;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import cn.evendy.iutil_lib.R;
import cn.evendy.iutil_lib.view.adapter.NumericWheelAdapter;
import cn.evendy.iutil_lib.listener.OnWheelChangedListener;
import cn.evendy.iutil_lib.view.widget.WheelView;

public class WhellDatePickerDialog extends BaseDialog {

	private WheelView yearWhell;
	private WheelView monthWhell;
	private WheelView dayWhell;

	private View.OnClickListener onLeftBTNClickListener = null;
	private View.OnClickListener onRightBTNClickListener = null;

	private Calendar mCalendar;
	private int startYear = 1901;
	private int endYear = 2100;

	private List<String> list_big;
	private List<String> list_little;

	private DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

	public WhellDatePickerDialog(Context context, Calendar calendar) {
		super(context);
		setDialogContentView(R.layout.include_whell_date_picker);
		this.mCalendar = calendar;
		initDatePickerView();
	}

	public WhellDatePickerDialog(Context context, Calendar calendar,
								 int startYear, int endYear) {
		super(context);
		setDialogContentView(R.layout.include_whell_date_picker);
		this.mCalendar = calendar;
		this.startYear=startYear;
		this.endYear=endYear;
		initDatePickerView();
	}

	private void initDatePickerView() {
		initDateWhell();

		// set time
		if (mCalendar == null) {
			mCalendar = Calendar.getInstance();
		}
		int curYear = mCalendar.get(Calendar.YEAR);
		int curMonth = mCalendar.get(Calendar.MONTH);
		int curDay = mCalendar.get(Calendar.DAY_OF_MONTH) - 1;

		yearWhell.setCurrentItem(curYear - startYear);
		monthWhell.setCurrentItem(curMonth);

		// 判断大小月及是否闰年,用来确定"日"的数据
		if (list_big.contains(String.valueOf(curMonth + 1))) {
			dayWhell.setAdapter(new NumericWheelAdapter(1, 31));
		} else if (list_little.contains(String.valueOf(curMonth + 1))) {
			dayWhell.setAdapter(new NumericWheelAdapter(1, 30));
		} else {
			if ((curYear % 4 == 0 && curYear % 100 != 0) || curYear % 400 == 0)
				dayWhell.setAdapter(new NumericWheelAdapter(1, 29));
			else
				dayWhell.setAdapter(new NumericWheelAdapter(1, 28));
		}

		dayWhell.setCurrentItem(curDay);



		updateDateShow();

		setWhellChangeListener();
	}



	private void updateDateWhellAdapter(int year, int month) {
		// 判断大小月及是否闰年,用来确定"日"的数据
		if (list_big.contains(String.valueOf(month))) {
			dayWhell.setAdapter(new NumericWheelAdapter(1, 31));
		} else if (list_little.contains(String.valueOf(month))) {
			if (dayWhell.getCurrentItem() == 30) {
				dayWhell.setCurrentItem(29);
			}
			dayWhell.setAdapter(new NumericWheelAdapter(1, 30));
		} else {
			if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0) {
				if (dayWhell.getCurrentItem() == 30
						|| dayWhell.getCurrentItem() == 29) {
					dayWhell.setCurrentItem(28);
				}
				dayWhell.setAdapter(new NumericWheelAdapter(1, 29));
			} else {
				if (dayWhell.getCurrentItem() == 30
						|| dayWhell.getCurrentItem() == 29
						|| dayWhell.getCurrentItem() == 28) {
					dayWhell.setCurrentItem(27);
				}
				dayWhell.setAdapter(new NumericWheelAdapter(1, 28));
			}
		}
		updateDateShow();
	}

	private void setWhellChangeListener() {
		// 添加"年"监听
		OnWheelChangedListener wheelListener_year = new OnWheelChangedListener() {
			public void onChanged(WheelView wheel, int oldValue, int newValue) {
				int year_num = newValue + startYear;

				updateDateWhellAdapter(year_num,
						monthWhell.getCurrentItem() + 1);
			}
		};
		// 添加"月"监听
		OnWheelChangedListener wheelListener_month = new OnWheelChangedListener() {
			public void onChanged(WheelView wheel, int oldValue, int newValue) {
				int month_num = newValue + 1;

				updateDateWhellAdapter(yearWhell.getCurrentItem(), month_num);
			}

		};

		OnWheelChangedListener wheelListener_day = new OnWheelChangedListener() {
			public void onChanged(WheelView wheel, int oldValue, int newValue) {
				updateDateShow();
			}
		};
		yearWhell.addChangingListener(wheelListener_year);
		monthWhell.addChangingListener(wheelListener_month);
		dayWhell.addChangingListener(wheelListener_day);
	}

	private void initDateWhell() {
		// 添加大小月月份并将其转换为list,方便之后的判断
		String[] months_big = { "1", "3", "5", "7", "8", "10", "12" };
		String[] months_little = { "4", "6", "9", "11" };

		list_big = Arrays.asList(months_big);
		list_little = Arrays.asList(months_little);

		yearWhell = (WheelView) findViewById(R.id.year);
		yearWhell.setAdapter(new NumericWheelAdapter(startYear, endYear));
		yearWhell.setLabel("year");
		yearWhell.setCyclic(true);// 可循环滚动

		monthWhell = (WheelView) findViewById(R.id.month);
		monthWhell.setAdapter(new NumericWheelAdapter(1, 12));
		monthWhell.setLabel("month");
		monthWhell.setCyclic(true);// 可循环滚动

		dayWhell = (WheelView) findViewById(R.id.day);
		dayWhell.setLabel("day");
		dayWhell.setCyclic(true);// 可循环滚动

		yearWhell.setVisibleItems(5);
		monthWhell.setVisibleItems(5);
		dayWhell.setVisibleItems(5);

	}

	private void updateDateShow() {
		int year = yearWhell.getCurrentItem() + startYear;
		int month = monthWhell.getCurrentItem();
		int day = dayWhell.getCurrentItem() + 1;
		mCalendar.set(year, month, day);

		String dateText = dateFormat.format(mCalendar.getTime());
		setTitle(dateText);

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

	public WheelView getYearWhell() {
		return yearWhell;
	}

	public void setYearWhell(WheelView yearWhell) {
		this.yearWhell = yearWhell;
	}

	public int getStartYear() {
		return startYear;
	}

	public void setStartYear(int startYear) {
		this.startYear = startYear;
	}

	public Calendar getCurrentDate() {
		return mCalendar;
	}

	public void setCurrentDate(Calendar mCalendar) {
		this.mCalendar = mCalendar;
	}

}
