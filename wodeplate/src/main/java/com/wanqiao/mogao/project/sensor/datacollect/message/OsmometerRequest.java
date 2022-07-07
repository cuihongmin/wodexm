package com.wanqiao.mogao.project.sensor.datacollect.message;

/**
 * 渗压计请求体
 */
public class OsmometerRequest extends OsmometerBaseProtocol{

    /**
     * 寄存器高位地址
     */
    private int registerHAddress;

    /**
     * 寄存器地位地址
     */
    private int registerLAddress;

    /**
     * 寄存器数量高位
     */
    private int registerHnum;

    /**
     * 寄存器数量地位
     */
    private int registerLnum;


    /**
     *
     * @return
     */
    @Override
    public byte[] getMessageBody() {
        return new byte[]{(byte) getAddress(), (byte) getFunctionCode(),
                (byte) getRegisterHAddress(), (byte) getRegisterLAddress(),
                (byte) getRegisterHnum(), (byte) getRegisterLnum()};
    }

    public int getRegisterHAddress() {
        return registerHAddress;
    }

    public void setRegisterHAddress(int registerHAddress) {
        this.registerHAddress = registerHAddress;
    }

    public int getRegisterLAddress() {
        return registerLAddress;
    }

    public void setRegisterLAddress(int registerLAddress) {
        this.registerLAddress = registerLAddress;
    }

    public int getRegisterHnum() {
        return registerHnum;
    }

    public void setRegisterHnum(int registerHnum) {
        this.registerHnum = registerHnum;
    }

    public int getRegisterLnum() {
        return registerLnum;
    }

    public void setRegisterLnum(int registerLnum) {
        this.registerLnum = registerLnum;
    }
}
