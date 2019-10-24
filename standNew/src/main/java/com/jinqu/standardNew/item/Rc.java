//
//  Rc
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
import android.widget.ImageView;
import android.widget.TextView;

import com.jinqu.standardNew.R;
import com.jinqu.standardNew.model.ModelRc;


public class Rc extends BaseItem {
    public TextView mTextView_currenttime;
    public TextView mTextView_endtime;
    public ImageView mImageViewtop;
    public ImageView mImageViewcenter;
    public ImageView mImageViewbottom;
    public TextView mTextView_title;


    @SuppressLint("InflateParams")
    public static View getView(Context context, ViewGroup parent) {
        LayoutInflater flater = LayoutInflater.from(context);
        View convertView = flater.inflate(R.layout.item_rc, null);
        convertView.setTag(new Rc(convertView));
        return convertView;
    }

    public Rc(View view) {
        this.contentview = view;
        this.context = contentview.getContext();
        initView();
    }

    private void initView() {
        this.contentview.setTag(this);
        findVMethod();
    }

    private void findVMethod() {
        mTextView_currenttime = (TextView) contentview.findViewById(R.id.mTextView_currenttime);
        mTextView_endtime = (TextView) contentview.findViewById(R.id.mTextView_endtime);
        mImageViewtop = (ImageView) contentview.findViewById(R.id.mImageViewtop);
        mImageViewcenter = (ImageView) contentview.findViewById(R.id.mImageViewcenter);
        mImageViewbottom = (ImageView) contentview.findViewById(R.id.mImageViewbottom);
        mTextView_title = (TextView) contentview.findViewById(R.id.mTextView_title);


    }

    public void set(ModelRc item) {
        mTextView_currenttime.setText(item.start_date.split(" ")[1]);
        mTextView_endtime.setText(item.end_date.split(" ")[1]);
        mTextView_title.setText(item.text);
    }


}