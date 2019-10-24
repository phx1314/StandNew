//
//  AdaLocationAddress
//
//  Created by wchj on 2015-07-21 16:20:09
//  Copyright (c) wchj All rights reserved.


/**
   
*/

package com.jinqu.standardNew.ada;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.framewidget.view.Pois;
import com.jinqu.standardNew.item.LocationAddress;
import com.mdx.framework.adapter.MAdapter;

import java.util.List;

public class AdaLocationAddress extends MAdapter<Pois> {

   public AdaLocationAddress(Context context, List<Pois> list) {
        super(context, list);
    }


 	@Override
    public View getview(int position, View convertView, ViewGroup parent) {
 		Pois item = get(position);
        if (convertView == null) {
            convertView = LocationAddress.getView(getContext(), parent);;
        }
        LocationAddress mLocationAddress=(LocationAddress) convertView.getTag();
        mLocationAddress.set(item);
        return convertView;
    }
}
