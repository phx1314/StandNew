//
//  FrgFujianDetail
//
//  Created by DELL on 2018-06-22 09:45:29
//  Copyright (c) DELL All rights reserved.


/**

 */

package com.jinqu.standardNew.frg;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.ab.http.AbFileHttpResponseListener;
import com.ab.http.HttpUtil;
import com.jinqu.standardNew.R;
import com.jinqu.standardNew.model.ModelFjList;
import com.jinqu.standardNew.util.UtilDate;
import com.jinqu.standardNew.util.UtilFile;
import com.mdx.framework.config.BaseConfig;
import com.mdx.framework.utility.Helper;
import com.mdx.framework.widget.ActionBar;

import java.io.File;
import java.net.URLDecoder;

import static com.jinqu.standardNew.F.filePath;
import static com.jinqu.standardNew.F.mModelUsreLogin;
import static com.jinqu.standardNew.util.UtilDate.changeTime;
import static io.rong.app.F.openFile;


public class FrgFujianDetail extends BaseFrg {

    public TextView mTextView_name;
    public TextView mTextView_pepole;
    public TextView mTextView_size;
    public TextView mTextView_time1;
    public TextView mTextView_time2;
    public TextView mTextView_progress;
    public ProgressBar mProgressBar;
    public TextView mTextView_kan;
    public ModelFjList.RowsBean data;
    public String refTable;
    public File f;

    @Override
    protected void create(Bundle savedInstanceState) {
        data = (ModelFjList.RowsBean) getActivity().getIntent().getSerializableExtra("data");
        refTable = getActivity().getIntent().getStringExtra("refTable");
        setContentView(R.layout.frg_fujian_detail);
        initView();
        loaddata();
    }

    private void initView() {
        findVMethod();
    }

    private void findVMethod() {
        mTextView_name = (TextView) findViewById(R.id.mTextView_name);
        mTextView_pepole = (TextView) findViewById(R.id.mTextView_pepole);
        mTextView_size = (TextView) findViewById(R.id.mTextView_size);
        mTextView_time1 = (TextView) findViewById(R.id.mTextView_time1);
        mTextView_time2 = (TextView) findViewById(R.id.mTextView_time2);
        mTextView_progress = (TextView) findViewById(R.id.mTextView_progress);
        mProgressBar = (ProgressBar) findViewById(R.id.mProgressBar);
        mTextView_kan = (TextView) findViewById(R.id.mTextView_kan);

        mTextView_kan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mTextView_kan.getText().toString().trim().equals("下载")) {
                    deleteFile(f);
                    if (com.framewidget.F.ExistSDCard()) {
                        File destDir = new File(filePath + mModelUsreLogin.UserInfo.EmpID
                                + "/" + refTable);
                        if (!destDir.exists()) {
                            destDir.mkdirs();
                        }
                        String url = "";
                        if (data.ID != 0) {
                            url = BaseConfig.getUri() + "/core/ProcessFile/Download?id=" + data.ID + "&type=" + data.Type;
                        } else {
                            url = BaseConfig.getUri() + "/core/ProcessFile/Download?id=" + data.ID + "&name=" + data.IDD +
                                    "&realName=" + data.Name;
                        }
                        HttpUtil.downLoadFile(getContext(), url, new AbFileHttpResponseListener(new File(filePath + mModelUsreLogin.UserInfo.EmpID
                                + "/" + refTable + "/" + data.Name)) {
                            @Override
                            public void onStart() {
                                mTextView_kan.setEnabled(false);
                            }


                            @Override
                            public void onFinish() {
                                if (mProgressBar.getProgress() == 100) {
                                    Helper.toast("下载成功", getContext());
                                    mProgressBar.setProgress(100);
                                    mTextView_progress.setText("100%");
                                    mTextView_kan.setText("打开");
                                    mTextView_kan.setEnabled(true);
                                }
                            }

                            @Override
                            public void onFailure(int statusCode, String content, Throwable error) {
                                Helper.toast("下载失败" + content, getContext());
                                mTextView_kan.setText("下载");
                                mTextView_kan.setEnabled(true);
                            }

                            @Override
                            public void onProgress(final long bytesWritten, final long totalSize) {
                                super.onProgress(bytesWritten, totalSize);
                                mTextView_kan.setEnabled(false);
                                mTextView_kan.setText("下载中...");
                                mProgressBar.setProgress((int) (bytesWritten * 100 / totalSize));
                                mTextView_progress.setText((int) (bytesWritten * 100 / totalSize) + "%");
                            }
                        });
                    } else {
                        Helper.toast("请先插入内存卡", getContext());
                    }
                } else {
                    openFile(f, getContext());
                }
            }
        });
    }

    public void loaddata() {
        f = new File(filePath + mModelUsreLogin.UserInfo.EmpID
                + "/" + refTable + "/" + data.Name);
        if (f.exists()) {
            mTextView_kan.setText("打开");
            mProgressBar.setVisibility(View.GONE);
        } else {
            mTextView_kan.setText("下载");
        }
        mTextView_name.setText(data.Name);
        mTextView_pepole.setText(URLDecoder.decode(data.EmpName));
        mTextView_size.setText(UtilFile.FormetFileSize(data.Size));
        mTextView_time1.setText((data.LastModifyDate.contains("T") ? changeTime(data.LastModifyDate) : UtilDate.formatMatchDate(data.LastModifyDate, "yyyy-MM-dd")));
        mTextView_time2.setText((data.UploadDate.contains("T") ? changeTime(data.UploadDate) : UtilDate.formatMatchDate(data.UploadDate, "yyyy-MM-dd")));
    }

    public void tiShi() {
        com.framewidget.F.yShoure(getActivity(), "确认退出", "文件正在下载，是否确认退出!",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        deleteFile(f);
                        FrgFujianDetail.this.finish();
                    }
                });
    }



    public void deleteFile(File file) {
        if (file.exists()) { // 判断文件是否存在
            if (file.isFile()) { // 判断是否是文件
                file.delete(); // delete()方法 你应该知道 是删除的意思;
            } else if (file.isDirectory()) { // 否则如果它是一个目录
                File files[] = file.listFiles(); // 声明目录下所有的文件 files[];
                for (int i = 0; i < files.length; i++) { // 遍历目录下所有的文件
                    this.deleteFile(files[i]); // 把每个文件 用这个方法进行迭代
                }
            }
            file.delete();
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (mProgressBar.getProgress() > 0 && mProgressBar.getProgress() < 100) {
                tiShi();
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void setActionBar(ActionBar actionBar, Context context) {
        super.setActionBar(actionBar, context);
        mHeadlayout.setTitle("附件详情");
    }
}