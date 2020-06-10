package com.hqyj.demo.modules.account.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name="user")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer userId;
	private String userName;
	private String password;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date createDate;

	@Transient
	private boolean rememberMe;
	@Transient
	private List<Role> roles;

	public Integer getUserId() {
		return userId;
	}
	

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public User(Integer userId, String userName, Date createDate) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.createDate = createDate;
	}
	
	

	public User(Integer userId, String userName, String password, Date createDate) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.password = password;
		this.createDate = createDate;
	}


	public User(Integer userId, String userName, String password, Date createDate, boolean rememberMe,
			List<Role> roles) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.password = password;
		this.createDate = createDate;
		this.rememberMe = rememberMe;
		this.roles = roles;
	}


	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public boolean getRememberMe() {
		return rememberMe;
	}

	public void setRememberMe(boolean rememberMe) {
		this.rememberMe = rememberMe;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

}