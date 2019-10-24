//
//  ChoosePopSon
//
//  Created by DELL on 2018-06-20 11:52:22
//  Copyright (c) DELL All rights reserved.


/**

 */

package com.jinqu.standardNew.item;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jinqu.standardNew.R;

import static com.jinqu.standardNew.F.COLOR;


public class ChoosePopSon extends BaseItem {
    public TextView mTextView;


    @SuppressLint("InflateParams")
    public static View getView(Context context, ViewGroup parent) {
        LayoutInflater flater = LayoutInflater.from(context);
        View convertView = flater.inflate(R.layout.item_choose_pop_son, null);
        convertView.setTag(new ChoosePopSon(convertView));
        return convertView;
    }

    public ChoosePopSon(View view) {
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

    public void set(String item, boolean isChoose) {
        mTextView.setText(item);
        if (isChoose) {
            mTextView.setTextColor(Color.parseColor(COLOR));
        } else {
            mTextView.setTextColor(Color.parseColor("#000000"));
        }
    }


}