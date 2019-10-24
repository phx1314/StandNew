//
//  FrgZhyaq
//
//  Created by DELL on 2018-07-16 09:55:30
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
import android.widget.TextView;
import android.widget.ToggleButton;

import com.framewidget.frg.FrgPubBianJi;
import com.jinqu.standardNew.F;
import com.jinqu.standardNew.R;
import com.mdx.framework.activity.NoTitleAct;
import com.mdx.framework.activity.TitleAct;
import com.mdx.framework.utility.Helper;
import com.mdx.framework.widget.ActionBar;

import static com.jinqu.standardNew.F.mModelUsreInfo;


public class FrgZhyaq extends BaseFrg {

    public LinearLayout mLinearLayout_1;
    public TextView mTextView1;
    public LinearLayout mLinearLayout_2;
    public TextView mTextView2;
    public LinearLayout mLinearLayout_3;
    public LinearLayout mLinearLayout_4;
    public LinearLayout mLinearLayout_5;
    public TextView mTextView_5;
    public ToggleButton mToggleButton1;
    public ToggleButton mToggleButton2;


    @Override
    protected void create(Bundle savedInstanceState) {
        setContentView(R.layout.frg_zhyaq);
        initView();
        loaddata();
    }

    private void initView() {
        findVMethod();
    }

    private void findVMethod() {
        mLinearLayout_1 = (LinearLayout) findViewById(R.id.mLinearLayout_1);
        mTextView1 = (TextView) findViewById(R.id.mTextView1);
        mLinearLayout_2 = (LinearLayout) findViewById(R.id.mLinearLayout_2);
        mTextView2 = (TextView) findViewById(R.id.mTextView2);
        mLinearLayout_3 = (LinearLayout) findViewById(R.id.mLinearLayout_3);
        mLinearLayout_4 = (LinearLayout) findViewById(R.id.mLinearLayout_4);
        mLinearLayout_5 = (LinearLayout) findViewById(R.id.mLinearLayout_5);
        mTextView_5 = (TextView) findViewById(R.id.mTextView_5);
        mToggleButton1 = (ToggleButton) findViewById(R.id.mToggleButton1);
        mToggleButton2 = (ToggleButton) findViewById(R.id.mToggleButton2);

        mLinearLayout_1.setOnClickListener(Helper.delayClickLitener(this));
        mLinearLayout_2.setOnClickListener(Helper.delayClickLitener(this));

        mToggleButton1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                F.saveJson("toggle1", isChecked + "");
            }
        });
        mToggleButton2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                F.saveJson("toggle2", isChecked + "");
            }
        });
    }

    public void loaddata() {
        mTextView1.setText(mModelUsreInfo.model.EmpName);
        mTextView2.setText(mModelUsreInfo.model.EmpTel);

        mToggleButton1.setChecked(F.getJson("toggle1").equals("true"));
        mToggleButton2.setChecked(F.getJson("toggle2").equals("true"));
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        if (v.getId() == R.id.mLinearLayout_1) {
            Helper.startActivity(getContext(), FrgGrzx.class, NoTitleAct.class, "id", mModelUsreInfo.model.EmpID + "");
        } else if (v.getId() == R.id.mLinearLayout_1) {
            if (!TextUtils.isEmpty(mTextView2.getText().toString())) {
                return;
            }
            Helper.startActivity(getContext(), FrgPubBianJi.class,
                    TitleAct.class, "from", "FrgZhyaq", "EDT", 101,
                    "data", mTextView2.getText().toString(), "max", 11, "hint", "手机号");
        }
    }

    @Override
    public void setActionBar(ActionBar actionBar, Context context) {
        super.setActionBar(actionBar, context);
        mHeadlayout.setTitle("账户与安全");
    }
}