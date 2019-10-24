//
//  FrgFujianList
//
//  Created by DELL on 2017-04-18 08:34:01
//  Copyright (c) DELL All rights reserved.


/**

 */

package com.jinqu.standardNew.frg;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import com.ab.util.AbDateUtil;
import com.ab.view.pullview.AbPullListView;
import com.framewidget.error.PopUpdataPhoto;
import com.jinqu.standardNew.R;
import com.jinqu.standardNew.ada.AdaFujianList;
import com.jinqu.standardNew.model.ModelFjList;
import com.jinqu.standardNew.pop.PopShowFile;
import com.jinqu.standardNew.util.MyRandomAccessFile;
import com.jinqu.standardNew.util.UtilDate;
import com.jinqu.standardNew.util.UtilFile;
import com.jone.SDCard.DataFile;
import com.jone.SDCard.FileSizeUtil;
import com.jone.SDCard.MainActivity;
import com.mdx.framework.Frame;
import com.mdx.framework.utility.Helper;
import com.mdx.framework.utility.permissions.PermissionRequest;
import com.mdx.framework.widget.ActionBar;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.jinqu.standardNew.F.METHOD_DELETE;
import static com.jinqu.standardNew.F.METHOD_UPLOAD;
import static com.jinqu.standardNew.F.json2Model;
import static com.jinqu.standardNew.F.saveFile;


public class FrgFujianListEdit extends BaseFrg {

    public AbPullListView mAbPullListView;
    public String refTable;
    public ArrayList<ModelFjList.RowsBean> mModelWenjianUploads = new ArrayList<>();
    public DataFile file;
    public String from;
    public int refID;
    public String vido_path;
    public ProgressDialog mProgressDialog;

    @Override
    protected void create(Bundle savedInstanceState) {
        mModelWenjianUploads = (ArrayList<ModelFjList.RowsBean>) getActivity().getIntent().getSerializableExtra("mModelWenjianUploads");
        refTable = getActivity().getIntent().getStringExtra("refTable");
        refID = getActivity().getIntent().getIntExtra("id", 0);
        from = getActivity().getIntent().getStringExtra("from");
        setContentView(R.layout.frg_fujian_list);
        initView();
        loaddata();
    }

