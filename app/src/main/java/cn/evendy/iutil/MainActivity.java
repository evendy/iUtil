package cn.evendy.iutil;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.MenuItem;

import cn.evendy.iutil_lib.base.BaseActivity;
import cn.evendy.iutil_lib.base.BaseFragment;


/**
 * @author: evendy
 * @time: 2015/5/6 22:42
 * @mail: 244085027@qq.com
 */
public class MainActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        MainFragment main = new MainFragment();
        fragmentTransaction.replace(R.id.content, main, "mainFragment");
        fragmentTransaction.commit();
    }

    public void loadFragment(BaseFragment fragment, Bundle bundle) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        if (bundle != null)
            fragment.setArguments(bundle);
        fragmentTransaction.replace(R.id.content, fragment);
        fragmentTransaction.addToBackStack(null).commit();
    }

    public void loadFragment(BaseFragment fragment) {
        loadFragment(fragment, null);
    }
}
