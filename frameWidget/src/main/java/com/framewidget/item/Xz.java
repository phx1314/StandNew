//
//  Xz
//
//  Created by DELL on 2018-07-18 15:12:13
//  Copyright (c) DELL All rights reserved.


/**

 */

package com.framewidget.item;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.framewidget.R;
import com.framewidget.model.ModelDx;


public class Xz extends BaseItem {
    public TextView mTextView;


    @SuppressLint("InflateParams")
    public static View getView(Context context, ViewGroup parent) {
        LayoutInflater flater = LayoutInflater.from(context);
        View convertView = flater.inflate(R.layout.item_xz, null);
        convertView.setTag(new Xz(convertView));
        return convertView;
    }

    public Xz(View view) {
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


    }

    public void set(ModelDx item) {
        mTextView.setText(item.string);
        if (item.isChecked) {
            mTextView.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_check_black_24dp, 0);
        } else {
            mTextView.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
        }
    }


}