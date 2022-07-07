package com.wanqiao.mogao.project.sensor.datacollect.osmometer;

/**
 * 渗压计功能码值
 */
public class OsmometerFunctionCode {

    /**
     * 查询从机通信地址
     */
    public static final byte[] QUERY_ADDRESS = new byte[]{(byte) 0xFF, (byte) 0x6E, (byte) 0x00, (byte) 0x12,
            (byte) 0x00, (byte) 0x01, (byte) 0x9C, (byte) 0x18};


    /**
     * 读取寄存器数据
     */
    public static final byte[] READ_REGISTER_DATE = new byte[]{
            (byte) 0x2C, (byte) 0x03, (byte) 0x00, (byte) 0x01, (byte) 0x00, (byte) 0x05,
            (byte) 0xD2, (byte) 0x74
    };

    /**
     *  读取测斜仪数据
     */
    public static final byte[] CXY = new byte[]{(byte) 0x00, (byte) 0x16, (byte) 0x1A,
            (byte) 0xC2, (byte) 0x01, (byte) 0x00, (byte) 0xAC, (byte) 0xEE};

}