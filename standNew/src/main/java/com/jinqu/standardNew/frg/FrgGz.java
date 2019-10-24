//
//  FrgGz
//
//  Created by DELL on 2018-05-08 11:17:04
//  Copyright (c) DELL All rights reserved.


/**

 */

package com.jinqu.standardNew.frg;

import android.Manifest;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.ImageButton;

import com.ab.view.listener.AbOnListViewListener;
import com.framewidget.zxing.SimpleScannerActivity;
import com.google.gson.Gson;
import com.jinqu.standardNew.R;
import com.jinqu.standardNew.ada.AdaGz;
import com.jinqu.standardNew.model.ModelGzList;
import com.mdx.framework.adapter.MAdapter;
import com.mdx.framework.utility.Helper;
import com.mdx.framework.utility.permissions.PermissionRequest;

import static com.jinqu.standardNew.F.COLOR;
import static com.jinqu.standardNew.F.METHOD_mobilemenujson;


public class FrgGz extends BaseFrg {


    public com.ab.view.pullview.AbPullListView mAbPullListView;
    public ImageButton mImageButton_saoma;

    @Override
    protected void create(Bundle savedInstanceState) {
        setContentView(R.layout.frg_gz);
        initView();
        loaddata();
    }


    @Override
    public void disposeMsg(int type, Object obj) {
        switch (type) {
            case 10086:
                Drawable mDrawable = getResources().getDrawable(R.drawable.saoma);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
                    mDrawable.setTint(Color.parseColor(COLOR));
                mImageButton_saoma.setImageDrawable(mDrawable);
                break;
        }
    }

    private void initView() {
        findVMethod();
    }

    private void findVMethod() {


        mAbPullListView = (com.ab.view.pullview.AbPullListView) findViewById(R.id.mAbPullListView);
        mImageButton_saoma = (ImageButton) findViewById(R.id.mImageButton_saoma);
        mImageButton_saoma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setBgClickClor(v);
                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        1);
                Helper.requestPermissions(new String[]{"android.permission.CAMERA"}, new PermissionRequest() {
                    public void onGrant(String[] permissions, int[] grantResults) {
                        startActivity(new Intent(
                                getActivity(), SimpleScannerActivity.class));
                    }
                });
            }
        });
    }

    public void loaddata() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mImageButton_saoma.getDrawable().setTint(Color.parseColor(COLOR));
        }
        mAbPullListView.setPostApiLoadParams(METHOD_mobilemenujson);
        mAbPullListView.setPageSize(Integer.MAX_VALUE);
        mAbPullListView.setAbOnListViewListener(new AbOnListViewListener() {
            @Override
            public MAdapter onSuccess(String methodName, String content) {
                ModelGzList mModelGzList = new Gson().fromJson(content, ModelGzList.class);
                return new AdaGz(getContext(), mModelGzList.rows);
            }
        });
    }


}