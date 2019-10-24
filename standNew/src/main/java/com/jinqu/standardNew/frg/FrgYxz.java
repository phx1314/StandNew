//
//  FrgYxz
//
//  Created by DELL on 2018-05-17 17:19:50
//  Copyright (c) DELL All rights reserved.


/**
   
*/

package com.jinqu.standardNew.frg;
import android.os.Bundle;

import com.jinqu.standardNew.R;

import com.ab.view.pullview.AbPullListView;



public class FrgYxz extends BaseFrg{

    public AbPullListView mAbPullListView;


 	@Override
    protected void create(Bundle savedInstanceState) {
        setContentView(R.layout.frg_yxz);
        initView();
        loaddata();
    }

    private void initView(){
        findVMethod();
    }
    
    private void findVMethod() {
        mAbPullListView=(AbPullListView)findViewById(R.id.mAbPullListView);


    }
    
    public void loaddata(){

    }
    
   
 
}