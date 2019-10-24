//
//  FrgGzList
//
//  Created by DELL on 2018-07-31 09:09:10
//  Copyright (c) DELL All rights reserved.


/**

 */

package com.jinqu.standardNew.frg;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import com.ab.view.listener.AbOnListViewListener;
import com.ab.view.pullview.AbPullListView;
import com.google.gson.Gson;
import com.jinqu.standardNew.R;
import com.jinqu.standardNew.ada.AdaGg;
import com.jinqu.standardNew.ada.AdaGzList;
import com.jinqu.standardNew.ada.AdaXmxxdjList;
import com.jinqu.standardNew.model.ModelBd;
import com.jinqu.standardNew.model.ModelGzList;
import com.jinqu.standardNew.model.ModelMenuConfig;
import com.jinqu.standardNew.model.ModelObj;
import com.jinqu.standardNew.view.FloatingActionButton;
import com.jinqu.standardNew.view.ScrollDirectionListener;
import com.mdx.framework.activity.NoTitleAct;
import com.mdx.framework.activity.TitleAct;
import com.mdx.framework.adapter.MAdapter;
import com.mdx.framework.utility.Helper;
import com.mdx.framework.widget.ActionBar;

import static com.jinqu.standardNew.F.getRightdata;


public class FrgFlowList extends BaseFrg {

    public AbPullListView mAbPullListView;
    public FloatingActionButton fab;
    public ModelGzList.RowsBean item;

    @Override
    protected void create(Bundle savedInstanceState) {
        item = (ModelGzList.RowsBean) getActivity().getIntent().getSerializableExtra("item");

        setContentView(R.layout.frg_gz_list);
        initView();
        loaddata();
    }

    private void initView() {
        findVMethod();
    }

    @Override
    public void disposeMsg(int type, Object obj) {
        switch (type) {
            case 0:
                mAbPullListView.pullLoad();
                break;
            case 888:
                mAbPullListView.setPostApiLoadParamsBase(item.mModelMenuConfig.grid.url.get(0), item.mModelMenuConfig.grid.queryParams[0], "queryInfo", obj.toString());
                break;
        }
    }


    private void findVMethod() {
        mAbPullListView = (AbPullListView) findViewById(R.id.mAbPullListView);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.attachToListView(mAbPullListView, new ScrollDirectionListener() {
            @Override
            public void onScrollDown() {
            }

            @Override
            public void onScrollUp() {
            }
        }, mAbPullListView);
        fab.setOnClickListener(Helper.delayClickLitener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ModelBd.RowsBean mRowsBean_f = new ModelBd.RowsBean();
                mRowsBean_f.mModelMenuConfig = item.mModelMenuConfig;
                mRowsBean_f.text = item.text;
                Helper.startActivity(getContext(), FrgPubWeb.class, TitleAct.class, "item", mRowsBean_f);
            }
        }));
        if (TextUtils.isEmpty(item.mModelMenuConfig.grid.addUrl)) {
            fab.setVisibility(View.GONE);
        } else {
            fab.setVisibility(View.VISIBLE);
        }
    }


    public void loaddata() {
        mAbPullListView.setPostApiLoadParamsBase(item.mModelMenuConfig.grid.url.get(0), item.mModelMenuConfig.grid.queryParams[0]);
        mAbPullListView.setAbOnListViewListener(new AbOnListViewListener() {
            @Override
            public MAdapter onSuccess(String methodName, String content) {
                ModelObj mModelObj = new Gson().fromJson(content, ModelObj.class);
                ModelBd mModelSystemList = new Gson().fromJson(content, ModelBd.class);
                for (int i = 0; i < mModelSystemList.rows.size(); i++) {
                    mModelSystemList.rows.get(i).FlowSummary = mModelSystemList.rows.get(i)._summary;
//                    mModelSystemList.rows.get(i).Id = mModelSystemList.rows.get(i).Id;
                    mModelSystemList.rows.get(i).text = item.text;
                    mModelSystemList.rows.get(i).MenuNameEng = item.MenuNameEng;
                    mModelSystemList.rows.get(i).mModelMenuConfig = new Gson().fromJson(getRightdata(item.MenuMobileConfig, mModelObj.rows.get(i)), ModelMenuConfig.class);
                    mModelSystemList.rows.get(i).obj = mModelObj.rows.get(i);
                    mModelSystemList.rows.get(i).MenuMobileConfig = item.MenuMobileConfig;
                }
                if (item.mModelMenuConfig.grid.listPage.equals("FlowList")) {
                    return new AdaGzList(getContext(), mModelSystemList.rows);
                } else if (item.mModelMenuConfig.grid.listPage.equals("ProjectList")) {
                    return new AdaXmxxdjList(getContext(), mModelSystemList.rows);
                } else if (item.mModelMenuConfig.grid.listPage.equals("BaseList")) {
                    return new AdaGg(getContext(), mModelSystemList.rows);
                } else {
                    return new AdaGg(getContext(), mModelSystemList.rows);
                }
            }
        });
    }


    @Override
    public void setActionBar(ActionBar actionBar, Context context) {
        super.setActionBar(actionBar, context);
        mHeadlayout.setTitle(item.text);
        mHeadlayout.setRightBacgroud(R.drawable.sousuo);
        mHeadlayout.setRightOnclicker(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Helper.startActivity(getContext(), FrgSousuo.class, NoTitleAct.class, "from", "FrgFlowList", "data", item.mModelMenuConfig.search);
            }
        });
    }
}