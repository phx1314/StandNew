//
//  EmailList
//
//  Created by DELL on 2018-06-21 14:01:00
//  Copyright (c) DELL All rights reserved.


/**

 */

package com.jinqu.standardNew.item;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ab.util.AbDateUtil;
import com.framewidget.F;
import com.jinqu.standardNew.R;
import com.jinqu.standardNew.ada.AdaEmailList;
import com.jinqu.standardNew.frg.FrgEmailDetail;
import com.jinqu.standardNew.frg.FrgSendEmail;
import com.jinqu.standardNew.model.ModelUsreLogin;
import com.jinqu.standardNew.model.ModelYjList;
import com.jinqu.standardNew.view.SwipMoreView;
import com.mdx.framework.Frame;
import com.mdx.framework.activity.TitleAct;
import com.mdx.framework.utility.Helper;
import com.mdx.framework.widget.MImageView;

import static com.ab.util.AbDateUtil.dateFormatYMDHMS;
import static com.jinqu.standardNew.F.mModelUsreLogin;
import static com.jinqu.standardNew.F.setImage;
import static com.jinqu.standardNew.frg.FrgEmailList.isShowDel;


public class EmailList extends SwipMoreView {
    public TextView mTextView_name;
    public TextView mTextView_time;
    public TextView mTextView_content;
    public ModelYjList.RowsBean item;
    public MImageView mMImageView;
    public ImageView mCheckBox;
    public ImageView mImageView_wd;
    public TextView mTextView_title_fu;
    public Button mButton_delete;
    public LinearLayout mLinearLayout_delete;
    public Button mButton_yd;
    public LinearLayout mLinearLayout_yd;
    public LinearLayout mLinearLayout_content;
    public AdaEmailList mAdaEmailList;
    public int type;
    public LinearLayout mLinearLayout_content2;

    public EmailList(Context context) {
        super(context);
        initView();
    }


    private void initView() {
        LayoutInflater.from(getContext()).inflate(R.layout.item_email_list, this);
        findVMethod();
        reset();
    }

    private void findVMethod() {
        mTextView_name = (TextView) findViewById(R.id.mTextView_name);
        mTextView_time = (TextView) findViewById(R.id.mTextView_time);
        mTextView_content = (TextView) findViewById(R.id.mTextView_content);
        mMImageView = (MImageView) findViewById(R.id.mMImageView);
        mCheckBox = (ImageView) findViewById(R.id.mCheckBox);
        mImageView_wd = (ImageView) findViewById(R.id.mImageView_wd);
        mTextView_title_fu = (TextView) findViewById(R.id.mTextView_title_fu);
        mButton_delete = (Button) findViewById(R.id.mButton_delete);
        mLinearLayout_delete = (LinearLayout) findViewById(R.id.mLinearLayout_delete);
        mButton_yd = (Button) findViewById(R.id.mButton_yd);
        mLinearLayout_yd = (LinearLayout) findViewById(R.id.mLinearLayout_yd);
        mLinearLayout_content = (LinearLayout) findViewById(R.id.mLinearLayout_content);
        mLinearLayout_content2 = (LinearLayout) findViewById(R.id.mLinearLayout_content2);
        GradientDrawable myGrad = (GradientDrawable) getResources().getDrawable(R.drawable.shape_blue_yuan);
        myGrad.setColor(Color.parseColor(com.jinqu.standardNew.F.COLOR));
        mImageView_wd.setBackground(myGrad);
        mMImageView.setCircle(true);

        mLinearLayout_content2.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isShowDel) {
                    item.isChecked = !item.isChecked;
                    Frame.HANDLES.sentAll("FrgEmailList", 100, null);
                } else {
                    if (type == 3) {
                        Helper.startActivity(getContext(), FrgSendEmail.class, TitleAct.class, "id", item.Id + "", "type", type);
                    } else {
                        item.MailIsRead = true;
                        Helper.startActivity(getContext(), FrgEmailDetail.class, TitleAct.class, "id", item.Id + "", "type", type);
                    }

                }
                mAdaEmailList.notifyDataSetChanged();
            }
        });
        mLinearLayout_content2.setOnLongClickListener(new OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (!isShowDel) {
                    item.isChecked = true;
                    Frame.HANDLES.sentAll("FrgEmailList", 120, null);
                    return true;
                }
                return false;
            }
        });
    }

    public void set(ModelYjList.RowsBean item, int type, AdaEmailList mAdaEmailList) {
        this.item = item;
        this.type = type;
        this.mAdaEmailList = mAdaEmailList;
        mTextView_name.setText(item.MailEmpName);
        mTextView_content.setText(Html.fromHtml(item.MailNote));
        if (item.MailIsRead) {
            mImageView_wd.setVisibility(View.GONE);
        } else {
            mImageView_wd.setVisibility(View.VISIBLE);
        }
        setImage(mMImageView, TextUtils.isEmpty(item.MailEmpName) ? "" : item.MailEmpName.substring(0, 1));
        for (ModelUsreLogin.BaseEmployeeBean mRowsBean : mModelUsreLogin.BaseEmployee) {
            if (mRowsBean.EmpName.equals(item.MailEmpName)) {
                if (!TextUtils.isEmpty(mRowsBean.EmpHead)) {
                    mMImageView.setObj(mRowsBean.EmpHead);
                }
                break;
            }
        }

        mTextView_title_fu.setText(item.MailTitle);
        mTextView_time.setText(AbDateUtil.formatDateStr2Desc(AbDateUtil.getStringByFormat(AbDateUtil.getDateByFormat(item.MailDate, "yyyy-MM-dd'T'HH:mm:ss.SSS"), dateFormatYMDHMS)));
        mCheckBox.setBackgroundResource(item.isChecked ? R.drawable.xuanzhong : R.drawable.weixuan);
        if (isShowDel) {
            mCheckBox.setVisibility(View.VISIBLE);
        } else {
            mCheckBox.setVisibility(View.GONE);
        }
        if (item.HasAttachs > 0) {
            mTextView_name.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.fujian_, 0);
        } else {
            mTextView_name.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
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
            item.MailIsRead = true;
            mAdaEmailList.notifyDataSetChanged();
            EmailList.this.moveBack();
            Frame.HANDLES.sentAll("FrgEmailList", 111, item.Id + "");
        } else {
            F.yShoure(getContext(), "确认删除", "", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Frame.HANDLES.sentAll("FrgEmailList", 110, item.MailID + "");
                }
            }, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    EmailList.this.moveBack();
                }
            }, new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialog) {
                    EmailList.this.moveBack();
                }
            });
        }
    }
}