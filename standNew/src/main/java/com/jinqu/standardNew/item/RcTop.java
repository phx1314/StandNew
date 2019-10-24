//
//  RcTop
//
//  Created by DELL on 2018-05-08 11:17:04
//  Copyright (c) DELL All rights reserved.


/**

 */

package com.jinqu.standardNew.item;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ab.util.AbDateUtil;
import com.jinqu.standardNew.R;


public class RcTop extends BaseItem {
    public TextView mTextView_day;
    public LinearLayout mLinearLayout_k;
    public LinearLayout mLinearLayout_content;


    @SuppressLint("InflateParams")
    public static View getView(Context context, ViewGroup parent) {
        LayoutInflater flater = LayoutInflater.from(context);
        View convertView = flater.inflate(R.layout.item_rc_top, null);
        convertView.setTag(new RcTop(convertView));
        return convertView;
    }

    public RcTop(View view) {
        this.contentview = view;
        this.context = contentview.getContext();
        initView();
    }

    private void initView() {
        this.contentview.setTag(this);
        findVMethod();
    }

    private void findVMethod() {
        mTextView_day = (TextView) contentview.findViewById(R.id.mTextView_day);
        mLinearLayout_k = (LinearLayout) contentview.findViewById(R.id.mLinearLayout_k);
        mLinearLayout_content = (LinearLayout) findViewById(R.id.mLinearLayout_content);


    }

    public void set(int type) {
        if (type == 0) {
            mLinearLayout_content.setVisibility(View.GONE);
        } else {
            mLinearLayout_content.setVisibility(View.VISIBLE);
            if (type > 0) {
                mLinearLayout_k.setVisibility(View.GONE);
            } else {
                mLinearLayout_k.setVisibility(View.VISIBLE);
            }

        }
    }


    public void setTime(String time) {
        mTextView_day.setText(AbDateUtil.formatDateStr2Desc(time+" 00:00:00"));
    }
}