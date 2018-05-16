package com.mex.GalaxyChain.utils;

import android.text.Editable;
import android.text.Selection;
import android.widget.EditText;

/**
 * Author: 夜天子丶
 * *
 * Date: 2016-07-01 15:32
 * *
 * QQ: 363246266
 * *
 * Version: V1.0
 */
public class EditUtils {

    /**
     * 下面两行代码实现: 输入框光标一直在输入文本后面
     */
    public static void cursorFollow(EditText et) {
        Editable et_able = et.getText();
        Selection.setSelection(et_able, et_able.length());
        et.requestFocus();
    }

    /**
     * 下面两行代码实现: 输入框光标一直在输入文本后面
     */
    public static void cursorFollowNoFocus(EditText et) {
        Editable et_able = et.getText();
        Selection.setSelection(et_able, et_able.length());
    }

    /**
     * 获取EditText光标所在的位置
     */
    public static int getEditTextCursorIndex(EditText mEditText) {
        return mEditText.getSelectionStart();
    }

    /**
     * 向EditText指定光标位置插入字符串
     */
    public static void insertText(EditText editText, String mText) {
        editText.getText().insert(getEditTextCursorIndex(editText), mText);
    }

    /**
     * 向EditText指定光标位置删除字符串
     */
    public static void deleteText(EditText editText) {
        if (!IsEmptyUtils.isEmpty(editText.getText().toString()) && getEditTextCursorIndex(editText) != 0) {
            editText.getText().delete(getEditTextCursorIndex(editText) - 1, getEditTextCursorIndex(editText));
        }
    }
}
