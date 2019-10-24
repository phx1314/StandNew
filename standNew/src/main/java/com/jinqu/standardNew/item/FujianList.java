//
//  FujianList
//
//  Created by DELL on 2018-05-15 17:26:07
//  Copyright (c) DELL All rights reserved.


/**

 */

package com.jinqu.standardNew.item;

import android.content.Context;
import android.content.DialogInterface;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.framewidget.F;
import com.jinqu.standardNew.R;
import com.jinqu.standardNew.ada.AdaFujianList;
import com.jinqu.standardNew.frg.FrgFujianDetail;
import com.jinqu.standardNew.model.ModelFjList;
import com.jinqu.standardNew.util.UtilDate;
import com.jinqu.standardNew.util.UtilFile;
import com.mdx.framework.Frame;
import com.mdx.framework.activity.TitleAct;
import com.mdx.framework.commons.ParamsManager;
import com.mdx.framework.utility.Helper;
import com.mdx.framework.widget.SwipMoreView;

import java.net.URLDecoder;
import java.net.URLEncoder;

import static com.jinqu.standardNew.frg.BaseFrg.setBgClickClor;


public class FujianList extends SwipMoreView implements View.OnClickListener {
    public LinearLayout mLinearLayout_delete;
    public Button mButton_delete;
    public LinearLayout mLinearLayout_content;
    public TextView mTextView_title;
    public TextView mTextView_size;
    public TextView mTextView_content;
    public boolean canDelete;
    public ModelFjList.RowsBean item;
    public String refTable;
    public AdaFujianList mAdaFujianList;
    public int position;
    public com.mdx.framework.widget.MImageView mImageView;
    public LinearLayout mLinearLayout_content2;

    public FujianList(Context context) {
        super(context);
        initView();
    }

    private void initView() {
        LayoutInflater.from(getContext()).inflate(R.layout.item_fujian_list, this);
        findVMethod();
    }

    private void findVMethod() {
        mLinearLayout_delete = (LinearLayout) findViewById(R.id.mLinearLayout_delete);
        mButton_delete = (Button) findViewById(R.id.mButton_delete);
        mLinearLayout_content = (LinearLayout) findViewById(R.id.mLinearLayout_content);
        mTextView_title = (TextView) findViewById(R.id.mTextView_title);
        mTextView_size = (TextView) findViewById(R.id.mTextView_size);
        mTextView_content = (TextView) findViewById(R.id.mTextView_content);
        mImageView = (com.mdx.framework.widget.MImageView) findViewById(R.id.mImageView);
        mLinearLayout_content2 = (LinearLayout) findViewById(R.id.mLinearLayout_content2);

        mLinearLayout_content.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Helper.startActivity(getContext(), FrgFujianDetail.class, TitleAct.class, "data", item, "refTable", TextUtils.isEmpty(refTable) ? "0" : refTable);
            }
        });
        mButton_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setBgClickClor(view);
                F.yShoure(getContext(), "确定删除", "", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Frame.HANDLES.sentAll("FrgFujianListEdit", 103, item);
                        mAdaFujianList.remove(position);
                    }
                });
            }
        });
    }

    public void set(ModelFjList.RowsBean item, String refTable, AdaFujianList mAdaFujianList, int position, boolean canDelete) {
        this.mAdaFujianList = mAdaFujianList;
        this.canDelete = canDelete;
        this.item = item;
        this.refTable = refTable;
        this.position = position;

        mTextView_title.setText(item.Name);
        try {
            UtilDate.formatMatchDate(item.LastModifyDate, "yyyy-MM-dd");
            String dateChange = UtilDate.formatMatchDate(item.LastModifyDate, "yyyy-MM-dd");
            String dateUpload = UtilDate.formatMatchDate(item.UploadDate, "yyyy-MM-dd");
            mTextView_size.setText(UtilFile.FormetFileSize(item.Size));
            mTextView_content.setText("上传人：" + URLDecoder.decode(item.EmpName, "UTF-8") + "上传：" + dateUpload + "    修改：" + dateChange);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (item.file != null) {
            mImageView.setObj("file://" + item.file.getAbsolutePath());
        } else {
            mImageView.setObj(ParamsManager.get("image_star") + URLEncoder.encode(TextUtils.isEmpty(item.Dir) ? "" : item.Dir));
        }
        reset();
    }


    @Override
    public View moreView() {
        return mLinearLayout_delete;
    }

    @Override
    public boolean swipLeftAble() {
        return canDelete;
    }

    @Override
    public boolean swipRightAble() {
        return false;
    }

    @Override
    public View swipView() {
        return mLinearLayout_content;
    }

    @Override
    public void onClick(android.view.View v) {
    }

}