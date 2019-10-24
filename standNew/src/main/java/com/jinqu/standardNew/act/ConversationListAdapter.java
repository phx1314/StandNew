package com.jinqu.standardNew.act;//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sea_monster.resource.Resource;

import io.rong.imkit.AsyncImageView;
import io.rong.imkit.R.drawable;
import io.rong.imkit.R.id;
import io.rong.imkit.R.layout;
import io.rong.imkit.R.string;
import io.rong.imkit.RLog;
import io.rong.imkit.RongContext;
import io.rong.imkit.model.ConversationProviderTag;
import io.rong.imkit.model.UIConversation;
import io.rong.imkit.model.UIConversation.UnreadRemindType;
import io.rong.imkit.widget.ProviderContainerView;
import io.rong.imkit.widget.adapter.BaseAdapter;
import io.rong.imkit.widget.provider.IContainerItemProvider.ConversationProvider;
import io.rong.imlib.model.Conversation.ConversationType;

import static com.jinqu.standardNew.F.setImage;

public class ConversationListAdapter extends BaseAdapter<UIConversation> {
    LayoutInflater mInflater;
    Context mContext;
    private ConversationListAdapter.OnPortraitItemClick mOnPortraitItemClick;

    public long getItemId(int position) {
        UIConversation conversation = (UIConversation) this.getItem(position);
        return conversation == null ? 0L : (long) conversation.hashCode();
    }

    public ConversationListAdapter(Context context) {
        this.mContext = context;
        this.mInflater = LayoutInflater.from(this.mContext);
    }

    public int findGatherPosition(ConversationType type) {
        int index = this.getCount();
        int position = -1;
        if (RongContext.getInstance().getConversationGatherState(type.getName()).booleanValue()) {
            while (index-- > 0) {
                if (((UIConversation) this.getItem(index)).getConversationType().equals(type)) {
                    position = index;
                    break;
                }
            }
        }

        return position;
    }

    public int findPosition(ConversationType type, String targetId) {
        int index = this.getCount();
        int position = -1;
        if (RongContext.getInstance().getConversationGatherState(type.getName()).booleanValue()) {
            while (index-- > 0) {
                if (((UIConversation) this.getItem(index)).getConversationType().equals(type)) {
                    position = index;
                    break;
                }
            }
        } else {
            while (index-- > 0) {
                if (((UIConversation) this.getItem(index)).getConversationType().equals(type) && ((UIConversation) this.getItem(index)).getConversationTargetId().equals(targetId)) {
                    position = index;
                    break;
                }
            }
        }

        return position;
    }

    protected View newView(Context context, int position, ViewGroup group) {
        View result = this.mInflater.inflate(layout.rc_item_conversation, (ViewGroup) null);
        ConversationListAdapter.ViewHolder holder = new ConversationListAdapter.ViewHolder();
        holder.layout = this.findViewById(result, id.rc_item_conversation);
        holder.leftImageLayout = this.findViewById(result, id.rc_item1);
        holder.rightImageLayout = this.findViewById(result, id.rc_item2);
        holder.leftImageView = (AsyncImageView) this.findViewById(result, id.rc_left);
        holder.rightImageView = (AsyncImageView) this.findViewById(result, id.rc_right);
        holder.contentView = (ProviderContainerView) this.findViewById(result, id.rc_content);
        holder.unReadMsgCount = (TextView) this.findViewById(result, id.rc_unread_message);
        holder.unReadMsgCountRight = (TextView) this.findViewById(result, id.rc_unread_message_right);
        holder.unReadMsgCountIcon = (ImageView) this.findViewById(result, id.rc_unread_message_icon);
        holder.unReadMsgCountRightIcon = (ImageView) this.findViewById(result, id.rc_unread_message_icon_right);
        result.setTag(holder);
        return result;
    }

