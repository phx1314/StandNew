//
//  FrgPubWebDetail
//
//  Created by DELL on 2018-07-26 10:53:31
//  Copyright (c) DELL All rights reserved.


/**

 */

package com.jinqu.standardNew.frg;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
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
import com.jinqu.standardNew.R;
import com.jinqu.standardNew.model.ModelFjList;
import com.mdx.framework.activity.TitleAct;
import com.mdx.framework.config.BaseConfig;
import com.mdx.framework.dialog.PhotoShow;
import com.mdx.framework.utility.Helper;
import com.mdx.framework.widget.ActionBar;

import static com.framewidget.F.COLOR;
import static com.jinqu.standardNew.F.METHOD_GETATTACHFILES;
import static com.jinqu.standardNew.F.json2Model;
import static com.jinqu.standardNew.F.mModelUsreLogin;


public class FrgPubWebDetail extends BaseFrg {

    public ProgressBar mProgressBar;
    public WebView mWebView;
    public LinearLayout mLinearLayout_fj;
    public TextView mTextView_count;
    public int id;
    public String refTable;
    public String editUrl;

    @Override
    protected void create(Bundle savedInstanceState) {
        refTable = getActivity().getIntent().getStringExtra("refTable");
        editUrl = getActivity().getIntent().getStringExtra("editUrl");
        id = getActivity().getIntent().getIntExtra("id", 0);
        setContentView(R.layout.frg_pub_web_detail);
        initView();
        loaddata();
    }

    private void loaddata() {

        loadUrlPost(METHOD_GETATTACHFILES, "refID", id, "refTable", refTable);
        mWebView.loadUrl(BaseConfig.getUri() + "/" + editUrl + "?id=" + id + "&a=" + mModelUsreLogin.name + "&p=" + AbMd5.MD5(mModelUsreLogin.password));
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

    private void initView() {
        findVMethod();
    }

    private void findVMethod() {
        mProgressBar = (ProgressBar) findViewById(R.id.mProgressBar);
        mWebView = (WebView) findViewById(R.id.mWebView);
        mLinearLayout_fj = (LinearLayout) findViewById(R.id.mLinearLayout_fj);
        mTextView_count = (TextView) findViewById(R.id.mTextView_count);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            mProgressBar.getProgressDrawable().setTint(Color.parseColor(COLOR));
        mLinearLayout_fj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setBgClickClor(view);
                Helper.startActivity(getContext(), FrgFujianList.class, TitleAct.class, "id", id + "", "refTable", refTable);
            }
        });
    }

    private class JsInterface {
        private Context mContext;

        public JsInterface(Context context) {
            this.mContext = context;
        }

        @JavascriptInterface
        public void OpenImgBase64(String url) {
//            PhotoShow mPhotoShow = new PhotoShow(getContext(), stringToBitmap(url));
//            mPhotoShow.show();
            PhotoShow mPhotoShow = new PhotoShow(getContext(), url);
            mPhotoShow.show();
        }

    }

    @Override
    public void onSuccess(String methodName, String content) {
        if (methodName.equals(METHOD_GETATTACHFILES)) {
            ModelFjList mModelFjList = (ModelFjList) json2Model(content, ModelFjList.class);
            mTextView_count.setText(mModelFjList.rows.size() + "");
        }
    }

    @Override
    public void setActionBar(ActionBar actionBar, Context context) {
        super.setActionBar(actionBar, context);
        mHeadlayout.setTitle("详情");
    }
}