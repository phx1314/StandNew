//
//  AdaGjmb
//
//  Created by DELL on 2018-05-17 15:08:30
//  Copyright (c) DELL All rights reserved.


/**
   
*/

package com.jinqu.standardNew.ada;

import java.util.List;
import com.mdx.framework.adapter.MAdapter;
import android.content.Context;
import android.view.ViewGroup;
import android.view.View;

import com.jinqu.standardNew.item.Gjmb;

public class AdaGjmb extends MAdapter<String>{

   public AdaGjmb(Context context, List<String> list) {
        super(context, list);
    }


 	@Override
    public View getview(int position, View convertView, ViewGroup parent) {
        String item = get(position);
        if (convertView == null) {
            convertView = Gjmb.getView(getContext(), parent);;
        }
        Gjmb mGjmb=(Gjmb) convertView.getTag();
        mGjmb.set(item);
        return convertView;
    }
}
