//
//  Bottom
//
//  Created by DELL on 2018-10-29 09:44:40
//  Copyright (c) DELL All rights reserved.


/**

 */

package com.framewidget.item;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.framewidget.R;


public class Bottom extends BaseItem {
    public Dialog item;
    public TextView mTextView_1;
    public TextView mTextView_2;

    @SuppressLint("InflateParams")
    public static View getView(Context context, ViewGroup parent) {
        LayoutInflater flater = LayoutInflater.from(context);
        View convertView = flater.inflate(R.layout.item_bottom, null);
        convertView.setTag(new Bottom(convertView));
        return convertView;
    }

    public Bottom(View view) {
        this.contentview = view;
        this.context = contentview.getContext();
        initView();
    }

    private void initView() {
        this.contentview.setTag(this);
        findVMethod();
    }

    private void findVMethod() {
        mTextView_1 = (TextView) findViewById(R.id.mTextView_1);
        mTextView_2 = (TextView) findViewById(R.id.mTextView_2);

        mTextView_1.setOnClickListener(com.mdx.framework.utility.Helper.delayClickLitener(this));
        mTextView_2.setOnClickListener(com.mdx.framework.utility.Helper.delayClickLitener(this));

    }

    public void set(Dialog item) {
        this.item = item;
    }

    @Override
    public void onClick(android.view.View v) {

        if (R.id.mTextView_1 == v.getId()) {

        } else if (R.id.mTextView_2 == v.getId()) {
            item.dismiss();
        }
    }

}