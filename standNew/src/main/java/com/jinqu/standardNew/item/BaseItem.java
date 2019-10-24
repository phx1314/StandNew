package com.jinqu.standardNew.item;//
//  BaseItem
//
//  Created by JinQu on 2017-03-22 11:27:04
//  Copyright (c) JinQu All rights reserved.


/**

 */


import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;

import com.ab.http.HttpUtil;
import com.ab.util.HttpResponseListener;
import com.ab.util.HttpResponseListenerSon;
import com.mdx.framework.utility.handle.MHandler;

import static com.ab.view.pullview.BaseListView.readClassAttr;
import static com.jinqu.standardNew.F.POST;
import static com.jinqu.standardNew.frg.BaseFrg.setBgClickClor;

public class BaseItem implements OnClickListener, HttpResponseListenerSon {
    protected Context context;
    protected View contentview;
    protected MHandler handler;

    @Override
    public void onClick(View v) {
        setBgClickClor(v);
    }

    public View findViewById(int id) {
        return this.contentview.findViewById(id);
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onFinish() {

    }

    @Override
    public void onFailure(int statusCode, String content, Throwable error) {

    }

    @Override
    public void onSuccess(String methodName, String content) {

    }

    public void loadUrlPost(String methodName, Object... mparams) {
        HttpUtil.loadUrl(context, POST, methodName, new HttpResponseListener(context, this, methodName, true), mparams);
    }

    public void loadUrlPostBase(String methodName, Object obj, Object... mparams) {
        HttpUtil.loadUrl(context, POST, methodName, new HttpResponseListener(context, this, methodName, true), readClassAttr(obj, mparams));
    }
}

