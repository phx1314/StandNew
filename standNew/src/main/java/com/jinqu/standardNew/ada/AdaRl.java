//
//  AdaRl
//
//  Created by DELL on 2018-05-08 13:36:51
//  Copyright (c) DELL All rights reserved.


/**
   
*/

package com.jinqu.standardNew.ada;

import java.util.List;
import com.mdx.framework.adapter.MAdapter;
import android.content.Context;
import android.view.ViewGroup;
import android.view.View;

import com.jinqu.standardNew.item.Rl;

public class AdaRl extends MAdapter<String>{

   public AdaRl(Context context, List<String> list) {
        super(context, list);
    }


 	@Override
    public View getview(int position, View convertView, ViewGroup parent) {
        String item = get(position);
        if (convertView == null) {
            convertView = Rl.getView(getContext(), parent);;
        }
        Rl mRl=(Rl) convertView.getTag();
        mRl.set(item);
        return convertView;
    }
}
