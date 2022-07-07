package com.cui.user.entity;

import java.io.Serializable;

public class SmsTemplate implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 6193891985411070173L;
	private Integer id;
    private java.util.Date createTime;
    private String creater;
    private String content;//短信内容
    private String typeKey;
    private Integer status;
    public SmsTemplate() {
        super();
    }
    public SmsTemplate(Integer id, java.util.Date createTime, String creater, String content, String typeKey, Integer status) {
        super();
        this.id = id;
        this.createTime = createTime;
        this.creater = creater;
        this.content = content;
        this.typeKey = typeKey;
        this.status = status;
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

    public String getCreater() {
        return this.creater;
    }

    public void setCreater(String creater) {
        this.creater = creater;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTypeKey() {
        return this.typeKey;
    }

    public void setTypeKey(String typeKey) {
        this.typeKey = typeKey;
    }

    public Integer getStatus() {
        return this.status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

}
