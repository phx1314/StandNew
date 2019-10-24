//
//  FrgXtxx
//
//  Created by DELL on 2018-07-19 13:20:57
//  Copyright (c) DELL All rights reserved.


/**

 */

package com.jinqu.standardNew.frg;

import android.app.ActivityOptions;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ab.view.listener.AbOnListViewListener;
import com.ab.view.pullview.AbPullListView;
import com.google.gson.Gson;
import com.jinqu.standardNew.R;
import com.jinqu.standardNew.ada.AdaXtxx;
import com.jinqu.standardNew.model.EntitySearch;
import com.jinqu.standardNew.model.ModelSystemList;
import com.jinqu.standardNew.pop.PopShowChoose;
import com.mdx.framework.activity.MActivityActionbar;
import com.mdx.framework.activity.NoTitleAct;
import com.mdx.framework.adapter.MAdapter;
import com.mdx.framework.utility.Helper;
import com.mdx.framework.widget.ActionBar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.jinqu.standardNew.F.METHOD_DeleteRead;
import static com.jinqu.standardNew.F.METHOD_GETLIST;
import static com.jinqu.standardNew.F.METHOD_SetReadStatus;


public class FrgXtxx extends BaseFrg {

    public AbPullListView mAbPullListView;
    public TextView mTextView_del;
    public TextView mTextView_ydu;
    public LinearLayout mLinearLayout_del;
    public String[] data = {"未读系统消息", "已读系统消息"};
    public String status = "0";
    public static boolean isShowDel;

    @Override
    protected void create(Bundle savedInstanceState) {
        ((MActivityActionbar) getActivity()).setSwipeBackEnable(false);
        setContentView(R.layout.frg_xtxx);
        initView();
        loaddata();
    }

    @Override
    public void disposeMsg(int type, Object obj) {
        switch (type) {
            case 0:
                mAbPullListView.pullLoad();
                break;
            case 110:
                loadUrlPost(METHOD_DeleteRead, "idSet", obj.toString());
                break;
            case 111:
                loadUrlPostNoShow(METHOD_SetReadStatus, "idSet", obj.toString(), "status", 1);
                break;
            case 888:
                List<EntitySearch> mEntitySearchs = (List<EntitySearch>) obj;
                filter(mEntitySearchs);
                break;
            case 120:
                openDEL(true);
                break;
            case 100:
                jiSuanCount();
                break;
            case 101:
                mHeadlayout.setTitle(obj.toString());
                if (obj.toString().equals(data[0])) {
                    status = "0";
                } else if (obj.toString().equals(data[1])) {
                    status = "1";
                }
                mAbPullListView.setPostApiLoadParams(METHOD_GETLIST, "status", status);
                break;
        }
    }

    public void jiSuanCount() {
        int count = 0;
        for (ModelSystemList.RowsBean item : ((AdaXtxx) mAbPullListView.getmAdapter()).getList()) {
            if (item.isChecked) {
                count++;
            }
        }
        mHeadlayout.mTextView_title.setText(count > 0 ? "已选择" + count + "项" : "请选择");
    }

    public void openDEL(boolean isOpen) {
        if (isOpen) {
            isShowDel = true;
            mLinearLayout_del.setVisibility(View.VISIBLE);
            mHeadlayout.toogle(2);
        } else {
            isShowDel = false;
            mLinearLayout_del.setVisibility(View.GONE);
            mHeadlayout.toogle(1);
        }
        mAbPullListView.getmAdapter().notifyDataSetChanged();
        jiSuanCount();
    }

    public void filter(List<EntitySearch> mEntitySearchs) {
        String text = "";
        String starttime = "";
        String endtime = "";
        for (EntitySearch mEntitySearch : mEntitySearchs) {
            if (mEntitySearch.title.equals("开始时间")) {
                starttime = mEntitySearch.value;
            }
            if (mEntitySearch.title.equals("结束时间")) {
                endtime = mEntitySearch.value;
            }
            if (mEntitySearch.title.equals("关键字")) {
                text = mEntitySearch.value;
            }
        }
        mAbPullListView.setPostApiLoadParams(METHOD_GETLIST, "status", status, "text", text, "starttime", starttime, "endtime", endtime);

    }

    private void initView() {
        findVMethod();
    }

