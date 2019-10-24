//
//  AdaLy
//
//  Created by DELL on 2018-05-17 16:45:29
//  Copyright (c) DELL All rights reserved.


/**
   
*/

package com.jinqu.standardNew.ada;

import java.util.List;
import com.mdx.framework.adapter.MAdapter;
import android.content.Context;
import android.view.ViewGroup;
import android.view.View;

import com.jinqu.standardNew.item.Ly;

public class AdaLy extends MAdapter<String>{

   public AdaLy(Context context, List<String> list) {
        super(context, list);
    }


 	@Override
    public View getview(int position, View convertView, ViewGroup parent) {
        String item = get(position);
        if (convertView == null) {
            convertView = Ly.getView(getContext(), parent);;
        }
        Ly mLy=(Ly) convertView.getTag();
        mLy.set(item);
        return convertView;
    }
}
