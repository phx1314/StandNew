//
//  AdaMimaDialog
//
//  Created by DELL on 2018-07-19 08:47:48
//  Copyright (c) DELL All rights reserved.


/**
   
*/

package com.jinqu.standardNew.ada;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.jinqu.standardNew.item.MimaDialog;
import com.mdx.framework.adapter.MAdapter;

import java.util.List;

public class AdaMimaDialog extends MAdapter<String>{

   public AdaMimaDialog(Context context, List<String> list) {
        super(context, list);
    }


 	@Override
    public View getview(int position, View convertView, ViewGroup parent) {
        String item = get(position);
        if (convertView == null) {
            convertView = MimaDialog.getView(getContext(), parent);;
        }
        MimaDialog mMimaDialog=(MimaDialog) convertView.getTag();
//        mMimaDialog.set(item);
        return convertView;
    }
}
