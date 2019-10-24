//
//  FrgQzcy
//
//  Created by DELL on 2018-05-17 17:13:01
//  Copyright (c) DELL All rights reserved.


/**
   
*/

package com.jinqu.standardNew.frg;
import android.os.Bundle;

import com.jinqu.standardNew.R;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;



public class FrgQzcy extends BaseFrg{

    public TabLayout mTabLayout;
    public ViewPager mViewPager;


 	@Override
    protected void create(Bundle savedInstanceState) {
        setContentView(R.layout.frg_qzcy);
        initView();
        loaddata();
    }

    private void initView(){
        findVMethod();
    }
    
    private void findVMethod() {
        mTabLayout=(TabLayout)findViewById(R.id.mTabLayout);
        mViewPager=(ViewPager)findViewById(R.id.mViewPager);


    }
    
    public void loaddata(){

    }
    
   
 
}