//
//  XxTop
//
//  Created by DELL on 2018-07-23 09:01:24
//  Copyright (c) DELL All rights reserved.


/**

 */

package com.jinqu.standardNew.item;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jinqu.standardNew.R;
import com.jinqu.standardNew.frg.FrgGg;
import com.jinqu.standardNew.frg.FrgQzList;
import com.jinqu.standardNew.frg.FrgXtxx;
import com.mdx.framework.activity.TitleAct;
import com.mdx.framework.utility.Helper;
import com.mdx.framework.widget.MImageView;

import static com.jinqu.standardNew.F.font;


public class XxTop extends BaseItem {
    public MImageView mMImageView;
    public TextView mTextView_user;
    public TextView mTextView_pinglun;
    public TextView mTextView_chakan;
    public TextView mTextView_time;
    public TextView mTextView_title;
    public TextView mTextView_address;
    public TextView mImageView1;
    public TextView mTextView_time1;
    public TextView mTextView_content1;
    public TextView mTextView_count1;
    public LinearLayout mLinearLayout1;
    public TextView mImageView2;
    public TextView mTextView_time2;
    public TextView mTextView_content2;
    public TextView mTextView_count2;
    public LinearLayout mLinearLayout2;
    public TextView mImageView3;
    public TextView mTextView_content3;
    public TextView mTextView_count3;
    public LinearLayout mLinearLayout3;


    @SuppressLint("InflateParams")
    public static View getView(Context context, ViewGroup parent) {
        LayoutInflater flater = LayoutInflater.from(context);
        View convertView = flater.inflate(R.layout.item_xx_top, null);
        convertView.setTag(new XxTop(convertView));
        return convertView;
    }

    public XxTop(View view) {
        this.contentview = view;
        this.context = contentview.getContext();
        initView();
    }

    private void initView() {
        this.contentview.setTag(this);
        findVMethod();
    }

    private void findVMethod() {
        mMImageView = (MImageView) contentview.findViewById(R.id.mMImageView);
        mTextView_user = (TextView) contentview.findViewById(R.id.mTextView_user);
        mTextView_pinglun = (TextView) contentview.findViewById(R.id.mTextView_pinglun);
        mTextView_chakan = (TextView) contentview.findViewById(R.id.mTextView_chakan);
        mTextView_time = (TextView) contentview.findViewById(R.id.mTextView_time);
        mTextView_title = (TextView) contentview.findViewById(R.id.mTextView_title);
        mTextView_address = (TextView) contentview.findViewById(R.id.mTextView_address);
        mImageView1 = (TextView) findViewById(R.id.mImageView1);
        mTextView_time1 = (TextView) findViewById(R.id.mTextView_time1);
        mTextView_content1 = (TextView) findViewById(R.id.mTextView_content1);
        mTextView_count1 = (TextView) findViewById(R.id.mTextView_count1);
        mLinearLayout1 = (LinearLayout) findViewById(R.id.mLinearLayout1);
        mImageView2 = (TextView) findViewById(R.id.mImageView2);
        mTextView_time2 = (TextView) findViewById(R.id.mTextView_time2);
        mTextView_content2 = (TextView) findViewById(R.id.mTextView_content2);
        mTextView_count2 = (TextView) findViewById(R.id.mTextView_count2);
        mLinearLayout2 = (LinearLayout) findViewById(R.id.mLinearLayout2);
        mImageView3 = (TextView) findViewById(R.id.mImageView3);
        mTextView_content3 = (TextView) findViewById(R.id.mTextView_content3);
        mTextView_count3 = (TextView) findViewById(R.id.mTextView_count3);
        mLinearLayout3 = (LinearLayout) findViewById(R.id.mLinearLayout3);

        mLinearLayout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Helper.startActivity(context, FrgGg.class, TitleAct.class);
            }
        });
        mLinearLayout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Helper.startActivity(context, FrgXtxx.class, TitleAct.class);
            }
        });
        mLinearLayout3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Helper.startActivity(context, FrgQzList.class, TitleAct.class);
            }
        });
        ((GradientDrawable) mImageView1.getBackground()).setColor(Color.parseColor("#f1ac18"));
        ((GradientDrawable) mImageView2.getBackground()).setColor(Color.parseColor("#e71f19"));
        ((GradientDrawable) mImageView3.getBackground()).setColor(Color.parseColor("#2567b2"));
        mImageView1.setTypeface(font);
        mImageView2.setTypeface(font);
        mImageView3.setTypeface(font);
    }

    public void set(String item) {

    }


}