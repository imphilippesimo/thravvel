/**
 * 
 */
package com.thravvel.core.data.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;

/**
 * @author Philippe SIMO <philippechampion58@gmail.com>
 *
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Message extends BaseClass {
	@Lob
	private String content;
	private Date time;
	@OneToOne
	@JoinColumn(name = "senderUserId", unique = true, nullable = false, updatable = false)
	private User sender;

	/**
	 * 
	 */
	public Message() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Message [content=" + content + ", time=" + time + ", sender=" + sender + "]";
	}

	/**
	 * @param content
	 * @param time
	 * @param sender
	 */
	public Message(String content, Date time, User sender) {
		super();
		this.content = content;
		this.time = time;
		this.sender = sender;
	}

	/**
	 * @param content
	 *            the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * @return the time
	 */
	public Date getTime() {
		return time;
	}

	/**
	 * @param time
	 *            the time to set
	 */
	public void setTime(Date time) {
		this.time = time;
	}

	/**
	 * @return the sender
	 */
	public User getSender() {
		return sender;
	}

	/**
	 * @param sender
	 *            the sender to set
	 */
	public void setSender(User sender) {
		this.sender = sender;
	}

}
