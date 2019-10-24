//
//  AdaEmailList
//
//  Created by DELL on 2018-06-21 14:01:00
//  Copyright (c) DELL All rights reserved.


/**

 */

package com.jinqu.standardNew.ada;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.jinqu.standardNew.item.EmailList;
import com.jinqu.standardNew.model.ModelYjList;
import com.mdx.framework.adapter.MAdapter;

import java.util.List;

public class AdaEmailList extends MAdapter<ModelYjList.RowsBean> {
    public int type = 1;

    public AdaEmailList(Context context, List<ModelYjList.RowsBean> list, int type) {
        super(context, list);
        this.type = type;
    }


    @Override
    public View getview(int position, View convertView, ViewGroup parent) {
        final ModelYjList.RowsBean item = get(position);
        if (convertView == null) {
            convertView = new EmailList(getContext());
        }
        ((EmailList) convertView).set(item, type, this);
        return convertView;
    }
}
