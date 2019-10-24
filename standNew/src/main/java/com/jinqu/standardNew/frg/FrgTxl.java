//
//  FrgTxl
//
//  Created by DELL on 2018-05-03 15:30:47
//  Copyright (c) DELL All rights reserved.


/**

 */

package com.jinqu.standardNew.frg;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ab.view.listener.AbOnListViewListener;
import com.ab.view.pullview.AbPullListView;
import com.framewidget.view.SideBar;
import com.google.gson.Gson;
import com.jinqu.standardNew.R;
import com.jinqu.standardNew.ada.AdaTxl;
import com.jinqu.standardNew.model.EntitySearch;
import com.jinqu.standardNew.model.ModelEmploee;
import com.jinqu.standardNew.pop.PopShowChoose;
import com.jinqu.standardNew.util.PinyinComparator;
import com.mdx.framework.adapter.MAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.jinqu.standardNew.F.METHOD_ALLLIST;


public class FrgTxl extends BaseFrg {

    public AbPullListView mAbPullListView;
    public int type = 0;
    public TextView mTextView_type;
    public SideBar mSideBar;
    public RelativeLayout mRelativeLayout;
    public List<ModelEmploee.RowsBean> mBaseEmployeeBeans_data = new ArrayList<ModelEmploee.RowsBean>();
    public LinearLayout mLinearLayout_type;
    public String[] data = {"按A-Z", "按部门"};
    public TextView dialog;

    @Override
    protected void create(Bundle savedInstanceState) {
        setContentView(R.layout.frg_txl);
        initView();
        loaddata();
    }

    @Override
    public void disposeMsg(int type, Object obj) {
        switch (type) {
            case 101:
                if (obj.toString().equals("按A-Z")) {
                    this.type = 0;
                } else {
                    this.type = 1;
                }
                mAbPullListView.pullLoad();
                mTextView_type.setText(obj.toString());
                break;
            case 10086:
                mAbPullListView.setProgressColor();
                break;
            case 888:
                List<EntitySearch> mEntitySearchs = (List<EntitySearch>) obj;
                filter(mEntitySearchs);
                break;
            case 200:
                mAbPullListView.pullLoad();
                break;
        }
    }

    public void filter(List<EntitySearch> mEntitySearchs) {
        for (EntitySearch mEntitySearch : mEntitySearchs) {
            if (mEntitySearch.title.equals("关键字")) {
                filterData(mEntitySearch.value);
                break;
            }
        }
    }

    private void initView() {
        findVMethod();
    }

    private void findVMethod() {
        mAbPullListView = (AbPullListView) findViewById(R.id.mAbPullListView);
        mTextView_type = (TextView) findViewById(R.id.mTextView_type);
        mSideBar = (SideBar) findViewById(R.id.mSideBar);
        mRelativeLayout = (RelativeLayout) findViewById(R.id.mRelativeLayout);
        mLinearLayout_type = (LinearLayout) findViewById(R.id.mLinearLayout_type);
        dialog = (TextView) findViewById(R.id.dialog);
        mLinearLayout_type.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setBgClickClor(v);
                PopShowChoose mPopShowChoose = new PopShowChoose(getContext(), mLinearLayout_type, Arrays.asList(data), mTextView_type.getText().toString(), "FrgTxl");
                mPopShowChoose.show();
            }
        });
    }


    /**
     * 根据分类的首字母的Char ascii值获取其第一次出现该首字母的位置
     */
    public int getPositionForSection(char section, List<ModelEmploee.RowsBean> mUserInfoBeans) {
        for (int i = 0; i < mUserInfoBeans.size(); i++) {
            if (TextUtils.isEmpty(type == 0 ? mUserInfoBeans.get(i).EmpName : mUserInfoBeans.get(i).EmpDepName)) {
                continue;
            }
            String sortStr = type == 0 ? com.framewidget.F.toPinYin(mUserInfoBeans.get(i).EmpName.charAt(0)) : com.framewidget.F.toPinYin(mUserInfoBeans.get(i).EmpDepName.charAt(0));
            char firstChar = sortStr.toUpperCase().charAt(0);
            if (firstChar == section) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 根据输入框中的值来过滤数据并更新ListView
     *
     * @param filterStr
     */
    public void filterData(String filterStr) {
        List<ModelEmploee.RowsBean> filterDateList = new ArrayList<ModelEmploee.RowsBean>();
        if (TextUtils.isEmpty(filterStr)) {
            filterDateList = mBaseEmployeeBeans_data;
        } else {
            filterDateList.clear();
            for (ModelEmploee.RowsBean sortModel : mBaseEmployeeBeans_data) {
                String name = type == 0 ? sortModel.EmpName : sortModel.EmpDepName;
                if (name.indexOf(filterStr.toString()) != -1
                        || com.framewidget.F
                        .toPinYin((type == 0 ? sortModel.EmpName : sortModel.EmpDepName).charAt(0)).toUpperCase()
                        .startsWith(filterStr.toString()) || com.framewidget.F
                        .toPinYin((type == 0 ? sortModel.EmpName : sortModel.EmpDepName).charAt(0))
                        .startsWith(filterStr.toString())) {
                    filterDateList.add(sortModel);
                }
            }
        }
        // // 根据a-z进行排序
        Collections.sort(filterDateList, new PinyinComparator(type));
        mAbPullListView.setAdapter(new AdaTxl(getContext(), filterDateList, type));
    }

    public void loaddata() {
        mAbPullListView.setPullLoadEnable(false);
        mAbPullListView.setPageSize(Integer.MAX_VALUE);
        mAbPullListView.setPostApiLoadParams(METHOD_ALLLIST, "page", "1", "rows", Integer.MAX_VALUE);
        mAbPullListView.setAbOnListViewListener(new AbOnListViewListener() {
            @Override
            public MAdapter onSuccess(String methodName, String content) {
                ModelEmploee mModelSystemList = new Gson().fromJson(content, ModelEmploee.class);
                mBaseEmployeeBeans_data = mModelSystemList.rows;
                Collections.sort(mBaseEmployeeBeans_data, new PinyinComparator(type));
                return new AdaTxl(getContext(), mBaseEmployeeBeans_data, type);
            }
        });

        mSideBar.setOnTouchingLetterChangedListener(new SideBar.OnTouchingLetterChangedListener() {
            @Override
            public void onTouchingLetterChanged(String s) {
                dialog.setText(s);
                dialog.setVisibility(View.VISIBLE);
                int position = getPositionForSection(s.charAt(0), ((AdaTxl) mAbPullListView.getmAdapter()).getList());
                if (position != -1) {
                    mAbPullListView.setSelection(position + 1);
                } else if (s.equals("#")) {
                    mAbPullListView.setSelection(((AdaTxl) mAbPullListView.getmAdapter()).getCount() - 1);
                }
            }

            @Override
            public void onUp() {
            }
        });
        mSideBar.setTextView(dialog);
    }


}