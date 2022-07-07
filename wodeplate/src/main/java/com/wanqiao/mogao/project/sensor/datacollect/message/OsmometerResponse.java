package com.wanqiao.mogao.project.sensor.datacollect.message;


/**
 * 渗压机获取传感觉地址请求体
 */
public class OsmometerResponse extends OsmometerBaseProtocol{


    /**
     * 1 查询地址， 2 获取指标
     */
    private int requestType;

    /**
     * 温度  16进制 2个字节
     */
    private String temperature;

    /**
     * 水压  16进制 4个字节
     */
    private String waterPressure;


    /**
     * 水深 16进制 4个字节
     */
    private String depth;

    /**
     * 1 功能代码错误 指定了不存在的功能码
     * 2 寄存器地址出错 指定了不能使用的寄存器号的相对地址
     * 3 寄存器数量出错 指定的寄存器号超出其存在的范
     */
    private int messageState;


    @Override
    public byte[] getMessageBody() {
        return new byte[] {};
    }

    public int getRequestType() {
        return requestType;
    }

    public void setRequestType(int requestType) {
        this.requestType = requestType;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getWaterPressure() {
        return waterPressure;
    }

    public void setWaterPressure(String waterPressure) {
        this.waterPressure = waterPressure;
    }

    public String getDepth() {
        return depth;
    }

    public void setDepth(String depth) {
        this.depth = depth;
    }

    public int getMessageState() {
        return messageState;
    }

    public void setMessageState(int messageState) {
        this.messageState = messageState;
    }

    @Override
    public String toString() {
        return "OsmometerResponse{" +
                "requestType=" + requestType +
                ", temperature='" + temperature + '\'' +
                ", waterPressure='" + waterPressure + '\'' +
                ", depth='" + depth + '\'' +
                ", messageState=" + messageState +
                '}';
    }
}
