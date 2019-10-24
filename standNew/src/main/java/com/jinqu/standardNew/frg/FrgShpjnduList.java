//
//  FrgShpjnduList
//
//  Created by DELL on 2017-05-09 15:58:42
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
import com.jinqu.standardNew.ada.AdaShpjnduList;
import com.jinqu.standardNew.model.ModelShPjdList;
import com.mdx.framework.adapter.MAdapter;
import com.mdx.framework.widget.ActionBar;

import java.util.ArrayList;

import static com.jinqu.standardNew.F.METHOD_GETFLOWEXE;


public class FrgShpjnduList extends BaseFrg {

    public AbPullListView mAbPullListView;
    public String id;

    @Override
    protected void create(Bundle savedInstanceState) {
        id = getActivity().getIntent().getStringExtra("id");
        setContentView(R.layout.frg_shpjndu_list);
        initView();
        loaddata();
    }

    private void initView() {
        findVMethod();
    }

    private void findVMethod() {
        mAbPullListView = (AbPullListView) findViewById(R.id.mAbPullListView);


    }

    public void loaddata() { mAbPullListView.setPageSize(Integer.MAX_VALUE);
        mAbPullListView.setPostApiLoadParams(METHOD_GETFLOWEXE, "flowID", id);
        mAbPullListView.setAdapter(new AdaShpjnduList(getContext(), new ArrayList<ModelShPjdList.RowsBean>()));
        mAbPullListView.setAbOnListViewListener(new AbOnListViewListener() {
            @Override
            public MAdapter onSuccess(String methodName, String content) {
                ModelShPjdList mModelNewsList = new Gson().fromJson(content, ModelShPjdList.class);
                return new AdaShpjnduList(getContext(), mModelNewsList.rows);
            }
        });
    }

    @Override
    public void setActionBar(ActionBar actionBar, Context context) {
        super.setActionBar(actionBar, context);
        mHeadlayout.setTitle("审批进度");
    }
}