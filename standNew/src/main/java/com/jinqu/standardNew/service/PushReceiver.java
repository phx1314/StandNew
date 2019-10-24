package com.jinqu.standardNew.service;

import android.app.ActivityManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.jinqu.standardNew.F;
import com.jinqu.standardNew.R;
import com.jinqu.standardNew.frg.FrgHome;
import com.jinqu.standardNew.frg.FrgPubWeb;
import com.jinqu.standardNew.model.ModelBd;
import com.jinqu.standardNew.model.ModelMenuConfig;
import com.jinqu.standardNew.model.ModelPush;
import com.mdx.framework.Frame;
import com.mdx.framework.activity.IndexAct;
import com.mdx.framework.activity.TitleAct;
import com.mdx.framework.utility.Helper;

import java.util.List;

import cn.jpush.android.api.JPushInterface;

import static com.jinqu.standardNew.F.getData;
import static com.jinqu.standardNew.F.json2Model;
import static com.jinqu.standardNew.F.refTable_OaMail;
import static com.jinqu.standardNew.frg.FrgHome.mModelGzLists;

public class PushReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();

        if (JPushInterface.ACTION_REGISTRATION_ID.equals(intent.getAction())) {

        } else if (JPushInterface.ACTION_MESSAGE_RECEIVED.equals(intent
                .getAction())) {
            String extra = bundle.getString(JPushInterface.EXTRA_EXTRA);
            String content = bundle.getString(JPushInterface.EXTRA_MESSAGE);
            ModelPush mModelPush = (ModelPush) json2Model(extra, ModelPush.class);
            if (mModelPush.RefTable.equals("CreateGroup") || mModelPush.RefTable.equals("EidtGroup") || mModelPush.RefTable.equals("DelGroup")) {
                Frame.HANDLES.sentAll("FrgQzList", 0, null);
            }
            // 自定义消息不会展示在通知栏，完全要开发者写代码去处理
        } else if (JPushInterface.ACTION_NOTIFICATION_RECEIVED.equals(intent
                .getAction())) {
            String extra = bundle.getString(JPushInterface.EXTRA_EXTRA);
            String content = bundle.getString(JPushInterface.EXTRA_MESSAGE);
            ModelPush mModelPush = (ModelPush) json2Model(extra, ModelPush.class);
            if (!TextUtils.isEmpty(mModelPush.RefTable) && (mModelPush.RefTable.equals("CreateGroup") || mModelPush.RefTable.equals("EidtGroup") || mModelPush.RefTable.equals("DelGroup") || mModelPush.RefTable.equals("HasBeenRemoved") || mModelPush.RefTable.equals("HasBeenAdded"))) {
                Frame.HANDLES.sentAll("FrgQzList", 0, null);
            }
            Frame.HANDLES.sentAll("FrgGoutong", 4, null);
            Frame.HANDLES.sentAll("FrgWd", 0, null);
            Frame.HANDLES.sentAll("FrgHome", 6, null);
        } else if (JPushInterface.ACTION_NOTIFICATION_OPENED.equals(intent
                .getAction())) {
            if (getAppSatus(context, context.getPackageName()) == 3) {
                PackageManager packageManager = context.getPackageManager();
                context.startActivity(packageManager.getLaunchIntentForPackage(context.getPackageName()));
                return;
            }
            String extra = bundle.getString(JPushInterface.EXTRA_EXTRA);
            extra = extra.replace("RefID", "Id");
            ModelPush mModelPush = (ModelPush) json2Model(extra, ModelPush.class);
            if (mModelPush.RefTable.equals("CreateGroup") || mModelPush.RefTable.equals("EidtGroup") || mModelPush.RefTable.equals("DelGroup") || mModelPush.RefTable.equals(refTable_OaMail) || mModelPush.RefTable.equals("OaMess")) {
                return;
            }
            ModelBd.RowsBean item = new ModelBd.RowsBean();
            item.Id = mModelPush.Id;
            item.FlowRefTable = mModelPush.RefTable;
            item.FlowNodeID = mModelPush.FlowNodeID;
            item.FlowMultiSignID = mModelPush.FlowMultiSignID;

            for (int j = 0; j < mModelGzLists.size(); j++) {
                if (mModelGzLists.get(j).MenuMobileConfig.contains(item.FlowRefTable) && item.FlowRefTable.equals(mModelGzLists.get(j).mModelMenuConfig.flow.refTable)) {
                    item.text = mModelGzLists.get(j).text;
                    item.MenuNameEng = mModelGzLists.get(j).MenuNameEng;
                    item.mModelMenuConfig = new Gson().fromJson(getData(mModelGzLists.get(j).MenuMobileConfig, extra), ModelMenuConfig.class);
                    break;
                }
            }


            Helper.startActivity(context, Intent.FLAG_ACTIVITY_NEW_TASK, FrgPubWeb.class, TitleAct.class, "item", item);
        } else {
        }
    }

    public void showNormal(Context context, String content) {
        NotificationManager manager = (NotificationManager) context
                .getSystemService(Context.NOTIFICATION_SERVICE);
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        intent.setClass(context, IndexAct.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
        intent.putExtra("className", FrgHome.class);
        PendingIntent contentIntent = PendingIntent.getActivity(context, 0,
                intent, PendingIntent.FLAG_CANCEL_CURRENT);
        Notification notification = new NotificationCompat.Builder(context)
                .setSmallIcon(R.drawable.icon_blue).setContentTitle("金曲标准版")
                .setContentText(content).setContentIntent(contentIntent)
                .build();
        notification.flags = Notification.FLAG_AUTO_CANCEL;
        notification.defaults = Notification.DEFAULT_SOUND
                | Notification.DEFAULT_VIBRATE;
        if (F.mModelUsreLogin != null) {
            manager.notify((int) System.currentTimeMillis(), notification);
        }
    }

    public void showNormalPuTong(Context context, String content, String className, ModelBd.RowsBean obj) {
        NotificationManager manager = (NotificationManager) context
                .getSystemService(Context.NOTIFICATION_SERVICE);
        Intent intent = new Intent(context, TitleAct.class);
        intent.putExtra(IndexAct.EXTRA_CLASSNAME,
                className);
        intent.putExtra("item",
                obj);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent contentIntent = PendingIntent.getActivity(context, 0,
                intent, PendingIntent.FLAG_UPDATE_CURRENT);
        Notification notification = new NotificationCompat.Builder(context)
                .setSmallIcon(R.drawable.icon_blue).setTicker("金曲标准版")
                .setContentTitle("金曲标准版").setContentText(content)
                .setAutoCancel(true).setDefaults(Notification.DEFAULT_ALL)
                .setContentIntent(contentIntent).build();
        notification.flags = Notification.FLAG_AUTO_CANCEL;
        if (F.mModelUsreLogin != null) {
            manager.notify((int) System.currentTimeMillis(), notification);
        }
    }

    public int getAppSatus(Context context, String pageName) {
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> list = am.getRunningTasks(20);

        //判断程序是否在栈顶
        if (list.get(0).topActivity.getPackageName().equals(pageName)) {
            return 1;
        } else {
            //判断程序是否在栈里
            for (ActivityManager.RunningTaskInfo info : list) {
                if (info.topActivity.getPackageName().equals(pageName)) {
                    return 2;
                }
            }
            return 3;//栈里找不到，返回3
        }
    }
}
