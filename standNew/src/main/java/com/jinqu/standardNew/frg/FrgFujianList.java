//
//  FrgFujianList
//
//  Created by DELL on 2018-05-15 17:21:38
//  Copyright (c) DELL All rights reserved.


/**

 */

package com.jinqu.standardNew.frg;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;

import com.ab.view.listener.AbOnListViewListener;
import com.ab.view.pullview.AbPullListView;
import com.jinqu.standardNew.R;
import com.jinqu.standardNew.ada.AdaFujianList;
import com.jinqu.standardNew.model.ModelFjList;
import com.mdx.framework.adapter.MAdapter;
import com.mdx.framework.widget.ActionBar;

import static com.jinqu.standardNew.F.METHOD_GETATTACHFILES;
import static com.jinqu.standardNew.F.json2Model;


public class FrgFujianList extends BaseFrg {

    public AbPullListView mAbPullListView;
    public String id;
    public String refTable;

    @Override
    protected void create(Bundle savedInstanceState) {
        id = getActivity().getIntent().getStringExtra("id");
        refTable = getActivity().getIntent().getStringExtra("refTable");
        setContentView(R.layout.frg_fujian_list);
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
        mAbPullListView.setPageSize(Integer.MAX_VALUE);
        mAbPullListView.setPostApiLoadParams(METHOD_GETATTACHFILES, "refID", TextUtils.isEmpty(id) ? "0" : id, "refTable", refTable);
        mAbPullListView.setAbOnListViewListener(new AbOnListViewListener() {

            @Override
            public MAdapter onSuccess(String methodName, String content) {
                ModelFjList mModelFjList = (ModelFjList) json2Model(content, ModelFjList.class);
                return new AdaFujianList(getContext(), mModelFjList.rows, refTable, false);
            }
        });
    }

    @Override
    public void setActionBar(ActionBar actionBar, Context context) {
        super.setActionBar(actionBar, context);
        mHeadlayout.setTitle("附件列表");
    }
}