package com.jinqu.standardNew.frg;//
//  FrgLy
//
//  Created by DELL on 2018-04-08 13:19:38
//  Copyright (c) DELL All rights reserved.


/**

 */


import android.Manifest;
import android.content.Context;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.jinqu.standardNew.R;
import com.mdx.framework.Frame;
import com.mdx.framework.utility.Helper;
import com.mdx.framework.utility.permissions.PermissionRequest;
import com.mdx.framework.widget.ActionBar;

import java.io.File;


public class FrgLuyin extends BaseFrg {

    public TextView mTextView_time;
    public ImageButton mImageButton_ly;
    // 录音功能相关
    public MediaRecorder mMediaRecorder; // MediaRecorder 实例
    public int timeCount = 60; // 录音时长 计数
    // 录音文件存放目录
    public final String audioSaveDir = Environment.getExternalStorageDirectory() + "/" + System.currentTimeMillis() + ".mp3";
    public Handler mHandler = new Handler();
    public Runnable runnable;

    @Override
    protected void create(Bundle savedInstanceState) {
        setContentView(R.layout.frg_luyin);
        initView();
        loaddata();
    }

    private void initView() {
        findVMethod();
    }

    private void findVMethod() {
        mTextView_time = (TextView) findViewById(R.id.mTextView_time);
        mImageButton_ly = (ImageButton) findViewById(R.id.mImageButton_ly);

        mImageButton_ly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setBgClickClor(v);
                Helper.requestPermissions(new String[]{"android.permission.RECORD_AUDIO", Manifest.permission.WRITE_EXTERNAL_STORAGE}, new PermissionRequest() {
                    public void onGrant(String[] permissions, int[] grantResults) {
                        mImageButton_ly.setEnabled(false);
                        startRecord();
                        mHeadlayout.setRText("保存");
                        mHandler.post(runnable);
                    }
                });
            }
        });
    }

    public void loaddata() {
        File file = new File(audioSaveDir);
        if (file.exists())
            file.delete();

        runnable = new Runnable() {
            @Override
            public void run() {
                timeCount--;
                mTextView_time.setText(FormatMiss(timeCount));
                if (timeCount <= 1) {
                    stopRecord();
                    Helper.toast("录音完成", getContext());
                    Frame.HANDLES.sentAll("FrgFujianListEdit", 112, audioSaveDir);
                    FrgLuyin.this.finish();
                } else {
                    mHandler.postDelayed(runnable, 1000);
                }
            }
        };

    }

    /**
     * 开始录音 使用amr格式
     * 录音文件
     *
     * @return
     */
    public void startRecord() {
        // 开始录音
    /* ①Initial：实例化MediaRecorder对象 */
        if (mMediaRecorder == null)
            mMediaRecorder = new MediaRecorder();
        try {
      /* ②setAudioSource/setVedioSource */
            mMediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);// 设置麦克风
      /*
       * ②设置输出文件的格式：THREE_GPP/MPEG-4/RAW_AMR/Default THREE_GPP(3gp格式
       * ，H263视频/ARM音频编码)、MPEG-4、RAW_AMR(只支持音频且音频编码要求为AMR_NB)
       */
            mMediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
      /* ②设置音频文件的编码：AAC/AMR_NB/AMR_MB/Default 声音的（波形）的采样 */
            mMediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AAC);
      /* ③准备 */
            mMediaRecorder.setOutputFile(audioSaveDir);
            mMediaRecorder.prepare();
      /* ④开始 */
            mMediaRecorder.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 格式化 录音时长为 时:分:秒
    public static String FormatMiss(int miss) {
        String hh = miss / 3600 > 9 ? miss / 3600 + "" : "0" + miss / 3600;
        String mm = (miss % 3600) / 60 > 9 ? (miss % 3600) / 60 + "" : "0" + (miss % 3600) / 60;
        String ss = (miss % 3600) % 60 > 9 ? (miss % 3600) % 60 + "" : "0" + (miss % 3600) % 60;
        return hh + ":" + mm + ":" + ss;
    }


    /**
     * 停止录音
     */
    public void stopRecord() {
        mHandler.removeCallbacks(runnable);
        //有一些网友反应在5.0以上在调用stop的时候会报错，翻阅了一下谷歌文档发现上面确实写的有可能会报错的情况，捕获异常清理一下就行了，感谢大家反馈！
        try {
            if (mMediaRecorder != null) {
                mMediaRecorder.stop();
                mMediaRecorder.release();
                mMediaRecorder = null;
            }
        } catch (RuntimeException e) {
            mMediaRecorder.reset();
            mMediaRecorder.release();
            mMediaRecorder = null;
        }
    }


    @Override
    public void setActionBar(ActionBar actionBar, Context context) {
        super.setActionBar(actionBar, context);
        mHeadlayout.setTitle("录音");
        mHeadlayout.setLeftOnclicker(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopRecord();
                FrgLuyin.this.finish();
            }
        });
        mHeadlayout.setRightOnclicker(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopRecord();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Frame.HANDLES.sentAll("FrgFujianListEdit", 112, audioSaveDir);
                        FrgLuyin.this.finish();
                    }
                }, 1);

            }
        });
    }
}