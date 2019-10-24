//
//  FrgXzRenyuan
//
//  Created by DELL on 2017-04-20 13:51:49
//  Copyright (c) DELL All rights reserved.


/**

 */

package com.jinqu.standardNew.frg;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ab.view.pullview.AbPullListView;
import com.framewidget.view.SideBar;
import com.google.gson.Gson;
import com.jinqu.standardNew.R;
import com.jinqu.standardNew.ada.AdaXuanZeRenYuan;
import com.jinqu.standardNew.ada.AdaxuanzeRYSousuo;
import com.jinqu.standardNew.model.ModelEmploee;
import com.jinqu.standardNew.util.PinyinComparator;
import com.mdx.framework.Frame;
import com.mdx.framework.utility.Helper;
import com.mdx.framework.widget.ActionBar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.jinqu.standardNew.F.METHOD_ALLLIST;


public class FrgXzRenyuan extends BaseFrg {

    public TextView mTextView_1;
    public ImageView mImageView_1;
    public TextView mTextView_2;
    public ImageView mImageView_2;
    public TextView mTextView_3;
    public ImageView mImageView_3;
    public EditText mEditText_sousuo;
    public RelativeLayout mRelativeLayout;
    public AbPullListView mAbPullListView;
    public SideBar mSideBar;
    public TextView dialog;
    public String id;
    public int type = 0;
    public List<ModelEmploee.RowsBean> mBaseEmployeeBeans = new ArrayList<ModelEmploee.RowsBean>();
    public List<ModelEmploee.RowsBean> mBaseEmployeeBean_datas = new ArrayList<>();
    public static String from;
    public ListView mListView_sousuo;

    @Override
    protected void create(Bundle savedInstanceState) {
        id = getActivity().getIntent().getStringExtra("id");
        from = getActivity().getIntent().getStringExtra("from");
        setContentView(R.layout.frg_xz_renyuan);
        initView();
        loaddata();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        from = "";
    }

    @Override
    public void disposeMsg(int type, Object obj) {
        switch (type) {
            case 0:
                if (!mBaseEmployeeBean_datas.contains(obj)) {
                    mBaseEmployeeBean_datas.add((ModelEmploee.RowsBean) obj);
                }
                mTextView_3.setText("已选择(" + mBaseEmployeeBean_datas.size() + ")");
                break;
            case 1:
                if (mBaseEmployeeBean_datas.contains(obj)) {
                    mBaseEmployeeBean_datas.remove(obj);
                }
                mTextView_3.setText("已选择(" + mBaseEmployeeBean_datas.size() + ")");
                break;
        }
    }

    private void initView() {
        findVMethod();
    }

