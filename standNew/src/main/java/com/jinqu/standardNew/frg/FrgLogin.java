//
//  FrgLogin
//
//  Created by DELL on 2018-05-04 13:48:52
//  Copyright (c) DELL All rights reserved.


/**

 */

package com.jinqu.standardNew.frg;

import android.os.Build;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ab.util.AbMd5;
import com.framewidget.ModelUsreInfo;
import com.framewidget.frg.FrgPubBianJi;
import com.google.gson.Gson;
import com.jinqu.standardNew.F;
import com.jinqu.standardNew.R;
import com.jinqu.standardNew.model.ModelRongYun;
import com.jinqu.standardNew.model.ModelUsreLogin;
import com.mdx.framework.activity.IndexAct;
import com.mdx.framework.activity.TitleAct;
import com.mdx.framework.commons.ParamsManager;
import com.mdx.framework.config.BaseConfig;
import com.mdx.framework.utility.Helper;

import java.util.HashSet;
import java.util.Set;

import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.TagAliasCallback;
import io.rong.imlib.RongIMClient;

import static com.framewidget.F.closeSoftKey;
import static com.framewidget.F.cookie;
import static com.jinqu.standardNew.F.METHOD_EDITINFO;
import static com.jinqu.standardNew.F.METHOD_GETTOKEN;
import static com.jinqu.standardNew.F.METHOD_UpdateEmpMEID;
import static com.jinqu.standardNew.F.getJson;
import static com.jinqu.standardNew.F.mModelUsreInfo;
import static com.jinqu.standardNew.F.mModelUsreLogin;
import static com.jinqu.standardNew.F.saveJson;


public class FrgLogin extends BaseFrg {

    public EditText mEditText_yhm;
    public EditText mEditText_mm;
    public ImageView mImageView_kan;
    public EditText mEditText_yahzengma;
    public TextView mImageView_dl;
    public boolean isChecked = true;
    public ImageView imageView;
    public ImageView mImageView_get;
    public LinearLayout mLinearLayout_set;

    @Override
    protected void create(Bundle savedInstanceState) {
        setContentView(R.layout.frg_login);
        initView();
        loaddata();

    }

