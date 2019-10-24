//
//  FrgYchShq
//
//  Created by DELL on 2017-05-09 14:53:10
//  Copyright (c) DELL All rights reserved.


/**

 */

package com.jinqu.standardNew.frg;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
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

import com.ab.util.AbDateUtil;
import com.ab.util.AbMd5;
import com.framewidget.F;
import com.framewidget.view.CallBackOnly;
import com.google.gson.Gson;
import com.jinqu.standardNew.R;
import com.jinqu.standardNew.item.DialogSub;
import com.jinqu.standardNew.model.ModelAddPub;
import com.jinqu.standardNew.model.ModelBd;
import com.jinqu.standardNew.model.ModelEmploee;
import com.jinqu.standardNew.model.ModelFjList;
import com.jinqu.standardNew.model.ModelJDInfo;
import com.jinqu.standardNew.model.ModelUpFile;
import com.mdx.framework.Frame;
import com.mdx.framework.activity.NoTitleAct;
import com.mdx.framework.activity.TitleAct;
import com.mdx.framework.config.BaseConfig;
import com.mdx.framework.utility.Helper;
import com.mdx.framework.widget.ActionBar;

import java.util.ArrayList;
import java.util.List;

import cn.jpush.android.api.JPushInterface;

import static com.framewidget.F.COLOR;
import static com.jinqu.standardNew.F.METHOD_BussCustomer;
import static com.jinqu.standardNew.F.METHOD_FLOWWIDGET;
import static com.jinqu.standardNew.F.METHOD_GETATTACHFILES;
import static com.jinqu.standardNew.F.METHOD_OaMeetingUsesave;
import static com.jinqu.standardNew.F.json2Model;
import static com.jinqu.standardNew.F.mModelUsreLogin;
import static com.jinqu.standardNew.F.refTable_CarUse;


public class FrgPubWeb extends BaseFrg {

    public TextView mTextView_tijiao;
    public TextView mTextView_back;
    public LinearLayout mLinearLayout_bottom;
    public TextView mTextView_yj;
    public ModelBd.RowsBean mRowsBean;
    public TextView mTextView_fj;
    public TextView mTextView_finishi;
    public TextView mTextView_hr;
    public WebView mWebView;
    public String title;
    public String statusID;
    public TextView mTextView_count;
    public LinearLayout mLinearLayout_fj;
    public ArrayList<ModelFjList.RowsBean> mModelWenjianUploads = new ArrayList<>();
    public String json;
    public ProgressDialog mProgressDialog;
    public ModelEmploee.RowsBean mModelEmploee;
    public int type = 0;
    public ProgressBar mProgressBar;
    public TextView mTextView_zc;
    public int position;
    public ModelAddPub mModelAddPub;
    public ModelJDInfo mModelJDInfo;

    @Override
    protected void create(Bundle savedInstanceState) {
        mRowsBean = (ModelBd.RowsBean) getActivity().getIntent().getSerializableExtra("item");
        statusID = getActivity().getIntent().getStringExtra("statusID");
        setContentView(R.layout.frg_pub_shq);
        initView();
        loaddata();
    }

