//
//  FrgWr
//
//  Created by DELL on 2018-07-16 10:25:12
//  Copyright (c) DELL All rights reserved.


/**
   
*/

package com.jinqu.standardNew.frg;
import android.content.Context;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.jinqu.standardNew.R;
import com.mdx.framework.widget.ActionBar;



public class FrgWr extends BaseFrg{

    public LinearLayout mLinearLayout_1;
    public ToggleButton mToggleButton1;
    public LinearLayout mLinearLayout_2;
    public TextView mTextView2;
    public LinearLayout mLinearLayout_3;
    public TextView mTextView3;


 	@Override
    protected void create(Bundle savedInstanceState) {
        setContentView(R.layout.frg_wr);
        initView();
        loaddata();
    }

    private void initView(){
        findVMethod();
    }
    
    private void findVMethod() {
        mLinearLayout_1=(LinearLayout)findViewById(R.id.mLinearLayout_1);
        mToggleButton1=(ToggleButton)findViewById(R.id.mToggleButton1);
        mLinearLayout_2=(LinearLayout)findViewById(R.id.mLinearLayout_2);
        mTextView2=(TextView)findViewById(R.id.mTextView2);
        mLinearLayout_3=(LinearLayout)findViewById(R.id.mLinearLayout_3);
        mTextView3=(TextView)findViewById(R.id.mTextView3);


    }
    
    public void loaddata(){

    }

    @Override
    public void setActionBar(ActionBar actionBar, Context context) {
        super.setActionBar(actionBar, context);
        mHeadlayout.setTitle("勿扰模式");
    }
 
}