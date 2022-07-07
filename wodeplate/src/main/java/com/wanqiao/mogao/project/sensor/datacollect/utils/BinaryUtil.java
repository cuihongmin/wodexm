package com.wanqiao.mogao.project.sensor.datacollect.utils;

/**
 * 二进制计算工具
 */
public class BinaryUtil {

    /**
     * 16进制字符转换成 十进制(带符号)
     * @param hex
     * @return
     */
    public static long hexToDex(String hex) {
        String[] hexs = hex.split("");
        int frist = Integer.valueOf(hexs[0], 16);
        Long value = Long.valueOf(hex, 16);
        if(frist < 8) {
            return value;
        } else {
            //求反码
            String bin = Long.toBinaryString(value);
            String[] bins = bin.split("");
            StringBuilder sb = new StringBuilder();
            for(String i : bins) {
                if("0".equals(i)) {
                    sb.append("1");
                } else {
                    sb.append("0");
                }
            }
            return 0 - (Long.valueOf(sb.toString(), 2) + 1);
        }

    };

    public static void main(String[] args) {

        String a = "FF94";

        System.out.println(BinaryUtil.hexToDex(a));

    }
}
