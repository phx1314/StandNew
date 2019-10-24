package io.rong.app.message;

import io.rong.common.ParcelUtils;
import io.rong.imlib.MessageTag;
import io.rong.imlib.model.MessageContent;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;

import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.util.Log;

/**
 * Created by Bob on 15/12/24.
 */
@SuppressLint("ParcelCreator")
@MessageTag(value = "UDOWS:FileMsg", flag = MessageTag.ISCOUNTED
		| MessageTag.ISPERSISTED)
public class FileMessage extends MessageContent implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String fileSize;
	private String fileName;
	private String fileId;

	public String getFileSize() {
		return fileSize;
	}

	public void setFileSize(String fileSize) {
		this.fileSize = fileSize;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	public FileMessage(String fileSize, String fileName, String fileId) {
		this.fileSize = fileSize;
		this.fileName = fileName;
		this.fileId = fileId;
	}

	public FileMessage(byte[] data) {
		String jsonStr = null;

		try {
			jsonStr = new String(data, "UTF-8");
		} catch (UnsupportedEncodingException e1) {

		}

		try {
			JSONObject jsonObj = new JSONObject(jsonStr);
			setFileSize(jsonObj.getString("fileSize"));
			setFileName(jsonObj.getString("fileName"));
			setFileId(jsonObj.getString("fileId"));
		} catch (JSONException e) {
			Log.e("JSONException", e.getMessage());
		}
	}

	@Override
	public byte[] encode() {
		JSONObject jsonObj = new JSONObject();
		try {
			jsonObj.put("fileSize", fileSize);
			jsonObj.put("fileName", fileName);
			jsonObj.put("fileId", fileId);
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
	public FileMessage(Parcel in) {
		setFileSize(ParcelUtils.readFromParcel(in));
		setFileName(ParcelUtils.readFromParcel(in));
		setFileId(ParcelUtils.readFromParcel(in));
	}

	/**
	 * 读取接口，目的是要从Parcel中构造一个实现了Parcelable的类的实例处理。
	 */
	public static final Creator<FileMessage> CREATOR = new Creator<FileMessage>() {

		@Override
		public FileMessage createFromParcel(Parcel source) {
			return new FileMessage(source);
		}

		@Override
		public FileMessage[] newArray(int size) {
			return new FileMessage[size];
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
	public static FileMessage obtain(String fileSize, String fileName,
			String fileId) {
		FileMessage obj = new FileMessage(fileSize, fileName, fileId);
		return obj;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		ParcelUtils.writeToParcel(dest, fileSize);
		ParcelUtils.writeToParcel(dest, fileName);
		ParcelUtils.writeToParcel(dest, fileId);
	}

}