//
//  FrgGjmb
//
//  Created by DELL on 2018-05-17 14:58:39
//  Copyright (c) DELL All rights reserved.


/**
   
*/

package com.jinqu.standardNew.frg;
import android.os.Bundle;

import com.jinqu.standardNew.R;

import android.widget.TextView;
import com.ab.view.pullview.AbPullListView;



public class FrgGjmb extends BaseFrg{

    public TextView mTextView_type;
    public AbPullListView mAbPullListView;


 	@Override
    protected void create(Bundle savedInstanceState) {
        setContentView(R.layout.frg_gjmb);
        initView();
        loaddata();
    }

    private void initView(){
        findVMethod();
    }
    
    private void findVMethod() {
        mTextView_type=(TextView)findViewById(R.id.mTextView_type);
        mAbPullListView=(AbPullListView)findViewById(R.id.mAbPullListView);


    }
    
    public void loaddata(){

    }
    
   
 
}