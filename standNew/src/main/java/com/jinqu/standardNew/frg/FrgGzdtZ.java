//
//  FrgGzdtZ
//
//  Created by DELL on 2018-05-17 15:39:18
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
import com.jinqu.standardNew.ada.AdaGzdtZ;
import com.jinqu.standardNew.item.Gzdt;
import com.jinqu.standardNew.model.ModelDtetail;
import com.jinqu.standardNew.model.ModelgzdtList;
import com.mdx.framework.Frame;
import com.mdx.framework.activity.TitleAct;
import com.mdx.framework.adapter.MAdapter;
import com.mdx.framework.utility.Helper;
import com.mdx.framework.widget.ActionBar;

import static com.jinqu.standardNew.F.METHOD_GETREPLYDATA;


public class FrgGzdtZ extends BaseFrg {

    public AbPullListView mAbPullListView;
    public View view_top;
    public ModelgzdtList.RowsBean item;

    @Override
    protected void create(Bundle savedInstanceState) {
        item = (ModelgzdtList.RowsBean) getActivity().getIntent().getSerializableExtra("item");
        setContentView(R.layout.frg_gzdt_z);
        initView();
        loaddata();
    }

    @Override
    public void disposeMsg(int type, Object obj) {
        switch (type) {
            case 0:
                if (Boolean.valueOf(obj.toString())) {
                    item.ReplyCount++;
                } else {
                    item.ReplyCount--;
                }
                mAbPullListView.pullLoad();
                Frame.HANDLES.sentAll("FrgGzdt", 0, null);
                break;
            case 1:
                finish();
                break;
        }
    }

    private void initView() {
        findVMethod();
    }

    private void findVMethod() {
        mAbPullListView = (AbPullListView) findViewById(R.id.mAbPullListView);
        view_top = Gzdt.getView(getContext(), null);
        mAbPullListView.addHeaderView(view_top);
        mAbPullListView.setPageIndex_key("pageIndex");
        mAbPullListView.setPageSize_key("pageSize");
        mAbPullListView.setPageIndex(0);
        mAbPullListView.setPostApiLoadParams(METHOD_GETREPLYDATA, "talkId", item.Id);
        mAbPullListView.setAbOnListViewListener(new AbOnListViewListener() {
            @Override
            public MAdapter onSuccess(String methodName, String content) {
                ((Gzdt) view_top.getTag()).set(item);
                ModelDtetail mModelNewsList = new Gson().fromJson(content, ModelDtetail.class);
                return new AdaGzdtZ(getContext(), mModelNewsList.rows);
            }
        });
    }

    public void loaddata() {

    }

    @Override
    public void setActionBar(ActionBar actionBar, Context context) {
        super.setActionBar(actionBar, context);
        mHeadlayout.setTitle("动态详情");
        mHeadlayout.setRightBacgroud(R.drawable.add_dt);
        mHeadlayout.setRightOnclicker(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setBgClickClor(view);
                Helper.startActivity(getContext(), FrgCreateDtpl.class, TitleAct.class, "item", item);
            }
        });
    }
}