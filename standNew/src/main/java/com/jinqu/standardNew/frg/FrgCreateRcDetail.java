//
//  FrgCreateRc
//
//  Created by DELL on 2018-08-10 08:59:57
//  Copyright (c) DELL All rights reserved.


/**

 */

package com.jinqu.standardNew.frg;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.ab.util.AbMd5;
import com.framewidget.F;
import com.google.gson.Gson;
import com.jinqu.standardNew.R;
import com.jinqu.standardNew.model.ModelEmploee;
import com.mdx.framework.Frame;
import com.mdx.framework.activity.TitleAct;
import com.mdx.framework.config.BaseConfig;
import com.mdx.framework.utility.Helper;
import com.mdx.framework.widget.ActionBar;

import java.util.List;

import static com.framewidget.F.COLOR;
import static com.jinqu.standardNew.F.METHOD_IgnoreRemind;
import static com.jinqu.standardNew.F.METHOD_SchedulerSave;
import static com.jinqu.standardNew.F.mModelUsreLogin;


public class FrgCreateRcDetail extends BaseFrg {

    public ProgressBar mProgressBar;
    public WebView mWebView;
    public String id;
    public String method;
    public TextView mImageView_hl;
    public LinearLayout mLinearLayout_hl;
    public boolean isRemind;
    public String startTime;
    public String endTime;

    @Override
    protected void create(Bundle savedInstanceState) {
        id = getActivity().getIntent().getStringExtra("id");
        startTime = getActivity().getIntent().getStringExtra("startTime");
        endTime = getActivity().getIntent().getStringExtra("endTime");
        isRemind = getActivity().getIntent().getBooleanExtra("isRemind", false);
        setContentView(R.layout.frg_create_rc);
        initView();
        loaddata();
    }

    @Override
    public void disposeMsg(int type, Object obj) {
        switch (type) {
            case 101:
                List<ModelEmploee.RowsBean> mBaseEmployeeBeans = (List<ModelEmploee.RowsBean>) obj;
                mWebView.loadUrl("javascript:" + method + "('" + new Gson().toJson(mBaseEmployeeBeans) + "')");
                break;
        }
    }

    private void initView() {
        findVMethod();
    }

    private void findVMethod() {
        mProgressBar = (ProgressBar) findViewById(R.id.mProgressBar);
        mWebView = (WebView) findViewById(R.id.mWebView);
        mImageView_hl = (TextView) findViewById(R.id.mImageView_hl);
        mLinearLayout_hl = (LinearLayout) findViewById(R.id.mLinearLayout_hl);if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
        mProgressBar.getProgressDrawable().setTint(Color.parseColor(COLOR));
        mImageView_hl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadUrlPost(METHOD_IgnoreRemind, "id", id);
            }
        });
    }

    public void loaddata() {
        if (isRemind) {
            mWebView.loadUrl(BaseConfig.getUri() + "/oa/Schedulermobile/Edit?id=" + (TextUtils.isEmpty(id) ? "-1" : id) + "&source=remind&a=" + mModelUsreLogin.name + "&p=" + AbMd5.MD5(mModelUsreLogin.password));
            mLinearLayout_hl.setVisibility(View.VISIBLE);
        } else {
            mWebView.loadUrl(BaseConfig.getUri() + "/oa/Schedulermobile/Edit?id=" + (TextUtils.isEmpty(id) ? "-1" : id) + "&a=" + mModelUsreLogin.name + "&p=" + AbMd5.MD5(mModelUsreLogin.password));
        }
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.getSettings().setDomStorageEnabled(true);
        mWebView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        mWebView.setWebChromeClient(new WebChromeClient() {
            @Override
            public boolean onJsAlert(WebView view, String url, String message, android.webkit.JsResult result) {
                return super.onJsAlert(view, url, message, result);
            }
        });
        mWebView.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // 重写此方法表明点击网页里面的链接还是在当前的webview里跳转，不跳到浏览器那边
                return true;
            }

            @Override
            public void onPageFinished(WebView webView, String s) {
                super.onPageFinished(webView, s);

                if (!TextUtils.isEmpty(startTime)) {
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mWebView.loadUrl("javascript:schedulertime('" + startTime + "','" + endTime + "')");
                        }
                    });

                }
            }

        });
        mWebView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (newProgress == 100) {
                    mProgressBar.setVisibility(View.GONE);//加载完网页进度条消失
                } else {
                    mProgressBar.setVisibility(View.VISIBLE);//开始加载网页时显示进度条
                    mProgressBar.setProgress(newProgress);//设置进度值
                }
            }
        });
        mWebView.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    if (keyCode == KeyEvent.KEYCODE_BACK
                            && mWebView.canGoBack()) { // 表示按返回键
                        mWebView.goBack(); // 后退
                        return true; // 已处理
                    }
                }
                return false;
            }
        });
        mWebView.addJavascriptInterface(new JsInterface(getActivity()), "JinQuMobile");
    }

    private class JsInterface {
        private Context mContext;

        public JsInterface(Context context) {
            this.mContext = context;
        }

        @JavascriptInterface
        public void validateFormEnd(String json) {//提交表单
            Log.i("json", json);
            loadUrlPostBase(METHOD_SchedulerSave, json, null);
        }

        @JavascriptInterface
        public void EmpSingleSelectBegin(String json, String method) {//选择责任人
            FrgCreateRcDetail.this.method = method;
            Helper.startActivity(getContext(), FrgXzRenyuan.class, TitleAct.class, "id", json, "from", "FrgCreateRcDetail");
        }
    }

    @Override
    public void onSuccess(String methodName, String content) {
        if (methodName.equals(METHOD_SchedulerSave)) {
            Frame.HANDLES.sentAll("FrgRc", 115, null);
            Helper.toast("保存成功", getContext());
            F.closeSoftKey(getActivity());
            finish();
        } else if (methodName.equals(METHOD_IgnoreRemind)) {
            Frame.HANDLES.sentAll("FrgRc", 114, null);
            finish();
        }

    }

    @Override
    public void setActionBar(ActionBar actionBar, Context context) {
        super.setActionBar(actionBar, context);
        mHeadlayout.setTitle(TextUtils.isEmpty(id) ? "创建日程" : "日程详情");
        mHeadlayout.setRText("完成");
        mHeadlayout.setRightOnclicker(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mWebView.loadUrl("javascript:validateFormBegin()");
                F.closeSoftKey(getActivity());
            }
        });
    }
}