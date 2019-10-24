//
//  XmxxdjListSon
//
//  Created by DELL on 2018-08-22 15:31:25
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
import com.jinqu.standardNew.frg.FrgPubWeb;
import com.jinqu.standardNew.model.ModelBd;
import com.mdx.framework.activity.TitleAct;
import com.mdx.framework.utility.Helper;

import static com.jinqu.standardNew.F.json2Model;


public class XmxxdjListSon extends BaseItem {
    public LinearLayout mLinearLayout_content;
    public ModelBd.RowsBean item;
    public LinearLayout mLinearLayout_content2;

    @SuppressLint("InflateParams")
    public static View getView(Context context, ViewGroup parent) {
        LayoutInflater flater = LayoutInflater.from(context);
        View convertView = flater.inflate(R.layout.item_xmxxdj_list_son, null);
        convertView.setTag(new XmxxdjListSon(convertView));
        return convertView;
    }

    public XmxxdjListSon(View view) {
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
        mLinearLayout_content2 = (LinearLayout) findViewById(R.id.mLinearLayout_content2);

        mLinearLayout_content.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Helper.startActivity(context, FrgPubWeb.class, TitleAct.class, "item", item);
            }
        });
    }

    public void set(ModelBd.RowsBean item) {
        this.item = item;
        mLinearLayout_content2.removeAllViews();
        if (!TextUtils.isEmpty(item._summary)) {
            mLinearLayout_content2.setVisibility(View.VISIBLE);
            ModelBd.FlowSummary[] mFlowSummarys = (ModelBd.FlowSummary[]) json2Model(item._summary, ModelBd.FlowSummary[].class);
            for (ModelBd.FlowSummary mFlowSummary : mFlowSummarys) {
                View view = BdSon.getView(context, null);
                ((BdSon) view.getTag()).set(mFlowSummary);
                mLinearLayout_content2.addView(view);
            }
        } else {
            mLinearLayout_content2.setVisibility(View.GONE);
        }
    }


}