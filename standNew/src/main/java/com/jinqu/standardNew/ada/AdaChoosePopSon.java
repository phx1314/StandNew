//
//  AdaChoosePopSon
//
//  Created by DELL on 2018-06-20 11:52:22
//  Copyright (c) DELL All rights reserved.


/**

 */

package com.jinqu.standardNew.ada;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.jinqu.standardNew.item.ChoosePopSon;
import com.jinqu.standardNew.pop.PopShowChoose;
import com.mdx.framework.Frame;
import com.mdx.framework.adapter.MAdapter;

import java.util.List;

import static com.jinqu.standardNew.frg.BaseFrg.setBgClickClor;

public class AdaChoosePopSon extends MAdapter<String> {
    public String choosed;
    public String from;
    public PopShowChoose mPopShowChoose;

    public AdaChoosePopSon(Context context, List<String> list, String choosed, String from, PopShowChoose mPopShowChoose) {
        super(context, list);
        this.choosed = choosed;
        this.from = from;
        this.mPopShowChoose = mPopShowChoose;
    }


    @Override
    public View getview(int position, View convertView, ViewGroup parent) {
        final String item = get(position);
        if (convertView == null) {
            convertView = ChoosePopSon.getView(getContext(), parent);
        }
        ChoosePopSon mChoosePopSon = (ChoosePopSon) convertView.getTag();
        mChoosePopSon.set(item, choosed.equals(item));
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setBgClickClor(v);
                mPopShowChoose.hide();
                Frame.HANDLES.sentAll(from, 101, item);
            }
        });
        return convertView;
    }
}
