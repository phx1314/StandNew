//
//  AdaFujianList
//
//  Created by DELL on 2018-05-15 17:26:07
//  Copyright (c) DELL All rights reserved.


/**

 */

package com.jinqu.standardNew.ada;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.jinqu.standardNew.item.FujianList;
import com.jinqu.standardNew.model.ModelFjList;
import com.mdx.framework.adapter.MAdapter;

import java.util.List;

public class AdaFujianList extends MAdapter<ModelFjList.RowsBean> {
    boolean canDelete;
    String refTable;

    public AdaFujianList(Context context, List<ModelFjList.RowsBean> list, String refTable, boolean canDelete) {
        super(context, list);
        this.canDelete = canDelete;
        this.refTable = refTable;
    }


    @Override
    public View getview(int position, View convertView, ViewGroup parent) {
        ModelFjList.RowsBean item = get(position);
        if (convertView == null) {
            convertView = new FujianList(getContext());
        }
        ((FujianList) convertView).set(item, refTable, this,position, canDelete);
        return convertView;
    }
}
