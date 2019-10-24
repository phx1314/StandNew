//
//  FrgCreateDt
//
//  Created by DELL on 2018-05-17 10:13:31
//  Copyright (c) DELL All rights reserved.


/**

 */

package com.jinqu.standardNew.frg;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.framewidget.frg.FrgList;
import com.framewidget.view.MGridPhotoChoose;
import com.jinqu.standardNew.R;
import com.jinqu.standardNew.model.ModelUsreLogin;
import com.mdx.framework.Frame;
import com.mdx.framework.activity.TitleAct;
import com.mdx.framework.utility.Helper;
import com.mdx.framework.widget.ActionBar;

import java.util.ArrayList;
import java.util.List;

import static com.jinqu.standardNew.F.METHOD_DESDISCUSSSAVE;
import static com.jinqu.standardNew.F.mModelUsreLogin;


public class FrgCreateDt extends BaseFrg {

    public EditText mEditText_title;
    public EditText mEditText_content;
    public MGridPhotoChoose mMGridPhotoChoose;
    public TextView mTextView_address;
    public String TaskGroupId;
    public TextView mTextView_type;
    public List<String> list_data = new ArrayList();
    public List<String> list = new ArrayList();
    public String type_id;

    @Override
    protected void create(Bundle savedInstanceState) {
        TaskGroupId = getActivity().getIntent().getStringExtra("TaskGroupId");
        setContentView(R.layout.frg_create_dt);
        initView();
        loaddata();
    }

    @Override
    public void disposeMsg(int type, Object obj) {
        switch (type) {
            case 0:
                mTextView_type.setText(obj.toString());
                type_id = list_data.get(list.indexOf(obj.toString()));
                break;
        }
    }



    private void initView() {
        findVMethod();
    }

    private void findVMethod() {
        mEditText_title = (EditText) findViewById(R.id.mEditText_title);
        mEditText_content = (EditText) findViewById(R.id.mEditText_content);
        mMGridPhotoChoose = (MGridPhotoChoose) findViewById(R.id.mMGridPhotoChoose);
        mTextView_address = (TextView) findViewById(R.id.mTextView_address);
        mTextView_type = (TextView) findViewById(R.id.mTextView_type);

        mTextView_type.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setBgClickClor(v);
                Helper.startActivity(getContext(), FrgList.class, TitleAct.class, "from", "FrgCreateDt", "type", 0, "title", "选择类型", "data", list);
            }
        });
    }

    public void loaddata() {

        for (ModelUsreLogin.BaseDataBean mBaseDataBean : mModelUsreLogin.BaseData) {
            if (mBaseDataBean.BaseOrder.startsWith("069_")) {
                list_data.add(mBaseDataBean.BaseID + "");
                list.add(mBaseDataBean.BaseName);
            }
        }
    }

    @Override
    public void onSuccess(String methodName, String content) {
        if (methodName.equals(METHOD_DESDISCUSSSAVE)) {
            Helper.toast("发布成功", getContext());
            this.finish();
            Frame.HANDLES.sentAll("FrgGzdt,FrgGzdtF", 0, null);
        }
    }

    @Override
    public void setActionBar(ActionBar actionBar, Context context) {
        super.setActionBar(actionBar, context);
        mHeadlayout.setTitle("创建动态");
        mHeadlayout.setRText("完成");
        mHeadlayout.setRightOnclicker(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(mEditText_title.getText().toString()
                        .trim())) {
                    Helper.toast("请输入标题", getContext());
                    return;
                }
                if (TextUtils.isEmpty(mTextView_type.getText().toString()
                        .trim())) {
                    Helper.toast("请选择类型", getContext());
                    return;
                }
                loadUrlPost(METHOD_DESDISCUSSSAVE, "Id", "0", "TalkTitle", mEditText_title.getText().toString(), "TalkContent", mEditText_content.getText().toString(), "TalkGroupId", type_id, "TalkRefId", TaskGroupId, "TalkRefTable", "ProjDiscuss");
            }
        });
    }
}