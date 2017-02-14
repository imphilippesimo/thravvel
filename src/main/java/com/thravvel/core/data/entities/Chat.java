/**
 * 
 */
package com.thravvel.core.data.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

/**
 * @author Philippe SIMO <philippechampion58@gmail.com>
 *
 */
@Entity
public class Chat extends BaseClass {
	@OneToMany
	private List<User> chaters;
	@OneToMany
	private List<Message> messages;
	private Date creationDate;

	/**
	 * @return the chaters
	 */
	public List<User> getChaters() {
		return chaters;
	}

	/**
	 * @param chaters
	 * @param messages
	 * @param creationDate
	 */
	public Chat(List<User> chaters, List<Message> messages, Date creationDate) {
		super();
		this.chaters = chaters;
		this.messages = messages;
		this.creationDate = creationDate;
	}

	/**
	 * @param chaters
	 *            the chaters to set
	 */
	public void setChaters(List<User> chaters) {
		this.chaters = chaters;
	}

	/**
	 * @return the messages
	 */
	public List<Message> getMessages() {
		return messages;
	}

	/**
	 * @param messages
	 *            the messages to set
	 */
	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}

	/**
	 * @return the creationDate
	 */
	public Date getCreationDate() {
		return creationDate;
	}

	/**
	 * @param creationDate
	 *            the creationDate to set
	 */
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Chat [chaters=" + chaters + ", messages=" + messages + ", creationDate=" + creationDate + "]";
	}

}
