package com.jinqu.standardNew.act;//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//


import android.app.Activity;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.TextView;
import android.widget.Toast;

import com.framewidget.view.MListView;
import com.jinqu.standardNew.R;
import com.jinqu.standardNew.frg.FrgQzList;
import com.mdx.framework.activity.TitleAct;
import com.mdx.framework.utility.Helper;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import io.rong.imkit.R.bool;
import io.rong.imkit.R.color;
import io.rong.imkit.R.dimen;
import io.rong.imkit.R.id;
import io.rong.imkit.R.layout;
import io.rong.imkit.R.string;
import io.rong.imkit.RLog;
import io.rong.imkit.RongContext;
import io.rong.imkit.RongIM;
import io.rong.imkit.RongNotificationManager;
import io.rong.imkit.fragment.UriFragment;
import io.rong.imkit.model.Draft;
import io.rong.imkit.model.Event.ConnectEvent;
import io.rong.imkit.model.Event.ConversationNotificationEvent;
import io.rong.imkit.model.Event.ConversationRemoveEvent;
import io.rong.imkit.model.Event.ConversationTopEvent;
import io.rong.imkit.model.Event.ConversationUnreadEvent;
import io.rong.imkit.model.Event.CreateDiscussionEvent;
import io.rong.imkit.model.Event.GroupUserInfoEvent;
import io.rong.imkit.model.Event.MessageDeleteEvent;
import io.rong.imkit.model.Event.MessageListenedEvent;
import io.rong.imkit.model.Event.MessagesClearEvent;
import io.rong.imkit.model.Event.OnMessageSendErrorEvent;
import io.rong.imkit.model.Event.OnReceiveMessageEvent;
import io.rong.imkit.model.Event.PublicServiceFollowableEvent;
import io.rong.imkit.model.Event.QuitDiscussionEvent;
import io.rong.imkit.model.Event.QuitGroupEvent;
import io.rong.imkit.model.Event.ReadReceiptEvent;
import io.rong.imkit.model.GroupUserInfo;
import io.rong.imkit.model.UIConversation;
import io.rong.imkit.util.ConversationListUtils;
import io.rong.imkit.widget.ArraysDialogFragment;
import io.rong.imkit.widget.ArraysDialogFragment.OnArraysDialogItemListener;
import io.rong.imlib.MessageTag;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.RongIMClient.ConnectCallback;
import io.rong.imlib.RongIMClient.ConnectionStatusListener.ConnectionStatus;
import io.rong.imlib.RongIMClient.ErrorCode;
import io.rong.imlib.RongIMClient.ResultCallback;
import io.rong.imlib.model.Conversation;
import io.rong.imlib.model.Conversation.ConversationType;
import io.rong.imlib.model.Discussion;
import io.rong.imlib.model.Group;
import io.rong.imlib.model.Message;
import io.rong.imlib.model.Message.MessageDirection;
import io.rong.imlib.model.Message.SentStatus;
import io.rong.imlib.model.MessageContent;
import io.rong.imlib.model.PublicServiceProfile;
import io.rong.imlib.model.UserInfo;
import io.rong.message.ReadReceiptMessage;
import io.rong.message.VoiceMessage;

public class ConversationListFragment extends UriFragment implements OnItemClickListener, OnItemLongClickListener, ConversationListAdapter.OnPortraitItemClick {
    private static String TAG = "ConvListFrag";
    private ConversationListAdapter mAdapter;
    private MListView mList;
    private TextView mNotificationBar;
    private boolean isShowWithoutConnected = false;
    private ArrayList<ConversationType> mSupportConversationList = new ArrayList();
    private ArrayList<Message> mMessageCache = new ArrayList();
    private ResultCallback<List<Conversation>> mCallback = new ResultCallback<List<Conversation>>() {
        public void onSuccess(List<Conversation> conversations) {
            RLog.d(this, "ConversationListFragment", "initFragment onSuccess callback : list = " + (conversations != null ? Integer.valueOf(conversations.size()) : "null"));
            if (ConversationListFragment.this.mAdapter != null && ConversationListFragment.this.mAdapter.getCount() != 0) {
                ConversationListFragment.this.mAdapter.clear();
            }

            if (conversations != null && conversations.size() != 0) {
                ConversationListFragment.this.makeUiConversationList(conversations);
                if (ConversationListFragment.this.mList != null && ConversationListFragment.this.mList.getAdapter() != null) {
                    ConversationListFragment.this.mAdapter.notifyDataSetChanged();
                }

            } else {
                if (ConversationListFragment.this.mAdapter != null) {
                    ConversationListFragment.this.mAdapter.notifyDataSetChanged();
                }

            }
        }

        public void onError(ErrorCode e) {
            RLog.d(this, "ConversationListFragment", "initFragment onError callback, e=" + e);
            if (e.equals(ErrorCode.IPC_DISCONNECT)) {
                ConversationListFragment.this.isShowWithoutConnected = true;
            }

        }
    };

    public ConversationListFragment() {
    }

    public static ConversationListFragment getInstance() {
        return new ConversationListFragment();
    }

    public void onCreate(Bundle savedInstanceState) {
        RLog.d(this, "ConversationListFragment", "onCreate");
        super.onCreate(savedInstanceState);
        this.mSupportConversationList.clear();
        RongContext.getInstance().getEventBus().register(this);
    }

    public void onAttach(Activity activity) {
        RLog.d(this, "ConversationListFragment", "onAttach");
        super.onAttach(activity);
    }

