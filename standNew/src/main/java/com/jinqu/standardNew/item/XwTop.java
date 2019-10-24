//
//  XwTop
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

import com.jinqu.standardNew.R;
import com.jinqu.standardNew.ada.AdaNewsTop;
import com.jinqu.standardNew.model.ModelNewsImges;


public class XwTop extends BaseItem {


    public com.framewidget.view.DfCirleCurr mDfCirleCurr;
    public LinearLayout mLinearLayout_g;

    @SuppressLint("InflateParams")
    public static View getView(Context context, ViewGroup parent) {
        LayoutInflater flater = LayoutInflater.from(context);
        View convertView = flater.inflate(R.layout.item_xw_top, null);
        convertView.setTag(new XwTop(convertView));
        return convertView;
    }

    public XwTop(View view) {
        this.contentview = view;
        this.context = contentview.getContext();
        initView();
    }

    private void initView() {
        this.contentview.setTag(this);
        findVMethod();
    }

    private void findVMethod() {


        mDfCirleCurr = (com.framewidget.view.DfCirleCurr) findViewById(R.id.mDfCirleCurr);
        mLinearLayout_g = (LinearLayout) findViewById(R.id.mLinearLayout_g);


    }

    public void set(ModelNewsImges item) {
        mDfCirleCurr.setAdapter(new AdaNewsTop(context, item.rows));
    }

    public void set(String id) {
        if (id.equals("0")) {
            mLinearLayout_g.setVisibility(View.VISIBLE);
        } else {
            mLinearLayout_g.setVisibility(View.GONE);
        }
    }


}