package io.rong.app.message;

import io.rong.imlib.RongIMClient.ErrorCode;
import io.rong.imlib.RongIMClient.SendMessageCallback;
import android.os.Handler;
import android.os.Message;

/**
 * 
 * 发送图片消息的回调。
 */

public abstract class MySendImageMessageCallback extends SendMessageCallback {

	/**
	 * 
	 * 消息已保存数据库。
	 * 
	 *
	 * 
	 * @param message
	 *            保存后的消息实体。
	 */

	public abstract void onAttached(Message message);

	/**
	 * 
	 * 消息发送失败。
	 * 
	 *
	 * 
	 * @param message
	 *            发送失败的消息实体。
	 * 
	 * @param code
	 *            失败错误码。
	 */

	public abstract void onError(Message message, ErrorCode code);

	/**
	 * 
	 * 消息发送成功。
	 * 
	 *
	 * 
	 * @param message
	 *            发送成功的消息实体。
	 */

	public abstract void onSuccess(Message message);

	/**
	 * 
	 * 消息发送进度。
	 * 
	 *
	 * 
	 * @param message
	 *            发送的消息实体。
	 * 
	 * @param progress
	 *            消息发送进度 0 - 100。
	 */

	public abstract void onProgress(Message message, int progress);

	void onProgressCallback(final Message message, final int progress) {

		new Handler().post(new Runnable() {

			@Override
			public void run() {

				onProgress(message, progress);

			}

		});

	}

	void onAttachedCallback(final Message message) {

		new Handler().post(new Runnable() {
			@Override
			public void run() {
				onAttached(message);

			}

		});

	}

	void onFail(final Message message, final ErrorCode code) {

		new Handler().post(new Runnable() {
			@Override
			public void run() {
				onError(message, code);

			}

		});

	}

	@Override
	public void onSuccess(Integer integer) {

	}

	@Override
	public void onError(Integer messageId, ErrorCode e) {

	}

}
