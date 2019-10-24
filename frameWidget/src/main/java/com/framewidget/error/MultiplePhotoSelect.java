package com.framewidget.error;//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//


import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.provider.MediaStore.Images.Media;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.GridView;

import com.mdx.framework.Frame;
import com.mdx.framework.R.id;
import com.mdx.framework.R.layout;
import com.mdx.framework.R.string;
import com.mdx.framework.activity.CameraActivity;
import com.mdx.framework.activity.MFragment;
import com.mdx.framework.dialog.PhotoShow;
import com.mdx.framework.frg.multiplephoto.ImageCursorAdapter;
import com.mdx.framework.frg.multiplephoto.ImageCursorAdapter.Image;
import com.mdx.framework.utility.Util;
import com.mdx.framework.widget.ActionBar;
import com.mdx.framework.widget.getphoto.ActCameraStream;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.UUID;

public class MultiplePhotoSelect extends MFragment implements LoaderCallbacks<Cursor>, OnClickListener {
    private ImageCursorAdapter ica = null;
    private String filePath;
    private static final String[] STORE_IMAGES = new String[]{"_display_name", "date_added", "_data", "bucket_display_name", "latitude", "longitude", "_id"};
    public GridView grid_view;
    public Button button;
    public int maxSelectPhoto = 1;
    public int mfinishtype = 0;
    public OnClickListener camSel = new OnClickListener() {
        public void onClick(View v) {
            byte cam = 0;
            if (MultiplePhotoSelect.this.maxSelectPhoto == 1 && MultiplePhotoSelect.this.ica.checkedList.size() == 1) {
                MultiplePhotoSelect.this.result();
            }

            if (MultiplePhotoSelect.this.ica.img.data != null) {
                cam = 1;
            }

            if (MultiplePhotoSelect.this.maxSelectPhoto <= 0) {
                MultiplePhotoSelect.this.button.setText(MultiplePhotoSelect.this.getActivity().getString(string.complete) + "(" + (MultiplePhotoSelect.this.ica.checkedList.size() + cam) + ")");
            } else {
                MultiplePhotoSelect.this.button.setText(MultiplePhotoSelect.this.getActivity().getString(string.complete) + "(" + (MultiplePhotoSelect.this.ica.checkedList.size() + cam) + "/" + MultiplePhotoSelect.this.maxSelectPhoto + ")");
            }

        }
    };
    private boolean isCallBack = false;

    public MultiplePhotoSelect() {
    }

    public void setActionBar(ActionBar actionBar, Context context) {
        actionBar.setTitle(context.getString(string.choose_photo));
    }

