//
//  FrgCreateDtpl
//
//  Created by DELL on 2018-05-17 15:29:18
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

import com.framewidget.view.MGridPhotoChoose;
import com.jinqu.standardNew.R;
import com.jinqu.standardNew.model.ModelgzdtList;
import com.mdx.framework.Frame;
import com.mdx.framework.utility.Helper;
import com.mdx.framework.widget.ActionBar;

import static com.jinqu.standardNew.F.METHOD_DESDISCUSSSAVEREPLY;


public class FrgCreateDtpl extends BaseFrg {

    public EditText mEditText_content;
    public MGridPhotoChoose mMGridPhotoChoose;
    public TextView mTextView_address;

    public ModelgzdtList.RowsBean item;

    @Override
    protected void create(Bundle savedInstanceState) {
        item = (ModelgzdtList.RowsBean) getActivity().getIntent().getSerializableExtra("item");
        setContentView(R.layout.frg_create_dtpl);
        initView();
        loaddata();
    }

    private void initView() {
        findVMethod();
    }

    private void findVMethod() {
        mEditText_content = (EditText) findViewById(R.id.mEditText_content);
        mMGridPhotoChoose = (MGridPhotoChoose) findViewById(R.id.mMGridPhotoChoose);
        mTextView_address = (TextView) findViewById(R.id.mTextView_address);


    }

    public void loaddata() {

    }

    @Override
    public void onSuccess(String methodName, String content) {
        if (methodName.equals(METHOD_DESDISCUSSSAVEREPLY)) {
            Helper.toast("评论成功", getContext());
            this.finish();
            Frame.HANDLES.sentAll("FrgGzdtZ", 0, true);
        }
    }

    @Override
    public void setActionBar(ActionBar actionBar, Context context) {
        super.setActionBar(actionBar, context);
        mHeadlayout.setTitle("发布评论");
        mHeadlayout.setRText("完成");
        mHeadlayout.setRightOnclicker(new View.OnClickListener() {
            @Override
            public void onClick(View view) { setBgClickClor(view);
                if (TextUtils.isEmpty(mEditText_content.getText().toString()
                        .trim())) {
                    Helper.toast("请输入评论内容", getContext());
                    return;
                }
                loadUrlPost(METHOD_DESDISCUSSSAVEREPLY, "ReplyContent", mEditText_content.getText().toString(), "TalkId", item.Id);
            }
        });
    }
}