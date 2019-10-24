//
//  FrgGzdtF
//
//  Created by DELL on 2018-05-17 15:39:18
//  Copyright (c) DELL All rights reserved.


/**

 */

package com.jinqu.standardNew.frg;

import android.content.Context;
import android.os.Bundle;

import com.ab.view.listener.AbOnListViewListener;
import com.ab.view.pullview.AbPullListView;
import com.google.gson.Gson;
import com.jinqu.standardNew.R;
import com.jinqu.standardNew.ada.AdaGzdtF;
import com.jinqu.standardNew.model.ModelxmdtList;
import com.mdx.framework.adapter.MAdapter;
import com.mdx.framework.widget.ActionBar;

import static com.jinqu.standardNew.F.METHOD_PROJECTPLANLISTJSON;


public class FrgGzdtF extends BaseFrg {

    public AbPullListView mAbPullListView;


    @Override
    protected void create(Bundle savedInstanceState) {
        setContentView(R.layout.frg_gzdt_f);
        initView();
        loaddata();
    }

    @Override
    public void disposeMsg(int type, Object obj) {
        switch (type) {
            case 0:
                mAbPullListView.pullLoad();
                break;
        }
    }

    private void initView() {
        findVMethod();
    }

    private void findVMethod() {
        mAbPullListView = (AbPullListView) findViewById(R.id.mAbPullListView);


    }

    public void loaddata() {
        mAbPullListView.setPostApiLoadParams(METHOD_PROJECTPLANLISTJSON);
        mAbPullListView.setAbOnListViewListener(new AbOnListViewListener() {
            @Override
            public MAdapter onSuccess(String methodName, String content) {
                ModelxmdtList mModelNewsList = new Gson().fromJson(content, ModelxmdtList.class);
                return new AdaGzdtF(getContext(), mModelNewsList.rows);
            }
        });
    }

    @Override
    public void setActionBar(ActionBar actionBar, Context context) {
        super.setActionBar(actionBar, context);
        mHeadlayout.setTitle("工作动态");
    }
}