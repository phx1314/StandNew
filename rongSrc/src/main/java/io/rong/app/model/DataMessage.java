package io.rong.app.model;

import java.io.Serializable;

import io.rong.imlib.model.Conversation.ConversationType;

public class DataMessage implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String message;
	private String hbId;
	private String userName;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getHbId() {
		return hbId;
	}

	public void setHbId(String hbId) {
		this.hbId = hbId;
	}

	 

	public DataMessage(String message, String hbId, String userName) {
		super();
		this.message = message;
		this.hbId = hbId;
		this.userName = userName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}
