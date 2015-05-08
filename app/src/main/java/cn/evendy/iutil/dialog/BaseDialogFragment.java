package cn.evendy.iutil.dialog;

import android.support.v4.app.DialogFragment;
import android.view.View;

/**
 * Created by evendy on 2015/5/4.
 */
public class BaseDialogFragment extends DialogFragment {

    protected  <V extends View> V findViewById(View parentView, int resId) {
        return (V) parentView.findViewById(resId);
    }
}
