//
//  FrgGrzx
//
//  Created by DELL on 2018-06-20 10:23:40
//  Copyright (c) DELL All rights reserved.


/**

 */

package com.jinqu.standardNew.frg;

import android.Manifest;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.framewidget.ModelUsreInfo;
import com.framewidget.error.PopUpdataPhoto;
import com.framewidget.frg.FrgPubBianJi;
import com.google.gson.Gson;
import com.jinqu.standardNew.F;
import com.jinqu.standardNew.R;
import com.mdx.framework.Frame;
import com.mdx.framework.activity.TitleAct;
import com.mdx.framework.commons.ParamsManager;
import com.mdx.framework.config.BaseConfig;
import com.mdx.framework.utility.Helper;
import com.mdx.framework.utility.permissions.PermissionRequest;

import java.io.File;

import io.rong.imkit.RongIM;

import static com.jinqu.standardNew.F.METHOD_EDITINFO;
import static com.jinqu.standardNew.F.METHOD_UPDATE_USERINFO;
import static com.jinqu.standardNew.F.mModelUsreInfo;
import static com.jinqu.standardNew.F.reFreashQiTa;
import static com.jinqu.standardNew.F.saveJson;
import static com.jinqu.standardNew.F.setImage;


public class FrgGrzx extends BaseFrg {

    public ImageButton mImageButton_left;
    public ImageButton mImageButton_right;
    public TextView mTextView_name;
    public LinearLayout mLinearLayout_bm;
    public TextView mTextView_bm;
    public LinearLayout mLinearLayout_zc;
    public TextView mTextView_zc;
    public LinearLayout mLinearLayout_phone;
    public TextView mTextView_phone;
    public LinearLayout mLinearLayout_qm;
    public TextView mTextView_qm;
    public String id;
    public com.mdx.framework.widget.MImageView mMImageView;

    @Override
    protected void create(Bundle savedInstanceState) {
        setWindowStatusBarColor(Color.parseColor("#2D8FD6"));
        setDarkStatusIcon(getActivity(), false);
        id = getActivity().getIntent().getStringExtra("id");
        setContentView(R.layout.frg_grzx);
        initView();
        loaddata();
    }

    @Override
    public void disposeMsg(int type, Object obj) {
        switch (type) {
            case 101:
                loadUrlPost(METHOD_UPDATE_USERINFO, "EmpNote", mTextView_qm.getText().toString().trim(), "EmpTel", obj.toString(), "EmpTitle", mTextView_zc.getText().toString().trim());
                break;
            case 102:
                loadUrlPost(METHOD_UPDATE_USERINFO, "EmpNote", obj.toString(), "EmpTel", mTextView_phone.getText().toString().trim(), "EmpTitle", mTextView_zc.getText().toString().trim());
                break;
        }
    }

    private void initView() {
        findVMethod();
    }

