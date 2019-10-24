package com.jinqu.standardNew.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.mdx.framework.Frame;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public class TxService extends Service {
    private ScheduledExecutorService mScheduledExecutorService;

    @SuppressWarnings("deprecation")
    @Override
    public void onCreate() {
        super.onCreate();
        mScheduledExecutorService = Executors
                .newSingleThreadScheduledExecutor();
        mScheduledExecutorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {

                Frame.HANDLES.sentAll("FrgRc", 114, null);
            }
        }, 1, 1, TimeUnit.MINUTES);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        mScheduledExecutorService.shutdown();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    @Override
    public IBinder onBind(Intent arg0) {
        return null;
    }


}