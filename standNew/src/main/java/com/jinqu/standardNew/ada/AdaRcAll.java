//
//  AdaRcAll
//
//  Created by DELL on 2018-08-07 09:04:31
//  Copyright (c) DELL All rights reserved.


/**

 */

package com.jinqu.standardNew.ada;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.jinqu.standardNew.frg.FrgCreateRcDetail;
import com.jinqu.standardNew.item.RcAll;
import com.jinqu.standardNew.model.ModelRcAll;
import com.mdx.framework.activity.TitleAct;
import com.mdx.framework.adapter.MAdapter;
import com.mdx.framework.utility.Helper;

import java.util.List;

public class AdaRcAll extends MAdapter<ModelRcAll.RowsBean> {

    public AdaRcAll(Context context, List<ModelRcAll.RowsBean> list) {
        super(context, list);
    }


    @Override
    public View getview(int position, View convertView, ViewGroup parent) {
        final ModelRcAll.RowsBean item = get(position);
        if (convertView == null) {
            convertView = RcAll.getView(getContext(), parent);
        }
        RcAll mRcAll = (RcAll) convertView.getTag();
        mRcAll.set(item, position, getList());
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Helper.startActivity(getContext(), FrgCreateRcDetail.class, TitleAct.class, "id", item.ID + "");
            }
        });
        return convertView;
    }
}
