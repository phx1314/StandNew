//
//  FrgSjbd
//
//  Created by DELL on 2018-10-12 08:35:13
//  Copyright (c) DELL All rights reserved.


/**

 */

package com.jinqu.standardNew.frg;

import android.content.ClipboardManager;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.jinqu.standardNew.R;
import com.mdx.framework.utility.Helper;
import com.mdx.framework.widget.ActionBar;

import static com.jinqu.standardNew.F.mModelUsreLogin;


public class FrgSjbd extends BaseFrg {

    public TextView mTextView_sbh;
    public TextView mTextView1;
    public TextView mTextView2;


    @Override
    protected void create(Bundle savedInstanceState) {
        setContentView(R.layout.frg_sjbd);
        initView();
        loaddata();
    }

    private void initView() {
        findVMethod();
    }

    private void findVMethod() {
        mTextView_sbh = (TextView) findViewById(R.id.mTextView_sbh);
        mTextView1 = (TextView) findViewById(R.id.mTextView1);
        mTextView2 = (TextView) findViewById(R.id.mTextView2);


    }

    public void loaddata() {
        mTextView_sbh.setText(Build.SERIAL);
        mTextView_sbh.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                ClipboardManager cmb = (ClipboardManager) getContext().getSystemService(Context.CLIPBOARD_SERVICE);
                cmb.setText(mTextView_sbh.getText().toString().trim()); //将内容放入粘贴管理器,在别的地方长按选择"粘贴"即可
                Helper.toast("已经复制到剪贴板", getContext());
                return true;
            }
        });
        if (TextUtils.isEmpty(mModelUsreLogin.UserInfo.EmpMEID)) {
            mTextView1.setText("该账户未绑定手机");
            mTextView2.setText("请长安设备号复制或截屏发给管理员");
        } else {
            mTextView1.setText("该账户未在指定设备登录");
            mTextView2.setText("如需切换设备请长按设备号复制或截屏发给管理员");
        }
    }

    @Override
    public void setActionBar(ActionBar actionBar, Context context) {
        super.setActionBar(actionBar, context);
        mHeadlayout.setTitle("提示");
    }
}