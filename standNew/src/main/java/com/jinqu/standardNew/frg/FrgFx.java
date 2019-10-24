//
//  FrgFx
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
import android.view.View;
import android.widget.ImageButton;

import com.androidkun.xtablayout.XTabLayout;
import com.framewidget.newMenu.DfMViewPager;
import com.framewidget.zxing.SimpleScannerActivity;
import com.jinqu.standardNew.R;
import com.jinqu.standardNew.model.ModelMenuConfig;
import com.jinqu.standardNew.view.MFragmentAdapter;
import com.mdx.framework.activity.NoTitleAct;
import com.mdx.framework.utility.Helper;
import com.mdx.framework.utility.permissions.PermissionRequest;

import java.util.ArrayList;
import java.util.List;

import static com.jinqu.standardNew.F.COLOR;


public class FrgFx extends BaseFrg {

    public XTabLayout mTabLayout;
    public DfMViewPager mViewPager;

    public List<String> list_title = new ArrayList<>();
    public List<BaseFrg> fragments = new ArrayList();
    public ImageButton mImageButton_saoma;
    public ImageButton mImageButton_sousuo;

    @Override
    protected void create(Bundle savedInstanceState) {
        setContentView(R.layout.frg_fx);
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
                Drawable mDrawable2 = getResources().getDrawable(R.drawable.sousuo);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
                    mDrawable2.setTint(Color.parseColor(COLOR));
                mImageButton_sousuo.setImageDrawable(mDrawable2);
                break;
            case 0:
                mViewPager.setNoScroll((Boolean) obj);
                break;
            case 1:
                mViewPager.setCurrentItem(0);
                break;
        }
    }

    private void initView() {
        findVMethod();
    }

    private void findVMethod() {
        mTabLayout = (XTabLayout) findViewById(R.id.mTabLayout);
        mViewPager = (DfMViewPager) findViewById(R.id.mViewPager);
        mImageButton_saoma = (ImageButton) findViewById(R.id.mImageButton_saoma);
        mImageButton_sousuo = (ImageButton) findViewById(R.id.mImageButton_sousuo);

        mImageButton_saoma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Helper.requestPermissions(new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, new PermissionRequest() {
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
                List<ModelMenuConfig.SearchBean> search = new ArrayList<ModelMenuConfig.SearchBean>();
                ModelMenuConfig.SearchBean s1 = new ModelMenuConfig.SearchBean();
                s1.type = "text";
                s1.text = "请输入标题、流程名称、节点名称";
                s1.sqlstring =
                        "{\"isGroup\":false,\"list\":[{\"Key\":\"NewsTitle,NewsContent\",\"Contract\":\"like\",\"Value\":\"#{value}\"}]}";
                search.add(s1);
                Helper.startActivity(getContext(), FrgSousuo.class, NoTitleAct.class, "from", "FrgBd", "data", search);
            }
        });
    }


    public void loaddata() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mImageButton_saoma.getDrawable().setTint(Color.parseColor(COLOR));
            mImageButton_sousuo.getDrawable().setTint(Color.parseColor(COLOR));
        }
        list_title.add("日程");
        list_title.add("任务");
        list_title.add("表单");
        fragments.add(new FrgRc());
        fragments.add(new FrgRw());
        fragments.add(new FrgBd());
        mViewPager.setAdapter(new MFragmentAdapter(
                getChildFragmentManager(), fragments));
        //将tabLayout与viewpager连起来
        mTabLayout.setupWithViewPager(mViewPager);
        mViewPager.setOffscreenPageLimit(3);
        mTabLayout.getTabAt(0).setText(list_title.get(0));
        mTabLayout.getTabAt(1).setText(list_title.get(1));
        mTabLayout.getTabAt(2).setText(list_title.get(2));
    }


}