package com.liang.tind.www.tindtest.widget;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.widget.EditText;

/**
 * @author 梁天德
 * @date 2019/11/20 17:05
 * desc
 */
public class DigitsTextWatcher implements TextWatcher {
    private EditText editText;
    private int digits = 2;

    public DigitsTextWatcher(EditText et) {
        editText = et;
    }

    public DigitsTextWatcher setDigits(int d) {
        digits = d;
        return this;
    }

    public DigitsTextWatcher(EditText editText, int digits) {
        this.editText = editText;
        this.digits = digits;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        String str = s.toString().trim();
        if (TextUtils.isEmpty(str)){
            return;
        }
        //删除“.”后面超过2位后的数据
        if (str.contains(".")) {
            if (str.length() - 1 - str.indexOf(".") > digits) {
                str = str.substring(0, str.indexOf(".") + digits + 1);
                editText.setText(str);
                editText.setSelection(str.length()); //光标移到最后
                return;
            }
        }
        //如果"."在起始位置,则起始位置自动补0
        if (str.substring(0,1).equals(".")) {
            str = "0" + s;
            editText.setText(str);
            editText.setSelection(str.length());
            return;
        }

        //如果起始位置为0,且第二位跟的不是".",则无法后续输入
        if (str.startsWith("0") && str.length() > 1) {
            if (!str.substring(1, 2).equals(".")) {
                editText.setText(str.substring(0, 1));
                editText.setSelection(1);
            }
        }
    }
}