    protected void bindView(View v, int position, final UIConversation data) {
        ConversationListAdapter.ViewHolder holder = (ConversationListAdapter.ViewHolder) v.getTag();
        if (data != null) {
            ConversationProvider provider = RongContext.getInstance().getConversationTemplate(data.getConversationType().getName());
            View view = holder.contentView.inflate(provider);
            provider.bindView(view, position, data);
            if (data.isTop()) {
                holder.layout.setBackgroundDrawable(this.mContext.getResources().getDrawable(drawable.rc_item_top_list_selector));
            } else {
                holder.layout.setBackgroundDrawable(this.mContext.getResources().getDrawable(drawable.rc_item_list_selector));
            }

            ConversationProviderTag tag = RongContext.getInstance().getConversationProviderTag(data.getConversationType().getName());
            if (tag.portraitPosition() == 1) {
                holder.leftImageLayout.setVisibility(0);
                if (data.getConversationType().equals(ConversationType.GROUP)) {
                    holder.leftImageView.setDefaultDrawable(v.getContext().getResources().getDrawable(drawable.rc_default_group_portrait));
                } else if (data.getConversationType().equals(ConversationType.DISCUSSION)) {
                    holder.leftImageView.setDefaultDrawable(v.getContext().getResources().getDrawable(drawable.rc_default_discussion_portrait));
                } else {
//                    holder.leftImageView.setDefaultDrawable(v.getContext().getResources().getDrawable(drawable.rc_default_portrait));
                }

                holder.leftImageLayout.setOnClickListener(new OnClickListener() {
                    public void onClick(View v) {
                        if (ConversationListAdapter.this.mOnPortraitItemClick != null) {
                            ConversationListAdapter.this.mOnPortraitItemClick.onPortraitItemClick(v, data);
                        }

                    }
                });
                holder.leftImageLayout.setOnLongClickListener(new OnLongClickListener() {
                    public boolean onLongClick(View v) {
                        if (ConversationListAdapter.this.mOnPortraitItemClick != null) {
                            ConversationListAdapter.this.mOnPortraitItemClick.onPortraitItemLongClick(v, data);
                        }

                        return true;
                    }
                });
                if (data.getIconUrl() != null) {
                    if (data.getIconUrl().toString().contains("gid=0") && data.getConversationType().equals(ConversationType.PRIVATE)) {
                        setImage(holder.leftImageView, data.getUIConversationTitle().substring(0, 1));
                        holder.leftImageView.setCircle(true);
                    } else {
//                        holder.leftImageView.setResource(new Resource(data.getIconUrl()));
                        holder.leftImageView.setObj(data.getIconUrl().toString() );
                        holder.leftImageView.setCircle(true);
                    }

                } else {
                    holder.leftImageView.setResource((Resource) null);
                }

                RLog.d(this, "bindView", "getUnReadMessageCount=" + data.getUnReadMessageCount());
                if (data.getUnReadMessageCount() > 0) {
                    holder.unReadMsgCountIcon.setVisibility(0);
                    if (data.getUnReadType().equals(UnreadRemindType.REMIND_WITH_COUNTING)) {
                        if (data.getUnReadMessageCount() > 99) {
                            holder.unReadMsgCount.setText(this.mContext.getResources().getString(string.rc_message_unread_count));
                        } else {
                            holder.unReadMsgCount.setText(Integer.toString(data.getUnReadMessageCount()));
                        }

                        holder.unReadMsgCount.setVisibility(0);
                        holder.unReadMsgCountIcon.setImageResource(drawable.rc_unread_count_bg);
                    } else {
                        holder.unReadMsgCount.setVisibility(8);
                        holder.unReadMsgCountIcon.setImageResource(drawable.rc_unread_remind_without_count);
                    }
                } else {
                    holder.unReadMsgCountIcon.setVisibility(8);
                    holder.unReadMsgCount.setVisibility(8);
                }

                holder.rightImageLayout.setVisibility(8);
            } else if (tag.portraitPosition() == 2) {
                holder.rightImageLayout.setVisibility(0);
                holder.rightImageLayout.setOnClickListener(new OnClickListener() {
                    public void onClick(View v) {
                        if (ConversationListAdapter.this.mOnPortraitItemClick != null) {
                            ConversationListAdapter.this.mOnPortraitItemClick.onPortraitItemClick(v, data);
                        }

                    }
                });
                holder.rightImageLayout.setOnLongClickListener(new OnLongClickListener() {
                    public boolean onLongClick(View v) {
                        if (ConversationListAdapter.this.mOnPortraitItemClick != null) {
                            ConversationListAdapter.this.mOnPortraitItemClick.onPortraitItemLongClick(v, data);
                        }

                        return true;
                    }
                });
                if (data.getConversationType().equals(ConversationType.GROUP)) {
                    holder.rightImageView.setDefaultDrawable(v.getContext().getResources().getDrawable(drawable.rc_default_group_portrait));
                } else if (data.getConversationType().equals(ConversationType.DISCUSSION)) {
                    holder.rightImageView.setDefaultDrawable(v.getContext().getResources().getDrawable(drawable.rc_default_discussion_portrait));
                } else {
                    holder.rightImageView.setDefaultDrawable(v.getContext().getResources().getDrawable(drawable.rc_default_portrait));
                }

                if (data.getIconUrl() != null) {
                    holder.rightImageView.setResource(new Resource(data.getIconUrl()));
                } else {
                    holder.rightImageView.setResource((Resource) null);
                }

                if (data.getUnReadMessageCount() > 0) {
                    holder.unReadMsgCountRightIcon.setVisibility(0);
                    if (data.getUnReadType().equals(UnreadRemindType.REMIND_WITH_COUNTING)) {
                        holder.unReadMsgCount.setVisibility(0);
                        if (data.getUnReadMessageCount() > 99) {
                            holder.unReadMsgCountRight.setText(this.mContext.getResources().getString(string.rc_message_unread_count));
                        } else {
                            holder.unReadMsgCountRight.setText(Integer.toString(data.getUnReadMessageCount()));
                        }

                        holder.unReadMsgCountRightIcon.setImageResource(drawable.rc_unread_count_bg);
                    } else {
                        holder.unReadMsgCount.setVisibility(8);
                        holder.unReadMsgCountRightIcon.setImageResource(drawable.rc_unread_remind_without_count);
                    }
                } else {
                    holder.unReadMsgCountIcon.setVisibility(8);
                    holder.unReadMsgCount.setVisibility(8);
                }

                holder.leftImageLayout.setVisibility(8);
            } else {
                if (tag.portraitPosition() != 3) {
                    throw new IllegalArgumentException("the portrait position is wrong!");
                }

                holder.rightImageLayout.setVisibility(8);
                holder.leftImageLayout.setVisibility(8);
            }

            RLog.d(this, "leftImageLayout", "position:" + position + " Visibility:" + holder.leftImageLayout.getVisibility());
        }
    }

    public void setOnPortraitItemClick(ConversationListAdapter.OnPortraitItemClick onPortraitItemClick) {
        this.mOnPortraitItemClick = onPortraitItemClick;
    }

    public interface OnPortraitItemClick {
        void onPortraitItemClick(View var1, UIConversation var2);

        boolean onPortraitItemLongClick(View var1, UIConversation var2);
    }

    class ViewHolder {
        View layout;
        View leftImageLayout;
        View rightImageLayout;
        AsyncImageView leftImageView;
        TextView unReadMsgCount;
        ImageView unReadMsgCountIcon;
        AsyncImageView rightImageView;
        TextView unReadMsgCountRight;
        ImageView unReadMsgCountRightIcon;
        ProviderContainerView contentView;

        ViewHolder() {
        }
    }
}
