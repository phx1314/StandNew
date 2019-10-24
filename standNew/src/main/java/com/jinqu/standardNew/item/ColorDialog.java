//
//  ColorDialog
//
//  Created by DELL on 2018-05-24 09:21:08
//  Copyright (c) DELL All rights reserved.


/**

 */

package com.jinqu.standardNew.item;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.jinqu.standardNew.R;
import com.jinqu.standardNew.ada.AdaColorDialogSon;

import java.util.Arrays;


public class ColorDialog extends BaseItem {
    public GridView mGridView;
    public String[] colors = {"#c62828", "#ad1457", "#6a1b9a", "#4527a0", "#283593", "#1565c0", "#0277bd", "#00838f", "#00695c", "#1b5e20", "#33691e", "#ef6c00", "#bf360c", "#5d4037", "#455a64", "#272626"};
    public Dialog item;

    @SuppressLint("InflateParams")
    public static View getView(Context context, ViewGroup parent) {
        LayoutInflater flater = LayoutInflater.from(context);
        View convertView = flater.inflate(R.layout.item_color_dialog, null);
        convertView.setTag(new ColorDialog(convertView));
        return convertView;
    }

    public ColorDialog(View view) {
        this.contentview = view;
        this.context = contentview.getContext();
        initView();
    }

    private void initView() {
        this.contentview.setTag(this);
        findVMethod();
    }

    private void findVMethod() {
        mGridView = (GridView) contentview.findViewById(R.id.mGridView);

    }

    public void set(Dialog item) {
        this.item = item;
        mGridView.setAdapter(new AdaColorDialogSon(context, Arrays.asList(colors), item));
    }


}