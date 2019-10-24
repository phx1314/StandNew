//
//  Rl
//
//  Created by DELL on 2018-05-08 13:36:51
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
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ImageView;



public class Rl extends BaseItem{
    public RelativeLayout mRelativeLayout_pt;
    public TextView mTextView_xiu;
    public TextView mTextView_num;
    public TextView mTextView_nl;
    public ImageView mMImageView_dot;


	@SuppressLint("InflateParams")
    public static View getView(Context context,ViewGroup parent){
	     LayoutInflater flater = LayoutInflater.from(context);
	     View convertView = flater.inflate(R.layout.item_rl,null);
	     convertView.setTag( new Rl(convertView));
	     return convertView;
	}

	public Rl(View view){
		this.contentview=view;
		this.context=contentview.getContext();
		initView();
	}
    
    private void initView() {
    	this.contentview.setTag(this);
    	findVMethod();
    }

    private void findVMethod(){
        mRelativeLayout_pt=(RelativeLayout)contentview.findViewById(R.id.mRelativeLayout_pt);
        mTextView_xiu=(TextView)contentview.findViewById(R.id.mTextView_xiu);
        mTextView_num=(TextView)contentview.findViewById(R.id.mTextView_num);
        mTextView_nl=(TextView)contentview.findViewById(R.id.mTextView_nl);
        mMImageView_dot=(ImageView)contentview.findViewById(R.id.mMImageView_dot);


    }

    public void set(String item){

    }
    
    

}