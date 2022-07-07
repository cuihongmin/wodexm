package com.wanqiao.mogao.common.utils.primarykey;


/**
 * @author liufp
 * @date 20200507
 * <p>
 * 主键生成工具类
 */
public class WanqiaoPrimaryKeyUtils {

    private static final SnowflakeIdWorker snowFlakeIdWorker = new SnowflakeIdWorker(1, 1);

    public static String getSerialNumberString() {

        return String.valueOf(snowFlakeIdWorker.nextId());
    }

    public static Long getSerialNumberLong() {
        return snowFlakeIdWorker.nextId();
    }

    public static void main(String[] args) {
        System.out.println("args = [" + getSerialNumberString() + "]");
    }
}
