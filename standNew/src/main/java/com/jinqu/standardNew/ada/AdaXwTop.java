//
//  AdaXwTop
//
//  Created by DELL on 2018-05-08 11:17:04
//  Copyright (c) DELL All rights reserved.


/**
   
*/

package com.jinqu.standardNew.ada;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.jinqu.standardNew.item.XwTop;
import com.mdx.framework.adapter.MAdapter;

import java.util.List;

public class AdaXwTop extends MAdapter<String>{

   public AdaXwTop(Context context, List<String> list) {
        super(context, list);
    }


 	@Override
    public View getview(int position, View convertView, ViewGroup parent) {
        String item = get(position);
        if (convertView == null) {
            convertView = XwTop.getView(getContext(), parent);;
        }
        XwTop mXwTop=(XwTop) convertView.getTag();
//        mXwTop.set(item);
        return convertView;
    }
}
