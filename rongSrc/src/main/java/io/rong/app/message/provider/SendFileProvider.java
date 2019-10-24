package io.rong.app.message.provider;

import io.rong.app.R;
import io.rong.app.message.FileMessage;
import io.rong.imkit.RongContext;
import io.rong.imkit.RongIM;
import io.rong.imkit.widget.provider.InputProvider;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Conversation;
import io.rong.imlib.model.Message;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;

import com.jone.SDCard.DataFile;
import com.jone.SDCard.MainActivity;

/**
 * Created by AMing on 15/12/24. Company RongCloud
 */
public class SendFileProvider extends InputProvider.ExtendProvider {
	private static final String TAG = SendFileProvider.class.getSimpleName();
	private Context context;

	/**
	 * 实例化适配器。
	 *
	 * @param context
	 *            融云IM上下文。（通过 RongContext.getInstance() 可以获取）
	 */
	public SendFileProvider(RongContext context) {
		super(context);
		this.context = context;
	}

	@Override
	public Drawable obtainPluginDrawable(Context context) {
		return context.getResources().getDrawable(R.drawable.yslt_ic_file_n);
	}

	@Override
	public CharSequence obtainPluginTitle(Context context) {
		return "文件";
	}

	@Override
	public void onPluginClick(View view) {
		startActivityForResult(new Intent(context, MainActivity.class), 1);
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (data != null) {
			DataFile file = (DataFile) data.getSerializableExtra("data");
			Conversation conversation = getCurrentConversation();
			sendFile(conversation.getConversationType(), file);
		}
	}

	@SuppressWarnings("deprecation")
	private void sendFile(Conversation.ConversationType conversationType,
			DataFile file) {
		if (RongIM.getInstance() != null
				&& RongIM.getInstance().getRongIMClient() != null) {
			RongIM.getInstance()
					.getRongIMClient()
					.sendMessage(
							getCurrentConversation().getConversationType(),
							getCurrentConversation().getTargetId(),
							FileMessage.obtain(file.getFileSize(),
									file.getFileName(), file.getFileId()),
							null, null,
							new RongIMClient.SendImageMessageCallback() {
								@Override
								public void onAttached(Message message) {

									Log.e(TAG,
											"-------------onAttached--------");
								}

								@Override
								public void onError(Message message,
										RongIMClient.ErrorCode code) {
									Log.e(TAG, "----------------onError-----"
											+ code);
								}

								@Override
								public void onSuccess(Message message) {
									Log.e(TAG, "------------------onSuccess---");
								}

								@Override
								public void onProgress(Message message,
										int progress) {
									Log.e(TAG,
											"-----------------onProgress----"
													+ progress);

								}
							});
		}
	}
}
