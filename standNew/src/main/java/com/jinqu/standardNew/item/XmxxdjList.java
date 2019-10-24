//
//  XmxxdjList
//
//  Created by DELL on 2018-08-22 15:48:14
//  Copyright (c) DELL All rights reserved.


/**

 */

package com.jinqu.standardNew.item;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.google.gson.Gson;
import com.jinqu.standardNew.R;
import com.jinqu.standardNew.ada.AdaXmxxdjList;
import com.jinqu.standardNew.model.ModelBd;
import com.jinqu.standardNew.model.ModelMenuConfig;
import com.jinqu.standardNew.model.ModelObj;
import com.jinqu.standardNew.util.ViewExpandAnimation;

import java.util.Arrays;

import static com.jinqu.standardNew.F.getRightdata;
import static com.jinqu.standardNew.F.json2Model;
import static com.jinqu.standardNew.ada.AdaXmxxdjList.childrenLayout_k;


public class XmxxdjList extends BaseItem {
    public LinearLayout mLinearLayout_top;
    public LinearLayout mLinearLayout_content;
    public int position;
    public AdaXmxxdjList mAdaXmxxdjList;
    public DisplayMetrics mDisplayMetrics;
    public ModelBd.RowsBean item;
    public LinearLayout mLinearLayout_content2;
    public ModelObj modelObj = new ModelObj();

    @SuppressLint("InflateParams")
    public static View getView(Context context, ViewGroup parent) {
        LayoutInflater flater = LayoutInflater.from(context);
        View convertView = flater.inflate(R.layout.item_xmxxdj_list, null);
        convertView.setTag(new XmxxdjList(convertView));
        return convertView;
    }

    public XmxxdjList(View view) {
        this.contentview = view;
        this.context = contentview.getContext();
        initView();
    }

    private void initView() {
        this.contentview.setTag(this);
        findVMethod();
    }

    private void findVMethod() {
        this.mDisplayMetrics = context.getResources().getDisplayMetrics();
        mLinearLayout_top = (LinearLayout) contentview.findViewById(R.id.mLinearLayout_top);
        mLinearLayout_content = (LinearLayout) contentview.findViewById(R.id.mLinearLayout_content);
        mLinearLayout_content2 = (LinearLayout) findViewById(R.id.mLinearLayout_content2);


        mLinearLayout_top.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (item.rows != null) {
                    goAnim();
                } else {
                    loadUrlPostBase(item.mModelMenuConfig.grid.url.get(1), item.mModelMenuConfig.grid.queryParams[1], "id", item.Id, "page", "1", "rows", Integer.MAX_VALUE);
                }
            }
        });
    }


    public void goAnim() {
        AdaXmxxdjList.position = position;
        if (childrenLayout_k != null && (childrenLayout_k.getVisibility() != View.GONE)) {
            childrenLayout_k.startAnimation(new ViewExpandAnimation(
                    childrenLayout_k));
        }
        childrenLayout_k = mLinearLayout_content;
        ViewExpandAnimation expandAnimation = new ViewExpandAnimation(childrenLayout_k);
        childrenLayout_k.startAnimation(expandAnimation);
    }

    @Override
    public void onSuccess(String methodName, String content) {
        modelObj.rows = Arrays.asList((Object[])json2Model(content, Object[].class));
        item.rows = Arrays.asList((ModelBd.RowsBean[]) json2Model(content, ModelBd.RowsBean[].class));
        mLinearLayout_content.removeAllViews();
        for (int i = 0; i < item.rows.size(); i++) {
            getData();
        }
        //这里很重要，计算当前要展开view的高度
        int widthSpec = View.MeasureSpec.makeMeasureSpec((int) (mDisplayMetrics.widthPixels -
                10 * mDisplayMetrics.density), View.MeasureSpec.EXACTLY);
        mLinearLayout_content.measure(widthSpec, 0);
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) mLinearLayout_content.getLayoutParams();
        if (AdaXmxxdjList.position == position) {
            params.bottomMargin = 0;
        } else {
            params.bottomMargin = -mLinearLayout_content.getMeasuredHeight();
        }
        goAnim();
    }

    public void getData() {
        for (int i = 0; i < item.rows.size(); i++) {
//            item.rows.get(i).FlowRefID = item.rows.get(i).Id;
            item.rows.get(i).text = item.text;
            item.rows.get(i).MenuNameEng = item.MenuNameEng;
            item.rows.get(i).mModelMenuConfig = new Gson().fromJson(getRightdata(item.MenuMobileConfig, modelObj.rows .get(i)), ModelMenuConfig.class);
            View v = XmxxdjListSon.getView(context, null);
            ((XmxxdjListSon) v.getTag()).set(item.rows.get(i));
            mLinearLayout_content.addView(v);
        }
    }

    public void set(ModelBd.RowsBean item, int position, AdaXmxxdjList mAdaXmxxdjList) {
        this.position = position;
        this.item = item;
        this.mAdaXmxxdjList = mAdaXmxxdjList;
        mLinearLayout_content.removeAllViews();
        if (item.rows != null) {
            getData();
        }
        if (AdaXmxxdjList.position == position) {
            mLinearLayout_content.setVisibility(View.VISIBLE);
        } else {
            mLinearLayout_content.setVisibility(View.GONE);
        }
        mLinearLayout_content2.removeAllViews();
        if (!TextUtils.isEmpty(item._summary)) {
            ModelBd.FlowSummary[] mFlowSummarys = (ModelBd.FlowSummary[]) json2Model(item._summary, ModelBd.FlowSummary[].class);
            for (ModelBd.FlowSummary mFlowSummary : mFlowSummarys) {
                View view = BdSon.getView(context, null);
                ((BdSon) view.getTag()).set(mFlowSummary);
                mLinearLayout_content2.addView(view);
            }
        }
    }


}