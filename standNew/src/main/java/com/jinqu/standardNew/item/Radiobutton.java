//
//  Radiobutton
//
//  Created by DELL on 2018-05-08 11:17:04
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



public class Radiobutton extends BaseItem{


	@SuppressLint("InflateParams")
    public static View getView(Context context,ViewGroup parent){
	     LayoutInflater flater = LayoutInflater.from(context);
	     View convertView = flater.inflate(R.layout.item_radiobutton,null);
	     convertView.setTag( new Radiobutton(convertView));
	     return convertView;
	}

	public Radiobutton(View view){
		this.contentview=view;
		this.context=contentview.getContext();
		initView();
	}
    
    private void initView() {
    	this.contentview.setTag(this);
    	findVMethod();
    }

    private void findVMethod(){


    }

    public void set(String item){

    }
    
    

}