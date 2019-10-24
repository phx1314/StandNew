//
//  FrgTz
//
//  Created by DELL on 2018-07-16 10:23:24
//  Copyright (c) DELL All rights reserved.


/**

 */

package com.jinqu.standardNew.frg;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.ToggleButton;

import com.jinqu.standardNew.R;
import com.jinqu.standardNew.util.OpenAutoStartUtil;
import com.mdx.framework.widget.ActionBar;

import cn.jpush.android.api.JPushInterface;

import static com.jinqu.standardNew.F.getJson;
import static com.jinqu.standardNew.F.saveJson;


public class FrgTz extends BaseFrg {

    public LinearLayout mLinearLayout_1;
    public ToggleButton mToggleButton1;
    public LinearLayout mLinearLayout_2;
    public ToggleButton mToggleButton2;
    public LinearLayout mLinearLayout_3;
    public ToggleButton mToggleButton3;
    public LinearLayout mLinearLayout_zqd;
    public LinearLayout mLinearLayout_tz;


    @Override
    protected void create(Bundle savedInstanceState) {
        setContentView(R.layout.frg_tz);
        initView();
        loaddata();
    }

    private void initView() {
        findVMethod();
    }

    private void findVMethod() {
        mLinearLayout_1 = (LinearLayout) findViewById(R.id.mLinearLayout_1);
        mToggleButton1 = (ToggleButton) findViewById(R.id.mToggleButton1);
        mLinearLayout_2 = (LinearLayout) findViewById(R.id.mLinearLayout_2);
        mToggleButton2 = (ToggleButton) findViewById(R.id.mToggleButton2);
        mLinearLayout_3 = (LinearLayout) findViewById(R.id.mLinearLayout_3);
        mToggleButton3 = (ToggleButton) findViewById(R.id.mToggleButton3);
        mLinearLayout_zqd = (LinearLayout) findViewById(R.id.mLinearLayout_zqd);
        mLinearLayout_tz = (LinearLayout) findViewById(R.id.mLinearLayout_tz);

        mToggleButton1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    JPushInterface.resumePush(getActivity());
                } else {
                    JPushInterface.stopPush(getActivity());
                }
                saveJson("push", isChecked + "");
            }
        });
        mLinearLayout_zqd.setOnClickListener(this);
        mLinearLayout_tz.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        if (v.getId() == R.id.mLinearLayout_zqd) {
            OpenAutoStartUtil.jumpStartInterface(getContext());
        } else if (v.getId() == R.id.mLinearLayout_tz) {
            OpenAutoStartUtil.jumpStartTz(getContext());
        }
    }

    public void loaddata() {
        if (!TextUtils.isEmpty(getJson("push"))) {
            mToggleButton1.setChecked(Boolean.valueOf(getJson("push")));
            if (Boolean.valueOf(getJson("push"))) {
                JPushInterface.resumePush(getActivity());
            } else {
                JPushInterface.stopPush(getActivity());
            }
        }
    }


    @Override
    public void setActionBar(ActionBar actionBar, Context context) {
        super.setActionBar(actionBar, context);
        mHeadlayout.setTitle("消息通知");
    }
}