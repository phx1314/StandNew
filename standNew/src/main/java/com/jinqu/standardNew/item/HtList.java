//
//  HtList
//
//  Created by DELL on 2018-08-28 09:36:31
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
import com.jinqu.standardNew.model.ModelHt;

import static com.jinqu.standardNew.util.UtilDate.changeTime;


public class HtList extends BaseItem {
    public TextView mTextView_title;
    public TextView mTextView_title2;
    public TextView mTextView_content1;
    public TextView mTextView_content2;
    public TextView mTextView_content3;
    public TextView mTextView_content4;


    @SuppressLint("InflateParams")
    public static View getView(Context context, ViewGroup parent) {
        LayoutInflater flater = LayoutInflater.from(context);
        View convertView = flater.inflate(R.layout.item_ht_list, null);
        convertView.setTag(new HtList(convertView));
        return convertView;
    }

    public HtList(View view) {
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
        mTextView_content3 = (TextView) contentview.findViewById(R.id.mTextView_content3);
        mTextView_content4 = (TextView) contentview.findViewById(R.id.mTextView_content4);


    }

    public void set(ModelHt.RowsBean item) {
        if (item.MenuNameEng.equals("ContractInfo")) {//收款合同
            mTextView_title.setText("合同编号:" + item.ConNumber + "     合同名称:" + item.ConName);
            mTextView_title2.setText("客户名称:" + item.CustName + "     签订日期:" + changeTime(item.ConDate));
            mTextView_content1.setText("合同金额:" + item.SumConFee + "     已收款:" + item.FeeFact);
            mTextView_content2.setText("未收款:" + item.NoFee + "     开票款:" + item.FeeInvoice);
            mTextView_content3.setText("是否结清:" + item.ConIsFeeFinished + "     流程进度:" + (TextUtils.isEmpty(item.FlowStatusName) ? "" : item.FlowStatusName));
            mTextView_content4.setVisibility(View.GONE);
        } else if (item.MenuNameEng.equals("ContractSubInfo")) {//付款合同
            mTextView_title.setText("外委合同编号:" + item.ConSubNumber + "     外委合同名称:" + item.ConSubName);
            mTextView_title2.setText("外委合同类型:" + item.ConSubTypeName + "     外委合同类别:" + item.ConSubCategoryName);
            mTextView_content1.setText("外委合同状态:" + item.ConSubStatusName + "     外委合同单位:" + (TextUtils.isEmpty(item.CustName) ? "" : item.CustName));
            mTextView_content2.setText("归档盒号:" + item.ArchNumber + "     签订日期:" + changeTime(item.ConSubDate));
            mTextView_content3.setText("合同金额(万元):" + item.ConSubFee + "     已付款(万元):" + item.AlreadySumFeeMoney);
            mTextView_content4.setText("未付款(万元):" + item.UnPay + "     已收票(万元):" + item.AlreadySumInvoiceMoney + "     流程进度:" + (TextUtils.isEmpty(item.FlowStatusName) ? "" : item.FlowStatusName));
        } else if (item.MenuNameEng.equals("OtherContractInfo")) {//其他收款合同
            mTextView_title.setText("合同编号:" + item.ConNumber + "     合同名称:" + item.ConrName);
            mTextView_title2.setText("客户单位:" + item.CustName + "     合同金额（万元）:" + item.ConFactFee);
            mTextView_content1.setText("已收款（万元）:" + item.SumFeeMoney + "     未收款（万元）:" + item.NoFeeMoney);
            mTextView_content2.setText("已收票（万元）:" + item.SumInvoiceMoney + "     是否结清:" + item.ConIsFeeFinishedName);
            mTextView_content3.setVisibility(View.GONE);
            mTextView_content4.setVisibility(View.GONE);
        } else if (item.MenuNameEng.equals("OtherContracSubtInfo")) {//其他付款合同
            mTextView_title.setText("合同编号:" + item.ConNumber + "     合同名称:" + item.ConrName);
            mTextView_title2.setText("客户单位:" + item.CustName + "     合同金额（万元）:" + item.ConFactFee);
            mTextView_content1.setText("已收款（万元）:" + item.SumFeeMoney + "     未收款（万元）:" + item.NoFeeMoney);
            mTextView_content2.setText("已收票（万元）:" + item.SumInvoiceMoney + "     是否结清:" + item.ConIsFeeFinishedName);
            mTextView_content3.setVisibility(View.GONE);
            mTextView_content4.setVisibility(View.GONE);
        }

    }


}