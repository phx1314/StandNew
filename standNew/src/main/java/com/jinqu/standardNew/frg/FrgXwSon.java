//
//  FrgXwSon
//
//  Created by DELL on 2018-05-08 11:17:04
//  Copyright (c) DELL All rights reserved.


/**

 */

package com.jinqu.standardNew.frg;

import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.ab.view.listener.AbOnListListener;
import com.ab.view.listener.AbOnListViewListener;
import com.ab.view.pullview.AbPullListView;
import com.google.gson.Gson;
import com.jinqu.standardNew.R;
import com.jinqu.standardNew.ada.AdaXw;
import com.jinqu.standardNew.item.Radiobutton;
import com.jinqu.standardNew.item.XwTop;
import com.jinqu.standardNew.model.ModelNewsImges;
import com.jinqu.standardNew.model.ModelNewsList;
import com.jinqu.standardNew.model.ModelSearch;
import com.jinqu.standardNew.model.ModelSearchGk;
import com.jinqu.standardNew.model.ModelUsreLogin;
import com.mdx.framework.adapter.MAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.jinqu.standardNew.F.METHOD_GetImageNews;
import static com.jinqu.standardNew.F.METHOD_OANEW;
import static com.jinqu.standardNew.F.changeColor;
import static com.jinqu.standardNew.F.json2Model;
import static com.jinqu.standardNew.F.mModelUsreLogin;


public class FrgXwSon extends BaseFrg {

    public AbPullListView mAbPullListView;
    public View view_top;
    public RadioGroup mRadioGroup;
    public List<Object> mModelSearchs = new ArrayList<>();
    public ModelSearchGk mModelSearchGk = new ModelSearchGk();

    @Override
    protected void create(Bundle savedInstanceState) {
        setContentView(R.layout.frg_xw_son);
        initView();
        loaddata();
    }

    @Override
    public void disposeMsg(int type, Object obj) {
        switch (type) {
            case 10086:
                setColor();
//                mSwipeRefreshLayout.setColorSchemeColors(Color.parseColor(COLOR));
                break;
            case 888:
                List<ModelSearchGk> objs = Arrays.asList((ModelSearchGk[]) json2Model(obj.toString(), ModelSearchGk[].class));
                mModelSearchs.remove(mModelSearchGk);
                mModelSearchGk.list.clear();
                mModelSearchGk = objs.size() > 0 ? objs.get(0) : new ModelSearchGk();
                mModelSearchs.add(mModelSearchGk);
                mAbPullListView.setPostApiLoadParams(METHOD_OANEW, "queryInfo", new Gson().toJson(mModelSearchs));
                break;
        }
    }


    private void initView() {
        findVMethod();
    }

    private void findVMethod() {
        mAbPullListView = (AbPullListView) findViewById(R.id.mAbPullListView);
//        mSwipeRefreshLayout = (android.support.v4.widget.SwipeRefreshLayout) findViewById(mSwipeRefreshLayout);
        mRadioGroup = (RadioGroup) findViewById(R.id.mRadioGroup);
        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                setValue(checkedId + "");
            }
        });

    }

    public void loaddata() {
        view_top = XwTop.getView(getContext(), null);
        mAbPullListView.addHeaderView(view_top);
        mAbPullListView.setAbOnListListener(new AbOnListListener() {
            @Override
            public void onRefresh() {
                loadUrlPostNoShow(METHOD_GetImageNews);
            }
        });
        mAbPullListView.setAbOnListViewListener(new AbOnListViewListener() {

            @Override
            public MAdapter onSuccess(String methodName, String content) {
                ModelNewsList mModelNewsList = new Gson().fromJson(content, ModelNewsList.class);
                return new AdaXw(getContext(), mModelNewsList.rows);
            }
        });
        loadUrlPostNoShow(METHOD_GetImageNews);
        RadioButton mRadioButton1 = (RadioButton) Radiobutton.getView(getContext(), null);
        mRadioButton1.setId(0);
        mRadioButton1.setText("全部");
        mRadioButton1.setLayoutParams(new RadioGroup.LayoutParams(RadioGroup.LayoutParams.WRAP_CONTENT, RadioGroup.LayoutParams.WRAP_CONTENT));
        mRadioGroup.addView(mRadioButton1);
        for (ModelUsreLogin.BaseDataSystemBean mBaseDataBean : mModelUsreLogin.BaseDataSystem) {
            if (mBaseDataBean.BaseOrder.startsWith("002_")) {
                RadioButton mRadioButton = (RadioButton) Radiobutton.getView(getContext(), null);
                mRadioButton.setId(mBaseDataBean.BaseID);
                mRadioButton.setText(mBaseDataBean.BaseName);
                mRadioButton.setLayoutParams(new RadioGroup.LayoutParams(RadioGroup.LayoutParams.WRAP_CONTENT, RadioGroup.LayoutParams.WRAP_CONTENT));
                mRadioGroup.addView(mRadioButton);
            }
        }
        ((RadioButton) mRadioGroup.getChildAt(0)).setChecked(true);
        setValue(((RadioButton) mRadioGroup.getChildAt(0)).getId() + "");
        setColor();
    }

    public void setValue(String id) {
        ModelSearch modelSearch = new ModelSearch();
        ModelSearch.ListBean mListBean = new ModelSearch.ListBean();
        mListBean.Value = id.equals("0") ? "" : id;
        mListBean.Key = "NewsTypeID";
        mListBean.Contract = "in";
        modelSearch.list.add(mListBean);
        mModelSearchs.clear();
        mModelSearchs.add(modelSearch);
        ((XwTop) view_top.getTag()).set(id);
        mAbPullListView.setPostApiLoadParams(METHOD_OANEW, "queryInfo", new Gson().toJson(mModelSearchs));
    }

    @Override
    public void onSuccess(String methodName, String content) {
        if (methodName.equals(METHOD_GetImageNews)) {
            ModelNewsImges mModelNewsImges = (ModelNewsImges) json2Model(content, ModelNewsImges.class);
            ((XwTop) view_top.getTag()).set(mModelNewsImges);
        }
    }

    void setColor() {
        for (int i = 0; i < mRadioGroup.getChildCount(); i++) {
            View view = mRadioGroup.getChildAt(i);
            changeColor(view.getBackground());
        }
    }


}