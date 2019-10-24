//
//  Sousuo
//
//  Created by DELL on 2018-06-27 09:37:50
//  Copyright (c) DELL All rights reserved.


/**

 */

package com.jinqu.standardNew.item;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jinqu.standardNew.R;
import com.jinqu.standardNew.model.ModelMenuConfig;


public class Sousuo extends BaseItem {
    public TextView mTextView1;
    public TextView mTextView2;


    @SuppressLint("InflateParams")
    public static View getView(Context context, ViewGroup parent) {
        LayoutInflater flater = LayoutInflater.from(context);
        View convertView = flater.inflate(R.layout.item_sousuo, null);
        convertView.setTag(new Sousuo(convertView));
        return convertView;
    }

    public Sousuo(View view) {
        this.contentview = view;
        this.context = contentview.getContext();
        initView();
    }

    private void initView() {
        this.contentview.setTag(this);
        findVMethod();
    }

    private void findVMethod() {
        mTextView1 = (TextView) contentview.findViewById(R.id.mTextView1);
        mTextView2 = (TextView) contentview.findViewById(R.id.mTextView2);


    }

    public void set(ModelMenuConfig.SearchBean item) {
        mTextView1.setText(item.text);
        mTextView2.setText(item.value);
    }


}