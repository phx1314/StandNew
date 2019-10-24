//
//  GzdtF
//
//  Created by DELL on 2018-05-17 15:54:34
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
import com.jinqu.standardNew.model.ModelxmdtList;

import static com.jinqu.standardNew.util.UtilDate.changeTime;


public class GzdtF extends BaseItem {
    public TextView mTextView_name;
    public TextView mTextView_user;
    public TextView mTextView_time_1;
    public TextView mTextView_time_2;
    public TextView mTextView_pl;
    public TextView mTextView_renyuan;

    public ModelxmdtList.RowsBean item;

    @SuppressLint("InflateParams")
    public static View getView(Context context, ViewGroup parent) {
        LayoutInflater flater = LayoutInflater.from(context);
        View convertView = flater.inflate(R.layout.item_gzdt_f, null);
        convertView.setTag(new GzdtF(convertView));
        return convertView;
    }

    public GzdtF(View view) {
        this.contentview = view;
        this.context = contentview.getContext();
        initView();
    }

    private void initView() {
        this.contentview.setTag(this);
        findVMethod();
    }

    private void findVMethod() {
        mTextView_name = (TextView) contentview.findViewById(R.id.mTextView_name);
        mTextView_user = (TextView) contentview.findViewById(R.id.mTextView_user);
        mTextView_time_1 = (TextView) contentview.findViewById(R.id.mTextView_time_1);
        mTextView_time_2 = (TextView) contentview.findViewById(R.id.mTextView_time_2);
        mTextView_pl = (TextView) contentview.findViewById(R.id.mTextView_pl);
        mTextView_renyuan = (TextView) contentview.findViewById(R.id.mTextView_renyuan);
    }

    public void set(ModelxmdtList.RowsBean item) {
        this.item = item;
        mTextView_name.setText("项目名称：" + item.ProjName);
        mTextView_user.setText("项目负责人：" + item.ProjPhaseEmpName);
        mTextView_time_1.setText(changeTime(item.DatePlanStart));
        mTextView_time_2.setText(changeTime(item.DatePlanFinish));
        mTextView_pl.setText(item.TaskTotalCount + "");
        mTextView_renyuan.setText(item.row_number + "");
    }


}