    private void findVMethod() {
        mAbPullListView = (AbPullListView) findViewById(R.id.mAbPullListView);
        mTextView_del = (TextView) findViewById(R.id.mTextView_del);
        mTextView_ydu = (TextView) findViewById(R.id.mTextView_ydu);
        mLinearLayout_del = (LinearLayout) findViewById(R.id.mLinearLayout_del);

        mTextView_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setBgClickClor(v);
                String ids = "";
                for (ModelSystemList.RowsBean item : ((AdaXtxx) mAbPullListView.getmAdapter()).getList()) {
                    if (item.isChecked) {
                        ids += item.Id + ",";
                    }
                }
                if (!TextUtils.isEmpty(ids)) {
                    loadUrlPost(METHOD_DeleteRead, "idSet", ids.toString().substring(0, ids.toString().length() - 1));
                } else {
                    Helper.toast("请选择", getContext());
                }
            }
        });
        mTextView_ydu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setBgClickClor(v);
                String ids = "";
                for (ModelSystemList.RowsBean item : ((AdaXtxx) mAbPullListView.getmAdapter()).getList()) {
                    if (item.isChecked) {
                        ids += item.Id + ",";
                    }
                }
                if (!TextUtils.isEmpty(ids)) {
                    loadUrlPostNoShow(METHOD_SetReadStatus, "idSet", ids.toString().substring(0, ids.toString().length() - 1), "status", 1);
                } else {
                    Helper.toast("请选择", getContext());
                }
            }
        });
    }

    public void loaddata() {
        mAbPullListView.setPostApiLoadParams(METHOD_GETLIST, "status", status);
        mAbPullListView.setAbOnListViewListener(new AbOnListViewListener() {
            @Override
            public MAdapter onSuccess(String methodName, String content) {
                ModelSystemList mModelSystemList = new Gson().fromJson(content, ModelSystemList.class);
                return new AdaXtxx(getContext(), mModelSystemList.rows);
            }
        });
    }

    @Override
    public void onSuccess(String methodName, String content) {
        if (methodName.equals(METHOD_DeleteRead) || methodName.equals(METHOD_SetReadStatus)) {
            mAbPullListView.pullLoad();
        }
    }

    @Override
    public void setActionBar(ActionBar actionBar, Context context) {
        super.setActionBar(actionBar, context);
        mHeadlayout.setTitle(data[0]);
        mHeadlayout.tv_title.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.xia, 0);
        mHeadlayout.tv_title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopShowChoose mPopShowChoose = new PopShowChoose(getContext(), mHeadlayout, Arrays.asList(data), mHeadlayout.tv_title.getText().toString(), "FrgXtxx");
                mPopShowChoose.show();
            }
        });
        mHeadlayout.setRightBacgroud(R.drawable.sousuo);
        mHeadlayout.setRightOnclicker(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                List<EntitySearch> data = new ArrayList();
                EntitySearch mEntitySearch = new EntitySearch();
                mEntitySearch.title = "开始时间";
                EntitySearch mEntitySearch2 = new EntitySearch();
                mEntitySearch2.title = "结束时间";
                data.add(mEntitySearch);
                data.add(mEntitySearch2);
                com.jinqu.standardNew.util.Helper.startActivity(getContext(), FrgSousuo.class, NoTitleAct.class, ActivityOptions.makeSceneTransitionAnimation
                        (getActivity(), v, "myButton1")
                        .toBundle(), "from", "FrgXtxx", "data", data, "key_hint", "筛选字段");
            }
        });
        mHeadlayout.setCancelOnclicker(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (ModelSystemList.RowsBean item : ((AdaXtxx) mAbPullListView.getmAdapter()).getList()) {
                    item.isChecked = false;
                }
                openDEL(false);
            }
        });
        mHeadlayout.setToggleQxOnclicker(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isAllCHecked;
                if (mHeadlayout.mTextView_all.getText().toString().equals("全选")) {
                    isAllCHecked = true;
                    mHeadlayout.mTextView_all.setText("全不选");
                } else {
                    isAllCHecked = false;
                    mHeadlayout.mTextView_all.setText("全选");
                }
                for (ModelSystemList.RowsBean item : ((AdaXtxx) mAbPullListView.getmAdapter()).getList()) {
                    item.isChecked = isAllCHecked;
                }
                mAbPullListView.getmAdapter().notifyDataSetChanged();
                jiSuanCount();
            }
        });
    }


}