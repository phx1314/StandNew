//
//  Son
//
//  Created by DELL on 2018-05-08 11:17:04
//  Copyright (c) DELL All rights reserved.


/**

 */

package com.jinqu.standardNew.item;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.framewidget.F;
import com.framewidget.view.CallBackOnly;
import com.jinqu.standardNew.R;
import com.jinqu.standardNew.frg.FrgEmailList;
import com.jinqu.standardNew.frg.FrgFlowList;
import com.jinqu.standardNew.frg.FrgRizhi;
import com.jinqu.standardNew.frg.FrgSendEmail;
import com.jinqu.standardNew.model.ModelGzList;
import com.jinqu.standardNew.model.ModelMenuConfig;
import com.mdx.framework.Frame;
import com.mdx.framework.activity.TitleAct;
import com.mdx.framework.utility.Helper;

import static com.framewidget.F.toPinYin;
import static com.jinqu.standardNew.F.font;
import static com.jinqu.standardNew.F.mHashMap;


public class Son extends BaseItem {
    public TextView mMImageView;
    public TextView mTextView_title;
    public LinearLayout mLinearLayout;
    public int type;
    public Dialog mDialog;
    public ModelGzList.RowsBean item;

    @SuppressLint("InflateParams")
    public static View getView(Context context, ViewGroup parent) {
        LayoutInflater flater = LayoutInflater.from(context);
        View convertView = flater.inflate(R.layout.item_son, null);
        convertView.setTag(new Son(convertView));
        return convertView;
    }

    public Son(View view) {
        this.contentview = view;
        this.context = contentview.getContext();
        initView();
    }

    private void initView() {
        this.contentview.setTag(this);
        findVMethod();
    }

    private void findVMethod() {
        mMImageView = (TextView) contentview.findViewById(R.id.mMImageView);
        mTextView_title = (TextView) contentview.findViewById(R.id.mTextView_title);
        mLinearLayout = (LinearLayout) findViewById(R.id.mLinearLayout);
        mMImageView.setTypeface(font);
        mLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (type == 0 && item.children != null) {
                    final View view = GzDialog.getView(context, null);
                    F.showCenterDialog(context, view, new CallBackOnly() {
                        @Override
                        public void goReturnDo(Dialog mDialog) {
                            ((GzDialog) view.getTag()).set(item, mDialog);
                        }
                    });
                } else {
                    go2Where();
                    if (mDialog != null)
                        mDialog.dismiss();
                }
            }
        });
    }

    public void set(ModelGzList.RowsBean item, int type, Dialog mDialog) {
        this.type = type;
        this.item = item;
        this.mDialog = mDialog;
        mTextView_title.setText(item.text);
        String aa = item.iconCls.split(" ")[1].replace("-", "_");
        mMImageView.setText(context.getResources().getString(context.getResources().getIdentifier(aa, "string", context.getPackageName())));
        GradientDrawable myGrad = (GradientDrawable) context.getResources().getDrawable(R.drawable.shape_bg_gz);
        myGrad.setColor(Color.parseColor(mHashMap.get(toPinYin(aa.split("_")[1].charAt(0)).charAt(0) + "")));
        mMImageView.setBackground(myGrad);

    }

    public void go2Where() {
        item.mModelMenuConfig = (ModelMenuConfig) com.jinqu.standardNew.F.json2Model(item.MenuMobileConfig, ModelMenuConfig.class);
        if (item.MenuNameEng.equals("MailReceive")) {//收件箱
            Helper.startActivity(context, FrgEmailList.class, TitleAct.class, "type", 1); return;
        }else if (item.MenuNameEng.equals("OaCheckList")) {
             return;
        }  else if (item.MenuNameEng.equals("MailSend")) {//发件箱
            Helper.startActivity(context, FrgEmailList.class, TitleAct.class, "type", 2); return;
        } else if (item.MenuNameEng.equals("MailDraft")) {//草稿箱
            Helper.startActivity(context, FrgEmailList.class, TitleAct.class, "type", 3); return;
        } else if (item.MenuNameEng.equals("MailJunk")) {//垃圾箱
            Helper.startActivity(context, FrgEmailList.class, TitleAct.class, "type", 4); return;
        } else if (item.MenuNameEng.equals("MailWrite")) {//写邮件
            Helper.startActivity(context, FrgSendEmail.class, TitleAct.class); return;
        }
//        else if (item.MenuNameEng.equals("ContractInfo") || item.MenuNameEng.equals("ContractSubInfo")) {//收款合同|付款合同
//            Helper.startActivity(context, FrgHtList.class, TitleAct.class, "item", item); return;
//        } else if (item.MenuNameEng.equals("OtherContractInfo") || item.MenuNameEng.equals("OtherContracSubtInfo")) {//收款合同|付款合同
//            Helper.startActivity(context, FrgHtList.class, TitleAct.class, "item", item); return;
//        }
//        else if (item.MenuNameEng.equals("OaLeave") || item.MenuNameEng.equals("OaGoing") || item.MenuNameEng.equals("OaWorking")) {//办公
//            Helper.startActivity(context, FrgGzList.class, TitleAct.class, "item", item);
//        } else if (item.MenuNameEng.equals("ProjInfoRegister")) {//项目信息登记
//            Helper.startActivity(context, FrgXmxxdjList.class, TitleAct.class, "item", item);
//        }
        else if (item.MenuNameEng.equals("OaNewsInfo")) {//新闻管理
            Frame.HANDLES.sentAll("FrgHome", 113, null); return;
        }
//        else if (item.MenuNameEng.equals("OaNoticeInfo")) {//系统公告
//            Helper.startActivity(context, FrgBaseList.class, TitleAct.class); return;
//        }
//        else if (item.MenuNameEng.equals("CustomerInfo") || item.MenuNameEng.equals("CustomerSubInfo") || item.MenuNameEng.equals("OtherCustomerInfo")) {//客户管理
//            Helper.startActivity(context, FrgKhgl.class, TitleAct.class, "item", item); return;
//        }
        else if (item.MenuNameEng.equals("LoginLog")) {//审计
            Helper.startActivity(context, FrgRizhi.class, TitleAct.class, "type", 0); return;
        } else if (item.MenuNameEng.equals("BUSSLog")) {//业务
            Helper.startActivity(context, FrgRizhi.class, TitleAct.class, "type", 1); return;
        } else if (item.MenuNameEng.equals("OaScheduler")) {//工作
            Frame.HANDLES.sentAll("FrgHome", 114, null); return;
        }
        try {
            Helper.startActivity(context, FrgFlowList.class, TitleAct.class, "item", item);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}