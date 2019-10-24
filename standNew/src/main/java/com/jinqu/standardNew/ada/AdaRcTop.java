//
//  AdaRcTop
//
//  Created by DELL on 2018-05-08 11:17:04
//  Copyright (c) DELL All rights reserved.


/**
   
*/

package com.jinqu.standardNew.ada;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.jinqu.standardNew.item.RcTop;
import com.mdx.framework.adapter.MAdapter;

import java.util.List;

public class AdaRcTop extends MAdapter<String>{

   public AdaRcTop(Context context, List<String> list) {
        super(context, list);
    }


 	@Override
    public View getview(int position, View convertView, ViewGroup parent) {
        String item = get(position);
        if (convertView == null) {
            convertView = RcTop.getView(getContext(), parent);;
        }
        RcTop mRcTop=(RcTop) convertView.getTag();
//        mRcTop.set(item);
        return convertView;
    }
}
