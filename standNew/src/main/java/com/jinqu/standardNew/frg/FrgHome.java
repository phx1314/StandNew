//
//  FrgHome
//
//  Created by DELL on 2018-05-03 08:33:37
//  Copyright (c) DELL All rights reserved.


/**

 */

package com.jinqu.standardNew.frg;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.StateListDrawable;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.widget.LinearLayout;

import com.framewidget.ModelUsreInfo;
import com.framewidget.frg.FrgPtDetail;
import com.framewidget.newMenu.SlidingFragment;
import com.framewidget.view.CallBackPt;
import com.google.gson.Gson;
import com.jinqu.standardNew.F;
import com.jinqu.standardNew.R;
import com.jinqu.standardNew.model.ModelAA;
import com.jinqu.standardNew.model.ModelEmploee;
import com.jinqu.standardNew.model.ModelGzList;
import com.jinqu.standardNew.model.ModelMenuConfig;
import com.jinqu.standardNew.model.ModelQunList;
import com.jinqu.standardNew.model.ModelQunZiLiao;
import com.jinqu.standardNew.service.LocService;
import com.jinqu.standardNew.service.ScreenBroadcastReceiver;
import com.jinqu.standardNew.service.TxService;
import com.mdx.framework.Frame;
import com.mdx.framework.activity.IndexAct;
import com.mdx.framework.activity.NoTitleAct;
import com.mdx.framework.commons.ParamsManager;
import com.mdx.framework.dialog.PhotoShow;
import com.mdx.framework.utility.Helper;
import com.pgyersdk.javabean.AppBean;
import com.pgyersdk.update.PgyUpdateManager;
import com.pgyersdk.update.UpdateManagerListener;

import java.util.ArrayList;
import java.util.List;

import cn.jpush.android.api.JPushInterface;
import io.rong.app.callback.CallBackOnly;
import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Conversation;
import io.rong.imlib.model.Message;
import io.rong.message.ContactNotificationMessage;
import io.rong.message.RichContentMessage;

import static com.jinqu.standardNew.F.METHOD_ALLLIST;
import static com.jinqu.standardNew.F.METHOD_BASEGROUPPAGELIST;
import static com.jinqu.standardNew.F.METHOD_GROUPINFO;
import static com.jinqu.standardNew.F.METHOD_GetAmount;
import static com.jinqu.standardNew.F.METHOD_mobilemenujson;
import static com.jinqu.standardNew.F.changeColor;
import static com.jinqu.standardNew.F.json2Model;
import static com.jinqu.standardNew.F.reFreashQiTa;
import static com.jinqu.standardNew.F.reFreashQiTaQun;
import static com.jinqu.standardNew.F.stringToBitmap;
import static io.rong.app.RongCloudEvent.rid2GroupId;
import static io.rong.app.RongCloudEvent.rid2UserId;

//https://blog.csdn.net/jhl122/article/details/53433594  推送方案
public class FrgHome extends BaseFrg {
    public ScreenBroadcastReceiver mScreenBroadcastReceiver;
    public LinearLayout mLinearLayout_content;
    public SlidingFragment mSlidingFragment;
    public android.support.v4.app.FragmentManager fragmentManager;
    public StateListDrawable backgroundDrawable1;
    public StateListDrawable backgroundDrawable2;
    public StateListDrawable backgroundDrawable3;
    public StateListDrawable backgroundDrawable4;
    public StateListDrawable backgroundDrawable5;
    public int count, count2, count3;
    public static List<ModelEmploee.RowsBean> mBaseEmployeeBeans = new ArrayList<ModelEmploee.RowsBean>();
    public static List<ModelGzList.RowsBean> mModelGzLists = new ArrayList<>();

