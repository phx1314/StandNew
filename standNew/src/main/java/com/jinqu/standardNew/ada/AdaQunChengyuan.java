//
//  AdaQunChengyuan
//
//  Created by DELL on 2017-04-25 08:46:39
//  Copyright (c) DELL All rights reserved.


/**

 */

package com.jinqu.standardNew.ada;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.jinqu.standardNew.frg.FrgGrzx;
import com.jinqu.standardNew.frg.FrgXzRenyuan;
import com.jinqu.standardNew.item.QunChengyuan;
import com.jinqu.standardNew.model.ModelEmploee;
import com.mdx.framework.activity.NoTitleAct;
import com.mdx.framework.activity.TitleAct;
import com.mdx.framework.adapter.MAdapter;
import com.mdx.framework.utility.Helper;

import java.util.List;

import static com.jinqu.standardNew.frg.BaseFrg.setBgClickClor;

public class AdaQunChengyuan extends MAdapter<ModelEmploee.RowsBean> {

    public AdaQunChengyuan(Context context, List<ModelEmploee.RowsBean> list) {
        super(context, list);
    }

    public String getIds() {
        String ids = "";
        for (ModelEmploee.RowsBean mRowsBean : getList()) {
            if (mRowsBean.EmpID != 0)
                ids += mRowsBean.EmpID + ",";
        }
        return ids.substring(0, ids.length() - 1);
    }


    @Override
    public View getview(int position, View convertView, ViewGroup parent) {
        final ModelEmploee.RowsBean item = get(position);
        if (convertView == null) {
            convertView = QunChengyuan.getView(getContext(), parent);
        }
        QunChengyuan mQunChengyuan = (QunChengyuan) convertView.getTag();
        mQunChengyuan.set(item);
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { setBgClickClor(view);
                if (item.EmpID != 0) {
                    Helper.startActivity(getContext(), FrgGrzx.class, NoTitleAct.class, "id", item.EmpID + "");
                } else {
                    Helper.startActivity(getContext(), FrgXzRenyuan.class, TitleAct.class, "id", getIds(), "from", "FrgCreateQun");
                }
            }
        });
        return convertView;
    }
}
