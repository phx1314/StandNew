//
//  RcAll
//
//  Created by DELL on 2018-08-07 09:04:31
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

import com.ab.util.AbDateUtil;
import com.jinqu.standardNew.R;
import com.jinqu.standardNew.model.ModelRcAll;

import java.util.Date;
import java.util.List;

import static com.jinqu.standardNew.frg.FrgRc.df;


public class RcAll extends BaseItem {
    public TextView mTextView_year;
    public TextView mTextView_day;
    public TextView mTextView_title;
    public TextView mTextView_week;
    public TextView mTextView_content;
    public int position;
    public ModelRcAll.RowsBean item;
    public List<ModelRcAll.RowsBean> list;

    @SuppressLint("InflateParams")
    public static View getView(Context context, ViewGroup parent) {
        LayoutInflater flater = LayoutInflater.from(context);
        View convertView = flater.inflate(R.layout.item_rc_all, null);
        convertView.setTag(new RcAll(convertView));
        return convertView;
    }

    public RcAll(View view) {
        this.contentview = view;
        this.context = contentview.getContext();
        initView();
    }

    private void initView() {
        this.contentview.setTag(this);
        findVMethod();
    }

    private void findVMethod() {
        mTextView_year = (TextView) contentview.findViewById(R.id.mTextView_year);
        mTextView_day = (TextView) contentview.findViewById(R.id.mTextView_day);
        mTextView_title = (TextView) contentview.findViewById(R.id.mTextView_title);
        mTextView_week = (TextView) contentview.findViewById(R.id.mTextView_week);
        mTextView_content = (TextView) contentview.findViewById(R.id.mTextView_content);


    }

    public void set(ModelRcAll.RowsBean item, int position, List<ModelRcAll.RowsBean> list) {
        this.item = item;
        this.position = position;
        this.list = list;
        mTextView_year.setVisibility(position == getFristMPosition() ? View.VISIBLE : View.GONE);
        mTextView_year.setText(AbDateUtil.getStringByFormat(AbDateUtil.getDateByFormat(item.StartTime, df), "yyyy年MM月"));

        mTextView_title.setText(item.Content);
        mTextView_day.setText(AbDateUtil.getDateByFormat(item.StartTime, df).getDate() + "");
        mTextView_week.setText(AbDateUtil.getWeekNumber(item.StartTime, df));
        mTextView_content.setText(AbDateUtil.getStringByFormat(AbDateUtil.getDateByFormat(item.StartTime, df), "HH:mm") + "——" + AbDateUtil.getStringByFormat(AbDateUtil.getDateByFormat(item.EndTime, df), "HH:mm"));
    }

    public int getFristMPosition() {
        int p = -1;
        for (int i = 0; i < list.size(); i++) {
            Date data1 = AbDateUtil.getDateByFormat(item.StartTime, df);
            Date data2 = AbDateUtil.getDateByFormat(list.get(i).StartTime, df);
            if (data1.getYear() == data2.getYear() && data1.getMonth() == data2.getMonth()) {
                p = i;
                break;
            }
        }
        return p;
    }


}