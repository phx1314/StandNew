//
//  Xtxx
//
//  Created by DELL on 2018-07-19 13:17:46
//  Copyright (c) DELL All rights reserved.


/**

 */

package com.jinqu.standardNew.item;

import android.content.Context;
import android.content.DialogInterface;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ab.util.AbDateUtil;
import com.ab.util.AbMd5;
import com.framewidget.F;
import com.framewidget.frg.FrgPtDetail;
import com.jinqu.standardNew.R;
import com.jinqu.standardNew.ada.AdaXtxx;
import com.jinqu.standardNew.model.ModelSystemList;
import com.jinqu.standardNew.model.ModelUsreLogin;
import com.jinqu.standardNew.util.UtilDate;
import com.jinqu.standardNew.view.SwipMoreView;
import com.mdx.framework.Frame;
import com.mdx.framework.activity.NoTitleAct;
import com.mdx.framework.config.BaseConfig;
import com.mdx.framework.utility.Helper;

import static com.ab.util.AbDateUtil.dateFormatYMDHMS;
import static com.jinqu.standardNew.F.mModelUsreLogin;
import static com.jinqu.standardNew.F.setImage;
import static com.jinqu.standardNew.frg.FrgXtxx.isShowDel;


public class Xtxx extends SwipMoreView {


    public Button mButton_delete;
    public LinearLayout mLinearLayout_delete;
    public Button mButton_yd;
    public LinearLayout mLinearLayout_yd;
    public com.mdx.framework.widget.MImageView mMImageView;
    public TextView mTextView_name;
    public TextView mTextView_time;
    public TextView mTextView_title_fu;
    public ImageView mCheckBox;
    public LinearLayout mLinearLayout_content2;
    public LinearLayout mLinearLayout_content;
    public ModelSystemList.RowsBean item;
    public AdaXtxx mAdaXtxx;

    public Xtxx(Context context) {
        super(context);
        initView();
    }

    private void initView() {
        LayoutInflater.from(getContext()).inflate(R.layout.item_xtxx, this);
        findVMethod();
        reset();
    }

    private void findVMethod() {
        mButton_delete = (Button) findViewById(R.id.mButton_delete);
        mLinearLayout_delete = (LinearLayout) findViewById(R.id.mLinearLayout_delete);
        mButton_yd = (Button) findViewById(R.id.mButton_yd);
        mLinearLayout_yd = (LinearLayout) findViewById(R.id.mLinearLayout_yd);
        mMImageView = (com.mdx.framework.widget.MImageView) findViewById(R.id.mMImageView);
        mTextView_name = (TextView) findViewById(R.id.mTextView_name);
        mTextView_time = (TextView) findViewById(R.id.mTextView_time);
        mTextView_title_fu = (TextView) findViewById(R.id.mTextView_title_fu);
        mCheckBox = (ImageView) findViewById(R.id.mCheckBox);
        mLinearLayout_content2 = (LinearLayout) findViewById(R.id.mLinearLayout_content2);
        mLinearLayout_content = (LinearLayout) findViewById(R.id.mLinearLayout_content);

        mMImageView.setCircle(true);

        mLinearLayout_content2.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isShowDel) {
                    item.isChecked = !item.isChecked;
                    Frame.HANDLES.sentAll("FrgXtxx", 100, null);
                } else {
                    Helper.startActivity(getContext(), FrgPtDetail.class,
                            NoTitleAct.class, "title", "详情", "url",
                            BaseConfig.getUri() + "/oa/Messagemobile/Display?id=" + item.Id + "&a=" + mModelUsreLogin.name + "&p=" + AbMd5.MD5(mModelUsreLogin.password));

                }
                mAdaXtxx.notifyDataSetChanged();
            }
        });
        mLinearLayout_content2.setOnLongClickListener(new OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (!isShowDel) {
                    item.isChecked = true;
                    Frame.HANDLES.sentAll("FrgXtxx", 120, null);
                    return true;
                }
                return false;
            }
        });
    }

    public void set(ModelSystemList.RowsBean item, AdaXtxx mAdaXtxx) {
        this.item = item;
        this.mAdaXtxx = mAdaXtxx;
        mTextView_name.setText(item.MessEmpName);
        setImage(mMImageView, TextUtils.isEmpty(item.MessEmpName) ? "" : item.MessEmpName.substring(0, 1));
        for (ModelUsreLogin.BaseEmployeeBean mRowsBean : mModelUsreLogin.BaseEmployee) {
            if (mRowsBean.EmpID == item.MessEmpId) {
                if (!TextUtils.isEmpty(mRowsBean.EmpHead)) {
                    mMImageView.setObj(mRowsBean.EmpHead);
                }
                break;
            }
        }
        mTextView_title_fu.setText(item.MessLinkTitle);
        mTextView_time.setText(AbDateUtil.formatDateStr2Desc(AbDateUtil.getStringByFormat(UtilDate.formatMatchDate(item.MessDate, dateFormatYMDHMS), dateFormatYMDHMS)));
        mCheckBox.setBackgroundResource(item.isChecked ? R.drawable.xuanzhong : R.drawable.weixuan);
        if (isShowDel) {
            mCheckBox.setVisibility(View.VISIBLE);
        } else {
            mCheckBox.setVisibility(View.GONE);
        }

    }

    @Override
    public boolean swipLeftAble() {
        return !isShowDel;
    }

    @Override
    public boolean swipRightAble() {
        return !isShowDel;
    }

    @Override
    public View swipView() {
        return mLinearLayout_content;
    }

    @Override
    public View moreView() {
        return mLinearLayout_delete;
    }

    @Override
    public void swipEnd(boolean isLeft) {
        if (isLeft) {
//            item.MailIsRead = true;
//            mAdaEmailList.notifyDataSetChanged();
            this.moveBack();
            Frame.HANDLES.sentAll("FrgXtxx", 111, item.Id + "");
        } else {
            F.yShoure(getContext(), "确认删除", "", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Frame.HANDLES.sentAll("FrgXtxx", 110, item.Id + "");
                }
            }, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Xtxx.this.moveBack();
                }
            }, new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialog) {
                    Xtxx.this.moveBack();
                }
            });
        }
    }
}