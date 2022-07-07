package com.cui.user.entity;

import java.io.Serializable;

public class SmsSendLogs implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 8724336459040544810L;
	private Integer id;
    private java.util.Date createTime;
    private String mobile;
    private String content;
    private Integer status = 0;
    
    public SmsSendLogs() {
        super();
    }
    public SmsSendLogs(Integer id, java.util.Date createTime, String mobile, String content) {
        super();
        this.id = id;
        this.createTime = createTime;
        this.mobile = mobile;
        this.content = content;
    }
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public java.util.Date getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(java.util.Date createTime) {
        this.createTime = createTime;
    }

    public String getMobile() {
        return this.mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}

}
