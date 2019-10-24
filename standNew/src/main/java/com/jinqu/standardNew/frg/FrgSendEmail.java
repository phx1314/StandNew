//
//  FrgSendEmail
//
//  Created by DELL on 2018-06-22 10:10:15
//  Copyright (c) DELL All rights reserved.


/**

 */

package com.jinqu.standardNew.frg;

import android.content.Context;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ab.util.AbDateUtil;
import com.framewidget.F;
import com.jinqu.standardNew.R;
import com.jinqu.standardNew.model.ModelEmploee;
import com.jinqu.standardNew.model.ModelFjList;
import com.jinqu.standardNew.model.ModelYjDetail;
import com.mdx.framework.Frame;
import com.mdx.framework.activity.TitleAct;
import com.mdx.framework.utility.Helper;
import com.mdx.framework.widget.ActionBar;

import java.util.ArrayList;
import java.util.List;

import static com.jinqu.standardNew.F.METHOD_GETATTACHFILES;
import static com.jinqu.standardNew.F.METHOD_OAMAIL;
import static com.jinqu.standardNew.F.METHOD_OAMAILDELCG;
import static com.jinqu.standardNew.F.METHOD_OAMAILDETAIL;
import static com.jinqu.standardNew.F.getCurrentTime;
import static com.jinqu.standardNew.F.json2Model;
import static com.jinqu.standardNew.F.refTable_OaMail;


public class FrgSendEmail extends BaseFrg {

    public TextView mTextView_name;
    public EditText mTextView_title;
    public EditText mTextView_content;
    public LinearLayout mLinearLayout_fj;
    public TextView mTextView_count;
    public LinearLayout mLinearLayout_bottom;
    public TextView mTextView_fs;
    public TextView mTextView_cg;
    public String id;
    public String uploadFile = "";
    public CheckBox mCheckBox;
    public String MailFlag = "0";
    public String name;
    public int type;
    public ModelYjDetail mModelYjDetail;
    public String ids = "";
    public ArrayList<ModelFjList.RowsBean> mModelWenjianUploads = new ArrayList<>();
    public TextView mTextView_delete;
    public TextView mTextView_delete_forever;
    public ImageView mMImageView_add;
    public LinearLayout mLinearLayout_delete;
    public com.jinqu.standardNew.view.FloatingActionButton fab;


    @Override
    protected void create(Bundle savedInstanceState) {
        id = getActivity().getIntent().getStringExtra("id");
        name = getActivity().getIntent().getStringExtra("name");
        type = getActivity().getIntent().getIntExtra("type", 1);
        setContentView(R.layout.frg_send_email);
        initView();
        loaddata();
    }

    @Override
    public void disposeMsg(int type, Object obj) {
        switch (type) {
            case 101:
                List<ModelEmploee.RowsBean> mBaseEmployeeBeans = (List<ModelEmploee.RowsBean>) obj;
                String string = "";
                ids = "";
                for (ModelEmploee.RowsBean mBaseEmployeeBean : mBaseEmployeeBeans) {
                    string += mBaseEmployeeBean.EmpName + ",";
                    ids += mBaseEmployeeBean.EmpID + ",";
                }
                mTextView_name.setText(string.subSequence(0, string.length() - 1));
                break;
            case 102:
                mModelWenjianUploads.add((ModelFjList.RowsBean) obj);
                mTextView_count.setText(mModelWenjianUploads.size() + "");
                break;
            case 103:
                ModelFjList.RowsBean mRowsBean = (ModelFjList.RowsBean) obj;
                for (ModelFjList.RowsBean item : mModelWenjianUploads) {
                    if (mRowsBean.ID == item.ID) {
                        mModelWenjianUploads.remove(item);
                        break;
                    }
                }
                mTextView_count.setText(mModelWenjianUploads.size() + "");
                break;
        }
    }

    private void initView() {
        findVMethod();
    }

