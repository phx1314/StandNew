//
//  AdaFujianListPop
//
//  Created by DELL on 2018-05-17 10:34:00
//  Copyright (c) DELL All rights reserved.


/**
   
*/

package com.jinqu.standardNew.ada;

import java.util.List;
import com.mdx.framework.adapter.MAdapter;
import android.content.Context;
import android.view.ViewGroup;
import android.view.View;

import com.jinqu.standardNew.item.FujianListPop;

public class AdaFujianListPop extends MAdapter<String>{

   public AdaFujianListPop(Context context, List<String> list) {
        super(context, list);
    }


 	@Override
    public View getview(int position, View convertView, ViewGroup parent) {
        String item = get(position);
        if (convertView == null) {
            convertView = FujianListPop.getView(getContext(), parent);;
        }
        FujianListPop mFujianListPop=(FujianListPop) convertView.getTag();
        mFujianListPop.set(item);
        return convertView;
    }
}
