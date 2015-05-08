package cn.evendy.iutil_lib.view.controler;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import cn.evendy.iutil_lib.R;
import cn.evendy.iutil_lib.listener.OnCancelListener;


/**
 * Created by evendy on 2015/4/29.
 */
public class PwdLockControler implements View.OnClickListener {

    private static final String ONE = "1";
    private static final String TWO = "2";
    private static final String THREE = "3";
    private static final String FOUR = "4";
    private static final String FIVE = "5";
    private static final String SIX = "6";
    private static final String SEVEN = "7";
    private static final String EIGHT = "8";
    private static final String NINE = "9";
    private static final String ZERO = "0";

    private Context context;
    private View pwdView;
    private TextView pwdShowView1, pwdShowView2, pwdShowView3, pwdShowView4;
    private TextView one, two, three, four, five, six, seven, eight, nine, zero, reset, cancel;
    private StringBuffer pwd;

    private int pwdLength = 4;

    private OnCancelListener listener;

    public PwdLockControler(Context context, View view, OnCancelListener listener) {
        this.context = context;
        pwdView = LayoutInflater.from(context).inflate(R.layout.pwd_input, null);
        ViewGroup viewGroup = (ViewGroup) view;
        viewGroup.removeAllViews();
        viewGroup.addView(pwdView);
        this.listener = listener;
        pwd = new StringBuffer();
        initView();
    }

    private void initView() {
        pwdShowView1 = (TextView) findViewById(R.id.pwd_first);
        pwdShowView2 = (TextView) findViewById(R.id.pwd_secend);
        pwdShowView3 = (TextView) findViewById(R.id.pwd_third);
        pwdShowView4 = (TextView) findViewById(R.id.pwd_fourth);
        one = (TextView) findViewById(R.id.one);
        two = (TextView) findViewById(R.id.two);
        three = (TextView) findViewById(R.id.three);
        four = (TextView) findViewById(R.id.four);
        five = (TextView) findViewById(R.id.five);
        six = (TextView) findViewById(R.id.six);
        seven = (TextView) findViewById(R.id.seven);
        eight = (TextView) findViewById(R.id.eight);
        nine = (TextView) findViewById(R.id.nine);
        zero = (TextView) findViewById(R.id.zero);
        reset = (TextView) findViewById(R.id.reset);
        cancel = (TextView) findViewById(R.id.cancel);

        one.setOnClickListener(this);
        two.setOnClickListener(this);
        three.setOnClickListener(this);
        four.setOnClickListener(this);
        five.setOnClickListener(this);
        six.setOnClickListener(this);
        seven.setOnClickListener(this);
        eight.setOnClickListener(this);
        nine.setOnClickListener(this);
        zero.setOnClickListener(this);
        reset.setOnClickListener(this);
        cancel.setOnClickListener(this);
    }

    public String getPwd() {
        return pwd.toString();
    }

    private View findViewById(int resId) {
        return pwdView.findViewById(resId);
    }


    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.one) {
            inputCode(ONE);
        } else if (id == R.id.two) {
            inputCode(TWO);
        } else if (id == R.id.three) {
            inputCode(THREE);
        } else if (id == R.id.four) {
            inputCode(FOUR);
        } else if (id == R.id.five) {
            inputCode(FIVE);
        } else if (id == R.id.six) {
            inputCode(SIX);
        } else if (id == R.id.seven) {
            inputCode(SEVEN);
        } else if (id == R.id.eight) {
            inputCode(EIGHT);
        } else if (id == R.id.nine) {
            inputCode(NINE);
        } else if (id == R.id.zero) {
            inputCode(ZERO);
        } else if (id == R.id.reset) {
            resetPwd();
        } else if (id == R.id.cancel) {
            cancel();
        }
    }

    public void cancel() {
        if (listener != null) {
            listener.OnCancel();
        }
    }

    public void resetPwd() {
        pwd = new StringBuffer();
        pwdShowView1.setText("");
        pwdShowView2.setText("");
        pwdShowView3.setText("");
        pwdShowView4.setText("");
    }

    public void setPwdLength(int size) {
        pwdLength = size;
    }

    private void inputCode(String inputNumber) {
        if (pwd.length() >= pwdLength) {
            Toast.makeText(context, "can not input more number...", Toast.LENGTH_SHORT).show();
        } else {
            pwd.append(inputNumber);
            switch (pwd.length()) {
                case 4:
                    pwdShowView4.setText(inputNumber);
                    break;
                case 3:
                    pwdShowView3.setText(inputNumber);
                    break;
                case 2:
                    pwdShowView2.setText(inputNumber);
                    break;
                case 1:
                    pwdShowView1.setText(inputNumber);
                    break;
            }
        }
    }

}
