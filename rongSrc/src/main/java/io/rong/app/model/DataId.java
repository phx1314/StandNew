package io.rong.app.model;

import java.io.Serializable;

import io.rong.imlib.model.Conversation.ConversationType;

public class DataId implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String id;
	ConversationType type;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public ConversationType getType() {
		return type;
	}

	public void setType(ConversationType type) {
		this.type = type;
	}

	public DataId(String id, ConversationType type) {
		super();
		this.id = id;
		this.type = type;
	}

}
