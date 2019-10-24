//
//  FrgEmailDetail
//
//  Created by DELL on 2018-06-22 10:03:23
//  Copyright (c) DELL All rights reserved.


/**

 */

package com.jinqu.standardNew.frg;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
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
import com.framewidget.view.CallBackOnly;
import com.jinqu.standardNew.R;
import com.jinqu.standardNew.model.ModelFjList;
import com.jinqu.standardNew.model.ModelYjDetail;
import com.jinqu.standardNew.pop.YoujiancaozuoPop;
import com.mdx.framework.Frame;
import com.mdx.framework.activity.TitleAct;
import com.mdx.framework.config.BaseConfig;
import com.mdx.framework.dialog.PhotoShow;
import com.mdx.framework.utility.Helper;
import com.mdx.framework.widget.ActionBar;

import static com.framewidget.F.COLOR;
import static com.jinqu.standardNew.F.METHOD_GETATTACHFILES;
import static com.jinqu.standardNew.F.METHOD_OAMAILDETAIL;
import static com.jinqu.standardNew.F.METHOD_UpdateReadByIds;
import static com.jinqu.standardNew.F.json2Model;
import static com.jinqu.standardNew.F.mModelUsreLogin;
import static com.jinqu.standardNew.F.refTable_OaMail;
import static com.jinqu.standardNew.F.stringToBitmap;
import static com.jinqu.standardNew.util.UtilDate.changeTime;


public class FrgEmailDetail extends BaseFrg {

    public LinearLayout mLinearLayout_fj;
    public String id;
    public int type;
    public TextView mTextView_count;
    public ModelYjDetail mModelYjDetail;
    public ProgressBar mProgressBar;
    public WebView mWebView;

    @Override
    protected void create(Bundle savedInstanceState) {
        id = getActivity().getIntent().getStringExtra("id");
        type = getActivity().getIntent().getIntExtra("type", 1);
        setContentView(R.layout.frg_email_detail);
        initView();
        loaddata();
    }

    @Override
    public void disposeMsg(int type, Object obj) {
        switch (type) {
            case 0:
                this.finish();
                Frame.HANDLES.sentAll("FrgEmailList", 0, null);
                break;
        }
    }

    private void initView() {
        findVMethod();
    }

    private void findVMethod() {
        mLinearLayout_fj = (LinearLayout) findViewById(R.id.mLinearLayout_fj);
        mTextView_count = (TextView) findViewById(R.id.mTextView_count);
        mProgressBar = (ProgressBar) findViewById(R.id.mProgressBar);
        mWebView = (WebView) findViewById(R.id.mWebView);if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
        mProgressBar.getProgressDrawable().setTint(Color.parseColor(COLOR));
        mLinearLayout_fj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setBgClickClor(view);
                Helper.startActivity(getContext(), FrgFujianList.class, TitleAct.class, "id", TextUtils.isEmpty(id) ? "0" : id, "refTable", refTable_OaMail);
            }
        });

    }

    @Override
    public void onSuccess(String methodName, String content) {
        if (methodName.equals(METHOD_GETATTACHFILES)) {
            ModelFjList mModelFjList = (ModelFjList) json2Model(content, ModelFjList.class);
            mTextView_count.setText(mModelFjList.rows.size() + "");
        } else if (methodName.equals(METHOD_OAMAILDETAIL)) {
            mModelYjDetail = (ModelYjDetail) json2Model(content, ModelYjDetail.class);
        } else if (methodName.equals(METHOD_UpdateReadByIds)) {
            Frame.HANDLES.sentAll("FrgWd", 0, null);
        }
    }

    public void loaddata() {
        loadUrlGet(METHOD_OAMAILDETAIL, "Id", id, "ReceiveFlag", type == 1 ? "1" : "0");
        loadUrlPost(METHOD_GETATTACHFILES, "refID", TextUtils.isEmpty(id) ? "0" : id, "refTable", refTable_OaMail);
        loadPostNoShow(METHOD_UpdateReadByIds + id + "_" + mModelUsreLogin.UserInfo.EmpID, METHOD_UpdateReadByIds);
        mWebView.loadUrl(BaseConfig.getUri() + "/oa/OaMailMobile/edit?id=" + id + "&a=" + mModelUsreLogin.name + "&p=" + AbMd5.MD5(mModelUsreLogin.password));
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

    private class JsInterface {
        private Context mContext;

        public JsInterface(Context context) {
            this.mContext = context;
        }

        @JavascriptInterface
        public void OpenImgBase64(String url) {
            PhotoShow mPhotoShow = new PhotoShow(getContext(), stringToBitmap(url));
            mPhotoShow.show();
        }


    }

    @Override
    public void setActionBar(ActionBar actionBar, Context context) {
        super.setActionBar(actionBar, context);
        mHeadlayout.setTitle("邮件详情");
        mHeadlayout.setRightBacgroud(R.drawable.caidan_more);
        mHeadlayout.setRightOnclicker(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setBgClickClor(view);
                final View view1 = YoujiancaozuoPop.getView(getContext(), null);
                F.showBottomDialog(getContext(), view1, new CallBackOnly() {
                    @Override
                    public void goReturnDo(Dialog mDialog) {
                        ((YoujiancaozuoPop) view1.getTag()).set(id, mDialog, type, mModelYjDetail);
                    }
                });
            }
        });
    }


}