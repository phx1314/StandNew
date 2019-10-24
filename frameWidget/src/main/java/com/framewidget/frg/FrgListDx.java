//
//  FrgCxFaxian
//
//  Created by Administrator on 2015-04-21 10:29:49
//  Copyright (c) Administrator All rights reserved.

/**

 */

package com.framewidget.frg;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.framewidget.R;
import com.framewidget.ada.AdaXz;
import com.framewidget.model.ModelDx;
import com.mdx.framework.Frame;
import com.mdx.framework.widget.ActionBar;

import java.util.ArrayList;
import java.util.List;

public class FrgListDx extends BaseFrg {

    public ListView mMPageListView;
    public String from;
    public String title;
    public int type;
    public List<ModelDx> data = new ArrayList();
    public AdaXz mAdaXz;

    @Override
    protected void create(Bundle savedInstanceState) {
        setContentView(R.layout.frg_cx_sex_list);
        from = getActivity().getIntent().getStringExtra("from");
        title = getActivity().getIntent().getStringExtra("title");
        type = getActivity().getIntent().getIntExtra("type", 0);
        data = (List<ModelDx>) getActivity().getIntent().getSerializableExtra("data");
        initView();
        loaddata();
    }

    private void initView() {
        mMPageListView = (ListView) findViewById(R.id.mMPageListView);

    }

    public void loaddata() {

        mAdaXz = new AdaXz(getContext(), data);
        mMPageListView.setAdapter(mAdaXz);
    }

    @Override
    public void onClick(View arg0) {
    }

    @Override
    public void setActionBar(ActionBar actionBar, Context context) {
        super.setActionBar(actionBar, context);
        mHeadlayout.setTitle(title);
        mHeadlayout.setRText("完成");
        mHeadlayout.setRightOnclicker(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<ModelDx> modelDxes = new ArrayList();
                for (ModelDx mModelDx : mAdaXz.getList()) {
                    if (mModelDx.isChecked) {
                        modelDxes.add(mModelDx);
                    }
                }
                Frame.HANDLES.sentAll(from, type, modelDxes);
                FrgListDx.this.finish();
            }
        });
    }

}