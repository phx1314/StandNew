package com.framewidget.error;//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.view.View;

import com.mdx.framework.activity.PTitleAct;
import com.mdx.framework.utility.Helper;

import java.util.ArrayList;
import java.util.HashMap;

public class PopUpdataPhoto {
    private Context context;
    private PopUpdataPhoto.OnReceiverPhoto onReceiverPhoto;
    private PopUpdataPhoto.OnReceiverPhotos onReceiverPhotos;
    public int offerY = 0;
    private HashMap<String, Object> map = new HashMap();
    private BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            int i = intent.getExtras().getInt("type", 1);
            if(i == 2) {
                ArrayList str = (ArrayList)intent.getExtras().getSerializable("list");
                PopUpdataPhoto.this.onReceiverPhotos.onReceiverPhoto(str);
            } else if(i != 0) {
                String str1 = intent.getExtras().getString("what");
                int w = intent.getExtras().getInt("width", 0);
                int h = intent.getExtras().getInt("height", 0);
                PopUpdataPhoto.this.onReceiverPhoto.onReceiverPhoto(str1, w, h);
            }

            PopUpdataPhoto.this.endReceive();
        }
    };

    /** @deprecated */
    @Deprecated
    public PopUpdataPhoto(Context context, View view) {
        this.context = context;
    }

    /** @deprecated */
    @Deprecated
    public PopUpdataPhoto(Context context, PopUpdataPhoto.OnReceiverPhoto onReceiverPhoto, PopUpdataPhoto.OnReceiverPhotos onReceiverPhotos) {
        this.context = context;
        this.onReceiverPhoto = onReceiverPhoto;
        this.onReceiverPhotos = onReceiverPhotos;
    }

    public void putParams(String key, Integer value) {
        this.map.put(key, value);
    }

    /** @deprecated */
    @Deprecated
    public void show() {
        this.map.put("finishtype", Integer.valueOf(1));
        Helper.startActivity(this.context, MultiplePhotoSelect.class, PTitleAct.class, this.map);
        this.startReceive();
    }

    /** @deprecated */
    @Deprecated
    public void shown() {
        this.map.put("finishtype", Integer.valueOf(3));
        Helper.startActivity(this.context, MultiplePhotoSelect.class, PTitleAct.class, this.map);
        this.startReceive();
    }

    private void startReceive() {
        if(this.onReceiverPhoto != null || this.onReceiverPhotos != null) {
            try {
                this.context.unregisterReceiver(this.mBroadcastReceiver);
            } catch (Exception var2) {
                ;
            }

            IntentFilter myIntentFilter = new IntentFilter();
            myIntentFilter.addAction("com.mdx.receivePhoto");
            this.context.registerReceiver(this.mBroadcastReceiver, myIntentFilter);
        }
    }

    private void endReceive() {
        try {
            this.context.unregisterReceiver(this.mBroadcastReceiver);
        } catch (Exception var2) {
            ;
        }

    }

    public void setOnReceiverPhoto(PopUpdataPhoto.OnReceiverPhoto onreceiverPhoto) {
        this.onReceiverPhoto = onreceiverPhoto;
    }

    public interface OnReceiverPhotos {
        void onReceiverPhoto(ArrayList<String> var1);
    }

    public interface OnReceiverPhoto {
        void onReceiverPhoto(String var1, int var2, int var3);
    }
}
