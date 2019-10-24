//
//  Gz
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
import android.widget.TextView;

import com.framewidget.view.MGridView;
import com.jinqu.standardNew.R;
import com.jinqu.standardNew.ada.AdaSon;
import com.jinqu.standardNew.model.ModelGzList;


public class Gz extends BaseItem {
    public TextView mTextView_title;
    public MGridView mMGridView;


    @SuppressLint("InflateParams")
    public static View getView(Context context, ViewGroup parent) {
        LayoutInflater flater = LayoutInflater.from(context);
        View convertView = flater.inflate(R.layout.item_gz, null);
        convertView.setTag(new Gz(convertView));
        return convertView;
    }

    public Gz(View view) {
        this.contentview = view;
        this.context = contentview.getContext();
        initView();
    }

    private void initView() {
        this.contentview.setTag(this);
        findVMethod();
    }

    private void findVMethod() {
        mTextView_title = (TextView) contentview.findViewById(R.id.mTextView_title);
        mMGridView = (MGridView) contentview.findViewById(R.id.mMGridView);


    }

    public void set(ModelGzList.RowsBean item) {
        mTextView_title.setText(item.text);
        if (item.children != null)
            mMGridView.setAdapter(new AdaSon(context, item.children, 0));
    }


}