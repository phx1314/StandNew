//
//  ShpjnduList
//
//  Created by DELL on 2017-05-09 15:58:26
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
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ab.util.AbDateUtil;
import com.jinqu.standardNew.F;
import com.jinqu.standardNew.R;
import com.jinqu.standardNew.frg.FrgGrzx;
import com.jinqu.standardNew.model.ModelShPjdList;
import com.jinqu.standardNew.model.ModelUsreLogin;
import com.mdx.framework.activity.NoTitleAct;
import com.mdx.framework.utility.Helper;
import com.mdx.framework.widget.MImageView;


public class ShpjnduList extends BaseItem {
    public LinearLayout mLinearLayout;
    public TextView mTextView_yj;
    public MImageView mImageView;
    public TextView mTextView_fq;
    public TextView mTextView_1;
    public TextView mTextView_2;
    public TextView mTextView_3;
    public TextView mTextView_4;
    public ImageView mImageView_1;

    public ModelShPjdList.RowsBean item;

    @SuppressLint("InflateParams")
    public static View getView(Context context, ViewGroup parent) {
        LayoutInflater flater = LayoutInflater.from(context);
        View convertView = flater.inflate(R.layout.item_shpjndu_list, null);
        convertView.setTag(new ShpjnduList(convertView));
        return convertView;
    }

    public ShpjnduList(View view) {
        this.contentview = view;
        this.context = contentview.getContext();
        initView();
    }

    private void initView() {
        this.contentview.setTag(this);
        findVMethod();
    }

    private void findVMethod() {
        mLinearLayout = (LinearLayout) contentview.findViewById(R.id.mLinearLayout);
        mTextView_yj = (TextView) contentview.findViewById(R.id.mTextView_yj);
        mImageView = (MImageView) contentview.findViewById(R.id.mImageView);
        mTextView_fq = (TextView) contentview.findViewById(R.id.mTextView_fq);
        mTextView_1 = (TextView) contentview.findViewById(R.id.mTextView_1);
        mTextView_2 = (TextView) contentview.findViewById(R.id.mTextView_2);
        mTextView_3 = (TextView) contentview.findViewById(R.id.mTextView_3);
        mTextView_4 = (TextView) contentview.findViewById(R.id.mTextView_4);
        mImageView_1 = (ImageView) findViewById(R.id.mImageView_1);
        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Helper.startActivity(context, FrgGrzx.class, NoTitleAct.class, "id", item.ExeEmpId + "");
            }
        });
        mTextView_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Helper.startActivity(context, FrgGrzx.class, NoTitleAct.class, "id", item.ExeEmpId + "");
            }
        });
        mTextView_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Helper.startActivity(context, FrgGrzx.class, NoTitleAct.class, "id", item.ExeEmpId + "");
            }
        });
        mTextView_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Helper.startActivity(context, FrgGrzx.class, NoTitleAct.class, "id", item.FlowNodeID + "");
            }
        });
    }

    public void set(ModelShPjdList.RowsBean item, int position) {
        this.item = item;
        mTextView_1.setText(item.ExeEmpName + " " + item.ActionName);
        mTextView_yj.setText(AbDateUtil.getStringByFormat(item.NewExeActionDate, "yyyy-MM-dd"));
        mTextView_2.setText(item.FlowNodeName);
        mTextView_3.setText(AbDateUtil.getStringByFormat(item.NewExeActionDate, "HH:mm"));
        mTextView_4.setText(item.ExeNote);
        if (position == 0) {
            mTextView_fq.setVisibility(View.VISIBLE);
            mImageView_1.setVisibility(View.VISIBLE);
        } else {
            mTextView_fq.setVisibility(View.GONE);
            mImageView_1.setVisibility(View.GONE);
        }
        mImageView.setObj("0");
        for (ModelUsreLogin.BaseEmployeeBean mRowsBean : F.mModelUsreLogin.BaseEmployee) {
            if (mRowsBean.EmpID == item.ExeEmpId) {
                mImageView.setObj(mRowsBean.EmpHead);
            }
        }
        mImageView.setCircle(true);
    }


}