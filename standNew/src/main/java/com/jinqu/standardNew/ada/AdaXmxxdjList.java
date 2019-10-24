//
//  AdaXmxxdjList
//
//  Created by DELL on 2018-08-22 15:48:14
//  Copyright (c) DELL All rights reserved.


/**

 */

package com.jinqu.standardNew.ada;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.jinqu.standardNew.item.XmxxdjList;
import com.jinqu.standardNew.model.ModelBd;
import com.mdx.framework.adapter.MAdapter;

import java.util.List;

public class AdaXmxxdjList extends MAdapter<ModelBd.RowsBean> {
    public static int position = -1;
    public static LinearLayout childrenLayout_k;

    public AdaXmxxdjList(Context context, List<ModelBd.RowsBean> list ) {
        super(context, list);
        position = -1;
        childrenLayout_k = null;
    }


    @Override
    public View getview(int position, View convertView, ViewGroup parent) {
        ModelBd.RowsBean item = get(position);
        if (convertView == null) {
            convertView = XmxxdjList.getView(getContext(), parent);
        }
        XmxxdjList mXmxxdjList = (XmxxdjList) convertView.getTag();
        mXmxxdjList.set(item, position, this );

        return convertView;
    }
}