    @Override
    protected void create(Bundle savedInstanceState) {
        setContentView(R.layout.frg_home);
        startScreenBroadcastReceiver();
        getActivity().startService(
                new Intent(getContext(), LocService.class));
        getActivity().startService(
                new Intent(getContext(), TxService.class));
        initView();
        loaddata();

        PgyUpdateManager.register(getActivity(),
                new UpdateManagerListener() {
                    @Override
                    public void onUpdateAvailable(final String result) {
                        try { // 将新版本信息封装到AppBean中
                            final AppBean appBean = getAppBeanFromString(result);
                            new AlertDialog.Builder(getContext())
                                    .setTitle("版本更新")
                                    .setMessage("检查到新版本，是否更新")
                                    .setNegativeButton(
                                            "确定",
                                            new DialogInterface.OnClickListener() {

                                                @Override
                                                public void onClick(
                                                        DialogInterface dialog,
                                                        int which) {
                                                    startDownloadTask(
                                                            getActivity(),
                                                            appBean.getDownloadURL());
                                                }
                                            }).show();

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onNoUpdateAvailable() {
                    }
                });
    }

    @Override
    public void disposeMsg(int type, Object obj) {
        switch (type) {
            case 10086:
                setBgColor();
                mSlidingFragment.replaceResDrawable(0, backgroundDrawable1);
                mSlidingFragment.replaceResDrawable(1, backgroundDrawable2);
                mSlidingFragment.replaceResDrawable(2, backgroundDrawable3);
                mSlidingFragment.replaceResDrawable(3, backgroundDrawable4);
                mSlidingFragment.replaceResDrawable(4, backgroundDrawable5);
                mSlidingFragment.reFreashTcolor();
                break;
            case 3:
                Helper.toast("异地登录", getContext());
                F.clearJson();
                JPushInterface.stopPush(getContext());
                RongIM.getInstance().logout();
                Helper.startActivity(getContext(), Intent.FLAG_ACTIVITY_CLEAR_TOP,
                        FrgLogin.class, IndexAct.class);
                break;
            case 4:
                refreshCount();
                if ((count + count2 + count3) > 0) {
                    mSlidingFragment.setIsShow(true, 0);
                } else {
                    mSlidingFragment.setIsShow(false, 0);
                }
                Frame.HANDLES.sentAll("FrgGoutong", 4, null);
                break;
            case 5:
                count3 = Integer.valueOf(obj.toString());
                refreshCount();
                if ((count + count2 + count3) > 0) {
                    mSlidingFragment.setIsShow(true, 0);
                } else {
                    mSlidingFragment.setIsShow(false, 0);
                }
                break;
            case 6:
                loadUrlPostNoShow(METHOD_GetAmount, "app", "1");
                break;
            case 0:
                mSlidingFragment.setIsShow((Boolean) obj, 1);
                break;
            case 110:
                loadUrlPostNoShow("core/user/editInfo", "id", rid2UserId(obj.toString()));
                break;
            case 120:
                loadUrlGet(METHOD_GROUPINFO + rid2GroupId(obj.toString()));
                break;
            case 121:
                loadUrlPost(METHOD_BASEGROUPPAGELIST, "page", "1", "rows", Integer.MAX_VALUE);
                break;
            case 113:
                mSlidingFragment.goWhere(3);
                break;
            case 114:
                mSlidingFragment.goWhere(1);
                Frame.HANDLES.sentAll("FrgFx,FrgRc", 1, null);
                break;
            case 115:
                mSlidingFragment.setIsShow((Boolean) obj, 4);
                break;
        }
    }

    private void initView() {
        ActivityCompat.requestPermissions(getActivity(),
                new String[]{Manifest.permission.CHANGE_WIFI_STATE, Manifest.permission.CAMERA, Manifest.permission.ACCESS_WIFI_STATE, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE},
                1);
        findVMethod();
    }

    @Override
    public void onSuccess(String methodName, String content) {
        super.onSuccess(methodName, content);
        if (methodName.equals("core/user/editInfo")) {
            ModelUsreInfo mModelUsreInfo = new Gson().fromJson(content, ModelUsreInfo.class);
            reFreashQiTa(mModelUsreInfo);
        } else if (methodName.equals(METHOD_BASEGROUPPAGELIST)) {
            ModelQunList mModelSystemList = new Gson().fromJson(content, ModelQunList.class);
            if (RongIMClient.getInstance().getConversationList(Conversation.ConversationType.GROUP) != null)
                for (Conversation mConversation : RongIMClient.getInstance().getConversationList(Conversation.ConversationType.GROUP)) {
                    boolean isGoon = true;
                    for (ModelQunList.RowsBean item : mModelSystemList.rows) {
                        if ((ParamsManager.get("ChatStr") + item.GroupID).equals(mConversation.getTargetId())) {
                            isGoon = false;
                            break;
                        }
                    }
                    if (isGoon) {
                        RongIM.getInstance()
                                .getRongIMClient()
                                .removeConversation(Conversation.ConversationType.GROUP,
                                        mConversation.getTargetId());
                    }
                }
        } else if (methodName.equals(METHOD_ALLLIST)) {
            ModelEmploee mModelSystemList = new Gson().fromJson(content, ModelEmploee.class);
            mBaseEmployeeBeans = mModelSystemList.rows;
        } else if (methodName.equals(METHOD_mobilemenujson)) {
            ModelGzList mModelGzList = new Gson().fromJson(content, ModelGzList.class);
            addSon(mModelGzList.rows);
            Frame.HANDLES.sentAll("FrgBd", 10086, 0);
        } else if (methodName.equals(METHOD_GetAmount)) {
            ModelAA mModelCount = (ModelAA) json2Model(content, ModelAA.class);
            if (mModelCount.OAAmount + mModelCount.ProjectAmount   > 0) {
                mSlidingFragment.setIsShow(true, 1);
                mSlidingFragment.replaceResDianCounts(1, (mModelCount.OAAmount + mModelCount.ProjectAmount ) + "");
            } else {
                mSlidingFragment.setIsShow(false, 1);
            }
        } else {
            ModelQunZiLiao mModelQunZiLiao = (ModelQunZiLiao) json2Model(content, ModelQunZiLiao.class);
            reFreashQiTaQun(mModelQunZiLiao);
        }
    }

    private void addSon(List<ModelGzList.RowsBean> rows) {
        for (ModelGzList.RowsBean item : rows) {
            if (!item.MenuMobileConfig.equals("{}")) {
                item.mModelMenuConfig = (ModelMenuConfig) com.jinqu.standardNew.F.json2Model(item.MenuMobileConfig, ModelMenuConfig.class);
                mModelGzLists.add(item);
            }
            if (item.children != null)
                addSon(item.children);
        }

    }

    private void findVMethod() {
        mLinearLayout_content = (LinearLayout) findViewById(R.id.mLinearLayout_content);
        com.framewidget.F.mCallBackPt = new CallBackPt() {
            @Override
            public void Click(Context context, String url) {
                PhotoShow mPhotoShow = new PhotoShow(context, stringToBitmap(url));
                mPhotoShow.show();
            }

        };
        io.rong.app.F.mCallBackOnly = new CallBackOnly() {

            @Override
            public void goReturnDo(Object obj) {
            }


            @Override
            public void go2FileDetail(Object obj) {
            }

            @Override
            public void go2NewFriendClass(Object obj) {
            }

            @Override
            public void go2geRenDetail(Object obj) {
                Helper.startActivity(getContext(), FrgGrzx.class, NoTitleAct.class, "id", rid2UserId(obj.toString()));
            }

            @Override
            public void go2AddressChoose(Object obj) {
                Helper.startActivity(getContext(), FrgWmChooseAddress.class, NoTitleAct.class, "from", obj.toString());
            }

            @Override
            public void go2PubDetail(Object obj) {
                RichContentMessage mRichContentMessage = (RichContentMessage) obj;
                Helper.startActivity(getContext(), FrgPtDetail.class,
                        NoTitleAct.class, "title", "详情", "url",
                        mRichContentMessage.getUrl());
            }

            @Override
            public void go2delaydel(Object obj) {
                final Message message = (Message) obj;
                final ContactNotificationMessage contactContentMessage = (ContactNotificationMessage) message
                        .getContent();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        RongIM.getInstance()
                                .getRongIMClient()
                                .removeConversation(
                                        message.getConversationType(),
                                        message.getTargetId());
                        RongIM.getInstance()
                                .getRongIMClient()
                                .removeConversation(
                                        Conversation.ConversationType.PRIVATE,
                                        contactContentMessage.getSourceUserId());
                    }
                }).start();
            }

        };
    }

    public void refreshCount() {
//        try {
//            count = RongIM.getInstance().getRongIMClient()
//                    .getUnreadCount(Conversation.ConversationType.PRIVATE);
//            count2 = RongIM.getInstance().getRongIMClient()
//                    .getUnreadCount(Conversation.ConversationType.GROUP);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

    public void loaddata() {
        loadUrlPostNoShow(METHOD_GetAmount, "app", "1");
        loadUrlPost(METHOD_ALLLIST, "page", "1", "rows", Integer.MAX_VALUE);
        loadUrlPostNoShow(METHOD_mobilemenujson);
        mSlidingFragment = new SlidingFragment(this);
        fragmentManager = getActivity().getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager
                .beginTransaction();
        fragmentTransaction.add(R.id.mLinearLayout_content, mSlidingFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
        setBgColor();
        refreshCount();
        if ((count + count2) > 0) {
            mSlidingFragment.addContentView(new FrgXx(), "消息",
                    backgroundDrawable1, "");
        } else {
            mSlidingFragment.addContentView(new FrgXx(), "消息",
                    backgroundDrawable1);
        }
        mSlidingFragment.addContentView(new FrgFx(), "发现",
                backgroundDrawable2);
        mSlidingFragment.addContentView(new FrgGz(), "工作",
                backgroundDrawable3);
        mSlidingFragment.addContentView(new FrgXw(), "新闻",
                backgroundDrawable4);
        mSlidingFragment.addContentView(new FrgWd(), "我的",
                backgroundDrawable5);
        mSlidingFragment.setFadeDegree(0.5f);
        mSlidingFragment.setOffscreenPageLimit(5);


    }


    public void setBgColor() {
        backgroundDrawable1 = (StateListDrawable) getResources().getDrawable(R.drawable.btn_checked_1);
        changeColor(backgroundDrawable1);
        backgroundDrawable2 = (StateListDrawable) getResources().getDrawable(R.drawable.btn_checked_2);
        changeColor(backgroundDrawable2);
        backgroundDrawable3 = (StateListDrawable) getResources().getDrawable(R.drawable.btn_checked_3);
        changeColor(backgroundDrawable3);
        backgroundDrawable4 = (StateListDrawable) getResources().getDrawable(R.drawable.btn_checked_4);
        changeColor(backgroundDrawable4);
        backgroundDrawable5 = (StateListDrawable) getResources().getDrawable(R.drawable.btn_checked_5);
        changeColor(backgroundDrawable5);

    }

    private void startScreenBroadcastReceiver() {
        mScreenBroadcastReceiver = new ScreenBroadcastReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_SCREEN_ON);
        filter.addAction(Intent.ACTION_SCREEN_OFF);
        filter.addAction(Intent.ACTION_USER_PRESENT);
        getActivity().registerReceiver(mScreenBroadcastReceiver, filter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        PgyUpdateManager.unregister();
        getActivity().unregisterReceiver(mScreenBroadcastReceiver);
    }


}