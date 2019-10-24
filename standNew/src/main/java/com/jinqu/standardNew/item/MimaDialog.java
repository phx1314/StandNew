//
//  MimaDialog
//
//  Created by DELL on 2018-07-19 08:47:48
//  Copyright (c) DELL All rights reserved.


/**

 */

package com.jinqu.standardNew.item;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.framewidget.F;
import com.jinqu.standardNew.R;
import com.jungly.gridpasswordview.GridPasswordView;
import com.jungly.gridpasswordview.PasswordType;
import com.mdx.framework.activity.MActivityActionbar;
import com.mdx.framework.utility.Helper;

import static com.jinqu.standardNew.F.mModelUsreLogin;


public class MimaDialog extends BaseItem {
    public GridPasswordView mGridPasswordView;
    public Dialog item;
    public EditText mEditText;

    @SuppressLint("InflateParams")
    public static View getView(Context context, ViewGroup parent) {
        LayoutInflater flater = LayoutInflater.from(context);
        View convertView = flater.inflate(R.layout.item_mima_dialog, null);
        convertView.setTag(new MimaDialog(convertView));
        return convertView;
    }

    public MimaDialog(View view) {
        this.contentview = view;
        this.context = contentview.getContext();
        initView();
    }

    private void initView() {
        this.contentview.setTag(this);
        findVMethod();
    }

    private void findVMethod() {
        mGridPasswordView = (GridPasswordView) contentview.findViewById(R.id.mGridPasswordView);
        mEditText = (EditText) findViewById(R.id.mEditText);
        mGridPasswordView.setOnPasswordChangedListener(new GridPasswordView.OnPasswordChangedListener() {
            @Override
            public void onTextChanged(String psw) {

            }

            @Override
            public void onInputFinish(String psw) {
                if (!psw.equals(mModelUsreLogin.password)) {
                    Helper.toast("密码不正确", context);
                    return;
                }
                item.dismiss();
                F.closeSoftKey((MActivityActionbar) context);
            }
        });
        mGridPasswordView.setPasswordType(PasswordType.TEXT);

        mEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                if (actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_ACTION_NEXT || actionId == EditorInfo.IME_ACTION_GO) {
                    if (!mEditText.getText().toString().equals(mModelUsreLogin.password)) {
                        Helper.toast("密码不正确", context);
                    } else {
                        item.dismiss();
                        F.closeSoftKey((MActivityActionbar) context);
                    }
                    return true;
                }
                return false;
            }
        });
    }

    public void set(Dialog item) {
        this.item = item;
    }


}