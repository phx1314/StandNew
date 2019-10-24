//
//  AdaNewsTop
//
//  Created by DELL on 2018-07-09 15:34:02
//  Copyright (c) DELL All rights reserved.


/**

 */

package com.jinqu.standardNew.ada;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.jinqu.standardNew.frg.FrgPubWebDetail;
import com.jinqu.standardNew.item.NewsTop;
import com.jinqu.standardNew.model.ModelNewsImges;
import com.mdx.framework.activity.TitleAct;
import com.mdx.framework.adapter.MAdapter;
import com.mdx.framework.utility.Helper;

import java.util.List;

import static com.jinqu.standardNew.F.refTable_OaNew;
import static com.jinqu.standardNew.frg.BaseFrg.setBgClickClor;

public class AdaNewsTop extends MAdapter<ModelNewsImges.RowsBean> {

    public AdaNewsTop(Context context, List<ModelNewsImges.RowsBean> list) {
        super(context, list);
    }


    @Override
    public View getview(int position, View convertView, ViewGroup parent) {
        final ModelNewsImges.RowsBean item = get(position);
        if (convertView == null) {
            convertView = NewsTop.getView(getContext(), parent);
        }
        NewsTop mNewsTop = (NewsTop) convertView.getTag();
        mNewsTop.set(item);
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setBgClickClor(v);
                Helper.startActivity(getContext(), FrgPubWebDetail.class,
                        TitleAct.class, "id",
                        item.Id, "refTable", refTable_OaNew,  "editUrl","oa/oanewmobile/Query");

            }
        });
        return convertView;
    }
}
