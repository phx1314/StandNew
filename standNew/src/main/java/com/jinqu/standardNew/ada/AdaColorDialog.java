//
//  AdaColorDialog
//
//  Created by DELL on 2018-05-24 09:21:08
//  Copyright (c) DELL All rights reserved.


/**
   
*/

package com.jinqu.standardNew.ada;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.jinqu.standardNew.item.ColorDialog;
import com.mdx.framework.adapter.MAdapter;

import java.util.List;

public class AdaColorDialog extends MAdapter<String>{

   public AdaColorDialog(Context context, List<String> list) {
        super(context, list);
    }


 	@Override
    public View getview(int position, View convertView, ViewGroup parent) {
        String item = get(position);
        if (convertView == null) {
            convertView = ColorDialog.getView(getContext(), parent);;
        }
        ColorDialog mColorDialog=(ColorDialog) convertView.getTag();
//        mColorDialog.set(item);
        return convertView;
    }
}
