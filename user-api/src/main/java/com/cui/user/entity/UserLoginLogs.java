package com.cui.user.entity;

import java.io.Serializable;
import java.util.Date;

public class UserLoginLogs implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private Integer uid;
	private Date loginTime;
	private String loginIP;
	private String channel;
	private String sessionId;
	public UserLoginLogs(){
		super();
	}

	public UserLoginLogs(Integer id, Integer uid, Date loginTime, String loginIP, String channel) {
		super();
		this.id = id;
		this.uid = uid;
		this.loginTime = loginTime;
		this.loginIP = loginIP;
		this.channel = channel;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public Date getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}

	public String getLoginIP() {
		return loginIP;
	}

	public void setLoginIP(String loginIP) {
		this.loginIP = loginIP;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

}
