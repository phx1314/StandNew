//
//  BaseAct
//
//  Created by DELL on 2017-03-28 17:29:29
//  Copyright (c) DELL All rights reserved.


/**

 */


package com.jinqu.standardNew.act;

import android.os.Bundle;

import com.ab.http.HttpUtil;
import com.ab.util.HttpResponseListener;
import com.ab.util.HttpResponseListenerSon;
import com.framewidget.F;
import com.mdx.framework.activity.MActivity;

import static com.jinqu.standardNew.F.POST;
import static com.jinqu.standardNew.frg.BaseFrg.setDarkStatusIcon;

public abstract class BaseAct extends MActivity implements HttpResponseListenerSon {


    @Override
    public void onSuccess(String methodName, String content) {

    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onFinish() {

    }
    @Override
    protected void initcreate(Bundle savedInstanceState) {
        super.initcreate(savedInstanceState);
        setDarkStatusIcon(getActivity(), true);
    }
    @Override
    public void onFailure(int statusCode, String content, Throwable error) {

    }


    public void loadUrlPost(String methodName, Object... mparams) {
        HttpUtil.loadUrl(getApplicationContext(), POST, methodName, new HttpResponseListener(getContext(), this, methodName, true), mparams);
    }

    public void loadUrlPostNoShow(String methodName, Object... mparams) {
        HttpUtil.loadUrl(getContext(), com.jinqu.standardNew.F.POST, methodName, new HttpResponseListener(getContext(), this, methodName, false), mparams);
    }

    @Override
    public void finish() {
        F.closeSoftKey(this);
        super.finish();
    }

}