//
//  SousuoTop
//
//  Created by DELL on 2018-06-29 11:12:07
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
import com.mdx.framework.Frame;

import static com.jinqu.standardNew.R.id.mRadioButton3;
import static com.jinqu.standardNew.frg.BaseFrg.setBgClickClor;


public class SousuoTop extends BaseItem {
    public TextView mTextView;


    @SuppressLint("InflateParams")
    public static View getView(Context context, ViewGroup parent) {
        LayoutInflater flater = LayoutInflater.from(context);
        View convertView = flater.inflate(R.layout.item_sousuo_top, null);
        convertView.setTag(new SousuoTop(convertView));
        return convertView;
    }

    public SousuoTop(View view) {
        this.contentview = view;
        this.context = contentview.getContext();
        initView();
    }

    private void initView() {
        this.contentview.setTag(this);
        findVMethod();
    }

    private void findVMethod() {
        mTextView = (TextView) contentview.findViewById(mRadioButton3);
        mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Frame.HANDLES.sentAll("FrgSousuo", 0, mTextView.getText().toString());
            }
        });

    }

    public void set(String item) {
        mTextView.setText(item);
    }


}