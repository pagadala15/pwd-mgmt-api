package com.gtree.manage.events;

import java.util.Date;

/**
 * 
 * @author SrinivasuluP
 * This class reresents the PasswordResetEvent created when a successful password reset happens
 * Current it is logging the time of change and a msg indicating password reset
 */
public class PasswordResetEvent {

	String eventMessage;
	Date creationDate;

	public PasswordResetEvent() {
	}

	public PasswordResetEvent(String eventMessage, Date creationDate) {

		super();
		this.eventMessage = eventMessage;
		this.creationDate = creationDate;
	}

	public String getEventMessage() {
		return eventMessage;
	}

	public void setEventMessage(String eventMessage) {
		this.eventMessage = eventMessage;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
}
