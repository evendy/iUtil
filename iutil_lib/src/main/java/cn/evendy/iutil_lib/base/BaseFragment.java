package cn.evendy.iutil_lib.base;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.view.View;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class BaseFragment extends Fragment {
    protected List<AsyncTask> mAsyncTasks = new ArrayList<AsyncTask>();

    protected Context getContext() {
        return getActivity();
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

    protected void putAsyncTask(AsyncTask asyncTask) {
        mAsyncTasks.add(asyncTask.execute());
    }

    protected void clearAsyncTask() {
        Iterator<AsyncTask> iterator = mAsyncTasks
                .iterator();
        while (iterator.hasNext()) {
            AsyncTask asyncTask = iterator.next();
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
        intent.setClass(getContext(), cls);
        startActivity(intent);
    }

    protected <V extends View> V findViewbyId(int viewRes) {
        return (V) findViewById(viewRes);
    }
}
