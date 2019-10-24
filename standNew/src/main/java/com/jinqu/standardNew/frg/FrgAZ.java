//
//  FrgAZ
//
//  Created by DELL on 2018-05-17 17:19:43
//  Copyright (c) DELL All rights reserved.


/**
   
*/

package com.jinqu.standardNew.frg;
import android.os.Bundle;

import com.jinqu.standardNew.R;

import android.widget.RelativeLayout;
import com.ab.view.pullview.AbPullListView;
import com.framewidget.view.SideBar;



public class FrgAZ extends BaseFrg{

    public RelativeLayout mRelativeLayout;
    public AbPullListView mAbPullListView;
    public SideBar mSideBar;


 	@Override
    protected void create(Bundle savedInstanceState) {
        setContentView(R.layout.frg_a_z);
        initView();
        loaddata();
    }

    private void initView(){
        findVMethod();
    }
    
    private void findVMethod() {
        mRelativeLayout=(RelativeLayout)findViewById(R.id.mRelativeLayout);
        mAbPullListView=(AbPullListView)findViewById(R.id.mAbPullListView);
        mSideBar=(SideBar)findViewById(R.id.mSideBar);


    }
    
    public void loaddata(){

    }
    
   
 
}