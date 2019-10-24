//
//  FrgRizhi
//
//  Created by DELL on 2017-08-28 13:58:34
//  Copyright (c) DELL All rights reserved.


/**

 */

package com.jinqu.standardNew.frg;

import android.content.Context;
import android.os.Bundle;
import android.text.TextPaint;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ab.view.listener.AbOnListViewListener;
import com.ab.view.pullview.AbPullListView;
import com.google.gson.Gson;
import com.jinqu.standardNew.R;
import com.jinqu.standardNew.ada.AdaRz;
import com.jinqu.standardNew.model.ModelRzh;
import com.jinqu.standardNew.model.ModelSearch;
import com.jinqu.standardNew.model.ModelSearchGk;
import com.mdx.framework.adapter.MAdapter;
import com.mdx.framework.widget.ActionBar;

import java.util.ArrayList;
import java.util.List;

import static com.jinqu.standardNew.F.METHOD_BASELOG;


public class FrgRizhi extends BaseFrg {

    public TextView mTextView_shoujian;
    public ImageView mImageView_shoujian;
    public TextView mTextView_fajian;
    public ImageView mImageView_fajian;
    public AbPullListView mAbPullListView;
    public List<Object> mmModelSearchs = new ArrayList<>();
    public int type = 1;
    public ModelSearchGk modelXwSearch = new ModelSearchGk();

    @Override
    protected void create(Bundle savedInstanceState) {
        type = getActivity().getIntent().getIntExtra("type", 1);
        setContentView(R.layout.frg_rizhi);
        initView();
        loaddata();
    }

    @Override
    public void disposeMsg(int type, Object obj) {
        switch (type) {
            case 500:
                ModelSearchGk.ListBean mListBean = new ModelSearchGk.ListBean();
                mListBean.Value = obj.toString();
                mListBean.Key = "EmpName,BaseLogIP,BaseLogRefHTML";
                modelXwSearch.list.clear();
                modelXwSearch.list.add(mListBean);
                if (!mmModelSearchs.contains(modelXwSearch))
                    mmModelSearchs.add(modelXwSearch);
                mAbPullListView.setPostApiLoadParams(METHOD_BASELOG, "queryInfo", new Gson().toJson(mmModelSearchs));
                break;
        }
    }

    private void initView() {
        findVMethod();
    }

    private void findVMethod() {
        mTextView_shoujian = (TextView) findViewById(R.id.mTextView_shoujian);
        mImageView_shoujian = (ImageView) findViewById(R.id.mImageView_shoujian);
        mTextView_fajian = (TextView) findViewById(R.id.mTextView_fajian);
        mImageView_fajian = (ImageView) findViewById(R.id.mImageView_fajian);
        mAbPullListView = (AbPullListView) findViewById(R.id.mAbPullListView);
        mTextView_fajian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setBgClickClor(view);
                type = 0;
                mmModelSearchs.clear();
                ModelSearch mModelSearch = new ModelSearch();
                ModelSearch.ListBean mListBean = new ModelSearch.ListBean();
                mListBean.Value = "10";
                mListBean.Key = "BaseLogTypeID";
                mListBean.Contract = "<";
                mListBean.filedType = "Int";
                mModelSearch.list.add(mListBean);
                mmModelSearchs.add(mModelSearch);
                changeState((TextView) view, mImageView_fajian);
            }
        });
        mTextView_shoujian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setBgClickClor(view);
                type = 1;
                mmModelSearchs.clear();
                ModelSearch mModelSearch = new ModelSearch();
                ModelSearch.ListBean mListBean = new ModelSearch.ListBean();
                mListBean.Value = "10";
                mListBean.Key = "BaseLogTypeID";
                mListBean.Contract = ">=";
                mListBean.filedType = "Int";
                mModelSearch.list.add(mListBean);
                mmModelSearchs.add(mModelSearch);
                changeState((TextView) view, mImageView_shoujian);
            }
        });

    }

    public void changeState(TextView mTextView, ImageView mImageView) {
        mImageView_shoujian.setVisibility(View.GONE);
        mImageView_fajian.setVisibility(View.GONE);
        TextPaint tp1 = mTextView_shoujian.getPaint();
        tp1.setFakeBoldText(false);
        TextPaint tp2 = mTextView_fajian.getPaint();
        tp2.setFakeBoldText(false);
        TextPaint tp3 = mTextView.getPaint();
        tp3.setFakeBoldText(true);
        mImageView.setVisibility(View.VISIBLE);
        mAbPullListView.setPostApiLoadParams(METHOD_BASELOG, "queryInfo", new Gson().toJson(mmModelSearchs));


    }

    public void loaddata() {
        mAbPullListView.setAbOnListViewListener(new AbOnListViewListener() {
            @Override
            public MAdapter onSuccess(String methodName, String content) {
                ModelRzh mModelSystemList = new Gson().fromJson(content, ModelRzh.class);
                return new AdaRz(getContext(), mModelSystemList.rows, type);
            }
        });
        if (type == 1) {
            ModelSearch mModelSearch = new ModelSearch();
            ModelSearch.ListBean mListBean = new ModelSearch.ListBean();
            mListBean.Value = "10";
            mListBean.Key = "BaseLogTypeID";
            mListBean.Contract = ">=";
            mListBean.filedType = "Int";
            mModelSearch.list.add(mListBean);
            mmModelSearchs.add(mModelSearch);
            changeState(mTextView_shoujian, mImageView_shoujian);
        } else if (type == 0) {
            mmModelSearchs.clear();
            ModelSearch mModelSearch = new ModelSearch();
            ModelSearch.ListBean mListBean = new ModelSearch.ListBean();
            mListBean.Value = "10";
            mListBean.Key = "BaseLogTypeID";
            mListBean.Contract = "<";
            mListBean.filedType = "Int";
            mModelSearch.list.add(mListBean);
            mmModelSearchs.add(mModelSearch);
            changeState(mTextView_fajian, mImageView_fajian);
        }

    }

    @Override
    public void setActionBar(ActionBar actionBar, Context context) {
        super.setActionBar(actionBar, context);
        mHeadlayout.setTitle("日志统计");
        mHeadlayout.setRightBacgroud(R.drawable.sousuo_bai);
        mHeadlayout.setRightOnclicker(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setBgClickClor(view);
//                Helper.startActivity(getContext(), FrgSousuoPub.class, TitleAct.class, "from", "FrgRizhi", "hint", "人员、IP、内容");
            }
        });
    }
}