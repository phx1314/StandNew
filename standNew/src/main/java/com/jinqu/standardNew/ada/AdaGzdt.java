//
//  AdaGzdt
//
//  Created by DELL on 2018-05-17 15:37:26
//  Copyright (c) DELL All rights reserved.


/**

 */

package com.jinqu.standardNew.ada;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.jinqu.standardNew.frg.FrgGzdtZ;
import com.jinqu.standardNew.item.Gzdt;
import com.jinqu.standardNew.model.ModelgzdtList;
import com.mdx.framework.activity.TitleAct;
import com.mdx.framework.adapter.MAdapter;
import com.mdx.framework.utility.Helper;

import java.util.List;

import static com.jinqu.standardNew.frg.BaseFrg.setBgClickClor;

public class AdaGzdt extends MAdapter<ModelgzdtList.RowsBean> {

    public AdaGzdt(Context context, List<ModelgzdtList.RowsBean> list) {
        super(context, list);
    }


    @Override
    public View getview(int position, View convertView, ViewGroup parent) {
        final ModelgzdtList.RowsBean item = get(position);
        if (convertView == null) {
            convertView = Gzdt.getView(getContext(), parent);
        }
        Gzdt mGzdt = (Gzdt) convertView.getTag();
        mGzdt.set(item);
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setBgClickClor(v);
                Helper.startActivity(getContext(), FrgGzdtZ.class, TitleAct.class, "item", item);
            }
        });
        return convertView;
    }
}
