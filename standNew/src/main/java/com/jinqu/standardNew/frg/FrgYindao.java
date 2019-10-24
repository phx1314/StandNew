//
//  FrgYindao
//
//  Created by DELL on 2017-03-24 10:49:20
//  Copyright (c) DELL All rights reserved.


/**

 */

package com.jinqu.standardNew.frg;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.LinearLayout;

import com.framewidget.view.DfCirleCurr;
import com.jinqu.standardNew.R;
import com.jinqu.standardNew.ada.AdaYindao;
import com.jinqu.standardNew.item.LayoutGuideOne;
import com.jinqu.standardNew.item.LayoutGuideThree;
import com.jinqu.standardNew.item.LayoutGuideTwo;

import java.util.ArrayList;
import java.util.List;



public class FrgYindao extends BaseFrg {

    public LinearLayout activity_main;
    public DfCirleCurr mDfCirleCurr;
    public List<View> mviews = new ArrayList<>();
    public View view1;
    public View view2;
    public View view3;

    @Override
    protected void create(Bundle savedInstanceState) {
        setContentView(R.layout.frg_yindao);
        initView();
        loaddata();
    }

    private void initView() {
        findVMethod();
    }

    private void findVMethod() {
        activity_main = (LinearLayout) findViewById(R.id.activity_main);
        mDfCirleCurr = (DfCirleCurr) findViewById(R.id.mDfCirleCurr);
        mDfCirleCurr.setRadious(getResources().getDimension(R.dimen.j5dp));
        mDfCirleCurr.setAutoScroll(false);
        mDfCirleCurr.setPadding((int) getResources().getDimension(R.dimen.j10dp), 0, (int) getResources().getDimension(R.dimen.j10dp), (int) getResources().getDimension(R.dimen.j20dp));
    }

    public void loaddata() {
        view1 = LayoutGuideOne.getView(getContext(), null);
        view2 = LayoutGuideTwo.getView(getContext(), null);
        view3 = LayoutGuideThree.getView(getContext(), null);
        mviews.add(view1);
        mviews.add(view2);
        mviews.add(view3);
        mDfCirleCurr.setAdapter(new AdaYindao(getContext(), mviews));
        mDfCirleCurr.setFillColor(Color.parseColor("#375968"));
        mDfCirleCurr.setPageColor(Color.parseColor("#E2DEDE"));
        mDfCirleCurr.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        ((LayoutGuideOne) view1.getTag()).pageStop();
                        ((LayoutGuideOne) view1.getTag()).pageStart();
                        break;
                    case 1:
                        ((LayoutGuideTwo) view2.getTag()).pageStop();
                        ((LayoutGuideTwo) view2.getTag()).pageStart();
                        break;
                    case 2:
                        ((LayoutGuideThree) view3.getTag()).pageStop();
                        ((LayoutGuideThree) view3.getTag()).pageStart();
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                ((LayoutGuideOne) view1.getTag()).pageStart();
            }
        }, 500);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ((LayoutGuideOne) view1.getTag()).pageStop();
        ((LayoutGuideTwo) view2.getTag()).pageStop();
        ((LayoutGuideThree) view3.getTag()).pageStop();
    }
}