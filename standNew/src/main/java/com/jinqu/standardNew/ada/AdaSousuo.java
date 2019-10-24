//
//  AdaSousuo
//
//  Created by DELL on 2018-06-27 09:37:50
//  Copyright (c) DELL All rights reserved.


/**

 */

package com.jinqu.standardNew.ada;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.framewidget.frg.FrgList;
import com.framewidget.frg.FrgListDx;
import com.framewidget.model.ModelDx;
import com.framewidget.newMenu.DateDfSelectDialog;
import com.jinqu.standardNew.frg.FrgSousuo;
import com.jinqu.standardNew.item.Sousuo;
import com.jinqu.standardNew.model.ModelMenuConfig;
import com.jinqu.standardNew.model.ModelUsreLogin;
import com.mdx.framework.activity.TitleAct;
import com.mdx.framework.adapter.MAdapter;
import com.mdx.framework.utility.Helper;

import java.util.ArrayList;
import java.util.List;

import static com.jinqu.standardNew.F.mModelUsreLogin;

public class AdaSousuo extends MAdapter<ModelMenuConfig.SearchBean> {

    public AdaSousuo(Context context, List<ModelMenuConfig.SearchBean> list) {
        super(context, list);
    }


    @Override
    public View getview(final int position, View convertView, ViewGroup parent) {
        final ModelMenuConfig.SearchBean item = get(position);
        if (convertView == null) {
            convertView = Sousuo.getView(getContext(), parent);
        }
        Sousuo mSousuo = (Sousuo) convertView.getTag();
        mSousuo.set(item);
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (item.type.startsWith("time_")) {
                    DateDfSelectDialog mDateSelectDialog = new DateDfSelectDialog(getContext(), null, 3);
                    mDateSelectDialog.show();
                    mDateSelectDialog.setOnSelected(new DateDfSelectDialog.OnSelected() {
                        @Override
                        public void onSelected(Dialog dia, String selected) {
                            item.value = selected;
                            AdaSousuo.this.notifyDataSetChanged();
                        }
                    });
                } else if (item.type.equals("basedata")) {
                    FrgSousuo.position = position;
                    List mModelDxs = new ArrayList();
                    for (ModelUsreLogin.BaseDataBean son : mModelUsreLogin.BaseData) {
                        if ((son.BaseOrder.startsWith(item.baseorder))) {
                            mModelDxs.add(new ModelDx(son.BaseName, son.BaseID));
                        }
                    }
                    if (item.multiselect.equals("0")) {
                        Helper.startActivity(getContext(), FrgList.class, TitleAct.class, "from", "FrgSousuo", "type", 1, "title", item.text, "data", mModelDxs);
                    } else {
                        Helper.startActivity(getContext(), FrgListDx.class, TitleAct.class, "from", "FrgSousuo", "type", 2, "title", item.text, "data", mModelDxs);

                    }
                }
            }
        });
        return convertView;
    }
}
