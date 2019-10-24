//
//  FrgWodeWenjian
//
//  Created by DELL on 2017-04-20 11:48:03
//  Copyright (c) DELL All rights reserved.


/**

 */

package com.jinqu.standardNew.frg;

import android.content.Context;
import android.os.Bundle;

import com.jinqu.standardNew.R;
import com.jinqu.standardNew.ada.AdaYsltWenjian;
import com.mdx.framework.widget.ActionBar;
import com.mdx.framework.widget.MPageListView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static com.jinqu.standardNew.F.filePath;
import static com.jinqu.standardNew.F.mModelUsreLogin;


public class FrgWodeWenjian extends BaseFrg {

    public MPageListView mMPageListView;

    private List<File> items = new ArrayList<File>();// 存放名称
    private String rootPath = "/";

    @Override
    protected void create(Bundle savedInstanceState) {
        setContentView(R.layout.frg_wode_wenjian);
        initView();
        loaddata();
    }

    @Override
    public void disposeMsg(int type, Object obj) {
        switch (type) {
            case 0:
                getFileDir(filePath + mModelUsreLogin.UserInfo.EmpID);
                break;
        }
    }

    private void initView() {
        findVMethod();
    }

    private void findVMethod() {
        mMPageListView = (MPageListView) findViewById(R.id.mMPageListView);

    }

    public void loaddata() {
        getFileDir(filePath + mModelUsreLogin.UserInfo.EmpID);
    }

    public void getFileDir(String filePath) {
        items.clear();
        try {
            File f = new File(filePath);
            File[] files = f.listFiles();// 列出所有文件
            // 如果不是根目录,则列出返回根目录和上一目录选项
            if (!filePath.equals(rootPath)) {
            }
            // 将所有文件存入list中
            if (files != null) {
                int count = files.length;// 文件个数
                for (int i = 0; i < count; i++) {
                    File file = files[i];
                    if (file.isDirectory()) {
                        for (File file1 : file.listFiles()) {
                            items.add(file1);
                        }
                    } else {
                        items.add(file);
                    }
                }
            }
            mMPageListView.setAdapter(new AdaYsltWenjian(getContext(), items, "FrgWodeWenjian"));
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public void getFileDir(final String filePath, final String key) {
        items.clear();
        try {
            File f = new File(filePath);
            File[] files = f.listFiles();// 列出所有文件
            // 如果不是根目录,则列出返回根目录和上一目录选项
            if (!filePath.equals(rootPath)) {
            }
            // 将所有文件存入list中
            if (files != null) {
                int count = files.length;// 文件个数
                for (int i = 0; i < count; i++) {
                    File file = files[i];
                    if (file.isDirectory()) {
                        for (File file1 : file.listFiles()) {
                            if (file1.getName().toUpperCase()
                                    .contains(key.toUpperCase())) {
                                items.add(file1);
                            }
                        }
                    } else {
                        if (file.getName().toUpperCase()
                                .contains(key.toUpperCase())) {
                            items.add(file);
                        }
                    }
                }
            }
            ((AdaYsltWenjian) mMPageListView.getListAdapter()).clear();
            ((AdaYsltWenjian) mMPageListView.getListAdapter()).AddAll(items);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void onClick(android.view.View v) {

    }

    @Override
    public void setActionBar(ActionBar actionBar, Context context) {
        super.setActionBar(actionBar, context);
        mHeadlayout.setTitle("我的下载");
        mHeadlayout.setRightBacgroud(R.drawable.sousuo_bai);
    }
}