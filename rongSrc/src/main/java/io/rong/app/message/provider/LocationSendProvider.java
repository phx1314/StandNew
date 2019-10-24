package io.rong.app.message.provider;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.HandlerThread;
import android.view.View;

import com.framewidget.view.Pois;
import com.mdx.framework.Frame;
import com.mdx.framework.utility.handle.MHandler;

import io.rong.app.F;
import io.rong.app.R;
import io.rong.imkit.RongContext;
import io.rong.imkit.RongIM;
import io.rong.imkit.widget.provider.InputProvider;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Message;

/**
 * Created by Bob on 15/6/6.
 */
public class LocationSendProvider extends InputProvider.ExtendProvider {
	HandlerThread mWorkThread;
	Handler mUploadHandler;
	private Context context;
	private MHandler handler;

	// Contacts

	public LocationSendProvider(RongContext context) {
		super(context);
		this.context = context;
		String className = this.getClass().getSimpleName();
		handler = new MHandler();
		handler.setId(className);
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
		mWorkThread = new HandlerThread("LocationSendProvider");
		mWorkThread.start();
		mUploadHandler = new Handler(mWorkThread.getLooper());
	}

	private void disposeMsg(int arg1, Object obj) {
		switch (arg1) {
		case 3:
			mUploadHandler.post(new MyRunnable(obj));
			break;
		}
	}

	/**
	 * 设置展示的图标
	 * 
	 * @param context
	 * @return
	 */
	@Override
	public Drawable obtainPluginDrawable(Context context) {
		return context.getResources().getDrawable(R.drawable.rc_ic_location);
	}

	/**
	 * 设置图标下的title
	 * 
	 * @param context
	 * @return
	 */
	@Override
	public CharSequence obtainPluginTitle(Context context) {
		return "定位";
	}

	/**
	 * click 事件
	 * 
	 * @param view
	 */
	@SuppressWarnings("deprecation")
	@Override
	public void onPluginClick(View view) {
		try {
			F.mCallBackOnly.go2AddressChoose("LocationSendProvider");
		} catch (Exception e) {
		}
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		 mUploadHandler.post(new MyRunnable(data.getStringExtra("path")));
	}

	class MyRunnable implements Runnable {
		Pois mPois;

		public MyRunnable(Object mPois) {
			this.mPois = (Pois) mPois;
		}

		@SuppressWarnings("deprecation")
		@Override
		public void run() {

			RongIM.getInstance()
					.getRongIMClient()
					.sendMessage(
							getCurrentConversation().getConversationType(),
							getCurrentConversation().getTargetId(),
							io.rong.app.message.LocationMessage.obtain(
									Double.valueOf(mPois.getLat()),
									Double.valueOf(mPois.getLng()),
									mPois.getTitle()), null, null,
							new RongIMClient.SendImageMessageCallback() {
								@Override
								public void onAttached(Message message) {
									// 保存数据库成功
								}

								@Override
								public void onError(Message message,
										RongIMClient.ErrorCode errorCode) {
									// 发送失败
								}

								@Override
								public void onSuccess(Message message) {
									// 发送成功
								}

								@Override
								public void onProgress(Message message, int i) {
									// 发送进度
								}
							});

		}
	}
}
