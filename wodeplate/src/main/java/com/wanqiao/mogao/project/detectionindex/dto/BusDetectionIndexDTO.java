package com.wanqiao.mogao.project.detectionindex.dto;

import java.io.Serializable;

/**
 * @program: tailings-monitor
 * @description: 监控指标dto
 * @author: lixiangping
 * @create: 2021-05-11 16:43
 */
public class BusDetectionIndexDTO implements Serializable {
    /*
     * 监控指标表所需参数
     */
    private Long detectionIndexId;//修改使用
    private String detectionIndexName;//指标名称
    private Integer detectionIndexPriority;//优先级
    private String detectionIndexRemark;
    private Long detectionIndexTypeId;//监测类型id
    /*
     * 设备和监控指标关系表所需参数
     */
    private Long sensorId;//设备id
    private String sensorCode;//传感设备code
    private Long priority;//优先级

    @Override
    public String toString() {
        return "BusDetectionIndexDTO{" +
                "detectionIndexId='" + detectionIndexId + '\'' +
                "detectionIndexName='" + detectionIndexName + '\'' +
                ", detectionIndexPriority=" + detectionIndexPriority +
                ", detectionIndexRemark='" + detectionIndexRemark + '\'' +
                ", detectionIndexTypeId=" + detectionIndexTypeId +
                ", sensorId=" + sensorId +
                ", sensorCode='" + sensorCode + '\'' +
                ", priority=" + priority +
                '}';
    }

    public BusDetectionIndexDTO() {
    }

    public Long getDetectionIndexId() {
        return detectionIndexId;
    }

    public void setDetectionIndexId(Long detectionIndexId) {
        this.detectionIndexId = detectionIndexId;
    }

    public String getDetectionIndexName() {
        return detectionIndexName;
    }

    public void setDetectionIndexName(String detectionIndexName) {
        this.detectionIndexName = detectionIndexName;
    }

    public Integer getDetectionIndexPriority() {
        return detectionIndexPriority;
    }

    public void setDetectionIndexPriority(Integer detectionIndexPriority) {
        this.detectionIndexPriority = detectionIndexPriority;
    }

    public String getDetectionIndexRemark() {
        return detectionIndexRemark;
    }

    public void setDetectionIndexRemark(String detectionIndexRemark) {
        this.detectionIndexRemark = detectionIndexRemark;
    }

    public Long getDetectionIndexTypeId() {
        return detectionIndexTypeId;
    }

    public void setDetectionIndexTypeId(Long detectionIndexTypeId) {
        this.detectionIndexTypeId = detectionIndexTypeId;
    }

    public Long getSensorId() {
        return sensorId;
    }

    public void setSensorId(Long sensorId) {
        this.sensorId = sensorId;
    }

    public String getSensorCode() {
        return sensorCode;
    }

    public void setSensorCode(String sensorCode) {
        this.sensorCode = sensorCode;
    }

    public Long getPriority() {
        return priority;
    }

    public void setPriority(Long priority) {
        this.priority = priority;
    }
}
