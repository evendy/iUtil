package cn.evendy.iutil.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import cn.evendy.iutil.R;
import cn.evendy.iutil_lib.base.BaseFragment;
import cn.evendy.iutil_lib.listener.OnCancelListener;
import cn.evendy.iutil_lib.listener.OnPwdSubmitListener;
import cn.evendy.iutil_lib.view.controler.PwdLockControler;

/**
 * Created by evendy on 2015/5/4.
 */
public class PwdLockFragment extends BaseFragment implements OnPwdSubmitListener, OnCancelListener {
    private PwdLockControler pwdLockControler;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_pwdlock, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        pwdLockControler = new PwdLockControler(getContext(), findViewById(R.id.pwd_lay), this);
    }

    @Override
    public void OnPwdSubmit(String pwd) {
        Toast.makeText(getContext(), "submit " + pwd, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void OnCancel() {
        getFragmentManager().popBackStack();
    }
}
