//
//  YoujiancaozuoPop
//
//  Created by DELL on 2017-04-17 14:49:07
//  Copyright (c) DELL All rights reserved.


/**

 */

package com.jinqu.standardNew.pop;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.ab.http.HttpUtil;
import com.jinqu.standardNew.R;
import com.jinqu.standardNew.frg.FrgSendEmail;
import com.jinqu.standardNew.item.BaseItem;
import com.jinqu.standardNew.model.ModelYjDetail;
import com.mdx.framework.Frame;
import com.mdx.framework.activity.TitleAct;
import com.mdx.framework.utility.Helper;

import static com.jinqu.standardNew.F.METHOD_OAHUIFU;
import static com.jinqu.standardNew.F.METHOD_OAHUIFU_READ;
import static com.jinqu.standardNew.F.METHOD_OAMAILDEL;
import static com.jinqu.standardNew.F.METHOD_OAMAILDELCG;


public class YoujiancaozuoPop extends BaseItem {
    public Button btn_1;
    public Button btn_2;
    public Button btn_3;
    public Button btn_4;
    public Button btn_canel;
    public Dialog item;
    public int type;
    public String id;
    public ModelYjDetail mModelYjDetail;

    @SuppressLint("InflateParams")
    public static View getView(Context context, ViewGroup parent) {
        LayoutInflater flater = LayoutInflater.from(context);
        View convertView = flater.inflate(R.layout.item_youjiancaozuo_pop, null);
        convertView.setTag(new YoujiancaozuoPop(convertView));
        return convertView;
    }

    public YoujiancaozuoPop(View view) {
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
        btn_2 = (Button) contentview.findViewById(R.id.btn_2);
        btn_3 = (Button) contentview.findViewById(R.id.btn_3);
        btn_4 = (Button) contentview.findViewById(R.id.btn_4);
        btn_canel = (Button) contentview.findViewById(R.id.btn_canel);

        btn_1.setOnClickListener(com.mdx.framework.utility.Helper.delayClickLitener(this));
        btn_2.setOnClickListener(com.mdx.framework.utility.Helper.delayClickLitener(this));
        btn_3.setOnClickListener(com.mdx.framework.utility.Helper.delayClickLitener(this));
        btn_4.setOnClickListener(com.mdx.framework.utility.Helper.delayClickLitener(this));
        btn_canel.setOnClickListener(com.mdx.framework.utility.Helper.delayClickLitener(this));

    }

    public void set(String id, Dialog item, int type, ModelYjDetail mModelYjDetail) {
        this.item = item;
        this.type = type;
        this.id = id;
        this.mModelYjDetail = mModelYjDetail;
        switch (type) {
            case 1:
                break;
            case 2:
                btn_1.setVisibility(View.GONE);
                break;
            case 3:
                btn_1.setVisibility(View.GONE);
                break;
            case 4:
                btn_1.setVisibility(View.GONE);
                btn_3.setText("恢复");
                break;
        }
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        item.dismiss();
        if (R.id.btn_1 == v.getId()) {
            Helper.startActivity(context, FrgSendEmail.class, TitleAct.class, "id", id, "name", mModelYjDetail.model.CreatorEmpName);
        } else if (R.id.btn_2 == v.getId()) {
            Helper.startActivity(context, FrgSendEmail.class, TitleAct.class, "id", id);
        } else if (R.id.btn_3 == v.getId()) {
            if (btn_3.getText().toString().equals("删除")) {
                deleteOmail(id, "false");
            } else {
                HttpUtil.loadUrlPost(context, this, METHOD_OAHUIFU, "id", id, "IsResum", "true");
                HttpUtil.loadUrlPost(context, this, METHOD_OAHUIFU_READ, "id", id, "IsResum", "true");
            }
        } else if (R.id.btn_4 == v.getId()) {
            deleteOmail(id, "true");
        } else if (R.id.btn_canel == v.getId()) {

        }
    }

    @Override
    public void onSuccess(String methodName, String content) {
        if (methodName.equals(METHOD_OAMAILDEL) || methodName.equals(METHOD_OAMAILDELCG) || methodName.equals(METHOD_OAHUIFU)) {
            Helper.toast("操作成功", context);
            Frame.HANDLES.sentAll("FrgEmailDetail", 0, null);
        }
    }

    public void deleteOmail(String id, String ischedi) {
        if (this.type == 1 || this.type == 4) {
            HttpUtil.loadUrlPost(context, this, METHOD_OAMAILDEL, "ids", id, "DelType", ischedi);
        } else {
            HttpUtil.loadUrlPost(context, this, METHOD_OAMAILDELCG, "ids", id, "DelType", ischedi);
        }
    }
}