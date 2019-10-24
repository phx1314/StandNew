//
//  FrgQzList
//
//  Created by DELL on 2018-05-17 16:57:06
//  Copyright (c) DELL All rights reserved.


/**

 */

package com.jinqu.standardNew.frg;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import com.ab.view.listener.AbOnListViewListener;
import com.ab.view.pullview.AbPullListView;
import com.google.gson.Gson;
import com.jinqu.standardNew.R;
import com.jinqu.standardNew.ada.AdaQzList;
import com.jinqu.standardNew.model.ModelQunList;
import com.jinqu.standardNew.util.QzxxComparator;
import com.jinqu.standardNew.view.FloatingActionButton;
import com.jinqu.standardNew.view.ScrollDirectionListener;
import com.mdx.framework.activity.TitleAct;
import com.mdx.framework.adapter.MAdapter;
import com.mdx.framework.utility.Helper;
import com.mdx.framework.widget.ActionBar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import io.rong.imkit.RongIM;
import io.rong.imlib.model.Conversation;

import static com.jinqu.standardNew.F.COLOR;
import static com.jinqu.standardNew.F.METHOD_BASEGROUPPAGELIST;
import static io.rong.app.RongCloudEvent.rid2GroupId;


public class FrgQzList extends BaseFrg {

    public AbPullListView mAbPullListView;
    public FloatingActionButton fab;


    @Override
    protected void create(Bundle savedInstanceState) {
        setContentView(R.layout.frg_qz_list);
        initView();
        loaddata();
    }

    @Override
    public void disposeMsg(int type, Object obj) {
        switch (type) {
            case 0:
                mAbPullListView.reLoad();
                break;
        }
    }

    private void initView() {
        findVMethod();
    }

    private void findVMethod() {
        mAbPullListView = (AbPullListView) findViewById(R.id.mAbPullListView);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.attachToListView(mAbPullListView, new ScrollDirectionListener() {
            @Override
            public void onScrollDown() {
            }

            @Override
            public void onScrollUp() {
            }
        }, mAbPullListView);
        fab.setOnClickListener(Helper.delayClickLitener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Helper.startActivity(getContext(), FrgCreateQun.class, TitleAct.class);
            }
        }));
    }

    public void loaddata() {
        mAbPullListView.setPageSize(Integer.MAX_VALUE);
        mAbPullListView.setPostApiLoadParams(METHOD_BASEGROUPPAGELIST);
        mAbPullListView.setAbOnListViewListener(new AbOnListViewListener() {
            @Override
            public MAdapter onSuccess(String methodName, String content) {
                ModelQunList mModelSystemList = new Gson().fromJson(content, ModelQunList.class);
                List<Conversation> mConversations = RongIM.getInstance().getRongIMClient()
                        .getConversationList(Conversation.ConversationType.GROUP)==null?new ArrayList<Conversation>():RongIM.getInstance().getRongIMClient()
                        .getConversationList(Conversation.ConversationType.GROUP);
                for (ModelQunList.RowsBean item : mModelSystemList.rows) {
                    for (Conversation mConversation : mConversations) {
                        if (rid2GroupId(mConversation.getTargetId()).equals(item.GroupID + "")) {
                            item.mConversation = mConversation;
                            break;
                        }
                    }
                }
                Collections.sort(mModelSystemList.rows, new QzxxComparator());
                return new AdaQzList(getContext(), mModelSystemList.rows);
            }
        });
        fab.setColorNormal(Color.parseColor(COLOR));
        fab.setColorPressed(Color.parseColor(COLOR));
        fab.setColorRipple(Color.parseColor(COLOR));
    }

    @Override
    public void setActionBar(ActionBar actionBar, Context context) {
        super.setActionBar(actionBar, context);
        mHeadlayout.setTitle("我的群组");
    }
}