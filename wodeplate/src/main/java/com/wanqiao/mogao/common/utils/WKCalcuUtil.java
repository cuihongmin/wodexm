package com.wanqiao.mogao.common.utils;

import java.math.BigDecimal;

/**
 *
 *  尾矿库 检测点计算公式
 *
 * @author lzliufp
 *
 * @date  2021-04-20
 */

public class WKCalcuUtil {

    /**
     *  CRC 算法，高低位转换
     * @param bytes
     * @return
     */
    public static String getCRC(byte[] bytes) {
        int CRC = 0x0000ffff;
        int POLYNOMIAL = 0x0000a001;

        int i, j;
        for (i = 0; i < bytes.length; i++) {
            CRC ^= (int) bytes[i];
            for (j = 0; j < 8; j++) {
                if ((CRC & 0x00000001) == 1) {
                    CRC >>= 1;
                    CRC ^= POLYNOMIAL;
                } else {
                    CRC >>= 1;
                }
            }
        }
        //高低位转换
        CRC = ( (CRC & 0x0000FF00) >> 8) | ( (CRC & 0x000000FF ) << 8);
        return Integer.toHexString(CRC);
    }
    /**
     *
     * 浸润线高度计算   参考文档《浸润线计算方式》
     *
     * @param x2   地表到传感器的线长
     * @param x1    传感器测得值
     * @return  浸润线 高度
     */

    public static Float phreatic(Float x2,Float x1){


        if(x2==null){
            throw  new RuntimeException("地表到传感器的线长不能为空");
        }
        if(x1==null){
            throw  new RuntimeException("传感器测得值不能为空");
        }
        if(x2<x1){
            throw  new RuntimeException("地表到传感器的线长不能小于传感器测得值");
        }


        BigDecimal b1 = new BigDecimal(Float.toString(x1));
        BigDecimal b2 = new BigDecimal(Float.toString(x2));
        return b2.subtract(b1).floatValue();
    }

    /**
     *
     *
     * 坡比计算   参考《计算公式-模型》
     * @param Y1   1号物位计的高程
     * @param H1   1号物位计所测值
     * @param Y2   2号物位计的高程
     * @param H2   2号物位计所测值
     * @param X1   1号物位计到坝面距离X1
     * @param X2   两个设备之间的距离X2
     * @param Y4   超声波物位计水位标高Y4
     * @param H3   超声波物位计测得值H3
     *
     * @return  计算坡比SinQ
     */

    public static Float getSinQ(Float Y1,Float H1,Float Y2,Float H2,Float X1,Float X2 ,Float Y4,Float H3) {


        if (Y1 == null) {
            throw new RuntimeException("1号物位计的高程不能为空");
        }
        if (H1 == null) {
            throw new RuntimeException("1号物位计所测值不能为空");
        }

        if (Y2 == null) {
            throw new RuntimeException("2号物位计的高程不能为空");
        }
        if (H2 == null) {
            throw new RuntimeException("2号物位计所测值不能为空");
        }

        BigDecimal bY1 = new BigDecimal(Float.toString(Y1));
        BigDecimal bH1 = new BigDecimal(Float.toString(H1));
        BigDecimal bY2 = new BigDecimal(Float.toString(Y2));
        BigDecimal bH2 = new BigDecimal(Float.toString(H2));
        BigDecimal bX1 = new BigDecimal(Float.toString(X1));
        BigDecimal bX2 = new BigDecimal(Float.toString(X2));

        BigDecimal bY4 = new BigDecimal(Float.toString(Y4));
        BigDecimal bY3 = new BigDecimal(Float.toString(H3));

        // 计算 sinQ
        BigDecimal c1 = bY1.subtract(bH1);
        BigDecimal c2 = bY2.subtract(bH2);
        BigDecimal c3 = c1.subtract(c2);
        BigDecimal sinQ = c3.divide(bX2);

        return sinQ.floatValue();
    }

    /**
     *
     *
     * 干滩长度算法   参考《计算公式-模型》
     * @param Y1   1号物位计的高程
     * @param H1   1号物位计所测值
     * @param Y2   2号物位计的高程
     * @param H2   2号物位计所测值
     * @param X1   1号物位计到坝面距离X1
     * @param X2   两个设备之间的距离X2
     * @param Y4   超声波物位计水位标高Y4
     * @param H3   超声波物位计测得值H3
     *
     * @return  干滩长度
     */

    public static Float lengthOfDry(Float Y1,Float H1,Float Y2,Float H2,Float X1,Float X2 ,Float Y4,Float H3){


        if(Y1==null){
            throw  new RuntimeException("1号物位计的高程不能为空");
        }
        if(H1==null){
            throw  new RuntimeException("1号物位计所测值不能为空");
        }

        if(Y2==null){
            throw  new RuntimeException("2号物位计的高程不能为空");
        }
        if(H2==null){
            throw  new RuntimeException("2号物位计所测值不能为空");
        }

        BigDecimal bY1 = new BigDecimal(Float.toString(Y1));
        BigDecimal bH1 = new BigDecimal(Float.toString(H1));
        BigDecimal bY2 = new BigDecimal(Float.toString(Y2));
        BigDecimal bH2 = new BigDecimal(Float.toString(H2));
        BigDecimal bX1 = new BigDecimal(Float.toString(X1));
        BigDecimal bX2 = new BigDecimal(Float.toString(X2));

        BigDecimal bY4 = new BigDecimal(Float.toString(Y4));
        BigDecimal bY3 = new BigDecimal(Float.toString(H3));

        // 计算 sinQ
        BigDecimal c1= bY1.subtract(bH1);
        BigDecimal c2=bY2.subtract(bH2);
        BigDecimal c3=c1.subtract(c2);
        BigDecimal sinQ=c3.divide(bX2);

        //计算X3

        BigDecimal bX3=bY2.subtract(bH2).subtract(bY4.subtract(bY3));

        BigDecimal X3=bX3.divide(sinQ);

        return bX1.add(X3).add(bX2).floatValue();

    }


    /**
     *  计算新增干滩长度  参考《计算公式-模型》
     * @param Y1   1号物位计的高程
     * @param H1   1号物位计所测值
     * @param sinQ 坡比
     * @param X1   1号物位计到坝面距离X1
     * @param Y4   超声波物位计水位标高Y4
     * @param H3   超声波物位计测得值H3
     *
     * @return
     */
    public static  Float lengthOfDryAdd(Float Y1,Float H1,Float sinQ,Float X1,Float Y4,Float H3){

        if(Y1==null){
            throw  new RuntimeException("1号物位计的高程不能为空");
        }
        if(H1==null){
            throw  new RuntimeException("1号物位计所测值不能为空");
        }

        BigDecimal bY1 = new BigDecimal(Float.toString(Y1));
        BigDecimal bH1 = new BigDecimal(Float.toString(H1));
        BigDecimal bx1 = new BigDecimal(Float.toString(X1));


        BigDecimal bY4 = new BigDecimal(Float.toString(Y4));
        BigDecimal bY3 = new BigDecimal(Float.toString(H3));
        BigDecimal bsinQ=new BigDecimal(sinQ);

        //计算X2
        BigDecimal c4= bY1.subtract(bH1).subtract(bY4.subtract(bY3));
        BigDecimal x2=c4.divide(bsinQ);
        return  bx1.add(x2).floatValue();
    }

    public static void main(String[] args) {
        Float f1=2.0f;
        Float f2=0.8f;
        System.out.println("浸润线高度为：" + WKCalcuUtil.phreatic(f2,f1));
    }

}
