//
//  AdaShpjnduList
//
//  Created by DELL on 2017-05-09 15:58:26
//  Copyright (c) DELL All rights reserved.


/**

 */

package com.jinqu.standardNew.ada;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.jinqu.standardNew.item.ShpjnduList;
import com.jinqu.standardNew.model.ModelShPjdList;
import com.mdx.framework.adapter.MAdapter;

import java.util.List;

public class AdaShpjnduList extends MAdapter<ModelShPjdList.RowsBean> {

    public AdaShpjnduList(Context context, List<ModelShPjdList.RowsBean> list) {
        super(context, list);
    }


    @Override
    public View getview(int position, View convertView, ViewGroup parent) {
        ModelShPjdList.RowsBean item = get(position);
        if (convertView == null) {
            convertView = ShpjnduList.getView(getContext(), parent);
        }
        ShpjnduList mShpjnduList = (ShpjnduList) convertView.getTag();
        mShpjnduList.set(item, position);
        return convertView;
    }
}
