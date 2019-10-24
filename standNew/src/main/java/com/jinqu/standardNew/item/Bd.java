//
//  Bd
//
//  Created by DELL on 2018-05-08 11:17:04
//  Copyright (c) DELL All rights reserved.


/**

 */

package com.jinqu.standardNew.item;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ab.util.AbDateUtil;
import com.jinqu.standardNew.R;
import com.jinqu.standardNew.frg.FrgPubWeb;
import com.jinqu.standardNew.frg.FrgShpjnduList;
import com.jinqu.standardNew.model.ModelBd;
import com.jinqu.standardNew.model.ModelRw;
import com.jinqu.standardNew.model.ModelUsreLogin;
import com.jinqu.standardNew.util.UtilDate;
import com.mdx.framework.activity.TitleAct;
import com.mdx.framework.utility.Helper;

import org.json.JSONArray;
import org.json.JSONObject;

import static com.ab.util.AbDateUtil.dateFormatYMDHMS;
import static com.framewidget.F.COLOR;
import static com.jinqu.standardNew.F.json2Model;
import static com.jinqu.standardNew.F.jsonIsArray;
import static com.jinqu.standardNew.F.mModelUsreLogin;
import static com.jinqu.standardNew.F.setImage;
import static com.jinqu.standardNew.util.UtilDate.changeTime;


public class Bd extends BaseItem {
    public TextView mTextView_title;
    public com.mdx.framework.widget.MImageView mMImageView_tx;
    public TextView mTextView_name;
    public LinearLayout mLinearLayout_content;
    public TextView mTextView_title_state;
    public TextView mTextView_jd;
    public ImageView mImageView_z;


    @SuppressLint("InflateParams")
    public static View getView(Context context, ViewGroup parent) {
        LayoutInflater flater = LayoutInflater.from(context);
        View convertView = flater.inflate(R.layout.item_bd, null);
        convertView.setTag(new Bd(convertView));
        return convertView;
    }

    public Bd(View view) {
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
        mMImageView_tx = (com.mdx.framework.widget.MImageView) findViewById(R.id.mMImageView_tx);
        mTextView_name = (TextView) findViewById(R.id.mTextView_name);
        mLinearLayout_content = (LinearLayout) findViewById(R.id.mLinearLayout_content);
        mTextView_title_state = (TextView) findViewById(R.id.mTextView_title_state);
        mTextView_jd = (TextView) findViewById(R.id.mTextView_jd);
        mImageView_z = (ImageView) findViewById(R.id.mImageView_z);
        mTextView_title_state.setTextColor(Color.parseColor(COLOR));
        mTextView_jd.setTextColor(Color.parseColor(COLOR));
        mMImageView_tx.setCircle(true);
    }

    public void set(final ModelBd.RowsBean item, final String statusID) {
        for (ModelUsreLogin.BaseEmployeeBean mRowsBean : mModelUsreLogin.BaseEmployee) {
            if (mRowsBean.EmpID == item.CreatorEmpId) {
                setImage(mMImageView_tx, mRowsBean.EmpName.substring(0, 1));
                mTextView_name.setText(mRowsBean.EmpName + "  " + AbDateUtil.formatDateStr2Desc(UtilDate.formatMatchDate(item.FlowStartDate, dateFormatYMDHMS)));
                if (!TextUtils.isEmpty(mRowsBean.EmpHead)) {
                    mMImageView_tx.setObj(mRowsBean.EmpHead);
                }
                break;
            }
        }
        if (TextUtils.isEmpty(statusID)) {
            mTextView_title.setText(item.text);
        } else {
            mTextView_title.setText((item.FlowModular == 1 ? "项目·" : "办公·") + item.FlowName);
        }
        mTextView_title_state.setText(TextUtils.isEmpty(item.FlowStatusName) ? "查看详情" : item.FlowStatusName);
        mLinearLayout_content.removeAllViews();
        if (!TextUtils.isEmpty(item.FlowSummary)) {
            mLinearLayout_content.setVisibility(View.VISIBLE);
            ModelBd.FlowSummary[] mFlowSummarys = (ModelBd.FlowSummary[]) json2Model(item.FlowSummary, ModelBd.FlowSummary[].class);
            for (ModelBd.FlowSummary mFlowSummary : mFlowSummarys) {
                View view = BdSon.getView(context, null);
                ((BdSon) view.getTag()).set(mFlowSummary);
                mLinearLayout_content.addView(view);
            }
        } else {
            mLinearLayout_content.setVisibility(View.GONE);
        }
        mTextView_jd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (item.FlowID != null && !TextUtils.isEmpty(item.FlowID.toString())) {
                    Helper.startActivity(context, FrgShpjnduList.class, TitleAct.class, "id", String.format("%.0f", Double.valueOf(item.FlowID.toString())));
                } else {
                    Helper.startActivity(context, FrgShpjnduList.class, TitleAct.class, "id", "0");
                }
            }
        });
        mTextView_title_state.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                mRowsBean.isNeedSp = true;
