package cn.evendy.iutil_lib.util;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
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

    public static boolean isConnected(Context context) {
        if (getConnectState(context).equals(NetWorkState.NONE))
            return false;
        return true;
    }

    /**
     * 判断是否是wifi连接
     */
    public static boolean isWifiConnected(Context context) {
        if (getConnectState(context).equals(NetWorkState.WIFI))
            return false;
        return true;
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

    /**
     * 打开网络设置界面
     */
    public static void openSetting(Activity activity) {
        Intent intent = new Intent("/");
        ComponentName cm = new ComponentName("com.android.settings",
                "com.android.settings.WirelessSettings");
        intent.setComponent(cm);
        intent.setAction("android.intent.action.VIEW");
        activity.startActivityForResult(intent, 0);
    }

}
