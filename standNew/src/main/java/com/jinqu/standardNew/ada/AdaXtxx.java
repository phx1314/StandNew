//
//  AdaXtxx
//
//  Created by DELL on 2018-07-19 13:17:46
//  Copyright (c) DELL All rights reserved.


/**

 */

package com.jinqu.standardNew.ada;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.jinqu.standardNew.item.Xtxx;
import com.jinqu.standardNew.model.ModelSystemList;
import com.mdx.framework.adapter.MAdapter;

import java.util.List;

public class AdaXtxx extends MAdapter<ModelSystemList.RowsBean> {

    public AdaXtxx(Context context, List<ModelSystemList.RowsBean> list) {
        super(context, list);
    }

    @Override
    public View getview(int position, View convertView, ViewGroup parent) {
        final ModelSystemList.RowsBean item = get(position);
        if (convertView == null) {
            convertView = new Xtxx(getContext());
        }
        ((Xtxx) convertView).set(item, this);
        return convertView;
    }
}
