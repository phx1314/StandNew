//
//  FrgJsDetail
//
//  Created by DELL on 2018-05-17 16:31:56
//  Copyright (c) DELL All rights reserved.


/**
   
*/

package com.jinqu.standardNew.frg;
import android.os.Bundle;

import com.jinqu.standardNew.R;

import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;



public class FrgJsDetail extends BaseFrg{

    public EditText mEditText_title;
    public LinearLayout mLinearLayout_user;
    public TextView mTextView_user;
    public LinearLayout mLinearLayout_type;
    public TextView mTextView_type;
    public EditText mEditText_content;
    public LinearLayout mLinearLayout_fj;
    public TextView mTextView_count;


 	@Override
    protected void create(Bundle savedInstanceState) {
        setContentView(R.layout.frg_js_detail);
        initView();
        loaddata();
    }

    private void initView(){
        findVMethod();
    }
    
    private void findVMethod() {
        mEditText_title=(EditText)findViewById(R.id.mEditText_title);
        mLinearLayout_user=(LinearLayout)findViewById(R.id.mLinearLayout_user);
        mTextView_user=(TextView)findViewById(R.id.mTextView_user);
        mLinearLayout_type=(LinearLayout)findViewById(R.id.mLinearLayout_type);
        mTextView_type=(TextView)findViewById(R.id.mTextView_type);
        mEditText_content=(EditText)findViewById(R.id.mEditText_content);
        mLinearLayout_fj=(LinearLayout)findViewById(R.id.mLinearLayout_fj);
        mTextView_count=(TextView)findViewById(R.id.mTextView_count);


    }
    
    public void loaddata(){

    }
    
   
 
}