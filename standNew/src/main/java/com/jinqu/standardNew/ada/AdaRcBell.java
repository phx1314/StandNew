//
//  AdaRcMonth
//
//  Created by DELL on 2018-08-01 15:21:15
//  Copyright (c) DELL All rights reserved.


/**

 */

package com.jinqu.standardNew.ada;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.jinqu.standardNew.frg.FrgCreateRcDetail;
import com.jinqu.standardNew.item.RcMonth;
import com.jinqu.standardNew.model.ModelBell;
import com.mdx.framework.activity.TitleAct;
import com.mdx.framework.adapter.MAdapter;
import com.mdx.framework.utility.Helper;

import java.util.List;

public class AdaRcBell extends MAdapter<ModelBell> {

    public AdaRcBell(Context context, List<ModelBell> list) {
        super(context, list);
    }


    @Override
    public View getview(int position, View convertView, ViewGroup parent) {
        final ModelBell item = get(position);
        if (convertView == null) {
            convertView = RcMonth.getView(getContext(), parent);
            ;
        }
        RcMonth mRcMonth = (RcMonth) convertView.getTag();
        mRcMonth.set(item);
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Helper.startActivity(getContext(), FrgCreateRcDetail.class, TitleAct.class, "id", item.ID + "","isRemind",true);
            }
        });
        return convertView;
    }
}
