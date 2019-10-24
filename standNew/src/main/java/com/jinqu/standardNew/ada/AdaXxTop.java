//
//  AdaXxTop
//
//  Created by DELL on 2018-07-23 09:01:24
//  Copyright (c) DELL All rights reserved.


/**
   
*/

package com.jinqu.standardNew.ada;

import java.util.List;
import com.mdx.framework.adapter.MAdapter;
import android.content.Context;
import android.view.ViewGroup;
import android.view.View;

import com.jinqu.standardNew.item.XxTop;

public class AdaXxTop extends MAdapter<String>{

   public AdaXxTop(Context context, List<String> list) {
        super(context, list);
    }


 	@Override
    public View getview(int position, View convertView, ViewGroup parent) {
        String item = get(position);
        if (convertView == null) {
            convertView = XxTop.getView(getContext(), parent);;
        }
        XxTop mXxTop=(XxTop) convertView.getTag();
        mXxTop.set(item);
        return convertView;
    }
}
