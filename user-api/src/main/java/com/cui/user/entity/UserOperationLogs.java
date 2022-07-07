package com.cui.user.entity;

import java.io.Serializable;
import java.util.Date;

public class UserOperationLogs implements Serializable {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer id;
    private String oldData;//修改前的数据
    private Date createTime;
    private String updateData;//修改后的数据
    private String operation;//登录，修改密码
	private Integer uid;
    
    public UserOperationLogs() {
        super();
    }
    
	public UserOperationLogs(Integer id, String oldData, Date createTime, String updateData, String operation) {
		super();
		this.id = id;
		this.oldData = oldData;
		this.createTime = createTime;
		this.updateData = updateData;
		this.operation = operation;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getOldData() {
		return oldData;
	}

	public void setOldData(String oldData) {
		this.oldData = oldData;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getUpdateData() {
		return updateData;
	}

	public void setUpdateData(String updateData) {
		this.updateData = updateData;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}
}
