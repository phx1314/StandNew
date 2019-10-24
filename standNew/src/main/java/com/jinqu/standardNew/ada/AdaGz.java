//
//  AdaGz
//
//  Created by DELL on 2018-05-08 11:17:04
//  Copyright (c) DELL All rights reserved.


/**
   
*/

package com.jinqu.standardNew.ada;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.jinqu.standardNew.item.Gz;
import com.jinqu.standardNew.model.ModelGzList;
import com.mdx.framework.adapter.MAdapter;

import java.util.List;

public class AdaGz extends MAdapter<ModelGzList.RowsBean>{

   public AdaGz(Context context, List<ModelGzList.RowsBean> list) {
        super(context, list);
    }


 	@Override
    public View getview(int position, View convertView, ViewGroup parent) {
        ModelGzList.RowsBean item = get(position);
        if (convertView == null) {
            convertView = Gz.getView(getContext(), parent);;
        }
        Gz mGz=(Gz) convertView.getTag();
        mGz.set(item);
        return convertView;
    }
}
