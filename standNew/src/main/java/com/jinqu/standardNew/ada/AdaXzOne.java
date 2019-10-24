//
//  AdaTxl
//
//  Created by DELL on 2017-03-30 10:31:49
//  Copyright (c) DELL All rights reserved.


/**

 */

package com.jinqu.standardNew.ada;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.jinqu.standardNew.item.Txl;
import com.jinqu.standardNew.model.ModelEmploee;
import com.mdx.framework.Frame;
import com.mdx.framework.adapter.MAdapter;

import java.util.List;

public class AdaXzOne extends MAdapter<ModelEmploee.RowsBean> {
    public void setType(int type) {
        this.type = type;
        this.notifyDataSetChanged();
    }

    public int type;

    public AdaXzOne(Context context, List<ModelEmploee.RowsBean> list, int type) {
        super(context, list);
        this.type = type;
    }

    @Override
    public View getview(int position, View convertView, ViewGroup parent) {
        final ModelEmploee.RowsBean item = get(position);
        if (convertView == null) {
            convertView = Txl.getView(getContext(), parent);
        }
        Txl mTxl = (Txl) convertView.getTag();
        mTxl.set(item, position, getList(), type);
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Frame.HANDLES.sentAll("FrgXzRenyuanOne", 0, item);
            }
        });
        return convertView;
    }
}
