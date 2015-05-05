package cn.evendy.iutil_lib.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo.State;

import cn.evendy.iutil_lib.bean.NetWorkState;

/**
 * @author：evendy
 * @time: 2015/4/25 15:15
 * @mail：244085027@qq.com
 */
public class NetWorkUtils {

	private NetWorkUtils() {

	}

	/**
	 * 获取当前的网络状态
	 * 
	 * @return
	 */
	public static NetWorkState getConnectState(Context context) {
		ConnectivityManager manager = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		manager.getActiveNetworkInfo();
		State wifiState = manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
				.getState();
		State mobileState = manager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)
				.getState();
		if (wifiState != null && mobileState != null
				&& State.CONNECTED != wifiState
				&& State.CONNECTED == mobileState) {
			return NetWorkState.MOBILE;
		} else if (wifiState != null && mobileState != null
				&& State.CONNECTED != wifiState
				&& State.CONNECTED != mobileState) {
			return NetWorkState.NONE;
		} else if (wifiState != null && State.CONNECTED == wifiState) {
			return NetWorkState.WIFI;
		}
		return NetWorkState.NONE;
	}

}
