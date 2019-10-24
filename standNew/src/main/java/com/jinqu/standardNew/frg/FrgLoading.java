//
//  FrgLoading
//
//  Created by DELL on 2017-03-24 08:35:19
//  Copyright (c) DELL All rights reserved.


/**

 */

package com.jinqu.standardNew.frg;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.LinearLayout;

import com.google.gson.Gson;
import com.jinqu.standardNew.F;
import com.jinqu.standardNew.R;
import com.jinqu.standardNew.model.ModelRongYun;
import com.mdx.framework.activity.IndexAct;
import com.mdx.framework.commons.ParamsManager;
import com.mdx.framework.utility.Helper;

import java.util.HashSet;
import java.util.Set;

import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.TagAliasCallback;
import io.rong.imlib.RongIMClient;

import static com.jinqu.standardNew.F.METHOD_GETTOKEN;
import static com.jinqu.standardNew.F.mModelUsreInfo;
import static com.jinqu.standardNew.F.mModelUsreLogin;
import static com.jinqu.standardNew.F.reFreashQiTa;


public class FrgLoading extends BaseFrg {


    public com.mdx.framework.widget.MImageView mImageView;
    public LinearLayout activity_main;

    @Override
    protected void create(Bundle savedInstanceState) {
        setContentView(R.layout.frg_loading);
        initView();
        loaddata();
    }

    private void loaddata() {
        if (F.isFirstInstall()) {
            Helper.startActivity(getContext(), FrgYindao.class, IndexAct.class);
            FrgLoading.this.finish();
        } else {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (mModelUsreLogin == null) {
                        Helper.startActivity(getContext(), FrgLogin.class, IndexAct.class);
                        FrgLoading.this.finish();
                    } else {
                        Helper.startActivity(getContext(), FrgHome.class, IndexAct.class);
                        JPushInterface.resumePush(getActivity());
                        Set<String> s = new HashSet<String>();
                        s.add(ParamsManager.get("JPush_Alias_BeginWith"));
                        JPushInterface.setAliasAndTags(getActivity(),
                                ParamsManager.get("JPush_Alias_BeginWith") + mModelUsreLogin.UserInfo.EmpID,
                                s,
                                new TagAliasCallback() {
                                    @Override
                                    public void gotResult(int code, String s, Set<String> set) {
                                        FrgLoading.this.finish();
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

//                        loadUrlGetNoshow(METHOD_GETTOKEN, "userId", ParamsManager.get("ChatUserStr") + F.mModelUsreLogin.UserInfo.EmpID, "name", F.mModelUsreLogin.UserInfo.EmpName, "portraitUri", "");
                    }
                }
            }, 2000);
        }
    }

    @Override
    public void onSuccess(String methodName, String content) {
        if (methodName.equals(METHOD_GETTOKEN)) {
            ModelRongYun modelRongYun = new Gson().fromJson(content, ModelRongYun.class);
            F.connect(modelRongYun.getToken(), getContext(), new RongIMClient.ConnectCallback() {
                @Override
                public void onTokenIncorrect() {

                }

                @Override
                public void onSuccess(String str) {
                    Helper.startActivity(getContext(), FrgHome.class, IndexAct.class);
                    reFreashQiTa(mModelUsreInfo);
                    JPushInterface.resumePush(getActivity());
                    Set<String> s = new HashSet<String>();
                    s.add(ParamsManager.get("JPush_Alias_BeginWith"));
                    JPushInterface.setAliasAndTags(getActivity(),
                            ParamsManager.get("JPush_Alias_BeginWith") + mModelUsreLogin.UserInfo.EmpID,
                            s,
                            new TagAliasCallback() {
                                @Override
                                public void gotResult(int code, String s, Set<String> set) {
                                    FrgLoading.this.finish();
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
                }

                @Override
                public void onError(RongIMClient.ErrorCode errorCode) {

                }
            });
        }
    }

    private void initView() {
        findVMethod();
    }

    private void findVMethod() {
        mImageView = (com.mdx.framework.widget.MImageView) findViewById(R.id.mImageView);
        activity_main = (LinearLayout) findViewById(R.id.activity_main);

    }


}