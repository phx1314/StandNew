//
//  AdaYindao
//
//  Created by DELL on 2017-03-24 11:36:25
//  Copyright (c) DELL All rights reserved.


/**

 */

package com.jinqu.standardNew.ada;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.jinqu.standardNew.item.Yindao;
import com.mdx.framework.adapter.MAdapter;

import java.util.List;

public class AdaYindao extends MAdapter<View> {

    public AdaYindao(Context context, List<View> list) {
        super(context, list);
    }


    @Override
    public View getview(int position, View convertView, ViewGroup parent) {
        View item = get(position);
        Yindao mYindao;
        if (convertView == null) {
            convertView = Yindao.getView(getContext(), parent);
            mYindao = (Yindao) convertView.getTag();
            mYindao.addView(item);
        }
        return convertView;
    }
}
