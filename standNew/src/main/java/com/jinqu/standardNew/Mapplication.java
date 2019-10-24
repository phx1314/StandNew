package com.jinqu.standardNew;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.mdx.framework.Frame;

import cn.jpush.android.api.JPushInterface;
import io.rong.app.DemoContext;
import io.rong.app.RongCloudEvent;
import io.rong.app.message.AgreedFriendRequestMessage;
import io.rong.app.message.FileMessage;
import io.rong.app.message.LocationMessage;
import io.rong.app.message.provider.ContactNotificationMessageProvider;
import io.rong.app.message.provider.FileMessageProvider;
import io.rong.app.message.provider.LocationMessageProvider;
import io.rong.app.message.provider.NewDiscussionConversationProvider;
import io.rong.app.message.provider.RealTimeLocationMessageProvider;
import io.rong.imkit.RongContext;
import io.rong.imkit.RongIM;
import io.rong.imlib.ipc.RongExceptionHandler;

/**
 * Created by bob on 2015/1/30.
 */
public class Mapplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Frame.init(getApplicationContext());
        F.init();
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);
        if (getApplicationInfo().packageName
                .equals(getCurProcessName(getApplicationContext()))
                || "io.rong.push"
                .equals(getCurProcessName(getApplicationContext()))) {
            /**
             * IMKit SDK调用第一步 初始化
             */
            RongIM.init(this);
            /**
             * 融云SDK事件监听处理
             *
             * 注册相关代码，只需要在主进程里做。
             */
            if (getApplicationInfo().packageName
                    .equals(getCurProcessName(getApplicationContext()))) {
                DemoContext.init(this);
                RongCloudEvent.init(this);
                Thread.setDefaultUncaughtExceptionHandler(new RongExceptionHandler(
                        this));
                try {
                    RongIM.registerMessageType(AgreedFriendRequestMessage.class);

                    RongIM.registerMessageTemplate(new ContactNotificationMessageProvider());
                    // @ 消息模板展示
                    RongContext.getInstance().registerConversationTemplate(
                            new NewDiscussionConversationProvider());
                    RongIM.registerMessageTemplate(new RealTimeLocationMessageProvider());
                    RongIM.registerMessageType(FileMessage.class);
                    RongIM.registerMessageTemplate(new FileMessageProvider());
                    RongIM.registerMessageType(LocationMessage.class);
                    RongIM.registerMessageTemplate(new LocationMessageProvider());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 获得当前进程的名字
     *
     * @param context
     * @return 进程号
     */
    public static String getCurProcessName(Context context) {

        int pid = android.os.Process.myPid();

        ActivityManager activityManager = (ActivityManager) context
                .getSystemService(Context.ACTIVITY_SERVICE);

        for (ActivityManager.RunningAppProcessInfo appProcess : activityManager
                .getRunningAppProcesses()) {

            if (appProcess.pid == pid) {
                return appProcess.processName;
            }
        }
        return null;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}
