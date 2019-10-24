//
//  RcMonth
//
//  Created by DELL on 2018-08-01 15:21:15
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
import com.jinqu.standardNew.model.ModelBell;
import com.jinqu.standardNew.model.ModelRc;
import com.jinqu.standardNew.util.UtilDate;


public class RcMonth extends BaseItem {
    public TextView mTextView_title;
    public TextView mTextView_content;


    @SuppressLint("InflateParams")
    public static View getView(Context context, ViewGroup parent) {
        LayoutInflater flater = LayoutInflater.from(context);
        View convertView = flater.inflate(R.layout.item_rc_month, null);
        convertView.setTag(new RcMonth(convertView));
        return convertView;
    }

    public RcMonth(View view) {
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
        mTextView_content = (TextView) contentview.findViewById(R.id.mTextView_content);


    }

    public void set(ModelRc item) {
        mTextView_title.setText(item.text);
        mTextView_content.setText(item.start_date + "--" + item.end_date);
    }

    public void set(ModelBell item) {
        mTextView_title.setText(item.Content);
        mTextView_content.setText(UtilDate.formatMatchDate(item.StartTime, "yyyy-MM-dd HH:mm:ss") + "--" + UtilDate.formatMatchDate(item.EndTime, "yyyy-MM-dd HH:mm:ss"));
    }

}