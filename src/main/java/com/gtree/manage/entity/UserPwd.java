package com.gtree.manage.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

/**
 * 
 * @author SrinivasuluP Entity class representing UserPwd. password is stored as
 *         Base64 encoded format This table is queried to know if the user
 *         exists for the given email, and corresponding reset password is
 *         updated
 */

@Entity
@Table(name = "User_Pwd")
@Data
public class UserPwd {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique = true)
	private String email;

	@Column
	private String pwd;

}
