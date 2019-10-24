//
//  QunChengyuan
//
//  Created by DELL on 2017-04-25 08:46:39
//  Copyright (c) DELL All rights reserved.


/**

 */

package com.jinqu.standardNew.item;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jinqu.standardNew.R;
import com.jinqu.standardNew.model.ModelEmploee;
import com.mdx.framework.widget.MImageView;

import static com.jinqu.standardNew.F.setImage;


public class QunChengyuan extends BaseItem {
    public MImageView mMImageView;
    public TextView mTextView_name;
    public LinearLayout mLinearLayout_geren;
    public LinearLayout mLinearLayout_more;


    @SuppressLint("InflateParams")
    public static View getView(Context context, ViewGroup parent) {
        LayoutInflater flater = LayoutInflater.from(context);
        View convertView = flater.inflate(R.layout.item_qun_chengyuan, null);
        convertView.setTag(new QunChengyuan(convertView));
        return convertView;
    }

    public QunChengyuan(View view) {
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
        mTextView_name = (TextView) contentview.findViewById(R.id.mTextView_name);
        mLinearLayout_geren = (LinearLayout) findViewById(R.id.mLinearLayout_geren);
        mLinearLayout_more = (LinearLayout) findViewById(R.id.mLinearLayout_more);
        mMImageView.setCircle(true);
    }

    public void set(ModelEmploee.RowsBean item) {

        if (item.EmpID != 0) {
            if (!TextUtils.isEmpty(item.EmpHead)) {
                mMImageView.setObj(item.EmpHead);
            } else {
                setImage(mMImageView, item.EmpName.substring(0, 1));
            }
            mLinearLayout_more.setVisibility(View.GONE);
            mLinearLayout_geren.setVisibility(View.VISIBLE);
            mTextView_name.setText(item.EmpName);
        } else {
            mLinearLayout_more.setVisibility(View.VISIBLE);
            mLinearLayout_geren.setVisibility(View.GONE);
            mTextView_name.setText("更多");
        }
    }


}