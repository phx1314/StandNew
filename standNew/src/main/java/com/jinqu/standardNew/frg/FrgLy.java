//
//  FrgLy
//
//  Created by DELL on 2018-05-17 16:41:30
//  Copyright (c) DELL All rights reserved.


/**
   
*/

package com.jinqu.standardNew.frg;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ab.view.pullview.AbPullListView;
import com.jinqu.standardNew.R;



public class FrgLy extends BaseFrg{

    public AbPullListView mMPageListView;
    public LinearLayout mLinearLayout_fs;
    public EditText mEditText;
    public TextView mTextView_fs;


 	@Override
    protected void create(Bundle savedInstanceState) {
        setContentView(R.layout.frg_ly);
        initView();
        loaddata();
    }

    private void initView(){
        findVMethod();
    }
    
    private void findVMethod() {
        mMPageListView=(AbPullListView)findViewById(R.id.mMPageListView);
        mLinearLayout_fs=(LinearLayout)findViewById(R.id.mLinearLayout_fs);
        mEditText=(EditText)findViewById(R.id.mEditText);
        mTextView_fs=(TextView)findViewById(R.id.mTextView_fs);


    }
    
    public void loaddata(){

    }
    
   
 
}