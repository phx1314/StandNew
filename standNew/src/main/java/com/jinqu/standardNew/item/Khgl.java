//
//  Khgl
//
//  Created by DELL on 2018-08-24 09:14:07
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
import com.jinqu.standardNew.model.ModelKhdw;


public class Khgl extends BaseItem {
    public TextView mTextView_title;
    public TextView mTextView_title2;
    public TextView mTextView_content1;
    public TextView mTextView_content2;


    @SuppressLint("InflateParams")
    public static View getView(Context context, ViewGroup parent) {
        LayoutInflater flater = LayoutInflater.from(context);
        View convertView = flater.inflate(R.layout.item_khgl, null);
        convertView.setTag(new Khgl(convertView));
        return convertView;
    }

    public Khgl(View view) {
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
        mTextView_title2 = (TextView) contentview.findViewById(R.id.mTextView_title2);
        mTextView_content1 = (TextView) contentview.findViewById(R.id.mTextView_content1);
        mTextView_content2 = (TextView) contentview.findViewById(R.id.mTextView_content2);


    }

    public void set(ModelKhdw.RowsBean item) {
        if (item.MenuNameEng.equals("CustomerInfo")) {//客户单位信息
            mTextView_title.setText("客户名称:" + item.CustName + "     " + "客户类别:" + item.CustTypeName);
            mTextView_title2.setText("办公地址:" + item.CustAddress);
            mTextView_content1.setText("客户地区:" + item.CustRegion);
            mTextView_content2.setText("业务建立时间:" + item.CustDate);
        } else if (item.MenuNameEng.equals("CustomerSubInfo")) {//外委单位信息
            mTextView_title.setText("外协客户名称:" + item.CustName + "     " + "外协客户类别:" + item.TypeName);
            mTextView_title2.setText("注册地址:" + item.CustAddress);
            mTextView_content1.setText("企业资质等级:" + item.CustQualiGradeName);
            mTextView_content2.setVisibility(View.GONE);
        } else if (item.MenuNameEng.equals("OtherCustomerInfo")) {//其他单位信息
            mTextView_title.setText("其它单位名称:" + item.CustName + "     " + "客户类别:" + item.CustTypeName);
            mTextView_title2.setText("办公地址:" + item.CustAddress);
            mTextView_content1.setText("客户地区:" + item.CustRegion);
            mTextView_content2.setText("客户邮编:" + item.CustPost);
        }
    }

}