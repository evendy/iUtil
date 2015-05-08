package cn.evendy.iutil.dialog;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;

import cn.evendy.iutil.R;


/**
 * Created by evendy on 2015/4/30.
 */
public class InputDialogFragment extends BaseDialogFragment {

    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        view = inflater.inflate(R.layout.input_dialog, null);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        EditText nameInput = findViewById(view, R.id.name_input);
        EditText pwdInput = findViewById(view, R.id.pwd_input);
    }
}