    @Override
    public void disposeMsg(int type, Object obj) {
        switch (type) {
            case 1:
                mModelEmploee = (ModelEmploee.RowsBean) obj;
                mWebView.loadUrl("javascript:SetUseLeaderEmpEnd('" + new Gson().toJson(mModelEmploee) + "')");
                break;
            case 300:
                mModelAddPub = (ModelAddPub) obj;
                mModelAddPub._processor = mRowsBean.mModelMenuConfig.flow.processor;
                if (mRowsBean.FlowRefTable.equals(refTable_CarUse) && (mRowsBean._action.equals("load_next") || mRowsBean._action.equals("save"))) {//用车
                    mModelAddPub.hFormType = "SetCar";
                }
                if (mModelWenjianUploads.size() > 0) {
                    String uploadFile = "";
                    uploadFile += "&lt;Root&gt;&lt;Files RefTable=\"" + mRowsBean.RefTable_file + "\"&gt;&lt;";
                    for (ModelFjList.RowsBean mRowsBean : mModelWenjianUploads) {
                        uploadFile += "File FileName=\"" + mRowsBean.Name
                                + "\" LastModifiedTime=\"" + AbDateUtil.getCurrentDate("yyyy-MM-dd HH:mm:ss") +
                                "\"&gt;" + mRowsBean.IDD + "&lt;/File&gt;&lt;";
                    }
                    uploadFile += "/Files&gt;&lt;/Root&gt;";
                    mModelAddPub.$uplohad$_cache_y12$t1m = uploadFile;
                }
                loadUrlPostBase(METHOD_FLOWWIDGET, json, mModelAddPub);
                break;
            case 102:
                mModelWenjianUploads.add((ModelFjList.RowsBean) obj);
                mTextView_count.setText(mModelWenjianUploads.size() + "");
                break;
            case 103:
                ModelFjList.RowsBean mRowsBean = (ModelFjList.RowsBean) obj;
                for (ModelFjList.RowsBean item : mModelWenjianUploads) {
                    if ((mRowsBean.ID == item.ID && mRowsBean.ID != 0) || (!TextUtils.isEmpty(mRowsBean.IDD) && !TextUtils.isEmpty(item.IDD) && mRowsBean.IDD.equals(item.IDD))) {
                        mModelWenjianUploads.remove(item);
                        break;
                    }
                }
                mTextView_count.setText(mModelWenjianUploads.size() + "");
                break;
            case 104:
                mWebView.loadUrl("javascript:SetProjectSelectEnd('" + obj.toString() + "')");
                break;
            case 101:
                List<ModelEmploee.RowsBean> mBaseEmployeeBeans = (List<ModelEmploee.RowsBean>) obj;
                if (this.type == 0) {
                    mWebView.loadUrl("javascript:usePeopleFormEnd('" + new Gson().toJson(mBaseEmployeeBeans) + "')");
                } else if (this.type == 1) {
                    mWebView.loadUrl("javascript:SetSelectReviewPersonEnd('" + new Gson().toJson(mBaseEmployeeBeans) + "')");
                }
                break;
        }
    }


    @Override
    public void onSuccess(String methodName, String content) {
        if (methodName.equals(METHOD_FLOWWIDGET)) {
            Helper.toast("处理成功", getContext());
            Frame.HANDLES.sentAll("FrgFlowList", 0, null);
            Frame.HANDLES.sentAll("FrgHome", 6, null);
            Frame.HANDLES.sentAll("FrgBd", 10086, null);
            this.finish();
        } else if (methodName.equals(METHOD_FLOWWIDGET + "_JD")) {
            mModelJDInfo = (ModelJDInfo) json2Model(content, ModelJDInfo.class);
            mModelJDInfo.AllowEditControls = (!TextUtils.isEmpty(statusID) && !statusID.equals("1")) ? "" : TextUtils.isEmpty(mModelJDInfo.AllowEditControls) ? mModelJDInfo.AllowEditControls : mModelJDInfo.AllowEditControls.replace(",", ";");
            if (!TextUtils.isEmpty(mModelJDInfo.SignDatas))
                mModelJDInfo.SignDatas = mModelJDInfo.SignDatas.replace("\"", "\\\"");
            mRowsBean.IsNew = mModelJDInfo.IsNew;
            mRowsBean.FlowNodeID = mModelJDInfo.FlowNodeID;
            mRowsBean.FlowMultiSignID = mModelJDInfo.FlowMultiSignID;
            mRowsBean.FlowID = mModelJDInfo.FlowID;
            mHeadlayout.setRText("查看进度");
//            if (mRowsBean.FlowRefTable.equals(refTable_OaMeetingUse)) {
//                mModelJDInfo.AllowEditControls = "{}";
//            } else {
            if (mModelJDInfo.IsFinished || mModelJDInfo.IsFlowFinished || (!TextUtils.isEmpty(statusID) && !statusID.equals("1"))) {
                mLinearLayout_bottom.setVisibility(View.GONE);
            }
            if (mModelJDInfo.NextAction > 0) {
                mTextView_tijiao.setVisibility(View.VISIBLE);
            }
            if (mModelJDInfo.BackAction > 0) {
                mTextView_back.setVisibility(View.VISIBLE);
            }
            if (!mModelJDInfo.IsNew) {
                mTextView_yj.setVisibility(View.VISIBLE);
            }
            if (mModelJDInfo.RejectAction > 0) {
                mTextView_fj.setVisibility(View.VISIBLE);
            }
            if (mModelJDInfo.FinishAction > 0) {
                mTextView_finishi.setVisibility(View.VISIBLE);
            }
            if (mModelJDInfo.ChangeAction > 0) {
                mTextView_hr.setVisibility(View.VISIBLE);
//                }


            }
            mWebView.loadUrl("javascript:initFormBegin('" + new Gson().toJson(mModelJDInfo) + "')");
        } else if (methodName.equals(METHOD_GETATTACHFILES)) {
            ModelFjList mModelFjList = (ModelFjList) json2Model(content, ModelFjList.class);
            mModelWenjianUploads.addAll(mModelFjList.rows);
            mTextView_count.setText(mModelFjList.rows.size() + "");
        } else if (methodName.equals(METHOD_OaMeetingUsesave)) {
            Helper.toast("保存成功", getContext());
            Frame.HANDLES.sentAll("FrgHysList", 0, null);
            this.finish();
        } else if (methodName.equals(METHOD_BussCustomer)) {
            Helper.toast("保存成功", getContext());
            Frame.HANDLES.sentAll("FrgKhgl", 0, null);
            this.finish();
        }
    }

