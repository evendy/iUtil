package cn.evendy.iutil;

import android.os.Bundle;
import android.view.Window;

import cn.evendy.iutil_lib.view.activity.BaseActivity;

/**
 * @author£ºevendy
 * @time: 2015/5/6 22:42
 * @mail£º244085027@qq.com
 */
public class MainActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.main_activity);
        MainFragment fragment = new MainFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.content, fragment).commit();
    }
}
