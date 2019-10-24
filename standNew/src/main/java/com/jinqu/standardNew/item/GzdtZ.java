//
//  GzdtZ
//
//  Created by DELL on 2018-05-17 15:59:39
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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jinqu.standardNew.R;
import com.jinqu.standardNew.model.ModelDtetail;
import com.jinqu.standardNew.model.ModelUsreLogin;
import com.jinqu.standardNew.util.UtilDate;
import com.mdx.framework.widget.MImageView;

import static com.jinqu.standardNew.F.mModelUsreLogin;
import static com.jinqu.standardNew.F.setImage;


public class GzdtZ extends BaseItem {
    public LinearLayout mLinearLayout_hf;
    public MImageView mMImageView_touxiang;
    public TextView mTextView_name;
    public TextView mTextView_time;
    public ImageView mImageView_lz;
    public TextView mTextView_content;


    @SuppressLint("InflateParams")
    public static View getView(Context context, ViewGroup parent) {
        LayoutInflater flater = LayoutInflater.from(context);
        View convertView = flater.inflate(R.layout.item_gzdt_z, null);
        convertView.setTag(new GzdtZ(convertView));
        return convertView;
    }

    public GzdtZ(View view) {
        this.contentview = view;
        this.context = contentview.getContext();
        initView();
    }

    private void initView() {
        this.contentview.setTag(this);
        findVMethod();
    }

    private void findVMethod() {
        mLinearLayout_hf = (LinearLayout) contentview.findViewById(R.id.mLinearLayout_hf);
        mMImageView_touxiang = (MImageView) contentview.findViewById(R.id.mMImageView_touxiang);
        mTextView_name = (TextView) contentview.findViewById(R.id.mTextView_name);
        mTextView_time = (TextView) contentview.findViewById(R.id.mTextView_time);
        mImageView_lz = (ImageView) contentview.findViewById(R.id.mImageView_lz);
        mTextView_content = (TextView) contentview.findViewById(R.id.mTextView_content);
        mMImageView_touxiang.setCircle(true);

    }

    public void set(ModelDtetail.RowsBean item) {
        for (ModelUsreLogin.BaseEmployeeBean mRowsBean : mModelUsreLogin.BaseEmployee) {
            if (mRowsBean.EmpID == item.CreatorEmpId) {
                if (!TextUtils.isEmpty(mRowsBean.EmpHead)) {
                    mMImageView_touxiang.setObj(mRowsBean.EmpHead);
                } else {
                    setImage(mMImageView_touxiang, item.CreatorEmpName.substring(0, 1));
                }
                break;
            }
        }

        mTextView_name.setText(item.CreatorEmpName);
        mTextView_time.setText(UtilDate.formatMatchDate(item.CreationTime, "yyyy-MM-dd HH:mm:ss"));
        mTextView_content.setText(item.ReplyContent);

        if (item.CreatorEmpId == mModelUsreLogin.UserInfo.EmpID) {
            mImageView_lz.setVisibility(View.VISIBLE);
        } else {
            mImageView_lz.setVisibility(View.GONE);
        }
    }


}