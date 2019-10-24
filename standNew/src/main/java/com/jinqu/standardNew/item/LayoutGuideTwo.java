//
//  LayoutGuideTwo
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
import android.widget.ImageView;

import com.jinqu.standardNew.R;
import com.jinqu.standardNew.util.DensityUtil;


public class LayoutGuideTwo extends BaseItem {
    public View center;
    public ImageView page_two_center_bg;
    public ImageView page_two_center_chat;
    public ImageView page_two_item1;
    public ImageView page_two_item2;
    public ImageView page_two_item3;
    public ImageView page_two_item4;
    public ImageView page_two_text;
    private PageTwoAnimationListener pageTwoAnimationListener;
    private Animation animationTwoCenterChat, animationTwoItem1, animationTwoItem2, animationTwoItem3,
            animationTwoItem4_1, animationTwoItem4_2, animationTwoText;

    @SuppressLint("InflateParams")
    public static View getView(Context context, ViewGroup parent) {
        LayoutInflater flater = LayoutInflater.from(context);
        View convertView = flater.inflate(R.layout.item_layout_guide_two, null);
        convertView.setTag(new LayoutGuideTwo(convertView));
        return convertView;
    }

    public LayoutGuideTwo(View view) {
        this.contentview = view;
        this.context = contentview.getContext();
        initView();
    }

    public void pageStop() {

        animationTwoCenterChat.cancel();
        animationTwoItem1.cancel();
        animationTwoItem2.cancel();
        animationTwoItem3.cancel();
        animationTwoItem4_1.cancel();
        animationTwoItem4_2.cancel();
        animationTwoText.cancel();

    }

    public void pageStart() {
        page_two_center_chat.setVisibility(View.INVISIBLE);
        page_two_item1.setVisibility(View.INVISIBLE);
        page_two_item2.setVisibility(View.INVISIBLE);
        page_two_item3.setVisibility(View.INVISIBLE);
        animationTwoItem4_2.cancel();
        page_two_item4.setVisibility(View.INVISIBLE);
        page_two_text.setVisibility(View.INVISIBLE);

        page_two_center_chat.startAnimation(animationTwoCenterChat);
    }

    private void initView() {
        this.contentview.setTag(this);
        findVMethod();
        initPageTwo();
    }

    private void initPageTwo() {
        pageTwoAnimationListener = new PageTwoAnimationListener();
        animationTwoCenterChat = AnimationUtils.loadAnimation(context, R.anim.tutorail_alpha_common);
        animationTwoCenterChat.setAnimationListener(pageTwoAnimationListener);
        animationTwoItem1 = AnimationUtils.loadAnimation(context, R.anim.tutorail_alpha_common);
        animationTwoItem1.setAnimationListener(pageTwoAnimationListener);
        animationTwoItem2 = AnimationUtils.loadAnimation(context, R.anim.tutorail_alpha_common);
        animationTwoItem2.setAnimationListener(pageTwoAnimationListener);
        animationTwoItem3 = AnimationUtils.loadAnimation(context, R.anim.tutorail_alpha_common);
        animationTwoItem3.setAnimationListener(pageTwoAnimationListener);
        animationTwoItem4_1 = AnimationUtils.loadAnimation(context, R.anim.tutorail_alpha_common);
        animationTwoItem4_1.setAnimationListener(pageTwoAnimationListener);

        int[] location = new int[2];
        DensityUtil densityUtil = new DensityUtil(context);
        page_two_text.getLocationOnScreen(location);
        float fromXDelta = location[0];
        float toYDelta = location[1];
        animationTwoText = new TranslateAnimation(fromXDelta, fromXDelta, densityUtil.getScreenHeight(), toYDelta);
        animationTwoText.setDuration(1000);
        animationTwoText.setAnimationListener(pageTwoAnimationListener);
        animationTwoItem4_2 = AnimationUtils.loadAnimation(context, R.anim.tutorail_scalate_top);

    }

    private class PageTwoAnimationListener implements Animation.AnimationListener {

        @Override
        public void onAnimationStart(Animation animation) {
        }

        @Override
        public void onAnimationEnd(Animation animation) {
            if (animation == animationTwoCenterChat) {
                page_two_center_chat.setVisibility(View.VISIBLE);
                page_two_item1.startAnimation(animationTwoItem1);
            } else if (animation == animationTwoItem1) {
                page_two_item1.setVisibility(View.VISIBLE);
                page_two_item2.startAnimation(animationTwoItem2);
            } else if (animation == animationTwoItem2) {
                page_two_item2.setVisibility(View.VISIBLE);
                page_two_item3.startAnimation(animationTwoItem3);
            } else if (animation == animationTwoItem3) {
                page_two_item3.setVisibility(View.VISIBLE);
                page_two_item4.startAnimation(animationTwoItem4_1);
            } else if (animation == animationTwoItem4_1) {
                page_two_item4.setVisibility(View.VISIBLE);
                page_two_item4.startAnimation(animationTwoItem4_2);
                page_two_text.startAnimation(animationTwoText);
            } else if (animation == animationTwoText) {
                page_two_text.setVisibility(View.VISIBLE);
            }
        }

        @Override
        public void onAnimationRepeat(Animation animation) {
        }
    }

    private void findVMethod() {
        center = (View) contentview.findViewById(R.id.center);
        page_two_center_bg = (ImageView) contentview.findViewById(R.id.page_two_center_bg);
        page_two_center_chat = (ImageView) contentview.findViewById(R.id.page_two_center_chat);
        page_two_item1 = (ImageView) contentview.findViewById(R.id.page_two_item1);
        page_two_item2 = (ImageView) contentview.findViewById(R.id.page_two_item2);
        page_two_item3 = (ImageView) contentview.findViewById(R.id.page_two_item3);
        page_two_item4 = (ImageView) contentview.findViewById(R.id.page_two_item4);
        page_two_text = (ImageView) contentview.findViewById(R.id.page_two_text);


    }

    public void set(String item) {

    }


}