    @Override
    public void disposeMsg(int type, Object obj) {
        switch (type) {
            case 201:
                com.jinqu.standardNew.F.saveJson("IP", obj.toString());
                try {
                    BaseConfig.setUri("http://" + getJson("IP"));
                    ParamsManager.put("image_star", "http://" + getJson("IP").substring(0, getJson("IP").lastIndexOf("/")) + "/GoldFile/");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
        }
    }

    private void initView() {
        findVMethod();
    }

    private void findVMethod() {
        mEditText_yhm = (EditText) findViewById(R.id.mEditText_yhm);
        mEditText_mm = (EditText) findViewById(R.id.mEditText_mm);
        mImageView_kan = (ImageView) findViewById(R.id.mImageView_kan);
        mEditText_yahzengma = (EditText) findViewById(R.id.mEditText_yahzengma);
        mImageView_dl = (TextView) findViewById(R.id.mImageView_dl);
        imageView = (ImageView) findViewById(R.id.imageView);
        mImageView_get = (ImageView) findViewById(R.id.mImageView_get);
        mLinearLayout_set = (LinearLayout) findViewById(R.id.mLinearLayout_set);

        mImageView_dl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(mEditText_yhm.getText().toString().trim())) {
                    Helper.toast("请输入用户名", getContext());
                    return;
                }
                if (TextUtils.isEmpty(mEditText_mm.getText().toString().trim())) {
                    Helper.toast("请输入密码", getContext());
                    return;
                }
                loadUrlPost(F.METHOD_LOGIN, "userName", mEditText_yhm.getText().toString().trim(), "passWord", mEditText_mm.getText().toString().trim());
            }
        });
        mImageView_kan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setBgClickClor(v);
                if (isChecked) {
                    // 显示密码
                    mEditText_mm.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    mImageView_kan.setImageResource(R.drawable.mima2);
                } else {
                    // 隐藏密码
                    mEditText_mm.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    mImageView_kan.setImageResource(R.drawable.mima1);
                }
                isChecked = !isChecked;
            }
        });
        mLinearLayout_set.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Helper.startActivity(getContext(), FrgPubBianJi.class,
                        TitleAct.class, "from", "FrgLogin", "EDT", 201,
                        "data", BaseConfig.getUri().split("://")[1], "hint", "设置ip");
                return true;
            }
        });
    }

    public void loaddata() {

    }

    @Override
    public void onSuccess(String methodName, String content) {
        if (methodName.equals(F.METHOD_LOGIN)) {
            loginHX(content);
        } else if (methodName.equals(METHOD_EDITINFO)) {
            mModelUsreInfo = new Gson().fromJson(content, ModelUsreInfo.class);
            saveJson("mModelUsreInfo", content);
            Helper.startActivity(getContext(), FrgHome.class, IndexAct.class);
            Helper.toast("登录成功！", getContext());
            JPushInterface.resumePush(getActivity());
            Set<String> s = new HashSet<String>();
            s.add(ParamsManager.get("JPush_Alias_BeginWith"));
            JPushInterface.setAliasAndTags(getActivity(),
                    ParamsManager.get("JPush_Alias_BeginWith") + mModelUsreLogin.UserInfo.EmpID,
                    s,
                    new TagAliasCallback() {
                        @Override
                        public void gotResult(int code, String s, Set<String> set) {
                            FrgLogin.this.finish();
                            switch (code) {
                                case 0:
                                    Log.i("JPush", "设置别名成功");
                                    break;
                                case 6002:
                                    Log.i("JPush", "失败,错误码= " + code);
                                    break;
                                default:
                                    Log.i("JPush", "失败,错误码= " + code);
                            }
                        }
                    });
        } else if (methodName.equals(METHOD_GETTOKEN)) {
            ModelRongYun modelRongYun = new Gson().fromJson(content, ModelRongYun.class);
            F.connect(modelRongYun.getToken(), getContext(), new RongIMClient.ConnectCallback() {
                @Override
                public void onTokenIncorrect() {

                }

                @Override
                public void onSuccess(String s) {
                    loadUrlPost(METHOD_EDITINFO, "id", mModelUsreLogin.UserInfo.EmpID);
                    closeSoftKey(getActivity());
                }

                @Override
                public void onError(RongIMClient.ErrorCode errorCode) {

                }
            });
        }
    }

    public void loginHX(final String content) {
        mModelUsreLogin = new Gson().fromJson(content, ModelUsreLogin.class);
        mModelUsreLogin.password = mEditText_mm.getText().toString().trim();
        mModelUsreLogin.name = mEditText_yhm.getText().toString().trim();
        cookie = "UID=" + mModelUsreLogin.UserInfo.EmpID + "; " + "ASP.NET_SessionId=" + mModelUsreLogin.SessionID + "; " + "AgentID=; expires=Fri, 20-May-1983 16:00:00 GMT; path=/";
        if (ParamsManager.get("isCheck").equals("1")) {
            if (TextUtils.isEmpty(mModelUsreLogin.UserInfo.EmpMEID)) {
                loadUrlPost(METHOD_UpdateEmpMEID + "?a=" + mModelUsreLogin.name + "&p=" + AbMd5.MD5(mModelUsreLogin.password) + "&EmpMEID=" + Build.SERIAL);
            } else {
                if (!Build.SERIAL.equals(mModelUsreLogin.UserInfo.EmpMEID)) {
                    Helper.startActivity(getContext(), FrgSjbd.class, TitleAct.class);
                    return;
                }
            }
        }
        saveJson("mModelUsreLogin", new Gson().toJson(mModelUsreLogin));
        loadUrlPost(METHOD_EDITINFO, "id", mModelUsreLogin.UserInfo.EmpID);
//        loadUrlGet(METHOD_GETTOKEN, "userId", ParamsManager.get("ChatUserStr") + mModelUsreLogin.UserInfo.EmpID, "name", mModelUsreLogin.UserInfo.EmpName, "portraitUri", "");
    }
}