//
//  FrgGoutong
//
//  Created by DELL on 2018-05-08 13:12:34
//  Copyright (c) DELL All rights reserved.


/**

 */

package com.jinqu.standardNew.frg;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ab.util.AbDateUtil;
import com.google.gson.Gson;
import com.jinqu.standardNew.R;
import com.jinqu.standardNew.act.ConversationListFragment;
import com.jinqu.standardNew.model.ModelBd;
import com.jinqu.standardNew.model.ModelSystemList;
import com.jinqu.standardNew.model.ModelXtxxUnread;
import com.jinqu.standardNew.util.UtilDate;
import com.mdx.framework.Frame;
import com.mdx.framework.activity.TitleAct;
import com.mdx.framework.utility.Helper;

import io.rong.imkit.RongIM;
import io.rong.imlib.model.Conversation;

import static com.ab.util.AbDateUtil.dateFormatYMDHMS;
import static com.jinqu.standardNew.F.METHOD_GETLIST;
import static com.jinqu.standardNew.F.METHOD_GetMessages;
import static com.jinqu.standardNew.F.METHOD_OANOTICE;
import static com.jinqu.standardNew.F.font;
import static com.jinqu.standardNew.F.json2Model;


public class FrgGoutong extends BaseFrg {


    public TextView mTextView_time1;
    public TextView mTextView_content1;
    public TextView mTextView_time2;
    public TextView mTextView_content2;
    public TextView mTextView_content3;
    public TextView mImageView1;
    public TextView mImageView2;
    public TextView mImageView3;
    public LinearLayout mLinearLayout1;
    public LinearLayout mLinearLayout2;
    public LinearLayout mLinearLayout3;
    public TextView mTextView_count1;
    public TextView mTextView_count2;
    public TextView mTextView_count3;

    @Override
    protected void create(Bundle savedInstanceState) {
        setContentView(R.layout.frg_goutong);
        initView();
        loaddata();
    }

    @Override
    public void onResume() {
        super.onResume();
        js();
    }

