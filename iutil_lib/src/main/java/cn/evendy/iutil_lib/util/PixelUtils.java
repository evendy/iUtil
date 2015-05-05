package cn.evendy.iutil_lib.util;

import android.content.Context;
import android.content.res.Resources;
/**
 * @author：evendy
 * @time: 2015/4/25 15:15
 * @mail：244085027@qq.com
 */
public class PixelUtils {

	private PixelUtils(){}

	/**
	 * dp转px
	 * @param context
	 * @param value
	 * @return the int
	 */
	public static int dp2px(Context context,float value) {
		final float scale = context.getResources().getDisplayMetrics().densityDpi;
		return (int) (value * (scale / 160) + 0.5f);
	}

	/**
	 * px转dp
	 * @param context
	 * @param value
	 * @return the int
	 */
	public static int px2dp(Context context,float value) {
		final float scale = context.getResources().getDisplayMetrics().densityDpi;
		return (int) ((value * 160) / scale + 0.5f);
	}

	/**
	 * sp转px
	 * @param context
	 * @param value
	 * @return the int
	 */
	public static int sp2px(Context context,float value) {
		Resources r;
		if (context == null) {
			r = Resources.getSystem();
		} else {
			r = context.getResources();
		}
		float spvalue = value * r.getDisplayMetrics().scaledDensity;
		return (int) (spvalue + 0.5f);
	}

	/**
	 * px转sp
	 * @param context
	 * @param value
	 * @return the int
	 */
	public static int px2sp(Context context,float value) {
		final float scale = context.getResources().getDisplayMetrics().scaledDensity;
		return (int) (value / scale + 0.5f);
	}

}
