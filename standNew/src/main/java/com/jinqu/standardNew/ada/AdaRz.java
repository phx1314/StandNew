//
//  AdaRz
//
//  Created by DELL on 2017-08-28 14:03:58
//  Copyright (c) DELL All rights reserved.


/**

 */

package com.jinqu.standardNew.ada;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.framewidget.view.CallBackOnly;
import com.jinqu.standardNew.item.Rz;
import com.jinqu.standardNew.item.RzCz;
import com.jinqu.standardNew.model.ModelRzh;
import com.mdx.framework.adapter.MAdapter;

import java.util.List;

public class AdaRz extends MAdapter<ModelRzh.RowsBean> {

    private int type;

    public AdaRz(Context context, List<ModelRzh.RowsBean> list, int type) {
        super(context, list);
        this.type = type;
    }


    @Override
    public View getview(int position, View convertView, ViewGroup parent) {
        final ModelRzh.RowsBean item = get(position);
        if (convertView == null) {
            convertView = Rz.getView(getContext(), parent);
        }
        Rz mRz = (Rz) convertView.getTag();
        mRz.set(item);
        convertView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(final View view) {
                final View view1 = RzCz.getView(getContext(), null);
                com.framewidget.F.showBottomDialog(getContext(), view1, new CallBackOnly() {
                    @Override
                    public void goReturnDo(Dialog mDialog) {
                        ((RzCz) view1.getTag()).set(mDialog, item, type);
                    }
                });
                return true;
            }
        });
        return convertView;
    }

    public void setType(int type) {
        this.type = type;
    }
}
