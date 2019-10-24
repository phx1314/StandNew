//
//  AdaBd
//
//  Created by DELL on 2018-05-08 11:17:04
//  Copyright (c) DELL All rights reserved.


/**
   
*/

package com.jinqu.standardNew.ada;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.jinqu.standardNew.item.Bd;
import com.jinqu.standardNew.model.ModelRw;
import com.mdx.framework.adapter.MAdapter;

import java.util.List;

public class AdaRw extends MAdapter<ModelRw.RowsBean>{

   public AdaRw(Context context, List<ModelRw.RowsBean> list) {
        super(context, list);
    }


 	@Override
    public View getview(int position, View convertView, ViewGroup parent) {
        ModelRw.RowsBean item = get(position);
        if (convertView == null) {
            convertView = Bd.getView(getContext(), parent);;
        }
        Bd mBd=(Bd) convertView.getTag();
        mBd.setRw(item);
        return convertView;
    }
}
