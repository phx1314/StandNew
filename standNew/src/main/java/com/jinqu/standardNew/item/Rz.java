//
//  Rz
//
//  Created by DELL on 2017-08-28 14:03:58
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
import android.widget.TextView;

import com.ab.util.AbDateUtil;
import com.jinqu.standardNew.R;
import com.jinqu.standardNew.model.ModelRzh;
import com.jinqu.standardNew.model.ModelUsreLogin;

import static com.ab.util.AbDateUtil.dateFormatYMDHMS;
import static com.jinqu.standardNew.F.mModelUsreLogin;
import static com.jinqu.standardNew.F.setImage;
import static com.jinqu.standardNew.util.UtilDate.changeTime;


public class Rz extends BaseItem {
    public TextView mTextView_title;
    public TextView mTextView_content1;
    public TextView mTextView_content4;
    public com.mdx.framework.widget.MImageView mMImageView;


    @SuppressLint("InflateParams")
    public static View getView(Context context, ViewGroup parent) {
        LayoutInflater flater = LayoutInflater.from(context);
        View convertView = flater.inflate(R.layout.item_rz, null);
        convertView.setTag(new Rz(convertView));
        return convertView;
    }

    public Rz(View view) {
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
        mTextView_content1 = (TextView) contentview.findViewById(R.id.mTextView_content1);
        mTextView_content4 = (TextView) contentview.findViewById(R.id.mTextView_content4);
        mMImageView = (com.mdx.framework.widget.MImageView) findViewById(R.id.mMImageView);
        mMImageView.setCircle(true);

    }

    public void set(ModelRzh.RowsBean item) {
        for (ModelUsreLogin.BaseEmployeeBean mRowsBean : mModelUsreLogin.BaseEmployee) {
            if (mRowsBean.EmpID == item.BaseLogEmpID) {
                if (!TextUtils.isEmpty(mRowsBean.EmpHead)) {
                    mMImageView.setObj(mRowsBean.EmpHead);
                } else {
                    setImage(mMImageView, item.EmpName.substring(0, 1));
                }
                break;
            }
        }
        mTextView_title.setText(item.EmpName);
        mTextView_content1.setText(AbDateUtil.formatDateStr2Desc(changeTime(item.BaseLogDate, dateFormatYMDHMS)) + "    " + item.BaseLogIP);
        mTextView_content4.setText(item.BaseLogRefHTML);
    }


}