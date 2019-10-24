//
//  Xw
//
//  Created by DELL on 2018-05-08 11:17:04
//  Copyright (c) DELL All rights reserved.


/**

 */

package com.jinqu.standardNew.item;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jinqu.standardNew.R;
import com.jinqu.standardNew.model.ModelNewsList;
import com.mdx.framework.commons.ParamsManager;


public class Xw extends BaseItem {
    public TextView mTextView_title;
    public TextView mTextView_time;
    public TextView mTextView_content;
    public com.mdx.framework.widget.MImageView mMImageView;


    @SuppressLint("InflateParams")
    public static View getView(Context context, ViewGroup parent) {
        LayoutInflater flater = LayoutInflater.from(context);
        View convertView = flater.inflate(R.layout.item_xw, null);
        convertView.setTag(new Xw(convertView));
        return convertView;
    }

    public Xw(View view) {
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
        mTextView_time = (TextView) contentview.findViewById(R.id.mTextView_time);
        mTextView_content = (TextView) contentview.findViewById(R.id.mTextView_content);
        mMImageView = (com.mdx.framework.widget.MImageView) findViewById(R.id.mMImageView);


    }

    public void set(ModelNewsList.RowsBean item) {
        try {
            mTextView_title.setText(TextUtils.isEmpty(item.NewsTitle) ? "" : item.NewsTitle);
            mTextView_time.setText(item.NewsDate + "");
            mTextView_content.setText(Html.fromHtml(TextUtils.isEmpty(item.NewsTypeName) ? "" : item.NewsTypeName));
            if (TextUtils.isEmpty(item.NewsImage)) {
                mMImageView.setVisibility(View.GONE);
            } else {
                mMImageView.setVisibility(View.VISIBLE);
                mMImageView.setObj(ParamsManager.get("image_star") + item.NewsImage);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }


}