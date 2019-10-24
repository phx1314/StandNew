//
//  AdaXmxxdjListSon
//
//  Created by DELL on 2018-08-22 15:31:25
//  Copyright (c) DELL All rights reserved.


/**
   
*/

package com.jinqu.standardNew.ada;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.jinqu.standardNew.item.XmxxdjListSon;
import com.mdx.framework.adapter.MAdapter;

import java.util.List;

public class AdaXmxxdjListSon extends MAdapter<String>{

   public AdaXmxxdjListSon(Context context, List<String> list) {
        super(context, list);
    }


 	@Override
    public View getview(int position, View convertView, ViewGroup parent) {
        String item = get(position);
        if (convertView == null) {
            convertView = XmxxdjListSon.getView(getContext(), parent);;
        }
        XmxxdjListSon mXmxxdjListSon=(XmxxdjListSon) convertView.getTag();
//        mXmxxdjListSon.set(item);
        return convertView;
    }
}
