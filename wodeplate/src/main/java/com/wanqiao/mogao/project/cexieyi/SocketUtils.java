package com.wanqiao.mogao.project.cexieyi;


import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.text.DecimalFormat;

/**
 * socket工具类
 *
 * @author zhangguangbin
 * @date 2021-04-28
 */
public class SocketUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(SocketUtils.class);
    private static Socket socket = null;
    public static boolean connection() {
        if (socket != null) {
            return true;
        }

        try {
            socket = new Socket("192.168.100.229", 5001);
            return true;
        } catch (Exception e) {
            LOGGER.error("connection error", e);
            return false;
        }
    }

    public static void stop() {
        try {
            if (socket != null) {
                socket.close();
                socket = null;
            }
        } catch (Exception e) {
            LOGGER.error("connection error", e);
        }
    }

    /**
     * 发送数据
     *
     * @param cmd 需要发送的数据(十六进制的字符串形式)
     * @return 接受到的数据(十六进制的字符串形式)
     */
    public static String sendCmd(String cmd) {
        if (!connection() || socket == null) {
            return "error";
        }

        try {
            OutputStream out = socket.getOutputStream();
            byte[] hexStrToByteArrs = hexStrToByteArrs(cmd);
            String crc = getCRC(hexStrToByteArrs);
            byte[] hexStr = hexStrToByteArrs(cmd+crc);
            if (hexStrToByteArrs == null) {
                return "error";
            }
            out.write(hexStr);

            InputStream in = socket.getInputStream();
            byte[] buf = new byte[23];
            int len = in.read(buf);
            stop();
            return bytesToHexString(buf);
        } catch (IOException e) {
            LOGGER.error("sendCmd error", e);
            return "error";
        }
    }

    /**
     * 将十六进制的字符串转换成字节数组
     *
     * @param hexString
     * @return
     */
    public static byte[] hexStrToByteArrs(String hexString) {
        if (StringUtils.isEmpty(hexString)) {
            return null;
        }

        hexString = hexString.replaceAll(" ", "");
        int len = hexString.length();
        int index = 0;

        byte[] bytes = new byte[len / 2];

        while (index < len) {
            String sub = hexString.substring(index, index + 2);
            bytes[index / 2] = (byte) Integer.parseInt(sub, 16);
            index += 2;
        }

        return bytes;
    }

    /**
     * 数组转换成十六进制字符串
     *
     * @param bArray
     * @return HexString
     */
    public static final String bytesToHexString(byte[] bArray) {
        StringBuffer sb = new StringBuffer(bArray.length);
        String sTemp;
        for (int i = 0; i < bArray.length; i++) {
            sTemp = Integer.toHexString(0xFF & bArray[i]);
            if (sTemp.length() < 2){
                sb.append(0);
            }
            sb.append(sTemp.toUpperCase());
            // 在这里故意追加一个逗号便于最后的区分
            sb.append(" ");
        }
        return sb.toString();
    }

    /**
     * 十六进制字符串转二进制字符串
     * @param hex
     * @return
     */
    public static String hexToBin(String hex){
        String bin = "";
        String binFragment = "";
        int iHex;
        hex = hex.trim();
        hex = hex.replaceFirst("0x", "");

        for(int i = 0; i < hex.length(); i++){
            iHex = Integer.parseInt(""+hex.charAt(i),16);
            binFragment = Integer.toBinaryString(iHex);

            while(binFragment.length() < 4){
                binFragment = "0" + binFragment;
            }
            bin += binFragment;
        }
        return bin;
    }

    private static Long getOctFromHexString(String HexString){
        String s = hexToBin(HexString);
        long l = Long.parseLong(HexString, 16);
        if(s.startsWith("0")){
            return l;
        }else {
            return -l;
        }
    }

    /**
     * 计算CRC16校验码
     *
     * @param bytes
     * @return
     */
    public static String getCRC(byte[] bytes) {
        int CRC = 0x0000ffff;
        int POLYNOMIAL = 0x0000a001;

        int i, j;
        for (i = 0; i < bytes.length; i++) {
            CRC ^= ((int) bytes[i] & 0x000000ff);
            for (j = 0; j < 8; j++) {
                if ((CRC & 0x00000001) != 0) {
                    CRC >>= 1;
                    CRC ^= POLYNOMIAL;
                } else {
                    CRC >>= 1;
                }
            }
        }
        return Integer.toHexString(CRC);
    }

    /**
     * X 角度值：4 Byte int 型（保证小数后 6 位的精度），温度补偿后的角度值 x 1000
     *      000，计算：角度 = Ang_X / 1000000。如当前是 0x FF FB A9 70。先确定正数
     *      还是负数 FF，二进制首位代表符号，这里是 1，负数。之后 0xFFFBA970
     *      （4294682992）。之后用(2^32-4294682992)除以 1000000 就得出当前的角度值，之后加上符号为-0.284304。
     *      Y 角度值：4 Byte int 型（保证小数后 6 位的精度），温度补偿后的角度值 x 1000
     *      000，计算：角度 = Ang_X / 1000000。如当前是 0x 00 06 B4 E3。先确定正数
     *      还是负数 00，二进制首位代表符号，这里是 0，正数。之后 0x0006B4E3（439523）
     *      439523 除以 1000000 就得出当前的角度值为 0.439523。
     * @param Hex
     * @return
     */
    public static String getLongAndLat(String Hex) {
        String value ;
        double octValue = getOctFromHexString(Hex);
        //“0.000000”确定精度
        DecimalFormat df = new DecimalFormat("0.000000");
        if(octValue>0){
            value =  df.format(octValue / 1000000);
        }else {
            value = df.format(((1L << 32) + octValue) / 1000000);
        }
        return value;
    }
    public static void main(String[] args) {
        String res = sendCmd("00161AC20100");
        //解析返回结果(00 16 1A C2 81 00 00 00 00 00 00    0B 2E    FE 36 3C 80    00 DC 96 8B    58 35 )
        String[] resArray = res.split(" ");
        //温度
        String temptureHex = resArray[11]+resArray[12];
        //X角度值
        String XHex = resArray[13]+resArray[14]+resArray[15]+resArray[16];
        //Y角度值
        String YHex =  resArray[17]+resArray[18]+resArray[19]+resArray[20];
        System.out.println(getOctFromHexString(temptureHex) / 100);
        System.out.println(getLongAndLat(XHex));
        System.out.println(getLongAndLat(YHex));
       /* for (int i = 0; i < 10; i++) {
            System.out.println(sendCmd("2C 03 00 01 00 05 D2 74"));
        }*/

    }
}