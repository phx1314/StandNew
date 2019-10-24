//
//  FrgSousuoSon
//
//  Created by DELL on 2018-06-27 09:39:36
//  Copyright (c) DELL All rights reserved.


/**

 */

package com.jinqu.standardNew.frg;

import android.content.Context;
import android.os.Bundle;

import com.jinqu.standardNew.R;

import com.ab.view.pullview.AbPullListView;
import com.mdx.framework.widget.ActionBar;


public class FrgSousuoSon extends BaseFrg {

    public AbPullListView mAbPullListView;
    public int type = 0;
    public String key;

    @Override
    protected void create(Bundle savedInstanceState) {
        key = getActivity().getIntent().getStringExtra("key");
        type = getActivity().getIntent().getIntExtra("type", 0);
        setContentView(R.layout.frg_sousuo_son);
        initView();
        loaddata();
    }

    private void initView() {
        findVMethod();
    }

    private void findVMethod() {
        mAbPullListView = (AbPullListView) findViewById(R.id.mAbPullListView);


    }

    public void loaddata() {

    }


    @Override
    public void setActionBar(ActionBar actionBar, Context context) {
        super.setActionBar(actionBar, context);
        mHeadlayout.setTitle(key);
    }
}