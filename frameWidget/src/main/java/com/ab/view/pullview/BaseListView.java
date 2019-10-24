//
//  BaseFrg
//
//  Created by JinQu on 2017-03-22 11:26:45
//  Copyright (c) JinQu All rights reserved.


/**

 */

package com.ab.view.pullview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

import com.ab.http.AbRequestParams;
import com.ab.http.AbStringHttpResponseListener;
import com.ab.http.HttpUtil;
import com.google.gson.Gson;
import com.mdx.framework.log.MLog;

import org.json.JSONObject;

import java.io.File;
import java.util.Iterator;

public class BaseListView extends ListView {


    public BaseListView(Context context) {
        super(context);
    }

    public BaseListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BaseListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    public class HttpResponseListener extends AbStringHttpResponseListener {
        public String methodName;
        public boolean isreFreash = true;

        public HttpResponseListener(String methodName, boolean isreFreash) {
            this.methodName = methodName;
            this.isreFreash = isreFreash;
        }


        @Override
        public void onStart() {
        }

        @Override
        public void onFinish() {
            try {
                BaseListView.this.onFinish();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onFailure(int statusCode, String content, Throwable error) {
//            Helper.toast("请求服务器失败", getContext());
            MLog.D("请求服务器失败方法名：" + methodName);
            error.printStackTrace();
            try {
                BaseListView.this.onFailure();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        @Override
        public void onSuccess(int statusCode, String content) {
            MLog.I(content);
            try {
                BaseListView.this.onSuccess(methodName, content, isreFreash);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void onSuccess(String methodName, String content, boolean isreFreash) {
    }

    public void onFailure() {
    }

    public void onFinish() {
    }


    public void loadUrlGet(String methodName, boolean isreFreash, Object... mparams) {
        HttpUtil.loadUrl(getContext(), "GET", methodName, new HttpResponseListener(methodName, isreFreash), mparams);
    }


    public void loadUrlPost(String methodName, boolean isreFreash, Object... mparams) {
        HttpUtil.loadUrl(getContext(), "POST", methodName, new HttpResponseListener(methodName, isreFreash), mparams);
    }

    public void loadUrlPostBase(String methodName, boolean isreFreash, Object obj, Object... mparams) {
        HttpUtil.loadUrl(getContext(), "POST", methodName, new HttpResponseListener(methodName, isreFreash), readClassAttr(obj, mparams));
    }

    /**
     * 用来遍历对象属性和属性值
     */
    public static AbRequestParams readClassAttr(Object tb, Object... mparams) {
        AbRequestParams map = new AbRequestParams();
        try {
            JSONObject jsonObject = new JSONObject(new Gson().toJson(tb));
            Iterator iterator = jsonObject.keys();                       // joData是JSONObject对象
            while (iterator.hasNext()) {
                String key = iterator.next() + "";
                map.put(key, jsonObject.optString(key));
            }

            for (int i = 0; i < mparams.length; i++) {
                if (mparams[i] instanceof Object[]) {
                    for (int j = 0; j < ((Object[]) mparams[i]).length; j++) {
                        if (((Object[]) mparams[i]).length > j + 1 && ((Object[]) mparams[i])[j + 1] != null) {
                            if (((Object[]) mparams[i])[j + 1] instanceof File) {
                                map.put(((Object[]) mparams[i])[j].toString(), (File) ((Object[]) mparams[i])[j + 1]);
                            } else {
                                map.put(((Object[]) mparams[i])[j].toString(), ((Object[]) mparams[i])[j + 1].toString());
                            }
                        }
                        j++;
                    }
                } else {
                    if (mparams.length > i + 1 && mparams[i + 1] != null) {
                        if (mparams[i + 1] instanceof File) {
                            map.put(mparams[i].toString(), (File) mparams[i + 1]);
                        } else {
                            map.put(mparams[i].toString(), mparams[i + 1].toString());
                        }
                    }
                    i++;
                }
            }
            return map;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }
}