//                if (mRowsBean.FlowRefTable.equals(refTable_BussContract)) {
//                    mRowsBean.MenuNameEng = "ContractInfo";
//                }
//                if (mRowsBean.FlowRefTable.equals(refTable_BussContract) || mRowsBean.FlowRefTable.equals(refTable_Project) || mRowsBean.FlowRefTable.equals(refTable_BussFeeInvoiceISO) || mRowsBean.FlowRefTable.equals(refTable_SubFeeFact) || mRowsBean.FlowRefTable.equals(refTable_BussContractSub) || mRowsBean.FlowRefTable.equals(refTable_OaMeetingUse) || mRowsBean.FlowRefTable.equals(refTable_OaGoing) || mRowsBean.FlowRefTable.equals(refTable_OaWorking) || mRowsBean.FlowRefTable.equals(refTable_CarUse) || mRowsBean.FlowRefTable.equals(refTable_OaStampUse) || mRowsBean.FlowRefTable.equals(refTable_OaLeave) || mRowsBean.FlowRefTable.equals(refTable_OaEquipGetFlow)) {
//                }
                if (item.mModelMenuConfig != null)
                    Helper.startActivity(context, FrgPubWeb.class, TitleAct.class, "item", item, "statusID", statusID);
            }
        });
    }

    public void setRw(ModelRw.RowsBean item) {
        mTextView_jd.setVisibility(View.GONE);
        mImageView_z.setVisibility(View.GONE);
        setImage(mMImageView_tx, item.ItemEmpName.substring(0, 1));
        for (ModelUsreLogin.BaseEmployeeBean mRowsBean : mModelUsreLogin.BaseEmployee) {
            if (mRowsBean.EmpID == item.ItemEmpId) {
                if (!TextUtils.isEmpty(mRowsBean.EmpHead)) {
                    mMImageView_tx.setObj(mRowsBean.EmpHead);
                }
                break;
            }
        }
        mTextView_name.setText(item.ItemEmpName);
        try {
            if (jsonIsArray(item.ItemPath1)) {
                String path = "";
                JSONArray jsonArrayPath = new JSONArray(item.ItemPath1);
                for (int j = 0; j < jsonArrayPath.length(); j++) {
                    JSONObject jsonObjectPath = (JSONObject) jsonArrayPath.opt(j);
                    path += ">" + jsonObjectPath.getString("text");
                }
                JSONArray jsonArrayPath2 = new JSONArray(item.ItemPath2);
                for (int j = 0; j < jsonArrayPath2.length(); j++) {
                    JSONObject jsonObjectPath = (JSONObject) jsonArrayPath2.opt(j);
                    path += ">" + jsonObjectPath.getString("text");
                }
                mTextView_title.setText(path.substring(1, path.length()));
            }
        } catch (Exception e) {

        }
        mTextView_title_state.setText(item.ItemAction);
        mLinearLayout_content.removeAllViews();
        String json[] = {"任务负责人", item.ItemEmpName, "计划开始", changeTime(item.DatePlanStart, dateFormatYMDHMS), "计划结束", changeTime(item.DatePlanFinish, dateFormatYMDHMS), "实际结束", changeTime(item.DateActualFinish, dateFormatYMDHMS)};
        addContent(json);
    }


    public void addContent(String json[]) {
        mLinearLayout_content.removeAllViews();
        if (json.length > 0) {
            mLinearLayout_content.setVisibility(View.VISIBLE);
            for (int i = 0; i < json.length; i++) {
                if (i % 2 == 0) {
                    ModelBd.FlowSummary item = new ModelBd.FlowSummary();
                    item.Key = json[i];
                    item.Value = json[i + 1];
                    View view = BdSon.getView(context, null);
                    ((BdSon) view.getTag()).set(item);
                    mLinearLayout_content.addView(view);
                }
            }
        } else {
            mLinearLayout_content.setVisibility(View.GONE);
        }
    }


}