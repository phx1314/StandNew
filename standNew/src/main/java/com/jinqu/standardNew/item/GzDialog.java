//
//  GzDialog
//
//  Created by DELL on 2018-07-23 14:19:57
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
import android.widget.TextView;

import com.jinqu.standardNew.R;
import com.jinqu.standardNew.ada.AdaSon;
import com.jinqu.standardNew.model.ModelGzList;


public class GzDialog extends BaseItem {
    public GridView mGridView;
    public TextView mTextView;


    @SuppressLint("InflateParams")
    public static View getView(Context context, ViewGroup parent) {
        LayoutInflater flater = LayoutInflater.from(context);
        View convertView = flater.inflate(R.layout.item_gz_dialog, null);
        convertView.setTag(new GzDialog(convertView));
        return convertView;
    }

    public GzDialog(View view) {
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
        mTextView = (TextView) findViewById(R.id.mTextView);


    }

    public void set(ModelGzList.RowsBean item, Dialog mDialog) {
        mTextView.setText(item.text);
        mGridView.setAdapter(new AdaSon(context, item.children, 1,   mDialog));
    }


}