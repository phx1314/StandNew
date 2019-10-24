//
//  AdaXw
//
//  Created by DELL on 2018-05-08 11:17:04
//  Copyright (c) DELL All rights reserved.


/**

 */

package com.jinqu.standardNew.ada;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.jinqu.standardNew.frg.FrgPubWebDetail;
import com.jinqu.standardNew.item.Xw;
import com.jinqu.standardNew.model.ModelNewsList;
import com.mdx.framework.activity.TitleAct;
import com.mdx.framework.adapter.MAdapter;
import com.mdx.framework.utility.Helper;

import java.util.List;

import static com.jinqu.standardNew.F.refTable_OaNew;
import static com.jinqu.standardNew.frg.BaseFrg.setBgClickClor;

public class AdaXw extends MAdapter<ModelNewsList.RowsBean> {

    public AdaXw(Context context, List<ModelNewsList.RowsBean> list) {
        super(context, list);
    }

//    http://192.168.0.180/GoldPM9_std_9.1.0/oa/oanewmobile/Query?id=6&a=admin&p=1A1DC91C907325C69271DDF0C944BC72

    @Override
    public View getview(int position, View convertView, ViewGroup parent) {
        final ModelNewsList.RowsBean item = get(position);
        if (convertView == null) {
            convertView = Xw.getView(getContext(), parent);
        }
        Xw mXw = (Xw) convertView.getTag();
        mXw.set(item);
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setBgClickClor(v);
                Helper.startActivity(getContext(), FrgPubWebDetail.class,
                        TitleAct.class, "id",
                        item.Id, "refTable", refTable_OaNew,   "editUrl","oa/oanewmobile/Query");

            }
        });
        return convertView;
    }
}
