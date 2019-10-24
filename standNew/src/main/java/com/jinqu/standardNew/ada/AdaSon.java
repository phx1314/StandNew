//
//  AdaSon
//
//  Created by DELL on 2018-05-08 11:17:04
//  Copyright (c) DELL All rights reserved.


/**

 */

package com.jinqu.standardNew.ada;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.jinqu.standardNew.item.Son;
import com.jinqu.standardNew.model.ModelGzList;
import com.mdx.framework.adapter.MAdapter;

import java.util.List;

public class AdaSon extends MAdapter<ModelGzList.RowsBean> {
    public int type;
    public Dialog mDialog;

    public AdaSon(Context context, List<ModelGzList.RowsBean> list, int type, Dialog mDialog) {
        super(context, list);
        this.type = type;
        this.mDialog = mDialog;
    }
    public AdaSon(Context context, List<ModelGzList.RowsBean> list, int type ) {
        super(context, list);
        this.type = type;
    }


    @Override
    public View getview(int position, View convertView, ViewGroup parent) {
        ModelGzList.RowsBean item = get(position);
        if (convertView == null) {
            convertView = Son.getView(getContext(), parent);
        }
        Son mSon = (Son) convertView.getTag();
        mSon.set(item, type, mDialog);
        return convertView;
    }
}
