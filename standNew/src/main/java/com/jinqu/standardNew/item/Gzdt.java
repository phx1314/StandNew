//
//  Gzdt
//
//  Created by DELL on 2018-05-17 15:37:26
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

import com.jinqu.standardNew.R;
import com.jinqu.standardNew.model.ModelUsreLogin;
import com.jinqu.standardNew.model.ModelgzdtList;
import com.mdx.framework.widget.MImageView;

import static com.jinqu.standardNew.F.mModelUsreLogin;
import static com.jinqu.standardNew.F.setImage;
import static com.jinqu.standardNew.util.UtilDate.changeTime;


public class Gzdt extends BaseItem {
    public TextView mTextView_title;
    public TextView mTextView_address;
    public TextView mTextView_time;
    public MImageView mMImageView;
    public TextView mTextView_user;
    public TextView mTextView_pinglun;
    public TextView mTextView_chakan;


    @SuppressLint("InflateParams")
    public static View getView(Context context, ViewGroup parent) {
        LayoutInflater flater = LayoutInflater.from(context);
        View convertView = flater.inflate(R.layout.item_gzdt, null);
        convertView.setTag(new Gzdt(convertView));
        return convertView;
    }

    public Gzdt(View view) {
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
        mTextView_address = (TextView) contentview.findViewById(R.id.mTextView_address);
        mTextView_time = (TextView) contentview.findViewById(R.id.mTextView_time);
        mMImageView = (MImageView) contentview.findViewById(R.id.mMImageView);
        mTextView_user = (TextView) contentview.findViewById(R.id.mTextView_user);
        mTextView_pinglun = (TextView) contentview.findViewById(R.id.mTextView_pinglun);
        mTextView_chakan = (TextView) contentview.findViewById(R.id.mTextView_chakan);
        mMImageView.setCircle(true);

    }

    public void set(ModelgzdtList.RowsBean item) {
        mTextView_title.setText(item.TalkTitle);
        mTextView_user.setText(item.CreatorEmpName);
        mTextView_time.setText(changeTime(item.CreationTime));
        mTextView_pinglun.setText(item.HY.split("/")[0]);
        mTextView_chakan.setText(item.HY.split("/")[1]);


        for (ModelUsreLogin.BaseEmployeeBean mRowsBean : mModelUsreLogin.BaseEmployee) {
            if (mRowsBean.EmpID == item.CreatorEmpId) {
                if (!TextUtils.isEmpty(mRowsBean.EmpHead)) {
                    mMImageView.setObj(mRowsBean.EmpHead);
                } else {
                    setImage(mMImageView, item.CreatorEmpName.substring(0, 1));
                }
                break;
            }
        }

    }


}