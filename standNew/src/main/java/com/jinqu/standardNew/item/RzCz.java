//
//  RzCz
//
//  Created by DELL on 2017-08-28 14:31:16
//  Copyright (c) DELL All rights reserved.


/**

 */

package com.jinqu.standardNew.item;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.text.ClipboardManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.jinqu.standardNew.R;
import com.jinqu.standardNew.model.ModelRzh;
import com.mdx.framework.activity.MActivityActionbar;
import com.mdx.framework.utility.Helper;



public class RzCz extends BaseItem {
    public Button btn_1;
    public LinearLayout mLinearLayout_bottom;
    public Button btn_2;
    public Dialog item;
    public ModelRzh.RowsBean mRowsBean;
    public int type;

    @SuppressLint("InflateParams")
    public static View getView(Context context, ViewGroup parent) {
        LayoutInflater flater = LayoutInflater.from(context);
        View convertView = flater.inflate(R.layout.item_rz_cz, null);
        convertView.setTag(new RzCz(convertView));
        return convertView;
    }

    public RzCz(View view) {
        this.contentview = view;
        this.context = contentview.getContext();
        initView();
    }

    private void initView() {
        this.contentview.setTag(this);
        findVMethod();
    }

    private void findVMethod() {
        btn_1 = (Button) contentview.findViewById(R.id.btn_1);
        mLinearLayout_bottom = (LinearLayout) contentview.findViewById(R.id.mLinearLayout_bottom);
        btn_2 = (Button) contentview.findViewById(R.id.btn_2);

        btn_1.setOnClickListener(com.mdx.framework.utility.Helper.delayClickLitener(this));
        btn_2.setOnClickListener(com.mdx.framework.utility.Helper.delayClickLitener(this));

    }

    public void set(Dialog item, ModelRzh.RowsBean mRowsBean, int type) {
        this.item = item;
        this.mRowsBean = mRowsBean;
        this.type = type;
        if (type == 0) {
            mLinearLayout_bottom.setVisibility(View.GONE);
        }
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        String txt = "";
        if (R.id.btn_1 == v.getId()) {
            txt = mRowsBean.BaseLogIP;
        } else if (R.id.btn_2 == v.getId()) {
            txt = mRowsBean.BaseLogRefHTML;
        }
        ClipboardManager clip = (ClipboardManager) ((MActivityActionbar) context)
                .getSystemService(Context.CLIPBOARD_SERVICE);
        clip.setText(txt); // 复制
        item.dismiss();
        Helper.toast("复制成功", context);
    }

}