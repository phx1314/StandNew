//
//  AdaBottom
//
//  Created by DELL on 2018-10-29 09:44:40
//  Copyright (c) DELL All rights reserved.


/**
   
*/

package com.framewidget.ada;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.framewidget.item.Bottom;
import com.mdx.framework.adapter.MAdapter;

import java.util.List;

public class AdaBottom extends MAdapter<String>{

   public AdaBottom(Context context, List<String> list) {
        super(context, list);
    }


 	@Override
    public View getview(int position, View convertView, ViewGroup parent) {
        String item = get(position);
        if (convertView == null) {
            convertView = Bottom.getView(getContext(), parent);;
        }
        Bottom mBottom=(Bottom) convertView.getTag();
//        mBottom.set(item);
        return convertView;
    }
}
