//
//  Gg
//
//  Created by DELL on 2018-07-26 11:17:20
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

import com.jinqu.standardNew.R;
import com.jinqu.standardNew.model.ModelBd;

import static com.jinqu.standardNew.F.json2Model;


public class Gg extends BaseItem {
    public LinearLayout mLinearLayout_content;


    @SuppressLint("InflateParams")
    public static View getView(Context context, ViewGroup parent) {
        LayoutInflater flater = LayoutInflater.from(context);
        View convertView = flater.inflate(R.layout.item_gg, null);
        convertView.setTag(new Gg(convertView));
        return convertView;
    }

    public Gg(View view) {
        this.contentview = view;
        this.context = contentview.getContext();
        initView();
    }

    private void initView() {
        this.contentview.setTag(this);
        findVMethod();
    }

    private void findVMethod() {
        mLinearLayout_content = (LinearLayout) findViewById(R.id.mLinearLayout_content);


    }

    public void set(ModelBd.RowsBean item) {
        mLinearLayout_content.removeAllViews();
        if (!TextUtils.isEmpty(item._summary)) {
            ModelBd.FlowSummary[] mFlowSummarys = (ModelBd.FlowSummary[]) json2Model(item._summary, ModelBd.FlowSummary[].class);
            for (ModelBd.FlowSummary mFlowSummary : mFlowSummarys) {
                View view = BdSon.getView(context, null);
                ((BdSon) view.getTag()).set(mFlowSummary);
                mLinearLayout_content.addView(view);
            }
        }
    }


}