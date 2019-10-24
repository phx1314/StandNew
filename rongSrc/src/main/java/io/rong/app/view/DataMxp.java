package io.rong.app.view;

import java.io.Serializable;

import io.rong.imlib.model.Conversation.ConversationType;

public class DataMxp implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ConversationType type;
	private String name;

	public ConversationType getType() {
		return type;
	}

	public void setType(ConversationType type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public DataMxp(ConversationType type, String name) {
		super();
		this.type = type;
		this.name = name;
	}

}
