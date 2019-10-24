package io.rong.app.message;

import android.os.Parcel;
import android.util.Log;

import com.sea_monster.common.ParcelUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

import io.rong.imlib.MessageTag;
import io.rong.imlib.model.MessageContent;
import io.rong.imlib.model.UserInfo;

/**
 * Created by Bob on 2015/4/19.
 */
@MessageTag(value = "UDOWS:LOCATION", flag = MessageTag.ISCOUNTED
		| MessageTag.ISPERSISTED)
public class LocationMessage extends MessageContent {

	private double lat;
	private double lng;
	private String address;

	public LocationMessage(double lat, double lng, String address) {
		this.lat = lat;
		this.lng = lng;
		this.address = address;
	}

	public LocationMessage(byte[] data) {
		String jsonStr = null;

		try {
			jsonStr = new String(data, "UTF-8");
		} catch (UnsupportedEncodingException e1) {

		}

		try {
			JSONObject jsonObj = new JSONObject(jsonStr);
			setLat(jsonObj.getDouble("lat"));
			setLng(jsonObj.getDouble("lng"));
			setAddress(jsonObj.getString("address"));
		} catch (JSONException e) {
			Log.e("JSONException", e.getMessage());
		}
	}

	@Override
	public byte[] encode() {
		JSONObject jsonObj = new JSONObject();
		try {
			jsonObj.put("lat", lat);
			jsonObj.put("lng", lng);
			jsonObj.put("address", address);
		} catch (JSONException e) {
			Log.e("JSONException", e.getMessage());
		}

		try {
			return jsonObj.toString().getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 构造函数。
	 *
	 * @param in
	 *            初始化传入的 Parcel。
	 */
	public LocationMessage(Parcel in) {
		setLat(ParcelUtils.readDoubleFromParcel(in));
		setLng(ParcelUtils.readDoubleFromParcel(in));
		setAddress(ParcelUtils.readFromParcel(in));
	}

	/**
	 * 读取接口，目的是要从Parcel中构造一个实现了Parcelable的类的实例处理。
	 */
	public static final Creator<LocationMessage> CREATOR = new Creator<LocationMessage>() {

		@Override
		public LocationMessage createFromParcel(Parcel source) {
			return new LocationMessage(source);
		}

		@Override
		public LocationMessage[] newArray(int size) {
			return new LocationMessage[size];
		}
	};

	@Override
	public int describeContents() {
		return 0;
	}

	/**
	 * 创建消息实例。
	 *
	 * @param name
	 *            获取命令名。
	 * @param data
	 *            设置命令数据，可以为任意格式，如 JSON。
	 * @return 消息实例。
	 */
	public static LocationMessage obtain(double lat, double lng, String address) {
		LocationMessage obj = new LocationMessage(lat, lng, address);
		return obj;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		ParcelUtils.writeToParcel(dest, lat);
		ParcelUtils.writeToParcel(dest, lng);
		ParcelUtils.writeToParcel(dest, address);
	}

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public double getLng() {
		return lng;
	}

	public void setLng(double lng) {
		this.lng = lng;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
