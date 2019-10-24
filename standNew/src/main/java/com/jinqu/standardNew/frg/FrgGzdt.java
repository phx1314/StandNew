//
//  FrgGzdt
//
//  Created by DELL on 2018-05-17 15:30:20
//  Copyright (c) DELL All rights reserved.


/**

 */

package com.jinqu.standardNew.frg;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.ab.view.listener.AbOnListViewListener;
import com.ab.view.pullview.AbPullListView;
import com.google.gson.Gson;
import com.jinqu.standardNew.R;
import com.jinqu.standardNew.ada.AdaGzdt;
import com.jinqu.standardNew.model.ModelgzdtList;
import com.mdx.framework.activity.TitleAct;
import com.mdx.framework.adapter.MAdapter;
import com.mdx.framework.utility.Helper;
import com.mdx.framework.widget.ActionBar;

import static com.jinqu.standardNew.F.METHOD_DESDISCUSS;


public class FrgGzdt extends BaseFrg {

    public AbPullListView mAbPullListView;
    public String TaskGroupId;

    @Override
    protected void create(Bundle savedInstanceState) {
        TaskGroupId = getActivity().getIntent().getStringExtra("TaskGroupId");
        setContentView(R.layout.frg_gzdt);
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
        mAbPullListView.setPostApiLoadParams(METHOD_DESDISCUSS, "taskGroupId", TaskGroupId);
        mAbPullListView.setAbOnListViewListener(new AbOnListViewListener() {
            @Override
            public MAdapter onSuccess(String methodName, String content) {
                ModelgzdtList mModelNewsList = new Gson().fromJson(content, ModelgzdtList.class);
                return new AdaGzdt(getContext(), mModelNewsList.rows);
            }
        });
    }

    @Override
    public void setActionBar(ActionBar actionBar, Context context) {
        super.setActionBar(actionBar, context);
        mHeadlayout.setTitle("工作动态");
        mHeadlayout.setRightBacgroud(R.drawable.add_dt);
        mHeadlayout.setRightOnclicker(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setBgClickClor(view);
                Helper.startActivity(getContext(), FrgCreateDt.class, TitleAct.class, "TaskGroupId", TaskGroupId);
            }
        });
    }
}