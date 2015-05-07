package cn.evendy.iutil_lib.view.activity;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class BaseFragment extends Fragment {
    protected Activity mActivity;
    protected Context mContext;
    protected Application mApplication;

    protected List<AsyncTask<Void, Void, Boolean>> mAsyncTasks = new ArrayList<AsyncTask<Void, Void, Boolean>>();

    public BaseFragment() {
        super();
    }

    public BaseFragment(Application application, Activity activity,
                        Context context) {
        mApplication = application;
        mActivity = activity;
        mContext = context;
    }

    @Override
    public void onDestroy() {
        clearAsyncTask();
        super.onDestroy();
    }

    public <V extends View> V findViewById(int id) {
        if (getView() == null)
            return null;
        return (V) getView().findViewById(id);
    }

    protected void putAsyncTask(AsyncTask<Void, Void, Boolean> asyncTask) {
        mAsyncTasks.add(asyncTask.execute());
    }

    protected void clearAsyncTask() {
        Iterator<AsyncTask<Void, Void, Boolean>> iterator = mAsyncTasks
                .iterator();
        while (iterator.hasNext()) {
            AsyncTask<Void, Void, Boolean> asyncTask = iterator.next();
            if (asyncTask != null && !asyncTask.isCancelled()) {
                asyncTask.cancel(true);
            }
        }
        mAsyncTasks.clear();
    }

    /**
     * 通过Class跳转界面 *
     */
    protected void startActivity(Class<?> cls) {
        Intent intent = new Intent();
        intent.setClass(mContext, cls);
        startActivity(intent);
    }
}
