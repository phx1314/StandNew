//
//  FrgCreateQun
//
//  Created by DELL on 2018-05-15 16:20:55
//  Copyright (c) DELL All rights reserved.


/**

 */

package com.jinqu.standardNew.frg;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.framewidget.frg.FrgPubBianJi;
import com.jinqu.standardNew.R;
import com.jinqu.standardNew.ada.AdaQunChengyuan;
import com.jinqu.standardNew.model.ModelCreateQun;
import com.jinqu.standardNew.model.ModelEmploee;
import com.jinqu.standardNew.model.ModelQunZiLiao;
import com.jinqu.standardNew.model.ModelUpLoadFile;
import com.jinqu.standardNew.model.ModelUsreLogin;
import com.mdx.framework.Frame;
import com.mdx.framework.activity.TitleAct;
import com.mdx.framework.commons.ParamsManager;
import com.mdx.framework.config.BaseConfig;
import com.mdx.framework.utility.Helper;
import com.mdx.framework.widget.ActionBar;
import com.mdx.framework.widget.MImageView;
import com.mdx.framework.widget.getphoto.PopUpdataPhoto;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import io.rong.imkit.RongIM;
import io.rong.imlib.model.Conversation;

import static com.jinqu.standardNew.F.METHOD_DELETEGROUP;
import static com.jinqu.standardNew.F.METHOD_GROUP;
import static com.jinqu.standardNew.F.METHOD_GROUPINFO;
import static com.jinqu.standardNew.F.METHOD_QUITGROUP;
import static com.jinqu.standardNew.F.METHOD_UPDATEGROUP;
import static com.jinqu.standardNew.F.METHOD_UPLOADFILE;
import static com.jinqu.standardNew.F.json2Model;
import static com.jinqu.standardNew.F.mModelUsreLogin;
import static com.jinqu.standardNew.F.reFreashQiTaQun;


public class FrgCreateQun extends BaseFrg {

    public LinearLayout mLinearLayout_1;
    public MImageView mMImageView;
    public LinearLayout mLinearLayout_2;
    public TextView mTextView_qname;
    public LinearLayout mLinearLayout_3;
    public TextView mTextView_xm_name;
    public LinearLayout mLinearLayout_4;
    public TextView mTextView_qbz;
    public GridView mGridView;
    public TextView clk_mTextView_sub;
    public List<ModelEmploee.RowsBean> mBaseEmployeeBeans = new ArrayList<ModelEmploee.RowsBean>();
    public ModelUpLoadFile mModelUpLoadFile;
    public ModelQunZiLiao mModelQunZiLiao;
    public String refTable;
    public String refId;
    public String id;
    public LinearLayout mLinearLayout_sub;

    @Override
    protected void create(Bundle savedInstanceState) {
        id = getActivity().getIntent().getStringExtra("id");
        refTable = getActivity().getIntent().getStringExtra("refTable");
        refId = getActivity().getIntent().getStringExtra("refId");
        setContentView(R.layout.frg_create_qun);
        initView();
        loaddata();
    }

    public String getIds() {
        String ids = "";
        for (ModelEmploee.RowsBean mRowsBean : mBaseEmployeeBeans) {
            if (mRowsBean.EmpID != 0)
                ids += mRowsBean.EmpID + ",";
        }
        return ids.substring(0, ids.length() - 1);
    }

    @Override
    public void disposeMsg(int type, Object obj) {
        switch (type) {
            case 101:
                mBaseEmployeeBeans = (List<ModelEmploee.RowsBean>) obj;
                ModelEmploee.RowsBean mRowsBean1 = new ModelEmploee.RowsBean();
                mBaseEmployeeBeans.add(mRowsBean1);
                mGridView.setAdapter(new AdaQunChengyuan(getContext(), mBaseEmployeeBeans));
                changeData("UserIds", getIds());
                break;
            case 201:
                mTextView_qname.setText(obj.toString());
                break;
            case 202:
                mTextView_qbz.setText(obj.toString());
                changeData("GroupNote", mTextView_qbz.getText().toString());
                break;
        }
    }

    public void changeData(String key, String value) {
        if (!TextUtils.isEmpty(id)) {
            if (TextUtils.isEmpty(refId)) {
                loadPost(METHOD_UPDATEGROUP, "updateGroup", "GroupID", id, key, value);
            } else {
                loadPost(METHOD_UPDATEGROUP, "updateGroup", "GroupID", id, key, value, "refId", refId, "refTable", refTable);
            }

        }
    }

    private void initView() {
        findVMethod();
    }