    protected void create(Bundle savedInstanceState) {
        this.setContentView(layout.default_mutiple_photo_select);
        this.mfinishtype = this.getActivity().getIntent().getIntExtra("finishtype", 0);
        this.maxSelectPhoto = this.getActivity().getIntent().getIntExtra("Max", this.maxSelectPhoto);
        String temptime = UUID.randomUUID().toString().replace("-", "");
        this.filePath = Util.getDPath(this.getContext(), "/temp/csimages/").getPath() + temptime + "_cstempsave.temp";
        this.initView();
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == -1) {
            String str = "";
            if (data != null && data.getData() != null) {
                str = data.getData().toString();
                str = str.substring("file://".length());
            } else {
                str = this.filePath;
            }

            this.ica.img.data = str;
            this.ica.img.id = str;
            Frame.IMAGECACHE.remove("file:" + str);
            this.ica.notifyDataSetChanged();
            this.camSel.onClick((View) null);
            if (this.maxSelectPhoto == 1) {
                this.result();
            }
        }

    }

    private void initView() {
        this.grid_view = (GridView) this.findViewById(id.grid_view);
        this.button = (Button) this.findViewById(id.submit);
        this.ica = new ImageCursorAdapter(this.getContext(), (Cursor) null, this, new OnClickListener() {
            public void onClick(View v) {
                Image image = (Image) v.getTag();
                ArrayList list = new ArrayList();
                boolean bol = false;
                if (MultiplePhotoSelect.this.ica.img.data != null) {
                    list.add("file:" + MultiplePhotoSelect.this.ica.img.data);
                }

                Iterator ps = MultiplePhotoSelect.this.ica.checkedList.iterator();

                while (ps.hasNext()) {
                    Image img = (Image) ps.next();
                    list.add("file:" + img.data);
                    if (img.id.equals(image.id)) {
                        bol = true;
                    }
                }

                if (!bol) {
                    list.add("file:" + image.data);
                }

                PhotoShow ps1 = new PhotoShow(MultiplePhotoSelect.this.getContext(), list, "file:" + image.data);
                ps1.show();
            }
        }, this.camSel);
        if (this.maxSelectPhoto == 0) {
            this.ica.maxSize = 2147483647;
        } else {
            this.ica.maxSize = this.maxSelectPhoto;
        }

        this.camSel.onClick((View) null);
        this.grid_view.setAdapter(this.ica);
        this.getLoaderManager().initLoader(0, (Bundle) null, this);
        this.button.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                MultiplePhotoSelect.this.result();
            }
        });
        if (this.maxSelectPhoto == 1) {
            this.findViewById(id.control).setVisibility(8);
        }

    }

    private void result() {
        Intent intent = new Intent();
        ArrayList list = new ArrayList();
        if (this.ica.img.data != null) {
            list.add(this.ica.img.data);
        }

        Iterator sendi = this.ica.checkedList.iterator();

        while (sendi.hasNext()) {
            Image key = (Image) sendi.next();
            list.add(key.data);
        }

        intent.putExtra("data", list);
        intent.putExtra("type", "ms");
        if (this.mfinishtype == 3) {
            Intent sendi1 = new Intent();
            sendi1.setAction("com.mdx.receivePhoto");
            sendi1.putExtra("list", list);
            sendi1.putExtra("type", 2);
            this.getActivity().sendBroadcast(sendi1);
            this.isCallBack = true;
        }

        if (this.mfinishtype == 1) {
            intent.setClass(this.getActivity(), ActCameraStream.class);
            sendi = this.getActivity().getIntent().getExtras().keySet().iterator();

            while (sendi.hasNext()) {
                String key1 = (String) sendi.next();
                Object obj = this.getActivity().getIntent().getExtras().get(key1);
                if (obj instanceof Boolean) {
                    intent.putExtra(key1, (Boolean) obj);
                } else if (obj instanceof Integer) {
                    intent.putExtra(key1, (Integer) obj);
                } else if (obj instanceof Float) {
                    intent.putExtra(key1, (Float) obj);
                } else if (obj instanceof Double) {
                    intent.putExtra(key1, (Double) obj);
                } else if (obj instanceof Long) {
                    intent.putExtra(key1, (Long) obj);
                } else if (obj instanceof String) {
                    intent.putExtra(key1, (String) obj);
                } else if (obj instanceof Serializable) {
                    intent.putExtra(key1, (Serializable) obj);
                } else if (obj instanceof Byte[]) {
                    intent.putExtra(key1, (Byte[]) ((Byte[]) obj));
                } else if (obj instanceof String[]) {
                    intent.putExtra(key1, (String[]) ((String[]) obj));
                } else if (obj instanceof Parcelable) {
                    intent.putExtra(key1, (Parcelable) obj);
                }
            }

            this.startActivity(intent);
            this.isCallBack = true;
        } else {
            this.getActivity().setResult(-1, intent);
        }

        this.finish();
    }

    protected void destroy() {
        if (!this.isCallBack) {
            Intent intent = new Intent();
            intent.setAction("com.mdx.receivePhoto");
            intent.putExtra("type", 0);
            this.getActivity().sendBroadcast(intent);
        }

    }

    public Loader<Cursor> onCreateLoader(int arg0, Bundle arg1) {
        CursorLoader cursorLoader = new CursorLoader(this.getContext(), Media.EXTERNAL_CONTENT_URI, STORE_IMAGES, (String) null, (String[]) null, "date_modified desc");
        return cursorLoader;
    }

    public void onLoaderReset(Loader<Cursor> arg0) {
        this.ica.swapCursor((Cursor) null);
    }

    public void onLoadFinished(Loader<Cursor> arg0, Cursor cursor) {
        this.ica.swapCursor(cursor);
    }

    public void onClick(View v) {
        Intent capture;
        capture = new Intent(this.getActivity(), CameraActivity.class);
        capture.putExtra("output", Uri.fromFile(new File(this.filePath)));
        capture.putExtra("android.intent.extra.screenOrientation", true);
        this.startActivityForResult(capture, 2);
//        if("google,".indexOf(Device.getBrand() + ",") >= 0) {
//            capture = new Intent("android.media.action.IMAGE_CAPTURE");
//            capture.putExtra("output", Uri.fromFile(new File(this.filePath)));
//            capture.putExtra("android.intent.extra.screenOrientation", true);
//            this.startActivityForResult(capture, 2);
//        } else {
//            capture = new Intent(this.getActivity(), CameraActivity.class);
//            capture.putExtra("output", Uri.fromFile(new File(this.filePath)));
//            capture.putExtra("android.intent.extra.screenOrientation", true);
//            this.startActivityForResult(capture, 2);
//        }
    }
}
