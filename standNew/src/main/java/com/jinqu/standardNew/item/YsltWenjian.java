//
//  YsltWenjian
//
//  Created by df on 2016-02-02 10:25:31
//  Copyright (c) df All rights reserved.

/**

 */

package com.jinqu.standardNew.item;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jinqu.standardNew.R;
import com.jone.SDCard.FileSizeUtil;
import com.mdx.framework.Frame;
import com.mdx.framework.widget.MImageView;
import com.mdx.framework.widget.SwipMoreView;

import java.io.File;

import io.rong.app.F;

import static com.ab.util.AbFileUtil.deleteFile;
import static com.jinqu.standardNew.frg.BaseFrg.setBgClickClor;

public class YsltWenjian extends SwipMoreView {
    public MImageView mMImageView;
    public TextView mTextView_name;
    public TextView mTextView_size;
    public Button mButton_delete;
    public LinearLayout mLinearLayout_delete;
    public LinearLayout mLinearLayout_content;
    public File item;
    public String from;

    public YsltWenjian(Context context) {
        super(context);
        initView();
    }

    private void initView() {
        LayoutInflater.from(getContext()).inflate(R.layout.item_yslt_wenjian, this);
        findVMethod();
        mLinearLayout_content.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                setBgClickClor(view);
                F.openFile(item, getContext());
            }
        });
        mButton_delete.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                setBgClickClor(view);
                deleteFile(item);
                Frame.HANDLES.sentAll(from, 0, null);
            }
        });
        reset();
    }


    public void set(File item, String from) {
        this.item = item;
        this.from = from;
        mTextView_name.setText(item.getName());
        mTextView_size.setText(FileSizeUtil.getAutoFileOrFilesSize(item));
        mMImageView.setObj("file://" + item.getAbsolutePath());
    }

    @Override
    public View moreView() {
        return mLinearLayout_delete;
    }

    @Override
    public boolean swipLeftAble() {
        return true;
    }

    @Override
    public boolean swipRightAble() {
        return false;
    }

    @Override
    public View swipView() {
        return mLinearLayout_content;
    }

    private void findVMethod() {
        mButton_delete = (Button) findViewById(R.id.mButton_delete);
        mLinearLayout_delete = (LinearLayout) findViewById(R.id.mLinearLayout_delete);
        mMImageView = (MImageView) findViewById(R.id.mMImageView);
        mTextView_name = (TextView) findViewById(R.id.mTextView_name);
        mTextView_size = (TextView) findViewById(R.id.mTextView_size);
        mLinearLayout_content = (LinearLayout) findViewById(R.id.mLinearLayout_content);
    }
}