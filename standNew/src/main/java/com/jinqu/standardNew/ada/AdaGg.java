//
//  AdaGg
//
//  Created by DELL on 2018-07-26 11:17:20
//  Copyright (c) DELL All rights reserved.


/**

 */

package com.jinqu.standardNew.ada;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.jinqu.standardNew.frg.FrgPubWeb;
import com.jinqu.standardNew.frg.FrgPubWebDetail;
import com.jinqu.standardNew.item.Gg;
import com.jinqu.standardNew.model.ModelBd;
import com.mdx.framework.activity.TitleAct;
import com.mdx.framework.adapter.MAdapter;
import com.mdx.framework.utility.Helper;

import java.util.List;

import static com.jinqu.standardNew.F.refTable_OaNotice;
import static com.jinqu.standardNew.frg.BaseFrg.setBgClickClor;

public class AdaGg extends MAdapter<ModelBd.RowsBean> {

    public AdaGg(Context context, List<ModelBd.RowsBean> list) {
        super(context, list);
    }


    @Override
    public View getview(int position, View convertView, ViewGroup parent) {
        final ModelBd.RowsBean item = get(position);
        if (convertView == null) {
            convertView = Gg.getView(getContext(), parent);
        }
        Gg mGg = (Gg) convertView.getTag();
        mGg.set(item);
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setBgClickClor(v);
                if (item.mModelMenuConfig != null) {
                    Helper.startActivity(getContext(), FrgPubWeb.class, TitleAct.class, "item", item);
                } else {
                    Helper.startActivity(getContext(), FrgPubWebDetail.class,
                            TitleAct.class, "id",
                            item.Id, "refTable", refTable_OaNotice, "editUrl", "oa/oanoticemobile/Query");
                }
            }
        });
        return convertView;
    }
}
