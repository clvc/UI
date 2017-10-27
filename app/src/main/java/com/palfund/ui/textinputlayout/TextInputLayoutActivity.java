package com.palfund.ui.textinputlayout;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.palfund.ui.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextInputLayoutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_input_layout);
        final TextInputLayout textInputLayout_name = (TextInputLayout) findViewById(R.id
                .textInputLayout_name);
        final TextInputLayout textInputLayout_password = (TextInputLayout) findViewById(R.id
                .textInputLayout_password);
        EditText editText_name = textInputLayout_name.getEditText();
        EditText editText_password = textInputLayout_password.getEditText();
        // 设置文本改变监听
        editText_name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!isWord(s.toString())) {
                    // 在setError设置错误消息之前开启这个功能意味着在显示错误的时候布局不会变化。
                    textInputLayout_name.setErrorEnabled(true);
                    textInputLayout_name.setError("用户名输入错误");
                } else {
                    textInputLayout_name.setErrorEnabled(false);
                    textInputLayout_name.setError("");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        // 设置文本改变监听
        editText_password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!isWord(s.toString())) {
                    textInputLayout_password.setErrorEnabled(true);
                    textInputLayout_password.setError("用户名输入错误");
                } else {
                    textInputLayout_password.setErrorEnabled(false);
                    textInputLayout_password.setError("");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    private boolean isWord(String str) {
        String regexp = "^[A-Za-z]+$";
        Pattern pattern = Pattern.compile(regexp);
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }

    private boolean isChinese(String str) {
        String regexp = "^[\u4e00-\u9fa5]+$";
        Pattern pattern = Pattern.compile(regexp);
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }

    private boolean isDetail(String str) {
        String regexp = "^([\u4e00-\u9fa5]+[,;]?[\u4e00-\u9fa5]+)+$";
        Pattern pattern = Pattern.compile(regexp);
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }

    public void click(View view) {
        hideKeyboard();
    }

    private void hideKeyboard() {
        View view = getCurrentFocus();
        if (view != null) {
            ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).
                    hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager
                            .HIDE_NOT_ALWAYS);
        }
    }
}
