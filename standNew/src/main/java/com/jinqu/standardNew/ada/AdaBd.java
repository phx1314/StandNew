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
import com.jinqu.standardNew.model.ModelBd;
import com.mdx.framework.adapter.MAdapter;

import java.util.List;

public class AdaBd extends MAdapter<ModelBd.RowsBean> {
    public String statusID;

    public AdaBd(Context context, List<ModelBd.RowsBean> list, String statusID) {
        super(context, list);
        this.statusID = statusID;
    }


    @Override
    public View getview(int position, View convertView, ViewGroup parent) {
        ModelBd.RowsBean item = get(position);
        if (convertView == null) {
            convertView = Bd.getView(getContext(), parent);
        }
        Bd mBd = (Bd) convertView.getTag();
        mBd.set(item, statusID);
        return convertView;
    }
}
