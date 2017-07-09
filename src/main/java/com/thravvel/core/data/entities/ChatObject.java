package com.thravvel.core.data.entities;

public class ChatObject {

	private String userName;
	private String message;
	private Long date;

	public ChatObject() {
	}

	public ChatObject(String userName, String message, Long date) {
		super();
		this.userName = userName;
		this.message = message;
		this.date = date;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Long getDate() {
		return date;
	}

	public void setDate(Long date) {
		this.date = date;
	}

}