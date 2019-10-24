package io.rong.app.ui.activity;

import io.rong.app.R;
import io.rong.imkit.widget.AsyncImageView;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Conversation;
import io.rong.imlib.model.UserInfo;

import java.util.List;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationClientOption.AMapLocationMode;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMap.OnMarkerClickListener;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.sea_monster.resource.Resource;

/**
 * Created by zhjchen on 8/12/15.
 */
public abstract class LocationMapActivity extends BasicMapActivity implements
		OnMarkerClickListener, AMapLocationListener {
	private AMapLocationClient mlocationClient;
	private AMapLocationClientOption mLocationOption;

	protected Conversation.ConversationType conversationType;
	protected String targetId = null;

	@Override
	protected void initData() {

		mlocationClient = new AMapLocationClient(this);
		mLocationOption = new AMapLocationClientOption();
		// 设置定位监听
		mlocationClient.setLocationListener(this);
		// 设置为高精度定位模式
		mLocationOption.setLocationMode(AMapLocationMode.Hight_Accuracy);
		// 设置定位参数
		mlocationClient.setLocationOption(mLocationOption);
		// 此方法为每隔固定时间会发起一次定位请求，为了减少电量消耗或网络流量消耗，
		// 注意设置合适的定位时间的间隔（最小间隔支持为2000ms），并且在合适时间调用stopLocation()方法来取消定位请求
		// 在定位结束后，在合适的生命周期调用onDestroy()方法
		// 在单次定位情况下，定位无论成功与否，都无需调用stopLocation()方法移除请求，定位sdk内部会移除
		mlocationClient.startLocation();

	}

	@Override
	public void onLocationChanged(AMapLocation amapLocation) {
		if (amapLocation != null && amapLocation.getErrorCode() == 0) {
			// 获取位置信息
			Double geoLat = amapLocation.getLatitude();
			Double geoLng = amapLocation.getLongitude();

			RongIMClient.getInstance().updateRealTimeLocationStatus(
					conversationType, targetId, geoLat, geoLng);
		}

	}

	public void addMarker(LatLng latLng, final UserInfo userInfo) {

		final String url = userInfo.getPortraitUri().toString();

		final MarkerOptions markerOption = new MarkerOptions();

		markerOption.position(latLng);

		View view = LayoutInflater.from(this).inflate(
				R.layout.rc_icon_rt_location_marker, null);
		AsyncImageView imageView = (AsyncImageView) view
				.findViewById(android.R.id.icon);
		ImageView locImageView = (ImageView) view
				.findViewById(android.R.id.icon1);

		if (userInfo.getUserId().equals(
				RongIMClient.getInstance().getCurrentUserId())) {
			locImageView.setImageResource(R.drawable.rc_rt_loc_myself);
		} else {
			locImageView.setImageResource(R.drawable.rc_rt_loc_other);
		}

		imageView.setResource(new Resource(url));

		markerOption.anchor(0.5f, 0.5f).icon(
				BitmapDescriptorFactory.fromView(view));

		Marker marker = getaMap().addMarker(markerOption);
		marker.setObject(userInfo.getUserId());

	}

	public void removeMarker(String userId) {

		List<Marker> markers = getaMap().getMapScreenMarkers();

		for (Marker marker : markers) {
			if (marker.getObject() != null && userId.equals(marker.getObject())) {
				marker.remove();
				break;
			}
		}
	}

	public void moveMarker(final LatLng latLng, final UserInfo userInfo) {
		removeMarker(userInfo.getUserId());
		addMarker(latLng, userInfo);
	}

	/**
	 * 对marker标注点点击响应事件
	 */
	@Override
	public boolean onMarkerClick(final Marker marker) {
		return true;
	}

	/**
	 * 方法必须重写
	 */
	@Override
	protected void onDestroy() {
		if (mlocationClient != null) {
			mlocationClient.stopLocation();
			mlocationClient.onDestroy();
		}
		mlocationClient = null;
		super.onDestroy();
	}
}
