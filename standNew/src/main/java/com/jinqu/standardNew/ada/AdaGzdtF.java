//
//  AdaGzdtF
//
//  Created by DELL on 2018-05-17 15:54:34
//  Copyright (c) DELL All rights reserved.


/**

 */

package com.jinqu.standardNew.ada;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.jinqu.standardNew.frg.FrgGzdt;
import com.jinqu.standardNew.item.GzdtF;
import com.jinqu.standardNew.model.ModelxmdtList;
import com.mdx.framework.activity.TitleAct;
import com.mdx.framework.adapter.MAdapter;
import com.mdx.framework.utility.Helper;

import java.util.List;

import static com.jinqu.standardNew.frg.BaseFrg.setBgClickClor;

public class AdaGzdtF extends MAdapter<ModelxmdtList.RowsBean> {

    public AdaGzdtF(Context context, List<ModelxmdtList.RowsBean> list) {
        super(context, list);
    }


    @Override
    public View getview(int position, View convertView, ViewGroup parent) {
        final ModelxmdtList.RowsBean item = get(position);
        if (convertView == null) {
            convertView = GzdtF.getView(getContext(), parent);
        }
        GzdtF mGzdtF = (GzdtF) convertView.getTag();
        mGzdtF.set(item);
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setBgClickClor(v);
                Helper.startActivity(getContext(), FrgGzdt.class, TitleAct.class, "TaskGroupId", item.TaskGroupId + "");
            }
        });
        return convertView;
    }
}
