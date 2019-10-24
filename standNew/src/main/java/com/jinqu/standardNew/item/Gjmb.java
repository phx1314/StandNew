//
//  Gjmb
//
//  Created by DELL on 2018-05-17 15:08:30
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



public class Gjmb extends BaseItem{
    public TextView mTextView_title;
    public TextView mTextView_jy;
    public TextView mTextView_file;
    public TextView mTextView_user;
    public TextView mTextView_time;
    public TextView mTextView_xg;
    public TextView mTextView_tg;
    public TextView mTextView_ck;
    public TextView mTextView_ly;


	@SuppressLint("InflateParams")
    public static View getView(Context context,ViewGroup parent){
	     LayoutInflater flater = LayoutInflater.from(context);
	     View convertView = flater.inflate(R.layout.item_gjmb,null);
	     convertView.setTag( new Gjmb(convertView));
	     return convertView;
	}

	public Gjmb(View view){
		this.contentview=view;
		this.context=contentview.getContext();
		initView();
	}
    
    private void initView() {
    	this.contentview.setTag(this);
    	findVMethod();
    }

    private void findVMethod(){
        mTextView_title=(TextView)contentview.findViewById(R.id.mTextView_title);
        mTextView_jy=(TextView)contentview.findViewById(R.id.mTextView_jy);
        mTextView_file=(TextView)contentview.findViewById(R.id.mTextView_file);
        mTextView_user=(TextView)contentview.findViewById(R.id.mTextView_user);
        mTextView_time=(TextView)contentview.findViewById(R.id.mTextView_time);
        mTextView_xg=(TextView)contentview.findViewById(R.id.mTextView_xg);
        mTextView_tg=(TextView)contentview.findViewById(R.id.mTextView_tg);
        mTextView_ck=(TextView)contentview.findViewById(R.id.mTextView_ck);
        mTextView_ly=(TextView)contentview.findViewById(R.id.mTextView_ly);


    }

    public void set(String item){

    }
    
    

}