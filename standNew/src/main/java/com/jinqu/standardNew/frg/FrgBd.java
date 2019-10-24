//
//  FrgBd
//
//  Created by DELL on 2018-05-08 11:17:04
//  Copyright (c) DELL All rights reserved.


/**

 */

package com.jinqu.standardNew.frg;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ab.view.listener.AbOnListViewListener;
import com.ab.view.pullview.AbPullListView;
import com.google.gson.Gson;
import com.jinqu.standardNew.R;
import com.jinqu.standardNew.ada.AdaBd;
import com.jinqu.standardNew.model.EntitySearch;
import com.jinqu.standardNew.model.ModelBd;
import com.jinqu.standardNew.model.ModelMenuConfig;
import com.jinqu.standardNew.model.ModelObj;
import com.jinqu.standardNew.model.ModelSearchGk;
import com.jinqu.standardNew.pop.PopShowChoose;
import com.mdx.framework.adapter.MAdapter;

import java.util.Arrays;
import java.util.List;

import static com.jinqu.standardNew.F.METHOD_GETTODOLIST;
import static com.jinqu.standardNew.F.getRightdata;
import static com.jinqu.standardNew.F.json2Model;
import static com.jinqu.standardNew.frg.FrgHome.mModelGzLists;


public class FrgBd extends BaseFrg {

    public TextView mTextView_type;
    public AbPullListView mAbPullListView;
    public String statusID = "1";
    public String modular = "0";
    public LinearLayout mLinearLayout;
    public String[] data = {"未审批", "已审批"};
    public String[] data1 = {"全部", "项目表单", "办公表单"};
    public TextView mTextView_type1;
    public LinearLayout mLinearLayout1;
    public LinearLayout mLinearLayout_top;


    @Override
    protected void create(Bundle savedInstanceState) {
        setContentView(R.layout.frg_bd);
        initView();
        loaddata();
    }

    @Override
    public void disposeMsg(int type, Object obj) {
        switch (type) {
            case 101:
                if (obj.toString().equals("未审批")) {
                    statusID = "1";
                    mTextView_type.setText(obj.toString());
                } else if (obj.toString().equals("已审批")) {
                    statusID = "2";
                    mTextView_type.setText(obj.toString());
                } else if (obj.toString().equals("全部")) {
                    modular = "0";
                    mTextView_type1.setText(obj.toString());
                } else if (obj.toString().equals("项目表单")) {
                    modular = "1";
                    mTextView_type1.setText(obj.toString());
                } else if (obj.toString().equals("办公表单")) {
                    modular = "2";
                    mTextView_type1.setText(obj.toString());
                }
                mAbPullListView.setPostApiLoadParams(METHOD_GETTODOLIST, "statusID", statusID, "modular", modular, "app", "1");
                break;
            case 10086:
                mAbPullListView.pullLoad();
                break;
            case 888:
                List<ModelSearchGk> objs = Arrays.asList((ModelSearchGk[]) json2Model(obj.toString(), ModelSearchGk[].class));
                for (ModelSearchGk mEntitySearch : objs) {
                    mAbPullListView.setPostApiLoadParams(METHOD_GETTODOLIST, "statusID", statusID, "modular", modular, "text", mEntitySearch.list.get(0).Value, "app", "1");
                }
                break;
        }
    }

    public void filter(List<EntitySearch> mEntitySearchs) {
        for (EntitySearch mEntitySearch : mEntitySearchs) {
            if (mEntitySearch.title.equals("关键字")) {
                mAbPullListView.setPostApiLoadParams(METHOD_GETTODOLIST, "statusID", statusID, "modular", modular, "text", mEntitySearch.value, "app", "1");
            }
        }
    }

    private void initView() {
        findVMethod();
    }

    private void findVMethod() {
        mTextView_type = (TextView) findViewById(R.id.mTextView_type);
        mAbPullListView = (AbPullListView) findViewById(R.id.mAbPullListView);
        mLinearLayout = (LinearLayout) findViewById(R.id.mLinearLayout);
        mTextView_type1 = (TextView) findViewById(R.id.mTextView_type1);
        mLinearLayout1 = (LinearLayout) findViewById(R.id.mLinearLayout1);
        mLinearLayout_top = (LinearLayout) findViewById(R.id.mLinearLayout_top);

        mLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopShowChoose mPopShowChoose = new PopShowChoose(getContext(), mLinearLayout_top, Arrays.asList(data), mTextView_type.getText().toString(), "FrgBd");
                mPopShowChoose.show();
            }
        });
        mLinearLayout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopShowChoose mPopShowChoose = new PopShowChoose(getContext(), mLinearLayout_top, Arrays.asList(data1), mTextView_type1.getText().toString(), "FrgBd");
                mPopShowChoose.show();
            }
        });
    }

    public void loaddata() {

        mAbPullListView.setPostApiLoadParams(METHOD_GETTODOLIST, "statusID", statusID, "modular", modular, "app", "1");
        mAbPullListView.setAbOnListViewListener(new AbOnListViewListener() {
            @Override
            public MAdapter onSuccess(String methodName, String content) {
                String content1 = content.replace("FlowRefID", "Id");
                ModelObj mModelObj = new Gson().fromJson(content1, ModelObj.class);
                ModelBd mModelBd = new Gson().fromJson(content1, ModelBd.class);
                for (int i = 0; i < mModelBd.rows.size(); i++) {
                    for (int j = 0; j < mModelGzLists.size(); j++) {
                        if (mModelGzLists.get(j).MenuMobileConfig.contains(mModelBd.rows.get(i).FlowRefTable) && mModelBd.rows.get(i).FlowRefTable.equals(mModelGzLists.get(j).mModelMenuConfig.flow.refTable)) {
                            Log.i("fz", mModelBd.rows.get(i).FlowRefTable);
                            mModelBd.rows.get(i).text = mModelGzLists.get(j).text;
                            mModelBd.rows.get(i).MenuNameEng = mModelGzLists.get(j).MenuNameEng;
                            mModelBd.rows.get(i).mModelMenuConfig = new Gson().fromJson(getRightdata(mModelGzLists.get(j).MenuMobileConfig, mModelObj.rows.get(i)), ModelMenuConfig.class);
                            break;
                        }
                    }
                }
                return new AdaBd(getContext(), mModelBd.rows, statusID);
            }
        });
    }


}