    public void js() {
        try {
//            if (RongIM.getInstance().getRongIMClient()
//                    .getConversationList(Conversation.ConversationType.GROUP).size() > 0) {
//                mLinearLayout3.setVisibility(View.GONE);
//            } else {
//                mLinearLayout3.setVisibility(View.VISIBLE);
//            }
            loadUrlPostNoShow(METHOD_OANOTICE);
            loadUrlPostNoShow(METHOD_GetMessages);
            loadUrlPostNoShow(METHOD_GETLIST, "status", "0");
            int count = RongIM.getInstance().getRongIMClient()
                    .getUnreadCount(Conversation.ConversationType.GROUP);
            if (count > 0) {
                mTextView_count3.setText((count > 99 ? 99 : count) + "");
                mTextView_count3.setVisibility(View.VISIBLE);
            } else {
                mTextView_count3.setVisibility(View.INVISIBLE);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void disposeMsg(int type, Object obj) {
        switch (type) {
            case 4:
                js();
                break;
        }
    }

    private void initView() {
        findVMethod();
    }

    private void findVMethod() {


        mTextView_time1 = (TextView) findViewById(R.id.mTextView_time1);
        mTextView_content1 = (TextView) findViewById(R.id.mTextView_content1);
        mTextView_time2 = (TextView) findViewById(R.id.mTextView_time2);
        mTextView_content2 = (TextView) findViewById(R.id.mTextView_content2);
        mTextView_content3 = (TextView) findViewById(R.id.mTextView_content3);
        mImageView1 = (TextView) findViewById(R.id.mImageView1);
        mImageView2 = (TextView) findViewById(R.id.mImageView2);
        mImageView3 = (TextView) findViewById(R.id.mImageView3);
        mLinearLayout1 = (LinearLayout) findViewById(R.id.mLinearLayout1);
        mLinearLayout2 = (LinearLayout) findViewById(R.id.mLinearLayout2);
        mLinearLayout3 = (LinearLayout) findViewById(R.id.mLinearLayout3);
        mTextView_count1 = (TextView) findViewById(R.id.mTextView_count1);
        mTextView_count2 = (TextView) findViewById(R.id.mTextView_count2);
        mTextView_count3 = (TextView) findViewById(R.id.mTextView_count3);

        mLinearLayout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Helper.startActivity(getContext(), FrgGg.class, TitleAct.class);
            }
        });
        mLinearLayout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Helper.startActivity(getContext(), FrgXtxx.class, TitleAct.class);
            }
        });
        mLinearLayout3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Helper.startActivity(getContext(), FrgQzList.class, TitleAct.class);
            }
        });
    }

    public void loaddata() {
        ((GradientDrawable) mImageView1.getBackground()).setColor(Color.parseColor("#f1ac18"));
        ((GradientDrawable) mImageView2.getBackground()).setColor(Color.parseColor("#e71f19"));
        ((GradientDrawable) mImageView3.getBackground()).setColor(Color.parseColor("#2567b2"));
        mImageView1.setTypeface(font);
        mImageView2.setTypeface(font);
        mImageView3.setTypeface(font);
//        enterFragment();

        js();
    }

    @Override
    public void onSuccess(String methodName, String content) {
        if (methodName.equals(METHOD_OANOTICE)) {
            ModelBd mModelNewsList = new Gson().fromJson(content, ModelBd.class);
            if (mModelNewsList.rows.size() > 0) {
                mTextView_content1.setVisibility(View.VISIBLE);
                mTextView_time1.setVisibility(View.VISIBLE);
                mTextView_content1.setText(mModelNewsList.rows.get(0).Title);
                mTextView_time1.setText(AbDateUtil.formatDateStr2Desc(mModelNewsList.rows.get(0).CreationTime));
            } else {
                mTextView_content1.setVisibility(View.INVISIBLE);
                mTextView_time1.setVisibility(View.INVISIBLE);
            }
        } else if (methodName.equals(METHOD_GETLIST)) {
            ModelSystemList mModelSystemList = new Gson().fromJson(content, ModelSystemList.class);
            if (mModelSystemList.rows.size() > 0) {
                mTextView_content2.setVisibility(View.VISIBLE);
                mTextView_time2.setVisibility(View.VISIBLE);
                mTextView_content2.setText(mModelSystemList.rows.get(0).MessTitle);
                mTextView_time2.setText((AbDateUtil.formatDateStr2Desc(AbDateUtil.getStringByFormat(UtilDate.formatMatchDate(mModelSystemList.rows.get(0).MessDate, dateFormatYMDHMS), dateFormatYMDHMS))));
            } else {
                mTextView_content2.setVisibility(View.INVISIBLE);
                mTextView_time2.setVisibility(View.INVISIBLE);
            }
        } else if (methodName.equals(METHOD_GetMessages)) {
            ModelXtxxUnread mModelXtxxUnread = (ModelXtxxUnread) json2Model(content, ModelXtxxUnread.class);
            Frame.HANDLES.sentAll("FrgHome", 5, mModelXtxxUnread.Total);
            if (mModelXtxxUnread.Total > 0) {
                mTextView_count2.setText((mModelXtxxUnread.Total > 99 ? 99 : mModelXtxxUnread.Total) + "");
                mTextView_count2.setVisibility(View.VISIBLE);
            } else {
                mTextView_count2.setVisibility(View.INVISIBLE);
            }
        }
    }

    /**
     * 加载 会话列表 ConversationListFragment
     */
    private void enterFragment() {
        ConversationListFragment fragment = (ConversationListFragment) getChildFragmentManager()
                .findFragmentById(R.id.mfragment_siliao);
        Uri uri = Uri
                .parse("rong://"
                        + getActivity().getApplicationInfo().packageName)
                .buildUpon()
                .appendQueryParameter(
                        Conversation.ConversationType.PRIVATE.getName(),
                        "false") // 设置私聊会话非聚合显示
                .build();
        fragment.setUri(uri);
    }

}