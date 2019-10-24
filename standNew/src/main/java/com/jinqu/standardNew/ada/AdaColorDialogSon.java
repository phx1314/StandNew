//
//  AdaColorDialogSon
//
//  Created by DELL on 2018-05-24 09:21:08
//  Copyright (c) DELL All rights reserved.


/**

 */

package com.jinqu.standardNew.ada;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.jinqu.standardNew.F;
import com.jinqu.standardNew.item.ColorDialogSon;
import com.mdx.framework.Frame;
import com.mdx.framework.adapter.MAdapter;

import java.util.List;

import static com.jinqu.standardNew.frg.BaseFrg.setBgClickClor;

public class AdaColorDialogSon extends MAdapter<String> {
    public Dialog mDialog;

    public AdaColorDialogSon(Context context, List<String> list, Dialog mDialog) {
        super(context, list);
        this.mDialog = mDialog;
    }


    @Override
    public View getview(int position, View convertView, ViewGroup parent) {
        final String item = get(position);
        if (convertView == null) {
            convertView = ColorDialogSon.getView(getContext(), parent);
        }
        ColorDialogSon mColorDialogSon = (ColorDialogSon) convertView.getTag();
        mColorDialogSon.set(item);
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setBgClickClor(v);
                com.jinqu.standardNew.F.saveJson("COLOR", item);
                com.framewidget.F.COLOR = F.COLOR = item;
                mDialog.dismiss();
                Frame.HANDLES.sentAll("FrgGz,FrgSet,FrgWd,FrgXwSon,FrgRc,FrgHome,FrgXx,FrgFx,FrgXw,FrgGg,FrgBd,FrgRw,FrgTxl", 10086, null);
            }
        });
        return convertView;
    }
}
