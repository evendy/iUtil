package cn.evendy.iutil.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import cn.evendy.iutil.R;
import cn.evendy.iutil.dialog.InputDialogFragment;
import cn.evendy.iutil.dialog.LoginDialogFragment;
import cn.evendy.iutil_lib.base.BaseFragment;

/**
 * Created by evendy on 2015/5/4.
 */
public class DialogFragment extends BaseFragment implements View.OnClickListener, LoginDialogFragment.LoginDialogBTNClickListener {

    private Button createDialog, createView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_dialogfragment, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        createDialog = (Button) getView().findViewById(R.id.createdialog);
        createView = (Button) getView().findViewById(R.id.createview);

        createView.setOnClickListener(this);
        createDialog.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.createdialog: {
                showCreateDialog();
                break;
            }
            case R.id.createview: {
                showCreateView();
                break;
            }
        }
    }

    private InputDialogFragment inputDialogFragment;
    private LoginDialogFragment loginDialogFragment;

    private void showCreateDialog() {
        String userName = "evendy";
        loginDialogFragment = LoginDialogFragment.build(userName);
        loginDialogFragment.setOnLoginDialogClickListener(this);
        inputDialogFragment.setCancelable(false);
        loginDialogFragment.show(getFragmentManager(), "login");
    }

    private void showCreateView() {
        inputDialogFragment = new InputDialogFragment();
        inputDialogFragment.show(getFragmentManager(), "input");
    }

    @Override
    public void OnLogin(String userName, String pwd) {
        Toast.makeText(getActivity(), "userName=" + userName + "  " + "pwd=" + pwd, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void OnCancel() {

    }
}