    private void initView() {
        findVMethod();
    }

    private void findVMethod() {
        mProgressDialog = new ProgressDialog(getContext());
        mProgressDialog.setMessage("数据加载中...");
        mProgressDialog.setCancelable(false);
        mTextView_tijiao = (TextView) findViewById(R.id.mTextView_tijiao);
        mTextView_back = (TextView) findViewById(R.id.mTextView_back);
        mLinearLayout_bottom = (LinearLayout) findViewById(R.id.mLinearLayout_bottom);
        mTextView_yj = (TextView) findViewById(R.id.mTextView_yj);
        mTextView_fj = (TextView) findViewById(R.id.mTextView_fj);
        mTextView_finishi = (TextView) findViewById(R.id.mTextView_finishi);
        mTextView_hr = (TextView) findViewById(R.id.mTextView_hr);
        mWebView = (WebView) findViewById(R.id.mWebView);
        mTextView_count = (TextView) findViewById(R.id.mTextView_count);
        mLinearLayout_fj = (LinearLayout) findViewById(R.id.mLinearLayout_fj);
        mProgressBar = (ProgressBar) findViewById(R.id.mProgressBar);
        mTextView_zc = (TextView) findViewById(R.id.mTextView_zc);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            mProgressBar.getProgressDrawable().setTint(Color.parseColor(COLOR));
        mTextView_tijiao.setOnClickListener(Helper.delayClickLitener(this));
        mTextView_zc.setOnClickListener(Helper.delayClickLitener(this));
        mTextView_back.setOnClickListener(Helper.delayClickLitener(this));
        mTextView_yj.setOnClickListener(Helper.delayClickLitener(this));
        mTextView_fj.setOnClickListener(Helper.delayClickLitener(this));
        mTextView_finishi.setOnClickListener(Helper.delayClickLitener(this));
        mTextView_hr.setOnClickListener(Helper.delayClickLitener(this));
        mLinearLayout_fj.setOnClickListener(Helper.delayClickLitener(this));

        GradientDrawable myGrad = (GradientDrawable) getResources().getDrawable(R.drawable.shape_blue);
        myGrad.setColor(Color.parseColor(com.jinqu.standardNew.F.COLOR));
        mTextView_tijiao.setBackground(myGrad);
        mTextView_zc.setBackground(myGrad);
        mTextView_back.setBackground(myGrad);
        mTextView_yj.setBackground(myGrad);
        mTextView_fj.setBackground(myGrad);
        mTextView_finishi.setBackground(myGrad);
        mTextView_hr.setBackground(myGrad);
    }


