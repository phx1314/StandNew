//
//  LocationAddress
//
//  Created by wchj on 2015-07-21 16:20:09
//  Copyright (c) wchj All rights reserved.


/**
   
*/

package com.jinqu.standardNew.item;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.framewidget.view.Pois;
import com.jinqu.standardNew.R;


public class LocationAddress extends BaseItem{
    public TextView tv_title;
    public TextView tv_address;


	@SuppressLint("InflateParams")
    public static View getView(Context context,ViewGroup parent){
	     LayoutInflater flater = LayoutInflater.from(context);
	     View convertView = flater.inflate(R.layout.item_wm_location_address,null);
	     convertView.setTag( new LocationAddress(convertView));
	     return convertView;
	}

	public LocationAddress(View view){
		this.contentview=view;
		this.context=contentview.getContext();
		initView();
	}
    
    private void initView() {
    	this.contentview.setTag(this);
        tv_title=(TextView)contentview.findViewById(R.id.tv_title);
        tv_address=(TextView)contentview.findViewById(R.id.tv_address);


    }
    
    public void set(Pois item){
    	tv_title.setText(item.getTitle());
    	tv_address.setText(item.getAddress());
    }
    
    

}