//
//  AdaBdSon
//
//  Created by DELL on 2018-07-25 09:16:52
//  Copyright (c) DELL All rights reserved.


/**
   
*/

package com.jinqu.standardNew.ada;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.jinqu.standardNew.item.BdSon;
import com.mdx.framework.adapter.MAdapter;

import java.util.List;

public class AdaBdSon extends MAdapter<String>{

   public AdaBdSon(Context context, List<String> list) {
        super(context, list);
    }


 	@Override
    public View getview(int position, View convertView, ViewGroup parent) {
        String item = get(position);
        if (convertView == null) {
            convertView = BdSon.getView(getContext(), parent);;
        }
        BdSon mBdSon=(BdSon) convertView.getTag();
//        mBdSon.set(item);
        return convertView;
    }
}
