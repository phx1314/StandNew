package com.jinqu.standardNew.act;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import com.framewidget.F;
import com.framewidget.ModelUsreInfo;
import com.framewidget.view.Headlayout;
import com.google.gson.Gson;
import com.jinqu.standardNew.R;
import com.jinqu.standardNew.frg.FrgCreateQun;
import com.mdx.framework.activity.TitleAct;
import com.mdx.framework.utility.Helper;

import java.util.Locale;

import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Conversation;

import static com.jinqu.standardNew.F.METHOD_EDITINFO;
import static com.jinqu.standardNew.F.reFreashQiTa;
import static com.jinqu.standardNew.frg.BaseFrg.setBgClickClor;
import static io.rong.app.RongCloudEvent.rid2GroupId;
import static io.rong.app.RongCloudEvent.rid2UserId;

public class ConversationActivity extends BaseAct {
    private Headlayout mHeadlayout;
    private String uId;
    private String uName;
    public String refId;
    public String refTable;
    private Conversation.ConversationType mConversationType;

    @Override
    protected void create(Bundle savedInstanceState) {
        setContentView(R.layout.conversation);
        uId = getIntent().getData().getQueryParameter("targetId");
        uName = getIntent().getData().getQueryParameter("title");
        refId = getIntent().getData().getQueryParameter("refId");
        refTable = getIntent().getData().getQueryParameter("refTable");

        mHeadlayout = (Headlayout) findViewById(R.id.mHeadlayout);
        mHeadlayout.setLeftBackground(R.drawable.arrows_left);
        mHeadlayout.setGoBack(getActivity());
        mHeadlayout.setTitle(uName);
        mConversationType = Conversation.ConversationType.valueOf(getIntent()
                .getData().getLastPathSegment()
                .toUpperCase(Locale.getDefault()));
        if (mConversationType.equals(Conversation.ConversationType.PRIVATE)) {
            mHeadlayout.setRightBacgroud(R.drawable.ease_mm_title_remove);
            mHeadlayout.setRightOnclicker(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    setBgClickClor(view);
                    F.yShoure(getContext(), "确认删除", "删除聊天记录", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            RongIM.getInstance()
                                    .getRongIMClient()
                                    .clearMessages(mConversationType, uId,
                                            new RongIMClient.ResultCallback<Boolean>() {
                                                @Override
                                                public void onSuccess(Boolean arg0) {
                                                    Helper.toast("删除成功", getContext());
                                                }

                                                @Override
                                                public void onError(RongIMClient.ErrorCode arg0) {
                                                    Helper.toast("删除失败", getContext());
                                                }
                                            });
                        }
                    });

                }
            });
            loadUrlPostNoShow(METHOD_EDITINFO, "id", rid2UserId(uId));
        } else if (mConversationType
                .equals(Conversation.ConversationType.GROUP)) {
            mHeadlayout.setRightBacgroud(R.drawable.home_page_mygroup);
            mHeadlayout.setRightOnclicker(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (TextUtils.isEmpty(refId)) {
                        Helper.startActivity(getContext(), FrgCreateQun.class, TitleAct.class, "id", rid2GroupId(uId));
                    } else {
                        Helper.startActivity(getContext(), FrgCreateQun.class, TitleAct.class, "id", rid2GroupId(uId), "refId", refId, "refTable", refTable);
                    }
                }
            });

        }


    }

    @Override
    public void onSuccess(String methodName, String content) {
        if (methodName.equals(METHOD_EDITINFO)) {
            ModelUsreInfo mModelUsreInfo = new Gson().fromJson(content, ModelUsreInfo.class);
            reFreashQiTa(mModelUsreInfo);
        }
    }

    @Override
    public void disposeMsg(int type, Object obj) {
        switch (type) {
            case 1001:
                mHeadlayout.setTitle(obj.toString());
                break;
            case 2:
                this.finish();
                break;
            case 0:
                RongIM.getInstance()
                        .getRongIMClient()
                        .removeConversation(Conversation.ConversationType.GROUP,
                                uId);
                this.finish();
                break;
            case 3:
                if (mConversationType.equals(Conversation.ConversationType.PRIVATE)) {
                    mHeadlayout.setTitle(obj.toString());
                }
                break;
        }
    }
}
