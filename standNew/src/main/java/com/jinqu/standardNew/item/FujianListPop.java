//
//  FujianListPop
//
//  Created by DELL on 2018-05-17 10:34:00
//  Copyright (c) DELL All rights reserved.


/**
   
*/

package com.jinqu.standardNew.item;

import com.jinqu.standardNew.R;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import android.view.View;
import android.widget.TextView;



public class FujianListPop extends BaseItem{
    public TextView mTextView_sp;
    public TextView mTextView_yp;
    public TextView mTextView_wz;
    public TextView mTextView_more;


	@SuppressLint("InflateParams")
    public static View getView(Context context,ViewGroup parent){
	     LayoutInflater flater = LayoutInflater.from(context);
	     View convertView = flater.inflate(R.layout.item_fujian_list_pop,null);
	     convertView.setTag( new FujianListPop(convertView));
	     return convertView;
	}

	public FujianListPop(View view){
		this.contentview=view;
		this.context=contentview.getContext();
		initView();
	}
    
    private void initView() {
    	this.contentview.setTag(this);
    	findVMethod();
    }

    private void findVMethod(){
        mTextView_sp=(TextView)contentview.findViewById(R.id.mTextView_sp);
        mTextView_yp=(TextView)contentview.findViewById(R.id.mTextView_yp);
        mTextView_wz=(TextView)contentview.findViewById(R.id.mTextView_wz);
        mTextView_more=(TextView)contentview.findViewById(R.id.mTextView_more);


    }

    public void set(String item){

    }
    
    

}