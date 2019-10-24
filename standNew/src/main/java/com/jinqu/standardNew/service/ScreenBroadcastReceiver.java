package com.jinqu.standardNew.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import static com.jinqu.standardNew.frg.BaseFrg.isShowDialog;


/**
 * Created by DELL on 2017/7/19.
 */

public class ScreenBroadcastReceiver extends BroadcastReceiver {
    private String action = null;

    @Override
    public void onReceive(Context context, Intent intent) {
        action = intent.getAction();
        if (Intent.ACTION_SCREEN_ON.equals(action)) {
            // 开屏
        } else if (Intent.ACTION_SCREEN_OFF.equals(action)) {
            // 锁屏
            isShowDialog = true;
        } else if (Intent.ACTION_USER_PRESENT.equals(action)) {
            // 解锁
        }
    }
}