//
//  AdaSousuoTop
//
//  Created by DELL on 2018-06-29 11:12:07
//  Copyright (c) DELL All rights reserved.


/**
   
*/

package com.jinqu.standardNew.ada;

import java.util.List;
import com.mdx.framework.adapter.MAdapter;
import android.content.Context;
import android.view.ViewGroup;
import android.view.View;

import com.jinqu.standardNew.item.SousuoTop;

public class AdaSousuoTop extends MAdapter<String>{

   public AdaSousuoTop(Context context, List<String> list) {
        super(context, list);
    }


 	@Override
    public View getview(int position, View convertView, ViewGroup parent) {
        String item = get(position);
        if (convertView == null) {
            convertView = SousuoTop.getView(getContext(), parent);;
        }
        SousuoTop mSousuoTop=(SousuoTop) convertView.getTag();
        mSousuoTop.set(item);
        return convertView;
    }
}
