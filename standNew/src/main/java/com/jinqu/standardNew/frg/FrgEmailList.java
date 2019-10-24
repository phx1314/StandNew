//
//  FrgEmailList
//
//  Created by DELL on 2018-06-21 13:50:42
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
import com.jinqu.standardNew.ada.AdaEmailList;
import com.jinqu.standardNew.model.EntitySearch;
import com.jinqu.standardNew.model.ModelLaji;
import com.jinqu.standardNew.model.ModelMenuConfig;
import com.jinqu.standardNew.model.ModelSearch;
import com.jinqu.standardNew.model.ModelSearchGk;
import com.jinqu.standardNew.model.ModelYjList;
import com.jinqu.standardNew.pop.PopShowChoose;
import com.jinqu.standardNew.view.ScrollDirectionListener;
import com.mdx.framework.Frame;
import com.mdx.framework.activity.MActivityActionbar;
import com.mdx.framework.activity.NoTitleAct;
import com.mdx.framework.activity.TitleAct;
import com.mdx.framework.adapter.MAdapter;
import com.mdx.framework.utility.Helper;
import com.mdx.framework.widget.ActionBar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.jinqu.standardNew.F.METHOD_CAOGAO;
import static com.jinqu.standardNew.F.METHOD_MAILJUNKJSON;
import static com.jinqu.standardNew.F.METHOD_MAILRECEIVEJSON;
import static com.jinqu.standardNew.F.METHOD_MAILSENDJSON;
import static com.jinqu.standardNew.F.METHOD_OAMAILDEL;
import static com.jinqu.standardNew.F.METHOD_OAMAILDELCG;
import static com.jinqu.standardNew.F.METHOD_UpdateReadByIds;
import static com.jinqu.standardNew.F.mModelUsreLogin;


public class FrgEmailList extends BaseFrg {

    public AbPullListView mAbPullListView;
    public LinearLayout mLinearLayout_del;
    public String filterStr = "";
    public static boolean isShowDel;
    public TextView mTextView_del;
    public int type = 1;
    public String[] data = {"收件箱", "发件箱", "草稿箱", "垃圾箱"};
    public TextView mTextView_ydu;
    public com.jinqu.standardNew.view.FloatingActionButton fab;