    private void findVMethod() {
        mLinearLayout_1 = (LinearLayout) findViewById(R.id.mLinearLayout_1);
        mMImageView = (MImageView) findViewById(R.id.mMImageView);
        mLinearLayout_2 = (LinearLayout) findViewById(R.id.mLinearLayout_2);
        mTextView_qname = (TextView) findViewById(R.id.mTextView_qname);
        mLinearLayout_3 = (LinearLayout) findViewById(R.id.mLinearLayout_3);
        mTextView_xm_name = (TextView) findViewById(R.id.mTextView_xm_name);
        mLinearLayout_4 = (LinearLayout) findViewById(R.id.mLinearLayout_4);
        mTextView_qbz = (TextView) findViewById(R.id.mTextView_qbz);
        mGridView = (GridView) findViewById(R.id.mGridView);
        clk_mTextView_sub = (TextView) findViewById(R.id.clk_mTextView_sub);
        mLinearLayout_sub = (LinearLayout) findViewById(R.id.mLinearLayout_sub);

        mLinearLayout_sub.setOnClickListener(com.mdx.framework.utility.Helper.delayClickLitener(this));
        mLinearLayout_2.setOnClickListener(com.mdx.framework.utility.Helper.delayClickLitener(this));
        mLinearLayout_4.setOnClickListener(com.mdx.framework.utility.Helper.delayClickLitener(this));
        mMImageView.setOnClickListener(com.mdx.framework.utility.Helper.delayClickLitener(this));
    }

    public void loaddata() {
        if (!TextUtils.isEmpty(id)) {
            loadUrlPost(METHOD_GROUPINFO + id);
        } else {
            clk_mTextView_sub.setText("创建群");
            ModelEmploee.RowsBean mRowsBean = new ModelEmploee.RowsBean();
            ModelEmploee.RowsBean mRowsBean1 = new ModelEmploee.RowsBean();
            mRowsBean.EmpID = mModelUsreLogin.UserInfo.EmpID;
            for (ModelUsreLogin.BaseEmployeeBean item : mModelUsreLogin.BaseEmployee) {
                if (item.EmpID == mModelUsreLogin.UserInfo.EmpID) {
                    mRowsBean.EmpHead = item.EmpHead;
                    break;
                }
            }
            mRowsBean.EmpName = mModelUsreLogin.UserInfo.EmpName;
            mBaseEmployeeBeans.add(mRowsBean);
            mBaseEmployeeBeans.add(mRowsBean1);
            mGridView.setAdapter(new AdaQunChengyuan(getContext(), mBaseEmployeeBeans));
        }
    }

    @Override
    public void onSuccess(String methodName, String content) {
        if (methodName.equals(METHOD_GROUPINFO + id)) {
            mModelQunZiLiao = (ModelQunZiLiao) json2Model(content, ModelQunZiLiao.class);
            reFreashQiTaQun(mModelQunZiLiao);
            if (mModelQunZiLiao.stateType == 0) {
                mTextView_qname.setText(mModelQunZiLiao.stateValue.Model.GroupName);
                mTextView_qbz.setText(mModelQunZiLiao.stateValue.Model.GroupNote);
                mMImageView.setObj(BaseConfig.getUri() + ParamsManager.get("PortraitUrlGroupNew") + mModelQunZiLiao.stateValue.Model.PortraitUri);
                mMImageView.setCircle(true);
                mBaseEmployeeBeans.addAll(mModelQunZiLiao.stateValue.UserArr);
                if (mModelQunZiLiao.stateValue.Model.CreateEmp == mModelUsreLogin.UserInfo.EmpID) {//群主
                    ModelEmploee.RowsBean mRowsBean = new ModelEmploee.RowsBean();
                    mBaseEmployeeBeans.add(mRowsBean);
                    clk_mTextView_sub.setText("退出并解散群");
                } else {
                    clk_mTextView_sub.setText("退出群");
                }
                mGridView.setAdapter(new AdaQunChengyuan(getContext(), mBaseEmployeeBeans));
            } else {
                Helper.toast(mModelQunZiLiao.stateMsg, getContext());
            }
        } else if (methodName.equals(METHOD_DELETEGROUP + id)) {
            Helper.toast("删除成功", getContext());
            RongIM.getInstance()
                    .getRongIMClient()
                    .removeConversation(Conversation.ConversationType.GROUP,
                            ParamsManager.get("ChatStr") + mModelQunZiLiao.stateValue.Model.GroupID);
            this.finish();
            Frame.HANDLES.sentAll("ConversationActivity", 2, null);
            Frame.HANDLES.sentAll("FrgQzList,FrgTlQunzu", 0, null);
        } else if (methodName.equals(METHOD_QUITGROUP + id)) {
            Helper.toast("退出成功", getContext());
            RongIM.getInstance()
                    .getRongIMClient()
                    .removeConversation(Conversation.ConversationType.GROUP,
                            ParamsManager.get("ChatStr") + mModelQunZiLiao.stateValue.Model.GroupID);
            this.finish();
            Frame.HANDLES.sentAll("ConversationActivity", 2, null);
            Frame.HANDLES.sentAll("FrgQzList,FrgTlQunzu", 0, null);
        } else if (methodName.equals(METHOD_UPLOADFILE)) {
            mModelUpLoadFile = (ModelUpLoadFile) json2Model(content, ModelUpLoadFile.class);
            if (!TextUtils.isEmpty(id)) {
                if (TextUtils.isEmpty(refId)) {
                    loadPost(METHOD_UPDATEGROUP, "updateGroup", "GroupID", id, "FileID", mModelUpLoadFile.ID, "FileName", System.currentTimeMillis() + ".jpg");
                } else {
                    loadPost(METHOD_UPDATEGROUP, "updateGroup", "GroupID", id, "FileID", mModelUpLoadFile.ID, "FileName", System.currentTimeMillis() + ".jpg", "refId", refId, "refTable", refTable);
                }
            }
        } else if (methodName.equals(METHOD_GROUP)) {
            ModelCreateQun mModelCreateQun = (ModelCreateQun) json2Model(content, ModelCreateQun.class);
            Helper.toast(mModelCreateQun.stateMsg, getContext());
            if (mModelCreateQun.stateType == 0) {
                this.finish();
                Frame.HANDLES.sentAll("FrgQzList,FrgTlQunzu", 0, null);
                Frame.HANDLES.sentAll("FrgHome", 121, null);
            }
        } else if (methodName.equals("updateGroup")) {
            Frame.HANDLES.sentAll("FrgQzList,FrgTlQunzu", 0, null);
        }
    }

