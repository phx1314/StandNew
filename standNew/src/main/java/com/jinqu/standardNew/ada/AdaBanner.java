//
//  AdaBanner
//
//  Created by DELL on 2018-05-08 11:17:04
//  Copyright (c) DELL All rights reserved.


/**
   
*/

package com.jinqu.standardNew.ada;

import java.util.List;
import com.mdx.framework.adapter.MAdapter;
import android.content.Context;
import android.view.ViewGroup;
import android.view.View;

import com.jinqu.standardNew.item.Banner;

public class AdaBanner extends MAdapter<String>{

   public AdaBanner(Context context, List<String> list) {
        super(context, list);
    }


 	@Override
    public View getview(int position, View convertView, ViewGroup parent) {
        String item = get(position);
        if (convertView == null) {
            convertView = Banner.getView(getContext(), parent);;
        }
        Banner mBanner=(Banner) convertView.getTag();
        mBanner.set(item);
        return convertView;
    }
}
