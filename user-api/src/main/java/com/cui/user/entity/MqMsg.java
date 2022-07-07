package com.cui.user.entity;

import java.io.Serializable;

public class MqMsg implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1922171991831794799L;

	public MqMsg(){
		
	}
	
	
	public MqMsg(String mobile, String content, String type){
		this.content = content;
		this.mobile = mobile;
		this.type = type;
	}
	
	private String mobile;
	
	private String content;
	
	private String type;

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	
	
	
	
}