    private void findVMethod() {
        mImageButton_left = (ImageButton) findViewById(R.id.mImageButton_left);
        mImageButton_right = (ImageButton) findViewById(R.id.mImageButton_right);
        mTextView_name = (TextView) findViewById(R.id.mTextView_name);
        mLinearLayout_bm = (LinearLayout) findViewById(R.id.mLinearLayout_bm);
        mTextView_bm = (TextView) findViewById(R.id.mTextView_bm);
        mLinearLayout_zc = (LinearLayout) findViewById(R.id.mLinearLayout_zc);
        mTextView_zc = (TextView) findViewById(R.id.mTextView_zc);
        mLinearLayout_phone = (LinearLayout) findViewById(R.id.mLinearLayout_phone);
        mTextView_phone = (TextView) findViewById(R.id.mTextView_phone);
        mLinearLayout_qm = (LinearLayout) findViewById(R.id.mLinearLayout_qm);
        mTextView_qm = (TextView) findViewById(R.id.mTextView_qm);
        mMImageView = (com.mdx.framework.widget.MImageView) findViewById(R.id.mMImageView);
        mImageButton_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RongIM.getInstance().startPrivateChat(
                        getContext(),
                        ParamsManager.get("ChatUserStr") + mModelUsreInfo.model.EmpID,
                        mModelUsreInfo.model.EmpName);
            }
        });
        mImageButton_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FrgGrzx.this.finish();
            }
        });
        mLinearLayout_phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setBgClickClor(view);
                if (mModelUsreInfo.model.EmpID != F.mModelUsreLogin.UserInfo.EmpID) {
                    Helper.requestPermissions(new String[]{"android.permission.CALL_PHONE", Manifest.permission.WRITE_EXTERNAL_STORAGE}, new PermissionRequest() {
                        public void onGrant(String[] permissions, int[] grantResults) {
                            Intent intent = new Intent();
                            intent.setAction(Intent.ACTION_CALL);
                            Uri data = Uri.parse("tel:" + mTextView_phone.getText().toString());
                            intent.setData(data);
                            startActivity(intent);
                        }
                    });
                    return;
                }
                if (!TextUtils.isEmpty(mTextView_phone.getText().toString())) {
                    return;
                }
                Helper.startActivity(getContext(), FrgPubBianJi.class,
                        TitleAct.class, "from", "FrgGrzx", "EDT", 101,
                        "data", mTextView_phone.getText().toString(), "max", 11, "hint", "手机号");
            }
        });
        mTextView_qm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setBgClickClor(view);
                if (mModelUsreInfo.model.EmpID != F.mModelUsreLogin.UserInfo.EmpID) {
                    return;
                }
                Helper.startActivity(getContext(), FrgPubBianJi.class,
                        TitleAct.class, "from", "FrgGrzx", "EDT", 102,
                        "data", mTextView_qm.getText().toString(), "max", 150, "hint", "个性签名");
            }
        });
        mMImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setBgClickClor(view);
                if (mModelUsreInfo.model.EmpID != F.mModelUsreLogin.UserInfo.EmpID) {
                    return;
                }
                Helper.getPhotos();
                com.framewidget.F.getPhoto(getActivity(), new PopUpdataPhoto.OnReceiverPhoto() {
                    @Override
                    public void onReceiverPhoto(String photoPath, int width,
                                                int height) {
                        if (photoPath != null) {
                            mMImageView.setObj("file:" + photoPath);
                            mMImageView.setCircle(true);
                            loadUrlPost(BaseConfig.getUri() + "/core/user/ChangeAvatar1", "", new File(photoPath));
                        }
                    }
                }, 10, 10, 640, 640);
            }
        });
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mImageButton_left.getDrawable().setTint(Color.parseColor("#ffffff"));
            mImageButton_right.getDrawable().setTint(Color.parseColor("#ffffff"));
        }
    }

    public void loaddata() {
        loadUrlPost(METHOD_EDITINFO, "id", id);
    }

    @Override
    public void onSuccess(String methodName, String content) {
        if (methodName.equals(METHOD_EDITINFO)) {
            ModelUsreInfo mModelUsreInfo = new Gson().fromJson(content, ModelUsreInfo.class);
            if (id.equals(F.mModelUsreInfo.model.EmpID + "")) {
                F.mModelUsreInfo = new Gson().fromJson(content, ModelUsreInfo.class);
                saveJson("mModelUsreInfo", content);
                reFreashQiTa(mModelUsreInfo);
                Frame.HANDLES.sentAll("FrgWd,FrgTxl", 200, null);
            } else {
//                mImageButton_right.setVisibility(View.VISIBLE);
            }
            if (!TextUtils.isEmpty(mModelUsreInfo.model.EmpHead)) {
                mMImageView.setObj(mModelUsreInfo.model.EmpHead);
            } else {
                setImage(mMImageView, mModelUsreInfo.model.EmpName.substring(0, 1));
            }
            mMImageView.setCircle(true);
            mTextView_name.setText(mModelUsreInfo.model.EmpName);
            mTextView_bm.setText(mModelUsreInfo.model.EmpDepName);
            mTextView_zc.setText(mModelUsreInfo.model.EmpTitle);
            mTextView_phone.setText(mModelUsreInfo.model.EmpTel);
            mTextView_qm.setText(mModelUsreInfo.model.EmpNote);
        } else if (methodName.equals(BaseConfig.getUri() + "/core/user/ChangeAvatar1")) {
            loadUrlPost(METHOD_EDITINFO, "id", id);
            Helper.toast("上传头像成功", getContext());
        } else if (methodName.equals(METHOD_UPDATE_USERINFO)) {
            loadUrlPost(METHOD_EDITINFO, "id", id);
        }
    }
}