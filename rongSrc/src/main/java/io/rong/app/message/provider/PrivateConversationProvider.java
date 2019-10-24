package io.rong.app.message.provider;

import android.content.Context;
import android.net.Uri;
import android.os.Parcelable;
import android.view.View;
import android.view.ViewGroup;
import io.rong.imkit.model.ConversationProviderTag;
import io.rong.imkit.widget.provider.IContainerItemProvider;

/**
 * Created by Bob on 2015/4/17.
 */
@SuppressWarnings("rawtypes")
@ConversationProviderTag(conversationType = "private", portraitPosition = 2)
public class PrivateConversationProvider implements
		IContainerItemProvider.ConversationProvider {

	public PrivateConversationProvider() {
		super();
	} 

	@Override
	public void bindView(View arg0, int arg1, Parcelable arg2) {
	}

	@Override
	public View newView(Context arg0, ViewGroup arg1) {
		return null;
	}

	@Override
	public Uri getPortraitUri(String arg0) {
		return null;
	}

	@Override
	public String getTitle(String arg0) {
		return null;
	}
}
