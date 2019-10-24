//
//  FrgGy
//
//  Created by DELL on 2018-07-16 10:52:47
//  Copyright (c) DELL All rights reserved.


/**

 */

package com.jinqu.standardNew.frg;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.framewidget.frg.FrgPubBianJi;
import com.jinqu.standardNew.R;
import com.mdx.framework.activity.TitleAct;
import com.mdx.framework.config.BaseConfig;
import com.mdx.framework.utility.Helper;
import com.mdx.framework.widget.ActionBar;
import com.pgyersdk.javabean.AppBean;
import com.pgyersdk.update.PgyUpdateManager;
import com.pgyersdk.update.UpdateManagerListener;


public class FrgGy extends BaseFrg {

    public ImageView imageView;
    public LinearLayout mLinearLayout_1;
    public LinearLayout mLinearLayout_2;
    public TextView mTextView_name;
    public TextView mTextView_update;


    @Override
    protected void create(Bundle savedInstanceState) {
        setContentView(R.layout.frg_gy);
        initView();
        loaddata();
    }

    private void initView() {
        findVMethod();
    }

    private void findVMethod() {
        imageView = (ImageView) findViewById(R.id.imageView);
        mLinearLayout_1 = (LinearLayout) findViewById(R.id.mLinearLayout_1);
        mLinearLayout_2 = (LinearLayout) findViewById(R.id.mLinearLayout_2);
        mTextView_name = (TextView) findViewById(R.id.mTextView_name);
        mTextView_update = (TextView) findViewById(R.id.mTextView_update);
        try {
            mTextView_name.setText(getResources().getString(R.string.app_name) + "  v"
                    + getActivity().getPackageManager().getPackageInfo(
                    getActivity().getPackageName(), 0).versionName);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        mLinearLayout_1.setOnClickListener(com.mdx.framework.utility.Helper.delayClickLitener(this));
        mLinearLayout_2.setOnClickListener(com.mdx.framework.utility.Helper.delayClickLitener(this));
        mTextView_update.setText(""
                + Build.SERIAL);
        imageView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Helper.startActivity(getContext(), FrgPubBianJi.class,
                        TitleAct.class, "from", "FrgSet", "EDT", 201,
                        "data", BaseConfig.getUri().split("://")[1], "hint", "设置ip");
                return true;
            }
        });
    }

    public void loaddata() {

    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        if (R.id.mLinearLayout_2 == v.getId()) {
//            loadNoCookie(METHOD_UPDATE, false);
            PgyUpdateManager.register(getActivity(),
                    new UpdateManagerListener() {
                        @Override
                        public void onUpdateAvailable(final String result) {
                            try { // 将新版本信息封装到AppBean中
                                final AppBean appBean = getAppBeanFromString(result);
                                new AlertDialog.Builder(getContext())
                                        .setTitle("版本更新")
                                        .setMessage("检查到新版本，是否更新")
                                        .setNegativeButton(
                                                "确定",
                                                new DialogInterface.OnClickListener() {

                                                    @Override
                                                    public void onClick(
                                                            DialogInterface dialog,
                                                            int which) {
                                                        startDownloadTask(
                                                                getActivity(),
                                                                appBean.getDownloadURL());
                                                    }
                                                }).show();

                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                        }

                        @Override
                        public void onNoUpdateAvailable() {
                        }
                    });
        }
    }

    @Override
    public void setActionBar(ActionBar actionBar, Context context) {
        super.setActionBar(actionBar, context);
        mHeadlayout.setTitle("关于我们");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        PgyUpdateManager.unregister();
    }
}