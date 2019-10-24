//
//  LayoutGuideOne
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


public class LayoutGuideOne extends BaseItem {
    public View center;
    public ImageView page_one_center_bg;
    public ImageView page_one_center_hand;
    public ImageView page_one_item1;
    public ImageView page_one_item2;
    public ImageView page_one_item3;
    public ImageView page_one_left;
    public ImageView page_one_right;
    public ImageView page_one_text;
    private Animation animationOneCenterHand, animationOneItem1, animationOneItem2, animationOneItem3,
            animationOneLeft, animationOneRight, animationOneText;
    private PageOneAnimationListener pageOneAnimationListener;

    @SuppressLint("InflateParams")
    public static View getView(Context context, ViewGroup parent) {
        LayoutInflater flater = LayoutInflater.from(context);
        View convertView = flater.inflate(R.layout.item_layout_guide_one, null);
        convertView.setTag(new LayoutGuideOne(convertView));
        return convertView;
    }

    public LayoutGuideOne(View view) {
        this.contentview = view;
        this.context = contentview.getContext();
        initView();
    }

    private void initView() {
        this.contentview.setTag(this);
        findVMethod();
    }

    public void pageStart() {
        page_one_item1.setVisibility(View.INVISIBLE);
        page_one_item2.setVisibility(View.INVISIBLE);
        page_one_item3.setVisibility(View.INVISIBLE);
        page_one_text.setVisibility(View.INVISIBLE);

        page_one_center_hand.startAnimation(animationOneCenterHand);
        page_one_item1.startAnimation(animationOneItem1);
        page_one_left.startAnimation(animationOneLeft);
        page_one_right.startAnimation(animationOneRight);
    }

    public void pageStop() {
        animationOneCenterHand.cancel();
        animationOneItem1.cancel();
        animationOneItem2.cancel();
        animationOneItem3.cancel();
        animationOneLeft.cancel();
        animationOneRight.cancel();
        animationOneText.cancel();

    }

    public void initPageOne() {
        pageOneAnimationListener = new PageOneAnimationListener();

        animationOneCenterHand = AnimationUtils.loadAnimation(context, R.anim.tutorail_rotate_hand);
        animationOneCenterHand.setAnimationListener(pageOneAnimationListener);
        animationOneItem1 = AnimationUtils.loadAnimation(context, R.anim.tutorail_alpha_common);
        animationOneItem1.setAnimationListener(pageOneAnimationListener);
        animationOneItem2 = AnimationUtils.loadAnimation(context, R.anim.tutorail_alpha_common);
        animationOneItem2.setAnimationListener(pageOneAnimationListener);
        animationOneItem3 = AnimationUtils.loadAnimation(context, R.anim.tutorail_alpha_common);
        animationOneItem3.setAnimationListener(pageOneAnimationListener);

        int[] location = new int[2];
        DensityUtil densityUtil = new DensityUtil(context);
        page_one_text.getLocationOnScreen(location);
        float fromXDelta = location[0];
        float toYDelta = location[1];
        animationOneText = new TranslateAnimation(fromXDelta, fromXDelta, densityUtil.getScreenHeight(), toYDelta);
        animationOneText.setDuration(1000);
        animationOneText.setAnimationListener(pageOneAnimationListener);

        page_one_text.getLocationOnScreen(location);
        fromXDelta = location[0];
        float fromYDelta = location[1];
        animationOneLeft = new TranslateAnimation(fromXDelta, fromXDelta + 120, fromYDelta, fromYDelta);
        animationOneLeft.setDuration(800);
        animationOneLeft.setRepeatCount(-1);
        animationOneLeft.setRepeatMode(Animation.REVERSE);

        animationOneRight = new TranslateAnimation(fromXDelta, fromXDelta + 60, fromYDelta, fromYDelta);
        animationOneRight.setDuration(1200);
        animationOneRight.setRepeatCount(-1);
        animationOneRight.setRepeatMode(Animation.REVERSE);
    }

    private void findVMethod() {
        center = (View) contentview.findViewById(R.id.center);
        page_one_center_bg = (ImageView) contentview.findViewById(R.id.page_one_center_bg);
        page_one_center_hand = (ImageView) contentview.findViewById(R.id.page_one_center_hand);
        page_one_item1 = (ImageView) contentview.findViewById(R.id.page_one_item1);
        page_one_item2 = (ImageView) contentview.findViewById(R.id.page_one_item2);
        page_one_item3 = (ImageView) contentview.findViewById(R.id.page_one_item3);
        page_one_left = (ImageView) contentview.findViewById(R.id.page_one_left);
        page_one_right = (ImageView) contentview.findViewById(R.id.page_one_right);
        page_one_text = (ImageView) contentview.findViewById(R.id.page_one_text);
        initPageOne();
    }

    public void set(String item) {

    }

    private class PageOneAnimationListener implements Animation.AnimationListener {

        @Override
        public void onAnimationStart(Animation animation) {
        }

        @Override
        public void onAnimationEnd(Animation animation) {
            if (animation == animationOneCenterHand) {
                page_one_item1.startAnimation(animationOneItem1);
            } else if (animation == animationOneItem1) {
                page_one_item1.setVisibility(View.VISIBLE);
                page_one_item2.startAnimation(animationOneItem2);
            } else if (animation == animationOneItem2) {
                page_one_item2.setVisibility(View.VISIBLE);
                page_one_item3.startAnimation(animationOneItem3);
            } else if (animation == animationOneItem3) {
                page_one_item3.setVisibility(View.VISIBLE);
                page_one_text.startAnimation(animationOneText);
            } else if (animation == animationOneText) {
                page_one_text.setVisibility(View.VISIBLE);
            }
        }

        @Override
        public void onAnimationRepeat(Animation animation) {
        }
    }


}