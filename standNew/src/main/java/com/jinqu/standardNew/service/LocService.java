package com.jinqu.standardNew.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationClientOption.AMapLocationMode;
import com.amap.api.location.AMapLocationListener;
import com.jinqu.standardNew.F;

public class LocService extends Service implements AMapLocationListener {

    private AMapLocationClient mlocationClient;
    private AMapLocationClientOption mLocationOption;

    @SuppressWarnings("deprecation")
    @Override
    public void onCreate() {
        super.onCreate();
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
    public void onDestroy() {
        super.onDestroy();
        mlocationClient.stopLocation();
        mlocationClient.onDestroy();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    @Override
    public IBinder onBind(Intent arg0) {
        return null;
    }

    @Override
    public void onLocationChanged(AMapLocation location) {
        if (location == null)
            return;
        if (location.getLatitude() != 0 && location.getLongitude() != 0) {
            io.rong.app.F.lat = F.latitude = location.getLatitude();
            io.rong.app.F.lng = F.longitude = location.getLongitude();
            io.rong.app.F.address = location.getAddress();
        }
        if (location.getCity() == null)
            return;
        F.locationAddress = location.getCity() + location.getDistrict();
        if (location.getDistrict().endsWith("市")) {
            if (location.getDistrict().lastIndexOf("市") != -1) {
                F.city = location.getDistrict().substring(0,
                        location.getDistrict().length() - 1);
            } else {
                F.city = location.getDistrict();
            }
        } else {
            if (location.getCity().lastIndexOf("市") != -1) {
                F.city = location.getCity().substring(0,
                        location.getCity().length() - 1);
            } else {
                F.city = location.getCity();
            }
        }
        Log.i("坐标x", F.latitude + "");
        Log.i("坐标y", F.longitude + "");
        mlocationClient.stopLocation();
        mlocationClient.onDestroy();

    }

}