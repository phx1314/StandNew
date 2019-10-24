//
//  Yindao
//
//  Created by DELL on 2017-03-24 11:36:25
//  Copyright (c) DELL All rights reserved.


/**
   
*/

package com.jinqu.standardNew.item;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.jinqu.standardNew.R;


public class Yindao extends BaseItem{
	public LinearLayout mLinearLayout;


	@SuppressLint("InflateParams")
    public static View getView(Context context,ViewGroup parent){
	     LayoutInflater flater = LayoutInflater.from(context);
	     View convertView = flater.inflate(R.layout.item_yindao,null);
	     convertView.setTag(new Yindao(convertView));
	     return convertView;
	}

	public Yindao(View view){
		this.contentview=view;
		this.context=contentview.getContext();
		initView();
	}
    
    private void initView() {
    	this.contentview.setTag(this);
    	findVMethod();
    }

    private void findVMethod(){
		mLinearLayout = (LinearLayout) findViewById(R.id.mLinearLayout);


	}

    public void addView(View item){
		item.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT));
		mLinearLayout.addView(item);
    }
    
    

}