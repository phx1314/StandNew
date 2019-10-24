//
//  FrgSet
//
//  Created by DELL on 2018-05-08 11:17:04
//  Copyright (c) DELL All rights reserved.


/**

 */

package com.jinqu.standardNew.frg;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.framewidget.F;
import com.framewidget.view.CallBackOnly;
import com.jinqu.standardNew.R;
import com.jinqu.standardNew.item.ColorDialog;
import com.mdx.framework.Frame;
import com.mdx.framework.activity.IndexAct;
import com.mdx.framework.activity.TitleAct;
import com.mdx.framework.cache.DataStoreCacheManage;
import com.mdx.framework.cache.FileStoreCacheManage;
import com.mdx.framework.cache.ImageStoreCacheManage;
import com.mdx.framework.commons.ParamsManager;
import com.mdx.framework.config.BaseConfig;
import com.mdx.framework.utility.Helper;
import com.mdx.framework.utility.UnitConver;
import com.mdx.framework.widget.ActionBar;

import cn.jpush.android.api.JPushInterface;

import static com.jinqu.standardNew.F.clearJson;
import static com.jinqu.standardNew.F.getJson;


public class FrgSet extends BaseFrg {

    public LinearLayout mLinearLayout_qkhc;
    public TextView mTextView_qkhc;
    public TextView mTextView_update;
    public LinearLayout mLinearLayout_gy;
    public TextView mTextView_logout;
    public LinearLayout mLinearLayout_zh;
    public LinearLayout mLinearLayout_tz;
    public LinearLayout mLinearLayout_wr;
    public LinearLayout mLinearLayout_rz;
    public LinearLayout mLinearLayout_logout;

    private Runnable mRunnable;
    public LinearLayout mLinearLayout_ztset;

    @Override
    protected void create(Bundle savedInstanceState) {
        setContentView(R.layout.frg_set);
        initView();
        loaddata();
    }

