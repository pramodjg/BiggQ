package com.wemob.app.biggq.utils;

/**
 * Created by admin on 9/7/2017.
 */

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import org.json.JSONObject;

/**
 * Created by Shiburagi on 26/09/2016.
 */

public class Utils {
    public static void showKeyBoard(View view) {
        if (view != null) {
            if (view instanceof EditText) {
                EditText editText = (EditText) view;
                editText.setSelection(editText.length());
            }
            InputMethodManager imm = (InputMethodManager)
                    view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT);
        }
    }

    public static void hideKeyBoard(View view) {
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)
                    view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public static Snackbar createSnackBar(View view,String message, int type)
    {
        Snackbar snackbar = Snackbar
                .make(view, message, type);
        return snackbar;
    }
}