    public void loaddata() {
        try {
            mRowsBean.RefTable_file = mRowsBean.FlowRefTable = mRowsBean.mModelMenuConfig.flow.refTable;
            if (mRowsBean.mModelMenuConfig.uploaders != null && mRowsBean.mModelMenuConfig.uploaders.size() > 0) {
                mRowsBean.RefTable_file = mRowsBean.mModelMenuConfig.uploaders.get(0).refTable;
                mLinearLayout_fj.setVisibility(View.VISIBLE);
                loadUrlPost(METHOD_GETATTACHFILES, "refID", mRowsBean.Id + "", "refTable", mRowsBean.RefTable_file);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (mRowsBean.Id != 0) {
            JPushInterface.clearAllNotifications(getContext());
            mWebView.loadUrl(BaseConfig.getUri() + "/" + mRowsBean.mModelMenuConfig.grid.editUrl + "&a=" + mModelUsreLogin.name + "&p=" + AbMd5.MD5(mModelUsreLogin.password));

        } else {
            mWebView.loadUrl(BaseConfig.getUri() + "/" + mRowsBean.mModelMenuConfig.grid.addUrl + "?a=" + mModelUsreLogin.name + "&p=" + AbMd5.MD5(mModelUsreLogin.password));
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
                return true;
            }

            @Override
            public void onPageFinished(WebView webView, String s) {
                super.onPageFinished(webView, s);
                if (mRowsBean.IsSave) {
                    mTextView_tijiao.setVisibility(View.VISIBLE);
                    mTextView_tijiao.setText("保存");
                    return;
                }
                if (!TextUtils.isEmpty(mRowsBean.mModelMenuConfig.flow.processor)) {
                    if (mRowsBean.mModelMenuConfig.flow.isShowSave) {
                        mTextView_zc.setVisibility(View.VISIBLE);
                    }
                    loadPost(METHOD_FLOWWIDGET, METHOD_FLOWWIDGET + "_JD", "_refID", mRowsBean.Id, "_refTable", mRowsBean.FlowRefTable, "_flowNodeID", 0, "_action", "load", "_flowMultiSignID", 0);
                } else {
                    mTextView_tijiao.setVisibility(View.VISIBLE);
                    mTextView_tijiao.setText("保存");
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

//        if (!mRowsBean.isNeedSp) {
//            if (mRowsBean.MenuNameEng.equals("CustomerInfo") || mRowsBean.MenuNameEng.equals("CustomerSubInfo") || mRowsBean.MenuNameEng.equals("OtherCustomerInfo") || mRowsBean.MenuNameEng.equals("OtherContracSubtInfo") || mRowsBean.MenuNameEng.equals("OtherContractInfo") || mRowsBean.MenuNameEng.equals("ContractSubInfo") || mRowsBean.MenuNameEng.equals("ContractInfo")) {
//                if (mRowsBean.FlowRefID != 0) {
//                    return;//值丢失问题
//                }
//            }
//        }
        mWebView.addJavascriptInterface(new JsInterface(getActivity()), "JinQuMobile");

    }


    private class JsInterface {
        private Context mContext;

        public JsInterface(Context context) {
            this.mContext = context;
        }

        @JavascriptInterface
        public void FormEnd() {
        }

        @JavascriptInterface
        public void callBack(String json) {//提交表单
            Log.i("json", json);
            if (mTextView_tijiao.getText().toString().equals("保存")) {
                if (mModelWenjianUploads.size() > 0) {
                    ModelUpFile mModelUpFile = new ModelUpFile();
                    String uploadFile = "";
                    uploadFile += "&lt;Root&gt;&lt;Files RefTable=\"" + mRowsBean.RefTable_file + "\"&gt;&lt;";
                    for (ModelFjList.RowsBean mRowsBean : mModelWenjianUploads) {
                        uploadFile += "File FileName=\"" + mRowsBean.Name
                                + "\" LastModifiedTime=\"" + AbDateUtil.getCurrentDate("yyyy-MM-dd HH:mm:ss") +
                                "\"&gt;" + mRowsBean.IDD + "&lt;/File&gt;&lt;";
                    }
                    uploadFile += "/Files&gt;&lt;/Root&gt;";
                    mModelUpFile.$uplohad$_cache_y12$t1m = uploadFile;
                    loadUrlPostBase(METHOD_BussCustomer, json, mModelUpFile);
                } else {
                    loadUrlPostBase(METHOD_BussCustomer, json, null);
                }
                return;
            }

            FrgPubWeb.this.json = json;
            if (title.equals("暂存")) {
                ModelAddPub mModelAddPub = new ModelAddPub();
                mModelAddPub._refID = mRowsBean.Id;
                mModelAddPub._refTable = mRowsBean.FlowRefTable;
                mModelAddPub._flowNodeID = mRowsBean.FlowNodeID;
                mModelAddPub._action = mRowsBean._action;
                mModelAddPub._flowMultiSignID = mRowsBean.FlowMultiSignID;
                mModelAddPub._processor = mRowsBean.mModelMenuConfig.flow.processor;
                if (mRowsBean.FlowRefTable.equals(refTable_CarUse) && (mRowsBean._action.equals("load_next") || mRowsBean._action.equals("save"))) {//用车
                    mModelAddPub.hFormType = "SetCar";
                }
                if (mModelWenjianUploads.size() > 0) {
                    String uploadFile = "";
                    uploadFile += "&lt;Root&gt;&lt;Files RefTable=\"" + mRowsBean.RefTable_file + "\"&gt;&lt;";
                    for (ModelFjList.RowsBean mRowsBean : mModelWenjianUploads) {
                        uploadFile += "File FileName=\"" + mRowsBean.Name
                                + "\" LastModifiedTime=\"" + AbDateUtil.getCurrentDate("yyyy-MM-dd HH:mm:ss") +
                                "\"&gt;" + mRowsBean.IDD + "&lt;/File&gt;&lt;";
                    }
                    uploadFile += "/Files&gt;&lt;/Root&gt;";
                    mModelAddPub.$uplohad$_cache_y12$t1m = uploadFile;
                }
                loadUrlPostBase(METHOD_FLOWWIDGET, json, mModelAddPub);

            } else {
                final View view1 = DialogSub.getView(getContext(), null);
                F.showCenterDialog(getContext(), view1, new CallBackOnly() {
                    @Override
                    public void goReturnDo(Dialog mDialog) {
                        if (mRowsBean._action.equals("load_reject") || mRowsBean._action.equals("load_finish")) {
                            ((DialogSub) view1.getTag()).setNoLoad(mDialog, title, mRowsBean, "FrgPubWeb");
                        } else {
                            ((DialogSub) view1.getTag()).set(mDialog, title, mRowsBean, "FrgPubWeb");
                        }
                    }
                });
            }
        }
        @JavascriptInterface
        public void validateFormEnd(String json) {//提交表单
            Log.i("json", json);
            if (mTextView_tijiao.getText().toString().equals("保存")) {
                if (mModelWenjianUploads.size() > 0) {
                    ModelUpFile mModelUpFile = new ModelUpFile();
                    String uploadFile = "";
                    uploadFile += "&lt;Root&gt;&lt;Files RefTable=\"" + mRowsBean.RefTable_file + "\"&gt;&lt;";
                    for (ModelFjList.RowsBean mRowsBean : mModelWenjianUploads) {
                        uploadFile += "File FileName=\"" + mRowsBean.Name
                                + "\" LastModifiedTime=\"" + AbDateUtil.getCurrentDate("yyyy-MM-dd HH:mm:ss") +
                                "\"&gt;" + mRowsBean.IDD + "&lt;/File&gt;&lt;";
                    }
                    uploadFile += "/Files&gt;&lt;/Root&gt;";
                    mModelUpFile.$uplohad$_cache_y12$t1m = uploadFile;
                    loadUrlPostBase(METHOD_BussCustomer, json, mModelUpFile);
                } else {
                    loadUrlPostBase(METHOD_BussCustomer, json, null);
                }
                return;
            }

            FrgPubWeb.this.json = json;
            if (title.equals("暂存")) {
                ModelAddPub mModelAddPub = new ModelAddPub();
                mModelAddPub._refID = mRowsBean.Id;
                mModelAddPub._refTable = mRowsBean.FlowRefTable;
                mModelAddPub._flowNodeID = mRowsBean.FlowNodeID;
                mModelAddPub._action = mRowsBean._action;
                mModelAddPub._flowMultiSignID = mRowsBean.FlowMultiSignID;
                mModelAddPub._processor = mRowsBean.mModelMenuConfig.flow.processor;
                if (mRowsBean.FlowRefTable.equals(refTable_CarUse) && (mRowsBean._action.equals("load_next") || mRowsBean._action.equals("save"))) {//用车
                    mModelAddPub.hFormType = "SetCar";
                }
                if (mModelWenjianUploads.size() > 0) {
                    String uploadFile = "";
                    uploadFile += "&lt;Root&gt;&lt;Files RefTable=\"" + mRowsBean.RefTable_file + "\"&gt;&lt;";
                    for (ModelFjList.RowsBean mRowsBean : mModelWenjianUploads) {
                        uploadFile += "File FileName=\"" + mRowsBean.Name
                                + "\" LastModifiedTime=\"" + AbDateUtil.getCurrentDate("yyyy-MM-dd HH:mm:ss") +
                                "\"&gt;" + mRowsBean.IDD + "&lt;/File&gt;&lt;";
                    }
                    uploadFile += "/Files&gt;&lt;/Root&gt;";
                    mModelAddPub.$uplohad$_cache_y12$t1m = uploadFile;
                }
                loadUrlPostBase(METHOD_FLOWWIDGET, json, mModelAddPub);

            } else {
                final View view1 = DialogSub.getView(getContext(), null);
                F.showCenterDialog(getContext(), view1, new CallBackOnly() {
                    @Override
                    public void goReturnDo(Dialog mDialog) {
                        if (mRowsBean._action.equals("load_reject") || mRowsBean._action.equals("load_finish")) {
                            ((DialogSub) view1.getTag()).setNoLoad(mDialog, title, mRowsBean, "FrgPubWeb");
                        } else {
                            ((DialogSub) view1.getTag()).set(mDialog, title, mRowsBean, "FrgPubWeb");
                        }
                    }
                });
            }
        }


        @JavascriptInterface
        public void showProgressDialog() {//提交表单
            mProgressDialog.show();
        }

        @JavascriptInterface
        public void hideProgressDialog() {//提交表单
            mProgressDialog.dismiss();
        }

        @JavascriptInterface
        public void SelectReviewPersonBegin(String json, String method) {
            type = 1;
            Helper.startActivity(getContext(), FrgXzRenyuan.class, TitleAct.class, "id", json, "from", "FrgPubWeb");
        }

        @JavascriptInterface
        public void EmpSingleSelectBegin(String json, String method) {//选择责任人
            Helper.startActivity(getContext(), FrgXzRenyuanOne.class, TitleAct.class, "from", "FrgPubWeb");
        }

        @JavascriptInterface
        public void showProfileEnd(String id) {
            Helper.startActivity(getContext(), FrgGrzx.class, NoTitleAct.class, "id", Integer.valueOf(id));
        }

        @JavascriptInterface
        public void usePeopleSingleSelectBegin(String json, String method) {//选择同车人
            type = 0;
            Helper.startActivity(getContext(), FrgXzRenyuan.class, TitleAct.class, "id", json, "from", "FrgPubWeb");

        }


    }


    @Override
    public void setActionBar(ActionBar actionBar, Context context) {
        super.setActionBar(actionBar, context);
        mHeadlayout.setTitle(mRowsBean.text);
        mHeadlayout.setRightOnclicker(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mRowsBean.FlowID != null && !TextUtils.isEmpty(mRowsBean.FlowID.toString())) {
                    Helper.startActivity(getContext(), FrgShpjnduList.class, TitleAct.class, "id", String.format("%.0f", Double.valueOf(mRowsBean.FlowID.toString())));
                } else {
                    Helper.startActivity(getContext(), FrgShpjnduList.class, TitleAct.class, "id", "0");
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        com.framewidget.F.closeSoftKey(getActivity());
        switch (v.getId()) {
            case R.id.mTextView_tijiao:
                mRowsBean._action = "load_next";
                title = "提交";
                mWebView.loadUrl("javascript:validateFormBegin()");
                break;
            case R.id.mTextView_zc:
                mRowsBean._action = "save";
                title = "暂存";
                mWebView.loadUrl("javascript:validateFormBegin()");
                break;
            case R.id.mTextView_back:
                mRowsBean._action = "load_back";
                title = "回退";
                mWebView.loadUrl("javascript:noValidateFormBegin()");
                break;
            case R.id.mTextView_yj:
                mRowsBean._action = "load_transfer";
                title = "工作移交";
                mWebView.loadUrl("javascript:noValidateFormBegin()");
                break;
            case R.id.mTextView_fj:
                mRowsBean._action = "load_reject";
                title = "直接否决";
                mWebView.loadUrl("javascript:noValidateFormBegin()");
                break;
            case R.id.mTextView_finishi:
                mRowsBean._action = "load_finish";
                title = "直接完成";
                mWebView.loadUrl("javascript:validateFormBegin()");
                break;
            case R.id.mTextView_hr:
                mRowsBean._action = "load_change";
                title = "换人处理";
                mWebView.loadUrl("javascript:noValidateFormBegin()");
                break;
            case R.id.mLinearLayout_fj:
                if ((mModelJDInfo != null && mModelJDInfo.StepOrder == 1) || mRowsBean.Id == 0) {
                    Helper.startActivity(getContext(), FrgFujianListEdit.class, TitleAct.class, "id", mRowsBean.Id, "refTable", mRowsBean.RefTable_file, "mModelWenjianUploads", mModelWenjianUploads, "from", "FrgPubWeb");
                } else {
                    Helper.startActivity(getContext(), FrgFujianList.class, TitleAct.class, "id", mRowsBean.Id + "", "refTable", mRowsBean.RefTable_file);
                }
                break;
        }
    }
}