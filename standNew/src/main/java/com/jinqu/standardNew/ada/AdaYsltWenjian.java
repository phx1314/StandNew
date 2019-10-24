//
//  AdaYsltWenjian
//
//  Created by df on 2016-02-02 10:25:31
//  Copyright (c) df All rights reserved.

/**

 */

package com.jinqu.standardNew.ada;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.jinqu.standardNew.item.YsltWenjian;
import com.mdx.framework.adapter.MAdapter;

import java.io.File;
import java.util.List;

public class AdaYsltWenjian extends MAdapter<File> {
    public String from;

    public AdaYsltWenjian(Context context, List<File> list, String from) {
        super(context, list);
        this.from = from;
    }

    @Override
    public View getview(int position, View convertView, ViewGroup parent) {
        final File item = get(position);
        if (convertView == null) {
            convertView = new YsltWenjian(getContext());
        }
        ((YsltWenjian) convertView).set(item, from);
        return convertView;
    }
}
