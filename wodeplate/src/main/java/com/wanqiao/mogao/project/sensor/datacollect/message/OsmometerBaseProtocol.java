package com.wanqiao.mogao.project.sensor.datacollect.message;

/**
 * 渗压计基础协议
 */
public abstract class OsmometerBaseProtocol {

    //从机地址  1个字节
    private int address;
    //功能码 1个字节
    private int functionCode;
    //crc校验码  2个字节
    private int crcCode;

    public OsmometerBaseProtocol() {
    }

    public int getAddress() {
        return address;
    }

    public void setAddress(int address) {
        this.address = address;
    }

    public int getFunctionCode() {
        return functionCode;
    }

    public void setFunctionCode(int functionCode) {
        this.functionCode = functionCode;
    }

    public int getCrcCode() {
        return crcCode;
    }

    public void setCrcCode(int crcCode) {
        this.crcCode = crcCode;
    }

    /**
     * 获取报文除了校验码
     *
     * @return
     */
    public abstract byte[] getMessageBody();
}
