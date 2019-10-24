//
//  ColorDialogSon
//
//  Created by DELL on 2018-05-24 09:21:08
//  Copyright (c) DELL All rights reserved.


/**

 */

package com.jinqu.standardNew.item;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.jinqu.standardNew.F;
import com.jinqu.standardNew.R;


public class ColorDialogSon extends BaseItem {


    public ImageView mImageView;
    public com.thinkcool.circletextimageview.CircleTextImageView mCircleTextImageView;

    @SuppressLint("InflateParams")
    public static View getView(Context context, ViewGroup parent) {
        LayoutInflater flater = LayoutInflater.from(context);
        View convertView = flater.inflate(R.layout.item_color_dialog_son, null);
        convertView.setTag(new ColorDialogSon(convertView));
        return convertView;
    }

    public ColorDialogSon(View view) {
        this.contentview = view;
        this.context = contentview.getContext();
        initView();
    }

    private void initView() {
        this.contentview.setTag(this);
        findVMethod();
    }

    private void findVMethod() {


        mImageView = (ImageView) findViewById(R.id.mImageView);
        mCircleTextImageView = (com.thinkcool.circletextimageview.CircleTextImageView) findViewById(R.id.mCircleTextImageView);
    }

    public void set(String item) {
        if (F.COLOR.equals(item)) {
            mImageView.setVisibility(View.VISIBLE);
        } else {
            mImageView.setVisibility(View.GONE);
        }
        mCircleTextImageView.setImageDrawable(new ColorDrawable(Color.parseColor(item)));
    }


}