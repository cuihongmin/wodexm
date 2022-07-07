package com.cui.user.entity;

import java.io.Serializable;
import java.util.Date;

public class CertificatesFiles implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1014228233876905002L;

	private String id;

    private String modular;

    private Date createTime;

    private String fileFlow;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getModular() {
        return modular;
    }

    public void setModular(String modular) {
        this.modular = modular == null ? null : modular.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getFileFlow() {
        return fileFlow;
    }

    public void setFileFlow(String fileFlow) {
        this.fileFlow = fileFlow == null ? null : fileFlow.trim();
    }
}