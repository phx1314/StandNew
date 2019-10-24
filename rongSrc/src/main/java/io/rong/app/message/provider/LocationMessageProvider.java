package io.rong.app.message.provider;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v4.app.FragmentActivity;
import android.text.Spannable;
import android.text.SpannableString;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mdx.framework.Frame;
import com.mdx.framework.utility.handle.MHandler;

import io.rong.app.R;
import io.rong.app.message.LocationMessage;
import io.rong.app.ui.activity.SOSOLocationActivity;
import io.rong.imkit.RongContext;
import io.rong.imkit.RongIM;
import io.rong.imkit.model.ProviderTag;
import io.rong.imkit.model.UIMessage;
import io.rong.imkit.widget.ArraysDialogFragment;
import io.rong.imkit.widget.provider.IContainerItemProvider;
import io.rong.imlib.model.UserInfo;

/**
 * Created by Bob on 2015/4/17.
 */
@ProviderTag(messageContent = LocationMessage.class, showPortrait = true, centerInHorizontal = false, showProgress = true)
public class LocationMessageProvider extends
		IContainerItemProvider.MessageProvider<LocationMessage> {
	private MHandler handler;

	class ViewHolder {
		LinearLayout mLinearLayout_left;
		TextView mTextView;
		LinearLayout mLinearLayout_right;
		TextView mTextView_right;
	}

	@Override
	public View newView(Context context, ViewGroup group) {
		View view = LayoutInflater.from(context).inflate(
				R.layout.item_yslt_location, null);
		ViewHolder holder = new ViewHolder();
		holder.mLinearLayout_left = (LinearLayout) view
				.findViewById(R.id.mLinearLayout_left);
		holder.mLinearLayout_right = (LinearLayout) view
				.findViewById(R.id.mLinearLayout_right);
		holder.mTextView = (TextView) view.findViewById(R.id.mTextView);
		holder.mTextView_right = (TextView) view
				.findViewById(R.id.mTextView_right);
		view.setTag(holder);
		handler = new MHandler();
		handler.setId(this.getClass().getSimpleName());
		handler.setMsglisnener(new MHandler.HandleMsgLisnener() {
			@Override
			public void onMessage(android.os.Message msg) {
				switch (msg.what) {
				case 0:
					break;
				case 99:
					break;
				case 201:
					disposeMsg(msg.arg1, msg.obj);
					break;
				}
			}
		});
		Frame.HANDLES.add(handler);
		return view;
	}

	private void disposeMsg(int arg1, Object obj) {
		switch (arg1) {
		case 0:
			break;
		}
	}

	@Override
	public void bindView(View v, int position, LocationMessage content,
			UIMessage message) {
		ViewHolder holder = (ViewHolder) v.getTag();
		if (message.getMessageDirection() == UIMessage.MessageDirection.SEND) {// 消息方向，自己发送的
			holder.mLinearLayout_left.setVisibility(View.GONE);
			holder.mLinearLayout_right.setVisibility(View.VISIBLE);
		} else {
			holder.mLinearLayout_left.setVisibility(View.VISIBLE);
			holder.mLinearLayout_right.setVisibility(View.GONE);
		}
		holder.mTextView.setText(content.getAddress());
		holder.mTextView_right.setText(content.getAddress());
//		 AndroidEmoji.ensure((Spannable) holder.message.getText());
	}

	@Override
	public Spannable getContentSummary(LocationMessage data) {
		return new SpannableString("定位");
	}

	@Override
	public void onItemClick(View view, int position, LocationMessage content,
			UIMessage message) {
		Intent intent = new Intent(view.getContext(),
				SOSOLocationActivity.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		intent.putExtra("mlat", content.getLat());
		intent.putExtra("mlog", content.getLng());
		view.getContext().startActivity(intent);
	}

	@Override
	public void onItemLongClick(View view, int position,
			LocationMessage content, final UIMessage message) {

		String name = null;
		if (message.getSenderUserId() != null) {
			UserInfo userInfo = RongContext.getInstance().getUserInfoCache()
					.get(message.getSenderUserId());
			if (userInfo != null)
				name = userInfo.getName();
		}
		String[] items;

		Resources res = view.getContext().getResources();
		items = new String[] { res
				.getString(R.string.rc_dialog_item_message_delete) };

		ArraysDialogFragment
				.newInstance(name, items)
				.setArraysDialogItemListener(
						new ArraysDialogFragment.OnArraysDialogItemListener() {
							@Override
							public void OnArraysDialogItemClick(
									DialogInterface dialog, int which) {
								if (which == 0) {
									RongIM.getInstance()
											.getRongIMClient()
											.deleteMessages(
													new int[] { message
															.getMessageId() },
													null);
								}

							}
						})
				.show(((FragmentActivity) view.getContext())
						.getSupportFragmentManager());

	}

}