    private void findVMethod() {
        mTextView_name = (TextView) findViewById(R.id.mTextView_name);
        mTextView_title = (EditText) findViewById(R.id.mTextView_title);
        mTextView_content = (EditText) findViewById(R.id.mTextView_content);
        mCheckBox = (CheckBox) findViewById(R.id.mCheckBox);
        mLinearLayout_fj = (LinearLayout) findViewById(R.id.mLinearLayout_fj);
        mTextView_count = (TextView) findViewById(R.id.mTextView_count);
        mLinearLayout_bottom = (LinearLayout) findViewById(R.id.mLinearLayout_bottom);
        mTextView_fs = (TextView) findViewById(R.id.mTextView_fs);
        mTextView_cg = (TextView) findViewById(R.id.mTextView_cg);
        mTextView_delete = (TextView) findViewById(R.id.mTextView_delete);
        mTextView_delete_forever = (TextView) findViewById(R.id.mTextView_delete_forever);
        mMImageView_add = (ImageView) findViewById(R.id.mMImageView_add);
        mLinearLayout_delete = (LinearLayout) findViewById(R.id.mLinearLayout_delete);
        fab = (com.jinqu.standardNew.view.FloatingActionButton) findViewById(R.id.fab);
        mLinearLayout_fj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Helper.startActivity(getContext(), FrgFujianListEdit.class, TitleAct.class, "id", TextUtils.isEmpty(id) ? "0" : id, "refTable", "OaMail", "mModelWenjianUploads", mModelWenjianUploads, "from", "FrgSendEmail");
            }
        });
        mMImageView_add.setOnClickListener(Helper.delayClickLitener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Helper.startActivity(getContext(), FrgXzRenyuan.class, TitleAct.class, "id", ids, "from", "FrgSendEmail");
            }
        }));
        mTextView_delete.setOnClickListener(Helper.delayClickLitener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadUrlPost(METHOD_OAMAILDELCG, "ids", id, "DelType", "false");
            }
        }));
        mTextView_delete_forever.setOnClickListener(Helper.delayClickLitener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadUrlPost(METHOD_OAMAILDELCG, "ids", id, "DelType", "true");
            }
        }));
        mTextView_fs.setOnClickListener(Helper.delayClickLitener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveData(0);
            }
        }));
        mTextView_cg.setOnClickListener(Helper.delayClickLitener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveData(1);
            }
        }));
        fab.setOnClickListener(Helper.delayClickLitener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                F.closeSoftKey(getActivity());
                FrgSendEmail.this.finish();
            }
        }));
    }

    public void saveData(int type) {
        if (TextUtils.isEmpty(mTextView_name.getText().toString().trim())) {
            Helper.toast("请选择收件人", getContext());
            return;
        }
        if (TextUtils.isEmpty(mTextView_title.getText().toString().trim())) {
            Helper.toast("请输入主题", getContext());
            return;
        }
        MailFlag = type + "";
        uploadFile += "&lt;Root&gt;&lt;Files RefTable=\"OaMail\"&gt;&lt;";
        for (ModelFjList.RowsBean mRowsBean : mModelWenjianUploads) {
            uploadFile += "File FileName=\"" + mRowsBean.Name
                    + "\" LastModifiedTime=\"" + AbDateUtil.getCurrentDate("yyyy-MM-dd HH:mm:ss") +
                    "\"&gt;" + mRowsBean.IDD + "&lt;/File&gt;&lt;";
        }
        uploadFile += "/Files&gt;&lt;/Root&gt;";
        loadUrlPost(METHOD_OAMAIL, "Id", this.type == 3 ? id : "0", "MailFlag", MailFlag, "TypeID", this.type == 3 ? "3" : "0", "SendEmp", mTextView_name.getText().toString().trim(), "MailTitle", mTextView_title.getText().toString().trim(), "MailNote", mTextView_content.getText().toString().trim(), "MailIsBBC", type == 1 ? "" : (mCheckBox.isChecked() ? "0" : "1"), "MailDate", getCurrentTime("yyyy-MM-dd"), "$uplohad$_cache_y12$t1m", uploadFile);
    }

    public void loaddata() {
        if (!TextUtils.isEmpty(name)) {
            loadUrlPost(METHOD_OAMAILDETAIL, "Id", id, "ReceiveFlag", "0");
        } else if (!TextUtils.isEmpty(id)) {
            if (type == 3) {
                loadUrlPost(METHOD_GETATTACHFILES, "refID", id, "refTable", refTable_OaMail);
                mTextView_delete.setVisibility(View.VISIBLE);
                mLinearLayout_delete.setVisibility(View.VISIBLE);
            }
            loadUrlPost(METHOD_OAMAILDETAIL, "Id", id, "ReceiveFlag", "0", "type", "1");
        }
    }

    @Override
    public void onSuccess(String methodName, String content) {
        if (methodName.equals(METHOD_OAMAIL)) {
            Frame.HANDLES.sentAll("FrgEmailList", 0, null);
            Helper.toast(MailFlag.equals("0") ? "发送成功" : "保存成功", getContext());
            F.closeSoftKey(getActivity());
            finish();
        } else if (methodName.equals(METHOD_OAMAILDETAIL)) {
            mModelYjDetail = (ModelYjDetail) json2Model(content, ModelYjDetail.class);
            if (!TextUtils.isEmpty(name)) {
                mTextView_title.setText("【回复】" + mModelYjDetail.model.MailTitle);
                mTextView_name.setText(mModelYjDetail.model.CreatorEmpName);
                ids = mModelYjDetail.model.CreatorEmpId + "";
            } else if (type == 3) {
                mTextView_title.setText(mModelYjDetail.model.MailTitle);
                mTextView_name.setText(mModelYjDetail.SendEmp);
                ids = mModelYjDetail.SendEmpID + "";
            } else {
                mTextView_title.setText("【转发】" + mModelYjDetail.model.MailTitle);
                mCheckBox.setChecked(mModelYjDetail.model.MailIsBBC == 0 ? true : false);
                for (ModelYjDetail.AttachDataBean item : mModelYjDetail.AttachData) {
                    ModelFjList.RowsBean mRowsBean = new ModelFjList.RowsBean();
                    mRowsBean.IDD = item.FileName;
                    mRowsBean.LastModifyDate = item.LastModifiedTime;
                    mRowsBean.Size = Integer.valueOf(item.Size);
                    mRowsBean.Name = item.RealName;
                    mRowsBean.UploadDate = item.UploadTime;
                    mRowsBean.EmpName = item.EmpName;
                    mModelWenjianUploads.add(mRowsBean);
                }
                mTextView_count.setText(mModelYjDetail.AttachData.size() + "");
            }
            mTextView_content.setText(Html.fromHtml(mModelYjDetail.model.MailNote));
        } else if (methodName.equals(METHOD_GETATTACHFILES)) {
            ModelFjList mModelFjListHc = (ModelFjList) json2Model(content, ModelFjList.class);
            mTextView_count.setText(mModelFjListHc.rows.size() + "");
            for (ModelFjList.RowsBean mRowsBean : mModelFjListHc.rows) {
                mModelWenjianUploads.add(mRowsBean);
            }
        } else if (methodName.equals(METHOD_OAMAILDELCG)) {
            Helper.toast("删除成功", getContext());
            F.closeSoftKey(getActivity());
            this.finish();
            Frame.HANDLES.sentAll("FrgEmailList", 0, null);
        }
    }

    @Override
    public void setActionBar(ActionBar actionBar, Context context) {
        super.setActionBar(actionBar, context);
        if (!TextUtils.isEmpty(name)) {
            mHeadlayout.setTitle("回复邮件");
        } else if (type == 3) {
            mHeadlayout.setTitle("草稿邮件");
        } else if (!TextUtils.isEmpty(id)) {
            mHeadlayout.setTitle("转发邮件");
        } else {
            mHeadlayout.setTitle("写邮件");
        }
    }
}