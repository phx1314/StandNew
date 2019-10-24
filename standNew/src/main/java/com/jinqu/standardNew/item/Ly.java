//
//  Ly
//
//  Created by DELL on 2018-05-17 16:45:29
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
import com.mdx.framework.widget.MImageView;
import android.widget.TextView;



public class Ly extends BaseItem{
    public MImageView mMImageView;
    public TextView mTextView_name;
    public TextView mTextView_time;
    public TextView mTextView_content;


	@SuppressLint("InflateParams")
    public static View getView(Context context,ViewGroup parent){
	     LayoutInflater flater = LayoutInflater.from(context);
	     View convertView = flater.inflate(R.layout.item_ly,null);
	     convertView.setTag( new Ly(convertView));
	     return convertView;
	}

	public Ly(View view){
		this.contentview=view;
		this.context=contentview.getContext();
		initView();
	}
    
    private void initView() {
    	this.contentview.setTag(this);
    	findVMethod();
    }

    private void findVMethod(){
        mMImageView=(MImageView)contentview.findViewById(R.id.mMImageView);
        mTextView_name=(TextView)contentview.findViewById(R.id.mTextView_name);
        mTextView_time=(TextView)contentview.findViewById(R.id.mTextView_time);
        mTextView_content=(TextView)contentview.findViewById(R.id.mTextView_content);


    }

    public void set(String item){

    }
    
    

}