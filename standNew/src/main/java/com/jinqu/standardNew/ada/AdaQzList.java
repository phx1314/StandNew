//
//  AdaQzList
//
//  Created by DELL on 2018-05-17 16:59:07
//  Copyright (c) DELL All rights reserved.


/**

 */

package com.jinqu.standardNew.ada;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;

import com.jinqu.standardNew.item.QzList;
import com.jinqu.standardNew.model.ModelQunList;
import com.mdx.framework.adapter.MAdapter;
import com.mdx.framework.commons.ParamsManager;

import java.util.List;

import io.rong.imlib.model.Conversation;

import static com.jinqu.standardNew.frg.BaseFrg.setBgClickClor;

public class AdaQzList extends MAdapter<ModelQunList.RowsBean> {
    public String refId;
    public String refTable;

    public AdaQzList(Context context, List<ModelQunList.RowsBean> list) {
        super(context, list);
    }

    public AdaQzList(Context context, List<ModelQunList.RowsBean> list, String refId, String refTable) {
        super(context, list);
        this.refId = refId;
        this.refTable = refTable;
    }

    @Override
    public View getview(int position, View convertView, ViewGroup parent) {
        final ModelQunList.RowsBean item = get(position);
        if (convertView == null) {
            convertView = QzList.getView(getContext(), parent);
        }
        QzList mQzList = (QzList) convertView.getTag();
        mQzList.set(item);
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setBgClickClor(view);
                Uri uri = Uri.parse("rong://" + getContext().getApplicationInfo().packageName).buildUpon().appendPath("conversation").appendPath(Conversation.ConversationType.GROUP.getName().toLowerCase()).appendQueryParameter("targetId", ParamsManager.get("ChatStr") + item.GroupID).appendQueryParameter("refId", refId).appendQueryParameter("refTable", refTable).appendQueryParameter("title", item.GroupName).build();
                getContext().startActivity(new Intent("android.intent.action.VIEW", uri));
            }
        });
        return convertView;
    }
}
