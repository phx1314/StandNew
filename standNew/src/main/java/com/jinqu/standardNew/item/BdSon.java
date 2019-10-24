//
//  BdSon
//
//  Created by DELL on 2018-07-25 09:16:52
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
import com.jinqu.standardNew.model.ModelBd;


public class BdSon extends BaseItem {
    public TextView mTextView;
    public TextView mTextView_v;


    @SuppressLint("InflateParams")
    public static View getView(Context context, ViewGroup parent) {
        LayoutInflater flater = LayoutInflater.from(context);
        View convertView = flater.inflate(R.layout.item_bd_son, null);
        convertView.setTag(new BdSon(convertView));
        return convertView;
    }

    public BdSon(View view) {
        this.contentview = view;
        this.context = contentview.getContext();
        initView();
    }

    private void initView() {
        this.contentview.setTag(this);
        findVMethod();
    }

    private void findVMethod() {
        mTextView = (TextView) contentview.findViewById(R.id.mTextView);
        mTextView_v = (TextView) findViewById(R.id.mTextView_v);


    }

    public void set(ModelBd.FlowSummary item) {
        mTextView.setText(item.Key + "：");
        mTextView_v.setText(item.Value);
    }


}