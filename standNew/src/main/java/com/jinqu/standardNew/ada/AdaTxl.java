//
//  AdaTxl
//
//  Created by DELL on 2018-05-08 11:17:04
//  Copyright (c) DELL All rights reserved.


/**

 */

package com.jinqu.standardNew.ada;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.jinqu.standardNew.frg.FrgGrzx;
import com.jinqu.standardNew.item.Txl;
import com.jinqu.standardNew.model.ModelEmploee;
import com.mdx.framework.activity.NoTitleAct;
import com.mdx.framework.adapter.MAdapter;
import com.mdx.framework.utility.Helper;

import java.util.List;

import static com.jinqu.standardNew.frg.BaseFrg.setBgClickClor;

public class AdaTxl extends MAdapter<ModelEmploee.RowsBean> {
    public int type;

    public AdaTxl(Context context, List<ModelEmploee.RowsBean> list, int type) {
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
            public void onClick(View view) { setBgClickClor(view);
//                if (item.EmpID != mModelUsreLogin.UserInfo.EmpID) {
//                    RongIM.getInstance().startPrivateChat(
//                            getContext(),
//                            ParamsManager.get("ChatUserStr") + item.EmpID,
//                            item.EmpName);
//                }
                Helper.startActivity(getContext(), FrgGrzx.class, NoTitleAct.class, "id", item.EmpID + "");
            }
        });
        return convertView;
    }
}
