//
//  AdaXz
//
//  Created by DELL on 2018-07-18 15:12:13
//  Copyright (c) DELL All rights reserved.


/**

 */

package com.framewidget.ada;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.framewidget.item.Xz;
import com.framewidget.model.ModelDx;
import com.mdx.framework.adapter.MAdapter;

import java.util.List;

public class AdaXz extends MAdapter<ModelDx> {

    public AdaXz(Context context, List<ModelDx> list) {
        super(context, list);
    }


    @Override
    public View getview(int position, View convertView, ViewGroup parent) {
        final ModelDx item = get(position);
        if (convertView == null) {
            convertView = Xz.getView(getContext(), parent);
        }
        Xz mXz = (Xz) convertView.getTag();
        mXz.set(item);
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                item.isChecked = !item.isChecked;
                AdaXz.this.notifyDataSetChanged();
            }
        });
        return convertView;
    }
}
