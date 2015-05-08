package cn.evendy.iutil.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import cn.evendy.iutil.R;


/**
 * Created by evendy on 2015/4/30.
 */
public class LoginDialogFragment extends BaseDialogFragment implements View.OnClickListener {
    private static String USERNAME = "userName";
    private String userName;

    private EditText userInput;
    private EditText pwdInput;

    private AlertDialog dialog;

    private LoginDialogBTNClickListener listener;

    public static LoginDialogFragment build(String userName) {
        LoginDialogFragment dialog = new LoginDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putString(USERNAME, userName);
        dialog.setArguments(bundle);
        return dialog;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        userName = getArguments().getString(USERNAME);
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.login_dialog, null);
        builder.setView(view);
        userInput = findViewById(view, R.id.name_input);
        pwdInput = findViewById(view, R.id.pwd_input);
        Button loginBTN = findViewById(view, R.id.login);
        Button cancelBTN = findViewById(view, R.id.cancel);
        loginBTN.setOnClickListener(this);
        cancelBTN.setOnClickListener(this);
        dialog = builder.create();

        userInput.setText(userName);
        return dialog;
    }

    public void setOnLoginDialogClickListener(LoginDialogBTNClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login: {
                dialog.dismiss();
                if (listener != null) {
                    userName = userInput.getText().toString();
                    String pwd = pwdInput.getText().toString();
                    listener.OnLogin(userName, pwd);
                }
                break;
            }
            case R.id.cancel: {
                dialog.dismiss();
                if (listener != null) {
                    listener.OnCancel();
                }
                break;
            }
        }
    }


    public interface LoginDialogBTNClickListener {
        void OnLogin(String userName, String pwd);

        void OnCancel();
    }
}
