//
//  FrgWd
//
//  Created by DELL on 2018-05-08 11:17:04
//  Copyright (c) DELL All rights reserved.


/**

 */

package com.jinqu.standardNew.frg;

import android.Manifest;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.framewidget.frg.FrgPtDetail;
import com.framewidget.zxing.SimpleScannerActivity;
import com.jinqu.standardNew.R;
import com.jinqu.standardNew.model.ModelCount;
import com.mdx.framework.Frame;
import com.mdx.framework.activity.NoTitleAct;
import com.mdx.framework.activity.TitleAct;
import com.mdx.framework.utility.Helper;
import com.mdx.framework.utility.permissions.PermissionRequest;

import static com.jinqu.standardNew.F.COLOR;
import static com.jinqu.standardNew.F.METHOD_GetMails;
import static com.jinqu.standardNew.F.json2Model;
import static com.jinqu.standardNew.F.mModelUsreInfo;
import static com.jinqu.standardNew.F.mModelUsreLogin;
import static com.jinqu.standardNew.F.setImage;


public class FrgWd extends BaseFrg {

    public TextView mTextView_name;
    public TextView mTextView_phone;
    public LinearLayout mLinearLayout_2;
    public LinearLayout mLinearLayout_3;
    public LinearLayout mLinearLayout_4;
    public LinearLayout mLinearLayout_5;
    public LinearLayout mLinearLayout_6;
    public TextView mTextView_zhiwei;
    public TextView mTextView_bm;
    public TextView mTextView_qm;
    public RelativeLayout mLinearLayout_bg;
    public com.mdx.framework.widget.MImageView mMImageView_tx;
    public ImageButton mImageButton_saoma;
    public LinearLayout mLinearLayout_hh;
    public LinearLayout mLinearLayout_7;
    public ImageView mImageView_mail;

    @Override
    protected void create(Bundle savedInstanceState) {
        setContentView(R.layout.frg_wd);
        initView();
        loaddata();
    }

    @Override
    public void disposeMsg(int type, Object obj) {
        switch (type) {
            case 10086:
                Drawable mDrawable = getResources().getDrawable(R.drawable.saoma);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
                    mDrawable.setTint(Color.parseColor(COLOR));
                mImageButton_saoma.setImageDrawable(mDrawable);
                break;
            case 200:
                loaddata();
                break;
            case 0:
                loadUrlPostNoShow(METHOD_GetMails);
                break;
        }
    }

    private void initView() {
        findVMethod();
    }

