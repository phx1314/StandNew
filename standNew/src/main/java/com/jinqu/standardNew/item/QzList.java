//
//  QzList
//
//  Created by DELL on 2018-05-17 16:59:07
//  Copyright (c) DELL All rights reserved.


/**

 */

package com.jinqu.standardNew.item;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ab.util.AbDateUtil;
import com.jinqu.standardNew.R;
import com.jinqu.standardNew.model.ModelQunList;
import com.jinqu.standardNew.model.ModelUsreLogin;
import com.jinqu.standardNew.util.UtilDate;
import com.mdx.framework.commons.ParamsManager;
import com.mdx.framework.config.BaseConfig;
import com.mdx.framework.widget.MImageView;

import io.rong.app.message.LocationMessage;
import io.rong.imkit.util.AndroidEmoji;
import io.rong.imlib.model.MessageContent;
import io.rong.message.ImageMessage;
import io.rong.message.RichContentMessage;
import io.rong.message.TextMessage;
import io.rong.message.VoiceMessage;

import static com.ab.util.AbDateUtil.dateFormatYMDHMS;
import static com.jinqu.standardNew.F.mModelUsreLogin;
import static com.jinqu.standardNew.F.setImage;
import static io.rong.app.RongCloudEvent.rid2UserId;


public class QzList extends BaseItem {


    public MImageView mMImageView;
    public TextView mTextView;
    public TextView mTextView_time;
    public TextView mTextView_content;
    public TextView mTextView_count;
    public LinearLayout mLinearLayout_count;

    @SuppressLint("InflateParams")
    public static View getView(Context context, ViewGroup parent) {
        LayoutInflater flater = LayoutInflater.from(context);
        View convertView = flater.inflate(R.layout.item_qz_list, null);
        convertView.setTag(new QzList(convertView));
        return convertView;
    }

    public QzList(View view) {
        this.contentview = view;
        this.context = contentview.getContext();
        initView();
    }

    private void initView() {
        this.contentview.setTag(this);
        findVMethod();
    }

    private void findVMethod() {


        mMImageView = (MImageView) findViewById(R.id.mMImageView);
        mTextView = (TextView) findViewById(R.id.mTextView);
        mTextView_time = (TextView) findViewById(R.id.mTextView_time);
        mTextView_content = (TextView) findViewById(R.id.mTextView_content);
        mTextView_count = (TextView) findViewById(R.id.mTextView_count);
        mLinearLayout_count = (LinearLayout) findViewById(R.id.mLinearLayout_count);
        mMImageView.setCircle(true);
    }

    public void set(ModelQunList.RowsBean item) {
        mTextView.setText(item.GroupName);
        if (!TextUtils.isEmpty(item.HeadImage)) {
            mMImageView.setObj(BaseConfig.getUri() + ParamsManager.get("PortraitUrlGroupNew") + item.HeadImage);
        } else {
            setImage(mMImageView, item.GroupName.substring(0, 1));
        }

        if (item.mConversation != null) {
            mTextView_count.setText(item.mConversation.getUnreadMessageCount() + "");
            mTextView_count.setVisibility(item.mConversation.getUnreadMessageCount() > 0 ? View.VISIBLE : View.GONE);
            mTextView_content.setText(AndroidEmoji.ensure(doMessage(item.mConversation.getSenderUserId(), item.mConversation.getLatestMessage())));
            try {
                mTextView_time.setText(AbDateUtil.formatDateStr2Desc(AbDateUtil.getStringByFormat(UtilDate.longToDate(item.mConversation.getSentTime(), dateFormatYMDHMS), dateFormatYMDHMS)));
            } catch (Exception e) {
                e.printStackTrace();
            }
            mLinearLayout_count.setVisibility(View.VISIBLE);
            mTextView_time.setVisibility(View.VISIBLE);
        } else {
            mLinearLayout_count.setVisibility(View.GONE);
            mTextView_time.setVisibility(View.GONE);
        }
    }

    public String doMessage(String uid, MessageContent messageContent) {
        String uname = "匿名";
        for (ModelUsreLogin.BaseEmployeeBean item : mModelUsreLogin.BaseEmployee) {
            if (rid2UserId(uid).equals(item.EmpID + "")) {
                uname = item.EmpName;
                break;
            }
        }
        if (messageContent instanceof TextMessage) {// 文本消息
            TextMessage textMessage = (TextMessage) messageContent;
            textMessage.getExtra();
            return uname + ":" + textMessage.getContent();
        } else if (messageContent instanceof ImageMessage) {// 图片消息
            ImageMessage imageMessage = (ImageMessage) messageContent;
            return uname + ":" + "[图片]";
        } else if (messageContent instanceof VoiceMessage) {// 语音消息
            VoiceMessage voiceMessage = (VoiceMessage) messageContent;
            return uname + ":" + "[语音]";
        } else if (messageContent instanceof RichContentMessage) {// 图文消息
            RichContentMessage richContentMessage = (RichContentMessage) messageContent;
            return uname + ":" + "[图文]";
        } else if (messageContent instanceof LocationMessage) {// 地址消息
            LocationMessage mLocationMessage = (LocationMessage) messageContent;
            return uname + ":" + "[地址]";
        } else {
            return "";
        }
    }


}