    @Override
    public void onClick(android.view.View v) {
        super.onClick(v);
        if (R.id.mLinearLayout_sub == v.getId()) {
            if (!TextUtils.isEmpty(id)) {
                if (mModelQunZiLiao.stateValue.Model.CreateEmp == mModelUsreLogin.UserInfo.EmpID) {//群主
                    ModelEmploee.RowsBean mRowsBean = new ModelEmploee.RowsBean();
                    mBaseEmployeeBeans.add(mRowsBean);
                    loadUrlPost(METHOD_DELETEGROUP + id);
                } else {
                    loadUrlPost(METHOD_QUITGROUP + id);
                }

            } else {
                if (TextUtils.isEmpty(mTextView_qname.getText().toString())) {
                    Helper.toast("请输入群名", getContext());
                    return;
                }
                if (mModelUpLoadFile != null) {
                    if (TextUtils.isEmpty(refId)) {
                        loadUrlPost(METHOD_GROUP, "GroupName", mTextView_qname.getText().toString(), "GroupNote", mTextView_qbz.getText().toString(), "UserIds", getIds(), "FileID", mModelUpLoadFile.ID, "FileName", System.currentTimeMillis() + ".jpg");
                    } else {
                        loadUrlPost(METHOD_GROUP, "GroupName", mTextView_qname.getText().toString(), "GroupNote", mTextView_qbz.getText().toString(), "UserIds", getIds(), "FileID", mModelUpLoadFile.ID, "FileName", System.currentTimeMillis() + ".jpg", "refId", refId, "refTable", refTable);
                    }
                } else {
                    if (TextUtils.isEmpty(refId)) {
                        loadUrlPost(METHOD_GROUP, "GroupName", mTextView_qname.getText().toString(), "GroupNote", mTextView_qbz.getText().toString(), "UserIds", getIds());
                    } else {
                        loadUrlPost(METHOD_GROUP, "GroupName", mTextView_qname.getText().toString(), "GroupNote", mTextView_qbz.getText().toString(), "UserIds", getIds(), "refId", refId, "refTable", refTable);
                    }
                }
            }
        } else if (R.id.mLinearLayout_2 == v.getId()) {
            if (TextUtils.isEmpty(id))
                Helper.startActivity(getContext(), FrgPubBianJi.class,
                        TitleAct.class, "from", "FrgCreateQun", "EDT", 201,
                        "data", mTextView_qname.getText().toString(), "max", 11, "hint", "群名");

        } else if (R.id.mLinearLayout_4 == v.getId()) {
            if (TextUtils.isEmpty(id) || mModelQunZiLiao.stateValue.Model.CreateEmp == mModelUsreLogin.UserInfo.EmpID) {//群主
                Helper.startActivity(getContext(), FrgPubBianJi.class,
                        TitleAct.class, "from", "FrgCreateQun", "EDT", 202,
                        "data", mTextView_qbz.getText().toString(), "max", 150, "hint", "群备注");
            }
        } else if (R.id.mMImageView == v.getId()) {
            if (TextUtils.isEmpty(id) || mModelQunZiLiao.stateValue.Model.CreateEmp == mModelUsreLogin.UserInfo.EmpID) {//群主
                Helper.getPhoto(getActivity(), new PopUpdataPhoto.OnReceiverPhoto() {
                    @Override
                    public void onReceiverPhoto(String photoPath, int width,
                                                int height) {
                        if (photoPath != null) {
                            mMImageView.setObj("file:" + photoPath);
                            mMImageView.setCircle(true);
                            loadUrlPost(METHOD_UPLOADFILE, "", new File(photoPath));
                        }
                    }
                }, 10, 10, 640, 640);
            }
        } else if (R.id.mImageView_back == v.getId()) {
            this.finish();
        }
    }

    @Override
    public void setActionBar(ActionBar actionBar, Context context) {
        super.setActionBar(actionBar, context);
        mHeadlayout.setTitle("群资料");
    }
}