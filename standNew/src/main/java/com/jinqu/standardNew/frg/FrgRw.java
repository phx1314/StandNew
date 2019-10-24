//
//  FrgRw
//
//  Created by DELL on 2018-05-08 11:17:04
//  Copyright (c) DELL All rights reserved.


/**

 */

package com.jinqu.standardNew.frg;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ab.view.listener.AbOnListViewListener;
import com.ab.view.pullview.AbPullListView;
import com.google.gson.Gson;
import com.jinqu.standardNew.R;
import com.jinqu.standardNew.ada.AdaRw;
import com.jinqu.standardNew.model.EntitySearch;
import com.jinqu.standardNew.model.ModelRw;
import com.jinqu.standardNew.model.ModelSearchGk;
import com.jinqu.standardNew.pop.PopShowChoose;
import com.mdx.framework.adapter.MAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.jinqu.standardNew.F.METHOD_TASKREMINDLISTJSON;


public class FrgRw extends BaseFrg {

    public AbPullListView mAbPullListView;
    public TextView mTextView_type1;
    public LinearLayout mLinearLayout1;
    public TextView mTextView_type;
    public LinearLayout mLinearLayout;
    public LinearLayout mLinearLayout_top;
    public String[] data = {"忽略提交情况", "未上传文件", "已上传文件未提交", "已部分提交", "已全部提交"};
    public String[] data1 = {"待办任务", "已完任务", "回退任务", "安排任务"};
    public String itemStatus = "Now";
    public String fileStatus = "-1";

    @Override
    protected void create(Bundle savedInstanceState) {
        setContentView(R.layout.frg_rw);
        initView();
        loaddata();
    }

    @Override
    public void disposeMsg(int type, Object obj) {
        switch (type) {
            case 101:
                if (obj.toString().equals("待办任务")) {
                    itemStatus = "Now";
                    mTextView_type1.setText(obj.toString());
                } else if (obj.toString().equals("已完任务")) {
                    itemStatus = "Finished";
                    mTextView_type1.setText(obj.toString());
                } else if (obj.toString().equals("回退任务")) {
                    itemStatus = "Back";
                    mTextView_type1.setText(obj.toString());
                } else if (obj.toString().equals("安排任务")) {
                    itemStatus = "Waiting";
                    mTextView_type1.setText(obj.toString());
                } else if (obj.toString().equals("忽略提交情况")) {
                    fileStatus = "-1";
                    mTextView_type.setText(obj.toString());
                } else if (obj.toString().equals("未上传文件")) {
                    fileStatus = "0";
                    mTextView_type.setText(obj.toString());
                } else if (obj.toString().equals("已上传文件未提交")) {
                    fileStatus = "1";
                    mTextView_type.setText(obj.toString());
                } else if (obj.toString().equals("已部分提交")) {
                    fileStatus = "2";
                    mTextView_type.setText(obj.toString());
                } else if (obj.toString().equals("已全部提交")) {
                    fileStatus = "3";
                    mTextView_type.setText(obj.toString());
                }
                mAbPullListView.setPostApiLoadParams(METHOD_TASKREMINDLISTJSON, "showAll", "0", "itemStatus", itemStatus, "fileStatus", fileStatus);
                break;
            case 10086:
                mAbPullListView.pullLoad();
                break;
            case 888:
                List<EntitySearch> mEntitySearchs = (List<EntitySearch>) obj;
                filter(mEntitySearchs);
                break;
        }
    }

    public void filter(List<EntitySearch> mEntitySearchs) {
        List<Object> mModelSearchs = new ArrayList<>();
        for (EntitySearch mEntitySearch : mEntitySearchs) {
            if (mEntitySearch.title.equals("关键字")) {
                ModelSearchGk mModelSearchGk = new ModelSearchGk();
                ModelSearchGk.ListBean mListBean = new ModelSearchGk.ListBean();
                mListBean.Value = mEntitySearch.value;
                mListBean.Key = "ItemPath1,ItemPath2";
                mListBean.Contract = "like";
                mModelSearchGk.list.add(mListBean);
                mModelSearchs.add(mModelSearchGk);
                mAbPullListView.setPostApiLoadParams(METHOD_TASKREMINDLISTJSON, "showAll", "0", "itemStatus", itemStatus, "fileStatus", fileStatus, "queryInfo", new Gson().toJson(mModelSearchs));
            }
        }
    }

    private void initView() {
        findVMethod();
    }

    private void findVMethod() {
        mAbPullListView = (AbPullListView) findViewById(R.id.mAbPullListView);
        mTextView_type1 = (TextView) findViewById(R.id.mTextView_type1);
        mLinearLayout1 = (LinearLayout) findViewById(R.id.mLinearLayout1);
        mTextView_type = (TextView) findViewById(R.id.mTextView_type);
        mLinearLayout = (LinearLayout) findViewById(R.id.mLinearLayout);
        mLinearLayout_top = (LinearLayout) findViewById(R.id.mLinearLayout_top);


        mLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopShowChoose mPopShowChoose = new PopShowChoose(getContext(), mLinearLayout_top, Arrays.asList(data), mTextView_type.getText().toString(), "FrgRw");
                mPopShowChoose.show();
            }
        });
        mLinearLayout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopShowChoose mPopShowChoose = new PopShowChoose(getContext(), mLinearLayout_top, Arrays.asList(data1), mTextView_type1.getText().toString(), "FrgRw");
                mPopShowChoose.show();
            }
        });
    }

    public void loaddata() {
        mAbPullListView.setPostApiLoadParams(METHOD_TASKREMINDLISTJSON, "showAll", "0", "itemStatus", itemStatus, "fileStatus", fileStatus);
        mAbPullListView.setAbOnListViewListener(new AbOnListViewListener() {
            @Override
            public MAdapter onSuccess(String methodName, String content) {
                ModelRw mModeBd = new Gson().fromJson(content, ModelRw.class);
                return new AdaRw(getContext(), mModeBd.rows);
            }
        });
    }


}