    @Override
    protected void create(Bundle savedInstanceState) {
        type = getActivity().getIntent().getIntExtra("type", 1);
        ((MActivityActionbar) getActivity()).setSwipeBackEnable(false);
        isShowDel = false;
        setContentView(R.layout.frg_email_list);
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
                delEmail(obj.toString());
                break;
            case 111:
                loadPostNoShow(METHOD_UpdateReadByIds + obj.toString() + "_" + mModelUsreLogin.UserInfo.EmpID, METHOD_UpdateReadByIds);
                break;
            case 888:
                filterStr = obj.toString();
                reload();
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
                    this.type = 1;
                } else if (obj.toString().equals(data[1])) {
                    this.type = 2;
                } else if (obj.toString().equals(data[2])) {
                    this.type = 3;
                } else if (obj.toString().equals(data[3])) {
                    this.type = 4;
                }
                reload();
                break;
        }
    }

    public void filter(List<EntitySearch> mEntitySearchs) {
        if (type == 4) {
            ModelLaji mModelLaji = new ModelLaji();
            for (EntitySearch mEntitySearch : mEntitySearchs) {
                if (mEntitySearch.title.equals("开始时间")) {
                    mModelLaji.DateLower = mEntitySearch.value;
                }
                if (mEntitySearch.title.equals("结束时间")) {
                    mModelLaji.DateUpper = mEntitySearch.value;
                }
                if (mEntitySearch.title.equals("关键字")) {
                    mModelLaji.txtCondtion = mEntitySearch.value;
                }
            }
            mAbPullListView.setPostApiLoadParams(METHOD_MAILJUNKJSON, "queryInfo", "", "txtCondtion", mModelLaji.txtCondtion, "DateLower", mModelLaji.DateLower, "DateUpper", mModelLaji.DateUpper);
        }
    }

    private void initView() {
        findVMethod();
    }

    private void delEmail(String ids) {
        if (FrgEmailList.this.type == 1) {
            loadUrlPost(METHOD_OAMAILDEL, "ids", ids.contains(",") ? ids.substring(0, ids.length() - 1) : ids, "DelType", "false");
        } else if (FrgEmailList.this.type == 4) {
            loadUrlPost(METHOD_OAMAILDEL, "ids", ids.contains(",") ? ids.substring(0, ids.length() - 1) : ids, "DelType", "true");
        } else {
            loadUrlPost(METHOD_OAMAILDELCG, "ids", ids.contains(",") ? ids.substring(0, ids.length() - 1) : ids, "DelType", "false");
        }
    }

    private void reload() {
        switch (type) {
            case 1:
                mAbPullListView.setPostApiLoadParams(METHOD_MAILRECEIVEJSON, "queryInfo", filterStr);
                break;
            case 2:
                mAbPullListView.setPostApiLoadParams(METHOD_MAILSENDJSON, "queryInfo", filterStr);
                break;
            case 3:
                mAbPullListView.setPostApiLoadParams(METHOD_CAOGAO, "queryInfo", filterStr);
                break;
            case 4:
                mAbPullListView.setPostApiLoadParams(METHOD_MAILJUNKJSON, "queryInfo", filterStr);
                break;
        }
    }

    private void findVMethod() {
        mAbPullListView = (AbPullListView) findViewById(R.id.mAbPullListView);
        mLinearLayout_del = (LinearLayout) findViewById(R.id.mLinearLayout_del);
        mTextView_del = (TextView) findViewById(R.id.mTextView_del);
        mTextView_ydu = (TextView) findViewById(R.id.mTextView_ydu);
        fab = (com.jinqu.standardNew.view.FloatingActionButton) findViewById(R.id.fab);
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
                Helper.startActivity(getContext(), FrgSendEmail.class, TitleAct.class);
            }
        }));
        mTextView_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setBgClickClor(v);
                String ids = "";
                for (ModelYjList.RowsBean item : ((AdaEmailList) mAbPullListView.getmAdapter()).getList()) {
                    if (item.isChecked) {
                        ids += item.Id + ",";
                    }
                }
                if (!TextUtils.isEmpty(ids)) {
                    delEmail(ids);
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
                for (ModelYjList.RowsBean item : ((AdaEmailList) mAbPullListView.getmAdapter()).getList()) {
                    if (item.isChecked) {
                        ids += item.Id + "_" + mModelUsreLogin.UserInfo.EmpID + ",";
                    }
                }
                if (!TextUtils.isEmpty(ids)) {
                    loadPost(METHOD_UpdateReadByIds + ids.substring(0, ids.length() - 1), METHOD_UpdateReadByIds);
                } else {
                    Helper.toast("请选择", getContext());
                }
            }
        });
    }

    public void loaddata() {
        mAbPullListView.setAbOnListViewListener(new AbOnListViewListener() {
            @Override
            public MAdapter onSuccess(String methodName, String content) {
                ModelYjList mModelSystemList = new Gson().fromJson(content, ModelYjList.class);
                return new AdaEmailList(getContext(), mModelSystemList.rows, type);
            }
        });
        reload();
    }

    public void openDEL(boolean isOpen) {
        if (isOpen) {
            isShowDel = true;
            mLinearLayout_del.setVisibility(View.VISIBLE);
//            fab.hide();
            fab.setVisibility(View.GONE);
            mHeadlayout.toogle(2);
        } else {
            isShowDel = false;
            mLinearLayout_del.setVisibility(View.GONE);
//            fab.show();
            fab.setVisibility(View.VISIBLE);
            mHeadlayout.toogle(1);
        }
        mAbPullListView.getmAdapter().notifyDataSetChanged();
        jiSuanCount();
    }

    public void jiSuanCount() {
        int count = 0;
        for (ModelYjList.RowsBean item : ((AdaEmailList) mAbPullListView.getmAdapter()).getList()) {
            if (item.isChecked) {
                count++;
            }
        }
        mHeadlayout.mTextView_title.setText(count > 0 ? "已选择" + count + "项" : "请选择");
    }

    @Override
    public void onSuccess(String methodName, String content) {
        if (methodName.equals(METHOD_OAMAILDEL) || methodName.equals(METHOD_OAMAILDELCG) || methodName.equals(METHOD_UpdateReadByIds)) {
            mAbPullListView.pullLoad();
            Frame.HANDLES.sentAll("FrgWd", 0, null);
        }
    }

    @Override
    public void setActionBar(ActionBar actionBar, Context context) {
        super.setActionBar(actionBar, context);
        mHeadlayout.setTitle(data[type - 1]);
        mHeadlayout.tv_title.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, R.drawable.xia, 0);
        mHeadlayout.tv_title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopShowChoose mPopShowChoose = new PopShowChoose(getContext(), mHeadlayout, Arrays.asList(data), mHeadlayout.tv_title.getText().toString(), "FrgEmailList");
                mPopShowChoose.show();
            }
        });
        mHeadlayout.setRightBacgroud(R.drawable.sousuo);
        mHeadlayout.setRightOnclicker(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {




                List<ModelMenuConfig.SearchBean> search = new ArrayList<ModelMenuConfig.SearchBean>();
                ModelMenuConfig.SearchBean s1 = new ModelMenuConfig.SearchBean();
                s1.type = "text";
                s1.text = "请输入标题";
                s1.sqlstring =
                        "{\"isGroup\":false,\"list\":[{\"Key\":\"FK_OaMailRead_Id.MailTitle,FK_OaMailRead_Id.CreatorEmpName\",\"Contract\":\"like\",\"Value\":\"#{value}\"}]}";
                ModelMenuConfig.SearchBean s2 = new ModelMenuConfig.SearchBean();
                s2.type = "time_ymd";
                s2.text = "请选择开始时间";
                s2.sqlstring = "{\"isGroup\":false,\"list\":[{\"Key\":\"FK_OaMailRead_Id.MailDate\",\"Contract\":\">=\",\"filedType\":\"Date\",\"Value\":\"#{value}\"}]}";
                ModelMenuConfig.SearchBean s3 = new ModelMenuConfig.SearchBean();
                s3.type = "time_ymd";
                s3.text = "请选择结束时间";
                s3.sqlstring = "{\"isGroup\":false,\"list\":[{\"Key\":\"FK_OaMailRead_Id.MailDate\",\"Contract\":\"<=\",\"filedType\":\"Date\",\"Value\":\"#{value}\"}]}";
                search.add(s1);
                search.add(s2);
                search.add(s3);
                Helper.startActivity(getContext(), FrgSousuo.class, NoTitleAct.class, "from", "FrgEmailList", "data", search);
            }
        });
        mHeadlayout.setCancelOnclicker(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (ModelYjList.RowsBean item : ((AdaEmailList) mAbPullListView.getmAdapter()).getList()) {
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
                for (ModelYjList.RowsBean item : ((AdaEmailList) mAbPullListView.getmAdapter()).getList()) {
                    item.isChecked = isAllCHecked;
                }
                mAbPullListView.getmAdapter().notifyDataSetChanged();
                jiSuanCount();
            }
        });
    }
}