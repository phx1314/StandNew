package io.rong.app.pop;

import java.net.URISyntaxException;

import io.rong.app.R;
import io.rong.app.ui.activity.SOSOLocationActivity;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.PopupWindow;
import android.widget.PopupWindow.OnDismissListener;

public class PopShowDtShaiXuan implements OnClickListener {

	private Context context;
	private LinearLayout mLinearLayout_baidu;
	private LinearLayout mLinearLayout_gaode;
	private LinearLayout mLinearLayout_apple;
	private View view;
	private PopupWindow popwindow;
	private View popview;
	private String lat;
	private String lng;

	public PopShowDtShaiXuan(Context context, View view, String lat, String lng) {
		super();
		this.context = context;
		this.view = view;
		this.lat = lat;
		this.lng = lng;
		LayoutInflater flater = LayoutInflater.from(this.context);
		popview = flater.inflate(R.layout.pop_yslt_dt__shaixuan, null);
		popwindow = new PopupWindow(popview, LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT);
		mLinearLayout_baidu = (LinearLayout) popview
				.findViewById(R.id.mLinearLayout_baidu);
		mLinearLayout_gaode = (LinearLayout) popview
				.findViewById(R.id.mLinearLayout_gaode);
		mLinearLayout_apple = (LinearLayout) popview
				.findViewById(R.id.mLinearLayout_apple);
		popwindow.setBackgroundDrawable(new BitmapDrawable(context
				.getResources()));
		popwindow.setTouchable(true);
		popwindow.setOutsideTouchable(true);
		popwindow.setFocusable(true);
		setClick();
	}

	private void setClick() {
		mLinearLayout_baidu.setOnClickListener(this);
		mLinearLayout_gaode.setOnClickListener(this);
		mLinearLayout_apple.setOnClickListener(this);
		popwindow.setOnDismissListener(new OnDismissListener() {
			@Override
			public void onDismiss() {
			}
		});

	}

	public void setType(int type) {

	}

	@SuppressLint("NewApi")
	public void show() {
		popwindow.showAsDropDown(view, 0, -(int) context.getResources()
				.getDimension(R.dimen.j17dp));
	}

	public void hide() {
		popwindow.dismiss();
	}

	public boolean isShow() {
		return popwindow.isShowing();
	}

	@Override
	public void onClick(View arg0) {
		hide();
		if (arg0.getId() == R.id.mLinearLayout_baidu) {// http://p.gdown.baidu.com/41044bd4fefb0146beeab83e3022080764eaae8deef939419635733d29aab7e11ff70ecd052938d163f875f8e29df04faed9e1c956bb11b138cb12f9f3ba87e3c58f29b5df2eeb110c4fc32710c7b7822e5318964907626b66c06796288902afbaa2a93b66a8a9e4edf50123ea8eb59001a5ce15405544a4dc98241ae600462518b282897edba7d8b3d5233187449ab7f495bc3ec23f8805
			if (checkApkExist(context, "com.baidu.BaiduMap")) {
				// go2App("com.baidu.BaiduMap");
				try {
					@SuppressWarnings("deprecation")
					Intent intent = Intent
							.getIntent("intent://map/direction?origin=latlng:"
									+ io.rong.app.F.lat
									+ ","
									+ io.rong.app.F.lng
									+ "|name:我的位置&destination=latlng:"
									+ lat
									+ ","
									+ lng
									+ "|name:"
									+ ""
									+ "&mode=drivingion=西安&referer=Autohome|GasStation#Intent;scheme=bdapp;package=com.baidu.BaiduMap;end");
					context.startActivity(intent
							.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)); // 启动调用
				} catch (URISyntaxException e) {
					e.printStackTrace();
				}

			} else {
				Uri uri = Uri
						.parse("http://p.gdown.baidu.com/41044bd4fefb0146beeab83e3022080764eaae8deef939419635733d29aab7e11ff70ecd052938d163f875f8e29df04faed9e1c956bb11b138cb12f9f3ba87e3c58f29b5df2eeb110c4fc32710c7b7822e5318964907626b66c06796288902afbaa2a93b66a8a9e4edf50123ea8eb59001a5ce15405544a4dc98241ae600462518b282897edba7d8b3d5233187449ab7f495bc3ec23f8805");
				Intent intent = new Intent(Intent.ACTION_VIEW, uri);
				intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				context.startActivity(intent);
			}
		} else if (arg0.getId() == R.id.mLinearLayout_gaode) {// http://mapdownload.autonavi.com/mobileapk/Amap_Android_V7.6.2.1034_GuanWang.apk
			if (checkApkExist(context, "com.autonavi.minimap")) {
				// go2App("com.autonavi.minimap");
				Intent intent = new Intent(
						"android.intent.action.VIEW",
						android.net.Uri
								.parse("androidamap://navi?sourceApplication=快方配送&lat="
										+ lat + "&lon=" + lng + "&dev=0"));
				// Intent intent = new Intent(
				// "android.intent.action.VIEW",
				// android.net.Uri
				// .parse("androidamap://showTraffic?sourceApplication=UU&poiid=BGVIS1&lat="
				// + lat
				// + "&lon="
				// + lng
				// + "&dev=0&style=2"));
				intent.setPackage("com.autonavi.minimap");
				intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				context.startActivity(intent);
			} else {
				Uri uri = Uri
						.parse("http://mapdownload.autonavi.com/mobileapk/Amap_Android_V7.6.2.1034_GuanWang.apk");
				Intent intent = new Intent(Intent.ACTION_VIEW, uri);
				intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				context.startActivity(intent);
			}
		} else if (arg0.getId() == R.id.mLinearLayout_apple) {
		}
	}

	public boolean checkApkExist(Context context, String packageName) {
		if (packageName == null || "".equals(packageName))
			return false;
		try {
			ApplicationInfo info = context.getPackageManager()
					.getApplicationInfo(packageName,
							PackageManager.GET_UNINSTALLED_PACKAGES);
			return true;
		} catch (NameNotFoundException e) {
			return false;
		}
	}

	public void go2App(String _ComponentName) {
		// Intent _Intent;
		// _Intent = new Intent();
		// _Intent.setComponent(_ComponentName);
		// context.startActivity(_Intent);
		PackageManager pm = context.getPackageManager();
		context.startActivity(pm.getLaunchIntentForPackage(_ComponentName));
		// PackageManager pm = this.getPackageManager();
		// // Return a List of all packages that are installed on the device.
		// List<PackageInfo> packages = pm.getInstalledPackages(0);
		// for (PackageInfo packageInfo : packages) {
		// // 判断系统/非系统应用
		// if ((packageInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM)
		// == 0) // 非系统应用
		// {
		// Log.i("info", packageInfo.applicationInfo.loadLabel(pm)
		// .toString() + packageInfo.packageName);
		// // if (packageInfo.applicationInfo.loadLabel(pm).toString()
		// // .contains("高德")) {
		// startActivity(pm
		// .getLaunchIntentForPackage("com.autonavi.minimap"));
		// // }
		// // AppInfo info = new AppInfo();
		// // info.appName = packageInfo.applicationInfo.loadLabel(pm)
		// // .toString();
		// // info.pkgName = packageInfo.packageName;
		// // info.appIcon = packageInfo.applicationInfo.loadIcon(pm);
		// // // 获取该应用安装包的Intent，用于启动该应用
		// // info.appIntent =
		// // pm.getLaunchIntentForPackage(packageInfo.packageName);
		// // appList.add(info);
		// } else {
		// // 系统应用　　　　　　　　
		// }
		//
		// }
	}
}
