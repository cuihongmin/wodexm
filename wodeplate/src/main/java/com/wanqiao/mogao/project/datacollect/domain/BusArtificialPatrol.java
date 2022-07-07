package com.wanqiao.mogao.project.datacollect.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wanqiao.mogao.framework.aspectj.lang.annotation.Excel;
import com.wanqiao.mogao.framework.web.domain.BaseEntity;
import lombok.Data;

import java.util.Date;

/**
 * 人工巡检登记对象 bus_artificial_patrol
 * 
 * @author zhangguangbin
 * @date 2021-05-10
 */
@Data
public class BusArtificialPatrol extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 巡检时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "巡检时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date patrolTime;

    /** 巡检类别 */
    @Excel(name = "巡检类别")
    private String patrolTypeId;
    private String patrolTypeName;

    /** 安全评估 */
    @Excel(name = "安全评估")
    private String safetyAssessment;
    private String safetyAssessmentName;

    /** 巡查人员
 */
    @Excel(name = "巡查人员")
    private String patrolPerson;

    /** 巡检情况 */
    @Excel(name = "巡检情况")
    private String patrolDetails;

    /** 附件路径 */
    @Excel(name = "附件路径")
    private String files;

    /** 发布内容 */
    @Excel(name = "发布内容")
    private String releaseContent;

    /** 状态发布 */
    @Excel(name = "状态发布")
    private String stateRelease;
    private String stateReleaseName;

    /** 接受人 */
    @Excel(name = "接受人")
    private String acceptBy;
    /** 接受人姓名 */
    @Excel(name = "接受人姓名")
    private String acceptByName;

    /** 预警状态 1解除  0未解除 */
    @Excel(name = "预警状态 1解除  0未解除")
    private String warnState;

    /** 预警解除时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "预警解除时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date warnRelieveTime;

    /** 解除情况 */
    @Excel(name = "解除情况")
    private String relieveDetails;

    /** 解除人 */
    @Excel(name = "解除人")
    private String relieveBy;
    /** 解除人姓名*/
    @Excel(name = "解除人姓名")
    private String relieveByName;
}
