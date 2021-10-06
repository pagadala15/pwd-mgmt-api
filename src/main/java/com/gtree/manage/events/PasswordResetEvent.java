package com.gtree.manage.events;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @author SrinivasuluP This class represents the PasswordResetEvent created
 *         when a successful password reset happens Current it is logging the
 *         time of change and a msg indicating password reset
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PasswordResetEvent {

	String eventMessage;
	Date creationDate;
}
