package io.rong.app.ui.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.maps.AMap;
import com.amap.api.maps.AMap.OnCameraChangeListener;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.LocationSource;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.CameraPosition;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.MarkerOptions;
import com.framewidget.view.Headlayout;

import io.rong.app.R;
import io.rong.app.pop.PopShowDtShaiXuan;

/**
 * Created by DragonJ on 14/11/21.
 */

@SuppressLint("ClickableViewAccessibility")
public class SOSOLocationActivity extends Activity implements LocationSource,
		OnCameraChangeListener {
	private MapView mapView;
	private AMap aMap;
	private OnLocationChangedListener mListener;
	private AMapLocationClient mlocationClient;
	private AMapLocationClientOption mLocationOption;
	private Headlayout mHeadlayout;
	private ImageView mImageView_dingwei;
	private double mlat_qian, mlog_qian;
	private static int ZOOMSIZE = 18;

	@SuppressLint("NewApi")
	public void peiZhi() {
		StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
				.detectDiskReads().detectDiskWrites().detectNetwork() // 这里可以替换为detectAll()
				.penaltyLog() // 打印logcat，当然也可以定位到dropbox，通过文件保存相应的log
				.build());
		StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
				.detectLeakedSqlLiteObjects() // 探测SQLite数据库操作
				.penaltyLog() // 打印logcat
				.penaltyDeath().build());
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mlat_qian = getIntent().getDoubleExtra("mlat", 0);
		mlog_qian = getIntent().getDoubleExtra("mlog", 0);
		peiZhi();
		setContentView(R.layout.de_ac_soso_map);
		mapView = (MapView) findViewById(R.id.map);
		mImageView_dingwei = (ImageView) findViewById(R.id.mImageView_dingwei);
		mHeadlayout = (Headlayout) findViewById(R.id.mHeadlayout);
		mHeadlayout.setLeftBackground(R.drawable.arrows_left);
		mHeadlayout.setGoBack(this);
		mHeadlayout.setTitle("定位");
		mHeadlayout.setRightBacgroud(R.drawable.yslt_ic_sandian_n);
		mHeadlayout.setRightOnclicker(new OnClickListener() {
			@Override
			public void onClick(View v) {
				PopShowDtShaiXuan mPopShowDtShaiXuan = new PopShowDtShaiXuan(
						getApplicationContext(), mHeadlayout.btn_right,
						mlat_qian + "", mlog_qian + "");
				mPopShowDtShaiXuan.show();
			}
		});
		mapView.onCreate(savedInstanceState);
		init();
		mImageView_dingwei.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (io.rong.app.F.lat != 0) {
					LatLng marker1 = new LatLng(io.rong.app.F.lat,
							io.rong.app.F.lng);
					// 设置中心点和缩放比例
					aMap.moveCamera(CameraUpdateFactory.changeLatLng(marker1));
					aMap.moveCamera(CameraUpdateFactory.zoomTo(ZOOMSIZE));
				}
			}
		});
		LatLng marker1 = new LatLng(mlat_qian, mlog_qian);
		// LatLng marker1 = new LatLng(31.2303930, 121.4737040);
		// 设置中心点和缩放比例
		aMap.moveCamera(CameraUpdateFactory.changeLatLng(marker1));
		aMap.moveCamera(CameraUpdateFactory.zoomTo(ZOOMSIZE));
		aMap.addMarker(new MarkerOptions().position(marker1).icon(
				BitmapDescriptorFactory
						.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));
	}

	/**
	 * 初始化AMap对象
	 */
	private void init() {
		if (aMap == null) {
			aMap = mapView.getMap();
			setUpmap();
		}
	}

	private void setUpmap() {
		aMap.moveCamera(CameraUpdateFactory.zoomTo(ZOOMSIZE));
		aMap.setOnCameraChangeListener(this);
		aMap.setLocationSource(this);
		aMap.getUiSettings().setZoomControlsEnabled(false);
		aMap.getUiSettings().setMyLocationButtonEnabled(false);// 设置默认定位按钮是否显示
		aMap.setMyLocationEnabled(true);
		// aMap.setMyLocationType(AMap.MAP_TYPE_NORMAL);
	}

	/**
	 * 方法必须重写
	 */
	@Override
	protected void onResume() {
		super.onResume();
		mapView.onResume();
	}

	/**
	 * 方法必须重写
	 */
	@Override
	protected void onPause() {
		super.onPause();
		mapView.onPause();
		deactivate();
	}

	/**
	 * 方法必须重写
	 */
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		mapView.onSaveInstanceState(outState);
	}

	/**
	 * 方法必须重写
	 */
	@Override
	protected void onDestroy() {
		super.onDestroy();
		mapView.onDestroy();
	}

	// /**
	// * 定位结束后返回的数据
	// */
	// @Override
	// public void onLocationChanged(AMapLocation amapLocation) {
	//
	// if (mListener != null && amapLocation != null) {
	// if (amapLocation != null && amapLocation.getErrorCode() == 0) {
	// mListener.onLocationChanged(amapLocation);// 显示系统小蓝点
	// } else {
	// String errText = "定位失败," + amapLocation.getErrorCode() + ": "
	// + amapLocation.getErrorInfo();
	// }
	// }
	// }
	//
	@Override
	public void activate(OnLocationChangedListener listener) {
		mListener = listener;
		if (mlocationClient == null) {
			mlocationClient = new AMapLocationClient(this);
			mLocationOption = new AMapLocationClientOption();
			// // 设置定位监听
			// mlocationClient.setLocationListener(this);
			// // 设置为高精度定位模式
			// mLocationOption.setLocationMode(AMapLocationMode.Hight_Accuracy);
			// // 设置定位参数
			// mlocationClient.setLocationOption(mLocationOption);
			// // 此方法为每隔固定时间会发起一次定位请求，为了减少电量消耗或网络流量消耗，
			// // 注意设置合适的定位时间的间隔（最小间隔支持为2000ms），并且在合适时间调用stopLocation()方法来取消定位请求
			// // 在定位结束后，在合适的生命周期调用onDestroy()方法
			// // 在单次定位情况下，定位无论成功与否，都无需调用stopLocation()方法移除请求，定位sdk内部会移除
			// mlocationClient.startLocation();
		}
	}

	@Override
	public void deactivate() {
		mListener = null;
		if (mlocationClient != null) {
			mlocationClient.stopLocation();
			mlocationClient.onDestroy();
		}
		mlocationClient = null;
	}

	@Override
	public void onCameraChange(CameraPosition arg0) {

	}

	/**
	 * 地图移动结束后停留的点
	 */
	@Override
	public void onCameraChangeFinish(final CameraPosition cameraPosition) {

	}

	public void loadLocationData(final double lat, final double lng) {

	}

	public void loadKeyData(final String key) {
	}

}
