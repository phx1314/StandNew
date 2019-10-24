package com.jinqu.standardNew.frg;//
//  FrgXzRenyuan
//
//  Created by DELL on 2017-04-20 13:51:49
//  Copyright (c) DELL All rights reserved.


/**

 */


import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ab.view.pullview.AbPullListView;
import com.framewidget.F;
import com.framewidget.view.SideBar;
import com.jinqu.standardNew.R;
import com.jinqu.standardNew.ada.AdaXzOne;
import com.jinqu.standardNew.model.ModelEmploee;
import com.jinqu.standardNew.util.PinyinComparator;
import com.mdx.framework.Frame;
import com.mdx.framework.widget.ActionBar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class FrgXzRenyuanOne extends BaseFrg {

    public EditText mEditText_sousuo;
    public RelativeLayout mRelativeLayout;
    public AbPullListView mAbPullListView;
    public SideBar mSideBar;
    public TextView dialog;
    public int type = 0;
    public String from;
    public List<ModelEmploee.RowsBean> mBaseEmployeeBeans = new ArrayList<ModelEmploee.RowsBean>();

    @Override
    protected void create(Bundle savedInstanceState) {
        from = getActivity().getIntent().getStringExtra("from");
        mBaseEmployeeBeans = (List<ModelEmploee.RowsBean>) getActivity().getIntent().getSerializableExtra("mBaseEmployeeBeans");
        setContentView(R.layout.frg_xz_renyuan_one);
        initView();
        loaddata();
    }

    @Override
    public void disposeMsg(int type, Object obj) {
        switch (type) {
            case 0:
                F.closeSoftKey(getActivity());//干你妈
                mRelativeLayout.requestFocus();
                Frame.HANDLES.sentAll(from, 1, obj);
                finish();
                break;
        }
    }

    private void initView() {
        findVMethod();
    }

    private void findVMethod() {
        mEditText_sousuo = (EditText) findViewById(R.id.mEditText_sousuo);
        mRelativeLayout = (RelativeLayout) findViewById(R.id.mRelativeLayout);
        mAbPullListView = (AbPullListView) findViewById(R.id.mAbPullListView);
        mAbPullListView.setPullRefreshEnable(false);
        mAbPullListView.setPullLoadEnable(false);
        mSideBar = (SideBar) findViewById(R.id.mSideBar);
        dialog = (TextView) findViewById(R.id.dialog);

        mSideBar.setOnTouchingLetterChangedListener(new SideBar.OnTouchingLetterChangedListener() {
            @Override
            public void onTouchingLetterChanged(String s) {
                dialog.setText(s);
                dialog.setVisibility(View.VISIBLE);
                int position = getPositionForSection(s.charAt(0), ((AdaXzOne) mAbPullListView.getmAdapter()).getList());
                if (position != -1) {
                    mAbPullListView.setSelection(position + 1);
                } else if (s.equals("#")) {
                    mAbPullListView.setSelection(((AdaXzOne) mAbPullListView.getmAdapter()).getCount() - 1);
                }
            }

            @Override
            public void onUp() {
            }
        });
        mSideBar.setTextView(dialog);
        mEditText_sousuo.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence arg0, int arg1, int arg2,
                                      int arg3) {
                filterData(arg0.toString());
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1,
                                          int arg2, int arg3) {

            }

            @Override
            public void afterTextChanged(Editable arg0) {

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
            filterDateList = mBaseEmployeeBeans;
        } else {
            filterDateList.clear();
            for (ModelEmploee.RowsBean sortModel : mBaseEmployeeBeans) {
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

        ((AdaXzOne) mAbPullListView.getmAdapter()).clear();
        ((AdaXzOne) mAbPullListView.getmAdapter()).AddAll(filterDateList);
    }


    public void loaddata() {
        if (mBaseEmployeeBeans == null) {
            mBaseEmployeeBeans = FrgHome.mBaseEmployeeBeans;
        }
        Collections.sort(mBaseEmployeeBeans, new PinyinComparator(1));
        mAbPullListView.setAdapter(new AdaXzOne(getContext(), mBaseEmployeeBeans, 1));
    }

    @Override
    public void setActionBar(ActionBar actionBar, Context context) {
        super.setActionBar(actionBar, context);
        mHeadlayout.setTitle("选择人员");
    }
}