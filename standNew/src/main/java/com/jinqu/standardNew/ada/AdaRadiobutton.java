//
//  AdaRadiobutton
//
//  Created by DELL on 2018-05-08 11:17:04
//  Copyright (c) DELL All rights reserved.


/**
   
*/

package com.jinqu.standardNew.ada;

import java.util.List;
import com.mdx.framework.adapter.MAdapter;
import android.content.Context;
import android.view.ViewGroup;
import android.view.View;

import com.jinqu.standardNew.item.Radiobutton;

public class AdaRadiobutton extends MAdapter<String>{

   public AdaRadiobutton(Context context, List<String> list) {
        super(context, list);
    }


 	@Override
    public View getview(int position, View convertView, ViewGroup parent) {
        String item = get(position);
        if (convertView == null) {
            convertView = Radiobutton.getView(getContext(), parent);;
        }
        Radiobutton mRadiobutton=(Radiobutton) convertView.getTag();
        mRadiobutton.set(item);
        return convertView;
    }
}
