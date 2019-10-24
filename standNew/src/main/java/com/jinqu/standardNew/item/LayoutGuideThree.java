//
//  LayoutGuideThree
//
//  Created by DELL on 2017-03-24 13:09:16
//  Copyright (c) DELL All rights reserved.


/**

 */

package com.jinqu.standardNew.item;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;

import com.jinqu.standardNew.F;
import com.jinqu.standardNew.R;
import com.jinqu.standardNew.frg.FrgLogin;
import com.jinqu.standardNew.util.DensityUtil;
import com.mdx.framework.activity.IndexAct;
import com.mdx.framework.utility.Helper;


public class LayoutGuideThree extends BaseItem {
    public View center;
    public ImageView page_three_center_bg;
    public ImageView page_three_phone;
    public Button button;
    public ImageView page_three_text;
    private PageThreeAnimationListener pageThreeAnimationListener;
    private Animation animationThreeCenterPhone, animationThreeText, animationThreeBtn;

    @SuppressLint("InflateParams")
    public static View getView(Context context, ViewGroup parent) {
        LayoutInflater flater = LayoutInflater.from(context);
        View convertView = flater.inflate(R.layout.item_layout_guide_three, null);
        convertView.setTag(new LayoutGuideThree(convertView));
        return convertView;
    }

    public LayoutGuideThree(View view) {
        this.contentview = view;
        this.context = contentview.getContext();
        initView();
    }

    private void initView() {
        this.contentview.setTag(this);
        findVMethod();
    }

    public void pageStart() {
        page_three_phone.setVisibility(View.INVISIBLE);
        page_three_text.setVisibility(View.INVISIBLE);
        button.setVisibility(View.INVISIBLE);
        page_three_phone.startAnimation(animationThreeCenterPhone);
    }

    public void pageStop() {
        animationThreeCenterPhone.cancel();
        animationThreeBtn.cancel();
        animationThreeText.cancel();
    }

    private void findVMethod() {
        center = (View) contentview.findViewById(R.id.center);
        page_three_center_bg = (ImageView) contentview.findViewById(R.id.page_three_center_bg);
        page_three_phone = (ImageView) contentview.findViewById(R.id.page_three_phone);
        button = (Button) contentview.findViewById(R.id.button);
        page_three_text = (ImageView) contentview.findViewById(R.id.page_three_text);

        button.setOnClickListener(com.mdx.framework.utility.Helper.delayClickLitener(this));
        initPageThree();
    }

    private void initPageThree() {
        pageThreeAnimationListener = new PageThreeAnimationListener();

        animationThreeBtn = AnimationUtils.loadAnimation(context, R.anim.tutorail_alpha_common);
        animationThreeBtn.setAnimationListener(pageThreeAnimationListener);


        int[] location = new int[2];
        DensityUtil densityUtil = new DensityUtil(context);
        page_three_text.getLocationOnScreen(location);
        float fromXDelta = location[0];
        float toYDelta = location[1];
        animationThreeText = new TranslateAnimation(fromXDelta, fromXDelta, densityUtil.getScreenHeight(), toYDelta);
        animationThreeText.setDuration(1000);
        animationThreeText.setAnimationListener(pageThreeAnimationListener);

        page_three_phone.getLocationOnScreen(location);
        fromXDelta = location[0];
        float fromYDelta = location[1];
        animationThreeCenterPhone = new TranslateAnimation(fromXDelta, fromXDelta, densityUtil.getScreenHeight(), toYDelta);
        animationThreeCenterPhone.setDuration(1000);
        animationThreeCenterPhone.setAnimationListener(pageThreeAnimationListener);
    }

    private class PageThreeAnimationListener implements Animation.AnimationListener {

        @Override
        public void onAnimationStart(Animation animation) {
        }

        @Override
        public void onAnimationEnd(Animation animation) {
            if (animation == animationThreeCenterPhone) {
                page_three_phone.setVisibility(View.VISIBLE);
                page_three_text.startAnimation(animationThreeText);
            } else if (animation == animationThreeText) {
                page_three_text.setVisibility(View.VISIBLE);
                button.startAnimation(animationThreeBtn);
            } else if (animation == animationThreeBtn) {
                button.setVisibility(View.VISIBLE);
            }
        }

        @Override
        public void onAnimationRepeat(Animation animation) {
        }
    }

    public void set(String item) {

    }

    @Override
    public void onClick(View v) {
        if (R.id.button == v.getId()) {
            F.saveFirstInstall();
            Helper.startActivity(context, FrgLogin.class, IndexAct.class);
        }
    }

}