    protected void initFragment(Uri uri) {
        ConversationType[] conversationType = new ConversationType[]{ConversationType.PRIVATE, ConversationType.GROUP, ConversationType.DISCUSSION, ConversationType.SYSTEM, ConversationType.CUSTOMER_SERVICE, ConversationType.CHATROOM, ConversationType.PUBLIC_SERVICE, ConversationType.APP_PUBLIC_SERVICE};
        RLog.d(this, "ConversationListFragment", "initFragment");
        if (uri == null) {
            RongIM.getInstance().getRongIMClient().getConversationList(this.mCallback);
        } else {
            ConversationType[] arr$ = conversationType;
            int len$ = conversationType.length;

            for (int i$ = 0; i$ < len$; ++i$) {
                ConversationType type = arr$[i$];
                if (uri.getQueryParameter(type.getName()) != null) {
                    this.mSupportConversationList.add(type);
                    if ("true".equals(uri.getQueryParameter(type.getName()))) {
                        RongContext.getInstance().setConversationGatherState(type.getName(), Boolean.valueOf(true));
                    } else if ("false".equals(uri.getQueryParameter(type.getName()))) {
                        RongContext.getInstance().setConversationGatherState(type.getName(), Boolean.valueOf(false));
                    }
                }
            }

            if (RongIM.getInstance() != null && RongIM.getInstance().getRongIMClient() != null) {
                if (this.mSupportConversationList.size() > 0) {
                    RongIM.getInstance().getRongIMClient().getConversationList(this.mCallback, (ConversationType[]) this.mSupportConversationList.toArray(new ConversationType[this.mSupportConversationList.size()]));
                } else {
                    RongIM.getInstance().getRongIMClient().getConversationList(this.mCallback);
                }

            } else {
                Log.d("ConversationListFr", "RongCloud haven\'t been connected yet, so the conversation list display blank !!!");
                this.isShowWithoutConnected = true;
            }
        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        RLog.d(this, "ConversationListFragment", "onCreateView");
        View view = inflater.inflate(layout.rc_fr_conversationlist, container, false);
        this.mNotificationBar = (TextView) this.findViewById(view, id.rc_status_bar);
        this.mNotificationBar.setVisibility(8);
        this.mList = (MListView) this.findViewById(view, id.rc_list);
        TextView mEmptyView = (TextView) this.findViewById(view, 16908292);
        if (RongIM.getInstance() != null && RongIM.getInstance().getRongIMClient() != null) {
            mEmptyView.setText(RongContext.getInstance().getResources().getString(string.rc_conversation_list_empty_prompt));
        } else {
            mEmptyView.setText(RongContext.getInstance().getResources().getString(string.rc_conversation_list_not_connected));
        }

        this.mList.setEmptyView(mEmptyView);
        return view;
    }

    public void onViewCreated(View view, Bundle savedInstanceState) {
        if (this.mAdapter == null) {
            this.mAdapter = new ConversationListAdapter(RongContext.getInstance());
        }

        this.mList.setAdapter(this.mAdapter);
        this.mList.setOnItemClickListener(this);
        this.mList.setOnItemLongClickListener(this);
        this.mAdapter.setOnPortraitItemClick(this);
        super.onViewCreated(view, savedInstanceState);
    }

    public void onResume() {
        super.onResume();
        if (RongIM.getInstance() != null && RongIM.getInstance().getRongIMClient() != null) {
            RLog.d(this, "onResume", "current connect status is:" + RongIM.getInstance().getRongIMClient().getCurrentConnectionStatus());
            RongNotificationManager.getInstance().onRemoveNotification();
            ConnectionStatus status = RongIM.getInstance().getRongIMClient().getCurrentConnectionStatus();
            Drawable drawable = this.getActivity().getResources().getDrawable(R.drawable.rc_notification_network_available);
            int width = (int) this.getActivity().getResources().getDimension(dimen.rc_message_send_status_image_size);
            drawable.setBounds(0, 0, width, width);
            this.mNotificationBar.setCompoundDrawablePadding(16);
            this.mNotificationBar.setCompoundDrawables(drawable, (Drawable) null, (Drawable) null, (Drawable) null);
            if (status.equals(ConnectionStatus.NETWORK_UNAVAILABLE)) {
                this.mNotificationBar.setVisibility(0);
                this.mNotificationBar.setText(this.getResources().getString(string.rc_notice_network_unavailable));
                RongIM.getInstance().getRongIMClient().reconnect((ConnectCallback) null);
            } else if (status.equals(ConnectionStatus.KICKED_OFFLINE_BY_OTHER_CLIENT)) {
                this.mNotificationBar.setVisibility(0);
                this.mNotificationBar.setText(this.getResources().getString(string.rc_notice_tick));
            } else if (status.equals(ConnectionStatus.CONNECTED)) {
                this.mNotificationBar.setVisibility(8);
            } else if (status.equals(ConnectionStatus.DISCONNECTED)) {
                this.mNotificationBar.setVisibility(0);
                this.mNotificationBar.setText(this.getResources().getString(string.rc_notice_network_unavailable));
            } else if (status.equals(ConnectionStatus.CONNECTING)) {
                this.mNotificationBar.setVisibility(0);
                this.mNotificationBar.setText(this.getResources().getString(string.rc_notice_connecting));
            }

        } else {
            Log.d("ConversationListFr", "RongCloud haven\'t been connected yet, so the conversation list display blank !!!");
            this.isShowWithoutConnected = true;
        }
    }

    public void onDestroy() {
        RLog.d(this, "ConversationListFragment", "onDestroy");
        RongContext.getInstance().getEventBus().unregister(this);
        this.getHandler().removeCallbacksAndMessages((Object) null);
        super.onDestroy();
    }

    public void onPause() {
        RLog.d(this, "ConversationListFragment", "onPause");
        super.onPause();
    }

    public boolean onBackPressed() {
        return false;
    }

    public void setAdapter(ConversationListAdapter adapter) {
        if (this.mAdapter != null) {
            this.mAdapter.clear();
        }

        this.mAdapter = adapter;
        if (this.mList != null && this.getUri() != null) {
            this.mList.setAdapter(adapter);
            this.initFragment(this.getUri());
        }

    }

    public ConversationListAdapter getAdapter() {
        return this.mAdapter;
    }

    public void onEventMainThread(ConnectEvent event) {
        RLog.d(this, "onEventMainThread", "Event.ConnectEvent: isListRetrieved = " + this.isShowWithoutConnected);
        if (this.isShowWithoutConnected) {
            if (this.mSupportConversationList.size() > 0) {
                RongIM.getInstance().getRongIMClient().getConversationList(this.mCallback, (ConversationType[]) this.mSupportConversationList.toArray(new ConversationType[this.mSupportConversationList.size()]));
            } else {
                RongIM.getInstance().getRongIMClient().getConversationList(this.mCallback);
            }

            TextView mEmptyView = (TextView) this.mList.getEmptyView();
            mEmptyView.setText(RongContext.getInstance().getResources().getString(string.rc_conversation_list_empty_prompt));
            this.isShowWithoutConnected = false;
        }
    }

    public void onEventMainThread(ReadReceiptEvent event) {
        if (this.mAdapter == null) {
            Log.d(TAG, "the conversation list adapter is null.");
        } else {
            int originalIndex = this.mAdapter.findPosition(event.getMessage().getConversationType(), event.getMessage().getTargetId());
            boolean gatherState = RongContext.getInstance().getConversationGatherState(event.getMessage().getConversationType().getName()).booleanValue();
            if (!gatherState && originalIndex >= 0) {
                UIConversation conversation = (UIConversation) this.mAdapter.getItem(originalIndex);
                ReadReceiptMessage content = (ReadReceiptMessage) event.getMessage().getContent();
                if (content.getLastMessageSendTime() >= conversation.getUIConversationTime() && conversation.getConversationSenderId().equals(RongIMClient.getInstance().getCurrentUserId())) {
                    conversation.setSentStatus(SentStatus.READ);
                    this.mAdapter.getView(originalIndex, this.mList.getChildAt(originalIndex - this.mList.getFirstVisiblePosition()), this.mList);
                }
            }

        }
    }

    public void onEventMainThread(final OnReceiveMessageEvent event) {
        Log.d(TAG, "Receive MessageEvent: id=" + event.getMessage().getTargetId() + ", type=" + event.getMessage().getConversationType());
        if ((this.mSupportConversationList.size() == 0 || this.mSupportConversationList.contains(event.getMessage().getConversationType())) && (this.mSupportConversationList.size() != 0 || event.getMessage().getConversationType() != ConversationType.CHATROOM && event.getMessage().getConversationType() != ConversationType.CUSTOMER_SERVICE)) {
            if (this.mAdapter == null) {
                Log.d(TAG, "the conversation list adapter is null. Cache the received message firstly!!!");
                this.mMessageCache.add(event.getMessage());
            } else {
                int originalIndex = this.mAdapter.findPosition(event.getMessage().getConversationType(), event.getMessage().getTargetId());
                UIConversation uiConversation = this.makeUiConversation(event.getMessage(), originalIndex);
                int newPosition = ConversationListUtils.findPositionForNewConversation(uiConversation, this.mAdapter);
                if (originalIndex < 0) {
                    this.mAdapter.add(uiConversation, newPosition);
                } else if (originalIndex != newPosition) {
                    this.mAdapter.remove(originalIndex);
                    this.mAdapter.add(uiConversation, newPosition);
                }

                this.mAdapter.notifyDataSetChanged();
                MessageTag msgTag = (MessageTag) event.getMessage().getContent().getClass().getAnnotation(MessageTag.class);
                if (msgTag != null && (msgTag.flag() & 3) == 3) {
                    this.refreshUnreadCount(event.getMessage().getConversationType(), event.getMessage().getTargetId());
                }

                if (RongContext.getInstance().getConversationGatherState(event.getMessage().getConversationType().getName()).booleanValue()) {
                    RongIM.getInstance().getRongIMClient().getConversationList(new ResultCallback<List<Conversation>>() {
                        public void onSuccess(List<Conversation> conversations) {
                            Iterator i$ = conversations.iterator();

                            while (true) {
                                if (i$.hasNext()) {
                                    Conversation conv = (Conversation) i$.next();
                                    if (conversations == null || conversations.size() == 0) {
                                        return;
                                    }

                                    if (!conv.getConversationType().equals(event.getMessage().getConversationType()) || !conv.getTargetId().equals(event.getMessage().getTargetId())) {
                                        continue;
                                    }

                                    int pos = ConversationListFragment.this.mAdapter.findPosition(conv.getConversationType(), conv.getTargetId());
                                    if (pos >= 0) {
                                        ((UIConversation) ConversationListFragment.this.mAdapter.getItem(pos)).setDraft(conv.getDraft());
                                        if (TextUtils.isEmpty(conv.getDraft())) {
                                            ((UIConversation) ConversationListFragment.this.mAdapter.getItem(pos)).setSentStatus((SentStatus) null);
                                        } else {
                                            ((UIConversation) ConversationListFragment.this.mAdapter.getItem(pos)).setSentStatus(conv.getSentStatus());
                                        }

                                        ConversationListFragment.this.mAdapter.getView(pos, ConversationListFragment.this.mList.getChildAt(pos - ConversationListFragment.this.mList.getFirstVisiblePosition()), ConversationListFragment.this.mList);
                                    }
                                }

                                return;
                            }
                        }

                        public void onError(ErrorCode e) {
                        }
                    }, new ConversationType[]{event.getMessage().getConversationType()});
                }

            }
        } else {
            Log.e(TAG, "Not included in conversation list. Return directly!");
        }
    }

    public void onEventMainThread(Message message) {
        RLog.d(this, "onEventMainThread", "Receive Message: name=" + message.getObjectName() + ", type=" + message.getConversationType());
        if (this.mSupportConversationList.size() != 0 && !this.mSupportConversationList.contains(message.getConversationType()) || this.mSupportConversationList.size() == 0 && (message.getConversationType() == ConversationType.CHATROOM || message.getConversationType() == ConversationType.CUSTOMER_SERVICE)) {
            RLog.d(this, "onEventBackgroundThread", "Not included in conversation list. Return directly!");
        } else {
            int originalIndex = this.mAdapter.findPosition(message.getConversationType(), message.getTargetId());
            UIConversation uiConversation = this.makeUiConversation(message, originalIndex);
            int newPosition = ConversationListUtils.findPositionForNewConversation(uiConversation, this.mAdapter);
            if (originalIndex >= 0) {
                if (newPosition == originalIndex) {
                    this.mAdapter.getView(newPosition, this.mList.getChildAt(newPosition - this.mList.getFirstVisiblePosition()), this.mList);
                } else {
                    this.mAdapter.remove(originalIndex);
                    this.mAdapter.add(uiConversation, newPosition);
                    this.mAdapter.notifyDataSetChanged();
                }
            } else {
                this.mAdapter.add(uiConversation, newPosition);
                this.mAdapter.notifyDataSetChanged();
            }

        }
    }

    public void onEventMainThread(MessageContent content) {
        RLog.d(this, "onEventMainThread:", "MessageContent");

        for (int index = 0; index < this.mAdapter.getCount(); ++index) {
            UIConversation tempUIConversation = (UIConversation) this.mAdapter.getItem(index);
            if (content != null && tempUIConversation.getMessageContent() != null && tempUIConversation.getMessageContent() == content) {
                tempUIConversation.setMessageContent(content);
                tempUIConversation.setConversationContent(tempUIConversation.buildConversationContent(tempUIConversation));
                if (index >= this.mList.getFirstVisiblePosition()) {
                    this.mAdapter.getView(index, this.mList.getChildAt(index - this.mList.getFirstVisiblePosition()), this.mList);
                }
            } else {
                RLog.e(this, "onEventMainThread", "MessageContent is null");
            }
        }

    }

    public void onEventMainThread(ConnectionStatus status) {
        RLog.d(this, "ConnectionStatus", status.toString());
        if (this.isResumed() && this.getResources().getBoolean(bool.rc_is_show_warning_notification)) {
            Drawable drawable = this.getActivity().getResources().getDrawable(R.drawable.rc_notification_network_available);
            int width = (int) this.getActivity().getResources().getDimension(dimen.rc_message_send_status_image_size);
            drawable.setBounds(0, 0, width, width);
            this.mNotificationBar.setCompoundDrawablePadding(16);
            this.mNotificationBar.setCompoundDrawables(drawable, (Drawable) null, (Drawable) null, (Drawable) null);
            if (status.equals(ConnectionStatus.NETWORK_UNAVAILABLE)) {
                this.mNotificationBar.setVisibility(0);
                this.mNotificationBar.setText(this.getResources().getString(string.rc_notice_network_unavailable));
                RongIM.getInstance().getRongIMClient().reconnect((ConnectCallback) null);
            } else if (status.equals(ConnectionStatus.KICKED_OFFLINE_BY_OTHER_CLIENT)) {
                this.mNotificationBar.setVisibility(0);
                this.mNotificationBar.setText(this.getResources().getString(string.rc_notice_tick));
            } else if (status.equals(ConnectionStatus.CONNECTED)) {
                this.mNotificationBar.setVisibility(8);
            } else if (status.equals(ConnectionStatus.DISCONNECTED)) {
                this.mNotificationBar.setVisibility(0);
                this.mNotificationBar.setText(this.getResources().getString(string.rc_notice_network_unavailable));
            } else if (status.equals(ConnectionStatus.CONNECTING)) {
                this.mNotificationBar.setVisibility(0);
                this.mNotificationBar.setText(this.getResources().getString(string.rc_notice_connecting));
            }
        }

    }

    public void onEventMainThread(CreateDiscussionEvent createDiscussionEvent) {
        RLog.d(this, "onEventBackgroundThread:", "createDiscussionEvent");
        UIConversation conversation = new UIConversation();
        conversation.setConversationType(ConversationType.DISCUSSION);
        if (createDiscussionEvent.getDiscussionName() != null) {
            conversation.setUIConversationTitle(createDiscussionEvent.getDiscussionName());
        } else {
            conversation.setUIConversationTitle("");
        }

        conversation.setConversationTargetId(createDiscussionEvent.getDiscussionId());
        conversation.setUIConversationTime(System.currentTimeMillis());
        boolean isGather = RongContext.getInstance().getConversationGatherState(ConversationType.DISCUSSION.getName()).booleanValue();
        conversation.setConversationGatherState(isGather);
        if (isGather) {
            String gatherPosition = RongContext.getInstance().getGatheredConversationTitle(conversation.getConversationType());
            conversation.setUIConversationTitle(gatherPosition);
        }

        int gatherPosition1 = this.mAdapter.findGatherPosition(ConversationType.DISCUSSION);
        if (gatherPosition1 == -1) {
            this.mAdapter.add(conversation, ConversationListUtils.findPositionForNewConversation(conversation, this.mAdapter));
            this.mAdapter.notifyDataSetChanged();
        }

    }

    public void onEventMainThread(Draft draft) {
        ConversationType curType = ConversationType.setValue(draft.getType().intValue());
        if (curType == null) {
            throw new IllegalArgumentException("the type of the draft is unknown!");
        } else {
            RLog.i(this, "onEventMainThread(draft)", curType.getName());
            int position = this.mAdapter.findPosition(curType, draft.getId());
            if (position >= 0) {
                UIConversation conversation = (UIConversation) this.mAdapter.getItem(position);
                if (conversation.getConversationTargetId().equals(draft.getId())) {
                    conversation.setDraft(draft.getContent());
                    if (!TextUtils.isEmpty(draft.getContent())) {
                        conversation.setSentStatus((SentStatus) null);
                    }

                    this.mAdapter.getView(position, this.mList.getChildAt(position - this.mList.getFirstVisiblePosition()), this.mList);
                }
            }

        }
    }

    public void onEventMainThread(Group groupInfo) {
        int count = this.mAdapter.getCount();
        RLog.d(this, "onEventMainThread", "Group: name=" + groupInfo.getName() + ", id=" + groupInfo.getId());
        if (groupInfo.getName() != null) {
            for (int i = 0; i < count; ++i) {
                UIConversation item = (UIConversation) this.mAdapter.getItem(i);
                if (item != null && item.getConversationType().equals(ConversationType.GROUP) && item.getConversationTargetId().equals(groupInfo.getId())) {
                    boolean gatherState = RongContext.getInstance().getConversationGatherState(item.getConversationType().getName()).booleanValue();
                    if (gatherState) {
                        SpannableStringBuilder builder = new SpannableStringBuilder();
                        Spannable messageData = RongContext.getInstance().getMessageTemplate(item.getMessageContent().getClass()).getContentSummary(item.getMessageContent());
                        if (item.getMessageContent() instanceof VoiceMessage) {
                            boolean isListened = RongIM.getInstance().getRongIMClient().getConversation(item.getConversationType(), item.getConversationTargetId()).getReceivedStatus().isListened();
                            if (isListened) {
                                messageData.setSpan(new ForegroundColorSpan(RongContext.getInstance().getResources().getColor(color.rc_text_color_secondary)), 0, messageData.length(), 33);
                            } else {
                                messageData.setSpan(new ForegroundColorSpan(RongContext.getInstance().getResources().getColor(color.rc_voice_color)), 0, messageData.length(), 33);
                            }
                        }

                        builder.append(groupInfo.getName()).append(" : ").append(messageData);
                        item.setConversationContent(builder);
                        if (groupInfo.getPortraitUri() != null) {
                            item.setIconUrl(groupInfo.getPortraitUri());
                        }
                    } else {
                        item.setUIConversationTitle(groupInfo.getName());
                        if (groupInfo.getPortraitUri() != null) {
                            item.setIconUrl(groupInfo.getPortraitUri());
                        }
                    }

                    this.mAdapter.getView(i, this.mList.getChildAt(i - this.mList.getFirstVisiblePosition()), this.mList);
                }
            }

        }
    }

    public void onEventMainThread(Discussion discussion) {
        int count = this.mAdapter.getCount();
        RLog.d(this, "onEventMainThread", "Discussion: name=" + discussion.getName() + ", id=" + discussion.getId());

        for (int i = 0; i < count; ++i) {
            UIConversation item = (UIConversation) this.mAdapter.getItem(i);
            if (item != null && item.getConversationType().equals(ConversationType.DISCUSSION) && item.getConversationTargetId().equals(discussion.getId())) {
                boolean gatherState = RongContext.getInstance().getConversationGatherState(item.getConversationType().getName()).booleanValue();
                if (gatherState) {
                    SpannableStringBuilder builder = new SpannableStringBuilder();
                    Spannable messageData = RongContext.getInstance().getMessageTemplate(item.getMessageContent().getClass()).getContentSummary(item.getMessageContent());
                    if (messageData != null) {
                        if (item.getMessageContent() instanceof VoiceMessage) {
                            boolean isListened = RongIM.getInstance().getRongIMClient().getConversation(item.getConversationType(), item.getConversationTargetId()).getReceivedStatus().isListened();
                            if (isListened) {
                                messageData.setSpan(new ForegroundColorSpan(RongContext.getInstance().getResources().getColor(color.rc_text_color_secondary)), 0, messageData.length(), 33);
                            } else {
                                messageData.setSpan(new ForegroundColorSpan(RongContext.getInstance().getResources().getColor(color.rc_voice_color)), 0, messageData.length(), 33);
                            }
                        }

                        builder.append(discussion.getName()).append(" : ").append(messageData);
                    } else {
                        builder.append(discussion.getName());
                    }

                    item.setConversationContent(builder);
                } else {
                    item.setUIConversationTitle(discussion.getName());
                }

                this.mAdapter.getView(i, this.mList.getChildAt(i - this.mList.getFirstVisiblePosition()), this.mList);
            }
        }

    }

    public void onEventMainThread(GroupUserInfoEvent event) {
        int count = this.mAdapter.getCount();
        GroupUserInfo userInfo = event.getUserInfo();
        Log.d("qinxiao", "GroupUserInfoEvent: " + userInfo.getUserId());
        if (userInfo != null && userInfo.getNickname() != null) {
            for (int i = 0; i < count; ++i) {
                UIConversation uiConversation = (UIConversation) this.mAdapter.getItem(i);
                ConversationType type = uiConversation.getConversationType();
                boolean gatherState = RongContext.getInstance().getConversationGatherState(uiConversation.getConversationType().getName()).booleanValue();
                boolean isShowName;
                if (uiConversation.getMessageContent() == null) {
                    isShowName = false;
                } else {
                    isShowName = RongContext.getInstance().getMessageProviderTag(uiConversation.getMessageContent().getClass()).showSummaryWithName();
                }

                if (!gatherState && isShowName && type.equals(ConversationType.GROUP) && uiConversation.getConversationSenderId().equals(userInfo.getUserId())) {
                    Spannable messageData = RongContext.getInstance().getMessageTemplate(uiConversation.getMessageContent().getClass()).getContentSummary(uiConversation.getMessageContent());
                    SpannableStringBuilder builder = new SpannableStringBuilder();
                    if (uiConversation.getMessageContent() instanceof VoiceMessage) {
                        boolean isListened = RongIM.getInstance().getRongIMClient().getConversation(uiConversation.getConversationType(), uiConversation.getConversationTargetId()).getReceivedStatus().isListened();
                        if (isListened) {
                            messageData.setSpan(new ForegroundColorSpan(RongContext.getInstance().getResources().getColor(color.rc_text_color_secondary)), 0, messageData.length(), 33);
                        } else {
                            messageData.setSpan(new ForegroundColorSpan(RongContext.getInstance().getResources().getColor(color.rc_voice_color)), 0, messageData.length(), 33);
                        }
                    }

                    if (uiConversation.getConversationTargetId().equals(userInfo.getGroupId())) {
                        uiConversation.addNickname(userInfo.getUserId());
                        builder.append(userInfo.getNickname()).append(" : ").append(messageData);
                        uiConversation.setConversationContent(builder);
                    }

                    this.mAdapter.getView(i, this.mList.getChildAt(i - this.mList.getFirstVisiblePosition()), this.mList);
                }
            }

        }
    }

    public void onEventMainThread(UserInfo userInfo) {
        int count = this.mAdapter.getCount();
        if (userInfo.getName() != null) {
            for (int i = 0; i < count; ++i) {
                UIConversation temp = (UIConversation) this.mAdapter.getItem(i);
                String type = temp.getConversationType().getName();
                boolean gatherState = RongContext.getInstance().getConversationGatherState(temp.getConversationType().getName()).booleanValue();
                if (!temp.hasNickname(userInfo.getUserId())) {
                    boolean isShowName;
                    if (temp.getMessageContent() == null) {
                        isShowName = false;
                    } else {
                        isShowName = RongContext.getInstance().getMessageProviderTag(temp.getMessageContent().getClass()).showSummaryWithName();
                    }

                    Spannable messageData;
                    SpannableStringBuilder builder;
                    boolean isListened;
                    if (!gatherState && isShowName && (type.equals("group") || type.equals("discussion")) && temp.getConversationSenderId().equals(userInfo.getUserId())) {
                        messageData = RongContext.getInstance().getMessageTemplate(temp.getMessageContent().getClass()).getContentSummary(temp.getMessageContent());
                        builder = new SpannableStringBuilder();
                        if (temp.getMessageContent() instanceof VoiceMessage) {
                            isListened = RongIM.getInstance().getRongIMClient().getConversation(temp.getConversationType(), temp.getConversationTargetId()).getReceivedStatus().isListened();
                            if (isListened) {
                                messageData.setSpan(new ForegroundColorSpan(RongContext.getInstance().getResources().getColor(color.rc_text_color_secondary)), 0, messageData.length(), 33);
                            } else {
                                messageData.setSpan(new ForegroundColorSpan(RongContext.getInstance().getResources().getColor(color.rc_voice_color)), 0, messageData.length(), 33);
                            }
                        }

                        builder.append(userInfo.getName()).append(" : ").append(messageData);
                        temp.setConversationContent(builder);
                        this.mAdapter.getView(i, this.mList.getChildAt(i - this.mList.getFirstVisiblePosition()), this.mList);
                    } else if (temp.getConversationTargetId().equals(userInfo.getUserId())) {
                        if (gatherState || type != "private" && type != "system") {
                            if (isShowName) {
                                messageData = RongContext.getInstance().getMessageTemplate(temp.getMessageContent().getClass()).getContentSummary(temp.getMessageContent());
                                builder = new SpannableStringBuilder();
                                if (messageData != null) {
                                    if (temp.getMessageContent() instanceof VoiceMessage) {
                                        isListened = RongIM.getInstance().getRongIMClient().getConversation(temp.getConversationType(), temp.getConversationTargetId()).getReceivedStatus().isListened();
                                        if (isListened) {
                                            messageData.setSpan(new ForegroundColorSpan(RongContext.getInstance().getResources().getColor(color.rc_text_color_secondary)), 0, messageData.length(), 33);
                                        } else {
                                            messageData.setSpan(new ForegroundColorSpan(RongContext.getInstance().getResources().getColor(color.rc_voice_color)), 0, messageData.length(), 33);
                                        }
                                    }

                                    builder.append(userInfo.getName()).append(" : ").append(messageData);
                                } else {
                                    builder.append(userInfo.getName());
                                }

                                temp.setConversationContent(builder);
                                temp.setIconUrl(userInfo.getPortraitUri());
                            }
                        } else {
                            temp.setUIConversationTitle(userInfo.getName());
                            temp.setIconUrl(userInfo.getPortraitUri());
                        }

                        this.mAdapter.getView(i, this.mList.getChildAt(i - this.mList.getFirstVisiblePosition()), this.mList);
                    }
                }
            }

        }
    }

    public void onEventMainThread(PublicServiceProfile accountInfo) {
        int count = this.mAdapter.getCount();
        boolean gatherState = RongContext.getInstance().getConversationGatherState(accountInfo.getConversationType().getName()).booleanValue();

        for (int i = 0; i < count; ++i) {
            if (((UIConversation) this.mAdapter.getItem(i)).getConversationType().equals(accountInfo.getConversationType()) && ((UIConversation) this.mAdapter.getItem(i)).getConversationTargetId().equals(accountInfo.getTargetId()) && !gatherState) {
                ((UIConversation) this.mAdapter.getItem(i)).setUIConversationTitle(accountInfo.getName());
                ((UIConversation) this.mAdapter.getItem(i)).setIconUrl(accountInfo.getPortraitUri());
                this.mAdapter.getView(i, this.mList.getChildAt(i - this.mList.getFirstVisiblePosition()), this.mList);
                break;
            }
        }

    }

    public void onEventMainThread(PublicServiceFollowableEvent event) {
        if (event != null && !event.isFollow()) {
            int originalIndex = this.mAdapter.findPosition(event.getConversationType(), event.getTargetId());
            if (originalIndex >= 0) {
                this.mAdapter.remove(originalIndex);
                this.mAdapter.notifyDataSetChanged();
            }
        }

    }

    public void onEventMainThread(final ConversationUnreadEvent unreadEvent) {
        int targetIndex = this.mAdapter.findPosition(unreadEvent.getType(), unreadEvent.getTargetId());
        RLog.d(this, "onEventMainThread", "ConversationUnreadEvent: name=");
        if (targetIndex >= 0) {
            UIConversation temp = (UIConversation) this.mAdapter.getItem(targetIndex);
            boolean gatherState = temp.getConversationGatherState();
            if (gatherState) {
                RongIM.getInstance().getRongIMClient().getUnreadCount(new ResultCallback<Integer>() {
                    public void onSuccess(Integer count) {
                        int pos = ConversationListFragment.this.mAdapter.findPosition(unreadEvent.getType(), unreadEvent.getTargetId());
                        if (pos >= 0) {
                            ((UIConversation) ConversationListFragment.this.mAdapter.getItem(pos)).setUnReadMessageCount(count.intValue());
                            ConversationListFragment.this.mAdapter.getView(pos, ConversationListFragment.this.mList.getChildAt(pos - ConversationListFragment.this.mList.getFirstVisiblePosition()), ConversationListFragment.this.mList);
                        }

                    }

                    public void onError(ErrorCode e) {
                        System.err.print("Throw exception when get unread message count from ipc remote side!");
                    }
                }, new ConversationType[]{unreadEvent.getType()});
            } else {
                temp.setUnReadMessageCount(0);
                RLog.d(this, "onEventMainThread", "ConversationUnreadEvent: set unRead count to be 0");
                this.mAdapter.getView(targetIndex, this.mList.getChildAt(targetIndex - this.mList.getFirstVisiblePosition()), this.mList);
            }
        }

    }

    public void onEventMainThread(final ConversationTopEvent setTopEvent) throws IllegalAccessException {
        int originalIndex = this.mAdapter.findPosition(setTopEvent.getConversationType(), setTopEvent.getTargetId());
        if (originalIndex >= 0) {
            UIConversation temp = (UIConversation) this.mAdapter.getItem(originalIndex);
            boolean originalValue = temp.isTop();
            if (originalValue != setTopEvent.isTop()) {
                if (temp.getConversationGatherState()) {
                    RongIM.getInstance().getRongIMClient().getConversationList(new ResultCallback<List<Conversation>>() {
                        public void onSuccess(List<Conversation> conversations) {
                            if (conversations != null && conversations.size() != 0) {
                                UIConversation newConversation = ConversationListFragment.this.makeUIConversationFromList(conversations);
                                int pos = ConversationListFragment.this.mAdapter.findPosition(setTopEvent.getConversationType(), setTopEvent.getTargetId());
                                if (pos >= 0) {
                                    ConversationListFragment.this.mAdapter.remove(pos);
                                }

                                int newIndex = ConversationListUtils.findPositionForNewConversation(newConversation, ConversationListFragment.this.mAdapter);
                                ConversationListFragment.this.mAdapter.add(newConversation, newIndex);
                                ConversationListFragment.this.mAdapter.notifyDataSetChanged();
                            }
                        }

                        public void onError(ErrorCode e) {
                        }
                    }, new ConversationType[]{temp.getConversationType()});
                } else {
                    int newIndex;
                    if (originalValue) {
                        temp.setTop(false);
                        newIndex = ConversationListUtils.findPositionForCancleTop(originalIndex, this.mAdapter);
                    } else {
                        temp.setTop(true);
                        newIndex = ConversationListUtils.findPositionForSetTop(temp, this.mAdapter);
                    }

                    if (originalIndex == newIndex) {
                        this.mAdapter.getView(newIndex, this.mList.getChildAt(newIndex - this.mList.getFirstVisiblePosition()), this.mList);
                    } else {
                        this.mAdapter.remove(originalIndex);
                        this.mAdapter.add(temp, newIndex);
                        this.mAdapter.notifyDataSetChanged();
                    }
                }

            }
        } else {
            throw new IllegalAccessException("the item has already been deleted!");
        }
    }

    public void onEventMainThread(final ConversationRemoveEvent removeEvent) {
        int removedIndex = this.mAdapter.findPosition(removeEvent.getType(), removeEvent.getTargetId());
        boolean gatherState = RongContext.getInstance().getConversationGatherState(removeEvent.getType().getName()).booleanValue();
        if (!gatherState) {
            if (removedIndex >= 0) {
                this.mAdapter.remove(removedIndex);
                this.mAdapter.notifyDataSetChanged();
            }
        } else if (removedIndex >= 0) {
            RongIM.getInstance().getRongIMClient().getConversationList(new ResultCallback<List<Conversation>>() {
                public void onSuccess(List<Conversation> conversationList) {
                    int oldPos = ConversationListFragment.this.mAdapter.findPosition(removeEvent.getType(), removeEvent.getTargetId());
                    if (conversationList != null && conversationList.size() != 0) {
                        UIConversation newConversation = ConversationListFragment.this.makeUIConversationFromList(conversationList);
                        if (oldPos >= 0) {
                            ConversationListFragment.this.mAdapter.remove(oldPos);
                        }

                        int newIndex = ConversationListUtils.findPositionForNewConversation(newConversation, ConversationListFragment.this.mAdapter);
                        ConversationListFragment.this.mAdapter.add(newConversation, newIndex);
                        ConversationListFragment.this.mAdapter.notifyDataSetChanged();
                    } else {
                        if (oldPos >= 0) {
                            ConversationListFragment.this.mAdapter.remove(oldPos);
                        }

                        ConversationListFragment.this.mAdapter.notifyDataSetChanged();
                    }
                }

                public void onError(ErrorCode e) {
                }
            }, new ConversationType[]{removeEvent.getType()});
        }

    }

    public void onEventMainThread(MessageDeleteEvent event) {
        int count = this.mAdapter.getCount();

        for (int i = 0; i < count; ++i) {
            if (event.getMessageIds().contains(Integer.valueOf(((UIConversation) this.mAdapter.getItem(i)).getLatestMessageId()))) {
                boolean gatherState = ((UIConversation) this.mAdapter.getItem(i)).getConversationGatherState();
                if (gatherState) {
                    RongIM.getInstance().getRongIMClient().getConversationList(new ResultCallback<List<Conversation>>() {
                        public void onSuccess(List<Conversation> conversationList) {
                            if (conversationList != null && conversationList.size() != 0) {
                                UIConversation uiConversation = ConversationListFragment.this.makeUIConversationFromList(conversationList);
                                int oldPos = ConversationListFragment.this.mAdapter.findPosition(uiConversation.getConversationType(), uiConversation.getConversationTargetId());
                                if (oldPos >= 0) {
                                    ConversationListFragment.this.mAdapter.remove(oldPos);
                                }

                                int newIndex = ConversationListUtils.findPositionForNewConversation(uiConversation, ConversationListFragment.this.mAdapter);
                                ConversationListFragment.this.mAdapter.add(uiConversation, newIndex);
                                ConversationListFragment.this.mAdapter.notifyDataSetChanged();
                            }
                        }

                        public void onError(ErrorCode e) {
                        }
                    }, new ConversationType[]{((UIConversation) this.mAdapter.getItem(i)).getConversationType()});
                } else {
                    RongIM.getInstance().getRongIMClient().getConversation(((UIConversation) this.mAdapter.getItem(i)).getConversationType(), ((UIConversation) this.mAdapter.getItem(i)).getConversationTargetId(), new ResultCallback<Conversation>() {
                        public void onSuccess(Conversation conversation) {
                            if (conversation == null) {
                                RLog.d(this, "onEventMainThread", "getConversation : onSuccess, conversation = null");
                            } else {
                                UIConversation temp = UIConversation.obtain(conversation, false);
                                int pos = ConversationListFragment.this.mAdapter.findPosition(conversation.getConversationType(), conversation.getTargetId());
                                if (pos >= 0) {
                                    ConversationListFragment.this.mAdapter.remove(pos);
                                }

                                int newPosition = ConversationListUtils.findPositionForNewConversation(temp, ConversationListFragment.this.mAdapter);
                                ConversationListFragment.this.mAdapter.add(temp, newPosition);
                                ConversationListFragment.this.mAdapter.notifyDataSetChanged();
                            }
                        }

                        public void onError(ErrorCode e) {
                        }
                    });
                }
                break;
            }
        }

    }

    public void onEventMainThread(ConversationNotificationEvent notificationEvent) {
        int originalIndex = this.mAdapter.findPosition(notificationEvent.getConversationType(), notificationEvent.getTargetId());
        if (originalIndex >= 0) {
            this.mAdapter.getView(originalIndex, this.mList.getChildAt(originalIndex - this.mList.getFirstVisiblePosition()), this.mList);
        }

    }

    public void onEventMainThread(MessagesClearEvent clearMessagesEvent) {
        int originalIndex = this.mAdapter.findPosition(clearMessagesEvent.getType(), clearMessagesEvent.getTargetId());
        if (originalIndex >= 0) {
            boolean gatherState = RongContext.getInstance().getConversationGatherState(clearMessagesEvent.getType().getName()).booleanValue();
            if (gatherState) {
                RongIM.getInstance().getRongIMClient().getConversationList(new ResultCallback<List<Conversation>>() {
                    public void onSuccess(List<Conversation> conversationList) {
                        if (conversationList != null && conversationList.size() != 0) {
                            UIConversation uiConversation = ConversationListFragment.this.makeUIConversationFromList(conversationList);
                            int pos = ConversationListFragment.this.mAdapter.findPosition(uiConversation.getConversationType(), uiConversation.getConversationTargetId());
                            if (pos >= 0) {
                                ConversationListFragment.this.mAdapter.remove(pos);
                            }

                            int newIndex = ConversationListUtils.findPositionForNewConversation(uiConversation, ConversationListFragment.this.mAdapter);
                            ConversationListFragment.this.mAdapter.add(uiConversation, newIndex);
                            ConversationListFragment.this.mAdapter.notifyDataSetChanged();
                        }
                    }

                    public void onError(ErrorCode e) {
                    }
                }, new ConversationType[]{ConversationType.GROUP});
            } else {
                RongIMClient.getInstance().getConversation(clearMessagesEvent.getType(), clearMessagesEvent.getTargetId(), new ResultCallback<Conversation>() {
                    public void onSuccess(Conversation conversation) {
                        UIConversation uiConversation = UIConversation.obtain(conversation, false);
                        int pos = ConversationListFragment.this.mAdapter.findPosition(conversation.getConversationType(), conversation.getTargetId());
                        if (pos >= 0) {
                            ConversationListFragment.this.mAdapter.remove(pos);
                        }

                        int newPos = ConversationListUtils.findPositionForNewConversation(uiConversation, ConversationListFragment.this.mAdapter);
                        ConversationListFragment.this.mAdapter.add(uiConversation, newPos);
                        ConversationListFragment.this.mAdapter.notifyDataSetChanged();
                    }

                    public void onError(ErrorCode e) {
                    }
                });
            }
        }

    }

    public void onEventMainThread(OnMessageSendErrorEvent sendErrorEvent) {
        int index = this.mAdapter.findPosition(sendErrorEvent.getMessage().getConversationType(), sendErrorEvent.getMessage().getTargetId());
        if (index >= 0) {
            UIConversation temp = (UIConversation) this.mAdapter.getItem(index);
            temp.setUIConversationTime(sendErrorEvent.getMessage().getSentTime());
            temp.setMessageContent(sendErrorEvent.getMessage().getContent());
            temp.setConversationContent(temp.buildConversationContent(temp));
            temp.setSentStatus(SentStatus.FAILED);
            this.mAdapter.remove(index);
            int newPosition = ConversationListUtils.findPositionForNewConversation(temp, this.mAdapter);
            this.mAdapter.add(temp, newPosition);
            this.mAdapter.notifyDataSetChanged();
        }

    }

    public void onEventMainThread(QuitDiscussionEvent event) {
        int index = this.mAdapter.findPosition(ConversationType.DISCUSSION, event.getDiscussionId());
        if (index >= 0) {
            this.mAdapter.remove(index);
            this.mAdapter.notifyDataSetChanged();
        }

    }

    public void onEventMainThread(QuitGroupEvent event) {
        final int index = this.mAdapter.findPosition(ConversationType.GROUP, event.getGroupId());
        boolean gatherState = RongContext.getInstance().getConversationGatherState(ConversationType.GROUP.getName()).booleanValue();
        if (index >= 0 && gatherState) {
            RongIM.getInstance().getRongIMClient().getConversationList(new ResultCallback<List<Conversation>>() {
                public void onSuccess(List<Conversation> conversationList) {
                    if (conversationList != null && conversationList.size() != 0) {
                        UIConversation uiConversation = ConversationListFragment.this.makeUIConversationFromList(conversationList);
                        int pos = ConversationListFragment.this.mAdapter.findPosition(uiConversation.getConversationType(), uiConversation.getConversationTargetId());
                        if (pos >= 0) {
                            ConversationListFragment.this.mAdapter.remove(pos);
                        }

                        int newIndex = ConversationListUtils.findPositionForNewConversation(uiConversation, ConversationListFragment.this.mAdapter);
                        ConversationListFragment.this.mAdapter.add(uiConversation, newIndex);
                        ConversationListFragment.this.mAdapter.notifyDataSetChanged();
                    } else {
                        if (index >= 0) {
                            ConversationListFragment.this.mAdapter.remove(index);
                        }

                        ConversationListFragment.this.mAdapter.notifyDataSetChanged();
                    }
                }

                public void onError(ErrorCode e) {
                }
            }, new ConversationType[]{ConversationType.GROUP});
        } else if (index >= 0) {
            this.mAdapter.remove(index);
            this.mAdapter.notifyDataSetChanged();
        }

    }

    public void onEventMainThread(MessageListenedEvent event) {
        int originalIndex = this.mAdapter.findPosition(event.getConversationType(), event.getTargetId());
        if (originalIndex >= 0) {
            UIConversation temp = (UIConversation) this.mAdapter.getItem(originalIndex);
            if (temp.getLatestMessageId() == event.getLatestMessageId()) {
                temp.setConversationContent(temp.buildConversationContent(temp));
            }

            this.mAdapter.getView(originalIndex, this.mList.getChildAt(originalIndex - this.mList.getFirstVisiblePosition()), this.mList);
        }

    }

    public void onPortraitItemClick(View v, UIConversation data) {
        ConversationType type = data.getConversationType();
        if (RongContext.getInstance().getConversationGatherState(type.getName()).booleanValue()) {
            RongIM.getInstance().startSubConversationList(this.getActivity(), type);
        } else {
            if (RongContext.getInstance().getConversationListBehaviorListener() != null) {
                boolean isDefault = RongContext.getInstance().getConversationListBehaviorListener().onConversationPortraitClick(this.getActivity(), type, data.getConversationTargetId());
                if (isDefault) {
                    return;
                }
            }

            data.setUnReadMessageCount(0);
            RongIM.getInstance().startConversation(this.getActivity(), type, data.getConversationTargetId(), data.getUIConversationTitle());
        }

    }

    public boolean onPortraitItemLongClick(View v, UIConversation data) {
        ConversationType type = data.getConversationType();
        if (RongContext.getInstance().getConversationListBehaviorListener() != null) {
            boolean isDealt = RongContext.getInstance().getConversationListBehaviorListener().onConversationPortraitLongClick(this.getActivity(), type, data.getConversationTargetId());
            if (isDealt) {
                return true;
            }
        }

        if (!RongContext.getInstance().getConversationGatherState(type.getName()).booleanValue()) {
            this.buildMultiDialog(data);
            return true;
        } else {
            this.buildSingleDialog(data);
            return true;
        }
    }

    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        UIConversation uiconversation = (UIConversation) parent.getAdapter().getItem(position);
        ConversationType type = uiconversation.getConversationType();
        if (RongContext.getInstance().getConversationGatherState(type.getName()).booleanValue()) {
//            RongIM.getInstance().startSubConversationList(this.getActivity(), type);
            Helper.startActivity(getContext(), FrgQzList.class, TitleAct.class);
        } else {
            if (RongContext.getInstance().getConversationListBehaviorListener() != null) {
                boolean isDefault = RongContext.getInstance().getConversationListBehaviorListener().onConversationClick(this.getActivity(), view, uiconversation);
                if (isDefault) {
                    return;
                }
            }

            uiconversation.setUnReadMessageCount(0);
            RongIM.getInstance().startConversation(this.getActivity(), type, uiconversation.getConversationTargetId(), uiconversation.getUIConversationTitle());
        }

    }

    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        UIConversation uiConversation = (UIConversation) this.mAdapter.getItem(position);
        String type = uiConversation.getConversationType().getName();
        if (RongContext.getInstance().getConversationListBehaviorListener() != null) {
            boolean isDealt = RongContext.getInstance().getConversationListBehaviorListener().onConversationLongClick(this.getActivity(), view, uiConversation);
            if (isDealt) {
                return true;
            }
        }

