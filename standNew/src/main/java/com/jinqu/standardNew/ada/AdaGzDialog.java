//
//  AdaGzDialog
//
//  Created by DELL on 2018-07-23 14:19:57
//  Copyright (c) DELL All rights reserved.


/**
   
*/

package com.jinqu.standardNew.ada;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.jinqu.standardNew.item.GzDialog;
import com.mdx.framework.adapter.MAdapter;

import java.util.List;

public class AdaGzDialog extends MAdapter<String>{

   public AdaGzDialog(Context context, List<String> list) {
        super(context, list);
    }


 	@Override
    public View getview(int position, View convertView, ViewGroup parent) {
        String item = get(position);
        if (convertView == null) {
            convertView = GzDialog.getView(getContext(), parent);;
        }
        GzDialog mGzDialog=(GzDialog) convertView.getTag();
//        mGzDialog.set(item);
        return convertView;
    }
}