    @Override
    public void disposeMsg(int type, Object obj) {
        switch (type) {
            case 10086:
                mHeadlayout.setLeftBackground(R.drawable.arrows_left);
                break;
            case 201:
                com.jinqu.standardNew.F.saveJson("IP", obj.toString());
                try {
                    BaseConfig.setUri("http://" + getJson("IP"));
                    ParamsManager.put("image_star", "http://" + getJson("IP").substring(0, getJson("IP").lastIndexOf("/")) + "/GoldFile/");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                LogOut();
                break;
        }
    }

    private void initView() {
        findVMethod();
    }

    private void findVMethod() {
        mLinearLayout_qkhc = (LinearLayout) findViewById(R.id.mLinearLayout_qkhc);
        mTextView_qkhc = (TextView) findViewById(R.id.mTextView_qkhc);
        mTextView_update = (TextView) findViewById(R.id.mTextView_update);
        mLinearLayout_gy = (LinearLayout) findViewById(R.id.mLinearLayout_gy);
        mTextView_logout = (TextView) findViewById(R.id.mTextView_logout);
        mLinearLayout_ztset = (LinearLayout) findViewById(R.id.mLinearLayout_ztset);
        mLinearLayout_zh = (LinearLayout) findViewById(R.id.mLinearLayout_zh);
        mLinearLayout_tz = (LinearLayout) findViewById(R.id.mLinearLayout_tz);
        mLinearLayout_wr = (LinearLayout) findViewById(R.id.mLinearLayout_wr);
        mLinearLayout_rz = (LinearLayout) findViewById(R.id.mLinearLayout_rz);
        mLinearLayout_logout = (LinearLayout) findViewById(R.id.mLinearLayout_logout);

        mLinearLayout_ztset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setBgClickClor(v);
                final View view = ColorDialog.getView(getContext(), null);
                F.showCenterDialog(getContext(), view, new CallBackOnly() {
                    @Override
                    public void goReturnDo(Dialog mDialog) {
                        ((ColorDialog) view.getTag()).set(mDialog);
                    }
                });
            }
        });


        mLinearLayout_qkhc.setOnClickListener(com.mdx.framework.utility.Helper.delayClickLitener(this));
        mLinearLayout_gy.setOnClickListener(com.mdx.framework.utility.Helper.delayClickLitener(this));
        mLinearLayout_zh.setOnClickListener(com.mdx.framework.utility.Helper.delayClickLitener(this));
        mLinearLayout_tz.setOnClickListener(com.mdx.framework.utility.Helper.delayClickLitener(this));
        mLinearLayout_wr.setOnClickListener(com.mdx.framework.utility.Helper.delayClickLitener(this));
        mLinearLayout_rz.setOnClickListener(com.mdx.framework.utility.Helper.delayClickLitener(this));
        mLinearLayout_logout.setOnClickListener(com.mdx.framework.utility.Helper.delayClickLitener(this));
    }

    public void loaddata() {
        mRunnable = new Runnable() {
            @Override
            public void run() {
                mTextView_qkhc.setText(UnitConver
                        .getBytesSize(DataStoreCacheManage.FILEMANAGER.TEMPFILES.tempSize
                                + FileStoreCacheManage.FILEMANAGER.TEMPFILES.tempSize
                                + ImageStoreCacheManage.FILEMANAGER.TEMPFILES.tempSize + Frame.IMAGECACHE.getsize())
                        .toString());
            }
        };
        mTextView_qkhc.setText(UnitConver.getBytesSize(DataStoreCacheManage.FILEMANAGER.TEMPFILES.tempSize
                + FileStoreCacheManage.FILEMANAGER.TEMPFILES.tempSize
                + ImageStoreCacheManage.FILEMANAGER.TEMPFILES.tempSize + Frame.IMAGECACHE.getsize())
                .toString());

        try {
            mTextView_update.setText("v"
                    + getActivity().getPackageManager().getPackageInfo(
                    getActivity().getPackageName(), 0).versionName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(android.view.View v) {
        super.onClick(v);
        if (R.id.mLinearLayout_qkhc == v.getId()) {
            new AlertDialog.Builder(getActivity())
                    .setMessage("是否清除缓存")
                    .setPositiveButton("确定",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,
                                                    int which) {
                                    Frame.IMAGECACHE.clean();
                                    Frame.IMAGECACHE.cleanCache();
                                    DataStoreCacheManage.FILEMANAGER
                                            .clear(mRunnable);
                                    FileStoreCacheManage.FILEMANAGER
                                            .clear(mRunnable);
                                    ImageStoreCacheManage.FILEMANAGER
                                            .clear(mRunnable);
                                    dialog.dismiss();
                                }
                            }).setNegativeButton("取消", null).create().show();
        } else if (R.id.mLinearLayout_gy == v.getId()) {
            Helper.startActivity(getContext(), FrgGy.class, TitleAct.class);
        } else if (R.id.mLinearLayout_logout == v.getId()) {
            new AlertDialog.Builder(getActivity())
                    .setMessage("是否退出登录")
                    .setPositiveButton("确定",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,
                                                    int which) {
                                    LogOut();
                                }
                            }).setNegativeButton("取消", null).create().show();
        } else if (R.id.mLinearLayout_zh == v.getId()) {
            Helper.startActivity(getContext(), FrgZhyaq.class, TitleAct.class);
        } else if (R.id.mLinearLayout_tz == v.getId()) {
            Helper.startActivity(getContext(), FrgTz.class, TitleAct.class);
        } else if (R.id.mLinearLayout_wr == v.getId()) {
            Helper.startActivity(getContext(), FrgWr.class, TitleAct.class);
        } else if (R.id.mLinearLayout_rz == v.getId()) {
            Helper.startActivity(getContext(), FrgRizhi.class, TitleAct.class);
        }
    }

    public void LogOut() {
        clearJson();
//        RongIM.getInstance().logout();
        JPushInterface.stopPush(getContext());
        Helper.startActivity(getContext(), Intent.FLAG_ACTIVITY_CLEAR_TOP,
                FrgLogin.class, IndexAct.class);
    }

    @Override
    public void setActionBar(ActionBar actionBar, Context context) {
        super.setActionBar(actionBar, context);
        mHeadlayout.setTitle("设置");
    }
}