    @Override
    public void disposeMsg(int type, Object obj) {
        switch (type) {
            case 110:
                startActivityForResult(new Intent(getContext(), MainActivity.class), 1);
                break;
            case 114:
                com.framewidget.F.getPhoto(getActivity(), new PopUpdataPhoto.OnReceiverPhoto() {
                    @Override
                    public void onReceiverPhoto(final String photoPath, int width,
                                                int height) {
                        if (photoPath != null) {
                            File mfile = new File(photoPath);
                            uploadFile(mfile);
                        }
                    }
                }, -1, 10, 640, 640);
                break;
            case 111:
                Helper.requestPermissions(new String[]{"android.permission.CAMERA"}, new PermissionRequest() {
                    public void onGrant(String[] permissions, int[] grantResults) {
                        vido_path = Environment.getExternalStorageDirectory() + "/" + System.currentTimeMillis() + ".mp4";
                        // 激活系统的照相机进行录像
                        Intent intent = new Intent();
                        intent.setAction("android.media.action.VIDEO_CAPTURE");
                        intent.addCategory("android.intent.category.DEFAULT");
                        //限制时长
                        intent.putExtra(MediaStore.EXTRA_DURATION_LIMIT, 10);
//                        intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);
//                        intent.putExtra(MediaStore.EXTRA_SIZE_LIMIT, 2*1024 * 1024L);//限制录制大小(10M=10 * 1024 * 1024L)

                        File file = new File(vido_path);
                        if (file.exists())
                            file.delete();
                        Uri uri = Uri.fromFile(new File(vido_path));
                        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
                        startActivityForResult(intent, 5);
                    }
                });
                break;
            case 112:
                uploadFile(new File(obj.toString()));
                break;
            case 113:
                try {
                    uploadFile(saveFile((Bitmap) obj, Environment.getExternalStorageDirectory() + "/地图截屏文件夹", System.currentTimeMillis() + ".png"));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case 220:
                try {
                    if (file.getmFile().length() >= 1024 * 1024 * 2) {
                        int progress = (file.start * 100 + Integer.valueOf(obj.toString())) / file.mFile_son.size();
                        Log.i("progress", progress + "");
                        mProgressDialog.setProgress(progress);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case 103:
                Frame.HANDLES.sentAll(from, 103, obj);
                ModelFjList.RowsBean item = (ModelFjList.RowsBean) obj;
                loadUrlPost(METHOD_DELETE, "idSet", item.ID + "", "mode", "0");
                break;
            case 321:
                try {
                    uploadFile(saveFile((Bitmap) obj, Environment.getExternalStorageDirectory() + "/金曲水印文件夹", "shuiyin.png"));
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
        mAbPullListView = (AbPullListView) findViewById(R.id.mAbPullListView);

    }

    public void loaddata() {
        mAbPullListView.setPullLoadEnable(false);
        mAbPullListView.setPullRefreshEnable(false);
        mAbPullListView.setAdapter(new AdaFujianList(getContext(), mModelWenjianUploads, refTable, true));

        mProgressDialog = new ProgressDialog(getContext());
        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        mProgressDialog.setMessage("文件上传...");
        mProgressDialog.setMax(100);
    }


    @Override
    public void setActionBar(ActionBar actionBar, Context context) {
        super.setActionBar(actionBar, context);
        mHeadlayout.setTitle("附件列表");
        mHeadlayout.setRightBacgroud(R.drawable.caidan_more);
        mHeadlayout.setRightOnclicker(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setBgClickClor(view);
                PopShowFile mPopShowFile = new PopShowFile(getContext(), mHeadlayout.btn_right);
                mPopShowFile.show();
            }
        });
    }

    public void uploadFile(File mfile) {
        file = new DataFile(FileSizeUtil
                .getAutoFileOrFilesSize(mfile), mfile.getName(), "", mfile);
        MyRandomAccessFile mMyRandomAccessFile = new MyRandomAccessFile();
        if (file.getmFile().length() >= 1024 * 1024 * 2) {
            List<String> fileName = mMyRandomAccessFile.getFileName(file.getmFile(), mfile.getName());
            for (String string : fileName) {
                try {
                    File out = new File(string);
                    file.mFile_son.add(out);
                    mMyRandomAccessFile.segmentation(file.getmFile(), out);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
//            try {
//                mMyRandomAccessFile.mergeFile(fileName, new File(Environment.getExternalStorageDirectory() + "/HECHING.mp4"));
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
            mProgressDialog.show();
            loadPostNoShow(METHOD_UPLOAD, "gan", "file", file.mFile_son.get(file.start), "name", file.getFileName().endsWith("temp") ? file.getFileName().substring(0, file.getFileName().length() - 4) : file.getFileName(), "chunk", file.start, "chunks", file.mFile_son.size(), "refID", refID, "refTable", refTable, "fileGuid", "", "fileID", "1234", "parentID", "0", "mode", "0", "lastModifiedTime", AbDateUtil.getCurrentDate("yyyy-MM-dd HH:mm:ss"));
        } else {
            loadUrlPost(METHOD_UPLOAD, "file", file.getmFile(), "name", file.getFileName().endsWith("temp") ? file.getFileName().substring(0, file.getFileName().length() - 4) : file.getFileName(), "chunk", "0", "chunks", "1", "refID", refID, "refTable", refTable, "fileID", "UploadFile1", "parentID", "0", "mode", "0", "lastModifiedTime", AbDateUtil.getCurrentDate("yyyy-MM-dd HH:mm:ss"));
        }
    }


    @Override
    public void onSuccess(String methodName, String content) {
        if (methodName.equals(METHOD_UPLOAD)) {
            ModelFjList.RowsBean mModelWenjianUpload = (ModelFjList.RowsBean) json2Model(content, ModelFjList.RowsBean.class);
            mModelWenjianUpload.file = file.getmFile();
            if (!TextUtils.isEmpty(mModelWenjianUpload.Name)) {
                mModelWenjianUpload.IDD = mModelWenjianUpload.Name;
            } else {
                mModelWenjianUpload.ID = mModelWenjianUpload.AttachID;
                mModelWenjianUpload.Type = "attach";
            }
            mModelWenjianUpload.Name = file.getFileName().endsWith("temp") ? file.getFileName().substring(0, file.getFileName().length() - 4) : file.getFileName();
            mModelWenjianUpload.LastModifyDate = UtilDate.formatDateToLong(new Date(file.getmFile().lastModified()));
            mModelWenjianUpload.UploadDate = UtilDate.formatDateToLong(new Date());
            try {
                mModelWenjianUpload.Size = (int) UtilFile.getFileSize(file.getmFile());
            } catch (Exception e) {
                e.printStackTrace();
            }
            ((AdaFujianList) mAbPullListView.getmAdapter()).add(mModelWenjianUpload);
            Frame.HANDLES.sentAll(from, 102, mModelWenjianUpload);
            Helper.toast("文件上传成功", getContext());
        } else if (methodName.equals(METHOD_DELETE)) {
            Frame.HANDLES.sentAll("FrgGcjshDetail", 0, null);
        } else if (methodName.equals("gan")) {
            ModelFjList.RowsBean mModelWenjianUpload = (ModelFjList.RowsBean) json2Model(content, ModelFjList.RowsBean.class);
            file.start++;
            if (file.start < file.mFile_son.size()) {
                loadPostNoShow(METHOD_UPLOAD, "gan", "file", file.mFile_son.get(file.start), "name", file.getFileName().endsWith("temp") ? file.getFileName().substring(0, file.getFileName().length() - 4) : file.getFileName(), "fileGuid", mModelWenjianUpload.fileGuid, "chunk", file.start, "chunks", file.mFile_son.size(), "refID", refID, "refTable", refTable, "fileID", "1234", "parentID", "0", "mode", "0", "lastModifiedTime", AbDateUtil.getCurrentDate("yyyy-MM-dd HH:mm:ss"));
            } else {
                mProgressDialog.dismiss();
                mModelWenjianUpload.file = file.getmFile();
                if (!TextUtils.isEmpty(mModelWenjianUpload.Name)) {
                    mModelWenjianUpload.IDD = mModelWenjianUpload.Name;
                } else {
                    mModelWenjianUpload.ID = mModelWenjianUpload.AttachID;
                    mModelWenjianUpload.Type = "attach";
                }
                mModelWenjianUpload.Name = file.getFileName().endsWith("temp") ? file.getFileName().substring(0, file.getFileName().length() - 4) : file.getFileName();
                mModelWenjianUpload.LastModifyDate = UtilDate.formatDateToLong(new Date(file.getmFile().lastModified()));
                mModelWenjianUpload.UploadDate = UtilDate.formatDateToLong(new Date());
                try {
                    mModelWenjianUpload.Size = (int) UtilFile.getFileSize(file.getmFile());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                ((AdaFujianList) mAbPullListView.getmAdapter()).add(mModelWenjianUpload);
                Frame.HANDLES.sentAll(from, 102, mModelWenjianUpload);
                Helper.toast("文件上传成功", getContext());
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 5) {
            if (resultCode != 0) {
                uploadFile(new File(vido_path));
            }
        } else {
            if (data != null) {
                file = (DataFile) data.getSerializableExtra("data");
                uploadFile(file.getmFile());
//                loadUrlPost(METHOD_UPLOAD, "file", file.getmFile(), "name", file.getFileName().endsWith("temp") ? file.getFileName().substring(0, file.getFileName().length() - 4) : file.getFileName(), "chunk", "0", "chunks", "1", "refID", refID, "refTable", refTable, "fileID", "UploadFile1", "parentID", "0", "mode", "0", "lastModifiedTime", AbDateUtil.getCurrentDate("yyyy-MM-dd HH:mm:ss"));
            }
        }
    }
}