    private void findVMethod() {
        mTextView_name = (TextView) findViewById(R.id.mTextView_name);
        mTextView_phone = (TextView) findViewById(R.id.mTextView_phone);
        mLinearLayout_2 = (LinearLayout) findViewById(R.id.mLinearLayout_2);
        mLinearLayout_3 = (LinearLayout) findViewById(R.id.mLinearLayout_3);
        mLinearLayout_4 = (LinearLayout) findViewById(R.id.mLinearLayout_4);
        mLinearLayout_5 = (LinearLayout) findViewById(R.id.mLinearLayout_5);
        mLinearLayout_6 = (LinearLayout) findViewById(R.id.mLinearLayout_6);
        mTextView_zhiwei = (TextView) findViewById(R.id.mTextView_zhiwei);
        mTextView_bm = (TextView) findViewById(R.id.mTextView_bm);
        mTextView_qm = (TextView) findViewById(R.id.mTextView_qm);
        mLinearLayout_bg = (RelativeLayout) findViewById(R.id.mLinearLayout_bg);
        mMImageView_tx = (com.mdx.framework.widget.MImageView) findViewById(R.id.mMImageView_tx);
        mImageButton_saoma = (ImageButton) findViewById(R.id.mImageButton_saoma);
        mLinearLayout_hh = (LinearLayout) findViewById(R.id.mLinearLayout_hh);
        mLinearLayout_7 = (LinearLayout) findViewById(R.id.mLinearLayout_7);
        mImageView_mail = (ImageView) findViewById(R.id.mImageView_mail);

        mLinearLayout_6.setOnClickListener(Helper.delayClickLitener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setBgClickClor(v);
                Helper.startActivity(getContext(), FrgSet.class, TitleAct.class);
            }
        }));
        mLinearLayout_3.setOnClickListener(Helper.delayClickLitener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setBgClickClor(v);
                Helper.startActivity(getContext(), FrgEmailList.class, TitleAct.class);
            }
        }));
        mLinearLayout_2.setOnClickListener(Helper.delayClickLitener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setBgClickClor(v);
                Helper.startActivity(getContext(), FrgWodeWenjian.class, TitleAct.class);
            }
        }));
        mLinearLayout_4.setOnClickListener(Helper.delayClickLitener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setBgClickClor(v);
                Helper.startActivity(getContext(), FrgGzdtF.class, TitleAct.class);
            }
        }));
        mLinearLayout_5.setOnClickListener(Helper.delayClickLitener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setBgClickClor(v);
                Helper.startActivity(getContext(), FrgPtDetail.class, NoTitleAct.class, "url", "http://www.jinqu.cn", "title", "帮助中心");
            }
        }));
        mLinearLayout_7.setOnClickListener(Helper.delayClickLitener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setBgClickClor(v);
                Helper.startActivity(getContext(), FrgPtDetail.class, NoTitleAct.class, "url", "http://www.jinqu.cn:2016/channel.aspx?id=11#jq_4", "title", "联系客服");
            }
        }));
        mImageButton_saoma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setBgClickClor(v);
                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        1);
                Helper.requestPermissions(new String[]{"android.permission.CAMERA"}, new PermissionRequest() {
                    public void onGrant(String[] permissions, int[] grantResults) {
                        startActivity(new Intent(
                                getActivity(), SimpleScannerActivity.class));
                    }
                });
            }
        });
    }

    @Override
    public void onSuccess(String methodName, String content) {
        if (methodName.equals(METHOD_GetMails)) {
            ModelCount mModelYjList = (ModelCount) json2Model(content, ModelCount.class);
            if (mModelYjList.Total > 0) {
                mImageView_mail.setVisibility(View.VISIBLE);
                Frame.HANDLES.sentAll("FrgHome", 115, true);
            } else {
                mImageView_mail.setVisibility(View.GONE);
                Frame.HANDLES.sentAll("FrgHome", 115, false);
            }
        }
    }

    public void loaddata() {
        loadUrlPostNoShow(METHOD_GetMails);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mImageButton_saoma.getDrawable().setTint(Color.parseColor(COLOR));
        }
//        GradientDrawable myGrad = (GradientDrawable) getResources().getDrawable(R.drawable.shape_blue);
//        myGrad.setColor(Color.parseColor("#ffffff"));
//        mLinearLayout_bg.setBackground(myGrad);
        mLinearLayout_hh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setBgClickClor(v);
                Helper.startActivity(getContext(), FrgGrzx.class, NoTitleAct.class, "id", mModelUsreLogin.UserInfo.EmpID + "");
            }
        });
        mImageButton_saoma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setBgClickClor(v);
                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        1);
                Helper.requestPermissions(new String[]{"android.permission.CAMERA"}, new PermissionRequest() {
                    public void onGrant(String[] permissions, int[] grantResults) {
                        startActivity(new Intent(
                                getActivity(), SimpleScannerActivity.class));
                    }
                });
            }
        });
        if (!TextUtils.isEmpty(mModelUsreInfo.model.EmpHead)) {
            mMImageView_tx.setObj(mModelUsreInfo.model.EmpHead);
        } else {
            setImage(mMImageView_tx, mModelUsreInfo.model.EmpName.substring(0, 1));
        }
        mMImageView_tx.setCircle(true);
        mTextView_name.setText(mModelUsreInfo.model.EmpName);
        mTextView_bm.setText(mModelUsreInfo.model.EmpDepName);
        mTextView_zhiwei.setText(mModelUsreInfo.model.EmpTitle);
        mTextView_phone.setText(mModelUsreInfo.model.EmpTel);
        mTextView_qm.setText(mModelUsreInfo.model.EmpNote);
    }

}