//
//  FrgXx
//
//  Created by DELL on 2018-05-03 08:33:37
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
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageButton;

import com.androidkun.xtablayout.XTabLayout;
import com.framewidget.zxing.SimpleScannerActivity;
import com.jinqu.standardNew.R;
import com.jinqu.standardNew.view.MFragmentAdapter;
import com.mdx.framework.activity.NoTitleAct;
import com.mdx.framework.utility.Helper;
import com.mdx.framework.utility.permissions.PermissionRequest;

import java.util.ArrayList;
import java.util.List;

import static com.jinqu.standardNew.F.COLOR;


public class FrgXx extends BaseFrg {

//    ImageView image = new ImageView(context);
//    Drawable up = ContextCompat.getDrawable(context,R.drawable.ic_sort_up);
//    Drawable drawableUp= DrawableCompat.wrap(up);
//    DrawableCompat.setTint(drawableUp, ContextCompat.getColor(context,R.color.theme));
//    image.setImageDrawable(drawableUp);

    public android.support.v4.view.ViewPager mViewPager;
    public List<String> list_title = new ArrayList<>();
    public XTabLayout mTabLayout;
    public List<BaseFrg> fragments = new ArrayList();
    public ImageButton mImageButton_saoma;
    public ImageButton mImageButton_sousuo;

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

    @Override
    protected void create(Bundle savedInstanceState) {
        setContentView(R.layout.frg_xx);
        initView();
        loaddata();
    }

    private void initView() {
        findVMethod();
    }

    private void findVMethod() {
        mTabLayout = (XTabLayout) findViewById(R.id.mTabLayout);
        mViewPager = (android.support.v4.view.ViewPager) findViewById(R.id.mViewPager);
        mImageButton_saoma = (ImageButton) findViewById(R.id.mImageButton_saoma);
        mImageButton_sousuo = (ImageButton) findViewById(R.id.mImageButton_sousuo);
        mImageButton_saoma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
        mImageButton_sousuo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Helper.startActivity(getContext(), FrgSousuo.class, NoTitleAct.class, "from", "FrgTxl");
            }
        });
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == 0) {
                    mImageButton_sousuo.setVisibility(View.GONE);
                } else {
                    mImageButton_sousuo.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    public void loaddata() {
        mImageButton_sousuo.setVisibility(View.GONE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mImageButton_saoma.getDrawable().setTint(Color.parseColor(COLOR));
            mImageButton_sousuo.getDrawable().setTint(Color.parseColor(COLOR));
        }
        list_title.add("消  息");
        list_title.add("通讯录");
        fragments.add(new FrgGoutong());
        fragments.add(new FrgTxl());
        mViewPager.setAdapter(new MFragmentAdapter(
                getChildFragmentManager(), fragments));
        //将tabLayout与viewpager连起来
        mTabLayout.setupWithViewPager(mViewPager);
        mViewPager.setOffscreenPageLimit(2);
        mTabLayout.getTabAt(0).setText(list_title.get(0));
        mTabLayout.getTabAt(1).setText(list_title.get(1));
//        mTabLayout.getTabAt(0).select();

    }


}