//
//  FrgGg
//
//  Created by DELL on 2018-05-08 11:17:04
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
import com.jinqu.standardNew.ada.AdaGg;
import com.jinqu.standardNew.model.ModelBd;
import com.mdx.framework.adapter.MAdapter;
import com.mdx.framework.widget.ActionBar;

import static com.jinqu.standardNew.F.METHOD_OANOTICE;


public class FrgGg extends BaseFrg {

    public AbPullListView mAbPullListView;

    @Override
    protected void create(Bundle savedInstanceState) {
        setContentView(R.layout.frg_gg);
        initView();
        loaddata();
    }


    private void initView() {
        findVMethod();
    }

    private void findVMethod() {
        mAbPullListView = (AbPullListView) findViewById(R.id.mAbPullListView);


    }

    public void loaddata() {
        mAbPullListView.setPostApiLoadParams(METHOD_OANOTICE);
        mAbPullListView.setAbOnListViewListener(new AbOnListViewListener() {
            @Override
            public MAdapter onSuccess(String methodName, String content) {
                ModelBd mModelNewsList = new Gson().fromJson(content, ModelBd.class);
                return new AdaGg(getContext(), mModelNewsList.rows);
            }
        });
    }

    @Override
    public void setActionBar(ActionBar actionBar, Context context) {
        super.setActionBar(actionBar, context);
        mHeadlayout.setTitle("系统公告");
    }
}