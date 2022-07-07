package com.wanqiao.mogao.project.dfs.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.wanqiao.mogao.framework.aspectj.lang.annotation.Excel;
import com.wanqiao.mogao.framework.web.domain.BaseEntity;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 文件上传对象 sys_file
 * 
 * @author zhangguangbin
 * @date 2020-05-30
 */
@Data
public class SysFile extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private String uid;

    /** 文件名 */
    @Excel(name = "文件名")
    @JsonProperty(value ="name")
    private String name;

    /** 文件后缀名 */
    @Excel(name = "文件后缀名")
    @JsonProperty(value ="fileSuffix")
    private String fileSuffix;

    /** URL地址 */
    @Excel(name = "URL地址")
    @JsonProperty(value ="url")
    private String url;

    /** 服务商 */
    @Excel(name = "服务商")
    @JsonProperty(value ="businessId")
    private String businessId;

    /** $column.columnComment */
    @Excel(name = "服务商")
    @JsonProperty(value ="size")
    private Long size;

    /** 状态预留 */
    @Excel(name = "状态")
    @JsonProperty(value ="status")
    private String status;

    /** 0,删除,1,未删除 */
    @Excel(name = "0,删除,1,未删除")
    @JsonProperty(value ="isDel")
    private String isDel;










    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("uid", getUid())
            .append("name", getName())
            .append("fileSuffix", getFileSuffix())
            .append("url", getUrl())
            .append("createTime", getCreateTime())
            .append("createBy", getCreateBy())
            .append("businessId", getBusinessId())
            .append("size", getSize())
            .append("status", getStatus())
            .append("isDel", getIsDel())
            .toString();
    }
}