        if (!RongContext.getInstance().getConversationGatherState(type).booleanValue()) {
            this.buildMultiDialog(uiConversation);
            return true;
        } else {
            this.buildSingleDialog(uiConversation);
            return true;
        }
    }

    private void buildMultiDialog(final UIConversation uiConversation) {
        String[] items = new String[2];
        if (uiConversation.isTop()) {
            items[0] = RongContext.getInstance().getString(string.rc_conversation_list_dialog_cancel_top);
        } else {
            items[0] = RongContext.getInstance().getString(string.rc_conversation_list_dialog_set_top);
        }

        items[1] = RongContext.getInstance().getString(string.rc_conversation_list_dialog_remove);
        ArraysDialogFragment.newInstance(uiConversation.getUIConversationTitle(), items).setArraysDialogItemListener(new OnArraysDialogItemListener() {
            public void OnArraysDialogItemClick(DialogInterface dialog, int which) {
                if (which == 0) {
                    RongIM.getInstance().getRongIMClient().setConversationToTop(uiConversation.getConversationType(), uiConversation.getConversationTargetId(), !uiConversation.isTop(), new ResultCallback<Boolean>() {
                        public void onSuccess(Boolean aBoolean) {
                            if (uiConversation.isTop()) {
                                Toast.makeText(RongContext.getInstance(), ConversationListFragment.this.getString(string.rc_conversation_list_popup_cancel_top), 0).show();
                            } else {
                                Toast.makeText(RongContext.getInstance(), ConversationListFragment.this.getString(string.rc_conversation_list_dialog_set_top), 0).show();
                            }

                        }

                        public void onError(ErrorCode e) {
                        }
                    });
                } else if (which == 1) {
                    RongIM.getInstance().getRongIMClient().removeConversation(uiConversation.getConversationType(), uiConversation.getConversationTargetId());
                }

            }
        }).show(this.getFragmentManager());
    }

    private void buildSingleDialog(final UIConversation uiConversation) {
        String[] items = new String[]{RongContext.getInstance().getString(string.rc_conversation_list_dialog_remove)};
        ArraysDialogFragment.newInstance(uiConversation.getUIConversationTitle(), items).setArraysDialogItemListener(new OnArraysDialogItemListener() {
            public void OnArraysDialogItemClick(DialogInterface dialog, int which) {
                RongIM.getInstance().getRongIMClient().getConversationList(new ResultCallback<List<Conversation>>() {
                    public void onSuccess(List<Conversation> conversations) {
                        if (conversations != null && conversations.size() != 0) {
                            Iterator i$ = conversations.iterator();

                            while (i$.hasNext()) {
                                Conversation conversation = (Conversation) i$.next();
                                RongIM.getInstance().getRongIMClient().removeConversation(conversation.getConversationType(), conversation.getTargetId());
                            }

                        }
                    }

                    public void onError(ErrorCode errorCode) {
                    }
                }, new ConversationType[]{uiConversation.getConversationType()});
            }
        }).show(this.getFragmentManager());
    }

    private void makeUiConversationList(List<Conversation> conversationList) {
        UIConversation uiCon;
        for (Iterator i$ = conversationList.iterator(); i$.hasNext(); this.refreshUnreadCount(uiCon.getConversationType(), uiCon.getConversationTargetId())) {
            Conversation conversation = (Conversation) i$.next();
            ConversationType conversationType = conversation.getConversationType();
            boolean gatherState = RongContext.getInstance().getConversationGatherState(conversationType.getName()).booleanValue();
            int originalIndex = this.mAdapter.findPosition(conversationType, conversation.getTargetId());
            uiCon = UIConversation.obtain(conversation, gatherState);
            if (originalIndex < 0) {
                this.mAdapter.add(uiCon);
            }
        }

    }

    private UIConversation makeUiConversation(Message message, int pos) {
        UIConversation uiConversation;
        if (pos >= 0) {
            uiConversation = (UIConversation) this.mAdapter.getItem(pos);
            if (uiConversation != null) {
                uiConversation.setMessageContent(message.getContent());
                if (message.getMessageDirection() == MessageDirection.SEND) {
                    uiConversation.setUIConversationTime(message.getSentTime());
                    if (RongIM.getInstance() != null && RongIM.getInstance().getRongIMClient() != null) {
                        uiConversation.setConversationSenderId(RongIM.getInstance().getRongIMClient().getCurrentUserId());
                    }
                } else {
                    uiConversation.setUIConversationTime(message.getSentTime());
                    uiConversation.setConversationSenderId(message.getSenderUserId());
                }

                uiConversation.setConversationTargetId(message.getTargetId());
                uiConversation.setConversationContent(uiConversation.buildConversationContent(uiConversation));
                uiConversation.setSentStatus(message.getSentStatus());
                uiConversation.setLatestMessageId(message.getMessageId());
            }
        } else {
            uiConversation = UIConversation.obtain(message, RongContext.getInstance().getConversationGatherState(message.getConversationType().getName()).booleanValue());
        }

        return uiConversation;
    }

    private UIConversation makeUIConversationFromList(List<Conversation> conversations) {
        int unreadCount = 0;
        boolean topFlag = false;
        Conversation newest = (Conversation) conversations.get(0);

        Conversation conversation;
        for (Iterator uiConversation = conversations.iterator(); uiConversation.hasNext(); unreadCount += conversation.getUnreadMessageCount()) {
            conversation = (Conversation) uiConversation.next();
            if (newest.isTop()) {
                if (conversation.isTop() && conversation.getSentTime() > newest.getSentTime()) {
                    newest = conversation;
                }
            } else if (conversation.isTop() || conversation.getSentTime() > newest.getSentTime()) {
                newest = conversation;
            }

            if (conversation.isTop()) {
                topFlag = true;
            }
        }

        UIConversation uiConversation1 = UIConversation.obtain(newest, RongContext.getInstance().getConversationGatherState(newest.getConversationType().getName()).booleanValue());
        uiConversation1.setUnReadMessageCount(unreadCount);
        uiConversation1.setTop(topFlag);
        return uiConversation1;
    }

    private void refreshUnreadCount(final ConversationType type, final String targetId) {
        if (RongIM.getInstance() != null && RongIM.getInstance().getRongIMClient() != null) {
            if (RongContext.getInstance().getConversationGatherState(type.getName()).booleanValue()) {
                RongIM.getInstance().getRongIMClient().getUnreadCount(new ResultCallback<Integer>() {
                    public void onSuccess(Integer count) {
                        int curPos = ConversationListFragment.this.mAdapter.findPosition(type, targetId);
                        if (curPos >= 0) {
                            ((UIConversation) ConversationListFragment.this.mAdapter.getItem(curPos)).setUnReadMessageCount(count.intValue());
                            ConversationListFragment.this.mAdapter.getView(curPos, ConversationListFragment.this.mList.getChildAt(curPos - ConversationListFragment.this.mList.getFirstVisiblePosition()), ConversationListFragment.this.mList);
                        }

                    }

                    public void onError(ErrorCode e) {
                        System.err.print("Throw exception when get unread message count from ipc remote side!");
                    }
                }, new ConversationType[]{type});
            } else {
                RongIM.getInstance().getRongIMClient().getUnreadCount(type, targetId, new ResultCallback<Integer>() {
                    public void onSuccess(Integer integer) {
                        int curPos = ConversationListFragment.this.mAdapter.findPosition(type, targetId);
                        if (curPos >= 0) {
                            ((UIConversation) ConversationListFragment.this.mAdapter.getItem(curPos)).setUnReadMessageCount(integer.intValue());
                            ConversationListFragment.this.mAdapter.getView(curPos, ConversationListFragment.this.mList.getChildAt(curPos - ConversationListFragment.this.mList.getFirstVisiblePosition()), ConversationListFragment.this.mList);
                        }

                    }

                    public void onError(ErrorCode e) {
                    }
                });
            }
        }

    }
}
