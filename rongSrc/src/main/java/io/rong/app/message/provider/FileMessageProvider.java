package io.rong.app.message.provider;

import com.jone.SDCard.DataFile;

import io.rong.app.F;
import io.rong.app.R;
import io.rong.app.message.FileMessage;
import io.rong.imkit.RongContext;
import io.rong.imkit.RongIM;
import io.rong.imkit.model.ProviderTag;
import io.rong.imkit.model.UIMessage;
import io.rong.imkit.widget.ArraysDialogFragment;
import io.rong.imkit.widget.provider.IContainerItemProvider;
import io.rong.imlib.model.UserInfo;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.support.v4.app.FragmentActivity;
import android.text.Spannable;
import android.text.SpannableString;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by Bob on 15/12/24.
 */
@ProviderTag(messageContent = FileMessage.class, showPortrait = true, showProgress = true, centerInHorizontal = false)
public class FileMessageProvider extends
		IContainerItemProvider.MessageProvider<FileMessage> {
	/**
	 * 初始化View
	 */
	@Override
	public View newView(Context context, ViewGroup group) {
		View view = LayoutInflater.from(context).inflate(
				R.layout.item_yslt_file, group, false);

		ViewHolder holder = new ViewHolder();

		holder.mLinearLayout_left = (LinearLayout) view
				.findViewById(R.id.mLinearLayout_left);
		holder.mTextView_name = (TextView) view
				.findViewById(R.id.mTextView_name);
		holder.mTextView_content = (TextView) view
				.findViewById(R.id.mTextView_content);
		holder.mLinearLayout_right = (LinearLayout) view
				.findViewById(R.id.mLinearLayout_right);
		holder.mTextView_name_right = (TextView) view
				.findViewById(R.id.mTextView_name_right);
		holder.mTextView_content_right = (TextView) view
				.findViewById(R.id.mTextView_content_right);

		view.setTag(holder);
		return view;
	}

	@Override
	public void bindView(View v, int position, FileMessage content,
			UIMessage message) {
		final ViewHolder holder = (ViewHolder) v.getTag();
		if (message.getMessageDirection() == UIMessage.MessageDirection.SEND) {// 消息方向，自己发送的
			holder.mLinearLayout_left.setVisibility(View.GONE);
			holder.mLinearLayout_right.setVisibility(View.VISIBLE);
		} else {
			holder.mLinearLayout_left.setVisibility(View.VISIBLE);
			holder.mLinearLayout_right.setVisibility(View.GONE);
		}
		holder.mTextView_name.setText(content.getFileName());
		holder.mTextView_name_right.setText(content.getFileName());
		holder.mTextView_content.setText(content.getFileSize());
		holder.mTextView_content_right.setText(content.getFileSize());
	}

	@Override
	public Spannable getContentSummary(FileMessage data) {
		return new SpannableString("文件");
	}

	@Override
	public void onItemClick(View view, int position, FileMessage content,
			UIMessage message) {
		try {
			F.mCallBackOnly.go2FileDetail(content);
		} catch (Exception e) {
		}
	}

	@Override
	public void onItemLongClick(View view, int position, FileMessage content,
			final UIMessage message) {

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

	class ViewHolder {
		LinearLayout mLinearLayout_left;
		TextView mTextView_name;
		TextView mTextView_content;
		LinearLayout mLinearLayout_right;
		TextView mTextView_name_right;
		TextView mTextView_content_right;
	}

}