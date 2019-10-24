//
//  FrgSousuo
//
//  Created by DELL on 2018-06-27 09:36:35
//  Copyright (c) DELL All rights reserved.


/**

 */

package com.jinqu.standardNew.frg;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.ab.util.AbViewUtil;
import com.ab.view.pullview.AbPullListView;
import com.framewidget.F;
import com.framewidget.model.ModelDx;
import com.framewidget.view.FixGridLayout;
import com.google.gson.Gson;
import com.jinqu.standardNew.R;
import com.jinqu.standardNew.ada.AdaSousuo;
import com.jinqu.standardNew.item.SousuoTop;
import com.jinqu.standardNew.model.ModelMenuConfig;
import com.jinqu.standardNew.model.ModelMenuConfig.SearchBean;
import com.mdx.framework.Frame;
import com.mdx.framework.activity.MActivityActionbar;
import com.mdx.framework.utility.Helper;

import java.util.ArrayList;
import java.util.List;

import static com.jinqu.standardNew.F.COLOR;
import static com.jinqu.standardNew.F.json2Model;


public class FrgSousuo extends BaseFrg {

    public ImageView clk_mImageView_del;
    public FixGridLayout mFixGridLayout;
    public AbPullListView mAbPullListView;
    public List<String> data_history = new ArrayList<>();
    public String key = "SOUSUOJSON";
    public EditText mEditText_sousuo;
    public ImageButton mImageButton_left;
    public String from;
    public List<SearchBean> data = new ArrayList();
    public static int position;
    public TextView mTextView_search;
    public ModelMenuConfig.SearchBean mSearchBean;

    @Override
    protected void create(Bundle savedInstanceState) {
        ((MActivityActionbar) getActivity()).setSwipeBackEnable(false);
        from = getActivity().getIntent().getStringExtra("from");
        data = (List<ModelMenuConfig.SearchBean>) getActivity().getIntent().getSerializableExtra("data") == null ? new ArrayList() : (List<ModelMenuConfig.SearchBean>) getActivity().getIntent().getSerializableExtra("data");
        setContentView(R.layout.frg_sousuo);
        initView();
        loaddata();
    }

    @Override
    public void disposeMsg(int type, Object obj) {
        switch (type) {
            case 0:
                mEditText_sousuo.setText(obj.toString());
//                goWhere(obj.toString());
                break;
            case 1:
                ModelDx mModelDx = (ModelDx) obj;
                ((AdaSousuo) mAbPullListView.getmAdapter()).get(position).value = mModelDx.string;
                ((AdaSousuo) mAbPullListView.getmAdapter()).get(position).ids = mModelDx.id + "";
                mAbPullListView.getmAdapter().notifyDataSetChanged();
                break;
            case 2:
                List<ModelDx> mModelDxs = (List<ModelDx>) obj;
                String string = "";
                String ids = "";
                for (ModelDx son : mModelDxs) {
                    string += son.string + ",";
                    ids += son.id + ",";
                }
                if (string.length() > 0) {
                    string = string.substring(0, string.length() - 1);
                    ids = ids.substring(0, ids.length() - 1);
                }
                ((AdaSousuo) mAbPullListView.getmAdapter()).get(position).value = string;
                ((AdaSousuo) mAbPullListView.getmAdapter()).get(position).ids = ids;
                mAbPullListView.getmAdapter().notifyDataSetChanged();
                break;
        }
    }

    private void initView() {
        findVMethod();
    }

    private void findVMethod() {
        clk_mImageView_del = (ImageView) findViewById(R.id.clk_mImageView_del);
        mFixGridLayout = (FixGridLayout) findViewById(R.id.mFixGridLayout);
        mAbPullListView = (AbPullListView) findViewById(R.id.mAbPullListView);
        mEditText_sousuo = (EditText) findViewById(R.id.mEditText_sousuo);
        mImageButton_left = (ImageButton) findViewById(R.id.mImageButton_left);
        mTextView_search = (TextView) findViewById(R.id.mTextView_search);
        mFixGridLayout.setDividerLine((int) AbViewUtil.dip2px(getContext(), 10f));
        clk_mImageView_del.setOnClickListener(com.mdx.framework.utility.Helper.delayClickLitener(this));
        mImageButton_left.setOnClickListener(com.mdx.framework.utility.Helper.delayClickLitener(this));
        mAbPullListView.setPullLoadEnable(false);
        mAbPullListView.setPullRefreshEnable(false);
        mTextView_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(mEditText_sousuo.getText().toString().trim()) && !data_history.contains(mEditText_sousuo.getText().toString().trim())) {
                    data_history.add(mEditText_sousuo.getText().toString().trim());
                    View view = SousuoTop.getView(getContext(), null);
                    ((SousuoTop) view.getTag()).set(mEditText_sousuo.getText().toString().trim());
                    mFixGridLayout.addView(view);
                    Helper.saveBuilder(key, data_history);
                }
                goWhere();
            }
        });
    }

    public void goWhere() {
        try {
            List<Object> objs = new ArrayList<>();
            if (!TextUtils.isEmpty(mEditText_sousuo.getText().toString())) {
                mSearchBean.sqlstring = mSearchBean.sqlstring.replace("#{value}", mEditText_sousuo.getText().toString());
                objs.add(json2Model(mSearchBean.sqlstring, Object.class));
            }
            for (ModelMenuConfig.SearchBean son : ((AdaSousuo) mAbPullListView.getmAdapter()).getList()) {
                if (!TextUtils.isEmpty(son.value)) {
                    son.sqlstring = son.sqlstring.replace("#{value}", son.type.equals("basedata") ? son.ids : son.value);
                    objs.add(json2Model(son.sqlstring, Object.class));
                }
            }
            Frame.HANDLES.sentAll(from, 888, new Gson().toJson(objs));
            FrgSousuo.this.finish();
            F.closeSoftKey(getActivity());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void loaddata() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            mImageButton_left.getDrawable().setTint(Color.parseColor(COLOR));
        data_history = Helper.readBuilder(key, new ArrayList<String>());
        if (data_history != null && data_history.size() > 0) {
            for (String str : data_history) {
                View view = SousuoTop.getView(getContext(), null);
                ((SousuoTop) view.getTag()).set(str);
                mFixGridLayout.addView(view);
            }
        }
        if (data.size() > 0 && data.get(0).type.equals("text")) {
            mEditText_sousuo.setHint(data.get(0).text);
            mSearchBean = data.get(0);
            data.remove(0);
        }
        mAbPullListView.setAdapter(new AdaSousuo(getContext(), data));

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            finish();
            F.closeSoftKey(getActivity());
            getActivity().overridePendingTransition(0, 0);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onClick(android.view.View v) {
        super.onClick(v);
        if (R.id.mImageButton_left == v.getId()) {
            finish();
            F.closeSoftKey(getActivity());
            getActivity().overridePendingTransition(0, 0);
        } else if (R.id.clk_mImageView_del == v.getId()) {
            Helper.deleteBuilder(key);
            mFixGridLayout.removeAllViews();
        }
    }

}