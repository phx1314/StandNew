//
//  AdaGzdtZ
//
//  Created by DELL on 2018-05-17 15:59:39
//  Copyright (c) DELL All rights reserved.


/**
   
*/

package com.jinqu.standardNew.ada;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.jinqu.standardNew.item.GzdtZ;
import com.jinqu.standardNew.model.ModelDtetail;
import com.mdx.framework.adapter.MAdapter;

import java.util.List;

public class AdaGzdtZ extends MAdapter<ModelDtetail.RowsBean>{

   public AdaGzdtZ(Context context, List<ModelDtetail.RowsBean> list) {
        super(context, list);
    }


 	@Override
    public View getview(int position, View convertView, ViewGroup parent) {
        ModelDtetail.RowsBean item = get(position);
        if (convertView == null) {
            convertView = GzdtZ.getView(getContext(), parent);;
        }
        GzdtZ mGzdtZ=(GzdtZ) convertView.getTag();
        mGzdtZ.set(item);
        return convertView;
    }
}