    private void findVMethod() {
        mTextView_1 = (TextView) findViewById(R.id.mTextView_1);
        mImageView_1 = (ImageView) findViewById(R.id.mImageView_1);
        mTextView_2 = (TextView) findViewById(R.id.mTextView_2);
        mImageView_2 = (ImageView) findViewById(R.id.mImageView_2);
        mTextView_3 = (TextView) findViewById(R.id.mTextView_3);
        mImageView_3 = (ImageView) findViewById(R.id.mImageView_3);
        mEditText_sousuo = (EditText) findViewById(R.id.mEditText_sousuo);
        mRelativeLayout = (RelativeLayout) findViewById(R.id.mRelativeLayout);
        mAbPullListView = (AbPullListView) findViewById(R.id.mAbPullListView);
        mAbPullListView.setPullRefreshEnable(false);
        mAbPullListView.setPullLoadEnable(false);
        mSideBar = (SideBar) findViewById(R.id.mSideBar);
        dialog = (TextView) findViewById(R.id.dialog);
        mListView_sousuo = (ListView) findViewById(R.id.mListView_sousuo);
        mTextView_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {setBgClickClor(view);
                type = 0;
                Collections.sort(mBaseEmployeeBeans, new PinyinComparator(type));
                setChecked();
                mAbPullListView.setAdapter(new AdaXuanZeRenYuan(getContext(), mBaseEmployeeBeans, type));
                changeState(mTextView_1, mImageView_1);
                mRelativeLayout.setVisibility(View.VISIBLE);
                mEditText_sousuo.setVisibility(View.VISIBLE);
                mListView_sousuo.setVisibility(View.GONE);
            }
        });
        mTextView_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {setBgClickClor(view);
                type = 1;
                Collections.sort(mBaseEmployeeBeans, new PinyinComparator(type));
                setChecked();
                mAbPullListView.setAdapter(new AdaXuanZeRenYuan(getContext(), mBaseEmployeeBeans, type));
                changeState(mTextView_2, mImageView_2);
                mRelativeLayout.setVisibility(View.VISIBLE);
                mEditText_sousuo.setVisibility(View.VISIBLE);
                mListView_sousuo.setVisibility(View.GONE);
            }
        });
        mTextView_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {setBgClickClor(view);
                mListView_sousuo.setAdapter(new AdaxuanzeRYSousuo(getContext(), mBaseEmployeeBean_datas));
                changeState(mTextView_3, mImageView_3);
                mRelativeLayout.setVisibility(View.GONE);
                mEditText_sousuo.setVisibility(View.GONE);
                mListView_sousuo.setVisibility(View.VISIBLE);
            }
        });
        mSideBar.setOnTouchingLetterChangedListener(new SideBar.OnTouchingLetterChangedListener() {
            @Override
            public void onTouchingLetterChanged(String s) {
                dialog.setText(s);
                dialog.setVisibility(View.VISIBLE);
                // 该字母首次出现的位置

                int position = getPositionForSection(s.charAt(0), ((AdaXuanZeRenYuan) mAbPullListView.getmAdapter()).getList());
                if (position != -1) {
                    mAbPullListView.setSelection(position + 1);
                } else if (s.equals("#")) {
                    mAbPullListView.setSelection(((AdaXuanZeRenYuan) mAbPullListView.getmAdapter()).getCount() - 1);
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
                if (arg0.toString().equals("")) {
                    mRelativeLayout.setVisibility(View.VISIBLE);
                    mListView_sousuo.setVisibility(View.GONE);
                } else {
                    mRelativeLayout.setVisibility(View.GONE);
                    mListView_sousuo.setVisibility(View.VISIBLE);
                    filterData(arg0.toString());
                }
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

    public void setChecked() {
        for (ModelEmploee.RowsBean mBaseEmployeeBean : mBaseEmployeeBeans) {
            if (mBaseEmployeeBean_datas.contains(mBaseEmployeeBean)) {
                mBaseEmployeeBean.isChecked = true;
            } else {
                mBaseEmployeeBean.isChecked = false;
            }
        }
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
        mListView_sousuo.setAdapter(new AdaxuanzeRYSousuo(getContext(),
                filterDateList));
    }

    public void changeState(TextView mTextView, ImageView mImageView) {
        mTextView_1.setTextColor(getResources().getColor(R.color.black));
        mTextView_2.setTextColor(getResources().getColor(R.color.black));
        mTextView_3.setTextColor(getResources().getColor(R.color.black));
        mImageView_1.setVisibility(View.GONE);
        mImageView_2.setVisibility(View.GONE);
        mImageView_3.setVisibility(View.GONE);

        mTextView.setTextColor(getResources().getColor(R.color.A));
        mImageView.setVisibility(View.VISIBLE);
        mEditText_sousuo.setText("");
    }

    @Override
    public void onSuccess(String methodName, String content) {
        ModelEmploee mModelSystemList = new Gson().fromJson(content, ModelEmploee.class);
        mBaseEmployeeBeans = mModelSystemList.rows;
        if (!TextUtils.isEmpty(id))
            for (ModelEmploee.RowsBean mBaseEmployeeBean : mBaseEmployeeBeans) {
                if (Arrays.asList(id.split(",")).contains(mBaseEmployeeBean.EmpID + "")) {
                    if (!mBaseEmployeeBean_datas.contains(mBaseEmployeeBean)) {
                        mBaseEmployeeBean_datas.add(mBaseEmployeeBean);
                    }
                }
            }
        mTextView_3.setText("已选择(" + mBaseEmployeeBean_datas.size() + ")");
        Collections.sort(mBaseEmployeeBeans, new PinyinComparator(type));
        setChecked();
        mAbPullListView.setAdapter(new AdaXuanZeRenYuan(getContext(), mBaseEmployeeBeans, type));
    }

    public void loaddata() {
        loadUrlPost(METHOD_ALLLIST, "page", "1", "rows", Integer.MAX_VALUE);

    }

    @Override
    public void setActionBar(ActionBar actionBar, Context context) {
        super.setActionBar(actionBar, context);
        mHeadlayout.setTitle("选择人员");
        mHeadlayout.setRText("确认");
        mHeadlayout.setRightOnclicker(new View.OnClickListener() {
            @Override
            public void onClick(View view) {setBgClickClor(view);
                if (mBaseEmployeeBean_datas.size() > 0) {
                    Frame.HANDLES.sentAll(from, 101, mBaseEmployeeBean_datas);
                    FrgXzRenyuan.this.finish();
                } else {
                    Helper.toast("请选择人员", getContext());
                }
            }
        });

    }
}