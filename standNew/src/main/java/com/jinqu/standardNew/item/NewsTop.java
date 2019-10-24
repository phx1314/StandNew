//
//  NewsTop
//
//  Created by DELL on 2018-07-09 15:34:02
//  Copyright (c) DELL All rights reserved.


/**
   
*/

package com.jinqu.standardNew.item;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jinqu.standardNew.R;
import com.jinqu.standardNew.model.ModelNewsImges;
import com.mdx.framework.commons.ParamsManager;
import com.mdx.framework.widget.MImageView;



public class NewsTop extends BaseItem{
    public MImageView mMImageView;
    public TextView mTextView;


	@SuppressLint("InflateParams")
    public static View getView(Context context,ViewGroup parent){
	     LayoutInflater flater = LayoutInflater.from(context);
	     View convertView = flater.inflate(R.layout.item_news_top,null);
	     convertView.setTag( new NewsTop(convertView));
	     return convertView;
	}

	public NewsTop(View view){
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
        mTextView=(TextView)contentview.findViewById(R.id.mTextView);


    }

    public void set(ModelNewsImges.RowsBean item){
		mMImageView.setObj(ParamsManager.get("image_star") +item.NewsImage );
		mTextView.setText(item.NewsTitle);
    }
    
    

}